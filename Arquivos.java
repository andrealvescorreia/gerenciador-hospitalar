package principal;
import java.io.*;

public class Arquivos {
	
	
	//serialização.
	public static void gravarHospital(Hospital hosp, String caminho){
        try(FileOutputStream fos = new FileOutputStream(caminho)){
            try(ObjectOutputStream oos = new ObjectOutputStream(fos)){
                // Metodo para escrever no arquivo
                oos.writeObject(hosp);
            }
        }catch(IOException e){
            //e.getMessage();
            e.printStackTrace();
        }

    }

	//deserialização.
    public static Hospital lerHospital(String caminho) throws IOException, ClassNotFoundException{

    	Hospital hosp = new Hospital();
        try(FileInputStream fis = new FileInputStream(caminho)){
            try(ObjectInputStream ois = new ObjectInputStream(fis)){
                // Metodo para ler do arquivo
                hosp = (Hospital)ois.readObject();
            }
        }catch(IOException | ClassNotFoundException e){
            //e.getMessage();
            e.printStackTrace();
            throw e;//lanca a excessação para quem chamou esta funcao.
        }

        return hosp;
    }
}
