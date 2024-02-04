package com.example.API_BazarSpringBoot.service;

import com.example.API_BazarSpringBoot.model.Producto;
import com.example.API_BazarSpringBoot.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    int stockMin = 5;

    @Autowired
    private IProductoRepository prodRepo;

    @Override
    public String crearProducto(Producto prod) {
        String mensj;

        mensj = "Producto creado: " + prod.getNombre() + " - " + prod.getMarca();
        prodRepo.save(prod);
        return mensj;
    }

    @Override
    public List<Producto> obtenerProductos() {
        return prodRepo.findAll();
    }

    @Override
    public String eliminarProducto(Long id) {
        String mens;
        Producto prod = this.buscarProducto(id);
        if (prod != null) {
            prodRepo.deleteById(id);
            mens = "Producto eliminado correctamente";
        } else {
            mens = "No se encontro el producto";
        }
        return mens;
    }

    @Override
    public Producto buscarProducto(Long codigo_producto) {
        return prodRepo.findById(codigo_producto).orElse(null);
    }

    @Override
    public void modificarProducto(Long idOrig, Long idNueva, String nomNuevo, String marcaNueva, Double costoNuevo, Double cantDispNueva) {
        Producto prod = this.buscarProducto(idOrig);
        prod.setCodigo_producto(idNueva);
        prod.setNombre(nomNuevo);
        prod.setMarca(marcaNueva);
        prod.setCosto(costoNuevo);
        prod.setCantidad_disponible(cantDispNueva);
        this.crearProducto(prod);
    }

    @Override
    public void modificarProducto(Producto prod) {
        this.crearProducto(prod);
    }

    @Override
    public List<Producto> productosFaltantes() {
        List<Producto> listaProductos = this.obtenerProductos();
        List<Producto> listaFaltantes = new ArrayList<Producto>();

        for (Producto prod : listaProductos) {
            if (prod.getCantidad_disponible() < stockMin) {
                listaFaltantes.add(prod);
            }
        }
        return listaFaltantes;
    }

}
