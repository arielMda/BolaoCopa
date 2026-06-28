/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolaocopa;

/**
 *
 * @author ariel2006
 */
public abstract class Usuario {
    protected String nome;
    protected String senha;
    
    public String getNome() { return this.nome; }
    public String getSenha() { return this.senha; }
    public boolean cadastrar(String novoNome, String novaSenha){
        if (novoNome.length() < 3)
            return false;
        
        if (novoNome.length() > 50)
            return false;
  
        if (novaSenha.length() < 6)
            return false;
        
        return true;
    }
}
