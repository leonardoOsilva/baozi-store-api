package com.example.baozistore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;


import com.example.baozistore.model.Cliente;
import com.example.baozistore.repository.ClienteRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private final ClienteRepository clienteRepository;
	
	public ClienteController(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
		
	}
	
	@GetMapping
	public List<Cliente> listarClientes() {
		return clienteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public Cliente buscarClientePorId(@PathVariable Long id) {
	    return clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	}
	
	@PostMapping
	public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
	    return clienteRepository.save(cliente);
	}
	
	@PutMapping("/{id}")
	public Cliente atualizarCliente(
	        @PathVariable Long id,
	        @RequestBody Cliente clienteAtualizado) {

	    Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
	    cliente.setNome(clienteAtualizado.getNome());
	    cliente.setClienteDesde(clienteAtualizado.getClienteDesde());

	    return clienteRepository.save(cliente);
	}
	
	@DeleteMapping("/{id}")
	public void excluirCliente(@PathVariable Long id) {

	    if (!clienteRepository.existsById(id)) {
	        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
	    }

	    clienteRepository.deleteById(id);
	}
}
