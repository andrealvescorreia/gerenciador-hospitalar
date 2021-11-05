package principal;

import java.io.Serializable;

import javax.swing.JOptionPane;

public class Endereco implements funcoesEntSaiDados, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pais;
	private String estado;
	private String cidade;
	private String rua;
	private String bairro;
	
	public Endereco() {}
	
	public Endereco(String pais, String estado, String cidade, String rua, String bairro) {
		this.pais = pais;
		this.estado = estado;
		this.cidade = cidade;
		this.rua = rua;
		this.bairro = bairro;
	}
	
	public void preencherDados() {
		this.pais 	= JOptionPane.showInputDialog("País:");
		this.estado = JOptionPane.showInputDialog("Estado:");
		this.cidade = JOptionPane.showInputDialog("Cidade:");
		this.rua 	= JOptionPane.showInputDialog("Rua:");
		this.bairro = JOptionPane.showInputDialog("Bairro:");
		
	}
	
	
	public String formatarDados() {
		String dadosEnd = "País: "+this.pais+"\nEstado: "+this.estado+"\nCidade: "+this.cidade+"\nRua: "+this.rua+"\nBairro: "+this.bairro;
		return dadosEnd;
	}
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getRua() {
		return rua;
	}
	public void setRua(String rua) {
		this.rua = rua;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}


	
	
	
	
	
}

