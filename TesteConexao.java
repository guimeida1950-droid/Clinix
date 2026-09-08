import java.sql.Connection;

public class TesteConexao {

    public static void main(String[] args) {

        try {

            Connection conexao = Conexao.conectar();

            System.out.println("CONEXÃO REALIZADA COM SUCESSO!");

            conexao.close();

        } catch (Exception e) {

            System.out.println("NÃO FOI POSSÍVEL CONECTAR AO MYSQL.");
            e.printStackTrace();
        }
    }
}
