package br.com.unifacisa.sql_poo.sistema_imobiliaria.repository;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ProprietarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProprietarioRepository extends JpaRepository<ProprietarioModel, Integer> {
}
