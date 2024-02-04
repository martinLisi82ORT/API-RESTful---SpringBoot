package com.example.API_BazarSpringBoot.service;

import com.example.API_BazarSpringBoot.model.Cliente;
import com.example.API_BazarSpringBoot.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private IClienteRepository cliRepo;

    @Override
    public String crearCliente(Cliente cli) {
        String mensj;

        if (this.buscarClienteDNI(cli.getDni()) != null) {
            mensj = "Cliente ya registrado";
        } else {
            cliRepo.save(cli);
            mensj = "Cliente creado correctamente: " + cli.getNombre() + " " + cli.getApellido();
        }

        return mensj;
    }

    @Override
    public List<Cliente> obtenerClientes() {
        return cliRepo.findAll();
    }

    @Override
    public String eliminarCliente(Long id) {
        String mens;
        Cliente clien = this.buscarCliente(id);
        if (clien != null) {
            cliRepo.deleteById(id);
            mens = "Cliente eliminado correctamente";
        } else {
            mens = "No se encontro al Cliente";
        }
        return mens;
    }

    @Override
    public void modificarCliente(Long idOrig, Long idNuevo, String nomNuevo, String apellNuevo, String dniNuevo) {
        Cliente cli = this.buscarCliente(idOrig);
        cli.setId_cliente(idNuevo);
        cli.setNombre(nomNuevo);
        cli.setApellido(apellNuevo);
        cli.setDni(dniNuevo);
        this.saveCliente(cli);
    }

    @Override
    public void modificarCliente(Cliente clien) {
        this.saveCliente(clien);
    }

    @Override
    public Cliente buscarCliente(Long id) {
        return cliRepo.findById(id).orElse(null);
    }

    private Cliente buscarClienteDNI(String dni) {
        List<Cliente> listaBD = this.obtenerClientes();
        Cliente clienteEnc = null;
        int pos = 0;

        while (pos < listaBD.size() && clienteEnc == null) {
            if (listaBD.get(pos).getDni().equals(dni)) {
                clienteEnc = listaBD.get(pos);
            }
            pos++;
        }
        return clienteEnc;
    }

    private void saveCliente(Cliente cli) {
        cliRepo.save(cli);
    }
}
