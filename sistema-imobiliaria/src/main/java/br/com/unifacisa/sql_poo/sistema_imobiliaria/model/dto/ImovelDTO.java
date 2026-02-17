package br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.enums.StatusImovel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.enums.TipoImovel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImovelDTO {

    private TipoImovel tipo;

    private String endereco;

    private int area_total;

    private int qtd_quartos;

    private BigDecimal valor_aluguel;

    private StatusImovel status;
}
