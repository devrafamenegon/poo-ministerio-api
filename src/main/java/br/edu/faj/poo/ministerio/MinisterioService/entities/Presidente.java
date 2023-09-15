package br.edu.faj.poo.ministerio.MinisterioService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Presidente extends Politico {
    private double verba;
    private double salario;
    public void criarMinisterio() {};

}
