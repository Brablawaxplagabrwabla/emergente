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
    }
    
}
