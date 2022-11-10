package clientdata;

public class Corrente extends Cliente{
	
	public Corrente(String nome, String nascimento, String cpf, String usuario, String senha){
		this.setNome(nome);
		this.setNascimento(nascimento);
		this.setCpf(cpf);
		this.setUsuario(usuario);
		this.setSenha(senha);
		this.setTipo("Conta Corrente");
		this.setSaldo(500);
		this.setMensal(50f);
		this.setEmprestimo(false);
		this.setFatura(0);
	}

}
