package app;

import java.util.ArrayList;
import java.util.Scanner;

import sistema_biblioteca.Emprestimo;
import sistema_biblioteca.Livro;
import sistema_biblioteca.Usuario;

public class App {

    private static ArrayList<Livro> livros = new ArrayList<>();
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    private static ArrayList<Emprestimo> emprestimos = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in); // Scanner único para evitar erros

    public static void mostrarMenu() {
        System.out.println("\n--- MENU BIBLIOTECA ---");
        System.out.println("1. Cadastrar livro");
        System.out.println("2. Cadastrar usuário");
        System.out.println("5. Registrar empréstimo");
        System.out.println("6. Sair");
        System.out.print("Escolha uma opção: ");
        System.out.println("3. Listar livros");
        System.out.println("4. Listar usuários");
        
        int opcao = scanner.nextInt();
        scanner.nextLine(); // Limpa o buffer do teclado
        
        switch(opcao) {
        case 1: cadastrarLivro();
        case 2: cadastrarUsuario();
        case 3: listarLivros();   // Chama o método acima
        case 4: listarUsuario(); // Chama o método acima
        case 5: registrarEmprestimo();
        break;
        case 6: System.exit(0);
    }
    }
    public static void main(String[] args) {
        while (true) {
            mostrarMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            if (opcao == 4) {
                System.out.println("Encerrando sistema...");
                break;
            } else if (opcao == 1) {
                cadastrarLivro();
            } else if (opcao == 2) {
                cadastrarUsuario();
            } else if (opcao == 3) {
                registrarEmprestimo();
            } else {
                System.out.println("Opção Inválida!");
            }
        }
    }

    private static void cadastrarLivro() {
        System.out.print("Digite o título: ");
        String titulo = scanner.nextLine();
        System.out.print("Digite o autor: ");
        String autor = scanner.nextLine();
        System.out.print("Digite o ISBN: ");
        String isbn = scanner.nextLine();
      
        livros.add(new Livro(titulo, autor, isbn));
        System.out.println("Livro cadastrado com sucesso!");
    }
    private static void listarUsuario() {
    	System.out.println("\n---LISTA DE USUÁRIOS---");
    	for(Usuario u: usuarios){
    		System.out.println("Nome: "+u.getNome() + "| CPF: " +u.getCpf());
    	}
    }
    private static void listarLivros() {
    	System.out.println("\n---LISTA DE LIVROS---");
    	for(Livro l: livros){
    		System.out.println("Titulo: "+l.getTitulo() + "| ISBN: " +l.getIsbn()+ "|Autor: "+l.getAutor());
    	}
    }

    private static void cadastrarUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o CPF do usuário: ");
        String cpf = scanner.nextLine();
        System.out.print("Digite o telefone do usuário: ");
        String telefone = scanner.nextLine();
        
        usuarios.add(new Usuario(nome, cpf, telefone));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void registrarEmprestimo() {
        if (usuarios.isEmpty() || livros.isEmpty()) {
        	
        	
            System.out.println("Erro: É necessário ter pelo menos um usuário e um livro cadastrados.");
            return;
        }

        System.out.print("Digite o nome do usuário: ");
        String nomeBusca = scanner.nextLine();
        
        // Busca Usuário
        Usuario usuarioEncontrado = null;
        for (Usuario u : usuarios) {
            if (u.getNome().equalsIgnoreCase(nomeBusca)) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado == null) {
            System.out.println("Usuário não encontrado!");
            return;
        }

        System.out.print("Digite o título do livro: ");
        String tituloBusca = scanner.nextLine();

        // Busca Livro
        Livro livroEncontrado = null;
        for (Livro l : livros) {
            if (l.getTitulo().equalsIgnoreCase(tituloBusca)) {
                livroEncontrado = l;
                break;
            }
        }

        if (livroEncontrado != null) {
            Emprestimo novoEmprestimo = new Emprestimo(livroEncontrado, usuarioEncontrado);
            emprestimos.add(novoEmprestimo);
            System.out.println("Empréstimo registrado com sucesso para: " + usuarioEncontrado.getNome());
        } else {
            System.out.println("Livro não encontrado!");
        }
    }
}
