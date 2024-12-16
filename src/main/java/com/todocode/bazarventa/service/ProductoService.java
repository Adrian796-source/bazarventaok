package com.todocode.bazarventa.service;


import com.todocode.bazarventa.exceptiones.ActualizarStockException;
import com.todocode.bazarventa.exceptiones.ProductoNotFoundException;
import com.todocode.bazarventa.exceptiones.StockNegativoException;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import com.todocode.bazarventa.repository.IProductoRepository;
import com.todocode.bazarventa.repository.IVentaRepository;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductoService implements IProductoService {


    private final IProductoRepository productoRepository;
    private final IVentaRepository ventaRepository;


    // Constructor donde se inyectan las dependencias
    @Autowired
    public ProductoService(IProductoRepository productoRepository, IVentaRepository ventaRepository) {
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;

    }


    @Override
    public void saveProducto(Producto producto) {
        productoRepository.save(producto);
    }


    @Override
    public Producto findCodigoProducto(Long codigoProducto) {
        return productoRepository.findByCodigoProducto(codigoProducto);

    }

    @Override
    public void editProducto(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public List<Producto> obtenerProductosFaltaStock(Long umbralStockMinimo) {
        // Usa umbralStockMinimo en tu lógica para filtrar productos con stock insuficiente.
        return productoRepository.findByCantidadDisponibleLessThan(umbralStockMinimo);
    }

    @Override
    @Transactional
    public void actualizarStock(Venta venta) throws ActualizarStockException {
        for (Producto producto : venta.getListaProductos()) {
            Long codigoProducto = producto.getCodigoProducto();

            try {
                // Disminuir la cantidad disponible del producto
                productoRepository.decrementarCantidadDisponible(codigoProducto);

                Producto productoActualizado = productoRepository.findByCodigoProducto(codigoProducto);
                if (productoActualizado.getCantidadDisponible() < 0) {
                    throw new StockNegativoException(codigoProducto);
                }

            } catch (StockNegativoException e) {
                // Relanzar la excepción sin log
                throw new ActualizarStockException("Stock negativo detectado al intentar actualizar el stock del producto con codigo " + codigoProducto, e);

            } catch (Exception e) {
                // Relanzar la excepción general sin log
                throw new ActualizarStockException("Error inesperado al actualizar el stock para el producto con código " + codigoProducto, e);
            }

        }

    }


    @Override
    @Transactional
    public void deleteProducto(Long codigoProducto) {
        Producto producto = productoRepository.findById(codigoProducto).orElse(null);
        if (producto != null) {
            eliminarVentasRelacionadas(producto);
            productoRepository.delete(producto);
        }
    }

    @Override
    @Transactional
    public Double calcularTotalProductos(List<Long> codigosProductos) {

        // Obtener la lista de productos por los códigos proporcionados


        // Calcular el total sumando los costos de los productos
        // Obtener la lista de productos por los códigos proporcionados y calcular el total directamente
        return productoRepository.findAllByCodigoProductoIn(codigosProductos)
                .stream()
                .mapToDouble(Producto::getCosto)
                .sum();


    }

    @Override
    @Transactional
    public void incrementarStock(Long codigoProducto, int cantidad) {
        // Verifica si la cantidad es válida
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }

        // Busca el producto
        Producto producto = productoRepository.findById(codigoProducto)
                .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con código: " + codigoProducto));

        // Obtener la cantidad disponible actual
        int cantidadDisponibleActual = producto.getCantidadDisponible();

        // Sumar la cantidad que deseas agregar
        int nuevaCantidadDisponible = cantidadDisponibleActual + cantidad;

        // Actualizar la cantidad disponible en el producto
        producto.setCantidadDisponible(nuevaCantidadDisponible);

        // Guardar el producto actualizado en la base de datos
        productoRepository.save(producto);
    }

    private void eliminarVentasRelacionadas(Producto producto) {
        // Obtener la lista de ventas asociadas al producto
        List<Venta> ventas = ventaRepository.findAllByListaProductos_CodigoProducto(producto.getCodigoProducto());

        // Eliminar cada venta
        for (Venta venta : ventas) {
            ventaRepository.deleteById(venta.getCodigo());
        }
    }


}   

   

   

        
    

    
   
   


