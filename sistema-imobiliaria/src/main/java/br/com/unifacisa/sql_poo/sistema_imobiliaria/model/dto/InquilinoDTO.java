package br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InquilinoDTO {

    private String nome_completo;

    private String cpf;

    private String telefone;

    private String email;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nascimento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_cadastro;
}
