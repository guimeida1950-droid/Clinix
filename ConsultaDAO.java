import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultaDAO {

    public void adicionar(Consulta consulta) throws SQLException {

        String sql = """
                INSERT INTO consultas
                (paciente_id, data_consulta, hora_consulta, medico, especialidade, observacoes)
                VALUES (?, ?, ?, ?, ?, ?)
                """;

        try (Connection conexao = Conexao.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getPacienteId());
            stmt.setDate(2, Date.valueOf(consulta.getDataConsulta()));
            stmt.setTime(3, Time.valueOf(consulta.getHoraConsulta()));
            stmt.setString(4, consulta.getMedico());
            stmt.setString(5, consulta.getEspecialidade());
            stmt.setString(6, consulta.getObservacoes());

            stmt.executeUpdate();
        }
    }

    public List<Consulta> listar() throws SQLException {

        List<Consulta> lista = new ArrayList<>();

        String sql = """
                SELECT
                    c.id,
                    c.paciente_id,
                    p.nome AS paciente_nome,
                    c.data_consulta,
                    c.hora_consulta,
                    c.medico,
                    c.especialidade,
                    c.observacoes
                FROM consultas c
                INNER JOIN pacientes p
                    ON c.paciente_id = p.id
                ORDER BY c.data_consulta, c.hora_consulta
                """;

        try (Connection conexao = Conexao.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {

                Consulta consulta = new Consulta();

                consulta.setId(rs.getInt("id"));
                consulta.setPacienteId(rs.getInt("paciente_id"));
                consulta.setPacienteNome(rs.getString("paciente_nome"));
                consulta.setDataConsulta(
                        rs.getDate("data_consulta").toString());
                consulta.setHoraConsulta(
                        rs.getTime("hora_consulta").toString());
                consulta.setMedico(rs.getString("medico"));
                consulta.setEspecialidade(rs.getString("especialidade"));
                consulta.setObservacoes(rs.getString("observacoes"));

                lista.add(consulta);
            }
        }

        return lista;
    }

    public void atualizar(Consulta consulta) throws SQLException {

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

        try (Connection conexao = Conexao.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, consulta.getPacienteId());
            stmt.setDate(2, Date.valueOf(consulta.getDataConsulta()));
            stmt.setTime(3, Time.valueOf(consulta.getHoraConsulta()));
            stmt.setString(4, consulta.getMedico());
            stmt.setString(5, consulta.getEspecialidade());
            stmt.setString(6, consulta.getObservacoes());
            stmt.setInt(7, consulta.getId());

            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {

        String sql = "DELETE FROM consultas WHERE id = ?";

        try (Connection conexao = Conexao.conectar();
                PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}