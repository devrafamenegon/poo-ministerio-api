package br.edu.faj.poo.ministerio.MinisterioService.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Politico {
    private int id;
    private String nome;
    private String partido;
    private Date dataEntrada;
    private Date dataSaida;
}
