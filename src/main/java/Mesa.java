/*
Iury Pedrosa - 202216280
Mariana Sany Ribeiro do Nascimento - 202217151
Pedro Henrique Guimaraes Rezende - 201910463
Paulo Henrique de Faria Junior - 201912530

 */

public class Mesa
{
    private int numeroMesa;
    private int capacidadeMesa;
    private String ocupacaoMesa;
    private Garcom garcomDaMesa;

    public Mesa(int numeroMesa, int capacidadeMesa, String ocupacaoMesa, Garcom garcomDaMesa)
    {
        this.numeroMesa = numeroMesa;
        this.capacidadeMesa = capacidadeMesa;
        this.ocupacaoMesa = ocupacaoMesa;
        this.garcomDaMesa = garcomDaMesa;
    }

    public Mesa()
    {

    }

    public Garcom getGarcomDaMesa()
    {
        return garcomDaMesa;
    }

    public void setGarcomDaMesa(Garcom garcomDaMesa)
    {
        this.garcomDaMesa = garcomDaMesa;
    }

    public int getNumeroMesa()
    {
        return numeroMesa;
    }

    public void setNumeroMesa(int numeroMesa)
    {
        this.numeroMesa = numeroMesa;
    }

    public int getCapacidadeMesa()
    {
        return capacidadeMesa;
    }

    public void setCapacidadeMesa(int capacidadeMesa)
    {
        this.capacidadeMesa = capacidadeMesa;
    }

    public String getOcupacaoMesa()
    {
        return ocupacaoMesa;
    }

    public void setOcupacaoMesa(String ocupacaoMesa)
    {
        this.ocupacaoMesa = ocupacaoMesa;
    }

}
