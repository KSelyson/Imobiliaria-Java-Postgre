package br.com.unifacisa.sql_poo.sistema_imobiliaria.controller;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.InquilinoModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.InquilinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/inquilino")
public class InquilinoController {

    private final InquilinoRepository repository;

    @Autowired
    public InquilinoController(InquilinoRepository repository) {
        this.repository = repository;
    }

    // Post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InquilinoModel> create(@RequestBody InquilinoModel inquilino) {
        InquilinoModel saved = repository.save(inquilino);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Get Geral
    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<InquilinoModel>> findAll() {
        List<InquilinoModel> inquilinos = repository.findAll();
        return ResponseEntity.ok(inquilinos);
    }

    // Get por ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<InquilinoModel> findById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        repository
                .findById(id)
                .map( inquilino -> {
                    repository.delete(inquilino);
                    return Void.TYPE;
                })
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Inquilino com id " + id + " não encontrado") );
    }
}
