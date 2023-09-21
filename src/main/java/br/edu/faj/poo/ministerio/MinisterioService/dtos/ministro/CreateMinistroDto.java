package br.edu.faj.poo.ministerio.MinisterioService.dtos.ministro;

import lombok.Data;

import java.util.Date;

@Data
public class CreateMinistroDto {
    private String nome;
    private String partido;
    private Date dataEntrada;
    private Date dataSaida;
    private Double salario;
    private Integer ministerioId;
}
