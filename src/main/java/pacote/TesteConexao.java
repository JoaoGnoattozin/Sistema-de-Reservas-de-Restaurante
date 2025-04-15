package pacote;
import java.sql.Connection;
public  class TesteConexao
{
	public static void main(String[] args) {
		Connection conn = Conexao.conectar();
		if (conn != null)
		{
			System.out.println("Conexao realizada com sucesso!");
		}
		else {
			System.out.println("Falha na conexao");
		}
	}
}