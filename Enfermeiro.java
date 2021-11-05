package principal;

import java.time.LocalDate;

public class Enfermeiro extends Pessoa {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String coren;//numero identificador COREN.
	
	public Enfermeiro() {}
	
	public Enfermeiro(String nome, String cpf, char sexo, LocalDate dataN, Endereco endereco, Contato contato, String coren) {
		super(nome,cpf,sexo,dataN,endereco,contato);
		this.coren = coren;
	}
	
	public void preencherDados() {
		super.nome = EntDados.pedirString("Nome do enfermeiro:");
		super.cpf = EntDados.pedirString("CPF:");
		super.sexo = EntDados.pedirChar("Sexo (M/F):");
		this.coren = EntDados.pedirString("COREN do enfermeiro:");
		//TO DO o resto.
	} 
	
	public String formatarDados() {
		String dadosEnfermeiro = "";
		dadosEnfermeiro += ("Nome: "+super.nome+"\nSexo: "+super.sexo+"\nCOREN: "+this.coren+"\n");
		//TO DO: todos os dados.
		return dadosEnfermeiro;
	}


	public String getCoren() {
		return coren;
	}


	public void setCoren(String coren) {
		this.coren = coren;
	}
}
