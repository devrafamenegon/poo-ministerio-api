package br.edu.faj.poo.ministerio.MinisterioService.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Detalhes sobre a entidade Presidente")
public class Presidente extends Politico {
    @ApiModelProperty(notes = "Verba do presidente")
    private Double verba;

    @ApiModelProperty(notes = "Salário do presidente")
    private Double salario;
}
