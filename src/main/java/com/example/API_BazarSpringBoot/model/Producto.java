package com.example.API_BazarSpringBoot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_producto;
    @NotNull(message = "Nombre no puede ser null")
    @NotBlank(message = "Nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "Marca no puede ser null")
    @NotBlank(message = "Marca no puede estar vacio")
    private String marca;
    @NotNull(message = "Costo no puede ser null")
    @Min(value = 0, message = "Costo no puede ser inferior a cero")
    private Double costo;
    @NotNull(message = "Cantidad no puede ser null")
    @Min(value = 0, message = "Cantidad no puede ser inferior a cero")
    private Double cantidad_disponible;

    public Producto() {
    }

    public Producto(Long codigo_producto, String nombre, String marca, Double costo, Double cantidad_disponible) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.costo = costo;
        this.cantidad_disponible = cantidad_disponible;
    }

}
