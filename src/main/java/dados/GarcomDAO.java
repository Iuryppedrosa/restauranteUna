package dados;
import beans.Garcom;
import beans.Mesa;
import conexao.Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class GarcomDAO
{
    public static void cadastrar(Garcom garcom) throws SQLException
    {
        try
        {
            Connection conexaoRecebida = Conexao.getInstance();

            String sql = """
                    INSERT INTO garcom (nome, cpf, dataNascimento, email, sexo, salarioFixo, telefone)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                    """;

            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            stmt.setString(1, garcom.getNome());
            stmt.setString(2, garcom.getCpf());
            stmt.setString(3, garcom.getDataNascimento());
            stmt.setString(4, garcom.getEmail());
            stmt.setString(5, String.valueOf(garcom.getSexo()));
            stmt.setDouble(6, garcom.getSalarioFixo());
            stmt.setString(7, garcom.getTelefone());

            stmt.execute();
            stmt.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar garcom!!!");
        }
    }

    public static Garcom bucarPeloCpf(String cpf) throws SQLException
    {
        Garcom garcom = null;

        try
        {
            Connection conexaoRecebida = Conexao.getInstance();

            String sql = """
                    SELECT * FROM garcom WHERE cpf = ?
                    """;

            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            stmt.setString(1, cpf);

            ResultSet rs = stmt.executeQuery();

            boolean retorno = rs.next();

            if(retorno == true)
            {
                String cpfGarcom = rs.getString("cpf");

                garcom = new Garcom();
                garcom.setCpf(cpfGarcom);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar garcom pelo cpf");
        }

        return garcom;
    }

    public static void remover(String cpf) throws SQLException
    {
        try {
            Connection conexaoRecebida = Conexao.getInstance();

            String sql = """
                    DELETE FROM garcom WHERE cpf = ?
                    """;
            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            stmt.setString(1, cpf);

            stmt.execute();
            stmt.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erro ao remover garçom!");
        }
    }

    public static Garcom buscaGarcomPeloEmail(String nomeEmail) throws SQLException
    {
        Garcom garcom = new Garcom();

        try {
            Connection conexaoRecebida = Conexao.getInstance();

            String sql = """
                    SELECT * FROM garcom WHERE email LIKE ?
                    """;

            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            stmt.setString(1, nomeEmail);

            ResultSet rs = stmt.executeQuery();

            boolean retorno = rs.next();

            if(retorno == true)
            {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String dataDeNascimento = rs.getString("dataNascimento");
                String email = rs.getString("email");
                //char sexo = rs.getNCharacterStream("sexo");
                double salarioFixo = rs.getDouble("salarioFixo");
                String telefone = rs.getString("telefone");


                garcom.setNome(nome);
                garcom.setCpf(cpf);
                garcom.setDataNascimento(dataDeNascimento);
                garcom.setEmail(email);
                garcom.setSalarioFixo(salarioFixo);
                garcom.setTelefone(telefone);
                //garcom = new Garcom(nome, cpf, dataDeNascimento, email, null, salarioFixo, telefone);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar garcom pelo email");
        }
        return garcom;
    }

    public static ArrayList<Garcom> buscarTodosGarcons() throws SQLException
    {
        ArrayList<Garcom> vetGarcom = new ArrayList<>();

        try {
            Connection conexaoRecebida = Conexao.getInstance();
            String sql = """
                    SELECT * FROM garcom
                    """;
            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next() == true)
            {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String dataDeNascimento = rs.getString("dataNascimento");
                String email = rs.getString("email");
                //char sexo = rs.getNCharacterStream("sexo");
                double salarioFixo = rs.getDouble("salarioFixo");
                String telefone = rs.getString("telefone");

                Garcom garcom = new Garcom();

                garcom.setNome(nome);
                garcom.setCpf(cpf);
                garcom.setDataNascimento(dataDeNascimento);
                garcom.setEmail(email);
                garcom.setSalarioFixo(salarioFixo);
                garcom.setTelefone(telefone);

                vetGarcom.add(garcom);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar garçons!");
        }
        return vetGarcom;
    }
//    public static Garcom atribuirGarcomMesa(String cpf  )
//    {
//
//    }
}
