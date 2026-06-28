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

public class UsuarioParticipante extends Usuario{
    private int pontuacao;
    private ArrayList<Partida> palpites;
    
    public UsuarioParticipante(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
        this.pontuacao = 0;
        this.palpites = new ArrayList();
    }
    
    public int getPontuacao() {
        return this.pontuacao;
    }
   
    
    public Partida getPalpite(int id) { return this.palpites.get(id); }
    
    public int getNumPalpites() { return this.palpites.size(); }
    
    public void adicionarPontos(int pontos) {
        this.pontuacao += pontos;
    }
    
    public void registrarPalpite(Partida palpite){
        this.palpites.add(palpite);
        System.out.println("Palpite registrado! Espere até o placar do jogo sair.");
    }
    
    public void mostrarPalpites(){
        
        System.out.println("====================");
        System.out.println("|  SEUS PALPITES   |");
        System.out.println("====================");
        
        for (Partida palpite : this.palpites) {
            palpite.mostrarPartida();
            if (palpite.getResultado() == null)
                System.out.println("Não declarou um vencedor.");
            else
                System.out.println("Vencedor: " + palpite.getResultado());
            
            if (palpite.getNumGolsMandante() == -1)
                System.out.println("Não informou placar.");
            else
                System.out.printf("%s %d x %s %d\n", palpite.getMandante().getNome(),
                        palpite.getNumGolsMandante(), palpite.getVisitante().getNome(),
                        palpite.getNumGolsVisitante());
            
            palpite.mostrarMarcadores();
            
        }
    }
    
}
