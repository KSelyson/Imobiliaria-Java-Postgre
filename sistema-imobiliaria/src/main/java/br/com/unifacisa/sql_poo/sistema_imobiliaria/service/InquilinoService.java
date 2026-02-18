package br.com.unifacisa.sql_poo.sistema_imobiliaria.service;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto.InquilinoDTO;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.InquilinoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InquilinoService {

    private final InquilinoRepository repository;

    @Autowired
    public InquilinoService(InquilinoRepository repository) {
        this.repository = repository;
    }

    //GET geral
    public void findAll(){

    }

    //GET por ID
    public void findById(int id){

    }

    //POST
    @Transactional
    public void create(InquilinoDTO dto){

    }

    //PUT
    @Transactional
    public void update(int id, InquilinoDTO dto){

    }

    //DEL
    @Transactional
    public void delete(int id){

    }
}
