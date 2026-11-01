package br.com.helpdeskti;

import br.com.helpdeskti.ui.TelaPrincipal;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/** Ponto de entrada do HelpDesk TI. */
public class Main {
    public static void main(String[] args) {
        // Protótipo desktop: abre a tela principal do sistema.
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
            catch (Exception ignored) { }
            new TelaPrincipal().setVisible(true);
        });
    }
}
