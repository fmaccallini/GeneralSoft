package GS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Obtener_SN {
	public boolean PrimeraVez = true;
	public String Serial="";
	public int SN = 0;
	public int Modo = -1;
	//private int CodigoEnConfig=-1;
	
	public Obtener_SN(String Name_Soft) {
		try{
		/*	File archivo = new File ("LocalData/Modo");
			//String linea = "";
			if(archivo.exists()){	    	
	    		FileReader fr = new FileReader (archivo);
	    		BufferedReader br = new BufferedReader(fr);
	    		//linea = br.readLine(); //Leo: Modo@;Codigo de activacion
	    		Modo = Integer.parseInt(br.readLine());
	    		br.close(); 
	    		//String Datos[] = linea.split("@;");
	    		//	Modo = Integer.parseInt(Datos[0]);
			}   		
			String curDir = System.getProperty("user.dir");
			curDir = curDir.replace("\\", "/");		
			if(Modo == -1){ //Si Modo == -1 es la primera vez que lo ejecuto o simplemente no existe el archivo de Modo
				ComprobarConfig();
				int[] modos = {0,4,5,6,1,2,3};
				if(CodigoEnConfig==-1){ //Quiere decir que no existe el config.
					int i=0;
					if (Util.isMac()) {
						System.out.println("Es una mac");
						modos = new int[]{5,6};  //Si es una Mac empiezo directamente viendo el serial por MacAdres
					}else{		
						if (Util.isUnix()) {
							System.out.println("Es Unix o Linux");
							modos = new int[]{5,6};  //Si es una Mac empiezo directamente viendo el serial por MacAdres
						}	
					}
					while(SN==0 && i<modos.length){		
						ObtenerSN(modos[i]);
						i++;
					}
					if(SN!=0){
						Modo = modos[i-1];
					}
				}else{ //Existe el config pero no el Modo
					PrimeraVez = false;					
					int i=0;
					while(i<modos.length){		
						ObtenerSN(modos[i]);
						Calcular_CA Cmp = new Calcular_CA();	
						int Cod = Cmp.CA_Calcular(SN, Name_Soft);
						if(Cod == CodigoEnConfig){							
							Modo = modos[i];
							i=7;
						}else{
							i++;
						}
					}
				}
//Creo el archivo para leer el modo				
				File file2 = new File("LocalData/Modo");
				if(!file2.exists()){file2.createNewFile();}    					
				PrintWriter salida = new PrintWriter( new BufferedWriter(new FileWriter(file2)));
				salida.println(Modo); //Escribo el Modo
				salida.close();
			}else{
				PrimeraVez = false;
				ObtenerSN(Modo);
			}*/
			Modo = 4;
			if (Util.isMac() ) {
				System.out.println("Es una mac");
				Modo = 6;  //Si es una Mac empiezo directamente viendo el serial por MacAdres
			}		
			if (Util.isUnix()) {
				System.out.println("Es Unix o Linux");
				Modo = 6;  //Si es una Mac empiezo directamente viendo el serial por MacAdres
			}	
			ObtenerSN(Modo);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void ObtenerSN(int modoDeObtencion){
		SN = 0;	
		Serial = "";
		try{
			switch(modoDeObtencion){
				case 0:
					ObtenerPorBat();
					break;					
				case 1:
					ObtenerPorWMIC_UUID();
					break;
				case 2:
					ObtenerPorWMIC_BIOS();
					break;
				case 3:
					ObtenerPorWMIC_DISKDRIVE();
					break;
				case 4:
					ObtenerPorDiskUtils();
					break;
				case 5:
					ObtenerPorMacAdress();
					break;
				case 6:
					ObtenerPorUserHome();
					break;
			}
			/*System.out.println("*******************************");
			System.out.println("Numero de Serie "+modoDeObtencion+":");
			System.out.println(Serial);
			System.out.println(SN);*/
		}catch (Exception e){ 
			e.printStackTrace();
		}		
	}

/*************************************************************************************/
	private int ObtenerNumeros(String AConvertir){
		String caracteres = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
		for (int i=AConvertir.length(); i>0; i--) {	
			if(!caracteres.contains((AConvertir.substring(i-1, i)))){
				AConvertir = AConvertir.replace(AConvertir.substring(i-1, i)," ");
			}			
		}
		AConvertir = AConvertir.replace(" ","");
		for (int i=0; i<caracteres.length(); i++) {	
			AConvertir = AConvertir.replace(String.valueOf(caracteres.charAt(i)), String.valueOf(i));// Reemplazamos los caracteres
		}
		if(AConvertir.length()>9){ AConvertir = AConvertir.substring(AConvertir.length()-9, AConvertir.length());}
		return Math.abs(Integer.valueOf(AConvertir));
	}
	
/*************************************************************************************/
	private void ObtenerPorBat(){
		try{
			File f = new File("LocalData/e42fe6e99/e42fe6e99.exe");
			if(f.exists()){
				String curDir = System.getProperty("user.dir");
				curDir = curDir.replace("\\", "/");
				FileWriter fw = new FileWriter("LocalData/e42fe6e99/15a603279.bat"); 
				BufferedWriter bw = new BufferedWriter(fw); 
				PrintWriter salida = new PrintWriter(bw); 
				String rutas[] = curDir.split("/");
				salida.println("cd /"); 
				for(int i=0;i<rutas.length;i++){
					salida.println("cd "+rutas[i]); 
				}
				salida.println("cd LocalData");
				salida.println("cd e42fe6e99");
				salida.println("e42fe6e99.exe > 15a603279"); 				
				salida.close(); 			
				String bat = curDir + "/LocalData/e42fe6e99/15a603279.bat";				
				Runtime.getRuntime().exec("cmd.exe /K "+bat);
				Thread.sleep (1000); 
				File archivo = new File (curDir + "/LocalData/e42fe6e99/15a603279");
				int segundos=10;
				while (!archivo.exists() && segundos>0){ 
					Thread.sleep (1000);
					if(PrimeraVez){
						segundos = segundos - 1;
					}
				}//Si todavia no lo creo espero un poco mas				
				if (archivo.exists()){
					FileReader fr = new FileReader (archivo);    			
					BufferedReader br = new BufferedReader(fr);
					String s = new String();
					while((s=br.readLine())!=null){
						if(s.contains("Drive Serial Number")){						
							s = s.replace("Hard", "");
							s = s.replace("Drive", "");
							s = s.replace("Serial", "");
							s = s.replace("Number", "");
							Serial = s;
							SN = ObtenerNumeros(s);
						}
					}
					br.close();
					fr.close();
					archivo.delete();	
				}
			}else{
				System.out.println("El archivo e42fe6e99.exe no existe");
			} 
			File f2 = new File("LocalData/e42fe6e99/15a603279.bat");
			if(f2.exists()){f2.delete();}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
/*************************************************************************************/
	private void ObtenerPorWMIC_UUID(){
		try{
			Process p = Runtime.getRuntime().exec("cmd /C wmic csproduct get UUID");  
			BufferedReader in = new BufferedReader( new InputStreamReader(p.getInputStream()));  
			String line = in.readLine();  
			while ((line = in.readLine()) != null) {  					
				if(!line.contains("UUID") && !line.equals("")){
					Serial = line;
					SN = ObtenerNumeros(line);  
				}					
			} 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
/*************************************************************************************/
	private void ObtenerPorWMIC_BIOS(){
		try{
			Process p2 = Runtime.getRuntime().exec("cmd /C wmic bios get serialnumber");  
			BufferedReader in2 = new BufferedReader( new InputStreamReader(p2.getInputStream()));  
			String line2 = in2.readLine();  
			while ((line2 = in2.readLine()) != null) {  					
				if(!line2.equals("")){
					Serial = line2;
					SN = ObtenerNumeros(line2);  
				}					
			} 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
/*************************************************************************************/
	private void ObtenerPorWMIC_DISKDRIVE(){
		try{
			Process p3 = Runtime.getRuntime().exec("cmd /C wmic DISKDRIVE get serialnumber");  
			BufferedReader in3 = new BufferedReader( new InputStreamReader(p3.getInputStream()));  
			String line3 = in3.readLine();  
			while ((line3 = in3.readLine()) != null) {  					
				if(!line3.equals("")){
					Serial = line3;
					SN = ObtenerNumeros(line3);  
				}					
			} 
		}catch(Exception e){
			e.printStackTrace();
		}
	}
		
/*************************************************************************************/
	private void ObtenerPorDiskUtils(){
		try{
			Serial = DiskUtils.getSerialNumber("C");
			if(Serial.length()>9){ Serial = Serial.substring(0, 9);}
			SN = Math.abs(Integer.valueOf(Serial));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
/*************************************************************************************/
	private void ObtenerPorMacAdress(){
		try{
			MacAdress Mac = new MacAdress();
			Serial = Mac.Serial;
			SN = Mac.SN;
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
/*************************************************************************************/
	private void ObtenerPorUserHome(){
		try{
			Serial = System.getProperty("user.home");
			System.out.println("home: "+System.getProperty("user.home"));
			SN = ObtenerNumeros(Serial);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
/*************************************************************************************/
/*	private void ComprobarConfig(){
		try{
			File archivo = new File ("LocalData/Config.cnf");
		    if(archivo.exists()){
		    	FileReader fr = new FileReader (archivo);
		    	BufferedReader br = new BufferedReader(fr);
		    	CodigoEnConfig = Integer.parseInt(br.readLine()); //Leo el codigo de activacion
		    	br.close(); 
		    }   
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
	
	
/*************************************************************************************/
	/*public static void main(String[] args) {
		for(int i=0;i<7;i++){
			Obtener_SN OSN = new Obtener_SN(i);  			
			System.out.println("*******************************");
			System.out.println("Numero de Serie "+OSN.modoDeObtencion+":");
			System.out.println(OSN.Serial);
			System.out.println(OSN.SN);
		}
     }*/
}
