
package com.example.cliente;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@CrossOrigin(origins = "http://localhost:8081")

@RestController
@RequestMapping("/api")
public class ClienteController {

    @Autowired
    private ClienteRepository ClienteRepository;

    @GetMapping("/clientes")
    public ResponseEntity<Object> getClientes() {
        List<Cliente> Clientes = ClienteRepository.findAll();
        if (!Clientes.isEmpty()) {
            return new ResponseEntity<>(Clientes, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Não existem Clientes cadastrados.");
        }
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> getClienteById(@PathVariable Long id) {

        Optional<Cliente> ClienteOptional = ClienteRepository.findById(id);
        if (ClienteOptional.isPresent()) {
            return new ResponseEntity<>(ClienteOptional.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado com o id: " + id);
        }
    }

    @PostMapping("/clientes")
    public ResponseEntity<Object> createCliente(@Valid @RequestBody Cliente cliente) {

        if (cliente.getSenha() != null && !cliente.senhaConfirmada()) {
            return ResponseEntity.badRequest().body("A confirmação da senha falhou.");
        }

        Cliente savedCliente = ClienteRepository.save(cliente);
        return ResponseEntity.ok(savedCliente);
    }

    /*
     * @PostMapping("/clientes")
     * public Cliente createCliente(@Valid @RequestBody Cliente Cliente) {
     * return ClienteRepository.save(Cliente);
     * }
     */

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Object> updateCliente(@PathVariable Long id, @RequestBody Cliente updatedCliente) {
        Optional<Cliente> ClienteOptional = ClienteRepository.findById(id);

        if (ClienteOptional.isPresent()) {
            Cliente c = ClienteOptional.get();
            c.setNome(updatedCliente.getNome());
            c.setEmail(updatedCliente.getEmail());
            c.setCPF(updatedCliente.getCPF());
            c.setTelefone(updatedCliente.getTelefone());
            c.setSenha(updatedCliente.getSenha());
            c.setConfirmacaoSenha(updatedCliente.getConfirmacaoSenha());
            if (c.getSenha() != null && !c.senhaConfirmada()) {
                return ResponseEntity.badRequest()
                        .body("A confirmação de senha não pode ser alterada porque não é igual à senha.");
            }
            c.setCEP(updatedCliente.getCEP());
            c.setNumero(updatedCliente.getNumero());
            c.setLogradouro(updatedCliente.getLogradouro());
            c.setComplemento(updatedCliente.getComplemento());
            c.setBairro(updatedCliente.getBairro());
            c.setCidade(updatedCliente.getCidade());
            c.setEstado(updatedCliente.getEstado());
            ClienteRepository.save(c);
            return ResponseEntity.ok(c);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado com o id: " + id);
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Object> deleteCliente(@PathVariable Long id) {
        if (ClienteRepository.existsById(id)) {
            ClienteRepository.deleteById(id);
            return ResponseEntity.ok("Cliente excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado com o ID: " + id);
        }
    }

}