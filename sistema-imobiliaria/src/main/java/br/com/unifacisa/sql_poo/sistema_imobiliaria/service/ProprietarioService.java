package br.com.unifacisa.sql_poo.sistema_imobiliaria.service;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ProprietarioModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProprietarioService {

    @Autowired
    private ProprietarioRepository repository;

    public ProprietarioModel getProprietarioModel() {

    }

    public ProprietarioModel getProprietarioModelById(int id) {

    }

    public ProprietarioModel createProprietarioModel(String nome) {

    }

    public ProprietarioModel updateProprietarioModel(int id, String nome) {

    }

    public void deleteProprietarioModelById(int id) {

    }
}
