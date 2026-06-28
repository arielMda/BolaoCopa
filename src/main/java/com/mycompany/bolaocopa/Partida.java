/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolaocopa;

/**
 *
 * @author ariel2006
 */

import java.util.ArrayList;
public class Partida {
    private String resultado;
    private Selecao visitante;
    private Selecao mandante;
    private int numGolsVisitante;
    private int numGolsMandante;
    private ArrayList<Jogador> marcadoresMan;
    private ArrayList<Jogador> marcadoresVis;
    
    
    public Partida(Selecao mandante, Selecao visitante){
        this.mandante = mandante;
        this.visitante = visitante;
        this.resultado = null;
        this.marcadoresMan = new ArrayList();
        this.marcadoresVis = new ArrayList();
    }
    
    
    public Selecao getVisitante(){
        return this.visitante;
    }
    
    public Selecao getMandante(){
        return this.mandante;
    }
    
    public int getNumGolsMandante() { return this.numGolsMandante; }
    
    public int getNumGolsVisitante() { return this.numGolsVisitante; }
    
    public String getResultado() { return this.resultado; }
    
    public void mostrarPartida(){
        System.out.println(this.mandante.getNome() + " x " + this.visitante.getNome());
    }
    
    public void mostrarMarcadores(){
        
        System.out.printf("Da: %s\n", this.mandante.getNome());
        if (!this.marcadoresMan.isEmpty()){
            for (Jogador marcador : marcadoresMan){
                System.out.println(marcador.getNome());
            }
        }
        
       
        System.out.printf("Da: %s\n", this.visitante.getNome());
        if (!this.marcadoresVis.isEmpty()){
            for (Jogador marcador : marcadoresVis){
                System.out.println(marcador.getNome());
            }
        }        
    }
    
    public void atualizarPlacar(int numGolsMandante, int numGolsVisitante,
            ArrayList<Jogador> marcadoresMan, ArrayList<Jogador> marcadoresVis, int numJogadoresMan, int numJogadoresVis){
        this.numGolsMandante = numGolsMandante;
        this.numGolsVisitante = numGolsVisitante;
        
        for (int i = 0; i < numJogadoresMan; i++)
            this.marcadoresMan.add(marcadoresMan.get(i));
        
        for (int i = 0; i < numJogadoresVis; i++)
            this.marcadoresVis.add(marcadoresVis.get(i));
        
        this.resultado = this.selecaoVencedora();
    }
    
    public int calcularPontuacao(Partida palpite){
        int somaPontos = 0;
        
        if (palpite.resultado.equals(this.resultado)){
            Pontuacoes pontos = Pontuacoes.ACERTOVENCEDOR;
            somaPontos += pontos.getPontos();
        }
        
        if (palpite.numGolsMandante == this.numGolsMandante && palpite.numGolsVisitante == this.numGolsVisitante){
            Pontuacoes pontos = Pontuacoes.ACERTOPLACAR;
            somaPontos += pontos.getPontos();
        }
        
        for (int i = 0; i < this.marcadoresMan.size(); i++){
            if (palpite.marcadoresMan.contains(this.marcadoresMan.get(i))){
                int index = palpite.marcadoresMan.indexOf(this.marcadoresMan.get(i));
                Pontuacoes pontos = Pontuacoes.ACERTOJOGADOR;
                somaPontos += pontos.getPontos() * getModificador(palpite.marcadoresMan.get(index).getPosicao());
            }
        }
        
        for (int i = 0; i < this.marcadoresVis.size(); i++){
            if (palpite.marcadoresVis.contains(this.marcadoresVis.get(i))){
                int index = palpite.marcadoresVis.indexOf(this.marcadoresVis.get(i));
                Pontuacoes pontos = Pontuacoes.ACERTOJOGADOR;
                somaPontos += pontos.getPontos() * getModificador(palpite.marcadoresVis.get(index).getPosicao());
            }
        }
        return somaPontos;
    }
    
    private String selecaoVencedora(){
        if (this.numGolsMandante > this.numGolsVisitante)
            return this.mandante.getNome();
        else if (this.numGolsMandante < this.numGolsVisitante)
            return this.visitante.getNome();
        else
            return "Empate";
    }
    
    private float getModificador(String posicao){
        if (posicao.equals("Goleiro")){ return 3.0f; }
        
        if (posicao.equals("Zagueiro")) { return 2.0f; }
        
        if (posicao.equals("Meia")) { return 1.5f; }
        
        if (posicao.equals("Atacante")) { return 1.0f; }
        return 0;
    }
}
