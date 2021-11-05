package principal;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

public class Prontuario implements Serializable{
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//etapa 1: os dados do paciente sao informados.
	private Paciente  paciente;
	private String    id;//numero indentificador do prontuario. 
	private LocalDateTime dataEntrada;// data e horario da chegada do paciente ao hospital.
	
	
	//etapa 2: a enfermagem avalia o nivel de risco do paciente.
	private Enfermeiro enfermeiroResp;// Enfermeiro responsavel.
	private String     risco;         // risco. Pode ser vermelho, amarelo, verde e azul
	private LocalDateTime  dataAvalRisco; //data e hora em que o enfermeiro avaliou o risco do paciente.
	
	
	//etapa 2.5: o paciente aguarda ser chamado para receber uma decisao final do medico.
	
	
	//etapa 3: o medico dá o diagnostico e decide se o paciente deve ser internado ou não.
	private Medico    medicoResp; // Medico responsavel.
	private String    diagnostico;
	private LocalDateTime dataDiag;   // data e hora no qual o medico deu o diagnostico final e decidiu se o paciente deveria ser internado ou não.
	
	//etapa 3.1: se a decisao foi de nao ser internado, o paciente recebe alta medica na hora.
	//etapa 3.2: caso contrario, ele é internado. Passado um tempo, no qual sua recuperação for satisfatoria, ele recebe alta medica.
	private boolean foiInternado = false;// true: foi Internado, false: teve alta medica sem precisar de internação.
	private LocalDateTime dataSaida;// data e hora da saida ao hospital (ou seja, alta medica).
	
	
	
	
	public Prontuario() {}
	
	/*
	public Prontuario(Paciente paciente, String diagnostico, float valorConsulta, LocalDate dataPag, Medico medResponsavel,
			Enfermeiro enfResponsavel) {
		//super();
		this.paciente = paciente;
		this.diagnostico = diagnostico;
		this.valorConsulta = valorConsulta;
		this.dataPag = dataPag;
		this.medicoResp = medResponsavel;
		this.enfermeiroResp = enfResponsavel;
	}*/
	
	
	/*
	public void preencherDados(Paciente pac, Enfermeiro enfResp, Medico medResp) {
		preencherDadosEtp1(pac);
		preencherDadosEtp2(enfResp);
		preencherDadosEtp3(medResp);
		preencherDadosEtp4();
	}*/
	
	//etapa 0: cadastrar o paciente no sistema.
	
	// É preciso passar um paciente ja criado como argumento, pois ele deve ser um paciente ja registrado no sistema. 
	// A mesma logica se aplica ao enfermeiro e medico, so poderao ser adicinados ao prontuario se ele ja estao registrados no sistema.
	public void preencherDadosEtp1(Paciente pac) {//etapa 1
		//paciente, id, dataEntrada.
		this.id = EntDados.pedirString("Insira o numero ID do prontuario");
		this.paciente = pac;
		this.dataEntrada = EntDados.pedirData_e_Hora("Insira a data e hora de chegada do paciente.");
	}
	
	public void preencherDadosEtp2(Enfermeiro enfResp) {//etapa 2
		//enfermeiro, risco, dataAvaliacaoRisco.
		this.enfermeiroResp = enfResp;
		
		this.risco = EntDados.pedirStringPorOpcoes(Hospital.grausDeRisco, "Selecione o grau de urgencia do caso do paciente:");
		
		this.dataAvalRisco = EntDados.pedirData_e_Hora("Insira a data e hora no qual a enfermagem instaurou um risco:");
		
	}
	public void preencherDadosEtp3(Medico medResp) {//etapa 3
		
		this.medicoResp = medResp;
		this.diagnostico = EntDados.pedirString("Insira o diagnóstico dado pelo medico:");
		this.dataDiag = EntDados.pedirData_e_Hora("Insira a data e hora no qual o medico concluiu com o diagnostico:");
		
		int escolha = JOptionPane.showConfirmDialog(null, "O paciente teve alta medica sem internação? (Yes: Sim/Cancel: Não)");
		if(escolha == JOptionPane.YES_OPTION) {		
			this.foiInternado = false;
			this.dataSaida = this.dataDiag;// tem alta medica na hora.
		}
		else {
			//JOptionPane.showMessageDialog(null, "O paciente agora está registrado como internado.");
			this.foiInternado = true;
		}
	}
	
	public void preencherDadosEtp4() {
		this.dataSaida = EntDados.pedirData_e_Hora("Insira data e hora de alta hospitalar:");
	}
	
	
	public String formatarDados() {
		String nInf = "Não informado.";
		
		// fiz uso de "Ternarios" para pôr ifs dentro da String.
		String dadosProntuario = "ID Prontuário: "+this.id +
								 "---Paciente:\n"+this.paciente.formatarDados()+
								 "\n---Enfermeiro responsavel:\n"+ (this.enfermeiroResp != null ? this.enfermeiroResp.formatarDados(): nInf)+
								 "\n---Médico responsavel:\n"+ (this.medicoResp != null ? this.medicoResp.formatarDados() : nInf)+
								 "\n---Risco: "+ (this.risco != null ? this.risco : "Não informado.")+
								 "\nData da avaliação do risco: "+ (this.dataAvalRisco != null ? this.dataAvalRisco.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")): nInf)+
								 "\n---Diagnóstico: "+(this.diagnostico != null ? this.diagnostico : nInf)+
								 "\nData diagnosticação: "+(this.dataDiag != null ? this.dataDiag.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")): nInf) + 
								 "\nData entrada: "+(this.dataEntrada != null ? this.dataEntrada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")): nInf) +
								 "\nData saída: "+(this.dataSaida != null ? this.dataSaida.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) : nInf);
		

		
		return dadosProntuario;
	}
	
	
	public Paciente getPaciente() {
		return this.paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	
	public boolean isFoiInternado() {
		return foiInternado;
	}
	public void setFoiInternado(boolean foiInternado) {
		this.foiInternado = foiInternado;
	}
	public Medico getMedResponsavel() {
		return this.medicoResp;
	}
	public void setMedResponsavel(Medico medResponsavel) {
		this.medicoResp = medResponsavel;
	}
	public Enfermeiro getEnfResponsavel() {
		return this.enfermeiroResp;
	}
	public void setEnfResponsavel(Enfermeiro enfResponsavel) {
		this.enfermeiroResp = enfResponsavel;
	}
	public String getDiagnostico() {
		return this.diagnostico;
	}
	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}
	public String getRisco() {
		return risco;
	}
	public void setRisco(String risco) {
		this.risco = risco;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public LocalDateTime getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(LocalDateTime dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public LocalDateTime getDataAvalRisco() {
		return dataAvalRisco;
	}
	public void setDataAvalRisco(LocalDateTime dataAvalRisco) {
		this.dataAvalRisco = dataAvalRisco;
	}
	public LocalDateTime getDataDiag() {
		return dataDiag;
	}
	public void setDataDiag(LocalDateTime dataDiag) {
		this.dataDiag = dataDiag;
	}
	public LocalDateTime getDataSaida() {
		return dataSaida;
	}
	public void setDataSaida(LocalDateTime dataSaida) {
		this.dataSaida = dataSaida;
	}

}
