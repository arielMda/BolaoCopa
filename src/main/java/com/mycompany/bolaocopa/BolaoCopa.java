/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bolaocopa;

/**
 *
 * @author ariel2006
 */
import java.util.Scanner;
public class BolaoCopa {
    static final String nomeAdmin = "admin";
    static final String senhaAdmin = "admin123";
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        // 1 - Admin 2 - Participante
        int modo = 1;
        boolean is_login = false;
        do {
            if (!is_login)
                modo = login();
            if (modo != 0)
                is_login = true;
            
        } while (modo != 0);
    }
    
    public static int login(){
        String nomeUsuario, senhaUsuario;
        System.out.print("Digite o nome de usuário (0 para sair): ");
        nomeUsuario = sc.nextLine();
        System.out.print("Digite sua senha: ");
        senhaUsuario = sc.nextLine();
        if (nomeUsuario.equals(nomeAdmin) && senhaUsuario.equals(senhaAdmin))
            return 1;
        
        if (nomeUsuario.equals(""))
            return 0;
        return 2;        
    }
}
