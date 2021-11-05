package principal;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Hospital implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String nome;//hospital.

	private List<Medico>     medicos     = new ArrayList<Medico>();
	private List<Enfermeiro> enfermeiros = new ArrayList<Enfermeiro>();
	private List<Paciente>   pacientes   = new ArrayList<Paciente>();
	
	
	// dividir para conquistar. Criei 5 listas de Prontuario ao inves de uma. Elas representam os diferentes estados que um paciente pode se encontrar.
	private List<Prontuario> emAtendimentoEnfermagem  = new ArrayList<Prontuario>();// estado 1 - prontuarios recem criados. O paciente ainda nao teve seu risco avaliado na enfermagem.
	private List<Prontuario> listaDeEspera = new ArrayList<Prontuario>();			// estado 2 - prontuarios de pacientes esperando seu nome ser chamado para ter a consulta finalizada.
	private List<Prontuario> emAtendimentoMedico = new ArrayList<Prontuario>();		// estado 3 - o paciente foi chamado. Aguardando o diagnostico do medico e se o paciente foi internado.
	
	private List<Prontuario> internados    = new ArrayList<Prontuario>();// estado 4 - prontuarios de pacientes atualmente internados.
	private List<Prontuario> altasMedicas  = new ArrayList<Prontuario>();// estado 5 - historico de prontuarios onde o paciente ja deixou o hospital.
	
	
	public static String[] grausDeRisco = {
			 "Azul",//     - NÃO URGÊNCIA
			 "Verde",//    - POUCA URGÊNCIA
			 "Amarelo",//  - MUITA URGÊNCIA
			 "Vermelho",// - EMERGÊNCIA
			 };
	
	
	public Hospital() {}
	
	public String formatarDadosProntuarios() {
		String strProEm_esperandoAvalRisco = "";
		String strProEm_listaDeEspera = "";
		String strProEm_esperandoDiag = "";
		String strProEm_internacao="";
		String strProEm_altaMedica="";
		
		for(Prontuario pro_i: this.emAtendimentoEnfermagem) {
			strProEm_esperandoAvalRisco += pro_i.formatarDados();
		}
		for(Prontuario pro_i: this.listaDeEspera) {
			strProEm_listaDeEspera += pro_i.formatarDados();
		}
		for(Prontuario pro_i: this.emAtendimentoMedico) {
			strProEm_esperandoDiag += pro_i.formatarDados();
		}
		for(Prontuario pro_i: this.internados) {
			strProEm_internacao+= pro_i.formatarDados();
		}
		for(Prontuario pro_i: this.altasMedicas) {
			strProEm_altaMedica+= pro_i.formatarDados();
		}
		
		String dadosProntuarios = 
				"PRONTUARIOS ATIVOS:\n\n"+
		
				"Esperando avaliação de risco:\n"+
				""+strProEm_esperandoAvalRisco + "\n"+
				"----------------\n"+
				"Na lista de espera:\n"+
				strProEm_listaDeEspera+
				"\n----------------\n"+
				"Esperando diagnostico:\n"+
				strProEm_esperandoDiag+
				"\n----------------\n"+
				"Internados:\n"+
				strProEm_internacao+
				"\n----------------\n\n"+
				
				"PRONTUARIOS INATIVOS (alta médica):\n\n"+
				strProEm_altaMedica+"\n";
		
		return dadosProntuarios;
	}

	public String formatarDadosPacientes() {
		String dados = "";
		for(Paciente pac_i: this.pacientes) {
			String nome =  pac_i.getNome();
			String risco = pac_i.getCpf();
			
			dados += nome + " | "+risco+"\n";
		}
		return dados;
	}
	
	public String formatarListaDeEspera() {//TO DO: adicionar mais dados (id, tempo esperando) depois.
		String strListaEspera = "";
		for(Prontuario pro_i: this.listaDeEspera) {
			String nome =  pro_i.getPaciente().getNome();
			String risco = pro_i.getRisco();
			
			strListaEspera += nome + " | "+risco+"\n";
		}
		return strListaEspera;
	}

	
	
	
	public void addNaListaDeEspera(Prontuario pro) {//adiciona um prontuario a "listaDeEspera".
		//Adiciona o paciente (mais exatamente seu prontuario) em uma posicão ordenada 
		//na lista de espera, levando em consideração o risco e sua data de admissao.
		int pontosDePrioridade_pro = 0;
		int pontosDePrioridade_pro_i = 0;
		
		
		
		
		//pega a prioridade de "pro" e tranforma em um numero (0,1,2,3).
		for(int i = 0; i < grausDeRisco.length;i++) {
			if(pro.getRisco() == grausDeRisco[i]) {
				pontosDePrioridade_pro = i;
				break;
			}
		}
		
		
		boolean adicionado = false;
		
		for(int i = this.listaDeEspera.size()-1; i >= 0; i--) {
			Prontuario pro_i = new Prontuario();
			pro_i = this.listaDeEspera.get(i);
			
			//pega a prioridade de "pro_i" e tranforma em um numero (0,1,2,3).// 0 tem menor prioridade que 2 por exemplo.
			for(int j = 0; j < grausDeRisco.length;j++) {
				if(pro_i.getRisco() == grausDeRisco[j]) {
					pontosDePrioridade_pro_i = j;
					break;
				}
			}
			
			if(pontosDePrioridade_pro == pontosDePrioridade_pro_i && i < this.listaDeEspera.size()) {
				this.listaDeEspera.add(i+1,pro);
				adicionado = true;
				break;
			}
			else if(pontosDePrioridade_pro < pontosDePrioridade_pro_i) {
				this.listaDeEspera.add(i,pro);
				adicionado = true;
				//System.out.println("yoloo");
				break;
			}
		}
		if(!adicionado) {
			//o loop chegou ao fim sem adicionar "pro" a lista.
			//Ou seja, ele deve ser adicionado no inicio da lista.
			System.out.println("adicionado no inicio");
			listaDeEspera.add(0,pro);
		}
		
	}
	public List<String> getNomesMedicos() {// Fernado Cabral | 3448521
		List<String> listaNomes = new ArrayList<String>();
		for(Medico med: this.medicos) {
			listaNomes.add(med.getNome()+" | "+ med.getCrm());
		}
		return listaNomes;
	}
	public List<String> getNomesEnfermeiros() {// Joao Pedro | 4002892
		List<String> listaNomes = new ArrayList<String>();
		for(Enfermeiro enf: this.enfermeiros) {
			listaNomes.add(enf.getNome()+" | "+ enf.getCoren());
		}
		return listaNomes;
	}
	public List<String> getNomesPacientes() {// Marcia Goularte | 345439767-12
		List<String> listaNomes = new ArrayList<String>();
		for(Paciente pac: this.pacientes) {
			listaNomes.add(pac.getNome()+" | "+ pac.getCpf());
		}
		return listaNomes;
	}
	//___________
	public List <String> getNomesEsperandoAvalRisco(){
		List<String> listaNomes = new ArrayList<String>();
		for(Prontuario pro_i: this.emAtendimentoEnfermagem) {
			listaNomes.add(pro_i.getPaciente().getNome()+" | "+pro_i.getPaciente().getCpf());
		}
		return listaNomes;
	}
	public List <String> getNomesNaListaDeEspera(){
		List<String> listaNomes = new ArrayList<String>();
		for(Prontuario pro_i: this.listaDeEspera) {
			listaNomes.add(pro_i.getPaciente().getNome()+" | "+pro_i.getPaciente().getCpf());
		}
		return listaNomes;
	}
	public List <String> getNomesEsperandoDiag(){
		List<String> listaNomes = new ArrayList<String>();
		for(Prontuario pro_i: this.emAtendimentoMedico) {
			listaNomes.add(pro_i.getPaciente().getNome()+" | "+pro_i.getPaciente().getCpf());
		}
		return listaNomes;
	}
	public List <String> getNomesInternados(){
		List<String> listaNomes = new ArrayList<String>();
		for(Prontuario pro_i: this.internados) {
			listaNomes.add(pro_i.getPaciente().getNome()+" | "+pro_i.getPaciente().getCpf());
		}
		return listaNomes;
	}
	public List <String> getNomesAltasMedicas(){
		List<String> listaNomes = new ArrayList<String>();
		for(Prontuario pro_i: this.altasMedicas) {
			listaNomes.add(pro_i.getPaciente().getNome()+" | "+pro_i.getPaciente().getCpf());
		}
		return listaNomes;
	}
	public String formatarDados() {
		String dadosClinica = "";
		dadosClinica += 
				  "Clinica "+this.nome +
				"\nMedicos: "+this.medicos.size()+
				"\nEnfermeiros: "+this.enfermeiros.size()+
				"\nPacientes total: "+ this.pacientes.size() + 
				"\nPacientes na lista de espera: " + this.listaDeEspera.size()+ 
				"\nPacientes internados: " + this.listaDeEspera.size()+
				"\nAltas médicas totais: " + this.altasMedicas.size();
		return dadosClinica;
	}
	
	public Medico buscarMedico(String crmMedico) {
		for(Medico med: this.medicos) {
			if(med.getCrm().equals(crmMedico)) {
				return med;//o medico foi encontrado.
			}
		}
		return null;//medico n encontrado.
	}
	
	public Enfermeiro buscarEnfermeiro(String corenEnfermeiro) {
		for(Enfermeiro enf: this.enfermeiros) {
			if(enf.getCoren().equals(corenEnfermeiro)) {
				return enf;//o enfermeiro foi encontrado.
			}
		}
		return null;//Enfermeiro n encontrado.
	}
	
	//___________________________________________________________
	
	public void addEmAtendimentoEnfermagem(Prontuario pro) {
		this.emAtendimentoEnfermagem.add(pro);
	}
	
	public void addPaciente(Paciente pac) {
		this.pacientes.add(0,pac);
	}
	
	public void addMedico(Medico med) {
		medicos.add(0,med);
	}
	public void addEnfermeiro(Enfermeiro enf) {
		enfermeiros.add(0,enf);
	}
	public void remMedico(Medico med) {
		medicos.remove(med);
	}
	public void remEnfermeiro(Enfermeiro enf) {
		enfermeiros.remove(enf);
	}
	
	public List<Paciente> getPacientes() {
		return pacientes;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<Enfermeiro> getEnfermeiros() {
		return enfermeiros;
	}
	public void setEnfermeiros(List<Enfermeiro> enfermeiros) {
		this.enfermeiros = enfermeiros;
	}
	public List<Medico> getMedicos() {
		return medicos;
	}
	public void setMedicos(List<Medico> medicos) {
		this.medicos = medicos;
	}


	


	public List<Prontuario> getListaDeEspera() {
		return listaDeEspera;
	}

	
	public void setListaDeEspera(List<Prontuario> listaDeEspera) {
		this.listaDeEspera = listaDeEspera;
	}


	public List<Prontuario> getInternados() {
		return internados;
	}


	public void setInternados(List<Prontuario> internados) {
		this.internados = internados;
	}


	public List<Prontuario> getAltasMedicas() {
		return altasMedicas;
	}


	public void setAltasMedicas(List<Prontuario> altasMedicas) {
		this.altasMedicas = altasMedicas;
	}


	public List<Prontuario> getEmAtendimentoEnfermagem() {
		return emAtendimentoEnfermagem;
	}


	public void setEmAtendimentoEnfermagem(List<Prontuario> recemCriados) {
		this.emAtendimentoEnfermagem = recemCriados;
	}


	public List<Prontuario> getEmAtendimentoMedico() {
		return emAtendimentoMedico;
	}


	public void setEmAtendimentoMedico(List<Prontuario> esperandoDiag) {
		this.emAtendimentoMedico = esperandoDiag;
	}


}
