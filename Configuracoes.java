import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Configuracoes extends JPanel {

        // =========================================================
        // CORES
        // =========================================================

        private static final Color FUNDO = new Color(245, 246, 250);

        private static final Color ROXO = new Color(104, 67, 180);

        private static final Color BRANCO = Color.WHITE;

        private static final Color TEXTO = new Color(45, 45, 50);

        private static final Color CINZA = new Color(120, 120, 125);

        private static final Color BORDA = new Color(220, 220, 225);

        private static final Color VERMELHO = new Color(190, 50, 50);

        private static final Color VERDE = new Color(45, 150, 90);

        // =========================================================
        // CAMPOS
        // =========================================================

        private JTextField campoHost;
        private JTextField campoPorta;
        private JTextField campoBanco;
        private JTextField campoUsuario;
        private JPasswordField campoSenha;

        private JCheckBox checkMaximizado;

        // =========================================================
        // CONSTRUTOR
        // =========================================================

        public Configuracoes() {

                setLayout(
                                new BorderLayout());

                setBackground(FUNDO);

                criarInterface();

                carregarConfiguracoes();
        }

        // =========================================================
        // INTERFACE
        // =========================================================

        private void criarInterface() {

                // -----------------------------------------------------
                // TÍTULO
                // -----------------------------------------------------

                JPanel painelTitulo = new JPanel(
                                new BorderLayout());

                painelTitulo.setBackground(
                                FUNDO);

                painelTitulo.setBorder(
                                BorderFactory.createEmptyBorder(
                                                25,
                                                30,
                                                15,
                                                30));

                JLabel titulo = new JLabel(
                                "Configurações");

                titulo.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                28));

                titulo.setForeground(
                                TEXTO);

                JLabel subtitulo = new JLabel(
                                "Configure o banco e o comportamento do Clinix.");

                subtitulo.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                14));

                subtitulo.setForeground(
                                CINZA);

                JPanel textos = new JPanel(
                                new GridLayout(
                                                2,
                                                1));

                textos.setOpaque(false);

                textos.add(titulo);
                textos.add(subtitulo);

                painelTitulo.add(
                                textos,
                                BorderLayout.WEST);

                add(
                                painelTitulo,
                                BorderLayout.NORTH);

                // -----------------------------------------------------
                // CONTEÚDO
                // -----------------------------------------------------

                JPanel conteudo = new JPanel(
                                new GridBagLayout());

                conteudo.setBackground(
                                FUNDO);

                conteudo.setBorder(
                                BorderFactory.createEmptyBorder(
                                                0,
                                                30,
                                                30,
                                                30));

                GridBagConstraints gbc = new GridBagConstraints();

                gbc.gridx = 0;

                gbc.weightx = 1;

                gbc.fill = GridBagConstraints.HORIZONTAL;

                gbc.anchor = GridBagConstraints.NORTHWEST;

                gbc.insets = new Insets(
                                0,
                                0,
                                20,
                                0);

                // -----------------------------------------------------
                // BANCO DE DADOS
                // -----------------------------------------------------

                JPanel painelBanco = criarSecao(
                                "Banco de dados",
                                "Configure onde o Clinix encontra o MySQL.");

                JPanel formulario = new JPanel(
                                new GridBagLayout());

                formulario.setOpaque(false);

                GridBagConstraints f = new GridBagConstraints();

                f.insets = new Insets(
                                6,
                                6,
                                6,
                                6);

                f.fill = GridBagConstraints.HORIZONTAL;

                f.weightx = 1;

                // HOST

                campoHost = criarCampo();

                adicionarCampo(
                                formulario,
                                f,
                                0,
                                "Servidor",
                                campoHost);

                // PORTA

                campoPorta = criarCampo();

                adicionarCampo(
                                formulario,
                                f,
                                1,
                                "Porta",
                                campoPorta);

                // BANCO

                campoBanco = criarCampo();

                adicionarCampo(
                                formulario,
                                f,
                                2,
                                "Banco de dados",
                                campoBanco);

                // USUÁRIO

                campoUsuario = criarCampo();

                adicionarCampo(
                                formulario,
                                f,
                                3,
                                "Usuário",
                                campoUsuario);

                // SENHA

                campoSenha = new JPasswordField();

                campoSenha.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                14));

                campoSenha.setPreferredSize(
                                new Dimension(
                                                300,
                                                38));

                campoSenha.setBorder(
                                BorderFactory.createCompoundBorder(
                                                BorderFactory.createLineBorder(
                                                                BORDA),
                                                BorderFactory.createEmptyBorder(
                                                                5,
                                                                10,
                                                                5,
                                                                10)));

                adicionarCampo(
                                formulario,
                                f,
                                4,
                                "Senha",
                                campoSenha);

                painelBanco.add(
                                formulario);

                // BOTÕES DO BANCO

                JPanel botoesBanco = new JPanel(
                                new FlowLayout(
                                                FlowLayout.LEFT,
                                                10,
                                                10));

                botoesBanco.setOpaque(false);

                JButton testar = criarBotao(
                                "TESTAR CONEXÃO");

                JButton salvar = criarBotao(
                                "SALVAR CONFIGURAÇÕES");

                JButton restaurar = criarBotaoSecundario(
                                "RESTAURAR PADRÃO");

                testar.addActionListener(
                                e -> testarConexao());

                salvar.addActionListener(
                                e -> salvarConfiguracoes());

                restaurar.addActionListener(
                                e -> restaurarConfiguracoes());

                botoesBanco.add(testar);
                botoesBanco.add(salvar);
                botoesBanco.add(restaurar);

                painelBanco.add(
                                botoesBanco);

                gbc.gridy = 0;

                conteudo.add(
                                painelBanco,
                                gbc);

                // -----------------------------------------------------
                // SISTEMA
                // -----------------------------------------------------

                JPanel painelSistema = criarSecao(
                                "Sistema",
                                "Defina o comportamento de inicialização do Clinix.");

                checkMaximizado = new JCheckBox(
                                "Iniciar o Clinix maximizado");

                checkMaximizado.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                14));

                checkMaximizado.setForeground(
                                TEXTO);

                checkMaximizado.setOpaque(false);

                checkMaximizado.setFocusPainted(false);

                painelSistema.add(
                                checkMaximizado);

                gbc.gridy = 1;

                conteudo.add(
                                painelSistema,
                                gbc);

                // -----------------------------------------------------
                // SOBRE
                // -----------------------------------------------------

                JPanel painelSobre = criarSecao(
                                "Sobre",
                                "Informações do aplicativo.");

                JLabel versao = new JLabel(
                                "Clinix 1.0");

                versao.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                15));

                versao.setForeground(
                                TEXTO);

                JLabel java = new JLabel(
                                "Java "
                                                + System.getProperty(
                                                                "java.version"));

                java.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                14));

                java.setForeground(
                                CINZA);

                JLabel sistema = new JLabel(
                                System.getProperty(
                                                "os.name"));

                sistema.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                14));

                sistema.setForeground(
                                CINZA);

                painelSobre.add(versao);
                painelSobre.add(java);
                painelSobre.add(sistema);

                gbc.gridy = 2;

                conteudo.add(
                                painelSobre,
                                gbc);

                // -----------------------------------------------------
                // SCROLL
                // -----------------------------------------------------

                JScrollPane scroll = new JScrollPane(
                                conteudo);

                scroll.setBorder(
                                BorderFactory.createEmptyBorder());

                scroll.getVerticalScrollBar()
                                .setUnitIncrement(
                                                16);

                add(
                                scroll,
                                BorderLayout.CENTER);
        }

        // =========================================================
        // SEÇÃO
        // =========================================================

        private JPanel criarSecao(
                        String titulo,
                        String descricao) {

                JPanel painel = new JPanel();

                painel.setLayout(
                                new FlowLayout(
                                                FlowLayout.LEFT,
                                                15,
                                                10));

                painel.setBackground(
                                BRANCO);

                painel.setBorder(
                                BorderFactory.createCompoundBorder(
                                                BorderFactory.createLineBorder(
                                                                BORDA),
                                                BorderFactory.createEmptyBorder(
                                                                15,
                                                                15,
                                                                15,
                                                                15)));

                JLabel labelTitulo = new JLabel(
                                titulo);

                labelTitulo.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                20));

                labelTitulo.setForeground(
                                TEXTO);

                labelTitulo.setPreferredSize(
                                new Dimension(
                                                750,
                                                30));

                JLabel labelDescricao = new JLabel(
                                descricao);

                labelDescricao.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                13));

                labelDescricao.setForeground(
                                CINZA);

                labelDescricao.setPreferredSize(
                                new Dimension(
                                                750,
                                                25));

                painel.add(
                                labelTitulo);

                painel.add(
                                labelDescricao);

                return painel;
        }

        // =========================================================
        // ADICIONAR CAMPO
        // =========================================================

        private void adicionarCampo(
                        JPanel painel,
                        GridBagConstraints gbc,
                        int linha,
                        String titulo,
                        java.awt.Component campo) {

                gbc.gridx = 0;
                gbc.gridy = linha;
                gbc.weightx = 0.25;

                JLabel label = new JLabel(
                                titulo);

                label.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                13));

                label.setForeground(
                                TEXTO);

                painel.add(
                                label,
                                gbc);

                gbc.gridx = 1;
                gbc.weightx = 1;

                painel.add(
                                campo,
                                gbc);
        }

        // =========================================================
        // CAMPO
        // =========================================================

        private JTextField criarCampo() {

                JTextField campo = new JTextField();

                campo.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.PLAIN,
                                                14));

                campo.setPreferredSize(
                                new Dimension(
                                                300,
                                                38));

                campo.setBorder(
                                BorderFactory.createCompoundBorder(
                                                BorderFactory.createLineBorder(
                                                                BORDA),
                                                BorderFactory.createEmptyBorder(
                                                                5,
                                                                10,
                                                                5,
                                                                10)));

                return campo;
        }

        // =========================================================
        // CARREGAR
        // =========================================================

        private void carregarConfiguracoes() {

                campoHost.setText(
                                ConfiguracoesManager.getHost());

                campoPorta.setText(
                                ConfiguracoesManager.getPorta());

                campoBanco.setText(
                                ConfiguracoesManager.getBanco());

                campoUsuario.setText(
                                ConfiguracoesManager.getUsuario());

                campoSenha.setText(
                                ConfiguracoesManager.getSenha());

                checkMaximizado.setSelected(
                                ConfiguracoesManager.isMaximizado());
        }

        // =========================================================
        // VALIDAR CAMPOS
        // =========================================================

        private boolean validarCampos() {

                if (campoHost.getText()
                                .trim()
                                .isEmpty()) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Digite o servidor do banco.",
                                        "Atenção",
                                        JOptionPane.WARNING_MESSAGE);

                        campoHost.requestFocus();

                        return false;
                }

                if (campoPorta.getText()
                                .trim()
                                .isEmpty()) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Digite a porta do MySQL.",
                                        "Atenção",
                                        JOptionPane.WARNING_MESSAGE);

                        campoPorta.requestFocus();

                        return false;
                }

                try {

                        int porta = Integer.parseInt(
                                        campoPorta.getText()
                                                        .trim());

                        if (porta < 1
                                        || porta > 65535) {

                                throw new NumberFormatException();
                        }

                } catch (NumberFormatException erro) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "A porta deve ser um número entre 1 e 65535.",
                                        "Porta inválida",
                                        JOptionPane.WARNING_MESSAGE);

                        campoPorta.requestFocus();

                        return false;
                }

                if (campoBanco.getText()
                                .trim()
                                .isEmpty()) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Digite o nome do banco.",
                                        "Atenção",
                                        JOptionPane.WARNING_MESSAGE);

                        campoBanco.requestFocus();

                        return false;
                }

                if (campoUsuario.getText()
                                .trim()
                                .isEmpty()) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Digite o usuário do banco.",
                                        "Atenção",
                                        JOptionPane.WARNING_MESSAGE);

                        campoUsuario.requestFocus();

                        return false;
                }

                return true;
        }

        // =========================================================
        // SALVAR CONFIGURAÇÕES
        // =========================================================

        private void salvarConfiguracoes() {

                if (!validarCampos()) {
                        return;
                }

                ConfiguracoesManager.setHost(
                                campoHost.getText().trim());

                ConfiguracoesManager.setPorta(
                                campoPorta.getText().trim());

                ConfiguracoesManager.setBanco(
                                campoBanco.getText().trim());

                ConfiguracoesManager.setUsuario(
                                campoUsuario.getText().trim());

                ConfiguracoesManager.setSenha(
                                new String(
                                                campoSenha.getPassword()));

                ConfiguracoesManager.setMaximizado(
                                checkMaximizado.isSelected());

                JOptionPane.showMessageDialog(
                                this,
                                "Configurações salvas com sucesso!",
                                "Clinix",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        // =========================================================
        // TESTAR CONEXÃO
        // =========================================================

        private void testarConexao() {

                if (!validarCampos()) {
                        return;
                }

                String hostAnterior = ConfiguracoesManager.getHost();

                String portaAnterior = ConfiguracoesManager.getPorta();

                String bancoAnterior = ConfiguracoesManager.getBanco();

                String usuarioAnterior = ConfiguracoesManager.getUsuario();

                String senhaAnterior = ConfiguracoesManager.getSenha();

                // Usa temporariamente os dados digitados

                ConfiguracoesManager.setHost(
                                campoHost.getText().trim());

                ConfiguracoesManager.setPorta(
                                campoPorta.getText().trim());

                ConfiguracoesManager.setBanco(
                                campoBanco.getText().trim());

                ConfiguracoesManager.setUsuario(
                                campoUsuario.getText().trim());

                ConfiguracoesManager.setSenha(
                                new String(
                                                campoSenha.getPassword()));

                try (
                                Connection conexao = Conexao.conectar()) {

                        if (conexao != null
                                        && !conexao.isClosed()) {

                                JOptionPane.showMessageDialog(
                                                this,
                                                "Conexão realizada com sucesso!\n\n"
                                                                + "Servidor: "
                                                                + campoHost.getText()
                                                                + "\nPorta: "
                                                                + campoPorta.getText()
                                                                + "\nBanco: "
                                                                + campoBanco.getText(),
                                                "Conexão OK",
                                                JOptionPane.INFORMATION_MESSAGE);
                        }

                } catch (SQLException erro) {

                        JOptionPane.showMessageDialog(
                                        this,
                                        "Não foi possível conectar:\n\n"
                                                        + erro.getMessage(),
                                        "Erro de conexão",
                                        JOptionPane.ERROR_MESSAGE);

                } finally {

                        // Restaura os dados anteriores.
                        // Eles só serão permanentes quando
                        // o usuário clicar em SALVAR.

                        ConfiguracoesManager.setHost(
                                        hostAnterior);

                        ConfiguracoesManager.setPorta(
                                        portaAnterior);

                        ConfiguracoesManager.setBanco(
                                        bancoAnterior);

                        ConfiguracoesManager.setUsuario(
                                        usuarioAnterior);

                        ConfiguracoesManager.setSenha(
                                        senhaAnterior);
                }
        }

        // =========================================================
        // RESTAURAR PADRÃO
        // =========================================================

        private void restaurarConfiguracoes() {

                int resposta = JOptionPane.showConfirmDialog(
                                this,
                                "Restaurar todas as configurações padrão?",
                                "Restaurar padrão",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE);

                if (resposta != JOptionPane.YES_OPTION) {

                        return;
                }

                ConfiguracoesManager.restaurarPadrao();

                carregarConfiguracoes();

                JOptionPane.showMessageDialog(
                                this,
                                "Configurações restauradas.",
                                "Clinix",
                                JOptionPane.INFORMATION_MESSAGE);
        }

        // =========================================================
        // BOTÃO ROXO
        // =========================================================

        private JButton criarBotao(
                        String texto) {

                JButton botao = new JButton(texto);

                botao.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                12));

                botao.setForeground(
                                BRANCO);

                botao.setBackground(
                                ROXO);

                botao.setFocusPainted(false);

                botao.setBorderPainted(false);

                botao.setPreferredSize(
                                new Dimension(
                                                190,
                                                40));

                return botao;
        }

        // =========================================================
        // BOTÃO SECUNDÁRIO
        // =========================================================

        private JButton criarBotaoSecundario(
                        String texto) {

                JButton botao = new JButton(texto);

                botao.setFont(
                                new Font(
                                                "Segoe UI",
                                                Font.BOLD,
                                                12));

                botao.setForeground(
                                TEXTO);

                botao.setBackground(
                                new Color(
                                                235,
                                                235,
                                                240));

                botao.setFocusPainted(false);

                botao.setBorderPainted(false);

                botao.setPreferredSize(
                                new Dimension(
                                                180,
                                                40));

                return botao;
        }
}