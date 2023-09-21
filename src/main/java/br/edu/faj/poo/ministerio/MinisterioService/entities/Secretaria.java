package br.edu.faj.poo.ministerio.MinisterioService.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Detalhes sobre a entidade Secretaria")
public class Secretaria {
    @ApiModelProperty(notes = "ID único da secretaria")
    private Integer id;

    @ApiModelProperty(notes = "Nome da secretaria")
    private String nome;

    @ApiModelProperty(notes = "Número de funcionários na secretaria")
    private Integer numFuncionarios;

    @ApiModelProperty(notes = "Verba da secretaria")
    private Double verba;

    @ApiModelProperty(notes = "ID do ministério ao qual a secretaria está associada")
    private Integer ministerioId;
}
