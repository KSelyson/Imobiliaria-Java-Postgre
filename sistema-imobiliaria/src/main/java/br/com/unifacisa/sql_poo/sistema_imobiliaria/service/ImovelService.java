package br.com.unifacisa.sql_poo.sistema_imobiliaria.service;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ImovelModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto.ImovelDTO;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.repository.ImovelRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ImovelService {

    private final ImovelRepository repository;

    @Autowired
    public ImovelService(ImovelRepository repository) {
        this.repository = repository;
    }

    //GET geral
    public List<ImovelModel> findAll(){
        return repository.findAll();
    }

    //GET por ID
    public ImovelModel findById(int id){
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Imovel não encontrado"));
    }

    //POST
    @Transactional
    public ImovelModel create(ImovelDTO dto){
        ImovelModel imovel = new ImovelModel();

        imovel.setTipo(dto.getTipo());
        imovel.setEndereco(dto.getEndereco());
        imovel.setArea_total(dto.getArea_total());
        imovel.setQtd_quartos(dto.getQtd_quartos());
        imovel.setValor_aluguel(dto.getValor_aluguel());
        imovel.setStatus(dto.getStatus());

        return repository.save(imovel);
    }

    //PUT
    @Transactional
    public void update(int id, ImovelDTO dto){

        ImovelModel imovel = repository.findById(id)
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Imovel com id " + id + " não encontrado"));

        imovel.setTipo(dto.getTipo());
        imovel.setEndereco(dto.getEndereco());
        imovel.setArea_total(dto.getArea_total());
        imovel.setQtd_quartos(dto.getQtd_quartos());
        imovel.setValor_aluguel(dto.getValor_aluguel());
        imovel.setStatus(dto.getStatus());

        repository.save(imovel);
    }

    //DEL
    @Transactional
    public void delete(int id){

        ImovelModel imovel = repository.findById(id)
                .orElseThrow( () ->  new ResponseStatusException(HttpStatus.NOT_FOUND, "Imovel com id " + id + " não encontrado") );

        repository.delete(imovel);
    }
}
