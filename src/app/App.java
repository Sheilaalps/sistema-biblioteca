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
		System.out.println("1. Cadastrar Livro");
		System.out.println("2. Cadastrar Usuário");
		System.out.println("3. Listar Livros");
		System.out.println("4. Listar Usuários");
		System.out.println("5. Registrar Empréstimo");
		System.out.println("6. Devolver Livro (Registrar Devolução)");
		System.out.println("7. Listar Empréstimos (Ver Datas)");
		System.out.println("8. Deletar usuário");
		System.out.println("9. Sair");
		System.out.print("Escolha uma opção: ");

		int opcao = scanner.nextInt();
		scanner.nextLine();

		switch (opcao) {
		case 1:
			cadastrarLivro();
			break;
		case 2:
			cadastrarUsuario();
			break;
		case 3:
			listarLivros();
			break;
		case 4:
			listarUsuario();
			break;
		case 5:
			registrarEmprestimo();
			break;
		case 6:
			registrarDevolucao();
			break;
		case 7:
			listarEmprestimos();
			break;
		case 8:
			deletarUsuario();
			break;
		case 9:
			System.out.println("Saindo...");
			System.exit(0);
			break;
		}
	}

	private static void deletarUsuario() {
		System.out.println("Digite o nome do usuário que deseja deletar");
		String nome = scanner.nextLine();

		Usuario encontrado = null;
		for (Usuario u : usuarios) {
			if (u.getNome().equalsIgnoreCase(nome)) {
				encontrado = u;
				break;

			}
		}
		usuarios.remove(encontrado);
		System.out.println("Usuário deletado com sucesso!" + encontrado.getNome());
	}

	private static void listarEmprestimos() {
		System.out.println("\n---LISTA DE EMPRÉSTIMOS---");
		for (Emprestimo e : emprestimos) {
			System.out.println(e.toString());
		}
	}

	private static void registrarDevolucao() {
		System.out.print("Informe o ISBN do livro: ");
		String isbn = scanner.nextLine();

		for (Emprestimo e : emprestimos) {
			// Se o ISBN bater e o livro NÃO foi devolvido ainda
			if (e.getLivro().getIsbn().equalsIgnoreCase(isbn) && !e.isDevolucao()) {
				e.registrarDevolucao(); // Ativa o boolean para true
				System.out.println("Sucesso! Livro devolvido.");
				return; // Encontrou? Para o método aqui.
			}
		}
		System.out.println("Empréstimo ativo não encontrado para este ISBN.");
	}

	public static void main(String[] args) {
		while (true) {
			mostrarMenu();
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
		for (Usuario u : usuarios) {
			System.out.println("Nome: " + u.getNome() + "| CPF: " + u.getCpf());
		}
	}

	private static void listarLivros() {
		System.out.println("\n---LISTA DE LIVROS---");
		for (Livro l : livros) {
			System.out.println("Titulo: " + l.getTitulo() + "| ISBN: " + l.getIsbn() + "|Autor: " + l.getAutor());
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
