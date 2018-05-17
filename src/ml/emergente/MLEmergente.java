/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.emergente;

import java.io.IOException;

/**
 *
 * @author gtroncone
 */
public class MLEmergente {

    /**
     * @param args the command line arguments
     */
    
    private RedNeuronal red;
    
    public MLEmergente() {
        this.red = new RedNeuronal();
    }
    
    public static void main(String[] args)  {
        // Localiza la data
        String csvFile = "Data2.csv";
        
        System.out.println("Bienvenido a Emergente ML.");
        
        // Crea lector
        LectorCSV lector = new LectorCSV();
        
        // Lee el archivo
        try {
            lector.leerCSVSimple(csvFile);
        }
        catch (IOException ex) {
        System.out.println (ex.toString());
        System.out.println("Rayos, no encontró archivo csv ");
        }
        
        //MLEmergente red = new MLEmergente();       
    }
    
}
