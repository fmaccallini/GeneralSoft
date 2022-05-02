package GS;
public class Calcular_CA {
  public int CD=0; 
  public int SN=0;
  public int Tipo=0;
  
  	public Calcular_CA(){
  	}
  	
  	public int CA_Calcular(int sn, String nombre) { 
  		SN = sn;
  		//System.out.println("nombre: "+nombre); 
  		Tipo = nombre.hashCode();  
  		CA_Calcular();
  		return CD;
  	}	
  	public int CA_Calcular() { 
  		Obtener_Digitos dig = new Obtener_Digitos(SN);
  		Obtener_DigitosNew gen = new Obtener_DigitosNew(SN);
  		//System.out.println(Math.abs(Tipo));  		
  		switch(Math.abs(Tipo)){
  			case 90576681: //RXFutbol3D (v1)
  				CD = Math.abs((dig.n1*dig.n3)-dig.n2 + 125); 
  				break;
  			case 1755122063: //RXFutbol3D (v2)
  			case 1661673965: //Pizarra (V2)
  			case 1813281667: //CampusVirtual3D
  			case 1487109664: //RXFutbol3D AER
  			case 2115153215: //CampusVirtual3D Pizarra 
  				CD = Math.abs((dig.n1*dig.n3)-dig.n2 - 125);  
  				break;    				
  			case 1085590832: //RXFutbol3D 2014 (v3)
  			case 1555444494: //RXFutbol3D 2014 (v3) Pizarra
  				CD = Math.abs((dig.n1*dig.n2)+ dig.n3 + 234);  
  				break;  				
  			case 1085590833: //RXFutbol3D 2015 (v4)
  			case 662313459:  //RXFutbol3D 2015 (v4) Pizarra
  				CD = Math.abs(((dig.n1*dig.n3*38)/5) - dig.n2 + 437);  
  				break;
  			case 1085590834: //RXFutbol3D 2016 (v16) y Pizarra
  				CD = Math.abs(((dig.n1*dig.n3*25)/3) - dig.n2 + 537);  
  				break;
  			case 1085590835: //RXFutbol3D 2017 (v17)
  			case 1941823120: //RXFutbol3D 2017 Pizarra (v17) 
  				CD = Math.abs(((dig.n1*dig.n3*25)/3) + dig.n2 - 357);  
  				break;
  			case 975977211: //RXFutbol3D 2017 ATFA (v17)
  			case 571800354: //RXFutbol3D 2017 Pizarra ATFA (v17) 
  				CD = Math.abs(((dig.n1*dig.n2*25)/3) - dig.n3 + 37);  
  				break;
  			case 430717193: //RXFutbol3D 2017 Pizarra Futsal (v17) 
  				CD = Math.abs(((dig.n1*dig.n2*25)/3) + dig.n3 + 234);  
  				break;  				  			
  			case 1085590836: //RXFutbol3D 2018 (v18)
  			case 1941823119: //RXFutbol3D 2018 Pizarra (v18) 
  				CD = Math.abs(((dig.n1*dig.n2*25)/3) - dig.n3 + 587);  
  				break;
  			case 1085590837: //RXFutbol3D 2019 (v19)
  			case 1941823118: //RXFutbol2D 2019 Pizarra (v19) 
  				CD = Math.abs(((dig.n1*dig.n3*20)/3) - dig.n2 + 239);  
  				break;  
  			case 1085590859: //RXFutbol3D 2020 (v20)
  			case 1941823096: //RXFutbol2D 2020 Pizarra (v20)   	
  			case 1085590860: //RXFutbol3D 2021 (v21)
  			case 1941823095: //RXFutbol2D 2021 Pizarra (v21) 
  			case 1085590861: //RXFutbol3D 2022 (v22)
  			case 1941823094: //RXFutbol2D 2022 Pizarra (v22) 
  				CD = Math.abs(((Integer.parseInt(gen.cod)*dig.n3*22)/4) + dig.n2 - 129);  
  				break;
  			case 211881984: //RXFutbol 2011
				CD  = Math.abs(dig.n1*dig.n2*dig.n4-dig.n1); 
				break;
			case 130629054: // RXFutbol2012
				CD = Math.abs(dig.n1*dig.n2*dig.n4-dig.n1+dig.n2-1234);
				break;				
  			case 218453863:
  				CD = Math.abs((dig.n3*dig.n2)+dig.n1 + 15);  //RXRunning
  				break;
  			case 986948483:
  				CD = Math.abs((dig.n1*dig.n3)-dig.n2 - 235); //RXBasquet Pizarra Tactica Digital
  				break;  
  			case 34860831:
  				CD = Math.abs((dig.n1*dig.n3)-dig.n2 - 386); //RXFutsal Pizarra Tactica Digital
  				break;  
  			case 1188326634:
  				CD = Math.abs((dig.n1*dig.n3)-dig.n2 - 193); //RXHandball Pizarra Tactica Digital
  				break; 
  			case 53133493:
  				CD = Math.abs((dig.n1*dig.n3)-dig.n2 - 437); //RXHockey Pizarra Tactica Digital
  				break;  
  			case 1082931191:
  				CD = Math.abs((dig.n1*dig.n3)-dig.n2 - 301); //RXRugby Pizarra Tactica Digital
  				break;  
  			case 186808355:
  				CD = Math.abs((dig.n1*dig.n3)-dig.n2 - 583); //RXTenis Pizarra Tactica Digital
  				break; 
  			case 1694952163:
  				CD = Math.abs((dig.n1*dig.n3)-dig.n2 - 55); //RXVoley Pizarra Tactica Digital
  				break;   		
  			case 883465998:
  				CD = Math.abs((dig.n1*dig.n3)+dig.n2 - 151); // RXBasquet3D Pizarra Tactica Digital
  				break;
  			case 973990161:
  				CD = Math.abs(dig.n1)*dig.n2+dig.n2; // RXTactico
  				break;
  			case 859297828:
  				CD = Math.abs(((Integer.parseInt(gen.cod)*dig.n2*25)/4) - dig.n3 + 236);   // RX Video Analisis 2021
  			case 859297829:
  				CD = Math.abs(((Integer.parseInt(gen.cod)*dig.n3*22)/4) - dig.n2 + 129);   // RX Video Analisis 2020
  				break;
  			case 859297851:
  				CD = Math.abs(((dig.n1+475)*dig.n3)-2*(dig.n2+dig.n1)); // RX Video Analisis 2019
  				break; 
  			case 859297852:
  				CD = Math.abs(((dig.n1+475)*dig.n1)-2*(dig.n2+dig.n1)); // RX Video Analisis 2018
  				break; 
  			case 859297853:
  				CD = Math.abs(((dig.n1+475)*dig.n1)+2*dig.n2-dig.n1); // RX Video Analisis 2017
  				break;  	  				
  			case 874841493:
  				CD = Math.abs(((dig.n1+475)*dig.n2)+2*dig.n1-dig.n2); // RX Video Analisis 2017 ATFA
  				break;  
  			case 859297854:
  				CD = Math.abs((dig.n1*dig.n1)+2*dig.n2+dig.n1); // RX Video Analisis 2016
  				break;
  			case 859297855:
  				CD = Math.abs((dig.n1*dig.n1)+dig.n2-dig.n1); // RX Video Analisis 2015
  				break;
  			case 1258449995:
  				CD = Math.abs(dig.n1)*dig.n2-dig.n2; // RX Video Analisis 2014
  				break;  			
  			case 870035781:
  				CD = Math.abs(((dig.n1+475)*dig.n2)-2*(dig.n3+dig.n1)); // RX Video Live 2019
  				break;
  			case 2031842476:
  				CD = (Math.abs(dig.n6) * dig.n2); // Planner 
  				break;
  			case 1844329789:// RXGestion v1.0
  				CD = Math.abs((dig.n1*dig.n3)+dig.n3 - 131); 
  				break;  
  			case 1800594463:// RXGestion 2015 (v2.0) 
  				CD = Math.abs((dig.n1*dig.n3)+dig.n3 - 222); 
  				break;  
  			case 1800594464:// RXGestion 2016 (v16.0) 
  				CD = Math.abs((dig.n1*dig.n3)-dig.n3 + 222); 
  				break;    
  			case 1800594466:// RXGestion 2018 (v18.0) 
  				CD = Math.abs((dig.n1*dig.n2)-dig.n3 + 222); 
  				break;   
  			case 1800594467:// RXGestion 2019 (v19) 
  				CD = Math.abs((dig.n1*dig.n3)-dig.n2 + 333); 
  				break; 
  			case 1800594489:// RXGestion 2020 (v20) 
  			case 1800594490:// RXGestion 2021 (v21) 
  			case 1800594491:// RXGestion 2022 (v22) 
  				CD = Math.abs((Integer.parseInt(gen.cod)*dig.n2)-dig.n3 + 125);  
  				break;   	
  				
  	  	}
  		return CD;
	}	
}
