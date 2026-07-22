package sc.pkg304_jn_proyecto;

import java.io.FileWriter;
import java.io.PrintWriter;
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
}
