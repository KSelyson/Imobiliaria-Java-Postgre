package br.com.unifacisa.sql_poo.sistema_imobiliaria.controller;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ImovelModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.InquilinoModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ImovelRepository;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.InquilinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/imovel")
public class ImovelController {

    private final ImovelRepository repository;

    @Autowired
    public ImovelController(ImovelRepository repository) {
        this.repository = repository;
    }

    // Post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ImovelModel> create(@RequestBody ImovelModel imovel) {
        ImovelModel saved = repository.save(imovel);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // Get Geral
    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<ImovelModel>> findAll() {
        List<ImovelModel> imovels = repository.findAll();
        return ResponseEntity.ok(imovels);
    }

    // Get por ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ImovelModel> findById(@PathVariable int id) {
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
                .map( imovel -> {
                    repository.delete(imovel);
                    return Void.TYPE;
                })
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Imovel com id " + id + " não encontrado") );
    }
}
