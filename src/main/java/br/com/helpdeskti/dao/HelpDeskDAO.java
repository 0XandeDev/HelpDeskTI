package br.com.helpdeskti.dao;

import br.com.helpdeskti.database.ConexaoMySQL;
import br.com.helpdeskti.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/** Operações SQL do sistema. Utiliza PreparedStatement para os dados informados pelo usuário. */
public class HelpDeskDAO {
    public void cadastrarUsuario(Usuario u) throws SQLException {
        String sql="INSERT INTO usuarios(nome,email,tipo) VALUES(?,?,?)";
        try(Connection c=ConexaoMySQL.conectar(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,u.getNome()); ps.setString(2,u.getEmail()); ps.setString(3,u.getTipo()); ps.executeUpdate();
        }
    }
    public void cadastrarEquipamento(Equipamento e) throws SQLException {
        String sql="INSERT INTO equipamentos(nome,patrimonio,tipo) VALUES(?,?,?)";
        try(Connection c=ConexaoMySQL.conectar(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,e.getNome()); ps.setString(2,e.getPatrimonio()); ps.setString(3,e.getTipo()); ps.executeUpdate();
        }
    }
    public void abrirChamado(Chamado ch) throws SQLException {
        String sql="INSERT INTO chamados(titulo,descricao,status,solucao,usuario_id,equipamento_id) VALUES(?,?,'Aberto',NULL,?,?)";
        try(Connection c=ConexaoMySQL.conectar(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,ch.getTitulo()); ps.setString(2,ch.getDescricao()); ps.setInt(3,ch.getUsuarioId());
            if(ch.getEquipamentoId()==null) ps.setNull(4,Types.INTEGER); else ps.setInt(4,ch.getEquipamentoId());
            ps.executeUpdate();
        }
    }
    public List<String[]> listarUsuarios() throws SQLException {
        List<String[]> lista=new ArrayList<>();
        try(Connection c=ConexaoMySQL.conectar(); Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("SELECT id,nome,email,tipo FROM usuarios ORDER BY nome")){
            while(rs.next()) lista.add(new String[]{""+rs.getInt("id"),rs.getString("nome"),rs.getString("email"),rs.getString("tipo")});
        } return lista;
    }
    public List<String[]> listarEquipamentos() throws SQLException {
        List<String[]> lista=new ArrayList<>();
        try(Connection c=ConexaoMySQL.conectar(); Statement st=c.createStatement();
            ResultSet rs=st.executeQuery("SELECT id,nome,patrimonio,tipo FROM equipamentos ORDER BY nome")){
            while(rs.next()) lista.add(new String[]{""+rs.getInt("id"),rs.getString("nome"),rs.getString("patrimonio"),rs.getString("tipo")});
        } return lista;
    }
    public List<String[]> listarChamados() throws SQLException {
        List<String[]> lista=new ArrayList<>();
        String sql="SELECT c.id,c.titulo,c.status,u.nome,COALESCE(e.nome,'Não informado'),c.data_abertura " +
                   "FROM chamados c JOIN usuarios u ON u.id=c.usuario_id LEFT JOIN equipamentos e ON e.id=c.equipamento_id ORDER BY c.id DESC";
        try(Connection c=ConexaoMySQL.conectar(); Statement st=c.createStatement(); ResultSet rs=st.executeQuery(sql)){
            while(rs.next()) lista.add(new String[]{""+rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)});
        } return lista;
    }
    public void atualizarStatus(int chamadoId, String status, String solucao) throws SQLException {
        String sql="UPDATE chamados SET status=?, solucao=? WHERE id=?";
        try(Connection c=ConexaoMySQL.conectar(); PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,status); ps.setString(2,solucao); ps.setInt(3,chamadoId);
            if(ps.executeUpdate()==0) throw new SQLException("Chamado não encontrado.");
        }
    }
}
