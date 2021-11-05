package principal;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Paciente extends Pessoa{
	
	//por mais que por enquanto Paciente é praticamente igual a Pessoa,
	// sinto ainda assim que a melhor decisão foi ter criado essa classe Paciente
	// separado de Pessoa, pois num futuro hipotetico, o programa pode evoluir e começarão a 
	// surgir distinções entre Pessoa e Paciente. Por isso, no logo termo, é melhor
	// deixar as novas caracteristicas de Paciente aqui.
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public Paciente() {}
	
	public Paciente(String nome, String cpf, char sexo, LocalDate dataN, Endereco endereco, Contato contato) {
		super(nome,cpf,sexo,dataN,endereco,contato);
		
	}
	
	public void preencherDados() {
		super.nome = EntDados.pedirString("Nome do paciente:");
		super.cpf = EntDados.pedirString("CPF:");
		super.sexo = EntDados.pedirChar("Sexo (M/F):");
		
		
		
		super.dataNasc = EntDados.pedirData("Data nascimento:");
		
		super.endereco.preencherDados();
		super.contato.preencherDados();
		
		//A FAZER O RESTO das variaveis da classe Pessoa.
	}
	
	
	
	public String formatarDados() {
		String nInf = "Não informado.";
		String dados = "";
		dados += "Nome: "+super.nome+
				"\nSexo: "+super.sexo+
				"\nCPF: "+super.cpf+
				/*"\nIdade atual:"+super.getIdade()+*/
				"\nData nascimento:"+(dataNasc != null ? super.getDataN().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : nInf)+
				"\n---Endereço:\n"+(endereco != null ? super.endereco.formatarDados(): nInf)+
				"\n---Contato:\n"+(contato != null ? super.contato.formatarDados(): nInf);
		return dados;
	}
	
	
	
}
