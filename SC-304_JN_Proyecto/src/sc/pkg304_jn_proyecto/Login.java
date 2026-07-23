/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sc.pkg304_jn_proyecto;

import javax.swing.JOptionPane;

/**
 *
 * @author RYZEN5
 */
public class Login {

    public boolean login() {

        String usuario = JOptionPane.showInputDialog("Ingrese el usuario:");
        String clave = JOptionPane.showInputDialog("Ingrese la contraseña:");

        if (usuario == null || clave == null) {
            
            JOptionPane.showMessageDialog(null, "Login cancelado por el usuario.");
            System.out.println("Login cancelado por el usuario.");
            return false; // o lo que corresponda según cómo esté armado tu método login()
        }
        if (usuario.equals("admin") && clave.equals("1234") || usuario.equals("admin2") && clave.equals("5678")) {
            JOptionPane.showMessageDialog(null, "Bienvenido al sistema.");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.");
            return false;
        }

    }
}
