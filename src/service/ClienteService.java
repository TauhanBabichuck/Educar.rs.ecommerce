/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Cliente;
import java.util.List;
import repository.ClienteRepository;
import repository.ProdutoRepository;

/**
 *
 * @author Escola
 */
public class ClienteService {
    private final ClienteRepository clienteRepository = new ClienteRepository();

    public Cliente validarLogin(Cliente cliente) {
        return clienteRepository.validarLogin(cliente);
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.salvarCliente(cliente);
    }
}
