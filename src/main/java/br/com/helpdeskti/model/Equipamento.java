package br.com.helpdeskti.model;

/** Equipamento da empresa que pode estar associado a um chamado. */
public class Equipamento {
    private int id;
    private String nome;
    private String patrimonio;
    private String tipo;

    public Equipamento(int id, String nome, String patrimonio, String tipo) {
        this.id = id; this.nome = nome; this.patrimonio = patrimonio; this.tipo = tipo;
    }
    public Equipamento(String nome, String patrimonio, String tipo) { this(0, nome, patrimonio, tipo); }
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getPatrimonio() { return patrimonio; }
    public String getTipo() { return tipo; }
    @Override public String toString() { return nome + " - " + patrimonio; }
}
