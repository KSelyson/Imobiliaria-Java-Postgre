package br.com.unifacisa.sql_poo.sistema_imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProprietarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_proprietario;

    @Column
    private String nome_completo;

    @Column
    private String cpf;

    @Column
    private String telefone;

    @Column
    private String email;

    @Column
    private String endereco;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date data_cadastro;
}
