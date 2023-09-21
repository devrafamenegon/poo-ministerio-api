package br.edu.faj.poo.ministerio.MinisterioService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Secretaria {
    private Integer id;
    private String nome;
    private Integer numFuncionarios;
    private Double verba;
    private Integer ministerioId;
}
