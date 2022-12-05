package dados;
import beans.Garcom;
import beans.Mesa;
import conexao.Conexao;
import java.sql.*;
import java.util.ArrayList;

public class MesaDao
{
    public static void cadastrarMesa(Mesa mesa) throws SQLException
    {
        try
        {
            Connection conexaoRecebida = Conexao.getInstance();
            String sql = """
                    INSERT INTO mesa (numeroMesa, capacidadeMesa, ocupacaoMesa, garcomDaMesa)
                    VALUES (?, ?, ?, ?)
                    """;

            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            stmt.setInt(1, mesa.getNumeroMesa());
            stmt.setInt(2, mesa.getCapacidadeMesa());
            stmt.setString(3, mesa.getOcupacaoMesa());
            stmt.setString(4, null);

            stmt.execute();
            stmt.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar mesa!!");
        }
    }
    public static void removerMesa(int numeroMesa) throws SQLException
    {
        try
        {
            Connection conexaoRecebida = Conexao.getInstance();

            String sql = """
                    DELETE FROM mesa WHERE numeroMesa = ?
                    """;

            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            stmt.setInt(1, numeroMesa);

            stmt.execute();
            stmt.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erro ao remover mesa.");
        }
    }
    public static ArrayList<Mesa> buscarMesas() throws SQLException
    {
        ArrayList<Mesa> mesas = new ArrayList<>();
        try
        {
            Connection conexaoRecebida = Conexao.getInstance();
            String sql = """
                    SELECT * FROM mesa
                    """;


            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();


            while (rs.next() == true)
            {
                int numeroMesa = rs.getInt("numeroMesa");
                int capacidadeMesa = rs.getInt("capacidadeMesa");
                String ocupacaoMesa = rs.getString("ocupacaoMesa");
                String garcomDaMesa = rs.getString("garcomDaMesa");
                Garcom garcomMesa = GarcomDAO.bucarPeloCpf(garcomDaMesa);


                Mesa mesa = new Mesa(numeroMesa,capacidadeMesa,ocupacaoMesa, null);
                //mesas.add(mesa);
                mesa.setGarcomDaMesa(garcomMesa);
                mesas.add(mesa);
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar mesas!!");
        }
        return mesas;
    }

    public static Mesa buscaMesaPeloCodigo(int codigoMesa) throws SQLException
    {
        Mesa mesa = new Mesa();

        try
        {
            Connection conexaoRecebida = Conexao.getInstance();

            String sql = """
                    SELECT * FROM mesa WHERE numeroMesa = ? 
                    """;

            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            stmt.setInt(1, codigoMesa);

            ResultSet rs = stmt.executeQuery();

            boolean retorno = rs.next();

            if(retorno == true)
            {
                int numeroMesa = rs.getInt("numeroMesa");
                int capacidadeMesa = rs.getInt("capacidadeMesa");
                String ocupacaoMesa = rs.getString("ocupacaoMesa");
                String garcomDaMesa = rs.getString("garcomDaMesa");

                mesa.setNumeroMesa(numeroMesa);
                mesa.setCapacidadeMesa(capacidadeMesa);
                mesa.setOcupacaoMesa(ocupacaoMesa);
                mesa.setGarcomDaMesa(null);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar mesa pelo numero.");
        }
        return mesa;
    }

    public static ArrayList<Mesa> buscarMesaLivreEcomGarcom() throws SQLException
    {
        ArrayList<Mesa> mesaARL = new ArrayList<>();

        try
        {
            Connection conexaoRecebida = Conexao.getInstance();

            String sql = """
                    SELECT * FROM mesa WHERE ocupacaoMesa = 2 and GarcomDaMesa IS NOT NULL
                    """;

            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next() == true)
            {
                int numeroMesa = rs.getInt("numeroMesa");
                String ocupacaoMesa = rs.getString("ocupacaoMesa");
                String garcomDaMesa = rs.getString("garcomDaMesa");
                Garcom garcomMesa = GarcomDAO.bucarPeloCpf(garcomDaMesa);

                Mesa mesa = new Mesa();
                mesa.setNumeroMesa(numeroMesa);
                mesa.setOcupacaoMesa(ocupacaoMesa);
                mesa.setGarcomDaMesa(garcomMesa);

                mesaARL.add(mesa);

            }
        }catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar mesa!!");
        }

        return mesaARL;
    }

    public static ArrayList<Mesa> buscarMesaPeloCapacidadeDeClientes(int capacidadeMesaBusca) throws SQLException
    {
        ArrayList<Mesa> mesaARL = new ArrayList<>();

        try
        {
            Connection conexaoRecebida = Conexao.getInstance();

            String sql = """
                    SELECT * FROM mesa WHERE capacidadeMesa >= ?
                    """;

            PreparedStatement stmt = conexaoRecebida.prepareStatement(sql);

            stmt.setInt(1, capacidadeMesaBusca);

            ResultSet rs = stmt.executeQuery();

            while (rs.next() == true)
            {
                int numeroMesa = rs.getInt("numeroMesa");
                int capacidadeMesa = rs.getInt("capacidadeMesa");
                String ocupacaoMesa = rs.getString("ocupacaoMesa");
                String garcomDaMesa = rs.getString("garcomDaMesa");

                Mesa mesa = new Mesa(numeroMesa, capacidadeMesa, ocupacaoMesa, null);

                mesaARL.add(mesa);
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Nao foi possivel buscar a mesa pela capacidade.");
        }
        return mesaARL;
    }

    public static void definirGarcomAUmaMesa(String cpf, int numeroMesa) throws SQLException
    {
        try
        {
            Connection conexao = Conexao.getInstance();

            String sql = """
                    UPDATE mesa set garcomDaMesa = ? WHERE numeroMesa = ?
                    """;

            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setInt(2, numeroMesa);


            stmt.execute();
            stmt.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Nao foi possivel incluir esse garcom a mesa.");
            throw new RuntimeException(e);
        }
    }
}
