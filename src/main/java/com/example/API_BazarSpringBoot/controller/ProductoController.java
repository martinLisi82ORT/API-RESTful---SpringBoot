package com.example.API_BazarSpringBoot.controller;

import com.example.API_BazarSpringBoot.model.Producto;
import com.example.API_BazarSpringBoot.service.IProductoService;
import jakarta.validation.Valid;
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
public class ProductoController {

    @Autowired
    private IProductoService prodServ;

    @PostMapping("/productos/crear")
    public String crearProducto(@Valid @RequestBody Producto prod, BindingResult result) {
        String mensj = "";
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                mensj = error.getDefaultMessage();
            }

        } else {
            mensj = prodServ.crearProducto(prod);
        }

        return mensj;
    }

    @GetMapping("/productos")
    public List<Producto> obtenerProductos() {
        return prodServ.obtenerProductos();
    }

    @GetMapping("/productos/{codigo_producto}")
    public Producto buscarProducto(@PathVariable Long codigo_producto) {
        return prodServ.buscarProducto(codigo_producto);
    }

    @DeleteMapping("/productos/eliminar/{codigo_producto}")
    public String eliminarProducto(@PathVariable Long codigo_producto) {
        return prodServ.eliminarProducto(codigo_producto);
    }

    @PutMapping("/productos/editar/{codigo_producto}")
    public Producto modificarProducto(@PathVariable Long codigo_producto,
            @RequestParam(required = false, name = "codigo_producto") Long nuevoId,
            @RequestParam(required = false, name = "nombre") String nuevoNom,
            @RequestParam(required = false, name = "marca") String nuevaMarca,
            @RequestParam(required = false, name = "costo") Double nuevoCosto,
            @RequestParam(required = false, name = "cantidad_disponible") Double nuevaCant) {

        prodServ.modificarProducto(codigo_producto, nuevoId, nuevoNom, nuevaMarca, nuevoCosto, nuevaCant);
        return prodServ.buscarProducto(nuevoId);
    }

    @PutMapping("/productos/editar")
    public Producto modificarProducto(@RequestBody Producto prod) {
        prodServ.modificarProducto(prod);
        return prodServ.buscarProducto(prod.getCodigo_producto());
    }

    @GetMapping("/productos/falta_stock")
    public List<Producto> productosFaltantes() {
        return prodServ.productosFaltantes();
    }

}
