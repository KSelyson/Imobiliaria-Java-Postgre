package br.com.unifacisa.sql_poo.sistema_imobiliaria.model;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.enums.StatusImovel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.enums.TipoImovel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "imovel")
public class ImovelModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_imovel;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "tipo_imovel")
    private TipoImovel tipo;

    @Column
    private String endereco;

    @Column
    private int area_total;

    @Column
    private int qtd_quartos;

    @Column
    private BigDecimal valor_aluguel;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "status_imovel")
    private StatusImovel status;
}
