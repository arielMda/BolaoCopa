/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.bolaocopa;

/**
 *
 * @author ariel2006
 */
public enum Pontuacoes {
    ACERTOVENCEDOR(5),
    ACERTOPLACAR(25),
    ACERTOJOGADOR(15);
    
    
    private final int pontos;
    
    private Pontuacoes(int pontos){
        this.pontos = pontos;
    }
    
    public int getPontos(){ return this.pontos; }
    
}
