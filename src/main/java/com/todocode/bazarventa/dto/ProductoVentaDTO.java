
package com.todocode.bazarventa.dto;

import com.todocode.bazarventa.model.Producto;

import java.util.List;


public class ProductoVentaDTO {

    private Long codigoVenta;
    private List<Producto> listaProductos;

    //Constructor vacio
    public ProductoVentaDTO() {
    }

    // Constructor con parametros
    public ProductoVentaDTO(Long codigoVenta, List<Producto> listaProductos) {
        this.codigoVenta = codigoVenta;
        this.listaProductos = listaProductos;
    }

    public Long getCodigoVenta() {

        return codigoVenta;
    }

    public void setCodigoVenta(Long codigoVenta) {

        this.codigoVenta = codigoVenta;
    }

    public List<Producto> getListaProductos() {

        return listaProductos;
    }

    public void setListaProductos(List<Producto> listaProductos) {

        this.listaProductos = listaProductos;
    }
}
