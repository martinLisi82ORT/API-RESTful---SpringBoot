package com.example.API_BazarSpringBoot.service;

import com.example.API_BazarSpringBoot.model.Producto;
import java.util.List;

public interface IProductoService {

    public String crearProducto(Producto prod);

    public List<Producto> obtenerProductos();

    public Producto buscarProducto(Long codigo_producto);

    public void modificarProducto(Long idOrig, Long idNuevo, String nomNuevo,
            String marcaNueva, Double costoNuevo, Double cantDispNueva);

    public String eliminarProducto(Long id);

    public List<Producto> productosFaltantes();

    public void modificarProducto(Producto prod);

}
