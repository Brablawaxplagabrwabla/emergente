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
public class FuncionActivacion {
    
    private int tipo;
    
    public FuncionActivacion(int tipoDeFuncion) {
        tipo = tipoDeFuncion;
    }
    
    public float[] ejecutar(float[] inputs) {
        float[] output = new float[inputs.length];
        for (int i = 0; i < inputs.length; i++) {
            switch (tipo) {
                case 1: // Función sigmoide
                    output[i] = 1/(1 + (float) Math.exp(-inputs[i]));
                    break;
                case 2: // Función tanh
                    output[i] = (float) Math.tanh(inputs[i]);
                    break;
                case 3: // Función arctan.
                    output[i] = (float) Math.atan(inputs[i]);
                    break;
                case 4: // Función Heaviside
                    if (inputs[i] > 0) {
                        output[i] = 1;
                    } else if (inputs[i] < 0) {
                        output[i] = 0;
                    } else {
                        output[i] = (float) 0.5;
                    }
                    break;
                default:
                    output[i] = 0;
            }
        }
        return output;
    }
    
    public float[] derivada() {
        return new float[1];
    }
    
}
