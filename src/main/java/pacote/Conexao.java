package pacote;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao 
{

    private static final String url  = "jdbc:postgresql://localhost:5432/postgres";
    private static final String usuario = "postgres";
    private static final String senha = "8416";

    public static Connection conectar() 
    {
        try 
        {
            return DriverManager.getConnection(url, usuario, senha);
        } catch (SQLException e) 
        
        {
            System.out.println("Erro ao conectar ao banco: " + e.getMessage());
            return null;
        }
    }
}                