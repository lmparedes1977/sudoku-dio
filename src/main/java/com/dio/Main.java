package com.dio;

import java.security.cert.PolicyNode;

public class Main {
    public static void main(String[] args) {

        Play play = new Play();

        play.startSudoku();

        play.startPosicoesIniciais();

        System.out.print("\n##### SUDOKU ##### SUDOKU ##### SUDOKU #####!\n\n" + //
                        "Digite *linha, *coluna e *valor com uma sequencia de números inteiros.\n" + //
                        "Exemplo: '375' (3ª linha, 7ª coluna, valor 5)\n");

        boolean ganhou = false;        
        
        while (!ganhou) {
            play.print();
            play.joga(play.getSudoku());
            ganhou = play.chegaVitoria();
            if (ganhou) {
                System.out.println("####### PA RA BÉNS! ########");
            }

        }

    }


}