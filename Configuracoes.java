
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class Configuracoes extends JPanel {

        // ========================================
        // COMPONENTES
        // ========================================

        private JPanel barraConfiguracoes;

        private JButton minimizarConfiguracoes;

        private JButton maximizarConfiguracoes;

        private JButton fecharConfiguracoes;

        private JLabel tituloConfiguracoes;

        // ========================================
        // CONSTRUTOR
        // ========================================

        public Configuracoes(
                        JFrame janela,
                        Runnable voltarInterface) {

                // ========================================
                // PAINEL PRINCIPAL
                // ========================================

                setLayout(null);

                setBackground(Color.WHITE);

                // ========================================
                // BARRA SUPERIOR
                // ========================================

                barraConfiguracoes = new JPanel();

                barraConfiguracoes.setLayout(null);

                barraConfiguracoes.setBackground(
                                new Color(40, 0, 60));

                add(barraConfiguracoes);

                // ========================================
                // BOTÃO MINIMIZAR
                // ========================================

                minimizarConfiguracoes = new JButton("—");

                minimizarConfiguracoes.setForeground(
                                Color.WHITE);

                minimizarConfiguracoes.setBackground(
                                new Color(40, 0, 60));

                minimizarConfiguracoes.setBorderPainted(false);

                minimizarConfiguracoes.setFocusPainted(false);

                minimizarConfiguracoes.setFont(
                                new Font(
                                                "Arial",
                                                Font.BOLD,
                                                20));

                minimizarConfiguracoes.setCursor(
                                new Cursor(Cursor.HAND_CURSOR));

                barraConfiguracoes.add(
                                minimizarConfiguracoes);

                // ========================================
                // BOTÃO MAXIMIZAR
                // ========================================

                maximizarConfiguracoes = new JButton("□");

                maximizarConfiguracoes.setForeground(
                                Color.WHITE);

                maximizarConfiguracoes.setBackground(
                                new Color(40, 0, 60));

                maximizarConfiguracoes.setBorderPainted(false);

                maximizarConfiguracoes.setFocusPainted(false);

                maximizarConfiguracoes.setFont(
                                new Font(
                                                "Arial",
                                                Font.BOLD,
                                                18));

                maximizarConfiguracoes.setCursor(
                                new Cursor(Cursor.HAND_CURSOR));

                barraConfiguracoes.add(
                                maximizarConfiguracoes);

                // ========================================
                // BOTÃO FECHAR
                // ========================================

                fecharConfiguracoes = new JButton("X");

                fecharConfiguracoes.setForeground(
                                Color.WHITE);

                fecharConfiguracoes.setBackground(
                                new Color(40, 0, 60));

                fecharConfiguracoes.setBorderPainted(false);

                fecharConfiguracoes.setFocusPainted(false);

                fecharConfiguracoes.setFont(
                                new Font(
                                                "Arial",
                                                Font.BOLD,
                                                18));

                fecharConfiguracoes.setCursor(
                                new Cursor(Cursor.HAND_CURSOR));

                barraConfiguracoes.add(
                                fecharConfiguracoes);

                // ========================================
                // EFEITO MINIMIZAR
                // ========================================

                minimizarConfiguracoes.addMouseListener(
                                new MouseAdapter() {

                                        @Override
                                        public void mouseEntered(
                                                        MouseEvent e) {

                                                minimizarConfiguracoes.setBackground(
                                                                new Color(70, 20, 90));
                                        }

                                        @Override
                                        public void mouseExited(
                                                        MouseEvent e) {

                                                minimizarConfiguracoes.setBackground(
                                                                new Color(40, 0, 60));
                                        }
                                });

                // ========================================
                // EFEITO MAXIMIZAR
                // ========================================

                maximizarConfiguracoes.addMouseListener(
                                new MouseAdapter() {

                                        @Override
                                        public void mouseEntered(
                                                        MouseEvent e) {

                                                maximizarConfiguracoes.setBackground(
                                                                new Color(70, 20, 90));
                                        }

                                        @Override
                                        public void mouseExited(
                                                        MouseEvent e) {

                                                maximizarConfiguracoes.setBackground(
                                                                new Color(40, 0, 60));
                                        }
                                });

                // ========================================
                // EFEITO FECHAR
                // ========================================

                fecharConfiguracoes.addMouseListener(
                                new MouseAdapter() {

                                        @Override
                                        public void mouseEntered(
                                                        MouseEvent e) {

                                                fecharConfiguracoes.setBackground(
                                                                new Color(200, 40, 40));
                                        }

                                        @Override
                                        public void mouseExited(
                                                        MouseEvent e) {

                                                fecharConfiguracoes.setBackground(
                                                                new Color(40, 0, 60));
                                        }
                                });
                JPanel painelConteudo = new JPanel();

                painelConteudo.setLayout(null);
                painelConteudo.setBackground(Color.WHITE);

                JScrollPane rolagem = new JScrollPane(painelConteudo);

                rolagem.setBounds(
                                0,
                                60,
                                getWidth(),
                                getHeight() - 60);

                rolagem.setHorizontalScrollBarPolicy(
                                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

                rolagem.setVerticalScrollBarPolicy(
                                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

                add(rolagem);
                JLabel nome = new JLabel("Nome:");

                nome.setBounds(
                                50,
                                50,
                                200,
                                30);

                painelConteudo.add(nome);
                // ========================================
                // TÍTULO
                // ========================================

                tituloConfiguracoes = new JLabel("Configurações");

                tituloConfiguracoes.setFont(
                                new Font(
                                                "Arial",
                                                Font.BOLD,
                                                28));

                tituloConfiguracoes.setForeground(
                                new Color(40, 0, 60));

                add(tituloConfiguracoes);

                // ========================================
                // SUBTÍTULO
                // ========================================

                JLabel subtituloConfiguracoes = new JLabel(
                                "Esta é a tela de configurações do sistema.");

                subtituloConfiguracoes.setFont(
                                new Font(
                                                "Arial",
                                                Font.PLAIN,
                                                18));

                subtituloConfiguracoes.setForeground(
                                Color.DARK_GRAY);

                subtituloConfiguracoes.setBounds(
                                50,
                                175,
                                600,
                                40);

                add(subtituloConfiguracoes);

                // ========================================
                // BOTÃO SALVAR
                // ========================================

                JButton botaoSalvarConfiguracoes = new JButton("SALVAR");

                botaoSalvarConfiguracoes.setFont(
                                new Font(
                                                "Arial",
                                                Font.BOLD,
                                                18));

                botaoSalvarConfiguracoes.setForeground(
                                Color.WHITE);

                botaoSalvarConfiguracoes.setBackground(
                                new Color(80, 40, 150));

                botaoSalvarConfiguracoes.setFocusPainted(false);

                botaoSalvarConfiguracoes.setBorderPainted(false);

                botaoSalvarConfiguracoes.setCursor(
                                new Cursor(Cursor.HAND_CURSOR));

                botaoSalvarConfiguracoes.setBounds(
                                50,
                                240,
                                160,
                                45);

                add(botaoSalvarConfiguracoes);

                // ========================================
                // EFEITO BOTÃO SALVAR
                // ========================================

                botaoSalvarConfiguracoes.addMouseListener(
                                new MouseAdapter() {

                                        @Override
                                        public void mouseEntered(
                                                        MouseEvent e) {

                                                botaoSalvarConfiguracoes.setBackground(
                                                                new Color(110, 60, 180));
                                        }

                                        @Override
                                        public void mouseExited(
                                                        MouseEvent e) {

                                                botaoSalvarConfiguracoes.setBackground(
                                                                new Color(80, 40, 150));
                                        }
                                });

                // ========================================
                // BOTÃO VOLTAR
                // ========================================

                ImageIcon iconeConfiguracoes = new ImageIcon(
                                "icones/casa.png");

                JButton botaoVoltarConfiguracoes = new JButton(
                                "Ínicio",
                                iconeConfiguracoes);

                botaoVoltarConfiguracoes.setBounds(
                                5,
                                5,
                                150,
                                50);

                botaoVoltarConfiguracoes.setContentAreaFilled(
                                false);

                botaoVoltarConfiguracoes.setBorderPainted(
                                false);

                botaoVoltarConfiguracoes.setFocusPainted(
                                false);

                botaoVoltarConfiguracoes.setForeground(
                                Color.WHITE);

                botaoVoltarConfiguracoes.setCursor(
                                new Cursor(Cursor.HAND_CURSOR));

                barraConfiguracoes.add(
                                botaoVoltarConfiguracoes);

                // ========================================
                // VOLTAR PARA INTERFACE
                // ========================================

                botaoVoltarConfiguracoes.addActionListener(
                                e -> {

                                        voltarInterface.run();
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

                barraConfiguracoes.setBounds(
                                0,
                                0,
                                largura,
                                60);

                // ========================================
                // MINIMIZAR
                // ========================================

                minimizarConfiguracoes.setBounds(
                                largura - 180,
                                0,
                                60,
                                60);

                // ========================================
                // MAXIMIZAR
                // ========================================

                maximizarConfiguracoes.setBounds(
                                largura - 120,
                                0,
                                60,
                                60);

                // ========================================
                // FECHAR
                // ========================================

                fecharConfiguracoes.setBounds(
                                largura - 60,
                                0,
                                60,
                                60);

                // ========================================
                // TÍTULO
                // ========================================

                tituloConfiguracoes.setBounds(
                                50,
                                110,
                                600,
                                50);
        }

        // ========================================
        // GET BARRA
        // ========================================

        public JPanel getBarraConfiguracoes() {

                return barraConfiguracoes;
        }

        // ========================================
        // GET MINIMIZAR
        // ========================================

        public JButton getBotaoMinimizarConfiguracoes() {

                return minimizarConfiguracoes;
        }

        // ========================================
        // GET MAXIMIZAR
        // ========================================

        public JButton getBotaoMaximizarConfiguracoes() {

                return maximizarConfiguracoes;
        }

        // ========================================
        // GET FECHAR
        // ========================================

        public JButton getBotaoFecharConfiguracoes() {

                return fecharConfiguracoes;
        }

        // ========================================
        // ALTERAR TEXTO MAXIMIZAR
        // ========================================

        public void setTextoMaximizarConfiguracoes(
                        String texto) {

                maximizarConfiguracoes.setText(
                                texto);
        }
}
