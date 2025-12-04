package br.com.unifacisa.sql_poo.sistema_imobiliaria.controller;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.InquilinoModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ProprietarioModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.InquilinoRepository;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/proprietario")
public class ProprietarioController {

    private final ProprietarioRepository repository;

    @Autowired
    public ProprietarioController(ProprietarioRepository repository) {
        this.repository = repository;
    }

    // Post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProprietarioModel> create(@RequestBody ProprietarioModel proprietario) {
        ProprietarioModel saved = repository.save(proprietario);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Get Geral
    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<ProprietarioModel>> findAll() {
        List<ProprietarioModel> proprietarios = repository.findAll();
        return ResponseEntity.ok(proprietarios);
    }

    // Get por ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ProprietarioModel> findById(@PathVariable int id) {
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
                .map( proprietario -> {
                    repository.delete(proprietario);
                    return Void.TYPE;
                })
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Proprietario com id " + id + " não encontrado") );
    }
}
