package br.edu.faj.poo.ministerio.MinisterioService.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Detalhes sobre a entidade Ministerio")
public class Ministerio {
    @ApiModelProperty(notes = "ID único do ministério")
    private Integer id;

    @ApiModelProperty(notes = "Nome do ministério")
    private String nome;

    @ApiModelProperty(notes = "Número de funcionários no ministério")
    private Integer numFuncionarios;

    @ApiModelProperty(notes = "Verba do ministério")
    private Double verba;
}