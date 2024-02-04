package com.example.API_BazarSpringBoot.service;

import com.example.API_BazarSpringBoot.dto.VentaProdClienteDTO;
import com.example.API_BazarSpringBoot.model.Producto;
import com.example.API_BazarSpringBoot.model.Venta;
import java.time.LocalDate;
import java.util.List;

public interface IVentaService {

    public String crearVenta(Venta vent);

    public List<Venta> obtenerVentas();

    public Venta buscarVenta(Long id);

    public void modificarVenta(Long idOrig, Long idNuevo, LocalDate fecVenNueva,
            Double totalNuevo);

    public String eliminarVenta(Long id);

    public List<Producto> obtenerListaProductos(Long codigo_venta);

    public String buscarVentaDia(LocalDate fecha_venta);

    public VentaProdClienteDTO obtenerMayorVenta();

    public void modificarVenta(Venta ven);

}
