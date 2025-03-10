package com.dio;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Posicao {

    private Linha linha;
    private Coluna coluna;
    private Cubo cubo;
    private int valor;

    public Posicao (Linha linha, Coluna coluna) {
        this.linha = linha;
        this.coluna = coluna;
    }

}
