package br.com.unifacisa.sql_poo.sistema_imobiliaria.repository;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ContratoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<ContratoModel, Integer> {
}
