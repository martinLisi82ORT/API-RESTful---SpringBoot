package com.example.API_BazarSpringBoot.service;

import com.example.API_BazarSpringBoot.model.Cliente;
import java.util.List;

public interface IClienteService {

    public String crearCliente(Cliente cli);

    public List<Cliente> obtenerClientes();

    public Cliente buscarCliente(Long id);

    public void modificarCliente(Long idOrig, Long idNuevo, String nomNuevo,
            String apellNuevo, String dniNuevo);

    public String eliminarCliente(Long id);

    public void modificarCliente(Cliente clien);

}
