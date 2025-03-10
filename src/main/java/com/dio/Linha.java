package com.dio;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Linha extends Modal {

    private int numero;
    private List<Posicao> posicoes;
//    private Posicao posicao1;
//    private Posicao posicao2;
//    private Posicao posicao3;
//    private Posicao posicao4;
//    private Posicao posicao5;
//    private Posicao posicao6;
//    private Posicao posicao7;
//    private Posicao posicao8;
//    private Posicao posicao9;

    public Linha (int numero) {
        this.numero = numero;
    }

}
