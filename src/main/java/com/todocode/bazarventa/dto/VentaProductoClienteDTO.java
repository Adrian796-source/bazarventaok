
package com.todocode.bazarventa.dto;







public class VentaProductoClienteDTO {//DTO para el requerimiento 7
    
    
    
    
    private Long codigo; // codigo_venta(convencion JpaSpringData)
    private Double total; // total de la venta
    private int cantidadDisponible; // cantidad disponible de productos
    private String nombre; // nombre del cliente
    private String apellido; // apellido del cliente

    public VentaProductoClienteDTO() {
    }

    public VentaProductoClienteDTO(Long codigo, Double total, int cantidadDisponible, String nombre, String apellido) {
        this.codigo = codigo;
        this.total = total;
        this.cantidadDisponible = cantidadDisponible;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public int getCantidadDisponible() {
        return cantidadDisponible;
    }

    public void setCantidadDisponible(int cantidadDisponible) {
        this.cantidadDisponible = cantidadDisponible;
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
    
}  

