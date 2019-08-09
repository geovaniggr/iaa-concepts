package br.usp;

import java.util.Random;

public class Cavalo {
	
    public static int[][] criaTabuleiro() {
    int i, j;
    int m[][] = new int[8][8];
    zeraMatriz(m, 8, 8);
    return m;
  }

  public static void imprimeMatriz(int[][] m, int x, int y) {
    int i, j;
    for(i = 0; i < x; i++) {
      for(j = 0; j < y; j++) {
        if(m[i][j] < 10)
          System.out.print("0");
        System.out.print(m[i][j] + " ");
      }
      System.out.println();
    }
  }

  public static int[] sorteiaPosicaoInicial() {
    Random random = new Random();
    int coordenadas[] = new int[2];
    coordenadas[0] = random.nextInt(8);
    coordenadas[1] = random.nextInt(8);
    return coordenadas;
  }

  public static void marcaPosicaoTabuleiro(int[][] m, int x, int y, int jogada){
    m[x][y] = jogada + 1;
  }

  public static boolean verificaLimites(int x, int y) {
    return (x < 8 && x >= 0) && (y < 8 && y >= 0);
  }

  public static boolean verificaNaoVisitado(int x, int y, int[][] m) {
    return m[x][y] == 0;
  }

  public static int[][] inicializaMovimentos() {
    int movimentosCavalo[][] = new int[8][2];

    movimentosCavalo[0][0] = 2; movimentosCavalo[0][1] = 1;
    movimentosCavalo[1][0] = 1; movimentosCavalo[1][1] = 2;
    movimentosCavalo[2][0] = -1; movimentosCavalo[2][1] = 2;
    movimentosCavalo[3][0] = -2; movimentosCavalo[3][1] = 1;
    movimentosCavalo[4][0] = -2; movimentosCavalo[4][1] = -1;
    movimentosCavalo[5][0] = -1; movimentosCavalo[5][1] = -2;
    movimentosCavalo[6][0] = 1; movimentosCavalo[6][1] = -2;
    movimentosCavalo[7][0] = 2; movimentosCavalo[7][1] = -1;

    return movimentosCavalo;
  }

  public static void zeraMatriz(int[][] m, int x, int y) {
      int i, j;
      for(i = 0; i < x; i++){
          for(j = 0; j < y; j++) {
              m[i][j] = 0;
          }
      }
  }

  public static void voltaJogada(int[][] m, int x, int y, int jogada) {
    m[x][y] = 0;
  }

  public static int andaCavalo(int[][] m, int x, int y, int jogada, long tempoInicial){
    int i, result, nextX, nextY;
    int movimentosCavalo[][] = inicializaMovimentos();
    if(jogada == 64) { imprimeResultado(m, tempoInicial, jogada); System.exit(1); }
    for(i = 0; i < 8; i++){
      nextX = x + movimentosCavalo[i][0];
      nextY = y + movimentosCavalo[i][1];
      if(verificaLimites(nextX, nextY) && verificaNaoVisitado(nextX, nextY, m)) {
        marcaPosicaoTabuleiro(m, nextX, nextY, jogada);
        if(andaCavalo(m, nextX, nextY, jogada+1, tempoInicial) == 1) return 1;
        else { voltaJogada(m, nextX, nextY, jogada); };
      }
    }
    return 0;
  }

  public static void imprimeResultado(int[][] m, long tempoInicial, int jogadas) {
    System.out.println("Número de casas visitadas: " + jogadas);
    System.out.println();
    imprimeMatriz(m, 8, 8);
    System.out.println();
    System.out.println("O método executou em " + ((System.currentTimeMillis() - tempoInicial)/1000) + " milisegundos.");
  }

  public static void main(String []args) {
    long tempoInicial = System.currentTimeMillis();
    int[][] m = criaTabuleiro();
    int posicaoInicial[] = sorteiaPosicaoInicial();
    marcaPosicaoTabuleiro(m, posicaoInicial[0], posicaoInicial[1], 0);
    int resultado = andaCavalo(m, posicaoInicial[0], posicaoInicial[1], 1, tempoInicial);
    
    System.out.println("Fim do Código");
  }
}