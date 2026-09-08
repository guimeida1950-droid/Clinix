
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

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Pacientes extends JPanel {

    // =========================================================
    // CORES
    // =========================================================

    private static final Color FUNDO = new Color(245, 246, 250);
    private static final Color ROXO = new Color(104, 67, 180);
    private static final Color ROXO_ESCURO = new Color(82, 51, 145);
    private static final Color BRANCO = Color.WHITE;
    private static final Color TEXTO = new Color(45, 45, 50);
    private static final Color CINZA = new Color(120, 120, 125);
    private static final Color BORDA = new Color(220, 220, 225);
    private static final Color VERMELHO = new Color(190, 50, 50);

    // =========================================================
    // CAMPOS
    // =========================================================

    private JTextField campoNome;
    private JTextField campoCPF;
    private JTextField campoTelefone;
    private JTextField campoEmail;
    private JTextField campoNascimento;

    private JTable tabela;
    private DefaultTableModel modeloTabela;

    // BOTÃO SALVAR COMO CAMPO
    private JButton botaoSalvar;

    // ID do paciente que está sendo editado
    private int idPacienteEditando = -1;

    // =========================================================
    // CONSTRUTOR
    // =========================================================

    public Pacientes() {

        setLayout(new BorderLayout());
        setBackground(FUNDO);

        criarInterface();

        carregarPacientes();
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
                        25, 30, 15, 30));

        JLabel titulo = new JLabel("Pacientes");

        titulo.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        28));

        titulo.setForeground(TEXTO);

        JLabel subtitulo = new JLabel(
                "Cadastre, edite e gerencie os pacientes.");

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
                        0, 30, 30, 30));

        // -----------------------------------------------------
        // FORMULÁRIO
        // -----------------------------------------------------

        JPanel painelFormulario = new JPanel(
                new GridBagLayout());

        painelFormulario.setBackground(BRANCO);

        painelFormulario.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDA),
                        BorderFactory.createEmptyBorder(
                                20, 20, 20, 20)));

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(8, 8, 8, 8);

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        // -----------------------------------------------------
        // NOME
        // -----------------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel labelNome = criarLabel("Nome completo");

        painelFormulario.add(
                labelNome,
                gbc);

        campoNome = criarCampo();

        gbc.gridy = 1;

        painelFormulario.add(
                campoNome,
                gbc);

        // -----------------------------------------------------
        // CPF
        // -----------------------------------------------------

        gbc.gridy = 2;

        JLabel labelCPF = criarLabel("CPF");

        painelFormulario.add(
                labelCPF,
                gbc);

        campoCPF = criarCampo();

        aplicarMascaraCPF(campoCPF);

        gbc.gridy = 3;

        painelFormulario.add(
                campoCPF,
                gbc);

        // -----------------------------------------------------
        // TELEFONE
        // -----------------------------------------------------

        gbc.gridy = 4;

        JLabel labelTelefone = criarLabel("Telefone");

        painelFormulario.add(
                labelTelefone,
                gbc);

        campoTelefone = criarCampo();

        aplicarMascaraTelefone(
                campoTelefone);

        gbc.gridy = 5;

        painelFormulario.add(
                campoTelefone,
                gbc);

        // -----------------------------------------------------
        // EMAIL
        // -----------------------------------------------------

        gbc.gridy = 6;

        JLabel labelEmail = criarLabel("E-mail");

        painelFormulario.add(
                labelEmail,
                gbc);

        campoEmail = criarCampo();

        gbc.gridy = 7;

        painelFormulario.add(
                campoEmail,
                gbc);

        // -----------------------------------------------------
        // NASCIMENTO
        // -----------------------------------------------------

        gbc.gridy = 8;

        JLabel labelNascimento = criarLabel("Data de nascimento");

        painelFormulario.add(
                labelNascimento,
                gbc);

        campoNascimento = criarCampo();

        aplicarMascaraData(
                campoNascimento);

        gbc.gridy = 9;

        painelFormulario.add(
                campoNascimento,
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

        botaoSalvar = criarBotao("CADASTRAR");

        botaoSalvar.addActionListener(
                e -> salvarPaciente());

        JButton botaoNovo = criarBotaoSecundario(
                "NOVO PACIENTE");

        botaoNovo.addActionListener(
                e -> limparFormulario());

        painelBotoes.add(
                botaoNovo);

        painelBotoes.add(
                botaoSalvar);

        gbc.gridy = 10;

        gbc.insets = new Insets(
                20, 8, 8, 8);

        painelFormulario.add(
                painelBotoes,
                gbc);

        // -----------------------------------------------------
        // TABELA
        // -----------------------------------------------------

        JPanel painelTabela = new JPanel(
                new BorderLayout(0, 10));

        painelTabela.setBackground(
                BRANCO);

        painelTabela.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDA),
                        BorderFactory.createEmptyBorder(
                                15, 15, 15, 15)));

        JLabel tituloTabela = new JLabel(
                "Pacientes cadastrados");

        tituloTabela.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        18));

        tituloTabela.setForeground(
                TEXTO);

        painelTabela.add(
                tituloTabela,
                BorderLayout.NORTH);

        // -----------------------------------------------------
        // MODELO DA TABELA
        // -----------------------------------------------------

        modeloTabela = new DefaultTableModel(
                new Object[] {
                        "ID",
                        "Nome",
                        "CPF",
                        "Telefone",
                        "E-mail",
                        "Nascimento"
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

        tabela.setForeground(
                TEXTO);

        tabela.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION);

        tabela.setShowVerticalLines(
                false);

        tabela.setShowHorizontalLines(
                true);

        tabela.setGridColor(
                new Color(235, 235, 240));

        JTableHeader cabecalho = tabela.getTableHeader();

        cabecalho.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13));

        cabecalho.setBackground(
                new Color(240, 240, 245));

        cabecalho.setForeground(
                TEXTO);

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
        // BOTÕES DA TABELA
        // -----------------------------------------------------

        JPanel painelAcoes = new JPanel(
                new FlowLayout(
                        FlowLayout.RIGHT,
                        10,
                        0));

        painelAcoes.setOpaque(false);

        JButton botaoEditar = criarBotao(
                "EDITAR");

        botaoEditar.addActionListener(
                e -> editarPaciente());

        JButton botaoExcluir = criarBotaoExcluir(
                "EXCLUIR");

        botaoExcluir.addActionListener(
                e -> excluirPaciente());

        painelAcoes.add(
                botaoEditar);

        painelAcoes.add(
                botaoExcluir);

        painelTabela.add(
                painelAcoes,
                BorderLayout.SOUTH);

        // -----------------------------------------------------
        // SELEÇÃO DA TABELA
        // -----------------------------------------------------

        tabela.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int linha = tabela.getSelectedRow();

                                if (linha >= 0) {

                                    carregarPacienteNoFormulario(
                                            linha);
                                }
                            }
                        });

        // -----------------------------------------------------
        // ADICIONAR AO PAINEL CENTRAL
        // -----------------------------------------------------

        painelCentral.add(
                painelFormulario,
                BorderLayout.WEST);

        painelCentral.add(
                painelTabela,
                BorderLayout.CENTER);

        add(
                painelCentral,
                BorderLayout.CENTER);
    }

    // =========================================================
    // CRIAR LABEL
    // =========================================================

    private JLabel criarLabel(
            String texto) {

        JLabel label = new JLabel(texto);

        label.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13));

        label.setForeground(
                TEXTO);

        return label;
    }

    // =========================================================
    // CRIAR CAMPO
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
                                5, 10, 5, 10)));

        return campo;
    }

    // =========================================================
    // CRIAR BOTÃO ROXO
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

        botao.setFocusPainted(
                false);

        botao.setBorderPainted(
                false);

        botao.setOpaque(
                true);

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

        botao.setForeground(
                TEXTO);

        botao.setBackground(
                new Color(
                        235,
                        235,
                        240));

        botao.setFocusPainted(
                false);

        botao.setBorderPainted(
                false);

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

        botao.setForeground(
                BRANCO);

        botao.setBackground(
                VERMELHO);

        botao.setFocusPainted(
                false);

        botao.setBorderPainted(
                false);

        botao.setPreferredSize(
                new Dimension(
                        100,
                        40));

        return botao;
    }

    // =========================================================
    // CADASTRAR / ATUALIZAR
    // =========================================================

    private void salvarPaciente() {

        String nome = campoNome.getText().trim();

        String cpf = campoCPF.getText().trim();

        String telefone = campoTelefone.getText().trim();

        String email = campoEmail.getText().trim();

        String nascimento = campoNascimento.getText().trim();

        // -----------------------------------------------------
        // VALIDAÇÃO
        // -----------------------------------------------------

        if (nome.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite o nome do paciente.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            campoNome.requestFocus();

            return;
        }

        String cpfNumeros = cpf.replaceAll(
                "\\D",
                "");

        if (cpfNumeros.length() != 11) {

            JOptionPane.showMessageDialog(
                    this,
                    "Digite um CPF válido.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            campoCPF.requestFocus();

            return;
        }

        // -----------------------------------------------------
        // DATA
        // -----------------------------------------------------

        String dataSQL = null;

        if (!nascimento.isEmpty()) {

            if (!nascimento.matches(
                    "\\d{2}-\\d{2}-\\d{4}")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Digite a data no formato DD-MM-AAAA.",
                        "Data inválida",
                        JOptionPane.WARNING_MESSAGE);

                campoNascimento.requestFocus();

                return;
            }

            try {

                String[] partes = nascimento.split("-");

                String dia = partes[0];

                String mes = partes[1];

                String ano = partes[2];

                dataSQL = ano + "-"
                        + mes + "-"
                        + dia;

                Date.valueOf(dataSQL);

            } catch (Exception erro) {

                JOptionPane.showMessageDialog(
                        this,
                        "A data informada não é válida.",
                        "Data inválida",
                        JOptionPane.WARNING_MESSAGE);

                campoNascimento.requestFocus();

                return;
            }
        }

        // -----------------------------------------------------
        // ATUALIZAR
        // -----------------------------------------------------

        if (idPacienteEditando != -1) {

            atualizarPaciente(
                    nome,
                    cpf,
                    telefone,
                    email,
                    dataSQL);

            return;
        }

        // -----------------------------------------------------
        // CADASTRAR
        // -----------------------------------------------------

        String sql = """
                INSERT INTO pacientes
                (nome, cpf, telefone, email, data_nascimento)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(
                    1,
                    nome);

            comando.setString(
                    2,
                    cpf);

            comando.setString(
                    3,
                    telefone);

            comando.setString(
                    4,
                    email);

            if (dataSQL == null) {

                comando.setNull(
                        5,
                        java.sql.Types.DATE);

            } else {

                comando.setDate(
                        5,
                        Date.valueOf(dataSQL));
            }

            comando.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Paciente cadastrado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparFormulario();

            carregarPacientes();

        } catch (SQLException erro) {

            if (erro.getMessage()
                    .toLowerCase()
                    .contains("duplicate")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Este CPF já está cadastrado.",
                        "CPF duplicado",
                        JOptionPane.WARNING_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao cadastrar paciente:\n"
                                + erro.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // =========================================================
    // ATUALIZAR PACIENTE
    // =========================================================

    private void atualizarPaciente(
            String nome,
            String cpf,
            String telefone,
            String email,
            String dataSQL) {

        String sql = """
                UPDATE pacientes
                SET nome = ?,
                    cpf = ?,
                    telefone = ?,
                    email = ?,
                    data_nascimento = ?
                WHERE id = ?
                """;

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(
                    1,
                    nome);

            comando.setString(
                    2,
                    cpf);

            comando.setString(
                    3,
                    telefone);

            comando.setString(
                    4,
                    email);

            if (dataSQL == null) {

                comando.setNull(
                        5,
                        java.sql.Types.DATE);

            } else {

                comando.setDate(
                        5,
                        Date.valueOf(dataSQL));
            }

            comando.setInt(
                    6,
                    idPacienteEditando);

            comando.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Paciente atualizado com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparFormulario();

            carregarPacientes();

        } catch (SQLException erro) {

            if (erro.getMessage()
                    .toLowerCase()
                    .contains("duplicate")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Este CPF já pertence a outro paciente.",
                        "CPF duplicado",
                        JOptionPane.WARNING_MESSAGE);

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Erro ao atualizar paciente:\n"
                                + erro.getMessage(),
                        "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // =========================================================
    // EDITAR PACIENTE
    // =========================================================

    private void editarPaciente() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um paciente na tabela.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE);

            return;
        }

        Object valorID = modeloTabela.getValueAt(
                linha,
                0);

        idPacienteEditando = Integer.parseInt(
                valorID.toString());

        campoNome.setText(
                modeloTabela.getValueAt(
                        linha,
                        1).toString());

        campoCPF.setText(
                modeloTabela.getValueAt(
                        linha,
                        2).toString());

        campoTelefone.setText(
                modeloTabela.getValueAt(
                        linha,
                        3).toString());

        campoEmail.setText(
                modeloTabela.getValueAt(
                        linha,
                        4).toString());

        campoNascimento.setText(
                modeloTabela.getValueAt(
                        linha,
                        5).toString());

        botaoSalvar.setText(
                "SALVAR ALTERAÇÕES");

        campoNome.requestFocus();
    }

    // =========================================================
    // EXCLUIR PACIENTE
    // =========================================================

    private void excluirPaciente() {

        int linha = tabela.getSelectedRow();

        if (linha == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Selecione um paciente na tabela.",
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

        String nome = modeloTabela
                .getValueAt(
                        linha,
                        1)
                .toString();

        int resposta = JOptionPane.showConfirmDialog(
                this,
                "Deseja realmente excluir o paciente:\n\n"
                        + nome
                        + "?",
                "Confirmar exclusão",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (resposta != JOptionPane.YES_OPTION) {

            return;
        }

        String sql = "DELETE FROM pacientes WHERE id = ?";

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(
                    1,
                    id);

            comando.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Paciente excluído com sucesso!",
                    "Sucesso",
                    JOptionPane.INFORMATION_MESSAGE);

            limparFormulario();

            carregarPacientes();

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(
                    this,
                    "Erro ao excluir paciente:\n"
                            + erro.getMessage(),
                    "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // =========================================================
    // CARREGAR PACIENTES
    // =========================================================

    private void carregarPacientes() {

        modeloTabela.setRowCount(0);

        String sql = """
                SELECT id,
                       nome,
                       cpf,
                       telefone,
                       email,
                       data_nascimento
                FROM pacientes
                ORDER BY id DESC
                """;

        try (
                Connection conexao = Conexao.conectar();

                PreparedStatement comando = conexao.prepareStatement(sql);

                ResultSet resultado = comando.executeQuery()) {

            while (resultado.next()) {

                String nascimento = "";

                Date data = resultado.getDate(
                        "data_nascimento");

                if (data != null) {

                    java.time.LocalDate localDate = data.toLocalDate();

                    nascimento = String.format(
                            "%02d-%02d-%04d",
                            localDate.getDayOfMonth(),
                            localDate.getMonthValue(),
                            localDate.getYear());
                }

                String cpf = resultado.getString(
                        "cpf");

                String telefone = resultado.getString(
                        "telefone");

                // ------------------------------------------------
                // GARANTIR MÁSCARA DO CPF
                // ------------------------------------------------

                cpf = formatarCPF(cpf);

                // ------------------------------------------------
                // GARANTIR MÁSCARA DO TELEFONE
                // ------------------------------------------------

                telefone = formatarTelefone(telefone);

                modeloTabela.addRow(
                        new Object[] {
                                resultado.getInt("id"),
                                resultado.getString("nome"),
                                cpf,
                                telefone,
                                resultado.getString("email"),
                                nascimento
                        });
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
    // CARREGAR PACIENTE NO FORMULÁRIO
    // =========================================================

    private void carregarPacienteNoFormulario(
            int linha) {

        campoNome.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                1)
                        .toString());

        campoCPF.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                2)
                        .toString());

        campoTelefone.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                3)
                        .toString());

        campoEmail.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                4)
                        .toString());

        campoNascimento.setText(
                modeloTabela
                        .getValueAt(
                                linha,
                                5)
                        .toString());
    }

    // =========================================================
    // LIMPAR FORMULÁRIO
    // =========================================================

    private void limparFormulario() {

        campoNome.setText("");
        campoCPF.setText("");
        campoTelefone.setText("");
        campoEmail.setText("");
        campoNascimento.setText("");

        idPacienteEditando = -1;

        botaoSalvar.setText(
                "CADASTRAR");

        tabela.clearSelection();

        campoNome.requestFocus();
    }

    // =========================================================
    // MÁSCARA CPF
    // =========================================================

    private void aplicarMascaraCPF(
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

                                if (numeros.length() > 11) {

                                    numeros = numeros.substring(
                                            0,
                                            11);
                                }

                                String formatado = formatarCPF(
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
    // FORMATAR CPF
    // =========================================================

    private String formatarCPF(
            String valor) {

        if (valor == null) {
            return "";
        }

        String numeros = valor.replaceAll(
                "\\D",
                "");

        if (numeros.length() > 11) {

            numeros = numeros.substring(
                    0,
                    11);
        }

        if (numeros.length() <= 3) {

            return numeros;

        } else if (numeros.length() <= 6) {

            return numeros.substring(
                    0, 3)
                    + "."
                    + numeros.substring(
                            3);

        } else if (numeros.length() <= 9) {

            return numeros.substring(
                    0, 3)
                    + "."
                    + numeros.substring(
                            3, 6)
                    + "."
                    + numeros.substring(
                            6);

        } else {

            return numeros.substring(
                    0, 3)
                    + "."
                    + numeros.substring(
                            3, 6)
                    + "."
                    + numeros.substring(
                            6, 9)
                    + "-"
                    + numeros.substring(
                            9);
        }
    }

    // =========================================================
    // MÁSCARA TELEFONE
    // =========================================================

    private void aplicarMascaraTelefone(
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

                                if (numeros.length() > 11) {

                                    numeros = numeros.substring(
                                            0,
                                            11);
                                }

                                String formatado = formatarTelefone(
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
    // FORMATAR TELEFONE
    // =========================================================

    private String formatarTelefone(
            String valor) {

        if (valor == null) {
            return "";
        }

        String numeros = valor.replaceAll(
                "\\D",
                "");

        if (numeros.length() > 11) {

            numeros = numeros.substring(
                    0,
                    11);
        }

        if (numeros.length() == 0) {

            return "";

        } else if (numeros.length() <= 2) {

            return "(" + numeros;

        } else if (numeros.length() <= 7) {

            return "("
                    + numeros.substring(
                            0,
                            2)
                    + ") "
                    + numeros.substring(
                            2);

        } else {

            return "("
                    + numeros.substring(
                            0,
                            2)
                    + ") "
                    + numeros.substring(
                            2,
                            7)
                    + "-"
                    + numeros.substring(
                            7);
        }
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
}
