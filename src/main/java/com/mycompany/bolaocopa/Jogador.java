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

public class Jogador implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String nome;
    private int numCamisa;
    private String posicao;
    
    public Jogador(String nome, int numCamisa, String posicao){
        this.nome = nome;
        this.numCamisa = numCamisa;
        this.posicao = posicao;
    }
    
    public String getNome() { return this.nome; }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter e Setter para numCamisa
    public int getNumCamisa() {
        return numCamisa;
    }

    public void setNumCamisa(int numCamisa) {
        this.numCamisa = numCamisa;
    }

    // Getter e Setter para posicao
    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }
    
    public void mostrarInfo(){
        System.out.printf("Nome: %s Camisa: %d\n", this.nome, this.numCamisa);
        System.out.printf("Posição: %s\n", this.posicao);
        
    }
    
}
