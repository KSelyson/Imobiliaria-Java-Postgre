package br.com.unifacisa.sql_poo.sistema_imobiliaria.service;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ContratoModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto.ContratoDTO;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ContratoRepository;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ImovelRepository;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.InquilinoRepository;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ProprietarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContratoService {

    //Injeção de repositorios
    private final ContratoRepository repository;
    private final ImovelRepository imovelRepository;
    private final InquilinoRepository inquilinoRepository;
    private final ProprietarioRepository proprietarioRepository;

    @Autowired
    public ContratoService(ContratoRepository repository, ImovelRepository imovelRepository, InquilinoRepository inquilinoRepository
            , ProprietarioRepository proprietarioRepository) {
        this.repository = repository;
        this.imovelRepository = imovelRepository;
        this.inquilinoRepository = inquilinoRepository;
        this.proprietarioRepository = proprietarioRepository;
    }

    //GET geral
    public void findAll(){

    }

    //GET por ID
    public ContratoModel findById(Integer id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Contrato não encontrado"));
    }

    //POST
    @Transactional
    public void create(ContratoDTO dto){

    }

    //PUT
    @Transactional
    public void update(int id, ContratoDTO dto){

    }

    //DEL
    @Transactional
    public void delete(int id){

    }

}
