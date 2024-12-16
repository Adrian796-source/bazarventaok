
package com.todocode.bazarventa.dto;


import com.todocode.bazarventa.model.Producto;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaResponseDTO {
    
    private Long codigo;
    private LocalDate fecha;
    private Double total;
    private List<Producto> listaProductos;
    private String nombre;
    private String apellido;
    private String dni;
    
    @Autowired
    public VentaResponseDTO() {
    }

    public VentaResponseDTO(Long codigo1, LocalDate fecha1, Double total1, List<Producto> listaProductos1, String nombre1, String apellido1, String dni1) {
        this.codigo = codigo1;
        this.fecha = fecha1;
        this.total = total1;
        this.listaProductos = listaProductos1;
        this.nombre = nombre1;
        this.apellido = apellido1;
        this.dni = dni1;
        
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Producto> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

   
    
     
     
}
