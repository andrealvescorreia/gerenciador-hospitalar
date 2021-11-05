package principal;

import java.io.Serializable;

//import javax.swing.JOptionPane;

public class Contato implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String telefone;
	private String email;
	
	public Contato() {}
	public Contato(String telefone, String email) {
		this.setTelefone(telefone);
		this.setEmail(email);
	}
	
	public void preencherDados() {
		this.telefone = EntDados.pedirString("Telefone:");
		this.email 	  = EntDados.pedirString("E-mail:");
	}
	
	public String formatarDados(){
		String dadosCont = "Email: "+this.email+"\nTel: "+  this.telefone+"\n";
		return dadosCont;
	}
	
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
	
}
