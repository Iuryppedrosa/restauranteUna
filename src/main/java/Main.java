import java.util.ArrayList;
import java.util.Scanner;

public class Main
{
    private static final ArrayList<Garcom> arlGarcom = new ArrayList<>();
    private static final ArrayList<Mesa> arlMesa = new ArrayList<>();
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args)
    {
        Main.menuInicial();
        int opcao = input.nextInt();
        do
        {
            switch (opcao)
            {
                case 1:
                    menuGarcom();
                    int opcao1 = input.nextInt();
                    switch (opcao1)
                    {
                        case 1:
                            cadastrarGarcom();//FALTA TELEFONE
                            break;
                        case 2:
                            removerGarcom();
                            break;
                        case 3:
                            buscarGarcomEmail();
                            break;
                        case 4:
                            atribuirGarcomUmaMesa();
                            //buscarGarcomNumero();//nao tem necessidade
                            break;
                        case 5:
                            relatorioGarcom();
                            break;
                        case 6:
                            break;
                        case 0:
                            System.out.println("Agradecemos por tentar cadastrar uma mesa, obrigado!!!");
                            return;
                        default:
                            System.out.println("Opcao invalida, Digite outra opcao");
                    }
                    break;
                case 2:
                    menuMesa();
                    int opcao2 = input.nextInt();
                    {
                        switch (opcao2)
                        {
                            case 1:
                                cadastrarMesa();//feito
                                break;
                            case 2:
                                removerMesa();///feito
                                break;
                            case 3:
                                relatorioMesa();// feito
                                break;
                            case 4:
                                /*System.out.print("Digite o numero da mesa que deseja buscar: ");
                                int numeroMesa = input.nextInt();*/
                                buscarMesaPorNumero(/*numeroMesa*/);//feito
                                break;
                            case 5:
                                buscarMesaPorCapacidadeDeClientes();//feito
                                break;
                            case 6:
                                buscarMesasEGarconsResponsaveis();
                                break;
                            case 7:
                                break;
                            case 0:
                                System.out.println("Agradecemos por tentar cadastrar uma mesa, obrigado!!!");
                                return;
                            default:
                                System.out.println("Opcao invalida, Digite outra opcao");
                        }
                    }
                    break;
                case 0:
                    System.out.println("Agradecemos por utilizar o sistema!");
                    return;
                default:
                    System.out.println("Opcao Invalida.");
            }
            menuInicial();
            opcao = input.nextInt();
    } while (opcao != 0);
}

    private static void atribuirGarcomUmaMesa()
    {
        relatorioGarcom();

        System.out.println("\nQual o nome do garcom que vc deseja atribuir uma mesa: ");
        String nomeGarcom = input.next();

        Garcom garcom = new Garcom();

        try
        {
            for (int i = 0; i < arlGarcom.size(); i++)
            {
                Garcom garcomNomeNaLista = arlGarcom.get(i);

                if(garcomNomeNaLista.getNome().equalsIgnoreCase(nomeGarcom))
                {
                    relatorioMesa();

                    System.out.println("\nQual o numero da mesa vc deseja atribuir este garcom: ");
                    int numeroDaMesa = input.nextInt();

                    Mesa mesaAtribuicao = new Mesa();

                    for (int j = 0; j < arlMesa.size(); j++)
                    {
                        Mesa mesasAtuaisParaAtribuicao = arlMesa.get(j);

                        garcom.setArlMesasNoGarcom(arlMesa);
                        mesaAtribuicao.setGarcomDaMesa(garcom);
                    }
                }
            }
            System.out.println("GARCOM ATRIBUIDO!! ");
        }
        catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao atribuir garcom!");
        }

    }

    private static void buscarGarcomEmail()
    {
        System.out.print("Qual o e-mail do garcom que deseja buscar: ");
        String emailGarcomBusca = input.next();
        
        Garcom garcomEmail = null;
        try
        {
            for (int i = 0; i < arlGarcom.size(); i++)
            {
                Garcom emailAtualGarcom = arlGarcom.get(i);

                if(emailAtualGarcom.getEmail().equals(emailGarcomBusca))
                {
                    garcomEmail = arlGarcom.get(i);
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar garcom pelo email!");
        }

        if(garcomEmail == null)
        {
            System.out.println("Nao foi encontrado garcom com este email.");
        }
        else
        {
            System.out.println("Nome: " + garcomEmail.getNome());
            System.out.println("Cpf: " + garcomEmail.getCpf());
            System.out.println("Data de nascimento: " + garcomEmail.getDataNascimento());
            System.out.println("Email: " + garcomEmail.getEmail());
            System.out.println("Sexo: " + garcomEmail.getSexo());
            System.out.printf("Salario: R$ %.2f ", garcomEmail.getSalarioFixo());
        }
    }

    private static void relatorioGarcom()
    {
        ArrayList<Garcom> arraysGarcons = buscarRelatorioGarcom();

        arraysGarcons.stream().forEach(garcom ->
        {
            System.out.println("Nome: " + garcom.getNome());
            System.out.println("Cpf: " + garcom.getCpf());
            System.out.println("Data de nascimento: " + garcom.getDataNascimento());
            System.out.println("Email: " + garcom.getEmail());
            System.out.println("Sexo: " + garcom.getSexo());
            System.out.printf("Salario: R$ %.3f ", garcom.getSalarioFixo());

        });
    }

    private static ArrayList<Garcom> buscarRelatorioGarcom()
    {
        return arlGarcom;
    }

    private static void removerGarcom()
    {
        System.out.print("Digite o nome do garcom que deseja remover: ");
        String nomeRemocao = input.next();
        try
        {
            for (int i = 0; i < arlGarcom.size(); i++)
            {
                Garcom garcomAtual = arlGarcom.get(i);

                if(garcomAtual.getNome().equalsIgnoreCase(nomeRemocao))
                {
                    arlGarcom.remove(i);
                }

            }
        } catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao remover garcom.");
        }
    }

    private static void cadastrarGarcom()
    {
        /*System.out.print("Qual mesa este garcom será atribuido: ");
        int numeroMesa = input.nextInt();*/

        //Mesa numeroMesaEncontrado = buscarMesaPorNumero(numeroMesa);

        System.out.print("Digite o nome do garcom: ");
        String nome = input.next();

        System.out.print("Qual o cpf do garcom: ");
        String cpf = input.next();

        System.out.print("Qual a data de nascimento do garcom: ");
        String dataNascimento = input.next();

        System.out.print("Qual o e-mail deste garcom: ");
        String email = input.next();

        System.out.print("Qual o sexo do garcom: ");
        String sexo = input.next();
        char sexoResultado = sexo.toLowerCase().charAt(0);

        System.out.print("Qual o salario para esse garcom: ");
        double salario = input.nextDouble();

        try
        {
            Garcom garcom = new Garcom(nome, cpf, dataNascimento, email, sexoResultado, salario/*, numeroMesaEncontrado*/);
            arlGarcom.add(garcom);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar garcom.");
        }
    }

    private static void buscarMesasEGarconsResponsaveis()
    {
    }

    private static void relatorioMesa()
    {
        ArrayList<Mesa> arrayMesas = buscarRelatorioMesas();

        arrayMesas.forEach(mesas ->
                {
                    System.out.print("Numero da mesa: "+ mesas.getNumeroMesa());
                    System.out.print("\nCapacidade mesa: " + mesas.getCapacidadeMesa());
                    System.out.print("\nOcupacao da mesa ");
                    String situacaoMesa = mesas.getOcupacaoMesa();

                    int situacao = Integer.parseInt(situacaoMesa);
                    if(situacao == 1)
                        System.out.print("OCUPADA.");
                    else if (situacao == 2)
                        System.out.print("LIVRE");
                    else
                    {
                        System.out.print("RESERVADA.");
                    }
                    try
                    {

                        System.out.println("Garcom da mesa: " + mesas.getGarcomDaMesa().getNome());
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                        System.out.println("/nNao ha garcons para essa mesa");
                    }
                }
                );
    }
    private static ArrayList<Mesa> buscarRelatorioMesas()
    {
        return arlMesa;
    }
    private static void buscarMesaPorCapacidadeDeClientes()
    {
        System.out.print("Voce deseja buscar uma mesa com a capacidade de clientes maior ou igual a: ");
        int capacidadeBusca = input.nextInt();

        Mesa capacidadeDeBusca = null;
        try
        {
            for (int i = 0; i < arlMesa.size(); i++)
            {
                Mesa capacidadeAtuaisNaMesa = arlMesa.get(i);

                if (capacidadeAtuaisNaMesa.getCapacidadeMesa() >= capacidadeBusca)
                {
                    capacidadeDeBusca = arlMesa.get(i);

                    System.out.println("Numero da mesa: " + capacidadeDeBusca.getNumeroMesa());
                    System.out.println("Capacidade mesa: " + capacidadeDeBusca.getCapacidadeMesa());
                    System.out.println("Ocupacao mesa: " + capacidadeDeBusca.getOcupacaoMesa());
                }
            }
            System.out.println("\n\n >> Nao foi encontrado mesa com essa capacidade.");

        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar mesa pela definiçao de capacidade!");
        }
    }
    private static void buscarMesaPorNumero(/*int numeroMesa*/)
    {
        System.out.print("Qual mesa este garcom será atribuido: ");
        int numeroMesa = input.nextInt();

        Mesa numeroMesaEncontrado = null;
        try
        {
            for (int i = 0; i < arlMesa.size(); i++)
            {
                Mesa numeroAtualMesa = arlMesa.get(i);

                if(numeroAtualMesa.getNumeroMesa() == numeroMesa)
                {
                    numeroMesaEncontrado = arlMesa.get(i);
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar mesa pelo numero!");
        }

        if(numeroMesaEncontrado == null)
            System.out.println("Nao foi encontrado mesa com esse numero.");
        else
        {
            System.out.println("Numero da mesa: " + numeroMesaEncontrado.getNumeroMesa());
            System.out.println("Capacidade mesa: " + numeroMesaEncontrado.getCapacidadeMesa());
            System.out.println("Ocupacao mesa: " + numeroMesaEncontrado.getOcupacaoMesa());

        }
        //return numeroMesaEncontrado;
    }

    private static void removerMesa()
    {
        System.out.println("Digite o codigo da mesa que deseja removar do restaurante.");
        int numeroMesa = input.nextInt();
        try
        {
            for (int i = 0; i < arlMesa.size(); i++)
            {
                Mesa mesaAtual = arlMesa.get(i);

                if(mesaAtual.getNumeroMesa() == numeroMesa)
                {
                    arlMesa.remove(i);
                }
            }

        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao remover a mesa!");
        }
    }

    private static void cadastrarMesa()
    {
        System.out.print("Qual o numero da mesa: ");
        int numeroMesa = input.nextInt();

        System.out.print("Qual a capacidade para a mesa: ");
        int capacidadeMesa = input.nextInt();

        System.out.print("""
                         Qual ocupacao atual da mesa: 
                         1. Ocupada.
                         2. Livre.
                         3. Reservada.
                         """);
        String ocupacaoMesa = input.next();

        try
        {
            Mesa mesa = new Mesa(numeroMesa, capacidadeMesa, ocupacaoMesa, null);
            mesa.setGarcomDaMesa();
            arlMesa.add(mesa);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar mesa!!");
        }
    }

    public static void menuGarcom()
    {
        System.out.println("""
              
                1. Cadastrar garçom.
                2. Remover garçom.
                3. Buscar garçons por e-mail.             
                4. Buscar garçons pelo numero.
                5. Relatorio Garçons cadastrados.
                6. Voltar ao menu inicial.
                0. Sair.
                (selecione uma opcao abaixo)
                """);
    }

    public static void menuMesa()
    {
        System.out.println("""
                1. Cadastro de mesa.
                2. Remoção de mesa.
                3. Relatorio mesas.
                4. Busca mesa pelo número.
                5. Busca mesa pela capacidade de clientes.
                6. Mesas e garcons responsáveis.
                7. Voltar ao menu inicial.
                0. Sair.
                (selecione uma opcao abaixo)
                """);
    }

    public static void menuInicial()
    {
        System.out.println("""
                
                               
                ======== Bem vindo ao Sistema de Cadastro e Gerenciamento de Mesas e Garçons. ======
                
                1. Opcoes sobre garcom.
                2. Opcoes sobre mesa.
                0. Sair
                (Selecione o menu abaixo)
                """);
    }
}
