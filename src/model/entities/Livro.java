package model.entities;

public class Livro {

    private String titulo;
    private String autor;
    private String isbn;
    private boolean disponivel;

    public Livro() {
    }

    public Livro(String titulo, String autor, String isbn) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = true;
    }

    public Livro(String titulo, String autor, String isbn, boolean disponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.disponivel = disponivel;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }


    public String getIsbn() {
        return isbn;
    }


    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    @Override
    public String toString() {
        return "Livro [Titulo: " + titulo + ", Autor: " + autor +
                ", Isbn: " + isbn + ", Disponivel: " + disponivel + "]";
    }
}
