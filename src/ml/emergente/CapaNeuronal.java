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
    private float[] salida;
    private Neurona[] neuronas;
    
    private void menu() {
        System.out.println("Indique la función de activación para la capa: ");
        System.out.println("[1]: Sigmoide");
        System.out.println("[2]: Tanh");
        System.out.println("[3]: Arctan");
        System.out.println("[4]: Heaviside");
    }
    
    public CapaNeuronal(int num) {
        numCapa = num;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Indique el número de neuronas en la capa " + numCapa +": ");
            numNeuronas = sc.nextInt();
        } while(numNeuronas < 0);
        
        neuronas = new Neurona[numNeuronas];

        
        for (int i = 0; i < numNeuronas; i++) {
            neuronas[i] = new Neurona(0, 0);
        }
        
        int selecFun = 0;
        do {
            this.menu();
            selecFun = sc.nextInt();
        } while(selecFun < 0 || selecFun > 4);
        
        if (numCapa > 0) {
            funcion = new FuncionActivacion(selecFun);
        }
    }

    public int getNumNeuronas() {
        return numNeuronas;
    }

    public int getNumCapa() {
        return numCapa;
    }

    public FuncionActivacion getFuncion() {
        return funcion;
    }

    public float[][] getPesos() {
        return pesos;
    }

    public float[] getBiases() {
        return biases;
    }
    
    public float[] getPreActivaciones() {
        float[] out = new float[numNeuronas];
        for (int i = 0; i < out.length; i++) {
            out[i] = neuronas[i].getPreActivacion();
        }
        return out;
    }

    public float[] getSalidasCapa() {
        float[] out = new float[numNeuronas];
        for (int i = 0; i < out.length; i++) {
            out[i] = neuronas[i].getSalida();
        }
        return out;
    }
    
    public Neurona[] getNeuronas() {
        return neuronas;
    }

    public float[] getSalida() {
        return salida;
    }

    public void setBiases(float[] biases) {
        this.biases = biases;
    }

    public void setNeuronas(Neurona[] neuronas) {
        this.neuronas = neuronas;
    }

    public void setPesos(float[][] pesos) {
        this.pesos = pesos;
    }
}
