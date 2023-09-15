package br.edu.faj.poo.ministerio.MinisterioService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Ministro extends Politico {
    private double salario;
    private int ministerio;

    public void representacao() {
        System.out.print("method representacao not implemented.");
    };
    public void despachos() {
        System.out.print("method despachos not implemented.");
    };
}
