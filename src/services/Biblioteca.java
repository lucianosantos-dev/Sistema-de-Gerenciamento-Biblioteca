package services;

import model.entities.Livro;
import model.entities.Membro;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Livro> acervoDeLivros = new ArrayList<>();
    private List<Membro> listaDeMembros = new ArrayList<>();

    public Biblioteca() {
    }

    public Biblioteca(List<Livro> acervoDeLivros, List<Membro> listaDeMembros) {
        this.acervoDeLivros = acervoDeLivros;
        this.listaDeMembros = listaDeMembros;
        carregarLivros();
        carregarMembros();
    }

    public List<Livro> getAcervoDeLivros() {
        return acervoDeLivros;
    }

    public void setAcervoDeLivros(List<Livro> acervoDeLivros) {
        this.acervoDeLivros = acervoDeLivros;
    }

    public List<Membro> getListaDeMembros() {
        return listaDeMembros;
    }

    public void setListaDeMembros(List<Membro> listaDeMembros) {
        this.listaDeMembros = listaDeMembros;
    }

    public void adicionarLivro(Livro livro) {

        this.acervoDeLivros.add(livro);
    }

    public boolean cadastrarMembro(Membro membro) {

        for (Membro membrosNaLista : this.listaDeMembros) {
            if (membrosNaLista.getIdMembro().equals(membro.getIdMembro())) {
                return false;
            }
        }
        this.listaDeMembros.add(membro);
        return true;
    }

    public void emprestarLivro(String isbn, int idMembro) {

        Livro livroEncontrado = null;
        Membro membroEncontrado = null;

        for (Livro livroDaLista : this.acervoDeLivros) {
            if (livroDaLista.getIsbn().equals(isbn)) {
                livroEncontrado = livroDaLista;
                break;
            }
        }

        for (Membro membroDaLista : this.listaDeMembros) {
            if (membroDaLista.getIdMembro() == (idMembro)) {
                membroEncontrado = membroDaLista;
                break;
            }
        }

        if (livroEncontrado == null) {
            System.out.println("Livro não encontrado.");
            return;
        }
        if (membroEncontrado == null) {

            System.out.println("Membro não encontrado.");
            return;
        }
        if (!livroEncontrado.isDisponivel()) {
            System.out.println("Livro já emprestado.");
            return;
        }

        livroEncontrado.setDisponivel(false);
        membroEncontrado.adicionarLivro(livroEncontrado);
        System.out.println("Empréstimo realizado com sucesso!" + membroEncontrado);

    }

    public void devolverLivro(String isbn) {

        Livro livroParaDevolver = null;

        for (Livro livroDaLista : this.acervoDeLivros) {
            if (livroDaLista.getIsbn().equals(isbn)) {
                livroParaDevolver = livroDaLista;
                break;
            }
        }

        if (livroParaDevolver == null) {
            System.out.println("Livro não encontrado.");
            return;
        }

        if (livroParaDevolver.isDisponivel()) {
            System.out.println("Erro: Não é possível devolver um livro que já está  disponível.");
            return;
        }

        Membro membroParaEncontrar = null;

        for (Membro membro : listaDeMembros) {
            if (membro.getLivrosEmprestados().contains(livroParaDevolver)) {
                membroParaEncontrar = membro;
                break;
            }

        }

        if (membroParaEncontrar == null) {
            System.out.println("ERRO INESPERADO: O livro consta como emprestado," +
                    " mas não foi encontrado na lista de nenhum membro.");
            return;
        }

        livroParaDevolver.setDisponivel(true);
        membroParaEncontrar.removerLivro(livroParaDevolver);
        System.out.println("Livro devolvido com sucesso!" + livroParaDevolver);
    }

    public void buscarLivroPorTitulo(String titulo) {

        boolean encontrouAlgumLivro = false;

        for (Livro livroBuscado : acervoDeLivros) {
            if (livroBuscado.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                System.out.println(livroBuscado);
                encontrouAlgumLivro = true;
            }
        }

        if (!encontrouAlgumLivro) {
            System.out.println("Nenhum livro encontrado com esse título.");
        }
    }

    public void listarLivros() {

        System.out.println("---- Livros da Biblioteca ----");
        for (Livro livro : this.acervoDeLivros) {
            System.out.println(livro);
        }
        System.out.println("----------------------");
    }

    public void listarMembros() {

        System.out.println("---- Membros Cadastrados ----");
        for (Membro membro : this.listaDeMembros) {
            System.out.println(membro);
        }
        System.out.println("-------------------------");
    }

    public void salvarLivros() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("livros.csv"))) {
            for (Livro livro : this.acervoDeLivros) {
                writer.println(livro.getTitulo() + "," + livro.getAutor() + "," + livro.getIsbn() + "," + livro.isDisponivel());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os livros: " + e.getMessage());
        }
    }

    public void carregarLivros() {
        try (BufferedReader reader = new BufferedReader(new FileReader("livros.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length == 4) {
                    String titulo = dados[0];
                    String autor = dados[1];
                    String isbn = dados[2];
                    boolean disponivel = Boolean.parseBoolean(dados[3]);

                    Livro livro = new Livro(titulo, autor, isbn);
                    livro.setDisponivel(disponivel);
                    this.acervoDeLivros.add(livro);
                }
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            System.out.println("Erro ao carregar os livros: " + e.getMessage());
        }
    }

    private Livro buscarLivroPorIsbn(String isbn) {
        for (Livro livro : this.acervoDeLivros) {
            if (livro.getIsbn().equals(isbn)) {
                return livro;
            }
        }
        return null;
    }

    public void salvarMembros() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("membros.csv"))) {
            for (Membro membro : this.listaDeMembros) {
                StringBuilder linha = new StringBuilder();
                linha.append(membro.getIdMembro()).append(",").append(membro.getNome());

                if (!membro.getLivrosEmprestados().isEmpty()) {
                    linha.append(",");
                    List<String> isbnsEmprestados = new ArrayList<>();
                    for (Livro livro : membro.getLivrosEmprestados()) {
                        isbnsEmprestados.add(livro.getIsbn());
                    }
                    linha.append(String.join(";", isbnsEmprestados));
                }
                writer.println(linha.toString());
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar os membros: " + e.getMessage());
        }
    }

    public void carregarMembros() {
        try (BufferedReader reader = new BufferedReader(new FileReader("membros.csv"))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                String[] dados = linha.split(",");
                if (dados.length >= 2) {
                    int id = Integer.parseInt(dados[0]);
                    String nome = dados[1];
                    Membro membro = new Membro(nome, id);

                    if (dados.length > 2) {
                        String[] isbnsEmprestados = dados[2].split(";");
                        for (String isbn : isbnsEmprestados) {
                            Livro livroEmprestado = buscarLivroPorIsbn(isbn);
                            if (livroEmprestado != null) {
                                membro.adicionarLivro(livroEmprestado);
                            }
                        }
                    }
                    this.listaDeMembros.add(membro);
                }
            }
        } catch (FileNotFoundException ignored) {
        } catch (IOException e) {
            System.out.println("Erro ao carregar os membros: " + e.getMessage());
        }
    }
}
