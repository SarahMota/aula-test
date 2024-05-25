
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
public class PrestadorController {

    @Autowired
    private PrestadorRepository PrestadorRepository;

    @GetMapping("/prestadores")
    public ResponseEntity<Object> getPrestadores() {
        List<Prestador> Prestadores = PrestadorRepository.findAll();
        if (!Prestadores.isEmpty()) {
            return new ResponseEntity<>(Prestadores, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Não existem Prestadores cadastrados.");
        }
    }

    @GetMapping("/prestadores/{id}")
    public ResponseEntity<Object> getPrestadorById(@PathVariable Long id) {

        Optional<Prestador> PrestadorOptional = PrestadorRepository.findById(id);
        if (PrestadorOptional.isPresent()) {
            return new ResponseEntity<>(PrestadorOptional.get(), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestador não encontrado com o id: " + id);
        }
    }

    @PostMapping("/prestadores")
    public ResponseEntity<Object> createPrestador(@Valid @RequestBody Prestador prestador) {

        if (prestador.getSenha() != null && !prestador.senhaConfirmada()) {
            return ResponseEntity.badRequest().body("A confirmação da senha falhou.");
        }

        Prestador savedPrestador = PrestadorRepository.save(prestador);
        return ResponseEntity.ok(savedPrestador);
    }

    /*
     * @PostMapping("/prestadores")
     * public Prestador createPrestador(@Valid @RequestBody Prestador Prestador) {
     * return PrestadorRepository.save(Prestador);
     * }
     */

    @PutMapping("/prestadores/{id}")
    public ResponseEntity<Object> updatePrestador(@PathVariable Long id, @RequestBody Prestador updatedPrestador) {
        Optional<Prestador> PrestadorOptional = PrestadorRepository.findById(id);

        if (PrestadorOptional.isPresent()) {
            Prestador p = PrestadorOptional.get();
            p.setNome(updatedPrestador.getNome());
            p.setEmail(updatedPrestador.getEmail());
            p.setCPF(updatedPrestador.getCPF());
            p.setTelefone(updatedPrestador.getTelefone());
            p.setSenha(updatedPrestador.getSenha());
            p.setConfirmacaoSenha(updatedPrestador.getConfirmacaoSenha());
            if (p.getSenha() != null && !p.senhaConfirmada()) {
                return ResponseEntity.badRequest()
                        .body("A confirmação de senha não pode ser alterada porque não é igual à senha.");
            }
            p.setCEP(updatedPrestador.getCEP());
            p.setNumero(updatedPrestador.getNumero());
            p.setLogradouro(updatedPrestador.getLogradouro());
            p.setComplemento(updatedPrestador.getComplemento());
            p.setBairro(updatedPrestador.getBairro());
            p.setCidade(updatedPrestador.getCidade());
            p.setEstado(updatedPrestador.getEstado());
            PrestadorRepository.save(p);
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestador não encontrado com o id: " + id);
        }
    }

    @DeleteMapping("/prestadores/{id}")
    public ResponseEntity<Object> deletePrestador(@PathVariable Long id) {
        if (PrestadorRepository.existsById(id)) {
            PrestadorRepository.deleteById(id);
            return ResponseEntity.ok("Prestador excluído com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prestador não encontrado com o ID: " + id);
        }
    }

}