package com.example.API_BazarSpringBoot.controller;

import com.example.API_BazarSpringBoot.model.Cliente;
import com.example.API_BazarSpringBoot.service.IClienteService;
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
public class ClienteController {

    @Autowired
    private IClienteService cliServ;

    @PostMapping("/clientes/crear")
    public String crearCliente(@Valid @RequestBody Cliente cli, BindingResult result) {
        String mensj = "";
        if (result.hasErrors()) {
            for (ObjectError error : result.getAllErrors()) {
                mensj = error.getDefaultMessage();
            }

        } else {
            mensj = cliServ.crearCliente(cli);
        }

        return mensj;
    }

    @GetMapping("/clientes")
    public List<Cliente> obtenerClientes() {
        return cliServ.obtenerClientes();
    }

    @GetMapping("/clientes/{id_cliente}")
    public Cliente buscarCliente(@PathVariable Long id_cliente) {
        return cliServ.buscarCliente(id_cliente);
    }

    @DeleteMapping("/clientes/eliminar/{id_cliente}")
    public String eliminarCliente(@PathVariable Long id_cliente) {
        return cliServ.eliminarCliente(id_cliente);
    }

    @PutMapping("/clientes/editar/{id_cliente}")
    public Cliente modificarCliente(@PathVariable Long id_cliente,
            @RequestParam(required = false, name = "id_cliente") Long nuevoId,
            @RequestParam(required = false, name = "nombre") String nuevoNom,
            @RequestParam(required = false, name = "apellido") String nuevoApell,
            @RequestParam(required = false, name = "dni") String nuevoDni) {

        cliServ.modificarCliente(id_cliente, nuevoId, nuevoNom, nuevoApell, nuevoDni);
        return cliServ.buscarCliente(nuevoId);
    }

    @PutMapping("/clientes/editar")
    public Cliente modificarCliente(@RequestBody Cliente clien) {
        cliServ.modificarCliente(clien);
        return cliServ.buscarCliente(clien.getId_cliente());
    }

}
