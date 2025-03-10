package com.dio;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class Play {

    private Sudoku sudoku;

    private boolean checaGeral(int valor, int linha, int coluna, int cubo) {
        return checaLinha(valor, linha) && checaColuna(valor, coluna) && checaCubo(valor, cubo);
    }

    private boolean checaCubo(int valor, int cubo) {
        for (Posicao p: sudoku.getCubos().get(cubo).getPosicoes()) {
            if (p.getValor() == valor) {
                System.out.println("Já existe no cubo!");
                return false;
            }
        }
        return true;
    }

    private boolean checaColuna(int valor, int coluna) {
        for (Posicao p: sudoku.getColunas().get(coluna).getPosicoes()) {
            if (p.getValor() == valor) {
                System.out.println("Já existe na coluna!");
                return false;
            }
        }
        return true;
    }

    private boolean checaLinha(int valor, int linha) {
        for (Posicao p: sudoku.getLinhas().get(linha).getPosicoes()) {
            if (p.getValor() == valor) {
                System.out.println("Já existe na linha!");
                return false;
            }
        }
        return true;
    }

    public boolean chegaVitoria() {
        int totalAcertos = 0;
        for (Linha linha: sudoku.getLinhas()) {
            totalAcertos += checaPosicoes(linha.getPosicoes());
        }
        for (Coluna coluna: sudoku.getColunas()) {
            totalAcertos += checaPosicoes(coluna.getPosicoes());
        }
        for (Cubo cubo: sudoku.getCubos()) {
            totalAcertos += checaPosicoes(cubo.getPosicoes());
        }
        return totalAcertos == 243;
    }

    private int checaPosicoes(List<Posicao> posicoes) {
        int count = 0;
        for (Posicao p: posicoes) {
            for (int i = 1; i <= 9; i++) {
                if (i == p.getValor()) {
                    count++;
                }
            }
        }
        return count;
    }

    public void startSudoku () {
        sudoku = new Sudoku();
        sudoku.setLinhas(startLinhas());
        sudoku.setColunas(startColunas());
        sudoku.setCubos(startCubos());
    }

    public List<Linha> startLinhas() {
        List<Linha> linhas = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Linha linha = new Linha(i);
            linha.setPosicoes(startPosicoes(i, linha));
            linhas.add(linha);
        }
        return linhas;
    }

    public List<Coluna> startColunas() {
        List<Coluna> colunas = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Coluna coluna = new Coluna(i);
            coluna.setPosicoes(startPosicoes(i, coluna));
            colunas.add(coluna);
        }
        return colunas;
    }

    public List<Cubo> startCubos() {
        List<Cubo> cubos = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            Cubo cubo = new Cubo(i);
            cubo.setPosicoes(startPosicoes(i, cubo));
            cubos.add(cubo);
        }
        return cubos;
    }

    public List<Posicao> startPosicoes(int i, Modal modal) {
        List<Posicao> posicoes = new ArrayList<>();
        if (modal instanceof Linha) {
            for (int j = 0; j < 9; j++) {
                Posicao posicao = new Posicao((Linha) modal, new Coluna(j));
                posicoes.add(posicao);
            }
            return posicoes;
        } else if (modal instanceof Coluna) {
            for (int j = 0; j < 9; j++) {
                posicoes.add(sudoku.getLinhas().get(j).getPosicoes().get(i));
            }
            return posicoes;
        } else if (modal instanceof Cubo){
            if (i == 0) {
                for (Linha l: sudoku.getLinhas()) {
                    for (Posicao p: l.getPosicoes()) {
                        if (p.getLinha().getNumero() < 3 && p.getColuna().getNumero() < 3) {
                            posicoes.add(p);
                            p.setCubo((Cubo) modal);
                        }
                    }
                }
                return posicoes;
            } else if (i == 1) {
                for (Linha l: sudoku.getLinhas()) {
                    for (Posicao p: l.getPosicoes()) {
                        if (p.getLinha().getNumero() < 3 && p.getColuna().getNumero() < 6 && p.getColuna().getNumero() > 2 ) {
                            posicoes.add(p);
                            p.setCubo((Cubo) modal);
                        }
                    }
                }
                return posicoes;
            } else if (i == 2) {
                for (Linha l: sudoku.getLinhas()) {
                    for (Posicao p: l.getPosicoes()) {
                        if (p.getLinha().getNumero() < 3 && p.getColuna().getNumero() > 5) {
                            posicoes.add(p);
                            p.setCubo((Cubo) modal);
                        }
                    }
                }
                return posicoes;
            } else if (i == 3) {
                for (Linha l: sudoku.getLinhas()) {
                    for (Posicao p : l.getPosicoes()) {
                        if (p.getLinha().getNumero() > 2 && p.getLinha().getNumero() < 6  && p.getColuna().getNumero() < 3) {
                            posicoes.add(p);
                            p.setCubo((Cubo) modal);
                        }
                    }
                }
                return posicoes;
            } else if (i == 4) {
                for (Linha l: sudoku.getLinhas()) {
                    for (Posicao p : l.getPosicoes()) {
                        if (p.getLinha().getNumero() > 2 && p.getLinha().getNumero() < 6 && p.getColuna().getNumero() < 6 && p.getColuna().getNumero() > 2 ) {
                            posicoes.add(p);
                            p.setCubo((Cubo) modal);
                        }
                    }
                }
                return posicoes;
            } else if (i == 5) {
                for (Linha l: sudoku.getLinhas()) {
                    for (Posicao p: l.getPosicoes()) {
                        if (p.getLinha().getNumero() > 2 && p.getLinha().getNumero() < 6 && p.getColuna().getNumero() > 5) {
                            posicoes.add(p);
                            p.setCubo((Cubo) modal);
                        }
                    }
                }
                return posicoes;
            } else if (i == 6) {
                for (Linha l: sudoku.getLinhas()) {
                    for (Posicao p: l.getPosicoes()) {
                        if (p.getLinha().getNumero() > 5 && p.getColuna().getNumero() < 3) {
                            posicoes.add(p);
                            p.setCubo((Cubo) modal);
                        }
                    }
                }
                return posicoes;
            } else if (i == 7) {
                for (Linha l: sudoku.getLinhas()) {
                    for (Posicao p: l.getPosicoes()) {
                        if (p.getLinha().getNumero() > 5 && p.getColuna().getNumero() < 6 && p.getColuna().getNumero() > 2 ) {
                            posicoes.add(p);
                            p.setCubo((Cubo) modal);
                        }
                    }
                }
                return posicoes;
            } else if (i == 8) {
                for (Linha l: sudoku.getLinhas()) {
                    for (Posicao p : l.getPosicoes()) {
                        if (p.getLinha().getNumero() > 5 && p.getColuna().getNumero() > 5) {
                            posicoes.add(p);
                            p.setCubo((Cubo) modal);
                        }
                    }
                }
                return posicoes;
            }
        }
        return posicoes;
    }

    public void print() {
        System.out.println();
        int count1 = 0;
        for (Linha l: sudoku.getLinhas()) {
            System.out.print("| ");
            int count2 = 0;
            for (Posicao p: l.getPosicoes()) {
                System.out.print(p.getValor() + " | ");
                if (count2 == 2 || count2 == 5) {
                    System.out.print("| ");
                }
                count2++;
            }
            System.out.println();
            if (count1 == 2 || count1 == 5) {
                System.out.println("-----------------------------------------");
            }
            count1++;
        }
    }

    public void joga(Sudoku sudoku) {
        Scanner scan = new Scanner(System.in);
        System.out.print("\nManda: ");
        String jogada = scan.nextLine();
        int linha = 0;
        int coluna = 0;
        int valor = 0;
        boolean invalido = true;
        while (invalido) {
            try{
                if (jogada.length() != 3) {
                    System.out.println("\n*** Preciso de exatamente 3 caracteres: ");
                    jogada = scan.nextLine();
                }

                linha = Integer.parseInt(jogada.substring(0,1)) - 1;
                coluna = Integer.parseInt(jogada.substring(1,2)) -1;
                valor = Integer.parseInt(jogada.substring(2));
                if (linha < 0 || linha > 8) {
                    System.out.println("\n*** Número da linha deve ser entre 1 e 9: ");
                    jogada = scan.nextLine();
                } else if (coluna < 0 || coluna > 8) {
                    System.out.println("\n*** Número da coluna deve ser entre 1 e 9: ");
                    jogada = scan.nextLine();
                } else if (valor < 1 || valor > 9) {
                    System.out.println("\n*** Valor deve estar entre 1 e 9: ");
                    jogada = scan.nextLine();
                } else {
                    invalido = false;
                }
            } catch (Exception e) {
                System.out.println("\n*** Digite apenas caracteres numéricos: ");
                jogada = scan.nextLine();
            }
        }
        
        
        for (Linha l: sudoku.getLinhas()) {
            for (Posicao p: l.getPosicoes()) {
                if (p.getLinha().getNumero() == linha && p.getColuna().getNumero() == coluna ) {
                    if (checaGeral(valor, linha, coluna, p.getCubo().getNumero())) {
                        p.setValor(valor);
                    } else {
                        System.out.println("Já tem, tenta de novo: ");
                    }
                }
            }
        }
    }      
        
    public void startPosicoesIniciais() {
        List<String> posicoesIniciais = Arrays.asList("119","125","138", "182","242", "255", "266", "284", "336", "375", "381", "397", "416", "443", "457", "468",
                                        "517", "528", "534", "579", "583", "592", "644", "652", "669", "698", "714", "729", "732", "771", "826", "845", "858", "861",
                                        "921", "977", "986", "993");
        aprontaJogo(posicoesIniciais);
    }

    public void aprontaJogo(List<String> posicoesIniciais) {
        for (String jogada: posicoesIniciais) {
            int linha = Integer.parseInt(jogada.substring(0,1)) - 1;
            int coluna = Integer.parseInt(jogada.substring(1,2)) -1;
            int valor = Integer.parseInt(jogada.substring(2));
            for (Linha l: sudoku.getLinhas()) {
                for (Posicao p: l.getPosicoes()) {
                    if (p.getLinha().getNumero() == linha && p.getColuna().getNumero() == coluna ) {
                        p.setValor(valor);
                    }
                }
            }
        }
    }
}
