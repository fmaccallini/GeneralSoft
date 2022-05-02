package GS;

public class Idioma_General {
	    public String[] Menu;
	    public String[] Labels;
	    public String[] Excepcion;
	    public VariablesSoft Vars;
	    public final int ESPANOL=0, INGLES=1, PORTUGUES=2, ITALIANO=3; 

/*---------------------------------------------------------*/
	    public Idioma_General(int idioma){
	    	Inicializar();   	
	    	Vars = new VariablesSoft(1.0f, "", true, false);
	        SetIdioma(Vars.Idioma);
	        CargarExcepcion();
	    }
	    
/*---------------------------------------------------------*/
	    public Idioma_General(VariablesSoft vars){
	    	Inicializar();
	    	Vars = vars;
	        SetIdioma(Vars.Idioma);
	        CargarExcepcion();
	    }

	/*---------------------------------------------------------*/
	    public void SetIdioma(int id){
	    	Vars.Idioma = id;
			switch(Vars.Idioma){
				case ESPANOL:
					SetESPANOL();
					break;
				case INGLES:
					SetINGLES();
					break;
				case PORTUGUES:
					SetPORTUGUES();
					break;
		   }	
		   CambiarCaracteres();
	    }
	    
	/*---------------------------------------------------------*/
		public void Inicializar(){
			Menu = new String[0];
			Labels = new String[16];
			Excepcion = new String[0];
		}
		
	/*---------------------------------------------------------*/
		public void SetESPANOL(){
			Labels[0] = "N�mero de serie: ";
			Labels[1] = "C�digo de activaci�n:  ";
			Labels[2] = "Aceptar";
			Labels[3] = "C�digo de activaci�n";
			Labels[4] = "El c�digo de activaci�n ingresado no es Correcto.";   
			Labels[5] = "Copiar";
    		Labels[6] = "Versi�n: ";
    		Labels[7] = "Modo: ";
    		Labels[8] = "Aceptar";
    		Labels[9] = "Debe estar conectado a internet para poder activar el software.";
    		Labels[10] = "La fecha en su PC no es la correcta.";
    		Labels[11] = "El software se encuentra vencido. Contactarse con info@rxfutbol.com para obtener una nueva licencia.";
    		Labels[12] = "Hay un error en la fecha de su PC. Contactarse con info@rxfutbol.com para obtener mas informaci�n.";
    		Labels[13] = "Error";
    		Labels[14] = "Error al tratar de leer la fecha de activacion. Contactse con info@rxfutbol.com para obtener mas informaci�n.";
    		Labels[15] = "Env�e este n�mero de serie a info@rxfutbol.com para activar su licencia";
		}
		
	/*---------------------------------------------------------*/
		public void SetINGLES(){
			Labels[0] = "Serial Number: ";
			Labels[1] = "Activation code:  ";
			Labels[2] = "Accept";
			Labels[3] = "Activation code";
			Labels[4] = "The activation code entered is not correct.";   
			Labels[5] = "Copy";
    		Labels[6] = "Version: ";
    		Labels[7] = "Mode: ";
    		Labels[8] = "Accept";
    		Labels[9] = "You must be connected to the internet to activate the software.";
    		Labels[10] = "The date on your PC is not correct.";
    		Labels[11] = "The software is expired. Contact info@rxfutbol.com to obtain a new license.";
    		Labels[12] = "There is an error in the date of your PC. Contact info@rxfutbol.com for more information.";
    		Labels[13] = "Error";
    		Labels[14] = "Error trying to read the activation date. Contact info@rxfutbol.com for more information.";
    		Labels[15] = "Send this serial number to info@rxfutbol.com to activate your license";
		}
		
	/*---------------------------------------------------------*/
		public void SetPORTUGUES(){
			Labels[0] = "N�mero de serie: ";
			Labels[1] = "C�digo de activaci�n:  ";
			Labels[2] = "Aceptar";
			Labels[3] = "C�digo de activaci�n";
			Labels[4] = "El c�digo de activaci�n ingresado no es Correcto.";   
			Labels[5] = "Copiar";
    		Labels[6] = "Versi�n: ";
    		Labels[7] = "Modo: ";
    		Labels[8] = "Aceptar";
    		Labels[9] = "Debe estar conectado a internet para poder activar el software.";
    		Labels[10] = "La fecha en su PC no es la correcta.";
    		Labels[11] = "O software expirou. Contate info@rxfutbol.com para obter uma nova licen�a.";
    		Labels[12] = "Hay un error en la fecha de su PC. Contactse con info@rxfutbol.com para obtener mas informaci�n.";
    		Labels[13] = "Error";
    		Labels[14] = "Erro ao tentar ler a data de ativa��o. Contate info@rxfutbol.com para mais informa��es.";
    		Labels[15] = "Envie este n�mero de s�rie para info@rxfutbol.com para ativar sua licen�a.";
		}
		
	/*---------------------------------------------------------*/
		public void CargarExcepcion(){
			
		}
	/*---------------------------------------------------------*/
		  public void CambiarCaracteres(){
			  for(int i=0;i<Menu.length;i++){
				  Menu[i] = Util.CaracteresEspeciales(Menu[i]);
			  }
			  for(int i=0;i<Labels.length;i++){
				  Labels[i] = Util.CaracteresEspeciales(Labels[i]);
			  }
		  }		 		  
	}
