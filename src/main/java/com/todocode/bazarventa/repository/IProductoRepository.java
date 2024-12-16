
package com.todocode.bazarventa.repository;



import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductoRepository  extends JpaRepository<Producto, Long> {

   
   
    public List<Producto> findByCantidadDisponibleLessThan(Long umbralStockMinimo);

    public Producto findByCodigoProducto(Long codigoProducto);

    public List<Producto> findAllByVenta(Venta venta);
    
    /**
     *
     * @param codigoProducto
     */
    public void deleteByCodigoProducto(Long codigoProducto);
    
    // Para actualizar el stock cuando se agregan productos a una venta y cuando esta se realiza descontarlos en cantidadDisponible
    @Modifying
    @Query("UPDATE Producto p SET p.cantidadDisponible = p.cantidadDisponible - 1 WHERE p.codigoProducto = :codigoProducto")
    void decrementarCantidadDisponible(@Param("codigoProducto") Long codigoProducto);
    
    // Para sumar el total de los productos que pertenecen a una venta
    List<Producto> findAllByCodigoProductoIn(List<Long> codigosProductos);
    
    // Agregar stock desde postman de cantidad de productos
    @Modifying
    @Query("UPDATE Producto p SET p.cantidadDisponible = p.cantidadDisponible + :cantidad WHERE p.codigoProducto = :codigoProducto")
    void incrementarCantidadDisponible(@Param("codigoProducto") Long codigoProducto, @Param("cantidad") int cantidad);
 
     // Método para eliminar un producto por su código
    @Modifying
    @Query("DELETE FROM Producto p WHERE p.codigoProducto = :codigoProducto")
    void deleteProducto(@Param("codigoProducto") Long codigoProducto);
   

    

    

   
    
  

}

    
  
 
    



