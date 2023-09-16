package br.edu.faj.poo.ministerio.MinisterioService.dtos.ministerio;

import lombok.Data;

@Data
public class CreateMinisterioDto {
    private String name;
    private int numFuncionarios;
    private double verba;
}
