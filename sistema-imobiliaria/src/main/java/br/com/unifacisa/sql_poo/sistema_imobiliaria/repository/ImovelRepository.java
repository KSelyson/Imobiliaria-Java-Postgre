package br.com.unifacisa.sql_poo.sistema_imobiliaria.repository;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ImovelModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<ImovelModel, Integer> {
}
