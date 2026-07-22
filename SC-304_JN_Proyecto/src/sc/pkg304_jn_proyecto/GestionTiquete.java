
package sc.pkg304_jn_proyecto;

import java.time.Duration;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

/**
 * @autores  Oscar Solis Barrientos, Jose Antonio Zeledon Sanchez, Javier Mora Jimenez
 */
public class GestionTiquete {
    Cola preferencial = new Cola();
    Cola unTramite = new Cola();
    
    // Arreglo para manejar múltiples cajas de Tipo B (ej. Caja 3 y Caja 4, o las necesarias)
    Cola[] cajasTipoB = { new Cola(), new Cola() }; 

    boolean caja1Ocupada = false;
    boolean caja2Ocupada = false;
    boolean[] cajasBOcupadas = { false, false };

    private final Reportes reportes = new Reportes();

    public void creartiquete() {
        String nombre = JOptionPane.showInputDialog("Digite el nombre:");
        if (nombre == null) { return; }

        String idStr = JOptionPane.showInputDialog("Digite el ID:");
        if (idStr == null) { return; }
        int id = Integer.parseInt(idStr);

        String edadStr = JOptionPane.showInputDialog("Digite la edad:");
        if (edadStr == null) { return; }
        int edad = Integer.parseInt(edadStr);

        String optTramiteStr = JOptionPane.showInputDialog(
                "Seleccione el trámite:\n"
                + "1. Depósito\n"
                + "2. Retiro\n"
                + "3. Cambio de Divisas"
        );
        if (optTramiteStr == null) { return; }
        int optTramite = Integer.parseInt(optTramiteStr);

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

        String optTipoStr = JOptionPane.showInputDialog(
                "Seleccione el tipo:\n"
                + "1. Preferencial\n"
                + "2. Un trámite\n"
                + "3. Dos o más trámites"
        );
        if (optTipoStr == null) { return; }
        int optTipo = Integer.parseInt(optTipoStr);

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
                            "Caja 2 (Un trámite)\nEs su turno de atención.");
                    caja2Ocupada = true;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Caja 2 (Un trámite)\nPersonas delante: " + personas2);
                }
                break;

            case B:
                // Lógica de balanceo entre las cajas de Tipo B (elige la que tenga menos personas)
                int indiceMejorCaja = 0;
                int menorPersonas = cajasTipoB[0].cantidadPersonas();

                for (int i = 1; i < cajasTipoB.length; i++) {
                    int actual = cajasTipoB[i].cantidadPersonas();
                    if (actual < menorPersonas) {
                        menorPersonas = actual;
                        indiceMejorCaja = i;
                    }
                }

                cajasTipoB[indiceMejorCaja].encolar(nuevo);
                int numeroCajaReal = 3 + indiceMejorCaja; // Caja 3, Caja 4, etc.

                if (menorPersonas == 0 && !cajasBOcupadas[indiceMejorCaja]) {
                    JOptionPane.showMessageDialog(null,
                            "Caja " + numeroCajaReal + " (Tipo B)\nEs su turno de atención.");
                    cajasBOcupadas[indiceMejorCaja] = true;
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Caja " + numeroCajaReal + " (Tipo B - Asignada por balanceo)\nPersonas delante: " + menorPersonas);
                }
                break;
        }
    }
    
    public void menuAtencionTiquetes() {
        int opcion = 0;

        do {
            String respuesta = JOptionPane.showInputDialog(
                    "----------------[ MÓDULO 1.2 - ATENCIÓN DE TIQUETES ]----------------\n"
                    + "Seleccione la caja donde se atendió el tiquete:\n\n"
                    + "1. Caja 1 (Preferencial)\n"
                    + "2. Caja 2 (Un trámite)\n"
                    + "3. Caja 3 (Tipo B - 1)\n"
                    + "4. Caja 4 (Tipo B - 2)\n"
                    + "5. Ver estado de las cajas\n"
                    + "6. Regresar");

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
                    atenderTiquete(3); // Caja 3 (Índice 0 de B)
                    break;
                case 4:
                    atenderTiquete(4); // Caja 4 (Índice 1 de B)
                    break;
                case 5:
                    mostrarEstadoCajas();
                    break;
                case 6:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        } while (opcion != 6);
    }

    public void atenderTiquete(int numeroCaja) {
        Cola cola;
        Tipo tipo;
        int indiceB = -1;

        if (numeroCaja == 1) {
            cola = preferencial;
            tipo = Tipo.P;
        } else if (numeroCaja == 2) {
            cola = unTramite;
            tipo = Tipo.A;
        } else if (numeroCaja >= 3 && numeroCaja < 3 + cajasTipoB.length) {
            indiceB = numeroCaja - 3;
            cola = cajasTipoB[indiceB];
            tipo = Tipo.B;
        } else {
            JOptionPane.showMessageDialog(null, "Número de caja inválido.");
            return;
        }

        if (cola.esVacia()) {
            JOptionPane.showMessageDialog(null,
                    "Caja " + numeroCaja + ": no hay clientes en la fila.");
            liberarCajaEspecifica(numeroCaja, tipo, indiceB);
            return;
        }

        Cliente atendido = cola.getPrimero();
        atendido.setFechaAtendido(LocalDateTime.now());
        reportes.guardarAtencion(numeroCaja, atendido);
        cola.desencolar();

        long minutosEspera = Duration.between(
                atendido.getFechaCreacion(), atendido.getFechaAtendido()).toMinutes();

        JOptionPane.showMessageDialog(null,
                "Caja " + numeroCaja + " — TIQUETE ATENDIDO\n"
                + "Cliente: " + atendido.getNombre() + " (ID " + atendido.getId() + ")\n"
                + "Trámite: " + atendido.getTramite() + "\n"
                + "Hora de atención: " + atendido.getFechaAtendido() + "\n"
                + "Tiempo de espera: " + minutosEspera + " min\n"
                + "Guardado en la base de datos de reportes.");

        if (!cola.esVacia()) {
            Cliente siguiente = cola.getPrimero();
            JOptionPane.showMessageDialog(null,
                    "Caja " + numeroCaja + ": es el turno de " + siguiente.getNombre() + ".\n"
                    + "Personas en fila detrás: " + (cola.cantidadPersonas() - 1));
        } else {
            liberarCajaEspecifica(numeroCaja, tipo, indiceB);
            JOptionPane.showMessageDialog(null,
                    "Caja " + numeroCaja + ": no hay más clientes. La caja queda libre.");
        }
    }

    public void mostrarEstadoCajas() {
        StringBuilder sb = new StringBuilder("----------------[ ESTADO DE LAS CAJAS ]----------------\n");
        sb.append("Caja 1 (Preferencial):     ").append(preferencial.cantidadPersonas()).append(" en fila | ").append(caja1Ocupada ? "Ocupada" : "Libre").append("\n");
        sb.append("Caja 2 (Un trámite):       ").append(unTramite.cantidadPersonas()).append(" en fila | ").append(caja2Ocupada ? "Ocupada" : "Libre").append("\n");
        
        for (int i = 0; i < cajasTipoB.length; i++) {
            int numCaja = 3 + i;
            sb.append("Caja ").append(numCaja).append(" (Tipo B - ").append(i + 1).append("): ").append(cajasTipoB[i].cantidadPersonas()).append(" en fila | ").append(cajasBOcupadas[i] ? "Ocupada" : "Libre").append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }

    private void liberarCajaEspecifica(int numeroCaja, Tipo tipo, int indiceB) {
        switch (tipo) {
            case P:
                caja1Ocupada = false;
                break;
            case A:
                caja2Ocupada = false;
                break;
            case B:
                if (indiceB >= 0 && indiceB < cajasBOcupadas.length) {
                    cajasBOcupadas[indiceB] = false;
                }
                break;
        }
    }
}


