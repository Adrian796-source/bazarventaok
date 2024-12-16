package com.todocode.bazarventa.serviceTest;

import com.todocode.bazarventa.exceptiones.*;
import com.todocode.bazarventa.model.Cliente;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import com.todocode.bazarventa.repository.IProductoRepository;
import com.todocode.bazarventa.repository.IVentaRepository;
import com.todocode.bazarventa.service.ClienteService;
import com.todocode.bazarventa.service.ProductoService;
import com.todocode.bazarventa.service.VentaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VentaServiceTest {

    @Mock
    private IVentaRepository ventaRepository;

    @Mock
    private ClienteService clienteService;

    @Mock
    private IProductoRepository productoRepository;

    @Mock
    private ProductoService productoService;


    @InjectMocks
    private VentaService ventaService;

    @Test
    void crearVentaConProductosTest() throws VentaDataAccessException, VentaValidationException {
        // Given
        Venta venta = new Venta();
        // Configura las propiedades necesarias de 'venta'
        List<Producto> listaProductos = new ArrayList<>();
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);
        venta.setListaProductos(listaProductos); // Inicializar la lista de productos
        venta.setUnCliente(cliente);

        // Mockear el cálculo del total de productos
        when(productoService.calcularTotalProductos(anyList())).thenReturn(100.0);

        // Mockear la búsqueda de cliente
        when(clienteService.findByCodigoCliente(anyLong())).thenReturn(Optional.of(cliente));


        // When
        when(ventaRepository.save(any(Venta.class))).thenReturn(venta);
        Venta result = ventaService.crearVentaConProductos(venta);

        // Then
        assertEquals(venta, result);
        verify(ventaRepository, times(1)).save(venta);
        verify(productoService, times(1)).calcularTotalProductos(anyList());
        verify(clienteService, times(1)).findByCodigoCliente(anyLong());

    }

    @Test
    void crearVentaConProductos_ProductoNotFoundException() {
        // Given
        Venta venta = new Venta();
        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(producto);
        venta.setListaProductos(listaProductos);

        // Aquí lanzamos una excepción específica para diferenciar los tests
        when(productoRepository.findById(anyLong())).thenThrow(new ProductoNotFoundException("Producto no encontrado"));

        // When / Then
        VentaUnexpectedException exception = assertThrows(VentaUnexpectedException.class, () -> ventaService.crearVentaConProductos(venta));
        assertEquals(ProductoNotFoundException.class, exception.getCause().getClass());
        assertEquals("Producto no encontrado", exception.getCause().getMessage());
    }

    @Test
    void crearVentaConProductos_ClienteNotFoundException() {
        // Given
        Venta venta = new Venta();
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);
        venta.setUnCliente(cliente);

        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(producto);
        venta.setListaProductos(listaProductos);

        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(producto));
        when(clienteService.findByCodigoCliente(anyLong())).thenReturn(Optional.empty());

        // When / Then
        VentaUnexpectedException exception = assertThrows(VentaUnexpectedException.class, () -> ventaService.crearVentaConProductos(venta));
        assertEquals(VentaValidationException.class, exception.getCause().getClass());
        assertEquals("Cliente no encontrado con código: 1", exception.getCause().getMessage());
    }

    @Test
    void crearVentaConProductos_VentaDataAccessException() {
        // Given
        Venta venta = new Venta();
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);
        venta.setUnCliente(cliente);

        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(producto);
        venta.setListaProductos(listaProductos);

        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(producto));
        when(clienteService.findByCodigoCliente(anyLong())).thenReturn(Optional.of(cliente));

        // Aquí lanzamos una excepción específica para diferenciar los tests
        doThrow(new org.springframework.dao.DataAccessException("Data access exception") {
        }).when(ventaRepository).save(any(Venta.class));

        // When / Then
        VentaDataAccessException exception = assertThrows(VentaDataAccessException.class, () -> ventaService.crearVentaConProductos(venta));
        assertTrue(exception.getMessage().startsWith("Error al crear la venta. Por favor, intente más tarde."));
        assertTrue(exception.getMessage().contains("Data access exception"));
    }

    @Test
    void crearVentaConProductos_DataAccessException() {
        // Given
        Venta venta = new Venta();
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);
        venta.setUnCliente(cliente);

        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(producto);
        venta.setListaProductos(listaProductos);

        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(producto));
        when(clienteService.findByCodigoCliente(anyLong())).thenReturn(Optional.of(cliente));
        doThrow(new org.springframework.dao.DataAccessException("Data access exception") {
        }).when(ventaRepository).save(any(Venta.class));

        // When / Then
        assertThrows(VentaDataAccessException.class, () -> ventaService.crearVentaConProductos(venta));
    }

    @Test
    void crearVentaConProductos_IllegalArgumentException() {
        // Given
        Venta venta = new Venta();
        Cliente cliente = new Cliente();
        cliente.setCodigoCliente(1L);
        venta.setUnCliente(cliente);

        Producto producto = new Producto();
        producto.setCodigoProducto(1L);
        List<Producto> listaProductos = new ArrayList<>();
        listaProductos.add(producto);
        venta.setListaProductos(listaProductos);

        when(productoRepository.findById(anyLong())).thenReturn(Optional.of(producto));
        when(clienteService.findByCodigoCliente(anyLong())).thenReturn(Optional.of(cliente));
        doThrow(new IllegalArgumentException("Invalid argument") {
        }).when(ventaRepository).save(any(Venta.class));

        // When / Then
        assertThrows(VentaValidationException.class, () -> ventaService.crearVentaConProductos(venta));
    }


    @Test
    void findVentaPorCodigoTest() {
        // Given
        Long codigo = 1L;
        Venta venta = new Venta();
        venta.setCodigo(codigo);

        when(ventaRepository.findByCodigo(anyLong())).thenReturn(venta);

        // When
        Venta result = ventaService.findVentaPorCodigo(codigo);

        // Then
        assertEquals(venta, result);
    }

    @Test
    void editVentaTest() {
        // Given
        Venta venta = new Venta();
        venta.setCodigo(1L);

        // When
        ventaService.editVenta(venta);

        // Then
        verify(ventaRepository, times(1)).save(venta);
    }

    @Test
    void deleteByIdTest() {
        // Given
        Long codigo = 1L;
        Venta venta = new Venta();
        venta.setCodigo(codigo);

        when(ventaRepository.findById(anyLong())).thenReturn(Optional.of(venta));

        // When
        ventaService.deleteById(codigo);

        // Then
        verify(ventaRepository, times(1)).deleteById(codigo);
    }

    @Test
    void deleteById_NotFoundTest() {
        // Given
        Long codigo = 1L;

        when(ventaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When / Then
        try {
            ventaService.deleteById(codigo);
        } catch (VentaNotFoundException e) {
            assertEquals("La venta con el código " + codigo + " no fue encontrada.", e.getMessage());
        }
    }


    @Test
    void obtenerVentaPorFechaTest() {
        // Given
        LocalDate fecha = LocalDate.now();
        List<Venta> ventas = List.of(new Venta(), new Venta());

        when(ventaRepository.findByFecha(any(LocalDate.class))).thenReturn(ventas);

        // When
        List<Venta> result = ventaService.obtenerVentaPorFecha(fecha);

        // Then
        assertEquals(ventas, result);
    }

    @Test
    void obtenerVentaConMontoMasAltoTest() {
        // Given
        Venta venta = new Venta();
        venta.setTotal(1000.0);

        when(ventaRepository.findFirstByOrderByTotalDesc()).thenReturn(venta);

        // When
        Venta result = ventaService.obtenerVentaConMontoMasAlto();

        // Then
        assertEquals(venta, result);
    }

    @Test
    void obtenerProductosDeVentaTest() {
        // Given
        Long codigo = 1L;
        Venta venta = new Venta();
        List<Producto> productos = List.of(new Producto(), new Producto());

        venta.setListaProductos(productos);

        when(ventaRepository.findById(anyLong())).thenReturn(Optional.of(venta));

        // When
        List<Producto> result = ventaService.obtenerProductosDeVenta(codigo);

        // Then
        assertEquals(productos, result);
    }

    @Test
    void obtenerProductosDeVenta_NotFoundTest() {
        // Given
        Long codigo = 1L;

        when(ventaRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When / Then
        try {
            ventaService.obtenerProductosDeVenta(codigo);
        } catch (VentaNotFoundException e) {
            assertEquals("La venta con el código " + codigo + " no fue encontrada.", e.getMessage());
        }
    }

    @Test
    void obtenerVentasPorFechaTest() {
        // Given
        LocalDate fecha = LocalDate.now();
        List<Venta> ventas = List.of(new Venta(), new Venta());

        when(ventaRepository.findAllByFecha(any(LocalDate.class))).thenReturn(ventas);

        // When
        List<Venta> result = ventaService.obtenerVentasPorFecha(fecha);

        // Then
        assertEquals(ventas, result);
    }

    @Test
    void contarVentasPorFechaTest() {
        // Given
        LocalDate fecha = LocalDate.now();
        Long cantidadVentas = 10L;

        when(ventaRepository.countVentasByFecha(any(LocalDate.class))).thenReturn(cantidadVentas);

        // When
        Long result = ventaService.contarVentasPorFecha(fecha);

        // Then
        assertEquals(cantidadVentas, result);
    }

    @Test
    void obtenerSumatoriaMontoPorFechaTest() {
        // Given
        LocalDate fecha = LocalDate.now();
        Double totalVentas = 1000.0;

        when(ventaRepository.obtenerSumatoriaMontoPorFecha(any(LocalDate.class))).thenReturn(totalVentas);

        // When
        Double result = ventaService.obtenerSumatoriaMontoPorFecha(fecha);

        // Then
        assertEquals(totalVentas, result);
    }


}
