package br.com.helpdeskti.model;

import java.time.LocalDateTime;

/** Solicitação de suporte técnico aberta por um usuário. */
public class Chamado {
    private int id;
    private String titulo;
    private String descricao;
    private String status;
    private String solucao;
    private int usuarioId;
    private Integer equipamentoId;
    private LocalDateTime dataAbertura;

    public Chamado(int id, String titulo, String descricao, String status, String solucao,
                   int usuarioId, Integer equipamentoId, LocalDateTime dataAbertura) {
        this.id=id; this.titulo=titulo; this.descricao=descricao; this.status=status;
        this.solucao=solucao; this.usuarioId=usuarioId; this.equipamentoId=equipamentoId;
        this.dataAbertura=dataAbertura;
    }
    public Chamado(String titulo, String descricao, int usuarioId, Integer equipamentoId) {
        this(0, titulo, descricao, "Aberto", null, usuarioId, equipamentoId, LocalDateTime.now());
    }
    public int getId(){return id;} public String getTitulo(){return titulo;}
    public String getDescricao(){return descricao;} public String getStatus(){return status;}
    public String getSolucao(){return solucao;} public int getUsuarioId(){return usuarioId;}
    public Integer getEquipamentoId(){return equipamentoId;}
    public LocalDateTime getDataAbertura(){return dataAbertura;}
}
