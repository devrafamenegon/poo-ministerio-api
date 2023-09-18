package br.edu.faj.poo.ministerio.MinisterioService.dtos.ministerio;

import lombok.Data;

@Data
public class CreateMinisterioDto {
    private String nome;
    private int numFuncionarios;
    private double verba;
}
