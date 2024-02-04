package com.example.API_BazarSpringBoot.controller;

import com.example.API_BazarSpringBoot.dto.VentaProdClienteDTO;
import com.example.API_BazarSpringBoot.model.Producto;
import com.example.API_BazarSpringBoot.model.Venta;
import com.example.API_BazarSpringBoot.service.IVentaService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bazar")
public class VentaController {

    @Autowired
    private IVentaService ventaServ;

    @PostMapping("/ventas/crear")
    public String crearVenta(@Valid @RequestBody Venta ven, BindingResult result) {
        String mensj = "";
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                mensj = error.getDefaultMessage();
            }
        } else {
            mensj = ventaServ.crearVenta(ven);
        }

        return mensj;
    }

    @GetMapping("/ventas")
    public List<Venta> obtenerVentas() {
        return ventaServ.obtenerVentas();
    }

    @GetMapping("/ventas/{codigo_venta}")
    public Venta buscarVenta(@PathVariable Long codigo_venta) {
        return ventaServ.buscarVenta(codigo_venta);
    }

    @DeleteMapping("/ventas/eliminar/{codigo_venta}")
    public String eliminarVenta(@PathVariable Long codigo_venta) {
        return ventaServ.eliminarVenta(codigo_venta);
    }

    @PutMapping("/ventas/editar/{codigo_venta}")
    public Venta modificarProducto(@PathVariable Long codigo_venta,
            @RequestParam(required = false, name = "codigo_venta") Long nuevoId,
            @RequestParam(required = false, name = "fecha_venta") LocalDate fecha_ventaNueva,
            @RequestParam(required = false, name = "total") Double totalNuevo) {

        ventaServ.modificarVenta(codigo_venta, nuevoId, fecha_ventaNueva, totalNuevo);
        return ventaServ.buscarVenta(nuevoId);
    }

    @PutMapping("/ventas/editar")
    public Venta modificarProducto(@RequestBody Venta ven) {
        ventaServ.modificarVenta(ven);
        return ventaServ.buscarVenta(ven.getCodigo_venta());
    }

    @GetMapping("/ventas/productos/{codigo_venta}")
    public List<Producto> obtenerListaProductos(@PathVariable Long codigo_venta) {
        return ventaServ.obtenerListaProductos(codigo_venta);
    }

    @GetMapping("/ventas/dia/{fecha_venta}")
    public String buscarVentaPorDia(@PathVariable LocalDate fecha_venta) {
        return ventaServ.buscarVentaDia(fecha_venta);
    }

    @GetMapping("/ventas/mayor_venta")
    public VentaProdClienteDTO obtenerMayorVenta() {
        return ventaServ.obtenerMayorVenta();
    }

}
