package principal;

import java.time.LocalDate;

public class Medico extends Pessoa{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String crm;//numero identificador CRM
	
	public Medico() {}
	
	public Medico(String nome, String cpf, char sexo, LocalDate dataN, Endereco endereco, Contato contato, String crm) {
		super(nome,cpf,sexo,dataN,endereco,contato);
		this.crm = crm;
	}
	
	@Override
	public void preencherDados() {
		
		super.nome = EntDados.pedirString("Nome do médico:");
		super.cpf = EntDados.pedirString("CPF:");
		super.sexo = EntDados.pedirChar("Sexo (M/F):");
		this.crm = EntDados.pedirString("CRM do médico:");
		// A fazer o resto.
		
		
	} 
	
	public String formatarDados() {
		String dadosMedico = "";
		dadosMedico += ("Nome: "+super.nome+"\nSexo: "+super.sexo+"\nCRM: "+this.crm+"\n");
		//TO DO, todos os dados.
		return dadosMedico;
	}
	public String getCrm() {
		return crm;
	}
	public void setCrm(String crm) {
		this.crm = crm;
	}
	
}
