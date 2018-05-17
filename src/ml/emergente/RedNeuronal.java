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
public class RedNeuronal {
  
    private float tasaAprendizaje;
    private CapaNeuronal[] capas;
    private float errorPermitido;
    private float[] vectorDePesos;
    private float[] vectorDelta;
    
    @SuppressWarnings("empty-statement")
    public RedNeuronal() {
        Scanner sc = new Scanner(System.in);
        int numCapas = 0;
        do {
            System.out.println("Indique el número de capas de la arquitectura: ");
            numCapas = sc.nextInt();
        } while(numCapas < 0);
        capas = new CapaNeuronal[numCapas];
        for (int i = 0; i < numCapas; i++) {
            capas[i] = new CapaNeuronal(i);
        }
        do {
            System.out.println("Indique la tasa de aprendizaje de la red: ");
            tasaAprendizaje = sc.nextFloat();
        } while (tasaAprendizaje < 0 || tasaAprendizaje > 1);
        do {
            System.out.println("Indique el error permitido por la red (entre 0 y 1): ");
            errorPermitido = sc.nextFloat();
        } while (errorPermitido < 0 && errorPermitido > 1);
        int total = 0;
        for (int i = 0; i < capas.length - 1; i++) {
            total += capas[i].getNumNeuronas() * (capas[i + 1].getNumNeuronas() + 1);
        }
        total -= capas[0].getNumNeuronas();
        total += capas[capas.length - 1].getNumNeuronas();
        vectorDePesos = new float[total];
        vectorDelta = new float[total];
        for (int i = 0; i < vectorDePesos.length; i++) {
            vectorDePesos[i] = (float) (2 * Math.random()) - 1;
        }
        
        asignarPesos();
        float[] temporal = new float[5];
        for (int i = 0; i < temporal.length; i++) {
            temporal[i] = i;
        }
        forwardPropagate(temporal);
    }
    
    public float[] forwardPropagate(float[] inputs) {
        float[] intermediario = new float[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            intermediario[i] = inputs[i];
        }
        for (int i = 0; i < capas.length - 1; i++) {
            float[][] pesos = capas[i].getPesos();
            float[] biases = capas[i + 1].getBiases();
            FuncionActivacion funcion = capas[i + 1].getFuncion();
            intermediario = sumaProducto(intermediario, pesos, biases);
            intermediario = funcion.ejecutar(intermediario);
        }
        imprimir(intermediario);
        return intermediario;
    }
    
    public void asignarPesos() {
        // El vector de pesos contiene los pesos de las neuronas de la primera capa,
        // en orden respecto a los de la segunda, y luego los biases
        int aux = 0;
        for (int i = 0; i < capas.length - 1; i++) {
            float[][] temporal = new float[capas[i].getNumNeuronas()][capas[i + 1].getNumNeuronas()];
            float[] biasTemporal = new float[capas[i].getNumNeuronas()];
            for (int fila = 0; fila < temporal.length; fila++) {
                for (int col = 0; col < temporal[0].length; col++) {
                    temporal[fila][col] = vectorDePesos[aux];
                    aux++;
                }
            }
            capas[i].setPesos(temporal);
            if (i != 0) {
                for (int fila = 0; fila < temporal.length; fila++) {
                    biasTemporal[fila] = vectorDePesos[aux];
                    aux++;
                }
                capas[i].setBiases(biasTemporal);
            }
        }
        float[] biasTemporal = new float[capas[capas.length - 1].getNumNeuronas()];
        for (int i = 0; i < biasTemporal.length; i++) {
            biasTemporal[i] = vectorDePesos[aux];
            aux++;
        }
        capas[capas.length - 1].setBiases(biasTemporal);
    }
    
    public static float[] sumaProducto(float[] v, float[][] m, float[] bias) {
        if (v.length != m.length) return null;
        float[] output = new float[m[0].length];
        for (int i = 0; i < m[0].length; i++) {
            int aux = 0;
            for (int j = 0; j < v.length; j++) {
                aux += v[j] * m[j][i];
            }
            output[i] = aux;
        }
        if (output.length != bias.length) return null;
        for (int i = 0; i < output.length; i++) {
            output[i] += bias[i];
        }
        return output;
    }
    
    public static void imprimir(float[] m) {
        for(int i = 0; i < m.length; i++) {
            System.out.println(m[i]);
        }
    }
}
