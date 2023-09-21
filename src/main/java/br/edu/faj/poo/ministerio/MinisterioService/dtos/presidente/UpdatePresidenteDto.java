package br.edu.faj.poo.ministerio.MinisterioService.dtos.presidente;

import lombok.Data;

import java.util.Date;

@Data
public class UpdatePresidenteDto {
    private String nome;
    private String partido;
    private Date dataEntrada;
    private Date dataSaida;
    private Double salario;
    private Double verba;
}
