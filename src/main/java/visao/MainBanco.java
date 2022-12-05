package visao;
import beans.Garcom;
import beans.Mesa;
import dados.GarcomDAO;
import dados.MesaDao;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class MainBanco
{
        private static final ArrayList<Garcom> arlGarcom = new ArrayList<>();
        private static final ArrayList<Mesa> arlMesa = new ArrayList<>();
        private static final Scanner input = new Scanner(System.in);

        public static void main(String[] args) throws SQLException {
            MainBanco.menuInicial();
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
                                cadastrarGarcom();//feito
                                break;
                            case 2:
                                removerGarcom();//feito
                                break;
                            case 3:
                                buscarGarcomEmail();//feito
                                break;
                            case 4:
                                atribuirGarcomUmaMesa();//feito
                                break;
                            case 5:
                                relatorioGarcom();//feito
                                break;
                            case 6:
                                buscaMesasOcupadasComGarcon();//feito
                                break;
                            case 7:
                                break;
                            case 0:
                                System.out.println("Agradecemos por navegar por garçom, obrigado!!!");
                                return;
                            default:
                                System.out.println("Opcao invalida, Digite outra opcao.");
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
                                removerMesa();//feito
                                break;
                            case 3:
                                relatorioMesa();//feito
                                break;
                            case 4:
                                buscarMesaPorNumero();//feito
                                break;
                            case 5:
                                buscarMesaPorCapacidadeDeClientes();//feito
                                break;
                            case 6:
                                definirGarcomParaMesa();//feito
                                break;
                            case 7:
                                buscarMesasLivresEGarconsDela();//feito
                                break;
                            case 8:
                                break;
                            case 0:
                                System.out.println("Agradecemos por tentar cadastrar uma mesa, obrigado!!!");
                                return;
                            default:
                                System.out.println("Opcao invalida, Digite outra opcao.");
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

        private static void buscaMesasOcupadasComGarcon() throws SQLException
        {
            System.out.println("Mesas ocupadas com garçom definido:");

            ArrayList<Mesa> arlMesas = GarcomDAO.buscarMesaOcupadasEcomGarcom();

            for (int i = 0; i < arlMesas.size(); i++)
            {
                Mesa mesaAqui = arlMesas.get(i);

                System.out.println("Numero mesa: " + mesaAqui.getNumeroMesa());
                System.out.print("Ocupacao mesa: ");
                String situacaoMesa = mesaAqui.getOcupacaoMesa();
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

                try
                {
                    if(mesaAqui.getGarcomDaMesa().getCpf() == null)
                    {
                        System.out.println("Nao ha garçom definido para esta mesa ainda.");
                    }
                    else
                        System.out.println(mesaAqui.getGarcomDaMesa().getCpf());
                    System.out.println();
                } catch (Exception e)
                {
                    System.out.println("Essa mesa nao possui garçom definido, por favor defina um garçom" +
                            " para ela antes de exibir relatorios.");
                }
            }
        }

        private static void buscarMesasLivresEGarconsDela() throws SQLException
        {
            System.out.println("Mesas livres: ");

            ArrayList<Mesa> arlMesas = MesaDao.buscarMesaLivreEcomGarcom();

            for (int i = 0; i < arlMesas.size(); i++)
            {
                Mesa mesaAqui = arlMesas.get(i);

                System.out.println("Numero mesa: " + mesaAqui.getNumeroMesa());
                System.out.print("Ocupacao mesa: ");
                String situacaoMesa = mesaAqui.getOcupacaoMesa();
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

                try
                {
                    if(mesaAqui.getGarcomDaMesa().getCpf() == null)
                    {
                        System.out.println("Nao ha garçom definido para esta mesa ainda.");
                    }
                    else
                        System.out.println(mesaAqui.getGarcomDaMesa().getCpf());
                    System.out.println();
                } catch (Exception e)
                {
                    System.out.println("Essa mesa nao possui garçom definido, por favor defina um garçom" +
                            " para ela antes de exibir relatorios.");
                }


            }
        }

        private static void atribuirGarcomUmaMesa() throws SQLException
        {
            System.out.println("Qual o numero de cpf do garçom que deseja atribuir a mesa: ");
            input.nextLine();
            String cpfGarcom = input.nextLine();


            System.out.println("Qual numero da mesa que deseja atribuir um garçom: ");
            int numeroMesa = input.nextInt();

            GarcomDAO.definirUmaMesaGarcom(cpfGarcom, numeroMesa);
        }

        private static void buscarGarcomEmail() throws SQLException
        {
            System.out.print("Qual o e-mail do garcom que deseja buscar: ");
            String emailGarcomBusca = input.next();

            Garcom garcomEncontrado = GarcomDAO.buscaGarcomPeloEmail(emailGarcomBusca);

            if(garcomEncontrado.getEmail() == null)
            {
                System.out.println("Nao foi encontrado garcom com este email.");
            }
            else
            {
                System.out.println("Nome: " + garcomEncontrado.getNome());
                System.out.println("Cpf: " + garcomEncontrado.getCpf());
                System.out.println("Data de nascimento: " + garcomEncontrado.getDataNascimento());
                System.out.println("Email: " + garcomEncontrado.getEmail());
                System.out.printf("Salario: R$ %.2f \n", garcomEncontrado.getSalarioFixo());
                System.out.println("Telefone : " + garcomEncontrado.getTelefone());
            }
        }

        private static void relatorioGarcom() throws SQLException
        {
            ArrayList<Garcom> arlGarcons = GarcomDAO.buscarTodosGarcons();
            ArrayList<Mesa> mesasAqui = MesaDao.buscarMesas();


            for (int i = 0; i < arlGarcons.size(); i++)
            {
                Garcom c = arlGarcons.get(i);
                System.out.println("Nome: " + c.getNome());
                System.out.println("Cpf: " + c.getCpf());
                System.out.println("Data de nascimento: " + c.getDataNascimento());
                System.out.println("Email: " + c.getEmail());
                System.out.printf("Salario: R$ %.2f ", c.getSalarioFixo());
                System.out.println("\nTelefone : " + c.getTelefone());
                System.out.println("Mesas atendidas por esse garçom: ");

                try
                {
                    for (int j = 0; j < mesasAqui.size(); j++)
                    {
                        Mesa aquiMesa = mesasAqui.get(j);
                        if(aquiMesa.getGarcomDaMesa() != null)
                        {
                            if(aquiMesa.getGarcomDaMesa().getCpf().equals(c.getCpf()))
                            {
                                System.out.println(aquiMesa.getNumeroMesa());
                            }
                        }
                    }
                    //System.out.println(contador);
                } catch (Exception e)
                {
                    System.out.println("Esse garçom ainda n atende mesas.");;
                }
                System.out.println("---------------");
            }
        }

        private static ArrayList<Garcom> buscarRelatorioGarcom()
        {
            return arlGarcom;
        }

        private static void removerGarcom()
        {
            System.out.print("Digite o cpf do garcom que deseja remover: ");
            input.nextLine();
            String cpf = input.nextLine();

            try
            {
                GarcomDAO.remover(cpf);
                for (int i = 0; i < arlGarcom.size(); i++)
                {
                    Garcom garcomAtual = arlGarcom.get(i);

                    if(garcomAtual.getCpf().equalsIgnoreCase(cpf))
                    {
                        arlGarcom.remove(i);

                        Mesa mesa = null;
                        for(Mesa mesaAqui : arlMesa)
                        {
                            if(mesaAqui.getGarcomDaMesa().getNome().equals(cpf))
                            {
                                mesa = mesaAqui;
                                mesa.setGarcomDaMesa(null);
                            }
                        }
                    }
                }
                System.out.println("Garçom removido com sucesso!!");
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
                GarcomDAO.cadastrar(garcom);//*****

                System.out.println("Garçom cadastrado com sucesso!!");
            }catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("Erro ao cadastrar garcom.");
            }
        }

        private static void definirGarcomParaMesa() throws SQLException
        {
            System.out.println("Qual o numero de cpf do garçom que deseja atribuir a mesa: ");
            input.nextLine();
            String cpfGarcom = input.nextLine();


            System.out.println("Qual numero da mesa que deseja atribuir um garçom: ");
            int numeroMesa = input.nextInt();

            MesaDao.definirGarcomAUmaMesa(cpfGarcom, numeroMesa);
        }

        private static void relatorioMesa() throws SQLException
        {
            ArrayList<Mesa> arlMesas = MesaDao.buscarMesas();

            for(int i = 0; i < arlMesas.size(); i++)
            {
                Mesa mesaAqui = arlMesas.get(i);

                System.out.println("Numero mesa: " + mesaAqui.getNumeroMesa());
                System.out.println("Capacidade mesa: " + mesaAqui.getCapacidadeMesa());
                System.out.print("Ocupacao mesa: ");
                String situacaoMesa = mesaAqui.getOcupacaoMesa();
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

                try
                {
                    if(mesaAqui.getGarcomDaMesa().getCpf() == null)
                    {
                        System.out.println("Nao ha garçom definido para esta mesa ainda.\n");
                    }
                    else
                        System.out.println(mesaAqui.getGarcomDaMesa().getCpf());
                    System.out.println();
                } catch (Exception e)
                {
                    System.out.println("Essa mesa nao possui garçom definido, por favor defina um garçom" +
                            " para ela antes de exibir relatorios.\n");
                }
            }
        }
        private static ArrayList<Mesa> buscarRelatorioMesas()
        {
            return arlMesa;
        }
        private static void buscarMesaPorCapacidadeDeClientes() throws SQLException
        {
            System.out.print("Voce deseja buscar uma mesa com a capacidade de clientes maior ou igual a: ");
            int capacidadeBusca = input.nextInt();

            ArrayList<Mesa> mesasCapacidade = MesaDao.buscarMesaPeloCapacidadeDeClientes(capacidadeBusca);

            for (int i = 0; i < mesasCapacidade.size(); i++)
            {
                Mesa mesaAqui = mesasCapacidade.get(i);

                System.out.println("Numero da mesa: " + mesaAqui.getNumeroMesa());
                System.out.println("Capacidade mesa: " + mesaAqui.getCapacidadeMesa());
                System.out.print("Ocupacao mesa: ");
                if (mesaAqui.getOcupacaoMesa().equals("1"))
                    System.out.println("OCUPADA!");
                else if (mesaAqui.getOcupacaoMesa().equals("2"))
                    System.out.println("LIVRE");
                else System.out.println("RESERVADA.");
                System.out.println();
            }

            if(mesasCapacidade.isEmpty())
                System.out.println("Nao ha mesas com essa capacidade.");

        }
        private static void buscarMesaPorNumero() throws SQLException
        {
            System.out.print("Qual numero da mesa voce gostaria de procurar: ");
            int numeroMesa = input.nextInt();

            Mesa mesaEncontrada = MesaDao.buscaMesaPeloCodigo(numeroMesa);

            if(mesaEncontrada.getNumeroMesa() != numeroMesa)
            {
                System.out.println("Nao foi encontrado mesa com esse numero.");
            }else
            {
                System.out.println("Numero da mesa: " + mesaEncontrada.getNumeroMesa());
                System.out.println("Capacidade mesa: " + mesaEncontrada.getCapacidadeMesa());
                System.out.print("Ocupacao mesa: ");
                if(mesaEncontrada.getOcupacaoMesa().equals("1"))
                    System.out.println("OCUPADA!");
                else if (mesaEncontrada.getGarcomDaMesa().equals("2"))
                    System.out.println("LIVRE");
                else System.out.println("RESERVADA.");
                System.out.println("Garcom da mesa: ");

                try
                {
                    if(mesaEncontrada.getGarcomDaMesa().getCpf() == null)
                    {
                        System.out.println("Nao ha garçom definido para esta mesa ainda.\n");
                    }
                    else
                        System.out.println(mesaEncontrada.getGarcomDaMesa().getCpf());
                    System.out.println();
                } catch (Exception e)
                {
                    System.out.println("Essa mesa nao possui garçom definido, por favor defina um garçom" +
                            " para ela antes de exibir relatorios.\n");
                }
            }
        }

        private static void removerMesa() throws SQLException
        {
            System.out.println("Digite o codigo da mesa que deseja remover do restaurante.");
            int numeroMesa = input.nextInt();

            MesaDao.removerMesa(numeroMesa);
            try
            {
                for (int i = 0; i < arlMesa.size(); i++)
                {
                    Mesa mesaAtual = arlMesa.get(i);

                    if (mesaAtual.getNumeroMesa() == numeroMesa)
                    {
                        arlMesa.remove(i);

                        Garcom garcom = null;
                        for (int j = 0; j < arlGarcom.size(); j++)
                        {
                            Garcom garcomAqui = arlGarcom.get(j);
                            garcom = garcomAqui;


                            garcom.getArlMesasNoGarcom().remove(mesaAtual);
                        }
                    }
                }
            }
            catch (Exception e)
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
                MesaDao.cadastrarMesa(mesa);
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
