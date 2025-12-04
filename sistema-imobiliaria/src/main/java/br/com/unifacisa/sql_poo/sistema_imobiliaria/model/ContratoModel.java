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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_contrato;

    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date data_inicio;
    
    @Column
    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date data_fim;

    @Column
    private String status;

    @Column
    private String clausulas;

    @ManyToOne
    @JoinColumn(name = "fk_inquilino",  referencedColumnName = "id_inquilino")
    private InquilinoModel inquilino;

    @ManyToOne
    @JoinColumn(name = "fk_proprietario", referencedColumnName = "id_proprietario")
    private ProprietarioModel proprietario;

    @ManyToOne
    @JoinColumn(name = "fk_imovel", referencedColumnName = "id_imovel")
    private ImovelModel imovel;
}
