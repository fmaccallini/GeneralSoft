package GS;

public class Obtener_DigitosNew {
	String cod = "";
	Obtener_DigitosNew(int s){		
		String Serial = String.valueOf(s);
		for(int i=Serial.length()-1;i>=0;i--) {
			int d = Integer.parseInt(Serial.substring(i,i+1));
			if(i%2==0) {
				cod = cod + String.valueOf((d+3)%10);
			}else {
				cod = cod + String.valueOf((d+5)%10);
			}
		}
	}
}
