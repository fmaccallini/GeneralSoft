package GS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Encriptador {

	public Encriptador() {
		BufferedReader br = null;
    	try{
    		String Source = "c:\\Users\\BANGHO\\Downloads\\SoftwareData.sdt";
    		File archivo = new File (Source);
    		if(archivo.exists()){
    			FileReader fr = new FileReader(archivo);    			
    			br = new BufferedReader(fr);
    			String s = br.readLine();
    			while(s!=null) {
    				System.out.println(Util.Encriptar(s));
    				s = br.readLine();
    			}    			   		
    		}    		
    	}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
			} catch (Exception ex) { }
		}
	}

	public static void main(String[] args) {
		new Encriptador();

	}

}
