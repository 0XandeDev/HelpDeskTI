package br.com.helpdeskti.model;

/** Representa funcionário ou técnico que utiliza o HelpDesk TI. */
public class Usuario {
    private int id;
    private String nome;
    private String email;
    private String tipo; // FUNCIONARIO ou TECNICO

    public Usuario(int id, String nome, String email, String tipo) {
        this.id = id; this.nome = nome; this.email = email; this.tipo = tipo;
    }
    public Usuario(String nome, String email, String tipo) { this(0, nome, email, tipo); }
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getTipo() { return tipo; }
    @Override public String toString() { return nome + " (" + tipo + ")"; }
}
