package GS;

public class Obtener_Digitos {
	int n1=0, n2=0, n3=0, n4=0, n5=0, n6=0;
	Obtener_Digitos(int s){
		int[] digitos = {0, 0, 0, 0, 0};
		int k = 0;
		while(k < 5) {
			digitos[k] = s % 10;
			s /= 10;
			k++;
		}
		n1=digitos[4]*10000+digitos[3]*1000+digitos[2]*100+digitos[1]*10+digitos[0]*1;
		n2=Math.max(digitos[0],digitos[1]);
		n2=Math.max(n2,digitos[2]);
		n2=Math.max(n2,digitos[3]);		
		n2=Math.max(n2,digitos[4]);			
		n3=n2-1;
		n4=n2-2;		
		n5=n2-3;	
		n6=n1+n2-25;
	}
}
