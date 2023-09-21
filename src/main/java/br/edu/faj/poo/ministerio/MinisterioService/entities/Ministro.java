package br.edu.faj.poo.ministerio.MinisterioService.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Detalhes sobre a entidade Ministro")
public class Ministro extends Politico {
    @ApiModelProperty(notes = "Salário do ministro")
    private Double salario;

    @ApiModelProperty(notes = "ID do ministério ao qual o ministro está associado")
    private Integer ministerioId;
}
