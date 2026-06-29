
package sc.pkg304_jn_proyecto;

import javax.swing.JOptionPane;

/**
 *
 * 
 * Poner aca nombres
 * @autores  Oscar Solis Barrientos,
 * 
 * 
 */
public class SC304_JN_Proyecto {
    
    public static int cantidad = 0;                                 // Esta variable almacena cantidad de clientes creados, almacena los returns

    /**
     
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        IniciarPrograma();
        
    }
    
    public static void IniciarPrograma() {
        int veces;
        int valorBTN;

        valorBTN = JOptionPane.showOptionDialog(null, "----------------[  MENÚ INICIO DE BANCO  ]----------------\n¡Bienvenido al Sistema del Banco!\n¿Qué desea hacer?", "Seleccione",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, new String[]{"CONFIGURACIÓN", "ATENCIÓN DE CLIENTES", "SALIR"}, "ATENCIÓN DE CLIENTES");
        switch (valorBTN) {                         //Switch encargado de evaluar el menu principal
            case 0:                                 //En el caso que se ingrese el menu banco se llama al metodo encargado
                veces=1;
                
                if (veces > 1) {
                    JOptionPane.showMessageDialog(null, "Ya este proceso fue hecho");
                    IniciarPrograma();
                            
                }else if (veces == 1){
                    JOptionPane.showMessageDialog(null, "Mostrando modulo configuracion");
                    veces = +veces;
                }
                
                IniciarPrograma();
                break;
            case 1:
                MenuAtencionCliente();
                break;

            case 2:
                JOptionPane.showMessageDialog(null, "Gracias por usar el Sistema del Banco");  //En caso de que se seleccione salir se da un mensaje de despedida y termina el programa
                
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

            if (respuesta == null) {                // Se evalua si no se ingreso un dato al menu, si no es asi se vuelve a iniciar el metodo principal + retrun para evitar enciclamiento y que no se caiga el codigo
                IniciarPrograma();
                return;
            }
            opt = Integer.parseInt(respuesta);      // Se transoforma la variable respuesta a integer para poder ser interpretada por el swtich, se almacena en "opt"
            
            //se usa el metodo Switch para escoger una opcion 
            switch (opt) {
                case 1: //Creacion de tiquetes
                    JOptionPane.showMessageDialog(null, "Mostrar las opciones de Creacion de tiquetes");
                
                    break;
                case 2: //Atencion de tiquetes 
                    JOptionPane.showMessageDialog(null, "Mostrar las opciones de Atencion de tiquetes");
                    

                    break;
                case 3: //Llenado de colas
                    JOptionPane.showMessageDialog(null, "Mostrar las opciones de Llenado de colas");
                    

                    break;
                case 4: //Reportes
                    JOptionPane.showMessageDialog(null, "Mostrando Reportes del sistema");

                    break;
                case 5: //Salir
                    IniciarPrograma();

                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Ingrese un número válido");    // Si no se ingreso opciones validas se pide al usuario que se ingrese una valida y se vuelve a evaluar 
                }    
            
        } while (opt!=5);
        
        }
    

}
