package com.todocode.bazarventa.controller;


import com.todocode.bazarventa.dto.ProductoDTO;
import com.todocode.bazarventa.exceptiones.ProductoNotFoundException;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.repository.IProductoRepository;
import com.todocode.bazarventa.service.IProductoService;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/productos")
public class ProductoController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);


    private final IProductoService productoService;
    private final IProductoRepository productoRepository;


    @Autowired
    public ProductoController(IProductoService productoService, IProductoRepository productoRepository) {
        this.productoService = productoService;
        this.productoRepository = productoRepository;

    }

    // Metodo para crear un producto
    @PostMapping("/crear")
    public void createProducto(@RequestBody Producto producto) {

        productoService.saveProducto(producto);
    }

    // Metodo para obtener la lista completa de productos
    @GetMapping
    public List<ProductoDTO> getAllProductosDTO() {

        // Mapear y devolver directamente la lista de productos a ProductoDTO
        return productoRepository.findAll().stream()
                // Mapear la lista de productos a una lista de ProductoDTO

                .map(producto -> new ProductoDTO(
                        producto.getCodigoProducto(),
                        producto.getNombre(),
                        producto.getMarca(),
                        producto.getCosto(),
                        producto.getCantidadDisponible()
                ))

                .toList();

    }

    //Metodo para traer un producto en particular por su codigo
    @GetMapping("/{codigoProducto}")
    public ProductoDTO findByCodigoProducto(@PathVariable Long codigoProducto) {
        // Obtener el cliente de la base de datos usando el servicio o el repositorio
        Producto product = productoRepository.findByCodigoProducto(codigoProducto);

        // Verificar si se encontró el cliente
        if (product != null) {
            // Mapear los datos del cliente a ClienteDTO
            //ProductoDTO productDTO = new ProductoDTO(
            return new ProductoDTO(
                    product.getCodigoProducto(),
                    product.getNombre(),
                    product.getMarca(),
                    product.getCosto(),
                    product.getCantidadDisponible()
            );

        } else {
            // Manejar el caso en el que no se encontró el cliente
            // Puedes devolver un DTO vacío o lanzar una excepción según tus necesidades.
            return new ProductoDTO();
        }

    }


    // Metodo para editar un producto por su codigo
    @PutMapping("/editar/{codigoProducto}")
    public void editProducto(@PathVariable Long codigoProducto, @RequestBody Producto producto) {
        Producto existingProducto = productoService.findCodigoProducto(codigoProducto);
        if (existingProducto != null) {

            // Actualiza los campos del producto existente
            existingProducto.setNombre(producto.getNombre());
            existingProducto.setMarca(producto.getMarca());
            existingProducto.setCosto(producto.getCosto());
            existingProducto.setCantidadDisponible(producto.getCantidadDisponible());
            // Luego, guardas el producto actualizado
            productoService.editProducto(existingProducto);

        }

    }

    @GetMapping("/falta-stock")
    public List<Producto> obtenerProductosFaltaStock(@RequestParam(value = "umbralStockMinimo", required = false) Long umbralStockMinimo) {
        if (umbralStockMinimo != null) {
            return productoService.obtenerProductosFaltaStock(umbralStockMinimo);
        } else {
            // Maneja el caso en el que el parámetro no se proporciona o es nulo.
            // Puedes devolver un mensaje de error o una lista vacía, según tus necesidades.
            return new ArrayList<>();
            //localhost:8080/productos/falta-stock?umbralStockMinimo=5
        }   // esta seria la url en postman

    }

    @PutMapping("/incrementar-stock/{codigoProducto}")
    public ResponseEntity<Object> incrementarStock(@PathVariable Long codigoProducto, @RequestParam int cantidad) {
        // Validación de la cantidad
        if (cantidad <= 0) {
            return ResponseEntity.badRequest().body("La cantidad debe ser mayor que 0.");
        }

        try {
            productoService.incrementarStock(codigoProducto, cantidad);
            return ResponseEntity.ok("Stock incrementado exitosamente.");
        } catch (ProductoNotFoundException e) {
            LOGGER.error("Producto no encontrado: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Argumento inválido: {}", e.getMessage(), e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("Error al incrementar el stock: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud");
        }
    }

    @DeleteMapping("/eliminar/{codigoProducto}")
    public ResponseEntity<String> deleteProducto(@PathVariable Long codigoProducto) {
        try {
            productoService.deleteProducto(codigoProducto);
            return ResponseEntity.ok("Producto y ventas asociadas eliminados correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}




    
    

