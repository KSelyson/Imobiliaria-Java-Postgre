package br.com.unifacisa.sql_poo.sistema_imobiliaria.model;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.enums.StatusImovel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.enums.TipoImovel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImovelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_imovel;

    @Column
    private TipoImovel tipo;

    @Column
    private String endereco;

    @Column
    private double area_total;

    @Column
    private int qtd_quartos;

    @Column
    private double valor_aluguel;

    @Column
    private StatusImovel status;
}
