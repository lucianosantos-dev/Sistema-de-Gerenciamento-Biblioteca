import model.entities.Livro;
import model.entities.Membro;
import services.Biblioteca;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        Biblioteca biblioteca = new Biblioteca();

        while (true) {

            System.out.println("---- Escolha uma opção ----");
            System.out.println("1 - Adicionar Livro");
            System.out.println("2 - Cadastrar Membro");
            System.out.println("3 - Listar Livros");
            System.out.println("4 - Listar Membros");
            System.out.println("5 - Emprestar livro");
            System.out.println("6 - Devolver Livro");
            System.out.println("7 - Buscar Livro Por Titulo");
            System.out.println("0 - Sair");
            System.out.println();

            System.out.print("Digite a opção desejada: ");
            char escolha = sc.next().charAt(0);
            sc.nextLine();

            switch (escolha) {
                case '1':
                    System.out.println("---- Adicionar um novo Livro ---");
                    System.out.print("Digite o Autor: ");
                    String autor = sc.nextLine();
                    System.out.print("Digite o Titulo: ");
                    String titulo = sc.nextLine();
                    System.out.print("Digite o ISBN: ");
                    String isbn = sc.nextLine();

                    Livro livro = new Livro(titulo, autor, isbn);
                    biblioteca.adicionarLivro(livro);
                    System.out.println("Livro cadastrado com sucesso.");
                    System.out.println();
                    break;

                case '2':
                    System.out.println("---- Cadastro de novo Membro ----");
                    System.out.print("Digite seu nome: ");
                    String nomeMembro = sc.nextLine();
                    try {
                        System.out.print("Digite seu ID (contendo apenas números): ");
                        int idMembro = sc.nextInt();
                        sc.nextLine();

                        Membro membro = new Membro(nomeMembro, idMembro);
                        boolean sucesso = biblioteca.cadastrarMembro(membro);

                        if (sucesso) {
                            System.out.println("Novo Membro cadastrado com sucesso.");
                            System.out.println();
                        } else {
                            System.out.println("Erro: O ID " + idMembro + " já está em uso. O membro náo foi cadastrado.");
                        }

                    } catch (InputMismatchException e) {
                        System.out.println("Opção inválida. Digite apenas número.");
                        sc.nextLine();
                    }
                    break;

                case '3':
                    System.out.println("---- Listar Livros ----");
                    biblioteca.listarLivros();
                    System.out.println();
                    break;

                case '4':
                    System.out.println("---- Listar Membros ----");
                    biblioteca.listarMembros();
                    System.out.println();
                    break;

                case '5':
                    System.out.println("---- Empréstimo de  Livro ----");
                    System.out.print("Digite o ISBN: ");
                    String isbnLido = sc.nextLine();
                    try {
                        System.out.print("Digite o ID do Membro: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        biblioteca.emprestarLivro(isbnLido, id);
                        System.out.println();
                    } catch (InputMismatchException e) {
                        System.out.println("Erro:  O ID do membro dever ser um número. Operação cancelada ");
                        sc.nextLine();
                    }
                    break;

                case '6':
                    System.out.println("---- Devolver Livro ----");
                    System.out.print("Digite o ISBN para devolução: ");
                    String isbnLivro = sc.nextLine();
                    biblioteca.devolverLivro(isbnLivro);
                    System.out.println();
                    break;

                case '7':
                    System.out.println("---- Buscar Livro por Titulo ----");
                    System.out.print("Digite o titulo que deseja buscar: ");
                    String tituloDesejado = sc.nextLine();
                    biblioteca.buscarLivroPorTitulo(tituloDesejado);
                    System.out.println();
                    break;

                case '0':
                    System.out.println("Salvando dados e saindo do sistema . Até Logo Bye, Bye...");
                    biblioteca.salvarLivros();
                    biblioteca.salvarMembros();
                    sc.close();
                    return;
                default:
                    System.out.println("ERRO: Tente novamente uma opção valída.");
            }
        }

    }

}