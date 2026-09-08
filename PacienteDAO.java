import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PacienteDAO {

    public void cadastrar(Paciente paciente) {

        String sql = """
                INSERT INTO pacientes
                (nome, cpf, telefone, email, data_nascimento)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conexao = Conexao.conectar();
                PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setString(1, paciente.getNome());
            comando.setString(2, paciente.getCpf());
            comando.setString(3, paciente.getTelefone());
            comando.setString(4, paciente.getEmail());
            comando.setString(5, paciente.getDataNascimento());

            comando.executeUpdate();

            System.out.println("Paciente cadastrado com sucesso!");

        } catch (SQLException e) {

            System.out.println("Erro ao cadastrar paciente:");
            System.out.println(e.getMessage());
        }
    }
}