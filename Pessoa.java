package principal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

//import java.util.Date;

public class Pessoa implements Serializable,funcoesEntSaiDados {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String nome;
	protected String cpf;
	protected char sexo;

	protected LocalDate dataNasc;// data Nascimento.
	
	protected Endereco endereco = new Endereco();
	protected Contato contato = new Contato();
	
	public Pessoa() {}
	
	public Pessoa(String nome, String cpf, char sexo, LocalDate dataN, Endereco endereco, Contato contato) {
		//super();
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.dataNasc = dataN;
		this.endereco = endereco;
		this.contato = contato;
	}
	
	public void preencherDados() {}
	
	
	public String formatarDados() {return "";}
	
	
	public final int getIdade() {
		int idade = Period.between(dataNasc, LocalDate.now()).getYears();
		return idade;
	}
	
	public final String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public final String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public final char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public final Endereco getEndereco() {
		return endereco;
	}
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	public final Contato getContato() {
		return contato;
	}
	public void setContato(Contato contato) {
		this.contato = contato;
	}


	public final LocalDate getDataN() {
		return dataNasc;
	}
	public void setDataN(LocalDate dataN) {
		this.dataNasc = dataN;
	}


	



}
