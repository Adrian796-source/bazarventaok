package com.todocode.bazarventa.service;


import com.todocode.bazarventa.exceptiones.VentaDataAccessException;
import com.todocode.bazarventa.exceptiones.VentaUnexpectedException;
import com.todocode.bazarventa.exceptiones.VentaValidationException;
import com.todocode.bazarventa.model.Producto;
import com.todocode.bazarventa.model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    
   
    
    public Venta findVentaPorCodigo(Long codigo);

    public void deleteById(Long codigo);

    public void editVenta(Venta venta);
    
    public List<Venta>obtenerVentaPorFecha(LocalDate fecha);// Nuevo método para el requerimiento 6
    
    public Venta obtenerVentaConMontoMasAlto();// Nuevo método para el requerimiento 7

    public List<Producto> obtenerProductosDeVenta(Long codigo);

    public List<Venta> obtenerVentasPorFecha(LocalDate fecha);

    public Long contarVentasPorFecha(LocalDate fecha);

    public Double obtenerSumatoriaMontoPorFecha(LocalDate fecha);

    public Venta crearVentaConProductos(Venta venta) throws VentaDataAccessException, VentaValidationException, VentaUnexpectedException;

    
    
   
    
    
}


   

 
