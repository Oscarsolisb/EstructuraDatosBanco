/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sc.pkg304_jn_proyecto;

import javax.swing.JOptionPane;

/**
 * @autores  Oscar Solis Barrientos, Jose Antonio Zeledon Sanchez, Javier Mora Jimenez
 */
public class LlenadoColas {

    public void mostrarEstadoYAsignar(Cola unTrámite, Cola[] cajasTipoB) {
        int opcion = 0;
        
        do {
            String respuesta = JOptionPane.showInputDialog(
                "----------------[ MÓDULO 1.3: LLENADO DE COLAS ]----------------\n" +
                "1. Ver estado actual de las colas y balanceo\n" +
                "2. Simular llegada a Caja 2 (Un trámite - Tipo A)\n" +
                "3. Simular llegada a Cajas Tipo B (Balanceo automático)\n" +
                "4. Regresar al menú anterior"
            );
            
            if (respuesta == null) {
                break;
            }
            
            opcion = Integer.parseInt(respuesta);
            
            switch (opcion) {
                case 1:
                    int personasCaja2 = unTrámite.cantidadPersonas();
                    
                    StringBuilder sb = new StringBuilder("ESTADO ACTUAL DE LAS COLAS Y BALANCEO\n");
                    sb.append("Caja 1 (Preferencial): [Manejado por módulo preferencial]\n");
                    sb.append("Caja 2 (Un solo trámite - Tipo A): ").append(personasCaja2).append(" personas en fila\n");
                    
                    int menorB = cajasTipoB[0].cantidadPersonas();
                    int mejorCajaBIndex = 0;
                    
                    for (int i = 0; i < cajasTipoB.length; i++) {
                        int cant = cajasTipoB[i].cantidadPersonas();
                        sb.append("Caja ").append(3 + i).append(" (Tipo B - Caja ").append(i + 1).append("): ").append(cant).append(" personas en fila\n");
                        if (cant < menorB) {
                            menorB = cant;
                            mejorCajaBIndex = i;
                        }
                    }
                    
                    sb.append("\nRegla de asignación: Los tipo B se reparten eligiendo la caja B con menos gente.\n");
                    sb.append("Resultado actual: La siguiente persona de Tipo B se asignará a la Caja ").append(3 + mejorCajaBIndex);
                    
                    JOptionPane.showMessageDialog(null, sb.toString());
                    break;
                    
                case 2:
                    Cliente c2 = new Cliente("Cliente Prueba A", 111, 25, Tramite.DEPOSITOS, Tipo.A);
                    unTrámite.encolar(c2);
                    JOptionPane.showMessageDialog(null, "¡Cliente agregado exitosamente a la Caja 2!");
                    break;
                    
                case 3:
                    // Simula el balanceo buscando la caja B con menos gente
                    int idxMejor = 0;
                    int minP = cajasTipoB[0].cantidadPersonas();
                    for (int i = 1; i < cajasTipoB.length; i++) {
                        if (cajasTipoB[i].cantidadPersonas() < minP) {
                            minP = cajasTipoB[i].cantidadPersonas();
                            idxMejor = i;
                        }
                    }
                    Cliente cB = new Cliente("Cliente Prueba B", 222, 30, Tramite.RETIROS, Tipo.B);
                    cajasTipoB[idxMejor].encolar(cB);
                    JOptionPane.showMessageDialog(null, "¡Cliente Tipo B agregado exitosamente a la Caja " + (3 + idxMejor) + " (la de menor fila)!");
                    break;
                    
                case 4:
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 4);
    }
}
