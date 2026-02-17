package br.com.unifacisa.sql_poo.sistema_imobiliaria.controller;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ContratoModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ImovelModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.InquilinoModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ProprietarioModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto.ContratoDTO;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ContratoRepository;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ImovelRepository;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.InquilinoRepository;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ProprietarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/contrato")
public class ContratoController {

    //Injeção de repositorios
    private final ContratoRepository repository;
    private final ImovelRepository imovelRepository;
    private final InquilinoRepository inquilinoRepository;
    private final ProprietarioRepository proprietarioRepository;

    @Autowired
    public ContratoController(ContratoRepository repository, ImovelRepository imovelRepository, InquilinoRepository inquilinoRepository
    , ProprietarioRepository proprietarioRepository) {
        this.repository = repository;
        this.imovelRepository = imovelRepository;
        this.inquilinoRepository = inquilinoRepository;
        this.proprietarioRepository = proprietarioRepository;
    }

    //GET
    @GetMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<List<ContratoModel>> findAll() {
        List<ContratoModel> contratos = repository.findAll();
        return ResponseEntity.ok(contratos);
    }


    //GET por ID
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<ContratoModel> findById(@PathVariable int id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    //POST
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ContratoModel> create(@RequestBody ContratoDTO dto) {

        //Encontrando os objetos
        InquilinoModel inquilino = inquilinoRepository.findById(dto.getInquilino().getId_inquilino())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inquilino com id " + dto.getInquilino().getId_inquilino() + " não encontrado.") );

        ProprietarioModel proprietario = proprietarioRepository.findById(dto.getProprietario().getId_proprietario())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proprietario com id " + dto.getProprietario().getId_proprietario() + " não encontrado."));

        ImovelModel imovel = imovelRepository.findById(dto.getImovel().getId_imovel())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imovel com o id " + dto.getImovel().getId_imovel() + "não encontrado."));

        //Set dos atributos especificos do contrato
        ContratoModel contrato = new ContratoModel();

        contrato.setData_inicio(dto.getData_inicio());
        contrato.setData_fim(dto.getData_fim());
        contrato.setStatus(dto.getStatus());
        contrato.setClausulas(dto.getClausulas());

        //Set dos objetos ligados ao contrato
        contrato.setInquilino(inquilino);
        contrato.setProprietario(proprietario);
        contrato.setImovel(imovel);

        repository.save(contrato);

        return ResponseEntity.status(HttpStatus.CREATED).body(contrato);
    }


    //UPDATE
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable int id, @RequestBody @Valid ContratoDTO dto){
        repository
                .findById(id)
                .map( contrato -> {

                    //Encontrando os objetos
                    InquilinoModel inquilino = inquilinoRepository.findById(dto.getInquilino().getId_inquilino())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Inquilino com id " + dto.getInquilino().getId_inquilino() + " não encontrado.") );

                    ProprietarioModel proprietario = proprietarioRepository.findById(dto.getProprietario().getId_proprietario())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Proprietario com id " + dto.getProprietario().getId_proprietario() + " não encontrado."));

                    ImovelModel imovel = imovelRepository.findById(dto.getImovel().getId_imovel())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Imovel com o id " + dto.getImovel().getId_imovel() + "não encontrado."));

                    //Atualizando os atributos proprios
                    contrato.setData_inicio(dto.getData_inicio());
                    contrato.setData_fim(dto.getData_fim());
                    contrato.setStatus(dto.getStatus());
                    contrato.setClausulas(dto.getClausulas());

                    //Atualizando os objetos
                    contrato.setInquilino(inquilino);
                    contrato.setProprietario(proprietario);
                    contrato.setImovel(imovel);

                    return repository.save(contrato);

                }).orElseThrow(() ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Contrato com id " + id + " não encontrado") );
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        repository
                .findById(id)
                .map( contrato -> {
                    repository.delete(contrato);
                    return Void.TYPE;
                })
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Contrato com id " + id + " não encontrado") );
    }
}
