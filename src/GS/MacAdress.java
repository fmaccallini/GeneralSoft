package GS;
import com.eaio.uuid.UUID;

public class MacAdress {
	public int SN= 0;
	public String Serial="";
	public MacAdress() {
		  UUID u = new UUID();
		  String s = u.toString();
		  System.out.println(s);
		  Serial = s;
		  String datos[] =  s.split("-");
		  s = datos[datos.length-1];
		  String caracteres = " -_:[]";
			for (int i=0; i<caracteres.length(); i++) {	
				s = s.replace(String.valueOf(caracteres.charAt(i)), "");// Reemplazamos los caracteres
			}//for i
			caracteres = "abcdefghijklmnñopqrstuvwxyzABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
			for (int i=0; i<caracteres.length(); i++) {	
				s = s.replace(String.valueOf(caracteres.charAt(i)), String.valueOf(i));// Reemplazamos los caracteres
			}//for i
		if(s.length()>9){ s = s.substring(s.length()-9, s.length());}
		SN =  Math.abs(Integer.valueOf(s));
	}
}
