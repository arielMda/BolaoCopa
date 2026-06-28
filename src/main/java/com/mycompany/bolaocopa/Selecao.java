/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolaocopa;

/**
 *
 * @author ariel2006
 */
import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

public class Selecao implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private String confederacao;
    private int titulos;
    private Jogador[] jogadores = new Jogador[11];
    private int numJogadores;
    
    
    public Selecao(String nome, String confederacao, int titulos){
        this.nome = nome;
        this.confederacao = confederacao;
        this.titulos = titulos;
        this.numJogadores = 0;
    }
    
    public String getNome() { return this.nome; }
    
    public int getTitulos() { return this.titulos; }
    
    public int getNumJogadores() {return this.numJogadores; }
    
    public Jogador getJogador(int id){ return jogadores[id]; }
    
    public void adicionarJogador(Jogador novoJogador) {
        
        if (this.numJogadores == 11){
            System.out.println("Não é possível adicionar mais jogadores, a escalação está completa.");
            return;
        }
        
        jogadores[this.numJogadores] = novoJogador;
        this.numJogadores++;
        this.ordenar();
    }
    
    public void mostrarSelecao(){
        
        if (this.numJogadores == 0){
            System.out.println("Não há jogadores cadastrados nessa seleção ainda.");
        }
        
        for (int i = 0; i < this.numJogadores; i++){
            jogadores[i].mostrarInfo();
        }
    }
    
    private void ordenar(){
        boolean trocou;
        
        if (this.numJogadores < 2)
            return;
        
        do{
            trocou = false;
            for (int i = 0; i < numJogadores - 1; i++){
                if (jogadores[i].getNumCamisa() > jogadores[i + 1].getNumCamisa()){
                    Jogador aux = jogadores[i];
                    jogadores[i] = jogadores[i + 1];
                    jogadores[i + 1] = aux;
                    trocou = true;
                }
            }
        }while (trocou);
        
    }
    
    public void salvarSelecao(Selecao selecao, String nomeArquivo) {
        // O Java usa esses "Streams" para escrever o objeto no arquivo
        try (FileOutputStream fileOut = new FileOutputStream(nomeArquivo);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            
            objectOut.writeObject(selecao); // Aqui a mágica acontece!
            System.out.println("Seleção salva com sucesso em: " + nomeArquivo);
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar a seleção: " + e.getMessage());
        }
    }
    
    public Selecao carregarSelecao(String nomeArquivo) {
        try (FileInputStream fileIn = new FileInputStream(nomeArquivo);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            
            // Lemos o objeto e fazemos o "cast" para o tipo Selecao
            return (Selecao) objectIn.readObject();
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar a seleção: " + e.getMessage());
            return null;
        }
    }
}
