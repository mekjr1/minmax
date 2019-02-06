/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mz.com.osoma.velha;


import java.util.ArrayList;

public class MiniMax
{
  /*
   * Lista dos nos sucessores
   */
  static ArrayList<Sucessor> sucessores = new ArrayList<Sucessor> ();
  /*
   * Jogar com pecas escorregadias?
   */

  int tam, maxProf;
  
  /*
   * Construtor
   */
  public MiniMax (int tam, int maxProf)
  {
    this.tam = tam;
    if(maxProf>0){
        this.maxProf = maxProf;
    }else{
        this.maxProf = Integer.MAX_VALUE;
    }
  }
  
  /*
   * Metodo de decisao do MiniMax
   */
  public int[][] decisao_minimax (int[][] tab)
  {
    /*
     * Limpa os sucessores
     */
    sucessores.clear ();

    /*
     * Recebe a utilidade mÃ¡xima
     */
    int v = valor_max (tab, true, 1);
    
    /*
     * Percorre a lista em busca do primeiro sucessor com utilidade maxima
     */
    for (Sucessor s: sucessores)
      if (s.utilidade == v)
        return s.tabuleiro;
    
    return tab;
  }
  
  public int valor_max (int[][] tab, boolean prim, int prof)
  {
    /*
     * Se o jogo acabou, retorna a utilidade
     */
    if (teste_terminal (tab) || prof++>maxProf)
      return utilidade (tab);
    
    /*
     * Atribui -Infinito
     */
    int v = Integer.MIN_VALUE;
    
    /*
     * Percorre os nos sucessores de MAX
     */
    for (Sucessor s: gerar_sucessores (tab, 1))
    {
      v = Math.max(v, valor_min (s.tabuleiro, prof));
      s.utilidade = v;

      /*
       * Se forem os primeiros sucessores, adiciona na lista de sucessores...
       */
      if (prim)
        sucessores.add(s);
    }
    
    return v;
  }
  
  public int valor_min (int[][] tab, int prof)
  {
    /*
     * Se o jogo acabou, retorna a utilidade
     */
    if (teste_terminal (tab) || prof++>maxProf)
      return utilidade (tab);
    
    /*
     * Atribui +Infinito
     */
    int v = Integer.MAX_VALUE;
    
    /*
     * Percorre os nos sucessores de MIN
     */
    for (Sucessor s: gerar_sucessores (tab, -1))
    {
      v = Math.min(v, valor_max (s.tabuleiro,false, prof));
      s.utilidade = v;
      
         }
    
    return v;
  }

  /*
   * Gera os sucessores de um jogador, a partir do estado atual
   */
  public ArrayList<Sucessor> gerar_sucessores (int[][] tab, int v)
  {
    ArrayList<Sucessor> suc = new ArrayList<Sucessor> ();
    for (int i = 0; i < tam; i++)
    {
      for (int j = 0; j < tam; j++)
      {        
        if (tab[i][j] == 0)
        {
          /*
           * Se estiver jogando com pecas escorregadias...
           */
          tab[i][j] = v;
          suc.add(new Sucessor (tab));
          tab[i][j] = 0;
        }
      }
    }
    
    return suc;
  }
  
  /*
   * Metodo que verifica se ha vizinhos livres, considerando as diagonais...
   */
  
  
  /*
   * Fim de jogo?
   * O jogo so termina se nao houver mais espaco para jogadas...
   */
  public boolean teste_terminal (int[][] tab)
  {
    return (ganhou(tab, 1) || ganhou(tab, -1) || semEspaco (tab));
  }
  
  /*
   * Retorna a utilidade...
   * Aqui a utilidade considerada e a diferenca de pontos entre o computador e
   * o jogador, o computador nao deseja apenas vencer, mas tambem humilhar =P
   */
  public int utilidade (int[][] tab)
  {
    if(ganhou(tab,1)) return 1;
    else if(ganhou(tab,-1)) return -1;
    else return 0;
  }
  
  public boolean ganhou(int[][] tab, int v){
      
      for(int i=0; i<tam; i++)
          if(ganhouLinha(tab, i, v) || ganhouColuna(tab, i, v)) return true;
        
      if(ganhouDiag1(tab, v) || ganhouDiag2(tab, v)) return true;
      
      return false;
          
      
  }
  
  private boolean ganhouLinha(int[][] tab, int l, int v){
      for(int i=0; i<tam;i++){
          if(tab[l][i]!=v) return false;
      }
      return true;
  }
  
  
  private boolean ganhouColuna(int[][] tab, int c, int v){
      for(int i=0; i<tam;i++){
          if(tab[i][c]!=v) return false;
      }
      return true;
  }
  
   private boolean ganhouDiag2(int[][] tab, int v){
  
      for(int i=0; i<tam; i++){
          if(tab[(tam-1)-i][i] !=v) return false;
         
      }
      
       return true;
  }
  
  private boolean ganhouDiag1(int[][] tab, int v){
  
      for(int i=0; i<tam; i++){
          if(tab[i][i] !=v) return false;
         
      }
      
       return true;
  }
  /*
   * Nao tem mais espacos restantes no tabuleiro..
   */
  public boolean semEspaco (int[][] tab)
  {    
    for (int l = 0; l < tam; l++)
      for (int c = 0; c < tam; c++)
        if (tab[l][c] == 0)
          return false;
    
    return true;
  }
}