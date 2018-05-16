/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.emergente;
// Librerias internas
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;


/**
 *
 * @author Jose
 */
public class LectorCSV {
    
	// Propiedades
	private char separador = ',';
	private char comillas;
        private DataNode[] data = new DataNode[10000];
	
	// Constructor
	
	/**
	 * Inicializa el constructor definiendo el separador de los campos y las comillas usadas
	 * @param separador
	 * @param comillas
	 */
	public LectorCSV(char separador, char comillas) {
		this.separador = separador;
		this.comillas = comillas;
	}
        public LectorCSV() {
            
	}
	
	// Métodos
	/**
	 * Lee un CSV que no contiene el mismo caracter que el separador en su texto
	 * y sin comillas que delimiten los campos
	 * @param path Ruta donde está el archivo
	 * @throws IOException 
	 */
	public void leerCSVSimple(String path) throws IOException {
		
		// Abro el .csv en buffer de lectura
		BufferedReader bufferLectura = new BufferedReader(new FileReader(path));
		
		// Leo una línea del archivo
		String linea = bufferLectura.readLine();
		int index = 0;
		while (linea != null) {
                        System.out.println("Index: "+index);
			// Separa la línea leída con el separador definido previamente
			String[] campos = linea.split(String.valueOf(this.separador));
			System.out.println(Arrays.toString(campos));
                        
                        float shoulderLength =  Float.parseFloat(campos[1]);
                        float waistcircumference = Float.parseFloat(campos[2]);
                        float Age = Float.parseFloat(campos[3]);
                        float Heightin = Float.parseFloat(campos[4]);
                        float Weightlbs = Float.parseFloat(campos[5]);
                        float[] inputAux = new float[5];
                        inputAux = new float[]{ shoulderLength,waistcircumference,Age,Heightin,Weightlbs };
                        //inputAux[0] = shoulderLength; inputAux[1] = waistcircumference; inputAux[2] = Age; inputAux[3] = Heightin; inputAux[4] = Weightlbs;
                        int outputAux = Integer.parseInt(campos[5]);
                        DataNode dataNodeAux = new DataNode(inputAux,outputAux );
                        data[index] = dataNodeAux;
                        index++;
			
			// Vuelvo a leer del fichero
			linea = bufferLectura.readLine();
		}
		index = 0;
		// CIerro el buffer de lectura
		if (bufferLectura != null) {
			bufferLectura.close();
		}
	}
	
}
