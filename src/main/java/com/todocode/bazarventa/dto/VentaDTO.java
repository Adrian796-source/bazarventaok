
package com.todocode.bazarventa.dto;

import java.time.LocalDate;

public class VentaDTO {
    
    private Long codigo;
    private LocalDate fecha;
    private Double total;

    public VentaDTO() {
    }

    public VentaDTO(Long codigo, LocalDate fecha, Double total) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.total = total;
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
    
    
    
}
