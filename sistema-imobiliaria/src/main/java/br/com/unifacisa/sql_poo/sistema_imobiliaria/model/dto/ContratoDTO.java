package br.com.unifacisa.sql_poo.sistema_imobiliaria.model.dto;

import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ImovelModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.InquilinoModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.ProprietarioModel;
import br.com.unifacisa.sql_poo.sistema_imobiliaria.model.enums.StatusContrato;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContratoDTO {

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_inicio;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data_fim;

    @NotBlank
    private StatusContrato status;

    @NotBlank
    private String clausulas;

    @NotNull
    private InquilinoModel inquilino;

    @NotNull
    private ProprietarioModel proprietario;

    @NotNull
    private ImovelModel imovel;
}
