/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.emergente;

import java.util.Scanner;

/**
 *
 * @author gtroncone
 */
public class CapaNeuronal {
    
    private int numNeuronas;
    private int numCapa;
    private FuncionActivacion funcion;
    private float[][] pesos;
    private float[] biases;
    private Neurona[] capa;
    private float[] salida;
    
    private void menu() {
        System.out.println("Indique la función de activación para la capa: ");
        System.out.println("[1]: Sigmoide");
        System.out.println("[2]: Logística");
        System.out.println("[3]: Tanh");
        System.out.println("[4]: Arctan");
        System.out.println("[5]: Signo");
    }
    
    public CapaNeuronal(int numCapa) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Indique el número de neuronas en la capa " + numCapa +": ");
            numNeuronas = sc.nextInt();
        } while(numNeuronas < 0);
        
        int selecFun = 0;
        do {
            this.menu();
            selecFun = sc.nextInt();
        } while(selecFun < 0 || selecFun > 6);
        
        funcion = new FuncionActivacion(selecFun);
        
        capa = new Neurona[numNeuronas];
        
        for (int i = 0; i < numNeuronas; i++) {
            capa[i] = new Neurona();
        }
    }
}
