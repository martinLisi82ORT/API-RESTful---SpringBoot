package com.example.API_BazarSpringBoot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VentaProdClienteDTO {

    private Long codigo_venta;
    private Double total;
    private int cantidad;
    private String nombre;
    private String apellido;

    public VentaProdClienteDTO() {
    }

    public VentaProdClienteDTO(Long codigo_venta, Double total, int cantidad, String nombre, String apellido) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.apellido = apellido;
    }

}
