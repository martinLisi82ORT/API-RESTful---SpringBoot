package com.example.API_BazarSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_cliente;
    @NotNull(message = "Nombre no puede ser null")
    @NotBlank(message = "Nombre no puede estar vacio")
    private String nombre;
    @NotNull(message = "Apellido no puede ser null")
    @NotBlank(message = "Apellido no puede estar vacio")
    private String apellido;
    @NotNull(message = "DNI no puede ser null")
    @NotBlank(message = "DNI no puede estar vacio")
    private String dni;

    @OneToMany(mappedBy = "unCliente")
    @JsonIgnoreProperties("unCliente")
    private List<Venta> listaVentas;

    public Cliente() {
    }

    public Cliente(Long id_cliente, String nombre, String apellido, String dni, List<Venta> listaVentas) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.listaVentas = listaVentas;
    }

}
