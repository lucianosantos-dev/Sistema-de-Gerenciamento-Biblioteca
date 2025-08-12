package model.entities;

import java.util.ArrayList;
import java.util.List;

public class Membro {

    private String nome;
    private Integer idMembro;

    private List<Livro> livrosEmprestados = new ArrayList<>();

    public Membro() {
    }

    public Membro(String nome, Integer idMembro) {
        this.nome = nome;
        this.idMembro = idMembro;
    }

    public Membro(String nome, Integer idMembro, List<Livro> livrosEmprestados) {
        this.nome = nome;
        this.idMembro = idMembro;
        this.livrosEmprestados = livrosEmprestados;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getIdMembro() {
        return idMembro;
    }

    public void setIdMembro(Integer idMembro) {
        this.idMembro = idMembro;
    }

    public List<Livro> getLivrosEmprestados() {
        return livrosEmprestados;
    }

    public void adicionarLivro(Livro livro) {

        this.livrosEmprestados.add(livro);
    }

    public void removerLivro(Livro livro) {

        this.livrosEmprestados.remove(livro);
    }

    @Override
    public String toString() {
        return "Membro [ID: " + idMembro + ", Nome: " + nome +
                ", Livros Emprestados: " + livrosEmprestados.size() + "]";
    }

}
