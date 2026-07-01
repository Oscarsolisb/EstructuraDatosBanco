
package sc.pkg304_jn_proyecto;

import javax.swing.JOptionPane;

/**
 *
 * 
 * Poner aca nombres
 * @autores  Oscar Solis Barrientos, Jose Antonio Zeledon Sanchez, Javier Mora Jimenez
 * 
 * 
 */
public class SC304_JN_Proyecto {
    
    public static int cantidad = 0;                                 
    private static ConfigurarModulo0 modulo0 = new ConfigurarModulo0();
    private static PruebaConceptoF pruebaFecha = new PruebaConceptoF();

    public static void main(String[] args) {
        GestionTiquete Gestion = new GestionTiquete();
        modulo0.ejecutarConfiguracionSilenciosa();
        IniciarPrograma();
        
    }
    
    public static void IniciarPrograma() {
        int valorBTN;

        valorBTN = JOptionPane.showOptionDialog(null, "----------------[  MENÚ INICIO DE BANCO  ]----------------\n¡Bienvenido al Sistema del Banco!\n¿Qué desea hacer?", "Seleccione",
        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"CONFIGURACIÓN", "PRUEBA DE CONCEPTO", "ATENCIÓN DE CLIENTES", "SALIR"}, "ATENCIÓN DE CLIENTES");
        
        switch (valorBTN) {                         
            case 0:                                 
                modulo0.ejecutarConfiguracionManual();
                IniciarPrograma();
                break;
                
            case 1:
                MenuAtencionCliente();
                break;

            case 2:
                JOptionPane.showMessageDialog(null, "Gracias por usar el Sistema del Banco");  
                break;
                
            default:
                JOptionPane.showMessageDialog(null, "Finalizando proceso....");
        }
    }
    
    public static void MenuAtencionCliente() {
        int opt;

        do {
            String respuesta = (JOptionPane.showInputDialog("""         
                                _______MENÚ ATENCIÓN DE CLIENTE_______
                                1. Creación de tiquetes\s
                                2. Atención de tiquetes
                                3. Llenado de Colas
                                4. Reportes
                                5. Salir\s
                               \s"""));

            if (respuesta == null) {                
                IniciarPrograma();
                return;
            }
            opt = Integer.parseInt(respuesta);      
            
            switch (opt) {
                case 1: 
                    JOptionPane.showMessageDialog(null, "Mostrar las opciones de Creacion de tiquetes");
                    break;
                case 2: 
                    JOptionPane.showMessageDialog(null, "Mostrar las opciones de Atencion de tiquetes");
                    break;
                case 3: 
                    JOptionPane.showMessageDialog(null, "Mostrar las opciones de Llenado de colas");
                    break;
                case 4: 
                    JOptionPane.showMessageDialog(null, "Mostrando Reportes del sistema");
                    break;
                case 5: 
                    IniciarPrograma();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido");    
            }    
            
        } while (opt != 5);
    }
}
