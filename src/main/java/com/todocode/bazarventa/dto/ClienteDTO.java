
package com.todocode.bazarventa.dto;

public class ClienteDTO {
    private Long codigoCliente;
    private String nombre;
    private String apellido;
    private String dni;

    public ClienteDTO() {
    }

    public ClienteDTO(Long codigoCliente, String nombre, String apellido, String dni) {
        this.codigoCliente = codigoCliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
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
