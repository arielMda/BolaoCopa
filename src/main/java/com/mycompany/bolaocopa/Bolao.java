/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bolaocopa;

/**
 *
 * @author ariel2006
 */

import java.util.HashMap;
import java.util.ArrayList;

public class Bolao {
    private HashMap<String, Selecao> selecoes;
    private ArrayList<Partida> partidas;
    private ArrayList<UsuarioParticipante> ranking;
        
    public Bolao(){
        this.selecoes = new HashMap();
        this.partidas = new ArrayList();
        this.ranking = new ArrayList();
    }
    
    public Selecao getSelecao(String nomeSelecao) {
        return selecoes.get(nomeSelecao);
    }
    
    public Partida getPartida(int id){
        return partidas.get(id);
    }
    
    public boolean isSelecoesVazio() { return selecoes.isEmpty(); }
    
     public void adicionarSelecao(Selecao selecao){
        selecoes.putIfAbsent(selecao.getNome(), selecao);
    }
    
    public boolean selecaoExiste(String nomeSelecao) { 
        return selecoes.containsKey(nomeSelecao); 
    }
    
    public void mostrarSelecoes(){
        selecoes.forEach((chave, valor) -> {
            System.out.println("Nome: " + chave);
        });
    }
    
    public void adicionarPartidas(Partida novaPartida){
        partidas.add(novaPartida);
    }
    
    public void mostrarPartidas(){
        
        if (partidas.isEmpty()){
            System.out.println("Não há partidas registradas.");
            return;
        }
        
        System.out.println("====================");
        System.out.println("|     PARTIDAS     |");
        System.out.println("====================");
        
        for (int i = 0; i < partidas.size(); i++){
            System.out.printf("%d- ", i + 1);
            partidas.get(i).mostrarPartida();
        }
        System.out.println();
    }
    
    public void adicionarParticipantes(UsuarioParticipante participante){
        ranking.add(participante);
    }
    
    
    public void salvarSelecoes(){
        selecoes.forEach((chave, valor) -> {
            valor.salvarSelecao(valor, chave);
        });
    }
    
    public void carregarSelecoes(){
        selecoes.forEach((chave, valor) -> {
            valor.carregarSelecao(chave);
        });
    }
    
    public void atualizarRanking(){
        ranking.sort((p1, p2) -> Integer.compare(p2.getPontuacao(), p1.getPontuacao()));
    }
    
    public void verRanking(){
        int index = 1;
        for (UsuarioParticipante usuario : ranking){
            System.out.printf("%d - %s Pontos: %d\n", index, usuario.getNome(), usuario.getPontuacao());
            index++;
        }
    }
    
    public boolean isRankingVazio() { return ranking.isEmpty(); }
    
    public UsuarioParticipante getParticipante(String nome, String senha){
        if (this.isRankingVazio())
            return null;
        
        for (int i = 0; i < this.ranking.size(); i++){
            if (this.ranking.get(i).getSenha().equals(senha))
                return this.ranking.get(i);
        }
        return null;
    }
    public void verificarPalpites() {
    if (partidas.isEmpty()) {
        System.out.println("Não há partidas registradas neste bolão para validar.");
        return;
    }
    if (ranking.isEmpty()) {
        System.out.println("Não há participantes cadastrados no bolão.");
        return;
    }

    System.out.println("\n=======================================================");
    System.out.println("|             APURAÇÃO DE PALPITES DO BOLÃO           |");
    System.out.println("=======================================================");

    // 1. Passa por cada participante do sistema
    for (UsuarioParticipante participante : ranking) {
        System.out.println("\n>> PARTICIPANTE: " + participante.getNome().toUpperCase());
        int pontosGanhosNestaRodada = 0;

        // 2. Passa por todas as partidas oficiais do bolão
        for (int i = 0; i < partidas.size(); i++) {
            Partida partidaReal = partidas.get(i);

            // Verifica se o usuário chegou a registrar um palpite para este índice de partida
                if (i >= participante.getNumPalpites()) {
                    System.out.printf("   [Partida %d] %s x %s -> Participante não deixou palpite para este jogo.\n", 
                        (i + 1), partidaReal.getMandante().getNome(), partidaReal.getVisitante().getNome());
                    continue;
                }

                // Busca o palpite correspondente à partida atual
                Partida palpiteDoUsuario = participante.getPalpite(i);

                // 3. Calcula os pontos usando a regra interna que você implementou em Partida.java
                int pontosDaPartida = partidaReal.calcularPontuacao(palpiteDoUsuario);
                pontosGanhosNestaRodada += pontosDaPartida;

                // 4. Mostra o resultado oficial da partida
                System.out.printf("   [Jogo %d] Oficial: %s %d x %d %s (Vencedor: %s)\n", 
                    (i + 1),
                    partidaReal.getMandante().getNome(), partidaReal.getNumGolsMandante(),
                    partidaReal.getNumGolsVisitante(), partidaReal.getVisitante().getNome(),
                    partidaReal.getResultado());
            
            // 5. Mostra o palpite enviado pelo participante
                System.out.printf("             Palpite: %s %d x %d %s (Vencedor apostado: %s) -> Ganhou: %d pts\n", 
                    palpiteDoUsuario.getMandante().getNome(), palpiteDoUsuario.getNumGolsMandante(),
                    palpiteDoUsuario.getNumGolsVisitante(), palpiteDoUsuario.getVisitante().getNome(),
                    palpiteDoUsuario.getResultado() != null ? palpiteDoUsuario.getResultado() : "Não declarou",
                    pontosDaPartida);
            }

            // 6. Atualiza e acumula a pontuação final na ficha do usuário
            participante.adicionarPontos(pontosGanhosNestaRodada);
            System.out.printf(">> Resumo: %s acumulou +%d pontos nesta rodada. Total Geral: %d\n", 
                participante.getNome(), pontosGanhosNestaRodada, participante.getPontuacao());
            System.out.println("-------------------------------------------------------");
        }

    // 7. Reordena a tabela com base nas novas pontuações calculadas
        atualizarRanking();
        System.out.println("\n[SISTEMA] Processamento concluído. Ranking atualizado com sucesso!");
    }
}
