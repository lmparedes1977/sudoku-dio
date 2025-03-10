package com.dio;

import java.security.cert.PolicyNode;

public class Main {
    public static void main(String[] args) {

        Play play = new Play();

        play.startSudoku();

        play.startPosicoesIniciais();

        boolean ganhou = false;
        while (!ganhou) {
            play.print();
            play.joga(play.getSudoku());
            ganhou = play.chegaVitoria();
            if (ganhou) {
                System.out.println("PA RA BÉNS!");
            }

        }

    }


}