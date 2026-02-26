package sistema_biblioteca;

public class Usuario {
	
	private String nome;
	private int cpf;
	private int telefone;
	
public Usuario(String nome, int cpf, int telefone) {
	this.nome=nome;
	this.cpf=cpf;
	this.telefone=telefone;
}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCpf() {
		return cpf;
	}
	public void setCpf(int cpf) {
		this.cpf = cpf;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}


}
