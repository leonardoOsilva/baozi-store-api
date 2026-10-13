package com.example.baozistore.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.baozistore.model.Pedido;
import com.example.baozistore.repository.PedidoRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import com.example.baozistore.repository.ClienteRepository;
import com.example.baozistore.repository.ProdutoRepository;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoController(PedidoRepository pedidoRepository, ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }
    
    @PostMapping
    public Pedido cadastrarPedido(@RequestBody Pedido pedido) {

        if (pedido.getClienteId() == null || !clienteRepository.existsById(pedido.getClienteId())) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }

        if (pedido.getProdutoId() == null || !produtoRepository.existsById(pedido.getProdutoId())) {

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        return pedidoRepository.save(pedido);
    }
    
    @GetMapping("/{id}")
    public Pedido buscarPedido(@PathVariable Long id) {
        return pedidoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));
    }    
    
    @PutMapping("/{id}")
    public Pedido atualizarPedido(@PathVariable Long id, @RequestBody Pedido pedidoAtualizado) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado"));

        if (pedidoAtualizado.getClienteId() == null ||
            !clienteRepository.existsById(pedidoAtualizado.getClienteId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }

        if (pedidoAtualizado.getProdutoId() == null ||
            !produtoRepository.existsById(pedidoAtualizado.getProdutoId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado");
        }

        pedido.setClienteId(pedidoAtualizado.getClienteId());
        pedido.setProdutoId(pedidoAtualizado.getProdutoId());
        pedido.setQuantidade(pedidoAtualizado.getQuantidade());

        return pedidoRepository.save(pedido);
    }
    
    @DeleteMapping("/{id}")
    public void excluirPedido(@PathVariable Long id) {
        if (!pedidoRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado");
        }

        pedidoRepository.deleteById(id);
    }
}