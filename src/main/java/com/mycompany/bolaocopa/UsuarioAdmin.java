/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolaocopa;

/**
 *
 * @author ariel2006
 */
import java.util.Scanner;
import java.util.ArrayList;
public class UsuarioAdmin extends Usuario{
    
    private Scanner sc = new Scanner(System.in);
    
    public UsuarioAdmin(){
        this.nome = "admin";
        this.senha = "admin123";
    }
    
    public void cadastrarSelecoes(Bolao bolao, String nomeSelecao, String confederacao, int qtdeTitulos){
        Selecao novaSelecao = new Selecao(nomeSelecao, confederacao, qtdeTitulos);
        if (novaSelecao.getNome() == null){
            System.out.println("Não foi possivel cadastrar seleção por irregularidades.");
            return;
        }
        System.out.println("Seleção adicionada!");
        bolao.adicionarSelecao(novaSelecao);
        
    }
    
    public void cadastrarJogadores(Selecao selecao, String nomeJogador,
            String posicao, int numCamisa, int numCartaoAmarelos, 
            int numCartaoVermelhos, int numGols){
        
        Jogador novoJogador = new Jogador(nomeJogador, numCamisa, posicao);
        
        if (novoJogador.getNome() == null || novoJogador.getPosicao() == null ||
                novoJogador.getNumCamisa() == -1){
            System.out.println("Não foi possivel cadastrar jogador por irregularidades.");
            return;
        }
        
        System.out.println("Jogador adicionado!");
        selecao.adicionarJogador(novoJogador);
    }
    
    public void cadastrarPartida(Bolao bolao, Selecao mandante, Selecao visitante){
        Partida novaPartida = new Partida(mandante, visitante);
        bolao.adicionarPartidas(novaPartida);
    }
    
    public void atualizarPlacar(Bolao bolao, int numPartida, int golsMan, int golsVis, 
            ArrayList<Jogador> jogMan, ArrayList<Jogador> jogVis, int totalJogadoresMan, int totalJogadoresVis){
        bolao.getPartida(numPartida).atualizarPlacar(golsMan, golsVis, jogMan, 
                jogVis, totalJogadoresMan, totalJogadoresVis);
    }
    
    public void cadastrarParticipante(Bolao bolao, UsuarioParticipante user){
        bolao.adicionarParticipantes(user);
    }
    
}
