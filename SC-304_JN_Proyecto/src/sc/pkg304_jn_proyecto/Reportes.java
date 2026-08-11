package sc.pkg304_jn_proyecto;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 * Base de datos de reportes (Módulo 1.2).
 *
 * Cada vez que un tiquete es atendido se guarda una línea en el archivo
 * reportes.txt. Este archivo es el que luego consume el Módulo 1.4 para
 * calcular las estadísticas de atención.
 *
 * Formato de cada línea (separado por ';'):
 *   numeroCaja;id;nombre;edad;tramite;tipo;fechaCreacion;fechaAtendido
 *
 * @autores  Oscar Solis Barrientos, Jose Antonio Zeledon Sanchez, Javier Mora Jimenez
 */
public class Reportes {

    private static final String ARCHIVO = "reportes.txt";

    /**
     * Agrega (append) una línea al archivo de reportes con los datos del
     * tiquete que acaba de ser atendido.
     *
     * @param numeroCaja número de caja que atendió al cliente (1, 2 o 3)
     * @param c          cliente atendido; debe tener ya la fecha de atención
     */
    public void guardarAtencion(int numeroCaja, Cliente c) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             PrintWriter pw = new PrintWriter(fw)) {

            pw.println(
                    numeroCaja + ";"
                    + c.getId() + ";"
                    + c.getNombre() + ";"
                    + c.getEdad() + ";"
                    + c.getTramite() + ";"
                    + c.getTipo() + ";"
                    + c.getFechaCreacion() + ";"
                    + c.getFechaAtendido());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "Error: no se pudo guardar la atención en la base de datos de reportes.");
        }
    }

    
    public void mostrarReportePersona1() {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "No hay datos registrados en el archivo de reportes aún.");
            return;
        }

        int totalClientes = 0;
        int[] conteoCajas = new int[10];

        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split(";");
                    int numCaja = Integer.parseInt(datos[0]); 

                    totalClientes++; 

                    if (numCaja < conteoCajas.length) {
                        conteoCajas[numCaja]++; 
                    }
                }
            }
            br.close();
            fr.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de reportes.");
            return;
        }

        if (totalClientes == 0) {
            JOptionPane.showMessageDialog(null, "El archivo está vacío, no hay clientes atendidos.");
            return;
        }

        
        int cajaMayor = 0;
        int maxClientes = -1;

        for (int i = 1; i < conteoCajas.length; i++) {
            if (conteoCajas[i] > maxClientes) {
                maxClientes = conteoCajas[i];
                cajaMayor = i;
            }
        }

        
        String mensaje = "---------  REPORTES ---------\n\n"
                + " Total de clientes atendidos: " + totalClientes + "\n"
                + " Caja con mayor cantidad de atenciones: Caja " + cajaMayor + " (" + maxClientes + " clientes)";

        JOptionPane.showMessageDialog(null, mensaje);
    }

    public void mostrarReportePersona2() {
        File archivo = new File(ARCHIVO);

        if (!archivo.exists()) {
            JOptionPane.showMessageDialog(null, "No hay datos registrados en el archivo de reportes aún.");
            return;
        }

        int totalClientes = 0;
        long sumaSegundosTotal = 0;
        long[] sumaSegundosCaja = new long[10];
        int[] conteoCajas = new int[10];

        try {
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);
            String linea;

            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] datos = linea.split(";");
                    if (datos.length < 8) {
                        continue;
                    }
                    int numCaja = Integer.parseInt(datos[0]);
                    LocalDateTime creacion = LocalDateTime.parse(datos[6]);
                    LocalDateTime atencion = LocalDateTime.parse(datos[7]);
                    long segundos = Duration.between(creacion, atencion).getSeconds();

                    totalClientes++;
                    sumaSegundosTotal += segundos;

                    if (numCaja >= 0 && numCaja < sumaSegundosCaja.length) {
                        sumaSegundosCaja[numCaja] += segundos;
                        conteoCajas[numCaja]++;
                    }
                }
            }
            br.close();
            fr.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al leer el archivo de reportes.");
            return;
        }

        if (totalClientes == 0) {
            JOptionPane.showMessageDialog(null, "El archivo está vacío, no hay clientes atendidos.");
            return;
        }

        int mejorCaja = 0;
        double mejorPromedio = Double.MAX_VALUE;
        String detalle = "";

        for (int i = 1; i < sumaSegundosCaja.length; i++) {
            if (conteoCajas[i] > 0) {
                double promedio = (double) sumaSegundosCaja[i] / conteoCajas[i];
                detalle += " Caja " + i + ": " + String.format("%.1f", promedio)
                        + " seg promedio (" + conteoCajas[i] + " atenciones)\n";
                if (promedio < mejorPromedio) {
                    mejorPromedio = promedio;
                    mejorCaja = i;
                }
            }
        }

        double promedioGeneral = (double) sumaSegundosTotal / totalClientes;

        String mensaje = "---------  REPORTES (TIEMPOS DE ATENCIÓN) ---------\n\n"
                + detalle + "\n"
                + " Caja con mejor tiempo promedio: Caja " + mejorCaja
                + " (" + String.format("%.1f", mejorPromedio) + " seg)\n"
                + " Tiempo promedio general (todas las cajas): "
                + String.format("%.1f", promedioGeneral) + " seg";

        JOptionPane.showMessageDialog(null, mensaje);
    }
}
