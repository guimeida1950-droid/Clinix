
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Interface extends JPanel {

    // ========================================
    // COMPONENTES
    // ========================================

    private JPanel barra;

    private JButton minimizar;

    private JButton maximizar;

    private JButton fechar;

    private JLabel titulo;

    // ========================================
    // CONSTRUTOR
    // ========================================

    public Interface(
            JFrame janela,
            Runnable abrirConfiguracoes) {

        // ========================================
        // PAINEL PRINCIPAL
        // ========================================

        setLayout(null);

        setBackground(Color.WHITE);

        // ========================================
        // BARRA SUPERIOR
        // ========================================

        barra = new JPanel();

        barra.setLayout(null);

        barra.setBackground(
                new Color(40, 0, 60));

        add(barra);

        // ========================================
        // BOTÃO MINIMIZAR
        // ========================================

        minimizar = new JButton("—");

        minimizar.setForeground(Color.WHITE);

        minimizar.setBackground(
                new Color(40, 0, 60));

        minimizar.setBorderPainted(false);

        minimizar.setFocusPainted(false);

        minimizar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20));

        minimizar.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        barra.add(minimizar);

        // ========================================
        // BOTÃO MAXIMIZAR
        // ========================================

        maximizar = new JButton("□");

        maximizar.setForeground(Color.WHITE);

        maximizar.setBackground(
                new Color(40, 0, 60));

        maximizar.setBorderPainted(false);

        maximizar.setFocusPainted(false);

        maximizar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18));

        maximizar.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        barra.add(maximizar);

        // ========================================
        // BOTÃO FECHAR
        // ========================================

        fechar = new JButton("X");

        fechar.setForeground(Color.WHITE);

        fechar.setBackground(
                new Color(40, 0, 60));

        fechar.setBorderPainted(false);

        fechar.setFocusPainted(false);

        fechar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18));

        fechar.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        barra.add(fechar);

        // ========================================
        // EFEITO MINIMIZAR
        // ========================================

        minimizar.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        minimizar.setBackground(
                                new Color(70, 20, 90));
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        minimizar.setBackground(
                                new Color(40, 0, 60));
                    }
                });

        // ========================================
        // EFEITO MAXIMIZAR
        // ========================================

        maximizar.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        maximizar.setBackground(
                                new Color(70, 20, 90));
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        maximizar.setBackground(
                                new Color(40, 0, 60));
                    }
                });

        // ========================================
        // EFEITO FECHAR
        // ========================================

        fechar.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        fechar.setBackground(
                                new Color(200, 40, 40));
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        fechar.setBackground(
                                new Color(40, 0, 60));
                    }
                });

        // ========================================
        // TÍTULO
        // ========================================

        titulo = new JLabel(
                "Bem-vindo ao meu sistema!");

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28));

        titulo.setForeground(
                new Color(40, 0, 60));

        add(titulo);

        // ========================================
        // SUBTÍTULO
        // ========================================

        JLabel subtitulo = new JLabel(
                "Esta é a interface do sistema.");

        subtitulo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18));

        subtitulo.setForeground(
                Color.DARK_GRAY);

        subtitulo.setBounds(
                50,
                175,
                500,
                40);

        add(subtitulo);

        // ========================================
        // BOTÃO ENTRAR
        // ========================================

        JButton botaoEntrar = new JButton("ENTRAR");

        botaoEntrar.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18));

        botaoEntrar.setForeground(
                Color.WHITE);

        botaoEntrar.setBackground(
                new Color(80, 40, 150));

        botaoEntrar.setFocusPainted(false);

        botaoEntrar.setBorderPainted(false);

        botaoEntrar.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        botaoEntrar.setBounds(
                50,
                240,
                160,
                45);

        add(botaoEntrar);

        // ========================================
        // EFEITO BOTÃO ENTRAR
        // ========================================

        botaoEntrar.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e) {

                        botaoEntrar.setBackground(
                                new Color(110, 60, 180));
                    }

                    @Override
                    public void mouseExited(
                            MouseEvent e) {

                        botaoEntrar.setBackground(
                                new Color(80, 40, 150));
                    }
                });

        // ========================================
        // BOTÃO CONFIGURAÇÕES
        // ========================================

        ImageIcon icone = new ImageIcon("icones/configuracao.png");

        JButton botaoConfiguracoes = new JButton(
                "Configurações",
                icone);

        botaoConfiguracoes.setBounds(
                5,
                5,
                190,
                50);

        botaoConfiguracoes.setContentAreaFilled(false);

        botaoConfiguracoes.setBorderPainted(false);

        botaoConfiguracoes.setFocusPainted(false);

        botaoConfiguracoes.setForeground(
                Color.WHITE);

        botaoConfiguracoes.setCursor(
                new Cursor(Cursor.HAND_CURSOR));

        barra.add(botaoConfiguracoes);

        // ========================================
        // ABRIR CONFIGURAÇÕES
        // ========================================

        botaoConfiguracoes.addActionListener(
                e -> {

                    abrirConfiguracoes.run();
                });
    }

    // ========================================
    // ORGANIZAR COMPONENTES
    // ========================================

    @Override
    public void doLayout() {

        super.doLayout();

        int largura = getWidth();

        // ========================================
        // BARRA
        // ========================================

        barra.setBounds(
                0,
                0,
                largura,
                60);

        // ========================================
        // MINIMIZAR
        // ========================================

        minimizar.setBounds(
                largura - 180,
                0,
                60,
                60);

        // ========================================
        // MAXIMIZAR
        // ========================================

        maximizar.setBounds(
                largura - 120,
                0,
                60,
                60);

        // ========================================
        // FECHAR
        // ========================================

        fechar.setBounds(
                largura - 60,
                0,
                60,
                60);

        // ========================================
        // TÍTULO
        // ========================================

        titulo.setBounds(
                50,
                110,
                600,
                50);
    }

    // ========================================
    // GET BARRA
    // ========================================

    public JPanel getBarra() {

        return barra;
    }

    // ========================================
    // GET MINIMIZAR
    // ========================================

    public JButton getBotaoMinimizar() {

        return minimizar;
    }

    // ========================================
    // GET MAXIMIZAR
    // ========================================

    public JButton getBotaoMaximizar() {

        return maximizar;
    }

    // ========================================
    // GET FECHAR
    // ========================================

    public JButton getBotaoFechar() {

        return fechar;
    }

    // ========================================
    // ALTERAR TEXTO MAXIMIZAR
    // ========================================

    public void setTextoMaximizar(
            String texto) {

        maximizar.setText(texto);
    }
}
