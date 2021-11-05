package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class Main {
	
	private static final String nomeArquivoHosp = "dadosHospital.byte";
	private static final String camHosp ="C:/"+nomeArquivoHosp;
	
	public static void main(String[] args) {
		
		Hospital hospital = new Hospital();
		hospital.setNome("Hospital Regional de Patos");

		try {
			hospital = Arquivos.lerHospital(camHosp);
		}catch(IOException | ClassNotFoundException e){
			//TO-DO: melhorar esta solução depois
			JOptionPane.showMessageDialog(null, "Não foi possivel ler o arquivo '"+camHosp+"'. Será criado um novo arquivo limpo.");
			Arquivos.gravarHospital(hospital, camHosp);
		}
		MenuPrincipal(hospital);
		
	}
	
	static void MenuPrincipal(Hospital hospital) {
		
		/*Prontuario pro = new Prontuario();//objeto auxiliar
		Medico med = new Medico();//auxiliar
		Enfermeiro enf = new Enfermeiro();*/
		
		String opcoesMenu =
				
				  "1 - Novo paciente.\n"
				+ "2 - Novo prontuario: dados iniciais.\n"
				+ "3 - Prontuario: inserir a enfermagem.\n"
				+ "4 - Chamar próximo na lista de espera.\n"
				+ "5 - Prontuario: inserir dados atendimento médico.\n"
				+ "6 - Prontuario: alta de paciente internado.\n"
				+ "_ _ _ _ _ _ _ _ _ _ _ _ _ \n"
				+ "7 - Listar todos os prontuarios\n"
			    + "8 - Adicionar medico/a ao sistema.\n"
				+ "9 - Adicionar enfermeiro/a ao sistema.\n" 
				+ "10 - Listar os medicos e enfermeiros do hospital.\n" 
				+ "11 - Listar pacientes registrados\n"
				+ "0 - Sair e salvar";
			//ideias: menu enfermeiro e medico. remover enfermeiro ou medico.
		
		int op = -1;//valor inicial.
		
		while(op != 0) {
			
			String dadosHospital = hospital.getNome() + 
					"\nPacientes: "+hospital.getPacientes().size()+
					"\nMedicos: "+hospital.getMedicos().size()+
					"\nEnfermeiros: "+hospital.getMedicos().size()+
					"\nProntuários finalizados: "+hospital.getAltasMedicas().size()+"\n";
			String strListaEspera = hospital.formatarListaDeEspera();
			
			op = EntDados.pedirInt(dadosHospital+"____________________________________\n"+opcoesMenu+"\n____________________________________\n---LISTA DE ESPERA:---\n--Nome | Risco--\n"+strListaEspera);
			
			switch(op) {
				case 1:
					_registrarPaciente(hospital);
					
					break;
				case 2:
					_novoProntuario(hospital);
					break;
				case 3:
					_addNaListaDeEspera(hospital);
					break;
				case 4:
					_chamarProximoNaLista(hospital);
					break;
				case 5:
					_finalizarDiagnostico(hospital);
					break;
				case 6:
					_internadoAltaHospitalar(hospital);
					break;
				case 7:
					_mostrarProntuarios(hospital);					
					break;
				case 8:
					_novoMedico(hospital);					
					break;
				case 9:
					_novoEnfermeiro(hospital);					
					break;
				case 10:
					_mostrarMedicosEenfermeiros(hospital);			
					break;
				case 11:
					_mostrarPacientes(hospital);
					break;
				case 0:
					Arquivos.gravarHospital(hospital, camHosp);
					break;
			}
		}
	}
	
	
	//1
	static void _registrarPaciente(Hospital hosp) {
		Paciente pac = new Paciente();
		pac.preencherDados();
		hosp.addPaciente(pac);
	}
	//2
	static void _novoProntuario(Hospital hosp) {
		if(hosp.getMedicos().size() >= 1 && hosp.getEnfermeiros().size() >= 1) {
			//processo de selecao do paciente.
			List<String> nomesPac = hosp.getNomesPacientes(); 
			String strPacSelecionado = EntDados.pedirStringPorOpcoes(nomesPac.toArray(), "Insira o paciente pertencente ao novo prontuario:");
			int posPac = nomesPac.indexOf(strPacSelecionado);
			Paciente pac = new Paciente();
			pac = hosp.getPacientes().get(posPac);
			
			//--------
			Prontuario pro = new Prontuario();
			//processo de preechimento de dados, ETAPA 1.
			pro.preencherDadosEtp1(pac);
			hosp.addEmAtendimentoEnfermagem(pro);
		}
		else {
			JOptionPane.showMessageDialog(null, "Ainda nao existem medicos ou enfermeiros suficientes cadastrados.");
		}
	}
	//3
	static void _addNaListaDeEspera(Hospital hosp) {
		
		if(hosp.getEmAtendimentoEnfermagem().size() >= 1) {

			// processo de selecao do prontuario.
			List<String> nomesEsperandoAvalRisco = hosp.getNomesEsperandoAvalRisco();
			String strProSelecionado = EntDados.pedirStringPorOpcoes(nomesEsperandoAvalRisco.toArray(), "TO DO");
			int posPro = nomesEsperandoAvalRisco.indexOf(strProSelecionado);
			Prontuario pro = new Prontuario();
			pro = hosp.getEmAtendimentoEnfermagem().get(posPro);
			
			// processo de selecao do enfermeiro.
			List<String> listaEnfNomes = new ArrayList<String>();
			listaEnfNomes = hosp.getNomesEnfermeiros();
			String enfSelecionado = EntDados.pedirStringPorOpcoes(listaEnfNomes.toArray(), "Selecione o enfermeiro responsavel.");
			int posEnf = listaEnfNomes.indexOf(enfSelecionado);
			Enfermeiro enf = hosp.getEnfermeiros().get(posEnf);
			
			
			hosp.getEmAtendimentoEnfermagem().remove(pro);
			//processo de preechimento de dados ETAPA 2.
			pro.preencherDadosEtp2(enf);
			

			if(pro.getRisco() == Hospital.grausDeRisco[3]) {//Vermelho - ATENDIMENTO IMEDIATO!
				hosp.getEmAtendimentoMedico().add(pro);// o atendimento é feito na hora, basta aguardar o medico dar o diagnostico.
				JOptionPane.showMessageDialog(null, "ENVIANDO PACIENTE PARA ATENDIMENTO IMEDIATO!");
			}
			else {
				hosp.addNaListaDeEspera(pro);//coloca numa posicao que depende do risco + TO-DO: e data de entrada.
			}
			
			
			
		
		}
		else {
			JOptionPane.showMessageDialog(null, "Nao existem prontuarios de pacientes esperando sua avaliação de risco.");
		}
		
	}
	//4
	static void _chamarProximoNaLista(Hospital hosp) {
		if(hosp.getListaDeEspera().size() >= 1) {	
			
			Prontuario pro = hosp.getListaDeEspera().get(0);//primeiro da lista sera chamado.
			hosp.getListaDeEspera().remove(pro);
			hosp.getEmAtendimentoMedico().add(pro);

		}
		else {
			JOptionPane.showMessageDialog(null, "Nao existem prontuarios de pacientes na lista de espera.");
		}
	}
	//5
	static void _finalizarDiagnostico(Hospital hosp) {
		if(hosp.getEmAtendimentoMedico().size() >= 1) {
			
			// processo de selecao do pronturio.
			List<String> nomesEsperandoDiag = hosp.getNomesEsperandoDiag(); 
			String strProSelecionado = EntDados.pedirStringPorOpcoes(nomesEsperandoDiag.toArray(), "Selecione o prontuario do paciente que desejas finalizar o diagnostico medico.");
			int posPro = nomesEsperandoDiag.indexOf(strProSelecionado);
			Prontuario pro = new Prontuario();
			pro = hosp.getEmAtendimentoMedico().get(posPro);
			
			
			// processo de selecao do medico.
			List<String> listaMedNomes = new ArrayList<String>();
			listaMedNomes = hosp.getNomesMedicos();
			String medSelecionado = EntDados.pedirStringPorOpcoes(listaMedNomes.toArray(), "Selecione o medico responsavel.");
			int posMed = listaMedNomes.indexOf(medSelecionado);
			Medico med = hosp.getMedicos().get(posMed);
			
			//remove desta lista, para em seguida adicionar em outra.
			hosp.getEmAtendimentoMedico().remove(pro);
			
			// processo de preechimento de dados etapa 3.
			pro.preencherDadosEtp3(med);
			
			if(pro.isFoiInternado()) {//internacao.
				hosp.getInternados().add(pro);
			}
			else {//alta medica na hora.
				hosp.getAltasMedicas().add(pro);
			}
			
			

		}
		else {
			JOptionPane.showMessageDialog(null, "Não há prontuarios de pacientes esperando a finalização do diagnóstico médico.");
		}
	}
	
	//6
	static void _internadoAltaHospitalar(Hospital hosp) {
		if(hosp.getInternados().size() >= 1) {
			
			// selecao do prontuario.
			List<String> nomesInternados = hosp.getNomesInternados(); 
			String strProSelecionado = EntDados.pedirStringPorOpcoes(nomesInternados.toArray(), "TO DO");
			int posPro = nomesInternados.indexOf(strProSelecionado);
			Prontuario pro = new Prontuario();
			pro = hosp.getInternados().get(posPro);	
			
			
			hosp.getInternados().remove(pro);
			
			pro.preencherDadosEtp4();//alta hospitalar.
			hosp.getAltasMedicas().add(pro);
			
		}
		else {
			JOptionPane.showMessageDialog(null, "Não há prontuarios de pacientes internados.");
		}
	}
	//7
	static void _mostrarProntuarios(Hospital hosp) {//pega todos os prontuarios, formata eles e exibe.
		
		String dadosProntuarios = hosp.formatarDadosProntuarios();
		SaiDados.JOptionPaneComScroller(dadosProntuarios, "Dados prontuarios:", 400, 350);
		
	}
	//8
	static void _novoMedico(Hospital hosp) {
		Medico med = new Medico();
		med.preencherDados();
		hosp.addMedico(med);
	}
	//9
	static void _novoEnfermeiro(Hospital hosp) {
		Enfermeiro enf = new Enfermeiro();
		enf.preencherDados();
		hosp.addEnfermeiro(enf);
	}
	//10
	static void _mostrarMedicosEenfermeiros(Hospital hosp) {
		List<String> listaMedNomes = new ArrayList<String>();
		String medNomes = "Nome  |  CRM\n________\n";
		listaMedNomes = hosp.getNomesMedicos();
		for(String nome: listaMedNomes) {
			medNomes += nome+"\n";
		}
		
		
		SaiDados.JOptionPaneComScroller(medNomes,"Nomes medicos",400,400);
		List<String> listaEnfNomes = new ArrayList<String>();
		String enfNomes = "Nome  |  COREN\n________\n";
		listaEnfNomes = hosp.getNomesEnfermeiros();
		for(String nome: listaEnfNomes) {
			enfNomes += nome+"\n";
		}
		
		
		SaiDados.JOptionPaneComScroller(enfNomes,"Nomes enfermeiros:",400,400);
	}

	//11
	static void _mostrarPacientes(Hospital hosp) {
		String dadosPacientes = hosp.formatarDadosPacientes();
		SaiDados.JOptionPaneComScroller(dadosPacientes, "Dados pacientes:", 400, 400);
		//TO DO
	}



}
