package br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProprietarioDTO {

    private String nome_completo;

    private String cpf;

    private String telefone;

    private String email;

    private String endereco;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_cadastro;
}
