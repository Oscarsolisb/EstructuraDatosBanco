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

    public void mostrarEstadoYAsignar(Cola unTrámite, Cola variosTrámites) {
        int opcion = 0;
        
        do {
            String respuesta = JOptionPane.showInputDialog(
                "----------------[ MÓDULO 1.3]----------------\n" +
                "1. Ver estado actual de las colas\n" +
                "2. Simular llegada de cliente (Un trámite - Caja 2)\n" +
                "3. Simular llegada de cliente (Varios trámites - Caja 3)\n" +
                "4. Regresar al menú anterior"
            );
            
            if (respuesta == null) {
                break;
            }
            
            opcion = Integer.parseInt(respuesta);
            
            switch (opcion) {
                case 1:
                    int personasCaja2 = unTrámite.cantidadPersonas();
                    int personasCaja3 = variosTrámites.cantidadPersonas();
                    
                    String resultado = "";
                    if (personasCaja2 < personasCaja3) {
                        resultado = "Resultado: La Caja 2 tiene menos personas. El próximo cliente se asignará a la Caja 2.";
                    } else if (personasCaja3 < personasCaja2) {
                        resultado = "Resultado: La Caja 3 tiene menos personas. El próximo cliente se asignará a la Caja 3.";
                    } else {
                        resultado = "Hay un empate en las filas. Se puede asignar a cualquiera de las dos cajas.";
                    }
                    
                    JOptionPane.showMessageDialog(null, 
                            "ESTADO ACTUAL DE LAS COLAS \n" +
                            "Caja 1 (Preferencial): [Manejado por módulo preferencial]\n" +
                            "Caja 2 (Un solo trámite - Tipo A): " + personasCaja2 + " personas en fila\n" +
                            "Caja 3 (Dos o más trámites - Tipo B): " + personasCaja3 + " personas en fila\n\n" +
                            "Regla de asignación: El sistema asigna automáticamente a la caja con menos personas.\n\n" +
                            resultado
                    );
                    break;
                    
                case 2:
                    Cliente c2 = new Cliente("Cliente Prueba A", 111, 25, Tramite.DEPOSITOS, Tipo.A);
                    unTrámite.encolar(c2);
                    JOptionPane.showMessageDialog(null, "¡Cliente agregado a la Caja 2 exitosamente!");
                    break;
                    
                case 3:
                    Cliente c3 = new Cliente("Cliente Prueba B", 222, 30, Tramite.RETIROS, Tipo.B);
                    variosTrámites.encolar(c3);
                    JOptionPane.showMessageDialog(null, "¡Cliente agregado a la Caja 3 exitosamente!");
                    break;
                    
                case 4:
                    break;
                    
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 4);
    }
}
