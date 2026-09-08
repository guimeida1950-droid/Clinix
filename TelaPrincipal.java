import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class TelaPrincipal extends JFrame {

        // =====================================================
        // CORES
        // =====================================================

        private final Color FUNDO = new Color(245, 246, 250);

        private final Color MENU = new Color(35, 31, 45);

        private final Color MENU_HOVER = new Color(55, 49, 68);

        private final Color ROXO = new Color(104, 67, 180);

        private final Color BRANCO = Color.WHITE;

        private final Color TEXTO = new Color(45, 45, 50);

        private final Color CINZA = new Color(120, 120, 125);

        // =====================================================
        // COMPONENTES
        // =====================================================

        private JPanel painelPrincipal;

        private JPanel painelConteudo;

        private JLabel numeroPacientes;

        private JButton botaoDashboard;

        private JButton botaoPacientes;

        private JLabel numeroConsultas;

        private JButton botaoConsultas;

        private JButton botaoProntuarios;

        private JButton botaoConfiguracoes;

        private JButton botaoSair;

        // =====================================================
        // CONSTRUTOR
        // =====================================================

        public TelaPrincipal() {

                configurarJanela();

                criarInterface();

                setVisible(true);
        }

        // =====================================================
        // CONFIGURAR JANELA
        // =====================================================

        private void configurarJanela() {

                setTitle("Clinix");

                setSize(
                                1200,
                                700);

                setMinimumSize(
                                new Dimension(
                                                900,
                                                550));

                setLocationRelativeTo(null);

                setDefaultCloseOperation(
                                JFrame.EXIT_ON_CLOSE);

                setLayout(
                                new BorderLayout());

                getContentPane()
                                .setBackground(FUNDO);
        }

        // =====================================================
        // CRIAR INTERFACE
        // =====================================================

        private void criarInterface() {

                painelPrincipal = new JPanel(
                                new BorderLayout());

                painelPrincipal
                                .setBackground(FUNDO);

                criarMenuLateral();

                criarAreaConteudo();

                add(
                                painelPrincipal,
                                BorderLayout.CENTER);

                setIconImage(
                                Toolkit.getDefaultToolkit().getImage(
                                                "icones/icone1.png"));
        }

        // =====================================================
        // MENU LATERAL e IMAGEM DE BARRA INFERIOR
        // =====================================================

        private void criarMenuLateral() {

                JPanel menu = new JPanel();

                menu.setPreferredSize(
                                new Dimension(
                                                240,
                                                0));

                menu.setBackground(MENU);

                menu.setLayout(
                                new BorderLayout());

                // =================================================
                // LOGO
                // =================================================

                JPanel painelLogo = new JPanel();

                painelLogo.setBackground(MENU);

                painelLogo.setLayout(
                                new BoxLayout(
                                                painelLogo,
                                                BoxLayout.Y_AXIS));

                painelLogo.setBorder(
                                new EmptyBorder(
                                                30,
                                                25,
                                                30,
                                                25));

                JLabel titulo = new JLabel("CLINIX");

                titulo.setForeground(BRANCO);

                titulo.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                28));

                JLabel subtitulo = new JLabel(
                                "Gestão de Clínica");

                subtitulo.setForeground(
                                new Color(
                                                180,
                                                175,
                                                190));

                subtitulo.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                13));

                painelLogo.add(titulo);

                painelLogo.add(
                                Box.createVerticalStrut(5));

                painelLogo.add(subtitulo);

                menu.add(
                                painelLogo,
                                BorderLayout.NORTH);

                // =================================================
                // BOTÕES
                // =================================================

                JPanel painelBotoes = new JPanel();

                painelBotoes.setBackground(MENU);

                painelBotoes.setLayout(
                                new BoxLayout(
                                                painelBotoes,
                                                BoxLayout.Y_AXIS));

                painelBotoes.setBorder(
                                new EmptyBorder(
                                                10,
                                                15,
                                                10,
                                                15));

                botaoDashboard = criarBotaoMenu(
                                "Dashboard");

                botaoPacientes = criarBotaoMenu(
                                "Pacientes");

                botaoConsultas = criarBotaoMenu(
                                "Consultas");

                botaoProntuarios = criarBotaoMenu(
                                "Prontuários");

                botaoConfiguracoes = criarBotaoMenu(
                                "Configurações");

                painelBotoes.add(
                                botaoDashboard);

                painelBotoes.add(
                                Box.createVerticalStrut(8));

                painelBotoes.add(
                                botaoPacientes);

                painelBotoes.add(
                                Box.createVerticalStrut(8));

                painelBotoes.add(
                                botaoConsultas);

                painelBotoes.add(
                                Box.createVerticalStrut(8));

                painelBotoes.add(
                                botaoProntuarios);

                painelBotoes.add(
                                Box.createVerticalStrut(8));

                painelBotoes.add(
                                botaoConfiguracoes);

                menu.add(
                                painelBotoes,
                                BorderLayout.CENTER);

                // =================================================
                // SAIR
                // =================================================

                JPanel painelSair = new JPanel();

                painelSair.setBackground(MENU);

                painelSair.setBorder(
                                new EmptyBorder(
                                                15,
                                                15,
                                                20,
                                                15));

                botaoSair = criarBotaoMenu(
                                "Sair");

                painelSair.add(
                                botaoSair);

                menu.add(
                                painelSair,
                                BorderLayout.SOUTH);

                // =================================================
                // AÇÕES
                // =================================================

                botaoDashboard.addActionListener(
                                e -> mostrarDashboard());

                botaoPacientes.addActionListener(
                                e -> abrirPacientes());
                botaoConsultas.addActionListener(
                                e -> abrirConsultas());
                botaoProntuarios.addActionListener(
                                e -> mostrarMensagem(
                                                "Módulo de prontuários em desenvolvimento."));

                botaoConfiguracoes.addActionListener(
                                e -> mostrarMensagem(
                                                "Módulo de configurações em desenvolvimento."));

                botaoSair.addActionListener(
                                e -> {

                                        int resposta = JOptionPane.showConfirmDialog(
                                                        this,
                                                        "Deseja realmente sair?",
                                                        "Sair",
                                                        JOptionPane.YES_NO_OPTION);

                                        if (resposta == JOptionPane.YES_OPTION) {

                                                System.exit(0);
                                        }
                                });

                painelPrincipal.add(
                                menu,
                                BorderLayout.WEST);
        }

        // =====================================================
        // BOTÃO MENU
        // =====================================================

        private JButton criarBotaoMenu(
                        String texto) {

                JButton botao = new JButton(texto);

                botao.setMaximumSize(
                                new Dimension(
                                                Integer.MAX_VALUE,
                                                48));

                botao.setPreferredSize(
                                new Dimension(
                                                210,
                                                48));

                botao.setAlignmentX(
                                Component.CENTER_ALIGNMENT);

                botao.setHorizontalAlignment(
                                SwingConstants.LEFT);

                botao.setBorder(
                                new EmptyBorder(
                                                0,
                                                20,
                                                0,
                                                10));

                botao.setFocusPainted(false);

                botao.setBorderPainted(false);

                botao.setContentAreaFilled(false);

                botao.setForeground(
                                new Color(
                                                220,
                                                218,
                                                225));

                botao.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                15));

                botao.addMouseListener(
                                new java.awt.event.MouseAdapter() {

                                        @Override
                                        public void mouseEntered(
                                                        java.awt.event.MouseEvent e) {

                                                botao.setBackground(
                                                                MENU_HOVER);

                                                botao.setOpaque(true);
                                        }

                                        @Override
                                        public void mouseExited(
                                                        java.awt.event.MouseEvent e) {

                                                botao.setOpaque(false);
                                        }
                                });

                return botao;
        }

        // =====================================================
        // ÁREA DE CONTEÚDO
        // =====================================================

        private void criarAreaConteudo() {

                painelConteudo = new JPanel(
                                new BorderLayout());

                painelConteudo
                                .setBackground(FUNDO);

                painelPrincipal.add(
                                painelConteudo,
                                BorderLayout.CENTER);

                mostrarDashboard();
        }

        // =====================================================
        // DASHBOARD
        // =====================================================

        private void mostrarDashboard() {

                painelConteudo.removeAll();

                JPanel dashboard = new JPanel();

                dashboard.setBackground(FUNDO);

                dashboard.setLayout(
                                new BoxLayout(
                                                dashboard,
                                                BoxLayout.Y_AXIS));

                dashboard.setBorder(
                                new EmptyBorder(
                                                35,
                                                40,
                                                35,
                                                40));

                // =================================================
                // TÍTULO
                // =================================================

                JLabel titulo = new JLabel(
                                "Dashboard");

                titulo.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                30));

                titulo.setForeground(TEXTO);

                titulo.setAlignmentX(
                                Component.LEFT_ALIGNMENT);

                dashboard.add(titulo);

                dashboard.add(
                                Box.createVerticalStrut(5));

                JLabel descricao = new JLabel(
                                "Bem-vindo ao sistema de gestão da sua clínica.");

                descricao.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                15));

                descricao.setForeground(CINZA);

                descricao.setAlignmentX(
                                Component.LEFT_ALIGNMENT);

                dashboard.add(descricao);

                dashboard.add(
                                Box.createVerticalStrut(30));

                // =================================================
                // CARDS
                // =================================================

                JPanel cards = new JPanel(
                                new GridLayout(
                                                1,
                                                3,
                                                20,
                                                0));

                cards.setBackground(FUNDO);

                cards.setMaximumSize(
                                new Dimension(
                                                Integer.MAX_VALUE,
                                                150));

                cards.setAlignmentX(
                                Component.LEFT_ALIGNMENT);

                // =================================================
                // CARD PACIENTES
                // =================================================

                JPanel cardPacientes = criarCard(
                                "Pacientes",
                                "0",
                                "Total cadastrado");

                numeroPacientes = encontrarNumeroCard(
                                cardPacientes);

                cards.add(
                                cardPacientes);

                // =================================================
                // OUTROS CARDS
                // =================================================

                JPanel cardConsultas = criarCard(
                                "Consultas",
                                "0",
                                "Consultas hoje");

                numeroConsultas = encontrarNumeroCard(
                                cardConsultas);

                cards.add(
                                cardConsultas);

                cards.add(
                                criarCard(
                                                "Prontuários",
                                                "0",
                                                "Prontuários registrados"));

                dashboard.add(cards);

                dashboard.add(
                                Box.createVerticalStrut(35));

                // =================================================
                // ACESSO RÁPIDO
                // =================================================

                JLabel tituloAtalhos = new JLabel(
                                "Acesso rápido");

                tituloAtalhos.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                20));

                tituloAtalhos.setForeground(TEXTO);

                tituloAtalhos.setAlignmentX(
                                Component.LEFT_ALIGNMENT);

                dashboard.add(tituloAtalhos);

                dashboard.add(
                                Box.createVerticalStrut(15));

                JPanel atalhos = new JPanel(
                                new FlowLayout(
                                                FlowLayout.LEFT,
                                                10,
                                                5));

                atalhos.setBackground(FUNDO);

                atalhos.setAlignmentX(
                                Component.LEFT_ALIGNMENT);

                JButton cadastrarPaciente = criarBotaoAcao(
                                "Cadastrar paciente");

                JButton verPacientes = criarBotaoAcao(
                                "Ver pacientes");

                cadastrarPaciente.addActionListener(
                                e -> abrirPacientes());

                verPacientes.addActionListener(
                                e -> abrirPacientes());

                atalhos.add(
                                cadastrarPaciente);

                atalhos.add(
                                verPacientes);

                dashboard.add(atalhos);

                painelConteudo.add(
                                dashboard,
                                BorderLayout.CENTER);

                atualizarTotalPacientes();

                atualizarTotalPacientes();
                atualizarConsultasHoje();

                painelConteudo.revalidate();

                painelConteudo.repaint();
        }

        // =====================================================
        // ENCONTRAR LABEL DO NÚMERO
        // =====================================================
        private void atualizarConsultasHoje() {

                String sql = """
                                SELECT COUNT(*)
                                FROM consultas
                                WHERE data_consulta = CURDATE()
                                """;

                try (
                                Connection conexao = Conexao.conectar();

                                PreparedStatement comando = conexao.prepareStatement(sql);

                                ResultSet resultado = comando.executeQuery()) {

                        if (resultado.next()) {

                                int total = resultado.getInt(1);

                                if (numeroConsultas != null) {

                                        numeroConsultas.setText(
                                                        String.valueOf(total));
                                }
                        }

                } catch (SQLException e) {

                        if (numeroConsultas != null) {

                                numeroConsultas.setText("0");
                        }

                        System.out.println(
                                        "Erro ao buscar consultas de hoje:");

                        System.out.println(
                                        e.getMessage());
                }
        }

        private JLabel encontrarNumeroCard(
                        JPanel card) {

                for (Component componente : card.getComponents()) {

                        if (componente instanceof JLabel) {

                                JLabel label = (JLabel) componente;

                                if (label.getFont()
                                                .getSize() >= 30) {

                                        return label;
                                }
                        }
                }

                return null;
        }

        // =====================================================
        // ATUALIZAR TOTAL DE PACIENTES
        // =====================================================

        private void atualizarTotalPacientes() {

                String sql = "SELECT COUNT(*) FROM pacientes";

                try (
                                Connection conexao = Conexao.conectar();

                                PreparedStatement comando = conexao.prepareStatement(sql);

                                ResultSet resultado = comando.executeQuery()) {

                        if (resultado.next()) {

                                int total = resultado.getInt(1);

                                if (numeroPacientes != null) {

                                        numeroPacientes.setText(
                                                        String.valueOf(total));
                                }
                        }

                } catch (SQLException e) {

                        if (numeroPacientes != null) {

                                numeroPacientes.setText("0");
                        }

                        System.out.println(
                                        "Erro ao buscar total de pacientes:");

                        System.out.println(
                                        e.getMessage());
                }
        }

        // =====================================================
        // CARD
        // =====================================================

        private JPanel criarCard(
                        String titulo,
                        String numero,
                        String descricao) {

                JPanel card = new JPanel();

                card.setBackground(BRANCO);

                card.setLayout(
                                new BoxLayout(
                                                card,
                                                BoxLayout.Y_AXIS));

                card.setBorder(
                                BorderFactory.createCompoundBorder(
                                                BorderFactory.createLineBorder(
                                                                new Color(
                                                                                230,
                                                                                230,
                                                                                235)),
                                                new EmptyBorder(
                                                                20,
                                                                20,
                                                                20,
                                                                20)));

                JLabel labelTitulo = new JLabel(titulo);

                labelTitulo.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                14));

                labelTitulo.setForeground(CINZA);

                JLabel labelNumero = new JLabel(numero);

                labelNumero.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                32));

                labelNumero.setForeground(ROXO);

                JLabel labelDescricao = new JLabel(descricao);

                labelDescricao.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                12));

                labelDescricao.setForeground(CINZA);

                card.add(labelTitulo);

                card.add(
                                Box.createVerticalStrut(10));

                card.add(labelNumero);

                card.add(
                                Box.createVerticalStrut(5));

                card.add(labelDescricao);

                return card;
        }

        // =====================================================
        // BOTÃO AÇÃO
        // =====================================================

        private JButton criarBotaoAcao(
                        String texto) {

                JButton botao = new JButton(texto);

                botao.setPreferredSize(
                                new Dimension(
                                                180,
                                                45));

                botao.setBackground(ROXO);

                botao.setForeground(BRANCO);

                botao.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                13));

                botao.setFocusPainted(false);

                botao.setBorder(
                                BorderFactory.createEmptyBorder(
                                                5,
                                                15,
                                                5,
                                                15));

                botao.setCursor(
                                new Cursor(
                                                Cursor.HAND_CURSOR));

                return botao;
        }

        // =====================================================
        // ABRIR PACIENTES
        // =====================================================
        private void abrirConsultas() {

                painelConteudo.removeAll();

                Consultas consultas = new Consultas();

                painelConteudo.add(
                                consultas,
                                BorderLayout.CENTER);

                painelConteudo.revalidate();

                painelConteudo.repaint();
        }

        private void abrirPacientes() {

                painelConteudo.removeAll();

                Pacientes pacientes = new Pacientes();

                painelConteudo.add(
                                pacientes,
                                BorderLayout.CENTER);

                painelConteudo.revalidate();

                painelConteudo.repaint();
        }

        // =====================================================
        // MENSAGEM
        // =====================================================

        private void mostrarMensagem(
                        String mensagem) {

                JOptionPane.showMessageDialog(
                                this,
                                mensagem,
                                "Clinix",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        // =====================================================
        // MAIN
        // =====================================================

        public static void main(
                        String[] args) {

                SwingUtilities.invokeLater(
                                () -> new TelaPrincipal());
        }
}