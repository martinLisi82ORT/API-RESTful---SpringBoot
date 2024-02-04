package com.example.API_BazarSpringBoot.service;

import com.example.API_BazarSpringBoot.dto.VentaProdClienteDTO;
import com.example.API_BazarSpringBoot.model.Cliente;
import com.example.API_BazarSpringBoot.model.Producto;
import com.example.API_BazarSpringBoot.model.Venta;
import com.example.API_BazarSpringBoot.repository.IClienteRepository;
import com.example.API_BazarSpringBoot.repository.IProductoRepository;
import com.example.API_BazarSpringBoot.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    @Autowired
    private IVentaRepository ventRepo;
    @Autowired
    private IProductoRepository prodRepo;
    @Autowired
    private IClienteRepository cliRepo;

    @Override
    public String crearVenta(Venta vent) {
        String mensj;
        List<Producto> prodVta = vent.getListaProductos();
        List<Producto> prodVtaNueva = new ArrayList<>();
        Venta ventaNueva = new Venta();

        Cliente cliente = cliRepo.findById(vent.getUnCliente().getId_cliente()).orElse(null);
        if (cliente == null) {
            mensj = "No existe Cliente registrado en la Base de Datos";
        } else {
            double cant = 0;
            Boolean cantInf = false;
            Boolean cantFalt = true;
            for (Producto prod : prodVta) {
                Producto producto = prodRepo.findById(prod.getCodigo_producto()).orElse(null);
                if (producto == null) {
                    cantFalt = false;
                } else if (prod.getCantidad_disponible() <= producto.getCantidad_disponible()) {
                    cant = prod.getCantidad_disponible();
                    producto.setCantidad_disponible(producto.getCantidad_disponible() - cant);
                    prodRepo.save(producto);
                    prodVtaNueva.add(producto);
                } else {
                    cantInf = true;
                }
            }

            if (cantFalt) {
                if (cantInf) {
                    mensj = "Venta cancelada: hay productos con falta de stock";
                    prodVtaNueva.clear();
                } else {
                    ventaNueva.setCodigo_venta(vent.getCodigo_venta());
                    ventaNueva.setFecha_venta(vent.getFecha_venta());
                    ventaNueva.setTotal(vent.getTotal());
                    ventaNueva.setListaProductos(prodVtaNueva);
                    ventaNueva.setUnCliente(vent.getUnCliente());
                    ventRepo.save(ventaNueva);
                    mensj = "Venta exitosa";
                }
            } else {
                mensj = "Venta cancelada: hay Productos no existentes en la Base de Datos";
            }
        }
        return mensj;
    }

    @Override
    public List<Venta> obtenerVentas() {
        return ventRepo.findAll();
    }

    @Override
    public String eliminarVenta(Long id) {
        String mens;
        Venta venta = this.buscarVenta(id);
        if (venta != null) {
            ventRepo.deleteById(id);
            mens = "Venta eliminada correctamente";
        } else {
            mens = "No se encontro la Venta";
        }
        return mens;
    }

    @Override
    public Venta buscarVenta(Long codigo_venta) {
        return ventRepo.findById(codigo_venta).orElse(null);
    }

    @Override
    public void modificarVenta(Long idOrig, Long idNuevo, LocalDate fecha_venta, Double totalNuevo) {
        Venta venta = this.buscarVenta(idOrig);
        venta.setCodigo_venta(idNuevo);
        venta.setFecha_venta(fecha_venta);
        venta.setTotal(totalNuevo);
        this.crearVenta(venta);
    }

    @Override
    public void modificarVenta(Venta ven) {
        this.crearVenta(ven);
    }

    @Override
    public List<Producto> obtenerListaProductos(Long codigo_venta) {
        Venta venta = this.buscarVenta(codigo_venta);
        return venta.getListaProductos();
    }

    @Override
    public String buscarVentaDia(LocalDate fecha_venta) {
        String mensj;
        Boolean mensaje = false;
        List<Venta> listaVentas = this.obtenerVentas();
        int cant = 0;
        double suma = 0;

        for (Venta ven : listaVentas) {
            if (ven.getFecha_venta().equals(fecha_venta)) {
                cant++;
                suma += ven.getTotal();
                mensaje = true;
            }
        }

        if (mensaje) {
            mensj = "Suma total: " + suma + "\n " + "Cantidad de ventas: " + cant;
        } else {
            mensj = "No se registran ventas en esa fecha";
        }

        return mensj;
    }

    @Override
    public VentaProdClienteDTO obtenerMayorVenta() {
        VentaProdClienteDTO venProdCli = new VentaProdClienteDTO();
        List<Venta> listaVentas = this.obtenerVentas();
        Venta ventaMay = null;
        double mayVen = 0;
        int cant = 0;

        for (Venta ven : listaVentas) {
            if (ven.getTotal() > mayVen) {
                cant = 0;
                mayVen = ven.getTotal();
                ventaMay = ven;
                cant = listaVentas.size();
            }
        }

        venProdCli.setCodigo_venta(ventaMay.getCodigo_venta());
        venProdCli.setTotal(ventaMay.getTotal());
        venProdCli.setCantidad(cant);
        venProdCli.setNombre(ventaMay.getUnCliente().getNombre());
        venProdCli.setApellido(ventaMay.getUnCliente().getApellido());

        return venProdCli;
    }

}
