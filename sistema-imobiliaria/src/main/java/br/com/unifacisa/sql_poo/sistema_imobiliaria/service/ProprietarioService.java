package br.com.unifacisa.sql_poo.sistema_imobiliaria.service;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ProprietarioModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto.ContratoDTO;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto.ProprietarioDTO;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ProprietarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProprietarioService {

    private final ProprietarioRepository repository;

    @Autowired
    public ProprietarioService(ProprietarioRepository repository) {
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
    public void create(ProprietarioDTO dto){

    }

    //PUT
    @Transactional
    public void update(int id, ProprietarioDTO dto){

    }

    //DEL
    @Transactional
    public void delete(int id){

    }
}
