package com.example.API_BazarSpringBoot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;
    @NotNull(message = "Fecha no puede ser null")
    @PastOrPresent(message = "Fecha no puede ser superior a la fecha actual")
    private LocalDate fecha_venta;
    @NotNull(message = "Total no puede ser null")
    @Min(value = 0, message = "Total no puede ser inferior a cero")
    private Double total;

    @NotNull(message = "Cliente no puede ser null")
    @ManyToOne
    @OneToMany(mappedBy = "fkCliente")
    @JsonIgnoreProperties("listaVentas")
    private Cliente unCliente;

    @NotNull(message = "Lista no puede ser null")
    @NotEmpty(message = "Lista no puede estar vacia")
    @JsonIgnoreProperties("listaVentas")
    @ManyToMany
    @JoinTable(name = "venta_productos",
            joinColumns = @JoinColumn(name = "fk_venta", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "fk_producto", nullable = false))
    private List<Producto> listaProductos;

    public Venta() {
    }

    public Venta(Long codigo_venta, LocalDate fecha_venta, Double total, List<Producto> listaProductos, Cliente unCliente) {
        this.codigo_venta = codigo_venta;
        this.fecha_venta = fecha_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.unCliente = unCliente;
    }

}
