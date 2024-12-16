package com.todocode.bazarventa.serviceTest;

import com.todocode.bazarventa.exceptiones.ActualizarStockException;
import com.todocode.bazarventa.exceptiones.ProductoNotFoundException;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import com.todocode.bazarventa.repository.IProductoRepository;
import com.todocode.bazarventa.repository.IVentaRepository;
import com.todocode.bazarventa.service.ProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private IProductoRepository productoRepository;

    @Mock
    private IVentaRepository ventaRepository;

    @InjectMocks
    private ProductoService productoService;

    @Test
    void calcularTotalProductosTest() throws ProductoNotFoundException {
        // Given
        Producto producto1 = new Producto();
        producto1.setCosto(50.0);
        Producto producto2 = new Producto();
        producto2.setCosto(50.0);

        when(productoRepository.findAllByCodigoProductoIn(anyList()))
                .thenReturn(List.of(producto1, producto2));

        // When
        Double total = productoService.calcularTotalProductos(List.of(1L, 2L));

        // Then
        assertEquals(100.0, total);
    }


    @Test
    void saveProductoTest() {
        // Given
        Producto producto = new Producto();
        producto.setCodigoProducto(1L);

        // When
        productoService.saveProducto(producto);

        // Then
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void findCodigoProductoTest() {
        // Given
        Long codigoProducto = 1L;
        Producto producto = new Producto();
        producto.setCodigoProducto(codigoProducto);

        when(productoRepository.findByCodigoProducto(anyLong())).thenReturn(producto);

        // When
        Producto result = productoService.findCodigoProducto(codigoProducto);

        // Then
        assertEquals(producto, result);
    }

    @Test
    void editProductoTest() {
        // Given
        Producto producto = new Producto();
        producto.setCodigoProducto(1L);

        // When
        productoService.editProducto(producto);

        // Then
        verify(productoRepository, times(1)).save(producto);
    }

    @Test
    void obtenerProductosFaltaStockTest() {
        // Given
        Long umbralStockMinimo = 10L;
        List<Producto> productos = List.of(new Producto(), new Producto());

        when(productoRepository.findByCantidadDisponibleLessThan(anyLong())).thenReturn(productos);

        // When
        List<Producto> result = productoService.obtenerProductosFaltaStock(umbralStockMinimo);

        // Then
        assertEquals(productos, result);
    }

    @Test
    void actualizarStockTest() throws ActualizarStockException {
        // Given
        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        producto.setCantidadDisponible(10);
        Venta venta = new Venta();
        venta.setListaProductos(List.of(producto));

        when(productoRepository.findByCodigoProducto(anyLong())).thenReturn(producto);

        // When
        productoService.actualizarStock(venta);

        // Then
        verify(productoRepository, times(1)).decrementarCantidadDisponible(producto.getCodigoProducto());
        verify(productoRepository, times(1)).findByCodigoProducto(producto.getCodigoProducto());
    }

    @Test
    void actualizarStock_StockNegativoTest() {
        // Given
        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        producto.setCantidadDisponible(-1);

        Venta venta = new Venta();
        venta.setListaProductos(List.of(producto));

        when(productoRepository.findByCodigoProducto(anyLong())).thenReturn(producto);

        // When / Then
        assertThrows(ActualizarStockException.class, () -> productoService.actualizarStock(venta));
    }

    @Test
    void testDeleteProducto() {
        // Given
        Long codigoProducto = 1L;
        Producto producto = new Producto();
        producto.setCodigoProducto(codigoProducto);

        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(producto));

        // When
        productoService.deleteProducto(codigoProducto);

        // Then
        verify(productoRepository, times(1)).delete(producto);
    }

    @Test
    void testIncrementarStock() {
        // Given
        Long codigoProducto = 1L;
        Producto producto = new Producto();
        producto.setCodigoProducto(codigoProducto);
        producto.setCantidadDisponible(10);

        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(producto));

        // When
        productoService.incrementarStock(codigoProducto, 5);

        // Then
        assertEquals(15, producto.getCantidadDisponible());
        verify(productoRepository, times(1)).save(producto);
    }


}


