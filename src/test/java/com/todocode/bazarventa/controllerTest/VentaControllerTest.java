package com.todocode.bazarventa.controllerTest;


import com.todocode.bazarventa.dto.ProductoVentaDTO;
import com.todocode.bazarventa.dto.VentaDTO;
import com.todocode.bazarventa.dto.VentaProductoClienteDTO;
import com.todocode.bazarventa.dto.VentaResponseDTO;
import com.todocode.bazarventa.exceptiones.VentaDataAccessException;
import com.todocode.bazarventa.exceptiones.VentaValidationException;
import com.todocode.bazarventa.model.Cliente;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import com.todocode.bazarventa.repository.IVentaRepository;
import com.todocode.bazarventa.service.IVentaService;
import com.todocode.bazarventa.controller.VentaController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaControllerTest {

    @Mock
    private IVentaService ventaService;

    @Mock
    private IVentaRepository ventaRepository;

    @InjectMocks
    private VentaController ventaController;

    @Test
    void getAllVentasDTOTest() {
        // Given
        Venta venta = new Venta();
        venta.setCodigo(1L);
        venta.setFecha(LocalDate.now());
        venta.setTotal(100.0);
        List<Venta> ventas = List.of(venta);

        when(ventaRepository.findAll()).thenReturn(ventas);

        // When
        List<VentaDTO> result = ventaController.getAllVentasDTO();

        // Then
        assertEquals(1, result.size());
        VentaDTO dto = result.get(0);
        assertEquals(venta.getCodigo(), dto.getCodigo());
        assertEquals(venta.getFecha(), dto.getFecha());
        assertEquals(venta.getTotal(), dto.getTotal());
    }

    @Test
    void findByCodigoTest() {
        // Given
        Long codigo = 1L;
        Venta venta = new Venta();
        venta.setCodigo(codigo);
        venta.setFecha(LocalDate.now());
        venta.setTotal(100.0);

        when(ventaRepository.findByCodigo(anyLong())).thenReturn(venta);

        // When
        VentaDTO result = ventaController.findByCodigo(codigo);

        // Then
        assertEquals(venta.getCodigo(), result.getCodigo());
        assertEquals(venta.getFecha(), result.getFecha());
        assertEquals(venta.getTotal(), result.getTotal());
    }

    @Test
    void deleteVentaTest() {
        // Given
        Long codigo = 1L;

        // When
        ResponseEntity<String> response = ventaController.deleteVenta(codigo);

        // Then
        verify(ventaService, times(1)).deleteById(codigo);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Venta eliminada correctamente.", response.getBody());
    }

    @Test
    void editVentaTest() {
        // Given
        Long codigo = 1L;
        Venta venta = new Venta();
        venta.setCodigo(codigo);
        venta.setFecha(LocalDate.now());
        venta.setTotal(100.0);

        when(ventaService.findVentaPorCodigo(anyLong())).thenReturn(venta);

        // When
        ResponseEntity<String> response = ventaController.editVenta(codigo, venta);

        // Then
        verify(ventaService, times(1)).editVenta(venta);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Venta actualizada correctamente", response.getBody());
    }

    @Test
    void obtenerVentaConMontoMasAltoTest() {
        // Given
        Venta venta = new Venta();
        venta.setCodigo(1L);
        venta.setTotal(500.0);
        when(ventaService.obtenerVentaConMontoMasAlto()).thenReturn(venta);

        // When
        Venta result = ventaController.obtenerVentaConMontoMasAlto();

        // Then
        assertEquals(venta, result);
    }


    @Test
    void obtenerProductosDeVentaTest() {
        // Given
        Long codigo = 1L;
        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        List<Producto> productos = List.of(producto);
        Venta venta = new Venta();
        venta.setCodigo(codigo);
        venta.setListaProductos(productos);
        when(ventaService.obtenerProductosDeVenta(codigo)).thenReturn(productos);
        when(ventaService.findVentaPorCodigo(codigo)).thenReturn(venta);

        // When
        ResponseEntity<ProductoVentaDTO> response = ventaController.obtenerProductosDeVenta(codigo);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(codigo, response.getBody().getCodigoVenta());
        assertEquals(productos, response.getBody().getListaProductos());
    }

    @Test
    void obtenerVentasYCantidadPorFechaTest() {
        // Given
        LocalDate fecha = LocalDate.now();
        Double totalVentas = 1000.0;
        Long cantidadVentas = 10L;
        when(ventaService.obtenerSumatoriaMontoPorFecha(fecha)).thenReturn(totalVentas);
        when(ventaService.contarVentasPorFecha(fecha)).thenReturn(cantidadVentas);

        // When
        ResponseEntity<Object> response = ventaController.obtenerVentasYCantidadPorFecha(fecha);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(totalVentas, ((Map<String, Object>) response.getBody()).get("totalVentas"));
        assertEquals(cantidadVentas, ((Map<String, Object>) response.getBody()).get("cantidadVentas"));

    }


    @Test
    void obtenerInformacionVentaConMontoMasAltoTest() {
        // Given
        Venta venta = new Venta();
        venta.setCodigo(1L);
        venta.setTotal(1000.0);
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        venta.setUnCliente(cliente);
        venta.setListaProductos(List.of(new Producto(), new Producto()));
        when(ventaService.obtenerVentaConMontoMasAlto()).thenReturn(venta);

        // When
        ResponseEntity<VentaProductoClienteDTO> response = ventaController.obtenerInformacionVentaConMontoMasAlto();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        VentaProductoClienteDTO dto = response.getBody();
        assertEquals(venta.getCodigo(), dto.getCodigo());
        assertEquals(venta.getTotal(), dto.getTotal());
        assertEquals(venta.getListaProductos().size(), dto.getCantidadDisponible());
        assertEquals(cliente.getNombre(), dto.getNombre());
        assertEquals(cliente.getApellido(), dto.getApellido());

    }

    @Test
    void crearVentaConProductosTest() throws VentaDataAccessException, VentaValidationException {
        // Given
        Venta venta = new Venta();
        venta.setCodigo(1L);
        venta.setFecha(LocalDate.now());
        venta.setTotal(100.0);
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Perez");
        cliente.setDni("12345678");
        venta.setUnCliente(cliente);
        venta.setListaProductos(List.of(new Producto()));

        when(ventaService.crearVentaConProductos(any(Venta.class))).thenReturn(venta);

        // When
        ResponseEntity<VentaResponseDTO> response = ventaController.crearVentaConProductos(venta);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        VentaResponseDTO dto = response.getBody();
        assertEquals(venta.getCodigo(), dto.getCodigo());
        assertEquals(venta.getFecha(), dto.getFecha());
        assertEquals(venta.getTotal(), dto.getTotal());
        assertEquals(venta.getListaProductos(), dto.getListaProductos());
        assertEquals(cliente.getNombre(), dto.getNombre());
        assertEquals(cliente.getApellido(), dto.getApellido());
        assertEquals(cliente.getDni(), dto.getDni());
    }
}

