
package sc.pkg304_jn_proyecto;

import javax.swing.JOptionPane;

/**
 *
 * @autores  Oscar Solis Barrientos, Jose Antonio Zeledon Sanchez, Javier Mora Jimenez
 */
public class GestionTiquete {
    Cola preferencial = new Cola();
    Cola unTramite = new Cola();
    Cola variosTramites = new Cola();

    boolean caja1Ocupada = false;
    boolean caja2Ocupada = false;
    boolean caja3Ocupada = false;

    public void crearTiquete() {

        
        String nombre = JOptionPane.showInputDialog("Digite el nombre:");

        int id = Integer.parseInt(
                JOptionPane.showInputDialog("Digite el ID:")
        );

        int edad = Integer.parseInt(
                JOptionPane.showInputDialog("Digite la edad:")
        );

        int optTramite = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Seleccione el trámite:\n"
                        + "1. Depósito\n"
                        + "2. Retiro\n"
                        + "3. Cambio de Divisas")
        );

        Tramite tramite = null;

        switch (optTramite) {
            case 1:
                tramite = Tramite.Depositos;
                break;
            case 2:
                tramite = Tramite.Retiros;
                break;
            case 3:
                tramite = Tramite.CambiodeDivisas;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida");
                return;
        }

        int optTipo = Integer.parseInt(
                JOptionPane.showInputDialog(
                        "Seleccione el tipo:\n"
                        + "1. Preferencial\n"
                        + "2. Un trámite\n"
                        + "3. Dos o más trámites")
        );

        Tipo tipo = null;

        switch (optTipo) {
            case 1:
                tipo = Tipo.P;
                break;
            case 2:
                tipo = Tipo.A;
                break;
            case 3:
                tipo = Tipo.B;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción inválida");
                return;
        }
        Cliente nuevo = new Cliente(nombre, id, edad, tramite, tipo);

        switch (tipo) {

            case P:
                int personas1 = preferencial.cantidadPersonas();
                preferencial.encolar(nuevo);

                if (personas1 == 0 && !caja1Ocupada) {
                    JOptionPane.showMessageDialog(null,
                            "Caja 1\nEs su turno de atención.");
                    caja1Ocupada = true;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Caja 1\nPersonas delante: " + personas1);
                }
                break;

            case A:
                int personas2 = unTramite.cantidadPersonas();
                unTramite.encolar(nuevo);

                if (personas2 == 0 && !caja2Ocupada) {
                    JOptionPane.showMessageDialog(null,
                            "Caja 2\nEs su turno de atención.");
                    caja2Ocupada = true;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Caja 2\nPersonas delante: " + personas2);
                }
                break;

            case B:
                int personas3 = variosTramites.cantidadPersonas();
                variosTramites.encolar(nuevo);

                if (personas3 == 0 && !caja3Ocupada) {
                    JOptionPane.showMessageDialog(null,
                            "Caja 3\nEs su turno de atención.");
                    caja3Ocupada = true;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Caja 3\nPersonas delante: " + personas3);
                }
                break;
        }
    }
}


