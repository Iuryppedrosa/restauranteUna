import java.util.ArrayList;

public class Garcom
{
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String email;
    private char sexo;
    private double salarioFixo;

    private ArrayList<Mesa> arlMesasNoGarcom;

    //private Mesa mesaAtribuida;

    public Garcom(String nome, String cpf, String dataNascimento, String email,
                  char sexo, double salarioFixo/*Mesa arlMesasNoGarcom*/)
    {
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.email = email;
        this.sexo = sexo;
        this.salarioFixo = salarioFixo;
        //this.arlMesasNoGarcom = arlMesasNoGarcom;
    }

    public Garcom()
    {

    }

    public ArrayList<Mesa> getArlMesasNoGarcom()
    {
        return arlMesasNoGarcom;
    }

    public void setArlMesasNoGarcom(ArrayList<Mesa> arlMesasNoGarcom)
    {
        this.arlMesasNoGarcom = arlMesasNoGarcom;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public String getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public char getSexo()
    {
        return sexo;
    }

    public void setSexo(char sexo)
    {
        this.sexo = sexo;
    }

    public double getSalarioFixo()
    {
        return salarioFixo;
    }

    public void setSalarioFixo(double salarioFixo)
    {
        this.salarioFixo = salarioFixo;
    }
}
