/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ml.emergente;

/**
 *
 * @author Jose
 */
public class DataNode {
    // Atributos
    float[] input;
    int output;
    
    public DataNode(float inputValues[],int outputVal) {
        this.input = inputValues;
        this.output = outputVal;
    }
    public DataNode(int outputVal) {
        this.output = outputVal;
    }
    public DataNode() {
        this.input = new float[5];
    }

    public float[] getInput() {
        return input;
    }

    public void setInput(float[] input) {
        this.input = input;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }
    
    
    
}
