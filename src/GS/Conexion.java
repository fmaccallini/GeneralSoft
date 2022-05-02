package GS;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Conexion {
	private static int TimeOut = 1000;
	
    public Conexion() {
        }
    
//****************************************************************//
    public boolean ProbarConexion() {
		Socket socket = new Socket();
        boolean ProbandoConexion=true;
        boolean hayConexion = false;
        long start = 0;
        try {
            start = System.nanoTime();
            socket.connect(new InetSocketAddress("www.google.com", 80), TimeOut);
        } catch (Exception e) {
            System.out.println(e.toString());
            ProbandoConexion=false;
        } finally {
            long end = System.nanoTime();
            System.out.println("Conexion time: " + ((end - start) / 1000000) + " ms");
            hayConexion=ProbandoConexion;
        }
        try {
			socket.close();	
			socket = null;
		} catch (IOException e) {
			e.printStackTrace();
		}
        return hayConexion;
    }
}
