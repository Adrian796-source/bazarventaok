package com.todocode.bazarventa.service;

import com.todocode.bazarventa.exceptiones.*;
import com.todocode.bazarventa.model.Cliente;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import com.todocode.bazarventa.repository.IProductoRepository;
import com.todocode.bazarventa.repository.IVentaRepository;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class
VentaService implements IVentaService {

    private final IVentaRepository ventaRepository;
    private final IProductoRepository productoRepository;
    private final ProductoService productoService;
    private final ClienteService clienteService;


    @Autowired
    public VentaService(IVentaRepository ventaRepository,
                        IProductoRepository productoRepository,
                        ClienteService clienteService,
                        ProductoService productoService
    ) {
        this.ventaRepository = ventaRepository;
        this.productoRepository = productoRepository;
        this.clienteService = clienteService;
        this.productoService = productoService;

    }


    @Override
    public Venta findVentaPorCodigo(Long codigo) {
        return ventaRepository.findByCodigo(codigo);
    }


//Implementa la logica para eliminar una venta por su codigo

    /**
     * Elimina una venta por su código.
     *
     * @param codigo El código de la venta a eliminar.
     */
    @Override
    public void deleteById(Long codigo) {
        Optional<Venta> ventaOptional = ventaRepository.findById(codigo);

        if (ventaOptional.isPresent()) {
            ventaRepository.deleteById(codigo);
        } else {
            throw new VentaNotFoundException("La venta con el código " + codigo + " no fue encontrada.");
        }

    }

    //Implementa la logica para editar (actualizar) una venta en la base de datos
    @Override
    public void editVenta(Venta venta) {
        ventaRepository.save(venta);// puedo utilizar el metodo save para actualizar una venta existente
    }

    // Implementa la logica para obtener ventas por una fecha especifica desde  la base de datos
    @Override
    public List<Venta> obtenerVentaPorFecha(LocalDate fecha) {
        return ventaRepository.findByFecha(fecha);
    }

    // Implementa la logica para obtener la venta con el monto mas alto desde la base de datos
    @Override
    public Venta obtenerVentaConMontoMasAlto() {
        return ventaRepository.findFirstByOrderByTotalDesc();
    }


    @Override
    @Transactional
    public List<Producto> obtenerProductosDeVenta(Long codigo) {
        Optional<Venta> ventaOptional = ventaRepository.findById(codigo);

        if (ventaOptional.isPresent()) {
            Venta venta = ventaOptional.get();
            return venta.getListaProductos();
        } else {
            throw new VentaNotFoundException("La venta con el código " + codigo + " no fue encontrada.");

        }

    }

    @Override
    public List<Venta> obtenerVentasPorFecha(LocalDate fecha) {
        return ventaRepository.findAllByFecha(fecha);
    }

    @Override
    public Long contarVentasPorFecha(LocalDate fecha) {
        return ventaRepository.countVentasByFecha(fecha);
    }

    @Override
    public Double obtenerSumatoriaMontoPorFecha(LocalDate fecha) {
        return ventaRepository.obtenerSumatoriaMontoPorFecha(fecha);
    }


    @Override
    @Transactional
    public Venta crearVentaConProductos(Venta venta) throws VentaDataAccessException, VentaValidationException { //Venta

        try {
            // Lista para almacenar los productos persistentes
            List<Producto> productosPersistentes = new ArrayList<>();

            // Iterar sobre los productos de la venta
            for (Producto productoExistente : venta.getListaProductos()) {
                if (productoExistente.getCodigoProducto() != null) {
                    productosPersistentes.add(productoRepository.findById(productoExistente.getCodigoProducto())
                            .orElseThrow(() -> new ProductoNotFoundException("Producto no encontrado con código: "
                                    + productoExistente.getCodigoProducto())));
                }
            }

            // Establecer la lista actualizada de productos persistentes en la venta
            venta.setListaProductos(productosPersistentes);

            // Calcular el total de la venta basado en los totales de los productos
            Double totalVenta = productoService.calcularTotalProductos(
                    productosPersistentes.stream().map(Producto::getCodigoProducto).toList()
            );
            venta.setTotal(totalVenta);

            // Obtener el cliente existente por su código
            Long codigoCliente = venta.getUnCliente().getCodigoCliente();
            Optional<Cliente> clienteExistenteOpt = clienteService.findByCodigoCliente(codigoCliente);

            if (clienteExistenteOpt.isPresent()) {
                Cliente clienteExistente = clienteExistenteOpt.get();
                // Asignar el cliente existente a la venta
                venta.setUnCliente(clienteExistente); // Aquí ya no hay error
            } else {
                // Manejar el caso en el que no se encontró el cliente
                throw new VentaValidationException("Cliente no encontrado con código: " + codigoCliente);
            }

            // Guardar la venta en el repositorio y obtener la venta persistida
            Venta ventaPersistida = ventaRepository.save(venta);

            // Se llama actualizarStock una vez realizada la venta
            productoService.actualizarStock(ventaPersistida);

            return ventaPersistida;
        } catch (org.springframework.dao.DataAccessException e) {
            throw new VentaDataAccessException("Error al crear la venta. Por favor, intente más tarde.", e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new VentaValidationException("Los datos proporcionados no son válidos.", e);
        } catch (ProductoNotFoundException e) {
            throw new VentaUnexpectedException("Producto no encontrado: " + e.getMessage(), e);
        } catch (Exception e) {
            throw new VentaUnexpectedException("Error inesperado al crear la venta.", e);
        }
    }


}
