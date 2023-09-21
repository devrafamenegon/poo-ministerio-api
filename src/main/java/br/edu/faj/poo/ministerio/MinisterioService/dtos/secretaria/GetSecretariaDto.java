package br.edu.faj.poo.ministerio.MinisterioService.dtos.secretaria;

import lombok.Data;

@Data
public class GetSecretariaDto {
    private String id;
    private String nome;
    private Integer numFuncionarios;
    private Double verba;
    private Integer ministerioId;
}
