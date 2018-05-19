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
public class Neurona {
    
    private float salida;
    private float preActivacion;
    
    public Neurona(float _salida, float _preActivacion) {
        salida = _salida;
        preActivacion = _preActivacion;
    }

    public float getSalida() {
        return salida;
    }

    public float getPreActivacion() {
        return preActivacion;
    }

    public void setSalida(float salida) {
        this.salida = salida;
    }

    public void setPreActivacion(float preActivacion) {
        this.preActivacion = preActivacion;
    }
    
}
