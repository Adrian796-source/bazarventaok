
package com.todocode.bazarventa.repository;


import com.todocode.bazarventa.model.Cliente;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import org.springframework.stereotype.Repository;

@Repository
public interface IVentaRepository extends JpaRepository<Venta, Long> {
    
   
    
   
    public Venta findByCodigo(Long codigo);

    public List<Venta> findByFecha(LocalDate fecha);
    
    @Modifying
    @Transactional
    @Query("UPDATE Venta v SET v.fecha = :fecha, v.total = :total, v.listaProductos = :listaProductos, v.unCliente = :unCliente WHERE v.codigo = :codigo")
    void editVenta(Long codigo, LocalDate fecha, Double total, List<Producto> listaProductos, Cliente unCliente);
    
    public Venta findFirstByOrderByTotalDesc();
    
    @Override
    @Transactional
    public void deleteById(Long codigo);

   
    

    List<Venta> findAllByFecha(LocalDate fecha);

    @Query("SELECT COUNT(v) FROM Venta v WHERE v.fecha = :fecha")
    Long countVentasByFecha(@Param("fecha") LocalDate fecha);
    
    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.fecha = :fecha")
    Double obtenerSumatoriaMontoPorFecha(@Param("fecha") LocalDate fecha);
    
    /**
    * Obtiene la lista de productos asociados a una venta por su código.
    *
    * @param codigo El código de la venta.
    * @return La lista de productos asociados a la venta.
    */
    @Query("SELECT v.listaProductos FROM Venta v WHERE v.codigo = :codigo")
    public List<Producto> obtenerProductosDeVenta(@Param("codigo") Long codigo);
    
    @Transactional
    @Modifying
    @Query("UPDATE Venta v SET v.fecha = :fecha, v.total = :total, v.listaProductos = :listaProductos, v.unCliente = :unCliente WHERE v.codigo = :codigo")
    void crearVentaConProductos(@Param("codigo") Long codigo, @Param("fecha") LocalDate fecha,
                                @Param("total") Double total, @Param("listaProductos") List<Producto> listaProductos,
                                @Param("unCliente") Cliente unCliente);
     
   
    // En la lista de las ventas encuentro en la lista de productos
    // el codigo del producto que se pide en el metodo eliminarVentasRelacionadas
    // de ProductoService
    public List<Venta> findAllByListaProductos_CodigoProducto(Long codigoProducto);

   

  

   

 

}
    



    
    

    
    
    



