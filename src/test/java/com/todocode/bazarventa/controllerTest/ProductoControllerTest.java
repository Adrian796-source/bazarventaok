package com.todocode.bazarventa.controllerTest;

import com.todocode.bazarventa.controller.ProductoController;
import com.todocode.bazarventa.dto.ProductoDTO;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.repository.IProductoRepository;
import com.todocode.bazarventa.service.IProductoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductoControllerTest {

    @Mock
    private IProductoService productoService;

    @Mock
    private IProductoRepository productoRepository;

    @InjectMocks
    private ProductoController productoController;


    @Test
    void createProductoTest() {
        // Given
        Producto producto = new Producto();
        producto.setCodigoProducto(1L);

        // When
        productoController.createProducto(producto);

        // Then
        verify(productoService, times(1)).saveProducto(producto);
    }

    @Test
    void getAllProductosDTOTest() {
        // Given
        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        producto.setNombre("Producto 1");
        producto.setMarca("Marca 1");
        producto.setCosto(100.0);
        producto.setCantidadDisponible(10);

        List<Producto> productos = List.of(producto);

        when(productoRepository.findAll()).thenReturn(productos);

        // When
        List<ProductoDTO> result = productoController.getAllProductosDTO();

        // Then
        assertEquals(1, result.size());
        ProductoDTO dto = result.get(0);
        assertEquals(producto.getCodigoProducto(), dto.getCodigoProducto());
        assertEquals(producto.getNombre(), dto.getNombre());
        assertEquals(producto.getMarca(), dto.getMarca());
        assertEquals(producto.getCosto(), dto.getCosto());
        assertEquals(producto.getCantidadDisponible(), dto.getCantidadDisponible());
    }

    @Test
    void findByCodigoProductoTest() {
        // Given
        Long codigoProducto = 1L;
        Producto producto = new Producto();
        producto.setCodigoProducto(codigoProducto);
        producto.setNombre("Producto 1");
        producto.setMarca("Marca 1");
        producto.setCosto(100.0);
        producto.setCantidadDisponible(10);

        when(productoRepository.findByCodigoProducto(anyLong())).thenReturn(producto);

        // When
        ProductoDTO result = productoController.findByCodigoProducto(codigoProducto);

        // Then
        assertEquals(producto.getCodigoProducto(), result.getCodigoProducto());
        assertEquals(producto.getNombre(), result.getNombre());
        assertEquals(producto.getMarca(), result.getMarca());
        assertEquals(producto.getCosto(), result.getCosto());
        assertEquals(producto.getCantidadDisponible(), result.getCantidadDisponible());
    }

    @Test
    void findByCodigoProductoNotFoundTest() {
        // Given
        Long codigoProducto = 1L;
        when(productoRepository.findByCodigoProducto(anyLong())).thenReturn(null);

        // When
        ProductoDTO result = productoController.findByCodigoProducto(codigoProducto);

        // Then
        ProductoDTO expected = new ProductoDTO();
        assertEquals(expected.getCodigoProducto(), result.getCodigoProducto());
        assertEquals(expected.getNombre(), result.getNombre());
        assertEquals(expected.getMarca(), result.getMarca());
        assertEquals(expected.getCosto(), result.getCosto());
        assertEquals(expected.getCantidadDisponible(), result.getCantidadDisponible());
    }


    @Test
    void editProductoTest() {
        // Given
        Long codigoProducto = 1L;
        Producto producto = new Producto();
        producto.setCodigoProducto(codigoProducto);
        Producto existingProducto = new Producto();
        existingProducto.setCodigoProducto(codigoProducto);

        when(productoService.findCodigoProducto(anyLong())).thenReturn(existingProducto);

        // When
        productoController.editProducto(codigoProducto, producto);

        // Then
        verify(productoService, times(1)).editProducto(existingProducto);
    }

    @Test
    void editProductoNotFoundTest() {
        // Given
        Long codigoProducto = 1L;
        Producto producto = new Producto();
        when(productoService.findCodigoProducto(anyLong())).thenReturn(null);

        // When
        productoController.editProducto(codigoProducto, producto);

        // Then
        verify(productoService, never()).editProducto(any(Producto.class));
    }


    @Test
    void obtenerProductosFaltaStockTest() {
        // Given
        Long umbralStockMinimo = 5L;
        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        producto.setCantidadDisponible(2);

        List<Producto> productos = List.of(producto);

        when(productoService.obtenerProductosFaltaStock(anyLong())).thenReturn(productos);

        // When
        List<Producto> result = productoController.obtenerProductosFaltaStock(umbralStockMinimo);

        // Then
        assertEquals(1, result.size());
        assertEquals(producto, result.get(0));
    }

    @Test
    void obtenerProductosFaltaStockSinParametroTest() {
        // When
        List<Producto> result = productoController.obtenerProductosFaltaStock(null);

        // Then
        assertEquals(0, result.size());
    }

    @Test
    void incrementarStockTest() {
        // Given
        Long codigoProducto = 1L;
        int cantidad = 5;

        // When
        ResponseEntity<Object> response = productoController.incrementarStock(codigoProducto, cantidad);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productoService, times(1)).incrementarStock(codigoProducto, cantidad);
    }

    @Test
    void incrementarStockExceptionTest() {
        // Given
        Long codigoProducto = 1L;
        int cantidad = 5;
        doThrow(new RuntimeException("Error al incrementar stock")).when(productoService).incrementarStock(anyLong(), anyInt());

        // When
        ResponseEntity<Object> response = productoController.incrementarStock(codigoProducto, cantidad);

        // Then
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error al procesar la solicitud", response.getBody());
    }

    @Test
    void deleteProductoTest() {
        // Given
        Long codigoProducto = 1L;

        // When
        ResponseEntity<String> response = productoController.deleteProducto(codigoProducto);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(productoService, times(1)).deleteProducto(codigoProducto);
    }

    @Test
    void deleteProductoExceptionTest() {
        // Given
        Long codigoProducto = 1L;
        doThrow(new RuntimeException("Error al eliminar producto")).when(productoService).deleteProducto(anyLong());

        // When
        ResponseEntity<String> response = productoController.deleteProducto(codigoProducto);

        // Then
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error al eliminar producto", response.getBody());
    }


}


