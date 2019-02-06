package mz.com.osoma.velha;

import java.util.ArrayList;

public class Tabuleiro
{
  /*
   * Vetor de conversao para impressao na tela
   */
  static char[] conversao = {'o', ' ', 'x'};
  /*
   * Matriz do tabuleiro
   */
  static int[][] tabuleiro;
  
  static int tam;
  
  String divisor;
  /*
   * Construtor
   *   entrada: tamanho do tabuleiro
   */
  public Tabuleiro (int tam)
  {
    this.tam = tam;
    tabuleiro = new int[tam][tam];
    divisor = gerarDivisor();
  }
  
  /*
   * Metodo invocado para a jogada do Jogador! 
   */
  public void fazerJogada (int l, int c)
  {
    if (tabuleiro[l][c] == 0)
    {
      if(tabuleiro[l][c]==0)
      tabuleiro[l][c] = -1;
    }
    else
      System.out.println ("Posicao ja ocupada, perdeu a vez!");
  }
  
  
  /*
   * Metodo para a impressÃ£o do tabuleiro na tela 
   */
  public void imprimir ()
  {    
    for (int i = 0; i < tam; i++)
    {
      for (int j = 0; j < tam; j++)
      {
        System.out.printf (" %c %c", conversao[tabuleiro[i][j] + 1], j == tam-1 ? ' ' : '|');
      }
      if (i != (tam-1))
        System.out.println(divisor);
    }
    System.out.println("\r\n");
  }
  
  public String gerarDivisor(){
  
      String d = new String("\r\n");
  
      for(int i=0; i<(tam-1);i++){
          d += "---+";
      }
      
      d += "---+";
      
      return d;
  }
  
}