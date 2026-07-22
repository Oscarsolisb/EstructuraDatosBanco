
package sc.pkg304_jn_proyecto;

import java.time.Duration;
import java.time.LocalDateTime;
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

    private final Reportes reportes = new Reportes();

    public void creartiquete() {

        
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
                tramite = Tramite.DEPOSITOS;
                break;
            case 2:
                tramite = Tramite.RETIROS;
                break;
            case 3:
                tramite = Tramite.CAMBIO_DE_DIVISAS;
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
        asignarCaja(nuevo, tipo);
        

        
    }
    
    public void asignarCaja(Cliente nuevo, Tipo tipo) {
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
    
    // ---------------------------------------------------------------
    //  MÓDULO 1.2 - ATENCIÓN DE TIQUETES
    // ---------------------------------------------------------------

    /**
     * Menú del Módulo 1.2. El cajero indica el número de caja en la que se
     * atendió un tiquete ("Tiquete atendido") para que el sistema pase a
     * atender al siguiente cliente de esa cola.
     */
    public void menuAtencionTiquetes() {
        int opcion = 0;

        do {
            String respuesta = JOptionPane.showInputDialog(
                    "----------------[ MÓDULO 1.2 - ATENCIÓN DE TIQUETES ]----------------\n"
                    + "Seleccione la caja donde se atendió el tiquete:\n\n"
                    + "1. Caja 1 (Preferencial)\n"
                    + "2. Caja 2 (Un trámite)\n"
                    + "3. Caja 3 (Varios trámites)\n"
                    + "4. Ver estado de las cajas\n"
                    + "5. Regresar");

            if (respuesta == null) {
                return;
            }

            try {
                opcion = Integer.parseInt(respuesta);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Debe ingresar un número válido.");
                continue;
            }

            switch (opcion) {
                case 1:
                    atenderTiquete(1);
                    break;
                case 2:
                    atenderTiquete(2);
                    break;
                case 3:
                    atenderTiquete(3);
                    break;
                case 4:
                    mostrarEstadoCajas();
                    break;
                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 5);
    }

    /**
     * "Tiquete atendido": atiende al cliente que está al frente de la caja
     * indicada. Le asigna la hora de atención del sistema, lo guarda en la
     * base de datos de reportes, lo saca de la cola y asigna el siguiente
     * cliente a la caja.
     *
     * @param numeroCaja 1 = Preferencial, 2 = Un trámite, 3 = Varios trámites
     */
    public void atenderTiquete(int numeroCaja) {
        Cola cola;
        Tipo tipo;

        switch (numeroCaja) {
            case 1:
                cola = preferencial;
                tipo = Tipo.P;
                break;
            case 2:
                cola = unTramite;
                tipo = Tipo.A;
                break;
            case 3:
                cola = variosTramites;
                tipo = Tipo.B;
                break;
            default:
                JOptionPane.showMessageDialog(null, "Número de caja inválido.");
                return;
        }

        if (cola.esVacia()) {
            JOptionPane.showMessageDialog(null,
                    "Caja " + numeroCaja + ": no hay clientes en la fila.");
            liberarCaja(tipo);
            return;
        }

        // El cliente del frente pasa a ser atendido:
        Cliente atendido = cola.getPrimero();
        atendido.setFechaAtendido(LocalDateTime.now());   // hora de atención del sistema
        reportes.guardarAtencion(numeroCaja, atendido);   // se guarda en la BD de reportes
        cola.desencolar();                                // el tiquete queda atendido

        long minutosEspera = Duration.between(
                atendido.getFechaCreacion(), atendido.getFechaAtendido()).toMinutes();

        JOptionPane.showMessageDialog(null,
                "Caja " + numeroCaja + " — TIQUETE ATENDIDO\n"
                + "Cliente: " + atendido.getNombre() + " (ID " + atendido.getId() + ")\n"
                + "Trámite: " + atendido.getTramite() + "\n"
                + "Hora de atención: " + atendido.getFechaAtendido() + "\n"
                + "Tiempo de espera: " + minutosEspera + " min\n"
                + "Guardado en la base de datos de reportes.");

        // Se asigna el siguiente cliente de la cola a esta caja:
        if (!cola.esVacia()) {
            Cliente siguiente = cola.getPrimero();
            JOptionPane.showMessageDialog(null,
                    "Caja " + numeroCaja + ": es el turno de " + siguiente.getNombre() + ".\n"
                    + "Personas en fila detrás: " + (cola.cantidadPersonas() - 1));
        } else {
            liberarCaja(tipo);
            JOptionPane.showMessageDialog(null,
                    "Caja " + numeroCaja + ": no hay más clientes. La caja queda libre.");
        }
    }

    /**
     * Muestra cuántas personas hay en cada cola y si la caja está ocupada.
     */
    public void mostrarEstadoCajas() {
        JOptionPane.showMessageDialog(null,
                "----------------[ ESTADO DE LAS CAJAS ]----------------\n"
                + "Caja 1 (Preferencial):     " + preferencial.cantidadPersonas()
                        + " en fila | " + (caja1Ocupada ? "Ocupada" : "Libre") + "\n"
                + "Caja 2 (Un trámite):       " + unTramite.cantidadPersonas()
                        + " en fila | " + (caja2Ocupada ? "Ocupada" : "Libre") + "\n"
                + "Caja 3 (Varios trámites):  " + variosTramites.cantidadPersonas()
                        + " en fila | " + (caja3Ocupada ? "Ocupada" : "Libre"));
    }

    private void liberarCaja(Tipo tipo) {
        switch (tipo) {
            case P:
                caja1Ocupada = false;
                break;
            case A:
                caja2Ocupada = false;
                break;
            case B:
                caja3Ocupada = false;
                break;
        }
    }
}


