package br.com.unifacisa.sql_poo.sistema_imobiliaria.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.channels.spi.SelectorProvider;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inquilino")
public class InquilinoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_inquilino;

    @Column
    private String nome_completo;

    @Column(columnDefinition = "bpchar")
    private String cpf;

    @Column
    private String telefone;

    @Column
    private String email;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_nascimento;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_cadastro;
}
