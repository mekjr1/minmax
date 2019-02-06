/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.velha;


import java.util.Scanner;

public class Velha
{  
  /*
   * Pecas escorregadias?
   */

  static int TAM = 3, PROF = -1;
  
  public static void main (String[] args)
  {
    Scanner ent = new Scanner (System.in);
    
    Tabuleiro t = new Tabuleiro (TAM);
//    MiniMax mm = new MiniMax (TAM, PROF);
    AlphaBethaPruning mm = new AlphaBethaPruning (TAM, PROF);
    t.imprimir ();
    
    
    do
    {
      int l, c;
      System.out.printf ("Sua jogada:\r\nLinha [0 - %d]: ",(TAM-1));
      l = ent.nextInt ();
      System.out.printf ("Coluna [0 - %d]: ",(TAM-1));
      c = ent.nextInt ();
      t.fazerJogada(l, c);
      t.imprimir ();
      if (!mm.teste_terminal(t.tabuleiro))
      {
        long time = System.currentTimeMillis ();
        t.tabuleiro = mm.decisao_minimax(t.tabuleiro);
        time = System.currentTimeMillis () - time;
        System.out.println ("Jogada do Computador (" + time + " ms):");
        t.imprimir ();
      }
    } while (!mm.teste_terminal(t.tabuleiro));
    
    int u = mm.utilidade(t.tabuleiro);
    if (u < 0)
      System.out.println ("Parabens! Voce ganhou...");
    else if (u == 0)
      System.out.println ("Empatou!");
    else
      System.out.println ("Voce realmente e pior que um computador...");
    System.out.println("Voce marcou " + mm.contaPontos(t.tabuleiro, -1) + " pontos.");
    System.out.println("O computador marcou " + mm.contaPontos(t.tabuleiro, 1) + " pontos.");
   

//      if (mm.ganhou(t.tabuleiro, 1)) {
//          System.out.println("Voce realmente e pior que um computador...");
//      } else if (mm.ganhou(t.tabuleiro, -1)) {
//          System.out.println("Parabens! Voce ganhou...");
//      } else {
//          System.out.println("Empatou!");
//      }
  }
}