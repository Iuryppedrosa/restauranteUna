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
                            cadastrarGarcom();
                            break;
                        case 2:
                            removerGarcom();
                            break;
                        case 3:
                            buscarGarcomEmail();
                            break;
                        case 4:
                            atribuirGarcomUmaMesa();
                            break;
                        case 5:
                            relatorioGarcom();
                            break;
                        case 6:
                            buscaMesasOcupadasComGarcon();
                            break;
                        case 7:
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
                                buscarMesaPorNumero();//feito
                                break;
                            case 5:
                                buscarMesaPorCapacidadeDeClientes();//feito
                                break;
                            case 6:
                                definirGarcomParaMesa();
                                break;
                            case 7:
                                buscarMesasLivresEGarconsDela();
                                break;
                            case 8:
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

    private static void buscaMesasOcupadasComGarcon()
    {
        System.out.println("Mesas ocupadas com garçom definido:");
        Mesa mesaOcupada;

        for (Mesa mesasOcupadasAtuais : arlMesa)
        {
            if (mesasOcupadasAtuais.getOcupacaoMesa().equals("1") && mesasOcupadasAtuais.getGarcomDaMesa() != null)
            {
                mesaOcupada = mesasOcupadasAtuais;
                System.out.println("Numero da mesa que está ocupada: " + mesaOcupada.getNumeroMesa());
                System.out.println("Nome do garçom da mesa: " + mesaOcupada.getGarcomDaMesa().getNome());
            }
            System.out.println();
        }
        System.out.println("Nao há mesas ocupadas com garçom definido.");
    }

    private static void buscarMesasLivresEGarconsDela()
    {
        System.out.println("Mesas livres: ");
        Mesa mesaLivre = null;

        for (Mesa mesasAtuais : arlMesa)
        {
            if (mesasAtuais.getOcupacaoMesa().equals("2"))
            {

                mesaLivre = mesasAtuais;
                System.out.println("Numero da mesa que está livre: " + mesaLivre.getNumeroMesa());

                if (mesaLivre.getGarcomDaMesa() != null)
                {
                    System.out.println(mesaLivre.getGarcomDaMesa().getNome());
                } else System.out.println("Nao ha garçom definido para esta mesa ainda.");
                System.out.println();
            }
        }

        if(mesaLivre == null)
        {
            System.out.println("Nao foram encontradas mesas livres.");
        }

        /*ArrayList<Garcom> garconsParaMetodoMesaLivre = buscarRelatorioGarcom();
        garconsParaMetodoMesaLivre.forEach(g ->
        {
            if(g.getArlMesasNoGarcom() != null)
            {
                System.out.println(g.getNome());
            }
            else System.out.println("Nao ha garçom definido para esta mesa ainda.");
        });*/
    }

    private static void atribuirGarcomUmaMesa()
    {
        System.out.print("\nQual o nome do garcom que vc deseja atribuir uma mesa: ");
        String nomeGarcom = input.next();

        Garcom garcomBuscado = null;

        try
        {
            for (Garcom garcomNomeAtual : arlGarcom)
            {
                if (garcomNomeAtual.getNome().equalsIgnoreCase(nomeGarcom))
                {
                    garcomBuscado = garcomNomeAtual;
                }

            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar garcom pelo nome");
        }

        System.out.print("\nQual o numero da mesa que vc deseja atribuir ao garcom " + nomeGarcom + "?");
        int numeroMesa = input.nextInt();

        Mesa mesaBuscada = null;

        try
        {
            for (Mesa numeroAtualMesa : arlMesa)
            {
                if (numeroAtualMesa.getNumeroMesa() == numeroMesa)
                {
                    mesaBuscada = numeroAtualMesa;//tem um numero de mesa aqui
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar mesa pelo numero!");
        }

        //atribuicaoDeClasses(mesaBuscada, garcomBuscado);
        mesaBuscada.setGarcomDaMesa(garcomBuscado);
        garcomBuscado.getArlMesasNoGarcom().add(mesaBuscada);
    }

    /*public static void atribuicaoDeClasses(Mesa mesaBuscada, Garcom garcomBuscado)
    {
        if(mesaBuscada.getGarcomDaMesa().equals(garcomBuscado.getNome()))
        {
            System.out.println("Ja existe garcom para esta mesa.");
            mesaBuscada.setGarcomDaMesa(garcomBuscado);
        }
        else
        {
            System.out.println("Ja existe garcom para esta mesa.");
        }

        garcomBuscado.getArlMesasNoGarcom().add(mesaBuscada);

    }*/

    private static void buscarGarcomEmail()
    {
        System.out.print("Qual o e-mail do garcom que deseja buscar: ");
        String emailGarcomBusca = input.next();
        
        Garcom garcomEmail = null;
        try
        {
            for (Garcom emailAtualGarcom : arlGarcom)
            {
                if (emailAtualGarcom.getEmail().equals(emailGarcomBusca))
                {
                    garcomEmail = emailAtualGarcom;
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
            System.out.println("Telefone : " + garcomEmail.getTelefone());
        }
    }

    private static void relatorioGarcom()
    {
        ArrayList<Garcom> arraysGarcons = buscarRelatorioGarcom();

        arraysGarcons.forEach(garcom ->
        {
            System.out.println("Nome: " + garcom.getNome());
            System.out.println("Cpf: " + garcom.getCpf());
            System.out.println("Data de nascimento: " + garcom.getDataNascimento());
            System.out.println("Email: " + garcom.getEmail());
            System.out.println("Sexo: " + garcom.getSexo());
            System.out.printf("Salario: R$ %.2f ", garcom.getSalarioFixo());
            System.out.println("\nTelefone : " + garcom.getTelefone());
            System.out.print("Mesas desse garçom: \n");

            for (int i = 0; i < garcom.getArlMesasNoGarcom().size(); i++)
            {
                Mesa mesaRelatorio = garcom.getArlMesasNoGarcom().get(i);
                System.out.println(mesaRelatorio.getNumeroMesa());
            }
            System.out.println();
        });



        /*ArrayList<Mesa> mesas = buscarRelatorioMesas();
        mesas.forEach(mesa ->
        {
            if(mesa.getGarcomDaMesa() != null)
            {
                    System.out.println(mesa.getNumeroMesa());
            }
            else System.out.println("Nao ha mesas para este garçom ainda.");
        });*/
    }

    private static ArrayList<Garcom> buscarRelatorioGarcom()
    {
        return arlGarcom;
    }

    private static void removerGarcom()
    {
        System.out.print("Digite o nome do garcom que deseja remover: ");
        input.nextLine();
        String nomeRemocao = input.nextLine();

        //Garcom buscarGarcomParaRemocao = null;
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
        System.out.print("Digite o nome do garcom: ");
        input.nextLine();
        String nome = input.nextLine();

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
        System.out.print("Qual telefone deste garcom: ");
        String telefone = input.next();

        try
        {
            Garcom garcom = new Garcom(nome, cpf, dataNascimento, email, sexoResultado, salario, telefone);
            arlGarcom.add(garcom);
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao cadastrar garcom.");
        }
    }

    private static void definirGarcomParaMesa()
    {
        System.out.println("Qual numero da mesa que deseja atribuir um garçom: ");
        int numeroMesa = input.nextInt();

        Mesa mesaBuscada = null;

        try
        {
            for (Mesa mesaAtual : arlMesa)
            {
                if (mesaAtual.getNumeroMesa() == numeroMesa)
                {
                    mesaBuscada = mesaAtual;
                }
            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar mesa pelo numero");
        }

        System.out.println("Qual nome do garcom que vc deseja atribuir a essa mesa: ");
        String nomeGarcom = input.next();

        Garcom garcomParaMesa = null;

        try
        {
            for (Garcom garcomNomeAtuais : arlGarcom)
            {
                if (garcomNomeAtuais.getNome().equalsIgnoreCase(nomeGarcom))
                {
                    garcomParaMesa = garcomNomeAtuais;
                }

            }
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar garcom pelo nome!");
        }


        //atribuicaoDeClasses(mesaBuscada, garcomParaMesa);
        /*ArrayList<Mesa> garcomDaMesa = buscarRelatorioMesas();
        garcomParaMesa.setArlMesasNoGarcom(garcomDaMesa);*/
        mesaBuscada.setGarcomDaMesa(garcomParaMesa);
        garcomParaMesa.getArlMesasNoGarcom().add(mesaBuscada);
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
                    System.out.print("\nGarçom dessa mesa: \n");

                    if(mesas.getGarcomDaMesa() != null)
                    {
                        System.out.println(mesas.getGarcomDaMesa().getNome());
                    }
                    else
                        System.out.println("Nao ha garçom definido para esta mesa ainda.");
                    System.out.println();
                }
                );

        /*ArrayList<Garcom> garcons = buscarRelatorioGarcom();
        garcons.forEach(g ->
        {
            if(g.getArlMesasNoGarcom() != null)
            {
                System.out.println(g.getNome());
            }
            else System.out.println("\nNao ha garçom definido para esta mesa ainda.");
        });*/
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
            for (Mesa capacidadeAtuaisNaMesa : arlMesa)
            {
                if (capacidadeAtuaisNaMesa.getCapacidadeMesa() >= capacidadeBusca)
                {
                    capacidadeDeBusca = capacidadeAtuaisNaMesa;

                    System.out.println("Numero da mesa: " + capacidadeDeBusca.getNumeroMesa());
                    System.out.println("Capacidade mesa: " + capacidadeDeBusca.getCapacidadeMesa());
                    System.out.println("Ocupacao mesa: " + capacidadeDeBusca.getOcupacaoMesa());
                    System.out.println();
                }
            }
            if(capacidadeDeBusca == null)
            System.out.println("\n\n >> Nao foi encontrado mesa com essa capacidade.");

        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Erro ao buscar mesa pela definiçao de capacidade!");
        }
    }
    private static void buscarMesaPorNumero(/*int numeroMesa*/)
    {
        System.out.print("Qual numero da mesa voce gostaria de procurar: ");
        int numeroMesa = input.nextInt();

        Mesa numeroMesaEncontrado = null;
        try
        {
            for (Mesa numeroAtualMesa : arlMesa)
            {
                if (numeroAtualMesa.getNumeroMesa() == numeroMesa)
                {
                    numeroMesaEncontrado = numeroAtualMesa;
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
            Mesa mesa = new Mesa(numeroMesa, capacidadeMesa, ocupacaoMesa,null);
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
                4. Atribuir um garcom a uma mesa.
                5. Relatorio Garçons cadastrados.
                6. Buscar mesas ocupadas e nome do garçom definido.
                7. Voltar ao menu inicial.
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
                6. Definir Garçom para uma mesa.
                7. Buscar Mesas Livres.
                8. Voltar ao menu inicial.
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
