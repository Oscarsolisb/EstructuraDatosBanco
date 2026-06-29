/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sc.pkg304_jn_proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class ConfigurarModulo0 {

    private String nombreBanco;
    private int cantidadCajas;

    public void ejecutarConfiguracionSilenciosa() {
        File archivo = new File("prod.txt");
        if (archivo.exists()) {
            try {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                nombreBanco = br.readLine();
                cantidadCajas = Integer.parseInt(br.readLine());
                br.close();
                fr.close();
            } catch (Exception e) {
            }
        }
    }

    public void ejecutarConfiguracionManual() {
        File archivo = new File("prod.txt");

        if (archivo.exists()) {
            JOptionPane.showMessageDialog(null, "----------------[  INFORMACIÓN DEL BANCO  ]----------------\n"
                    + "Banco: " + nombreBanco + "\n"
                    + "Total Cajas de Atención: " + cantidadCajas + "\n\n"
                    + "Distribución obligatoria de Cajas:\n"
                    + "- 1 Caja Preferencial (Tipo P)\n"
                    + "- 1 Caja de Trámites Rápidos (Tipo A)\n"
                    + "- " + (cantidadCajas - 2) + " Cajas Normales (Tipo B)");
        } else {
            JOptionPane.showMessageDialog(null, "Iniciando: Configuración Inicial del Sistema Bancario...");
            pedirDatosNuevos();
        }
    }

    private void pedirDatosNuevos() {
        nombreBanco = JOptionPane.showInputDialog("Ingrese el nombre del Banco:");
        while (nombreBanco == null || nombreBanco.trim().isEmpty()) {
            nombreBanco = JOptionPane.showInputDialog("Error: El nombre no puede estar vacío.\nIngrese el nombre del Banco:");
        }

        boolean cantidadValida = false;
        while (!cantidadValida) {
            String lecturaCajas = JOptionPane.showInputDialog("Ingrese la cantidad de cajas de atención (Mínimo 3):");
            try {
                cantidadCajas = Integer.parseInt(lecturaCajas);
                if (cantidadCajas >= 3) {
                    cantidadValida = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Error: El mínimo de cajas permitido es 3.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error: Debe ingresar un número entero válido.");
            }
        }

        try {
            FileWriter fw = new FileWriter("prod.txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(nombreBanco);
            pw.println(cantidadCajas);
            pw.close();
            fw.close();

            JOptionPane.showMessageDialog(null, "¡Datos guardados con éxito!\n"
                    + "Banco: " + nombreBanco + "\n"
                    + "Total Cajas de Atención: " + cantidadCajas);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error del Sistema: No se pudieron salvar los datos.");
        }
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public int getCantidadCajas() {
        return cantidadCajas;
    }
}