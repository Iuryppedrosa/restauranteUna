package conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao
{
    private static Connection conexao = null;

    private Conexao()
    {
        try {
            conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/restauranteuna?useSSL=false", "root", "Francinete1209");
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Ocorreu um erro na conexao com o banco de dados!");
        }
    }

    public static Connection getInstance()
    {
        if(conexao == null)
        {
            new Conexao();
        }
        return conexao;
    }

    public static void main(String[] args)
    {
        Connection instance = getInstance();
        System.out.println(instance);
    }
}
