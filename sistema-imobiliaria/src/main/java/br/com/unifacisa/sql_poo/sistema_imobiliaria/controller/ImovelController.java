package br.com.unifacisa.sql_poo.sistema_imobiliaria.controller;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ImovelModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto.ImovelDTO;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ImovelRepository;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.service.ContratoService;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.service.ImovelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/imovel")
public class ImovelController {

    private final ImovelService imovelService;

    @Autowired
    public ImovelController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    // Get Geral
    @GetMapping
    public ResponseEntity<List<ImovelModel>> findAll() {
        //Já retorna a lista do service
        return ResponseEntity.ok(imovelService.findAll());
    }

    // Get por ID
    @GetMapping("/{id}")
    public ResponseEntity<ImovelModel> findById(@PathVariable int id) {
        //Cria um objeto e chama o service
        ImovelModel imovel = imovelService.findById(id);
        return ResponseEntity.ok(imovel);
    }

    // Post
    @PostMapping
    public ResponseEntity<ImovelModel> create(@RequestBody ImovelDTO dto) {
        //Cria um objeto e manda para o service
        ImovelModel imovel = imovelService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(imovel);
    }

    // Update
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable int id, @RequestBody @Valid ImovelDTO dto){
        imovelService.update(id, dto);
    }

    // Delete
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        imovelService.delete(id);
    }
}
