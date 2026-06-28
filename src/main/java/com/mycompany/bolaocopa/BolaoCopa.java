/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bolaocopa;

/**
 *
 * @author ariel2006
 */


import java.util.Scanner;
import java.util.ArrayList;

public class BolaoCopa {
    static final String nomeAdmin = "admin";
    static final String senhaAdmin = "admin123";
    static Scanner sc = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        int opcao;
        boolean is_login = false;
        Bolao bolao = new Bolao();
        Usuario user = null;
        
        if (!bolao.isSelecoesVazio())
            bolao.carregarSelecoes();
        
        do {
            if (!is_login) {
                user = login(bolao);
            }
            
            if (user != null) {
                is_login = true;
                
                // FLUXO DO ADMINISTRADOR
                if (user instanceof UsuarioAdmin) {
                    UsuarioAdmin admin = (UsuarioAdmin) user;
                    do {
                        menuPrincipalAdmin();
                        System.out.print("Digite sua escolha: ");
                        opcao = sc.nextInt();
                        sc.nextLine(); // Limpa o buffer
                        switch (opcao) {
                            case 1 -> registrarSelecao(admin, bolao);
                            case 2 -> registrarJogador(admin, bolao);
                            case 3 -> registrarPartida(admin, bolao);
                            case 4 -> atualizarPartida(admin, bolao);
                            case 5 -> cadastrarParticipante(admin, bolao);
                            case 6 -> bolao.verRanking();
                            case 7 -> verificarPalpites(bolao);
                            case 0 -> {
                                is_login = false;
                                user = null;
                                System.out.println("Logout efetuado com sucesso.");
                            }
                            default -> System.out.println("Opção inválida!");
                        }
                    } while (opcao != 0);
                    
                } 
                // FLUXO DO PARTICIPANTE
                else if (user instanceof UsuarioParticipante) {
                    UsuarioParticipante participante = (UsuarioParticipante) user;
                    do {
                        menuPrincipalParti();
                        System.out.print("Digite sua escolha: ");
                        opcao = sc.nextInt();
                        sc.nextLine(); // Limpa o buffer
                        
                        switch (opcao) {
                            case 1 -> registrarPalpite(bolao, participante);
                            case 2 -> bolao.verRanking();
                            case 0 -> {
                                is_login = false;
                                user = null;
                                System.out.println("Logout efetuado com sucesso.");
                            }
                            default -> System.out.println("Opção inválida!");
                        }
                    } while (opcao != 0);
                }
            } else {
                // Se o login retornar null, significa que o usuário escolheu sair do programa (digitou 0)
                break;
            }
        } while (user == null); // Permite voltar para a tela de login caso faça logout
    }
    
    public static Usuario login(Bolao bolao) {
        String nomeUsuario, senhaUsuario;
        UsuarioAdmin admin = new UsuarioAdmin();
        
        System.out.println("==================");
        System.out.println("|      LOGIN     |");
        System.out.println("==================");
        System.out.print("Digite o nome de usuário (0 para fechar o programa): ");
        nomeUsuario = sc.nextLine();
        
        if (nomeUsuario.trim().equals("") || nomeUsuario.equals("0"))
            return null;
        
        System.out.print("Digite sua senha: ");
        senhaUsuario = sc.nextLine();
        
        if (nomeUsuario.equals(nomeAdmin) && senhaUsuario.equals(senhaAdmin)) {
            System.out.println("\n[SISTEMA] Bem-vindo, Administrador!");
            return admin;
        }
        
        if (bolao.isRankingVazio()) {
            System.out.println("Nenhum participante cadastrado ainda. Entre como admin para cadastrar.");
            return null;
        }
        
        UsuarioParticipante part = bolao.getParticipante(nomeUsuario, senhaUsuario);
        if (part != null) {
            System.out.println("\n[SISTEMA] Bem-vindo, " + part.getNome() + "!");
            return part;
        } else {
            System.out.println("Usuário ou senha incorretos.");
            return null;
        }
    }
    
    public static void menuPrincipalAdmin() {
        System.out.println("\n=============================");
        System.out.println("|  MENU PRINCIPAL - ADMIN   |");
        System.out.println("=============================");
        System.out.println("| 1- Registrar Seleção      |");
        System.out.println("| 2- Registrar Jogador      |");
        System.out.println("| 3- Registrar Partida      |");
        System.out.println("| 4- Atualizar Placar       |");
        System.out.println("| 5- Adicionar Participante |");
        System.out.println("| 6- Ver Ranking            |");
        System.out.println("| 7- Verificar Palpites     |");
        System.out.println("| 0- Fazer Logout           |");
        System.out.println("=============================");
    }
    
    public static void menuPrincipalParti() {
        System.out.println("\n=============================");
        System.out.println("| MENU PRINCIPAL - JOGADOR  |");
        System.out.println("=============================");
        System.out.println("| 1- Registrar Palpite      |");
        System.out.println("| 2- Ver Ranking            |");
        System.out.println("| 0- Fazer Logout           |");
        System.out.println("=============================");
    }
    
    public static void registrarSelecao(UsuarioAdmin user, Bolao bolao) {
        String nomeSelecao, conf;
        int qtdeTitulos;
        System.out.print("Nome da seleção: ");
        nomeSelecao = sc.nextLine();
        System.out.print("Confederação em que a seleção está registrada: ");
        conf = sc.nextLine();
        System.out.print("Quantidade de títulos de Copa do Mundo: ");
        qtdeTitulos = sc.nextInt();
        sc.nextLine(); // Limpa o buffer
        
        user.cadastrarSelecoes(bolao, nomeSelecao, conf, qtdeTitulos);
        bolao.salvarSelecoes();
    }
    
    public static void registrarJogador(UsuarioAdmin user, Bolao bolao) {
        String nomeSelecao;
        System.out.print("Digite a seleção que o jogador pertence: ");
        nomeSelecao = sc.nextLine();
        if (bolao.selecaoExiste(nomeSelecao)) {
            String nomeJogador, posicao;
            int numCamisa;
            System.out.print("Digite o nome do jogador: ");
            nomeJogador = sc.nextLine();
            System.out.print("Digite a posição em que joga (Atacante/Meia/Zagueiro/Goleiro): ");
            posicao = sc.nextLine();
            System.out.print("Digite o número da camisa: ");
            numCamisa = sc.nextInt();
            sc.nextLine(); // Limpa o buffer
            
            user.cadastrarJogadores(bolao.getSelecao(nomeSelecao), nomeJogador, posicao, numCamisa, 0, 0, 0);
        } else {
            System.out.println("Seleção ainda não está registrada, registre-a primeiro.");
        }
    }
    
    public static void registrarPartida(UsuarioAdmin user, Bolao bolao) {
        String mandante, visitante;
        Selecao selMandante, selVisitante;
        System.out.print("Qual é o mandante? ");
        mandante = sc.nextLine();
        System.out.print("Qual é o visitante? ");
        visitante = sc.nextLine();
        
        if (bolao.selecaoExiste(mandante) && bolao.selecaoExiste(visitante)) {
            selMandante = bolao.getSelecao(mandante);
            selVisitante = bolao.getSelecao(visitante);
            user.cadastrarPartida(bolao, selMandante, selVisitante);
            System.out.println("Partida criada com sucesso!");
        } else {
            System.out.println("Uma das seleções informadas não está cadastrada.");
        }
    }
   
    public static void atualizarPartida(UsuarioAdmin user, Bolao bolao) {
        int numPartida, golsMan, golsVis, numJogador, totalJogadoresMan = 0, totalJogadoresVis = 0;
        ArrayList<Jogador> jogMan = new ArrayList<>();
        ArrayList<Jogador> jogVis = new ArrayList<>();
        System.out.println("Escolha a partida (Digite o número correspondente): ");
        bolao.mostrarPartidas();
        Jogador jogador;
                
        numPartida = sc.nextInt();
        sc.nextLine(); // Limpa o buffer
        numPartida--;
        
        System.out.print("Digite os gols do mandante: ");
        golsMan = sc.nextInt();
        System.out.print("Digite os gols do visitante: ");
        golsVis = sc.nextInt();
        sc.nextLine(); // Limpa o buffer
        
        for (int i = 0; i < golsMan; i++) {
            System.out.println("Digite o índice do jogador que fez o " + (i + 1) + "º gol do " + 
                bolao.getPartida(numPartida).getMandante().getNome() + ":");
            bolao.getPartida(numPartida).getMandante().mostrarSelecao();
            
            numJogador = sc.nextInt();
            sc.nextLine(); // Limpa o buffer
            
            jogador = bolao.getPartida(numPartida).getMandante().getJogador(numJogador);
            if (i > 0 && jogMan.get(i - 1).equals(jogador))
                continue;
            
            jogMan.add(jogador);
            totalJogadoresMan++;
        }
        
        for (int i = 0; i < golsVis; i++) {
            System.out.println("Digite o índice do jogador que fez o " + (i + 1) + "º gol do " + 
                bolao.getPartida(numPartida).getVisitante().getNome() + ":");
            bolao.getPartida(numPartida).getVisitante().mostrarSelecao();
            
            numJogador = sc.nextInt();
            sc.nextLine(); // Limpa o buffer
            
            jogador = bolao.getPartida(numPartida).getVisitante().getJogador(numJogador);
            if (i > 0 && jogVis.get(i - 1).equals(jogador))
                continue;
            
            jogVis.add(jogador);
            totalJogadoresVis++;
        }
        
        user.atualizarPlacar(bolao, numPartida, golsMan, golsVis, jogMan, jogVis, totalJogadoresMan, totalJogadoresVis);
        System.out.println("Placar oficial da partida atualizado com sucesso!");
    }
    
    public static void verificarPalpites(Bolao bolao) {
        bolao.verificarPalpites();
    }
    
    public static void registrarPalpite(Bolao bolao, UsuarioParticipante user) {
        int numPartida, numGolsMan, numGolsVis, numJogador, totalJogadoresMan = 0, totalJogadoresVis = 0;
        Partida palpite;
        ArrayList<Jogador> jogMan = new ArrayList<>();
        ArrayList<Jogador> jogVis = new ArrayList<>();
        Jogador jogador;
        
        System.out.println("Escolha a partida: ");
        bolao.mostrarPartidas();
        numPartida = sc.nextInt();
        sc.nextLine(); // Limpa o buffer
        numPartida--;
        
        System.out.print("Digite os gols do mandante: ");
        numGolsMan = sc.nextInt();
        System.out.print("Digite os gols do visitante: ");
        numGolsVis = sc.nextInt();
        sc.nextLine(); // Limpa o buffer
        
        for (int i = 0; i < numGolsMan; i++) {
            System.out.println("Quem fará o " + (i + 1) + "º gol do " + 
                bolao.getPartida(numPartida).getMandante().getNome() + "?");
            bolao.getPartida(numPartida).getMandante().mostrarSelecao();
            
            numJogador = sc.nextInt();
            sc.nextLine(); // Limpa o buffer
            
            jogador = bolao.getPartida(numPartida).getMandante().getJogador(numJogador);
            if (i > 0 && jogMan.get(i - 1).equals(jogador))
                continue;
            
            jogMan.add(jogador);
            totalJogadoresMan++;
        }
        
        for (int i = 0; i < numGolsVis; i++) {
            System.out.println("Quem fará o " + (i + 1) + "º gol do " + 
                bolao.getPartida(numPartida).getVisitante().getNome() + "?");
            bolao.getPartida(numPartida).getVisitante().mostrarSelecao();
            
            numJogador = sc.nextInt();
            sc.nextLine(); // Limpa o buffer
            
            jogador = bolao.getPartida(numPartida).getVisitante().getJogador(numJogador);
            if (i > 0 && jogVis.get(i - 1).equals(jogador))
                continue;
            
            jogVis.add(jogador);
            totalJogadoresVis++;
        }
        
        palpite = new Partida(bolao.getPartida(numPartida).getMandante(), bolao.getPartida(numPartida).getVisitante());
        palpite.atualizarPlacar(numGolsMan, numGolsVis, jogMan, jogVis, totalJogadoresMan, totalJogadoresVis);
        user.registrarPalpite(palpite);
    }
    
    public static void cadastrarParticipante(UsuarioAdmin user, Bolao bolao) {
        String nomePrtcpnt, senhaPrtcpnt;
        UsuarioParticipante userTmp;
        System.out.print("Digite o nome do participante: ");
        nomePrtcpnt = sc.nextLine();
        System.out.print("Digite a senha do participante: ");
        senhaPrtcpnt = sc.nextLine();
        
        userTmp = new UsuarioParticipante(nomePrtcpnt, senhaPrtcpnt);
        user.cadastrarParticipante(bolao, userTmp);
    }
}