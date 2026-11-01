package br.com.helpdeskti.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/** Configuração central da conexão. Ajuste as variáveis de ambiente ou os padrões abaixo. */
public final class ConexaoMySQL {
    private static final String URL = System.getenv().getOrDefault(
        "HELPDESK_DB_URL",
        "jdbc:mysql://localhost:3306/helpdesk_ti?serverTimezone=America/Sao_Paulo&useSSL=false&allowPublicKeyRetrieval=true");
    private static final String USUARIO = System.getenv().getOrDefault("HELPDESK_DB_USER", "root");
    private static final String SENHA = System.getenv().getOrDefault("HELPDESK_DB_PASSWORD", "");

    private ConexaoMySQL() {}
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
