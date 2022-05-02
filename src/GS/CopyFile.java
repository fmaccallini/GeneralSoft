package GS;

import java.io.*;
 
public class CopyFile {
	private File Origen, CarpetaDestino, Destino;

	
	public CopyFile(String sourceFile, String destinationFolder, String destinationFile) {
		Origen = new File(sourceFile);
		Destino = new File(destinationFolder+destinationFile);
		CarpetaDestino = new File(destinationFolder);
		if(!CarpetaDestino.exists()) { 
        	CarpetaDestino.mkdirs(); 
        } 
        if(!CarpetaDestino.exists()) { 
        	CarpetaDestino.mkdir(); 
        } 
		if (Origen.isDirectory()) {             
            String[] children = Origen.list(); 
            for (int i=0; i<children.length; i++) { 
            	CopiarArchivo(new File(Origen, children[i]), new File(Destino, children[i])); 
            } 
        } else { 
        	CopiarArchivo(Origen, Destino); 
        } 
	}
	
	public void CopiarArchivo(File inFile, File outFile){
 		System.out.println("Desde: " + inFile.getName());
		System.out.println("Hacia: " + outFile.getName());
		try {
			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);
 
			int c;
			while( (c = in.read() ) != -1){
				out.write(c);				
			}
 
			in.close();
			out.close();
		} catch(IOException e) {
			System.err.println("Hubo un error de entrada/salida!!!");
			e.printStackTrace();
		}
	}	
}