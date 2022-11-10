package clientdata;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente{
	
	private String nome;
	private String nascimento;
	private String cpf;
	private String usuario;
	private String senha;
	private String tipo;
	private boolean emprestimo;
	
	private float saldo;
	private float fatura;
	private float mensal;
	private int contaMensal;
	
	private int id;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNascimento() {
		return nascimento;
	}
	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	public float getMensal() {
		return mensal;
	}
	public void setMensal(float mensal) {
		this.mensal = mensal;
	}
	public boolean getEmprestimo() {
		return emprestimo;
	}
	public void setEmprestimo(boolean emprestimo) {
		this.emprestimo = emprestimo;
	}
	
	public float getFatura() {
		return fatura;
	}
	public void setFatura(float fatura) {
		this.fatura = fatura;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getContaMensal() {
		return contaMensal;
	}
	public void setContaMensal(int contaMensal) {
		this.contaMensal = contaMensal;
	}
	
	public void sacar(float valor) {
		this.setSaldo(this.getSaldo() - valor);
	}

	public void depositar(float valor) {
		this.setSaldo(this.getSaldo() + valor);
	}
	public void emprestar(float valor) {
		
		if(this.getFatura() <= 0) {
			this.setEmprestimo(true);
			this.setSaldo(this.getSaldo() + valor);
			this.setFatura(valor);
		}
		
		
	}
	
	public void pagarMensal() {
		
		this.setSaldo(this.getSaldo() - this.getMensal());
		
	}
	
	public void pagarFatura(float valor) {
			
		this.setSaldo(this.getSaldo() - valor);
		this.setFatura(this.getFatura() - valor);
		
	}
}
