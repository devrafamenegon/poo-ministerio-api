package br.edu.faj.poo.ministerio.MinisterioService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Secretaria {
    private int id;
    private String nome;
    private int numFuncionarios;
    private double verba;
    private int ministerioId;
}
