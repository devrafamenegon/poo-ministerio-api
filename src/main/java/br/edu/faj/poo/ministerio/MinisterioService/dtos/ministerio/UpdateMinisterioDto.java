package br.edu.faj.poo.ministerio.MinisterioService.dtos.ministerio;

import lombok.Data;

@Data
public class UpdateMinisterioDto {
    private String nome;
    private Integer numFuncionarios;
    private Double verba;
}
