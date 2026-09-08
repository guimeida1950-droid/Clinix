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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class Prontuarios extends JPanel {

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

    // =========================================================
    // CAMPOS
    // =========================================================

    private JComboBox<PacienteItem> comboPaciente;

    private JTextArea campoQueixa;
    private JTextArea campoHistorico;
    private JTextArea campoDiagnostico;
    private JTextArea campoTratamento;
    private JTextArea campoObservacoes;

    private JTable tabela;

    private DefaultTableModel modeloTabela;

    private JButton botaoSalvar;

    private int idProntuarioEditando = -1;

    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public Prontuarios() {

        setLayout(new BorderLayout());

        setBackground(FUNDO);

        criarInterface();

        carregarPacientes();

        carregarProntuarios();
    }

    // =========================================================
    // INTERFACE
    // =========================================================

    private void criarInterface() {

        // -----------------------------------------------------
        // TÍTULO
        // -----------------------------------------------------

        JPanel painelTitulo = new JPanel(new BorderLayout());

        painelTitulo.setBackground(FUNDO);

        painelTitulo.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        30,
                        15,
                        30));

        JLabel titulo = new JLabel("Prontuários");

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        titulo.setForeground(TEXTO);

        JLabel subtitulo = new JLabel(
                "Registre e visualize o histórico dos pacientes.");

        subtitulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        subtitulo.setForeground(CINZA);

        JPanel textosTitulo = new JPanel(
                new GridLayout(2, 1));

        textosTitulo.setOpaque(false);

        textosTitulo.add(titulo);
        textosTitulo.add(subtitulo);

        painelTitulo.add(
                textosTitulo,
                BorderLayout.WEST);

        add(
                painelTitulo,
                BorderLayout.NORTH);

        // -----------------------------------------------------
        // CENTRAL
        // -----------------------------------------------------

        JPanel painelCentral = new JPanel(
                new BorderLayout(
                        20,
                        20));

        painelCentral.setBackground(FUNDO);

        painelCentral.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        30,
                        30,
                        30));

        criarFormulario(painelCentral);

        criarTabela(painelCentral);

        add(
                painelCentral,
                BorderLayout.CENTER);
    }

    // =========================================================
    // FORMULÁRIO
    // =========================================================

    private void criarFormulario(
            JPanel painelCentral) {

        JPanel painelFormulario = new JPanel(
                new GridBagLayout());

        painelFormulario.setBackground(BRANCO);

        painelFormulario.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDA),
                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20)));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(
                7,
                7,
                7,
                7);

        gbc.gridx = 0;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        // -----------------------------------------------------
        // PACIENTE
        // -----------------------------------------------------

        gbc.gridy = 0;

        painelFormulario.add(
                criarLabel("Paciente"),
                gbc);

        comboPaciente = new JComboBox<>();

        comboPaciente.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        comboPaciente.setPreferredSize(
                new Dimension(
                        300,
                        38));

        gbc.gridy = 1;

        painelFormulario.add(
                comboPaciente,
                gbc);

        // -----------------------------------------------------
        // QUEIXA
        // -----------------------------------------------------

        gbc.gridy = 2;

        painelFormulario.add(
                criarLabel("Queixa principal"),
                gbc);

        campoQueixa = criarAreaTexto();

        gbc.gridy = 3;

        painelFormulario.add(
                criarScrollArea(
                        campoQueixa,
                        85),
                gbc);

        // -----------------------------------------------------
        // HISTÓRICO
        // -----------------------------------------------------

        gbc.gridy = 4;

        painelFormulario.add(
                criarLabel("Histórico"),
                gbc);

        campoHistorico = criarAreaTexto();

        gbc.gridy = 5;

        painelFormulario.add(
                criarScrollArea(
                        campoHistorico,
                        85),
                gbc);

        // -----------------------------------------------------
        // DIAGNÓSTICO
        // -----------------------------------------------------

        gbc.gridy = 6;

        painelFormulario.add(
                criarLabel("Diagnóstico"),
                gbc);

        campoDiagnostico = criarAreaTexto();

        gbc.gridy = 7;

        painelFormulario.add(
                criarScrollArea(
                        campoDiagnostico,
                        85),
                gbc);

        // -----------------------------------------------------
        // TRATAMENTO
        // -----------------------------------------------------

        gbc.gridy = 8;

        painelFormulario.add(
                criarLabel("Tratamento"),
                gbc);

        campoTratamento = criarAreaTexto();

        gbc.gridy = 9;

        painelFormulario.add(
                criarScrollArea(
                        campoTratamento,
                        85),
                gbc);

        // -----------------------------------------------------
        // OBSERVAÇÕES
        // -----------------------------------------------------

        gbc.gridy = 10;

        painelFormulario.add(
                criarLabel("Observações"),
                gbc);

        campoObservacoes = criarAreaTexto();

        gbc.gridy = 11;

        painelFormulario.add(
                criarScrollArea(
                        campoObservacoes,
                        120),
                gbc);

        // -----------------------------------------------------
        // BOTÕES
        // -----------------------------------------------------

        JPanel painelBotoes = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        10,
                        5));

        painelBotoes.setOpaque(false);

        JButton botaoNovo = criarBotaoSecundario(
                "NOVO PRONTUÁRIO");

        botaoSalvar = criarBotao(
                "SALVAR");

        botaoNovo.addActionListener(
                e -> limparFormulario());

        botaoSalvar.addActionListener(
                e -> salvarProntuario());

        painelBotoes.add(botaoNovo);

        painelBotoes.add(botaoSalvar);

        gbc.gridy = 12;

        gbc.insets = new Insets(
                15,
                7,
                7,
                7);

        painelFormulario.add(
                painelBotoes,
                gbc);

        // -----------------------------------------------------
        // SCROLL DO FORMULÁRIO
        // -----------------------------------------------------

        JScrollPane scrollFormulario = new JScrollPane(
                painelFormulario);

        scrollFormulario.setPreferredSize(
                new Dimension(
                        350,
                        500));

        scrollFormulario.setBorder(
                BorderFactory.createLineBorder(
                        BORDA));

        scrollFormulario.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scrollFormulario.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        painelCentral.add(
                scrollFormulario,
                BorderLayout.WEST);
    }

    // =========================================================
    // TEXT AREA
    // =========================================================

    private JTextArea criarAreaTexto() {

        JTextArea area = new JTextArea();

        area.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13));

        area.setLineWrap(true);

        area.setWrapStyleWord(true);

        area.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDA),
                        BorderFactory.createEmptyBorder(
                                8,
                                10,
                                8,
                                10)));

        return area;
    }

    // =========================================================
    // SCROLL DA ÁREA DE TEXTO
    // =========================================================

    private JScrollPane criarScrollArea(
            JTextArea area,
            int altura) {

        JScrollPane scroll = new JScrollPane(
                area);

        scroll.setPreferredSize(
                new Dimension(
                        300,
                        altura));

        scroll.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        scroll.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        return scroll;
    }

    // =========================================================
    // TABELA
    // =========================================================

    private void criarTabela(
            JPanel painelCentral) {

        JPanel painelTabela = new JPanel(
                new BorderLayout(
                        0,
                        10));

        painelTabela.setBackground(BRANCO);

        painelTabela.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDA),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15)));

        JLabel tituloTabela = new JLabel(
                "Prontuários registrados");

        tituloTabela.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        tituloTabela.setForeground(TEXTO);

        painelTabela.add(
                tituloTabela,
                BorderLayout.NORTH);

        // -----------------------------------------------------
        // MODELO
        // -----------------------------------------------------

        modeloTabela = new DefaultTableModel(
                new Object[] {
                        "ID",
                        "Paciente",
                        "Data do registro",
                        "Diagnóstico"
                },
                0) {

            @Override
            public boolean isCellEditable(
                    int row,
                    int column) {

                return false;
            }
        };

        tabela = new JTable(
                modeloTabela);

        tabela.setRowHeight(40);

        tabela.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13));

        tabela.setForeground(TEXTO);

        tabela.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        tabela.setShowVerticalLines(false);

        tabela.setShowHorizontalLines(true);

        tabela.setGridColor(
                new Color(
                        235,
                        235,
                        240));

        JTableHeader cabecalho = tabela.getTableHeader();

        cabecalho.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13));

        cabecalho.setBackground(
                new Color(
                        240,
                        240,
                        245));

        cabecalho.setForeground(TEXTO);

        cabecalho.setPreferredSize(
                new Dimension(
                        100,
                        42));

        // -----------------------------------------------------
        // LARGURAS
        // -----------------------------------------------------

        tabela.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(50);

        tabela.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(220);

        tabela.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(170);

        tabela.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(300);

        JScrollPane scroll = new JScrollPane(
                tabela);

        scroll.setBorder(
                BorderFactory.createEmptyBorder());

        painelTabela.add(
                scroll,
                BorderLayout.CENTER);

        // -----------------------------------------------------
        // BOTÕES
        // -----------------------------------------------------

        JPanel painelAcoes = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        10,
                        0));

        painelAcoes.setOpaque(false);

        JButton botaoVer = criarBotao(
                "VER PRONTUÁRIO");

        JButton botaoEditar = criarBotao(
                "EDITAR");

        JButton botaoExcluir = criarBotaoExcluir(
                "EXCLUIR");

        botaoVer.addActionListener(
                e -> visualizarProntuario());

        botaoEditar.addActionListener(
                e -> editarProntuario());

        botaoExcluir.addActionListener(
                e -> excluirProntuario());

        painelAcoes.add(botaoVer);

        painelAcoes.add(botaoEditar);

        painelAcoes.add(botaoExcluir);

        painelTabela.add(
                painelAcoes,
                BorderLayout.SOUTH);

        // -----------------------------------------------------
        // DUPLO CLIQUE
        // -----------------------------------------------------

        tabela.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e) {

                        if (e.getClickCount() == 2) {

                            visualizarProntuario();
                        }
                    }
                });

        painelCentral.add(
                painelTabela,
                BorderLayout.CENTER);
    }

    // =========================================================
    // LABEL
    // =========================================================

    private JLabel criarLabel(
            String texto) {

        JLabel label = new JLabel(texto);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13));

        label.setForeground(TEXTO);

        return label;
    }

    // =========================================================
    // BOTÃO
    // =========================================================

    private JButton criarBotao(
            String texto) {

        JButton botao = new JButton(texto);

        botao.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12));

        botao.setForeground(BRANCO);

        botao.setBackground(ROXO);

        botao.setFocusPainted(false);

        botao.setBorderPainted(false);

        botao.setOpaque(true);

        botao.setPreferredSize(
                new Dimension(
                        160,
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

        botao.setForeground(TEXTO);

        botao.setBackground(
                new Color(
                        235,
                        235,
                        240));

        botao.setFocusPainted(false);

        botao.setBorderPainted(false);

        botao.setPreferredSize(
                new Dimension(
                        170,
                        40));

        return botao;
    }

    // =========================================================
    // BOTÃO EXCLUIR
    // =========================================================

    private JButton criarBotaoExcluir(
            String texto) {

        JButton botao = new JButton(texto);

        botao.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12));

        botao.setForeground(BRANCO);

        botao.setBackground(VERMELHO);

        botao.setFocusPainted(false);

        botao.setBorderPainted(false);

        botao.setPreferredSize(
                new Dimension(
                        100,
                        40));

        return botao;
    }

    // =========================================================
    // CARREGAR PACIENTES
    // =========================================================

    private void carregarPacientes() {

        comboPaciente.removeAllItems();

        String sql = "SELECT id, nome FROM pacientes ORDER BY nome";

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql);

                ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {

                comboPaciente.addItem(
                        new PacienteItem(
                                resultado.getInt("id"),
                                resultado.getString("nome")));
            }

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar pacientes:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // SALVAR
    // =========================================================

    private void salvarProntuario() {

        PacienteItem paciente = (PacienteItem) comboPaciente.getSelectedItem();

        if (paciente == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um paciente.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        String queixa = campoQueixa.getText().trim();

        String historico = campoHistorico.getText().trim();

        String diagnostico = campoDiagnostico.getText().trim();

        String tratamento = campoTratamento.getText().trim();

        String observacoes = campoObservacoes.getText().trim();

        if (idProntuarioEditando != -1) {

            atualizarProntuario(
                    paciente.getId(),
                    queixa,
                    historico,
                    diagnostico,
                    tratamento,
                    observacoes);

            return;
        }

        String sql = """
                INSERT INTO prontuarios
                (
                    paciente_id,
                    queixa_principal,
                    historico,
                    diagnostico,
                    tratamento,
                    observacoes
                )
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    paciente.getId());

            comando.setString(
                    2,
                    queixa);

            comando.setString(
                    3,
                    historico);

            comando.setString(
                    4,
                    diagnostico);

            comando.setString(
                    5,
                    tratamento);

            comando.setString(
                    6,
                    observacoes);

            comando.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Prontuário registrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparFormulario();

            carregarProntuarios();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao salvar prontuário:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // ATUALIZAR
    // =========================================================

    private void atualizarProntuario(
            int pacienteId,
            String queixa,
            String historico,
            String diagnostico,
            String tratamento,
            String observacoes) {

        String sql = """
                UPDATE prontuarios
                SET paciente_id = ?,
                    queixa_principal = ?,
                    historico = ?,
                    diagnostico = ?,
                    tratamento = ?,
                    observacoes = ?
                WHERE id = ?
                """;

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    pacienteId);

            comando.setString(
                    2,
                    queixa);

            comando.setString(
                    3,
                    historico);

            comando.setString(
                    4,
                    diagnostico);

            comando.setString(
                    5,
                    tratamento);

            comando.setString(
                    6,
                    observacoes);

            comando.setInt(
                    7,
                    idProntuarioEditando);

            comando.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Prontuário atualizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparFormulario();

            carregarProntuarios();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao atualizar prontuário:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // CARREGAR PRONTUÁRIOS
    // =========================================================

    private void carregarProntuarios() {

        modeloTabela.setRowCount(0);

        String sql = """
                SELECT
                    pr.id,
                    p.nome AS paciente,
                    pr.data_registro,
                    pr.diagnostico
                FROM prontuarios pr
                INNER JOIN pacientes p
                    ON pr.paciente_id = p.id
                ORDER BY pr.data_registro DESC
                """;

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql);

                ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {

                Timestamp data = resultado.getTimestamp(
                        "data_registro");

                String dataFormatada = "";

                if (data != null) {

                    java.time.LocalDateTime localDateTime = data.toLocalDateTime();

                    dataFormatada = String.format(
                            "%02d-%02d-%04d %02d:%02d",
                            localDateTime.getDayOfMonth(),
                            localDateTime.getMonthValue(),
                            localDateTime.getYear(),
                            localDateTime.getHour(),
                            localDateTime.getMinute());
                }

                modeloTabela.addRow(
                        new Object[] {
                                resultado.getInt("id"),
                                resultado.getString("paciente"),
                                dataFormatada,
                                valorSeguro(
                                        resultado.getString(
                                                "diagnostico"))
                        });
            }

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar prontuários:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // VISUALIZAR PRONTUÁRIO COMPLETO
    // =========================================================

    private void visualizarProntuario() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um prontuário.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int id = Integer.parseInt(
                modeloTabela
                        .getValueAt(
                                linha,
                                0)
                        .toString());

        mostrarProntuarioCompleto(id);
    }

    // =========================================================
    // JANELA DO PRONTUÁRIO
    // =========================================================

    private void mostrarProntuarioCompleto(
            int id) {

        String sql = """
                SELECT
                    p.nome AS paciente,
                    pr.data_registro,
                    pr.queixa_principal,
                    pr.historico,
                    pr.diagnostico,
                    pr.tratamento,
                    pr.observacoes
                FROM prontuarios pr
                INNER JOIN pacientes p
                    ON pr.paciente_id = p.id
                WHERE pr.id = ?
                """;

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    id);

            try (
                    ResultSet resultado = comando.executeQuery()) {

                if (!resultado.next()) {

                    return;
                }

                JDialog dialog = new JDialog(
                        JOptionPane.getFrameForComponent(
                                this),
                        "Prontuário",
                        true);

                dialog.setSize(
                        800,
                        700);

                dialog.setLocationRelativeTo(this);

                dialog.setLayout(
                        new BorderLayout());

                // -------------------------------------------------
                // CABEÇALHO
                // -------------------------------------------------

                JPanel cabecalho = new JPanel(
                        new BorderLayout());

                cabecalho.setBackground(BRANCO);

                cabecalho.setBorder(
                        BorderFactory.createEmptyBorder(
                                20,
                                25,
                                20,
                                25));

                JLabel titulo = new JLabel(
                        "Prontuário do paciente");

                titulo.setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                24));

                titulo.setForeground(TEXTO);

                JLabel paciente = new JLabel(
                        resultado.getString(
                                "paciente"));

                paciente.setFont(
                        new Font(
                                "Segoe UI",
                                Font.PLAIN,
                                16));

                paciente.setForeground(CINZA);

                JPanel info = new JPanel(
                        new GridLayout(
                                2,
                                1));

                info.setOpaque(false);

                info.add(titulo);

                info.add(paciente);

                cabecalho.add(
                        info,
                        BorderLayout.WEST);

                dialog.add(
                        cabecalho,
                        BorderLayout.NORTH);

                // -------------------------------------------------
                // CONTEÚDO
                // -------------------------------------------------

                JPanel conteudo = new JPanel(
                        new GridBagLayout());

                conteudo.setBackground(FUNDO);

                conteudo.setBorder(
                        BorderFactory.createEmptyBorder(
                                10,
                                25,
                                10,
                                25));

                GridBagConstraints gbc = new GridBagConstraints();

                gbc.gridx = 0;

                gbc.weightx = 1;

                gbc.fill = GridBagConstraints.HORIZONTAL;

                gbc.insets = new Insets(
                        8,
                        0,
                        8,
                        0);

                int linhaAtual = 0;

                Timestamp data = resultado.getTimestamp(
                        "data_registro");

                String dataFormatada = "";

                if (data != null) {

                    java.time.LocalDateTime dt = data.toLocalDateTime();

                    dataFormatada = String.format(
                            "%02d-%02d-%04d %02d:%02d",
                            dt.getDayOfMonth(),
                            dt.getMonthValue(),
                            dt.getYear(),
                            dt.getHour(),
                            dt.getMinute());
                }

                adicionarCampoVisualizacao(
                        conteudo,
                        gbc,
                        linhaAtual++,
                        "Data do registro",
                        dataFormatada);

                adicionarCampoVisualizacao(
                        conteudo,
                        gbc,
                        linhaAtual++,
                        "Queixa principal",
                        valorSeguro(
                                resultado.getString(
                                        "queixa_principal")));

                adicionarCampoVisualizacao(
                        conteudo,
                        gbc,
                        linhaAtual++,
                        "Histórico",
                        valorSeguro(
                                resultado.getString(
                                        "historico")));

                adicionarCampoVisualizacao(
                        conteudo,
                        gbc,
                        linhaAtual++,
                        "Diagnóstico",
                        valorSeguro(
                                resultado.getString(
                                        "diagnostico")));

                adicionarCampoVisualizacao(
                        conteudo,
                        gbc,
                        linhaAtual++,
                        "Tratamento",
                        valorSeguro(
                                resultado.getString(
                                        "tratamento")));

                adicionarCampoVisualizacao(
                        conteudo,
                        gbc,
                        linhaAtual++,
                        "Observações",
                        valorSeguro(
                                resultado.getString(
                                        "observacoes")));

                gbc.gridy = linhaAtual;

                gbc.weighty = 1;

                JPanel espaco = new JPanel();

                espaco.setOpaque(false);

                conteudo.add(
                        espaco,
                        gbc);

                JScrollPane scroll = new JScrollPane(
                        conteudo);

                scroll.setBorder(
                        BorderFactory.createEmptyBorder());

                dialog.add(
                        scroll,
                        BorderLayout.CENTER);

                // -------------------------------------------------
                // BOTÃO FECHAR
                // -------------------------------------------------

                JPanel painelFechar = new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT));

                painelFechar.setBackground(BRANCO);

                painelFechar.setBorder(
                        BorderFactory.createEmptyBorder(
                                10,
                                20,
                                10,
                                20));

                JButton fechar = criarBotao(
                        "FECHAR");

                fechar.addActionListener(
                        e -> dialog.dispose());

                painelFechar.add(fechar);

                dialog.add(
                        painelFechar,
                        BorderLayout.SOUTH);

                dialog.setVisible(true);
            }

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao abrir prontuário:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // CAMPO DE VISUALIZAÇÃO
    // =========================================================

    private void adicionarCampoVisualizacao(
            JPanel painel,
            GridBagConstraints gbc,
            int linha,
            String titulo,
            String valor) {

        gbc.gridy = linha;

        JLabel label = new JLabel(titulo);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13));

        label.setForeground(TEXTO);

        painel.add(
                label,
                gbc);

        JTextArea area = new JTextArea(
                valor);

        area.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        area.setForeground(TEXTO);

        area.setBackground(BRANCO);

        area.setEditable(false);

        area.setLineWrap(true);

        area.setWrapStyleWord(true);

        area.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDA),
                        BorderFactory.createEmptyBorder(
                                10,
                                10,
                                10,
                                10)));

        JScrollPane scroll = new JScrollPane(
                area);

        scroll.setPreferredSize(
                new Dimension(
                        700,
                        80));

        scroll.setVerticalScrollBarPolicy(
                ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);

        scroll.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        gbc.gridy = linha + 1;

        painel.add(
                scroll,
                gbc);
    }

    // =========================================================
    // EDITAR
    // =========================================================

    private void editarProntuario() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um prontuário.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        idProntuarioEditando = Integer.parseInt(
                modeloTabela
                        .getValueAt(
                                linha,
                                0)
                        .toString());

        carregarDadosCompletos(
                idProntuarioEditando);

        botaoSalvar.setText(
                "SALVAR ALTERAÇÕES");
    }

    // =========================================================
    // CARREGAR DADOS COMPLETOS
    // =========================================================

    private void carregarDadosCompletos(
            int id) {

        String sql = """
                SELECT
                    paciente_id,
                    queixa_principal,
                    historico,
                    diagnostico,
                    tratamento,
                    observacoes
                FROM prontuarios
                WHERE id = ?
                """;

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    id);

            try (
                    ResultSet resultado = comando.executeQuery()) {

                if (resultado.next()) {

                    selecionarPacientePorId(
                            resultado.getInt(
                                    "paciente_id"));

                    campoQueixa.setText(
                            valorSeguro(
                                    resultado.getString(
                                            "queixa_principal")));

                    campoHistorico.setText(
                            valorSeguro(
                                    resultado.getString(
                                            "historico")));

                    campoDiagnostico.setText(
                            valorSeguro(
                                    resultado.getString(
                                            "diagnostico")));

                    campoTratamento.setText(
                            valorSeguro(
                                    resultado.getString(
                                            "tratamento")));

                    campoObservacoes.setText(
                            valorSeguro(
                                    resultado.getString(
                                            "observacoes")));
                }
            }

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar prontuário:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // SELECIONAR PACIENTE
    // =========================================================

    private void selecionarPacientePorId(
            int id) {

        for (int i = 0; i < comboPaciente.getItemCount(); i++) {

            PacienteItem item = comboPaciente.getItemAt(i);

            if (item.getId() == id) {

                comboPaciente.setSelectedIndex(
                        i);

                return;
            }
        }
    }

    // =========================================================
    // EXCLUIR
    // =========================================================

    private void excluirProntuario() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um prontuário.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        int id = Integer.parseInt(
                modeloTabela
                        .getValueAt(
                                linha,
                                0)
                        .toString());

        String paciente = modeloTabela
                .getValueAt(
                        linha,
                        1)
                .toString();

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir o prontuário de:\n\n"
                        + paciente
                        + "?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (resposta != JOptionPane.YES_OPTION) {

            return;
        }

        String sql = "DELETE FROM prontuarios WHERE id = ?";

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    id);

            comando.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Prontuário excluído com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparFormulario();

            carregarProntuarios();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao excluir prontuário:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // LIMPAR
    // =========================================================

    private void limparFormulario() {

        if (comboPaciente.getItemCount() > 0) {

            comboPaciente.setSelectedIndex(0);
        }

        campoQueixa.setText("");

        campoHistorico.setText("");

        campoDiagnostico.setText("");

        campoTratamento.setText("");

        campoObservacoes.setText("");

        idProntuarioEditando = -1;

        botaoSalvar.setText(
                "SALVAR");

        tabela.clearSelection();
    }

    // =========================================================
    // VALOR SEGURO
    // =========================================================

    private String valorSeguro(
            String valor) {

        return valor == null
                ? ""
                : valor;
    }

    // =========================================================
    // ITEM DO PACIENTE
    // =========================================================

    private static class PacienteItem {

        private final int id;

        private final String nome;

        public PacienteItem(
                int id,
                String nome) {

            this.id = id;

            this.nome = nome;
        }

        public int getId() {

            return id;
        }

        public String getNome() {

            return nome;
        }

        @Override
        public String toString() {

            return nome;
        }
    }
}