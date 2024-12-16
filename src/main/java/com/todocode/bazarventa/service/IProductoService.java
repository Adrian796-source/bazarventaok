
package com.todocode.bazarventa.service;


import com.todocode.bazarventa.exceptiones.ActualizarStockException;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import java.util.List;


public interface IProductoService {



    
    /**
     * Guarda un producto en la base de datos.
     * 
     * @param producto El producto a guardar.
     */
    public void saveProducto(Producto producto);
    
    
    /**
     * Encuentra un producto por su código.
     * 
     * @param codigoProducto El código del producto.
     * @return El producto encontrado, o null si no existe.
     */
    public Producto findCodigoProducto(Long codigoProducto);
    

    
     /**
     * Edita un producto en la base de datos.
     * 
     * @param producto El producto actualizado.
     */
    public void editProducto(Producto producto);
    
    /**
     * Obtiene productos con stock insuficiente.
     * 
     * @param umbralStockMinimo
     * @return Lista de productos con stock insuficiente.
     */
    // Nuevo método para el requerimiento 4

    public List<Producto> obtenerProductosFaltaStock(Long umbralStockMinimo);
    
    public void actualizarStock(Venta venta)throws ActualizarStockException;
    
    public Double calcularTotalProductos(List<Long> codigosProductos);
        
    public void incrementarStock(Long codigoProducto, int cantidad);

    public void deleteProducto(Long codigoProducto);
 
}