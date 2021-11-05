package principal;

import java.time.LocalDate;
/*import java.util.ArrayList;
import java.util.List;*/
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class EntDados { //Entrada de dados. Se resume ao usuario inserir texto no joptionpane.
	
	// Esse padrao de "pedirAlgo" permite lidar com excessoes gerais,
	// e evitar repeticao de codigo. Ou seja,
	// permite uma padronização em como o input do usuario é recebido.

	
	
	static String pedirStringPorOpcoes(Object[] opcoes, String pedido) {
		String escolha = (String)JOptionPane.showInputDialog(null, pedido, 
                null, JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
        return escolha;
	}
	
	public static String pedirString(String pedido) {
		String recebido = JOptionPane.showInputDialog(pedido);
		return recebido;
	}
	
	public static int pedirInt(String pedido) {
		
		try {
			int recebido = Integer.parseInt(JOptionPane.showInputDialog(pedido));
			return recebido;
		}catch(Exception e) {
			System.out.println("pedirInt: Erro na conversao para int. retornado valor -1.");
			return -1;
		}
		
	}
	
	public static float pedirFloat(String pedido) {
		
		try {
			float recebido = Float.parseFloat(JOptionPane.showInputDialog(pedido));
			return recebido;
			
		} catch(Exception e) {
			System.out.println(e);
			return -1;//retorna -1 se ocorreu algum erro.
		}
		
	}
	
	public static double pedirDouble(String pedido) {
		double recebido = Double.parseDouble(JOptionPane.showInputDialog(pedido));
		return recebido;
	}
	
	
	public static char pedirChar(String pedido) {
		try {
			char recebido = JOptionPane.showInputDialog(pedido).charAt(0);
			return recebido;
		}catch(Exception e) {
			System.out.println(e);
			return 'n';
		}
		
	}
	
	
	private static LocalDate erroDataInvalida(String pedido) { // uso de recursão.
		int escolha = JOptionPane.showConfirmDialog(null, "Data invalida, tentar denovo?");
		if(escolha == JOptionPane.YES_OPTION) {
			return pedirData(pedido);
		}
		else {
			return null;
		}
	}
	private static LocalTime erroHorarioInvalido(String pedido) {
		int escolha = JOptionPane.showConfirmDialog(null, "Hora invalida, tentar denovo?");
		if(escolha == JOptionPane.YES_OPTION) {
			return pedirHorario(pedido);
		}
		else {
			return null;
		}
	}
	public static LocalTime pedirHorario(String pedido) {
		
		
		
        
        
        String[] opcoes = {"Agora","Horario Manual"};
		String escolha = EntDados.pedirStringPorOpcoes(opcoes,"");
		if(escolha.equals(opcoes[0])) {
			return LocalTime.now();
			
		}
		else if(escolha.equals(opcoes[1])) {
			
			int hora, min;
			
			JTextField fldHora = new JTextField(3);//Field Hora
	        JTextField fldMin = new JTextField(3); //Field Minuto
	        
	        JPanel mensagem = new JPanel();
	        mensagem.add(fldHora);
	        mensagem.add(new JLabel(":"));
	        mensagem.add(fldMin);
	        
	        int resultado = JOptionPane.showConfirmDialog(null, mensagem, pedido, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
	        
	        if (resultado == JOptionPane.OK_OPTION) {//usuario deu OK.
	        	String strHora = fldHora.getText();
	        	String strMin = fldMin.getText();
	        	
	        	try {
	        		hora = Integer.parseInt(strHora);
	        		min = Integer.parseInt(strMin);
	        	} catch(Exception e){
	        		return erroHorarioInvalido(pedido);
	        	}
	        	if(hora >= 23 || hora <= 0 || min >= 59 || min <= 0) {
	        		return erroHorarioInvalido(pedido);
	        	}
	        	//else
	        	LocalTime horario = LocalTime.of(hora, min);
	        	return horario;
	        	
	        }
	        
		}
		
		return null;//user deu CANCEL ou fechou a janela.
	} 
	
	public static LocalDateTime pedirData_e_Hora(String pedido) {
		JOptionPane.showMessageDialog(null, pedido);
		LocalDate data = pedirData("");
		LocalTime hora = pedirHorario("");
		
		
		LocalDateTime data_e_hora = LocalDateTime.of(data, hora);
		return data_e_hora;
	}
	
    public static LocalDate pedirData(String pedido) {
    	
        
        String[] opcoes = {"Hoje","Data Manual"};
		String escolha = EntDados.pedirStringPorOpcoes(opcoes,"");
		
		if(escolha.equals(opcoes[0])) {
			return LocalDate.now();
			
		}
		else if(escolha.equals(opcoes[1])) {		
			
			int dia, mes, ano;   

	        
	        JTextField fldAno = new JTextField(4);
	        JTextField fldMes = new JTextField(3);
	        JTextField fldDia = new JTextField(3);
	        
	        JPanel mensagem = new JPanel();
	        mensagem.add(fldDia);
	        mensagem.add(new JLabel("/"));
	        mensagem.add(fldMes);
	        mensagem.add(new JLabel("/"));
	        mensagem.add(fldAno);
			
			
		    int resultado = JOptionPane.showConfirmDialog(null, mensagem, pedido, JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (resultado == JOptionPane.OK_OPTION) {//usuario deu OK.
		        String sDia = fldDia.getText();
		        String sMes = fldMes.getText();
		        String sAno = fldAno.getText();
		        //JOptionPane.showMessageDialog(null, "Voce inseriu: " + sDia + "/" + sMes + "/" + sAno);
		
		        try {
		            dia = Integer.parseInt(sDia);
		            mes = Integer.parseInt(sMes);
		            ano = Integer.parseInt(sAno);
		            
		            
		        } catch (Exception e) {
		            
		            return erroDataInvalida(pedido);
		            
		        }
		        
		        // monte de if para checar se a data é invalida. Se sim, retorna null.
		    	if(mes == 3 || mes == 4 || mes == 6 || mes == 9 || mes == 11) {
		    		if(dia > 30) {
		    			return erroDataInvalida(pedido);
		    		}
		    	}
		    	else if(mes == 1 || mes == 5 || mes == 7|| mes == 8|| mes == 10|| mes == 12) {
		    		if(dia > 31) {
		    			return erroDataInvalida(pedido);
		    		}
		    	}
		    	else if (ano%4!=0 & dia < 28) {//caso: mes de fevereiro (2); ano nao é bissexto; dia do mes excede 28.
		    		return erroDataInvalida(pedido);
		    	}
		    	else if (ano%4==0 & dia > 29){//caso: mes de fevereiro (2); ano É bissexto; dia do mes excede 29.
		    		return erroDataInvalida(pedido);
		    	}
		    	
		    	//else
		    
		    	LocalDate data = LocalDate.of(ano,mes,dia);//segue esse padrao estranho ai 1999/12/28
		    	return data;
		    
		    }
        }
        return null;//usuario fechou a janela ou deu CANCEL.
    }
	
}
// Todas as funcoes/metodos sao estaticos pois assim 
// não é preciso instanciar a classe para os acessar.