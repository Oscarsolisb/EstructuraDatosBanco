
package sc.pkg304_jn_proyecto;

import javax.swing.JOptionPane;

/**
 * 
 * 
 * Poner aca nombres
 * @autores  Oscar Solis Barrientos, Jose Antonio Zeledon Sanchez, Javier Mora Jimenez, Kevin Ruíz
 * 
 * 
 */
public class SC304_JN_Proyecto {
    
    public static int cantidad = 0;                                     
    private static ConfigurarModulo0 modulo0 = new ConfigurarModulo0();
    private static PruebaConceptoF pruebaFecha = new PruebaConceptoF();
    private static LlenadoColas llenadoColas = new LlenadoColas();
    private static GestionTiquete gestion = new GestionTiquete();
    private static Login login = new Login();
    
    
    

    public static void main(String[] args) {
        //USUARIO 1 = admin, 1234
        //USUARIO 2 = admin2, 5678
        
       

        while (true) {

            if (login.login()) {
                

                modulo0.ejecutarConfiguracionSilenciosa();
                IniciarPrograma();

            } else {
                
                
                break;
            }

        }

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
        pruebaFecha.ejecutarPruebaConcepto();
        IniciarPrograma();
        break;

    case 2:
        MenuAtencionCliente();
        break;

    case 3:
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
                                    1. Creación de tiquetes 
                                    2. Atención de tiquetes
                                    3. Llenado de Colas
                                    4. Reportes
                                    5. Salir 
                                    """));

            if (respuesta == null) {                
                IniciarPrograma();
                return;
            }
            opt = Integer.parseInt(respuesta);      
            
            switch (opt) {
                case 1: 
                    gestion.creartiquete(); 
                    break;
                case 2:
                    gestion.menuAtencionTiquetes();
                    break;
                case 3: 
                    
                    llenadoColas.mostrarEstadoYAsignar(gestion.unTramite, gestion.cajasTipoB);
                    break;
                case 4: 
                    JOptionPane.showMessageDialog(null, "Mostrando Reportes del sistema");
                    break;
                case 5: 
                    IniciarPrograma();
                    return;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido");    
            }   
            
        } while (opt != 5);
    }
}