package sistema_biblioteca;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
    private Usuario usuario;
    private Livro livro;
    private LocalDate dataEmprestimo; // Adicionado para controle real

    public Emprestimo(Livro livro, Usuario usuario) {
        this.usuario = usuario;
        this.livro = livro;
        this.dataEmprestimo = LocalDate.now(); // Define a data no momento da criação
    }

    // Método para facilitar a exibição na tela
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return "Livro: " + livro.getTitulo() + 
               " | Usuário: " + usuario.getNome() + 
               " | Data: " + dataEmprestimo.format(formato);
    }

    // Getters e Setters
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
}