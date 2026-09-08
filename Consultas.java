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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Consultas extends JPanel {

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

    private JTextField campoData;

    private JTextField campoHora;

    private JTextField campoMedico;

    private JTextField campoEspecialidade;

    private JTextArea campoObservacoes;

    private JTable tabela;

    private DefaultTableModel modeloTabela;

    private JButton botaoSalvar;

    private int idConsultaEditando = -1;

    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public Consultas() {

        setLayout(new BorderLayout());

        setBackground(FUNDO);

        criarInterface();

        carregarPacientes();

        carregarConsultas();
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

        JLabel titulo = new JLabel("Consultas");

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        titulo.setForeground(TEXTO);

        JLabel subtitulo = new JLabel(
                "Agende, edite e gerencie as consultas.");

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
        // ÁREA CENTRAL
        // -----------------------------------------------------

        JPanel painelCentral = new JPanel(
                new BorderLayout(20, 20));

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

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        // -----------------------------------------------------
        // PACIENTE
        // -----------------------------------------------------

        gbc.gridx = 0;
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
                        280,
                        38));

        gbc.gridy = 1;

        painelFormulario.add(
                comboPaciente,
                gbc);

        // -----------------------------------------------------
        // DATA
        // -----------------------------------------------------

        gbc.gridy = 2;

        painelFormulario.add(
                criarLabel("Data da consulta"),
                gbc);

        campoData = criarCampo();

        aplicarMascaraData(
                campoData);

        gbc.gridy = 3;

        painelFormulario.add(
                campoData,
                gbc);

        // -----------------------------------------------------
        // HORA
        // -----------------------------------------------------

        gbc.gridy = 4;

        painelFormulario.add(
                criarLabel("Horário"),
                gbc);

        campoHora = criarCampo();

        aplicarMascaraHora(
                campoHora);

        gbc.gridy = 5;

        painelFormulario.add(
                campoHora,
                gbc);

        // -----------------------------------------------------
        // MÉDICO
        // -----------------------------------------------------

        gbc.gridy = 6;

        painelFormulario.add(
                criarLabel("Médico"),
                gbc);

        campoMedico = criarCampo();

        gbc.gridy = 7;

        painelFormulario.add(
                campoMedico,
                gbc);

        // -----------------------------------------------------
        // ESPECIALIDADE
        // -----------------------------------------------------

        gbc.gridy = 8;

        painelFormulario.add(
                criarLabel("Especialidade"),
                gbc);

        campoEspecialidade = criarCampo();

        gbc.gridy = 9;

        painelFormulario.add(
                campoEspecialidade,
                gbc);

        // -----------------------------------------------------
        // OBSERVAÇÕES
        // -----------------------------------------------------

        gbc.gridy = 10;

        painelFormulario.add(
                criarLabel("Observações"),
                gbc);

        campoObservacoes = new JTextArea();

        campoObservacoes.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14));

        campoObservacoes.setLineWrap(true);

        campoObservacoes.setWrapStyleWord(true);

        campoObservacoes.setRows(4);

        campoObservacoes.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDA),
                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                10)));

        JScrollPane scrollObservacoes = new JScrollPane(
                campoObservacoes);

        gbc.gridy = 11;

        gbc.fill = GridBagConstraints.BOTH;

        gbc.weighty = 1;

        painelFormulario.add(
                scrollObservacoes,
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
                "NOVA CONSULTA");

        botaoSalvar = criarBotao(
                "AGENDAR");

        botaoNovo.addActionListener(
                e -> limparFormulario());

        botaoSalvar.addActionListener(
                e -> salvarConsulta());

        painelBotoes.add(botaoNovo);

        painelBotoes.add(botaoSalvar);

        gbc.gridy = 12;

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weighty = 0;

        gbc.insets = new Insets(
                15,
                7,
                7,
                7);

        painelFormulario.add(
                painelBotoes,
                gbc);

        painelCentral.add(
                painelFormulario,
                BorderLayout.WEST);
    }

    // =========================================================
    // TABELA
    // =========================================================

    private void criarTabela(
            JPanel painelCentral) {

        JPanel painelTabela = new JPanel(
                new BorderLayout(0, 10));

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
                "Consultas agendadas");

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
                        "Data",
                        "Hora",
                        "Médico",
                        "Especialidade",
                        "Observações"
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

        tabela.setRowHeight(35);

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
                        40));

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

        JButton botaoEditar = criarBotao(
                "EDITAR");

        JButton botaoExcluir = criarBotaoExcluir(
                "EXCLUIR");

        botaoEditar.addActionListener(
                e -> editarConsulta());

        botaoExcluir.addActionListener(
                e -> excluirConsulta());

        painelAcoes.add(
                botaoEditar);

        painelAcoes.add(
                botaoExcluir);

        painelTabela.add(
                painelAcoes,
                BorderLayout.SOUTH);

        // -----------------------------------------------------
        // SELEÇÃO
        // -----------------------------------------------------

        tabela.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int linha = tabela.getSelectedRow();

                                if (linha >= 0) {

                                    carregarConsultaNoFormulario(
                                            linha);
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
                        280,
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

        botao.setForeground(BRANCO);

        botao.setBackground(ROXO);

        botao.setFocusPainted(false);

        botao.setBorderPainted(false);

        botao.setOpaque(true);

        botao.setPreferredSize(
                new Dimension(
                        150,
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
                        150,
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
    // CARREGAR PACIENTES NO COMBOBOX
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
    // SALVAR / ATUALIZAR
    // =========================================================

    private void salvarConsulta() {

        PacienteItem paciente = (PacienteItem) comboPaciente.getSelectedItem();

        String data = campoData.getText().trim();

        String hora = campoHora.getText().trim();

        String medico = campoMedico.getText().trim();

        String especialidade = campoEspecialidade.getText().trim();

        String observacoes = campoObservacoes.getText().trim();

        // -----------------------------------------------------
        // VALIDAÇÃO PACIENTE
        // -----------------------------------------------------

        if (paciente == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Cadastre pelo menos um paciente antes de agendar uma consulta.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        // -----------------------------------------------------
        // VALIDAÇÃO DATA
        // -----------------------------------------------------

        if (!data.matches(
                "\\d{2}-\\d{2}-\\d{4}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite a data no formato DD-MM-AAAA.",
                    "Data inválida",
                    JOptionPane.WARNING_MESSAGE);

            campoData.requestFocus();

            return;
        }

        String dataSQL;

        try {

            String[] partes = data.split("-");

            dataSQL = partes[2]
                    + "-"
                    + partes[1]
                    + "-"
                    + partes[0];

            Date.valueOf(dataSQL);

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "A data informada não é válida.",
                    "Data inválida",
                    JOptionPane.WARNING_MESSAGE);

            campoData.requestFocus();

            return;
        }

        // -----------------------------------------------------
        // VALIDAÇÃO HORA
        // -----------------------------------------------------

        if (!hora.matches(
                "\\d{2}:\\d{2}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o horário no formato HH:MM.",
                    "Horário inválido",
                    JOptionPane.WARNING_MESSAGE);

            campoHora.requestFocus();

            return;
        }

        String horaSQL = hora + ":00";

        try {

            Time.valueOf(horaSQL);

        } catch (Exception erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "O horário informado não é válido.",
                    "Horário inválido",
                    JOptionPane.WARNING_MESSAGE);

            campoHora.requestFocus();

            return;
        }

        // -----------------------------------------------------
        // VALIDAÇÃO MÉDICO
        // -----------------------------------------------------

        if (medico.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome do médico.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            campoMedico.requestFocus();

            return;
        }

        // -----------------------------------------------------
        // ATUALIZAR
        // -----------------------------------------------------

        if (idConsultaEditando != -1) {

            atualizarConsulta(
                    paciente.getId(),
                    dataSQL,
                    horaSQL,
                    medico,
                    especialidade,
                    observacoes);

            return;
        }

        // -----------------------------------------------------
        // CADASTRAR
        // -----------------------------------------------------

        String sql = """
                INSERT INTO consultas
                (
                    paciente_id,
                    data_consulta,
                    hora_consulta,
                    medico,
                    especialidade,
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

            comando.setDate(
                    2,
                    Date.valueOf(dataSQL));

            comando.setTime(
                    3,
                    Time.valueOf(horaSQL));

            comando.setString(
                    4,
                    medico);

            comando.setString(
                    5,
                    especialidade);

            comando.setString(
                    6,
                    observacoes);

            comando.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Consulta agendada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparFormulario();

            carregarConsultas();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao agendar consulta:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // ATUALIZAR CONSULTA
    // =========================================================

    private void atualizarConsulta(
            int pacienteId,
            String dataSQL,
            String horaSQL,
            String medico,
            String especialidade,
            String observacoes) {

        String sql = """
                UPDATE consultas
                SET paciente_id = ?,
                    data_consulta = ?,
                    hora_consulta = ?,
                    medico = ?,
                    especialidade = ?,
                    observacoes = ?
                WHERE id = ?
                """;

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    pacienteId);

            comando.setDate(
                    2,
                    Date.valueOf(dataSQL));

            comando.setTime(
                    3,
                    Time.valueOf(horaSQL));

            comando.setString(
                    4,
                    medico);

            comando.setString(
                    5,
                    especialidade);

            comando.setString(
                    6,
                    observacoes);

            comando.setInt(
                    7,
                    idConsultaEditando);

            comando.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Consulta atualizada com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparFormulario();

            carregarConsultas();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao atualizar consulta:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // CARREGAR CONSULTAS
    // =========================================================

    private void carregarConsultas() {

        modeloTabela.setRowCount(0);

        String sql = """
                SELECT
                    c.id,
                    p.nome AS paciente,
                    c.data_consulta,
                    c.hora_consulta,
                    c.medico,
                    c.especialidade,
                    c.observacoes
                FROM consultas c
                INNER JOIN pacientes p
                    ON c.paciente_id = p.id
                ORDER BY
                    c.data_consulta,
                    c.hora_consulta
                """;

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql);

                ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {

                Date data = resultado.getDate(
                        "data_consulta");

                Time hora = resultado.getTime(
                        "hora_consulta");

                String dataFormatada = "";

                if (data != null) {

                    java.time.LocalDate localDate = data.toLocalDate();

                    dataFormatada = String.format(
                            "%02d-%02d-%04d",
                            localDate.getDayOfMonth(),
                            localDate.getMonthValue(),
                            localDate.getYear());
                }

                String horaFormatada = "";

                if (hora != null) {

                    java.time.LocalTime localTime = hora.toLocalTime();

                    horaFormatada = String.format(
                            "%02d:%02d",
                            localTime.getHour(),
                            localTime.getMinute());
                }

                modeloTabela.addRow(
                        new Object[] {
                                resultado.getInt("id"),
                                resultado.getString("paciente"),
                                dataFormatada,
                                horaFormatada,
                                resultado.getString("medico"),
                                resultado.getString("especialidade"),
                                resultado.getString("observacoes")
                        });
            }

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao carregar consultas:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // EDITAR
    // =========================================================

    private void editarConsulta() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma consulta na tabela.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        idConsultaEditando = Integer.parseInt(
                modeloTabela
                        .getValueAt(
                                linha,
                                0)
                        .toString());

        String nomePaciente = modeloTabela
                .getValueAt(
                        linha,
                        1)
                .toString();

        selecionarPaciente(
                nomePaciente);

        campoData.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                2)
                        .toString());

        campoHora.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                3)
                        .toString());

        campoMedico.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                4)
                        .toString());

        Object valorEspecialidade = modeloTabela
                .getValueAt(
                        linha,
                        5);

        campoEspecialidade.setText(
                valorEspecialidade == null
                        ? ""
                        : valorEspecialidade.toString());

        Object valorObservacoes = modeloTabela
                .getValueAt(
                        linha,
                        6);

        campoObservacoes.setText(
                valorObservacoes == null
                        ? ""
                        : valorObservacoes.toString());

        botaoSalvar.setText(
                "SALVAR ALTERAÇÕES");

        campoData.requestFocus();
    }

    // =========================================================
    // SELECIONAR PACIENTE
    // =========================================================

    private void selecionarPaciente(
            String nomePaciente) {

        for (int i = 0; i < comboPaciente.getItemCount(); i++) {

            PacienteItem item = comboPaciente.getItemAt(i);

            if (item.getNome()
                    .equals(nomePaciente)) {

                comboPaciente.setSelectedIndex(i);

                return;
            }
        }
    }

    // =========================================================
    // EXCLUIR
    // =========================================================

    private void excluirConsulta() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione uma consulta na tabela.",
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

        String nomePaciente = modeloTabela
                .getValueAt(
                        linha,
                        1)
                .toString();

        String data = modeloTabela
                .getValueAt(
                        linha,
                        2)
                .toString();

        String hora = modeloTabela
                .getValueAt(
                        linha,
                        3)
                .toString();

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir esta consulta?\n\n"
                        + "Paciente: "
                        + nomePaciente
                        + "\nData: "
                        + data
                        + "\nHorário: "
                        + hora,
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (resposta != JOptionPane.YES_OPTION) {

            return;
        }

        String sql = "DELETE FROM consultas WHERE id = ?";

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    id);

            comando.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Consulta excluída com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparFormulario();

            carregarConsultas();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao excluir consulta:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // CARREGAR CONSULTA NO FORMULÁRIO
    // =========================================================

    private void carregarConsultaNoFormulario(
            int linha) {

        selecionarPaciente(
                modeloTabela
                        .getValueAt(
                                linha,
                                1)
                        .toString());

        campoData.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                2)
                        .toString());

        campoHora.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                3)
                        .toString());

        campoMedico.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                4)
                        .toString());

        Object especialidade = modeloTabela
                .getValueAt(
                        linha,
                        5);

        campoEspecialidade.setText(
                especialidade == null
                        ? ""
                        : especialidade.toString());

        Object observacoes = modeloTabela
                .getValueAt(
                        linha,
                        6);

        campoObservacoes.setText(
                observacoes == null
                        ? ""
                        : observacoes.toString());
    }

    // =========================================================
    // LIMPAR
    // =========================================================

    private void limparFormulario() {

        if (comboPaciente.getItemCount() > 0) {
            comboPaciente.setSelectedIndex(0);
        }

        campoData.setText("");

        campoHora.setText("");

        campoMedico.setText("");

        campoEspecialidade.setText("");

        campoObservacoes.setText("");

        idConsultaEditando = -1;

        botaoSalvar.setText(
                "AGENDAR");

        tabela.clearSelection();

        campoData.requestFocus();
    }

    // =========================================================
    // MÁSCARA DATA
    // =========================================================

    private void aplicarMascaraData(
            JTextField campo) {

        ((AbstractDocument) campo.getDocument())
                .setDocumentFilter(
                        new DocumentFilter() {

                            @Override
                            public void insertString(
                                    FilterBypass fb,
                                    int offset,
                                    String string,
                                    AttributeSet attr)
                                    throws BadLocationException {

                                substituir(
                                        fb,
                                        offset,
                                        0,
                                        string,
                                        attr);
                            }

                            @Override
                            public void replace(
                                    FilterBypass fb,
                                    int offset,
                                    int length,
                                    String text,
                                    AttributeSet attrs)
                                    throws BadLocationException {

                                substituir(
                                        fb,
                                        offset,
                                        length,
                                        text,
                                        attrs);
                            }

                            private void substituir(
                                    FilterBypass fb,
                                    int offset,
                                    int length,
                                    String text,
                                    AttributeSet attrs)
                                    throws BadLocationException {

                                String atual = fb.getDocument()
                                        .getText(
                                                0,
                                                fb.getDocument()
                                                        .getLength());

                                String novo = atual.substring(
                                        0,
                                        offset)
                                        + (text == null
                                                ? ""
                                                : text)
                                        + atual.substring(
                                                offset + length);

                                String numeros = novo.replaceAll(
                                        "\\D",
                                        "");

                                if (numeros.length() > 8) {

                                    numeros = numeros.substring(
                                            0,
                                            8);
                                }

                                String formatado = formatarData(
                                        numeros);

                                fb.replace(
                                        0,
                                        fb.getDocument()
                                                .getLength(),
                                        formatado,
                                        attrs);
                            }
                        });
    }

    // =========================================================
    // FORMATAR DATA
    // =========================================================

    private String formatarData(
            String valor) {

        if (valor == null) {
            return "";
        }

        String numeros = valor.replaceAll(
                "\\D",
                "");

        if (numeros.length() > 8) {

            numeros = numeros.substring(
                    0,
                    8);
        }

        if (numeros.length() <= 2) {

            return numeros;

        } else if (numeros.length() <= 4) {

            return numeros.substring(
                    0,
                    2)
                    + "-"
                    + numeros.substring(
                            2);

        } else {

            return numeros.substring(
                    0,
                    2)
                    + "-"
                    + numeros.substring(
                            2,
                            4)
                    + "-"
                    + numeros.substring(
                            4);
        }
    }

    // =========================================================
    // MÁSCARA HORA
    // =========================================================

    private void aplicarMascaraHora(
            JTextField campo) {

        ((AbstractDocument) campo.getDocument())
                .setDocumentFilter(
                        new DocumentFilter() {

                            @Override
                            public void insertString(
                                    FilterBypass fb,
                                    int offset,
                                    String string,
                                    AttributeSet attr)
                                    throws BadLocationException {

                                substituir(
                                        fb,
                                        offset,
                                        0,
                                        string,
                                        attr);
                            }

                            @Override
                            public void replace(
                                    FilterBypass fb,
                                    int offset,
                                    int length,
                                    String text,
                                    AttributeSet attrs)
                                    throws BadLocationException {

                                substituir(
                                        fb,
                                        offset,
                                        length,
                                        text,
                                        attrs);
                            }

                            private void substituir(
                                    FilterBypass fb,
                                    int offset,
                                    int length,
                                    String text,
                                    AttributeSet attrs)
                                    throws BadLocationException {

                                String atual = fb.getDocument()
                                        .getText(
                                                0,
                                                fb.getDocument()
                                                        .getLength());

                                String novo = atual.substring(
                                        0,
                                        offset)
                                        + (text == null
                                                ? ""
                                                : text)
                                        + atual.substring(
                                                offset + length);

                                String numeros = novo.replaceAll(
                                        "\\D",
                                        "");

                                if (numeros.length() > 4) {

                                    numeros = numeros.substring(
                                            0,
                                            4);
                                }

                                String formatado = formatarHora(
                                        numeros);

                                fb.replace(
                                        0,
                                        fb.getDocument()
                                                .getLength(),
                                        formatado,
                                        attrs);
                            }
                        });
    }

    // =========================================================
    // FORMATAR HORA
    // =========================================================

    private String formatarHora(
            String valor) {

        if (valor == null) {
            return "";
        }

        String numeros = valor.replaceAll(
                "\\D",
                "");

        if (numeros.length() > 4) {

            numeros = numeros.substring(
                    0,
                    4);
        }

        if (numeros.length() <= 2) {

            return numeros;

        } else {

            return numeros.substring(
                    0,
                    2)
                    + ":"
                    + numeros.substring(
                            2);
        }
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