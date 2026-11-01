package br.com.helpdeskti.ui;

import br.com.helpdeskti.dao.HelpDeskDAO;
import br.com.helpdeskti.model.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

/** Interface desktop inicial em Swing. Requer o banco criado pelo script database/helpdesk_ti.sql. */
public class TelaPrincipal extends JFrame {
    private final HelpDeskDAO dao = new HelpDeskDAO();
    private final DefaultTableModel modelo = new DefaultTableModel(
        new String[]{"Nº","Título","Status","Solicitante","Equipamento","Abertura"},0) {
        @Override public boolean isCellEditable(int r,int c){return false;}
    };
    private final JTable tabela = new JTable(modelo);

    public TelaPrincipal() {
        setTitle("HelpDesk TI - Gerenciamento de chamados");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(950,580); setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245,247,250));
        JPanel topo=new JPanel(new BorderLayout());
        topo.setBackground(new Color(28,52,84)); topo.setBorder(BorderFactory.createEmptyBorder(18,22,18,22));
        JLabel titulo=new JLabel("HelpDesk TI");
        titulo.setForeground(Color.WHITE); titulo.setFont(new Font("SansSerif",Font.BOLD,25));
        JLabel subtitulo=new JLabel("Central de atendimento e suporte técnico");
        subtitulo.setForeground(new Color(220,230,242));
        JPanel textos=new JPanel(new GridLayout(2,1)); textos.setOpaque(false); textos.add(titulo); textos.add(subtitulo);
        topo.add(textos,BorderLayout.WEST); add(topo,BorderLayout.NORTH);

        JButton usuario=botao("Cadastrar usuário"), equipamento=botao("Cadastrar equipamento"),
                chamado=botao("Abrir chamado"), atualizar=botao("Atualizar status"), recarregar=botao("Atualizar lista");
        JPanel acoes=new JPanel(new FlowLayout(FlowLayout.LEFT,10,12));
        acoes.setBackground(new Color(245,247,250));
        for(JButton b:new JButton[]{usuario,equipamento,chamado,atualizar,recarregar}) acoes.add(b);
        JPanel centro=new JPanel(new BorderLayout(8,8)); centro.setBorder(BorderFactory.createEmptyBorder(15,18,18,18));
        centro.setBackground(new Color(245,247,250));
        JLabel label=new JLabel("Chamados registrados"); label.setFont(new Font("SansSerif",Font.BOLD,18));
        centro.add(label,BorderLayout.NORTH);
        tabela.setRowHeight(27); tabela.setFillsViewportHeight(true);
        centro.add(new JScrollPane(tabela),BorderLayout.CENTER);
        JPanel sul=new JPanel(new BorderLayout()); sul.setBackground(new Color(245,247,250));
        sul.add(acoes,BorderLayout.NORTH);
        JLabel dica=new JLabel("Dica: cadastre primeiro um usuário para depois abrir um chamado.");
        dica.setBorder(BorderFactory.createEmptyBorder(0,12,8,0)); sul.add(dica,BorderLayout.SOUTH);
        centro.add(sul,BorderLayout.SOUTH); add(centro,BorderLayout.CENTER);

        usuario.addActionListener(e->cadastrarUsuario());
        equipamento.addActionListener(e->cadastrarEquipamento());
        chamado.addActionListener(e->abrirChamado());
        atualizar.addActionListener(e->atualizarStatus());
        recarregar.addActionListener(e->carregarChamados());
        carregarChamados();
    }
    private JButton botao(String texto){ JButton b=new JButton(texto); b.setFocusPainted(true); return b; }
    private void erro(Exception ex){ JOptionPane.showMessageDialog(this,ex.getMessage(),"Atenção",JOptionPane.ERROR_MESSAGE); }

    private void cadastrarUsuario(){
        JTextField nome=new JTextField(), email=new JTextField();
        JComboBox<String> tipo=new JComboBox<>(new String[]{"FUNCIONARIO","TECNICO"});
        Object[] campos={"Nome:",nome,"E-mail:",email,"Perfil:",tipo};
        if(JOptionPane.showConfirmDialog(this,campos,"Cadastro de usuário",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
            if(nome.getText().isBlank()||email.getText().isBlank()){JOptionPane.showMessageDialog(this,"Preencha nome e e-mail.");return;}
            try{dao.cadastrarUsuario(new Usuario(nome.getText().trim(),email.getText().trim(),tipo.getSelectedItem().toString()));
                JOptionPane.showMessageDialog(this,"Usuário cadastrado.");}
            catch(SQLException ex){erro(ex);}
        }
    }
    private void cadastrarEquipamento(){
        JTextField nome=new JTextField(), patrimonio=new JTextField();
        JComboBox<String> tipo=new JComboBox<>(new String[]{"Computador","Impressora","Notebook","Rede","Outro"});
        Object[] campos={"Nome:",nome,"Patrimônio:",patrimonio,"Tipo:",tipo};
        if(JOptionPane.showConfirmDialog(this,campos,"Cadastro de equipamento",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
            if(nome.getText().isBlank()||patrimonio.getText().isBlank()){JOptionPane.showMessageDialog(this,"Preencha nome e patrimônio.");return;}
            try{dao.cadastrarEquipamento(new Equipamento(nome.getText().trim(),patrimonio.getText().trim(),tipo.getSelectedItem().toString()));
                JOptionPane.showMessageDialog(this,"Equipamento cadastrado.");}
            catch(SQLException ex){erro(ex);}
        }
    }
    private void abrirChamado(){
        try{
            List<String[]> usuarios=dao.listarUsuarios(), equipamentos=dao.listarEquipamentos();
            if(usuarios.isEmpty()){JOptionPane.showMessageDialog(this,"Cadastre um usuário antes de abrir chamado.");return;}
            JComboBox<String> solicitante=new JComboBox<>();
            for(String[] u:usuarios) solicitante.addItem(u[0]+" - "+u[1]+" ("+u[3]+")");
            JComboBox<String> equipamento=new JComboBox<>(); equipamento.addItem("0 - Não informado");
            for(String[] eq:equipamentos)equipamento.addItem(eq[0]+" - "+eq[1]+" ("+eq[2]+")");
            JTextField titulo=new JTextField(); JTextArea descricao=new JTextArea(4,22); descricao.setLineWrap(true); descricao.setWrapStyleWord(true);
            Object[] campos={"Solicitante:",solicitante,"Equipamento:",equipamento,"Título:",titulo,"Descrição:",new JScrollPane(descricao)};
            if(JOptionPane.showConfirmDialog(this,campos,"Abertura de chamado",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
                if(titulo.getText().isBlank()||descricao.getText().isBlank()){JOptionPane.showMessageDialog(this,"Preencha título e descrição.");return;}
                int uid=Integer.parseInt(solicitante.getSelectedItem().toString().split(" - ")[0]);
                int eid=Integer.parseInt(equipamento.getSelectedItem().toString().split(" - ")[0]);
                dao.abrirChamado(new Chamado(titulo.getText().trim(),descricao.getText().trim(),uid,eid==0?null:eid));
                JOptionPane.showMessageDialog(this,"Chamado aberto com sucesso."); carregarChamados();
            }
        }catch(SQLException ex){erro(ex);}
    }
    private void atualizarStatus(){
        int linha=tabela.getSelectedRow();
        if(linha<0){JOptionPane.showMessageDialog(this,"Selecione um chamado na tabela.");return;}
        int id=Integer.parseInt(modelo.getValueAt(linha,0).toString());
        JComboBox<String> status=new JComboBox<>(new String[]{"Aberto","Em andamento","Concluído"});
        JTextField solucao=new JTextField();
        Object[] campos={"Novo status:",status,"Solução (obrigatória ao concluir):",solucao};
        if(JOptionPane.showConfirmDialog(this,campos,"Atualizar chamado",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
            if(status.getSelectedItem().equals("Concluído")&&solucao.getText().isBlank()){
                JOptionPane.showMessageDialog(this,"Informe a solução para concluir o chamado.");return;
            }
            try{dao.atualizarStatus(id,status.getSelectedItem().toString(),solucao.getText().trim());carregarChamados();}
            catch(SQLException ex){erro(ex);}
        }
    }
    private void carregarChamados(){
        try{modelo.setRowCount(0);for(String[] linha:dao.listarChamados())modelo.addRow(linha);}
        catch(SQLException ex){modelo.setRowCount(0);JOptionPane.showMessageDialog(this,
            "Não foi possível carregar os chamados. Confira se o MySQL está ativo e se o banco foi criado.\n\nDetalhe: "+ex.getMessage(),
            "Conexão com banco de dados",JOptionPane.WARNING_MESSAGE);}
    }
}
