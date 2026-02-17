package br.com.unifacisa.sql_poo.sistema_imobiliaria.controller;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ImovelModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.InquilinoModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ProprietarioModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto.InquilinoDTO;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.InquilinoRepository;
import jakarta.validation.Valid;
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

    // SAFE
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

    // Post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InquilinoModel> create(@RequestBody InquilinoDTO dto) {

        InquilinoModel inquilino = new InquilinoModel();

        inquilino.setNome_completo(dto.getNome_completo());
        inquilino.setCpf(dto.getCpf());
        inquilino.setTelefone(dto.getTelefone());
        inquilino.setEmail(dto.getEmail());
        inquilino.setData_nascimento(dto.getData_nascimento());
        inquilino.setData_cadastro(dto.getData_cadastro());

        repository.save(inquilino);

        return ResponseEntity.status(HttpStatus.CREATED).body(inquilino);
    }

    // Update
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody @Valid InquilinoDTO dto) {
        repository.findById(id)
                .map(inquilino -> {

                    inquilino.setNome_completo(dto.getNome_completo());
                    inquilino.setCpf(dto.getCpf());
                    inquilino.setTelefone(dto.getTelefone());
                    inquilino.setEmail(dto.getEmail());
                    inquilino.setData_nascimento(dto.getData_nascimento());
                    inquilino.setData_cadastro(dto.getData_cadastro());


                    return repository.save(inquilino);
                })
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "proprietario com id " + id + " não encontrado") );
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
