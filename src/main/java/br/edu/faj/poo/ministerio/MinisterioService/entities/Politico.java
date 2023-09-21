package br.edu.faj.poo.ministerio.MinisterioService.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Detalhes sobre a entidade Politico")
public class Politico {
    @ApiModelProperty(notes = "ID único do político")
    private Integer id;

    @ApiModelProperty(notes = "Nome do político")
    private String nome;

    @ApiModelProperty(notes = "Partido político do político")
    private String partido;

    @ApiModelProperty(notes = "Data de entrada do político")
    private Date dataEntrada;

    @ApiModelProperty(notes = "Data de saída do político")
    private Date dataSaida;
}
