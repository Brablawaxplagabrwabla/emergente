/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.emergente;

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
    
    public static void main(String[] args) {
        System.out.println("Bienvenido a Emergente ML.");
        MLEmergente red = new MLEmergente();       
    }
    
}
