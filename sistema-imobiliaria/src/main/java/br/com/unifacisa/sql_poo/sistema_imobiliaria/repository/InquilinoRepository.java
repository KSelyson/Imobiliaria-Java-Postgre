package br.com.unifacisa.sql_poo.sistema_imobiliaria.repository;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.InquilinoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquilinoRepository extends JpaRepository<InquilinoModel, Integer> {
}
