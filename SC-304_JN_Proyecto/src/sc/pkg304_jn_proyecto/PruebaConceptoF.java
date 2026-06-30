/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sc.pkg304_jn_proyecto;

import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 *
 *  @autores  Oscar Solis Barrientos, Jose Antonio Zeledon Sanchez, Javier Mora Jimenez
 */
public class PruebaConceptoF {
    
    public void ejecutarPruebaConcepto() {

        LocalDateTime fechaSistema = LocalDateTime.now();

        JOptionPane.showMessageDialog(null,
                "Fecha y hora actual del sistema:\n"
                + mostrarFecha(fechaSistema)
        );

        int anio = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de la fecha a comparar:"));
        int mes = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el mes de la fecha a comparar:"));
        int dia = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el día de la fecha a comparar:"));
        int hora = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la hora de la fecha a comparar:"));
        int minuto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese los minutos de la fecha a comparar:"));

        String resultado = compararFechas(fechaSistema, anio, mes, dia, hora, minuto);

        JOptionPane.showMessageDialog(null,
                "Fecha del sistema:\n"
                + mostrarFecha(fechaSistema)
                + "\n\nFecha ingresada:\n"
                + dia + "/" + mes + "/" + anio + " " + hora + ":" + minuto
                + "\n\nResultado:\n"
                + resultado
        );
    }

    private String compararFechas(LocalDateTime fechaSistema, int anio, int mes, int dia, int hora, int minuto) {

        if (anio > fechaSistema.getYear()) {
            return "La fecha ingresada es más reciente que la fecha del sistema.";
        } else if (anio < fechaSistema.getYear()) {
            return "La fecha del sistema es más reciente que la fecha ingresada.";
        }

        if (mes > fechaSistema.getMonthValue()) {
            return "La fecha ingresada es más reciente que la fecha del sistema.";
        } else if (mes < fechaSistema.getMonthValue()) {
            return "La fecha del sistema es más reciente que la fecha ingresada.";
        }

        if (dia > fechaSistema.getDayOfMonth()) {
            return "La fecha ingresada es más reciente que la fecha del sistema.";
        } else if (dia < fechaSistema.getDayOfMonth()) {
            return "La fecha del sistema es más reciente que la fecha ingresada.";
        }

        if (hora > fechaSistema.getHour()) {
            return "La fecha ingresada es más reciente que la fecha del sistema.";
        } else if (hora < fechaSistema.getHour()) {
            return "La fecha del sistema es más reciente que la fecha ingresada.";
        }

        if (minuto > fechaSistema.getMinute()) {
            return "La fecha ingresada es más reciente que la fecha del sistema.";
        } else if (minuto < fechaSistema.getMinute()) {
            return "La fecha del sistema es más reciente que la fecha ingresada.";
        }

        return "Ambas fechas son iguales hasta el nivel de minutos.";
    }

    private String mostrarFecha(LocalDateTime fecha) {
        return fecha.getDayOfMonth() + "/"
                + fecha.getMonthValue() + "/"
                + fecha.getYear() + " "
                + fecha.getHour() + ":"
                + fecha.getMinute();
    }
}
