
package sc.pkg304_jn_proyecto;

import javax.swing.JOptionPane;

/**
 *@autores  Oscar Solis Barrientos, Jose Antonio Zeledon Sanchez, Javier Mora Jimenez
 * 
 */
public class Cola {
    private Nodo prim, ult;
    
    public boolean esVacia(){return prim==null;}
    
    //Para entenderlo mejor a posteridad
    //cuando vayamos a encolar si se necesita un parametro porque se necesita ingresar una persona nueva
    public void encolar(Cliente c){
        Nodo n=new Nodo(c);
        if(esVacia()){
            prim=ult=n;
        }else{
            ult.setSig(n);
            ult=n;
        }
    }
    //Para entenderlo mejor a posteridad
    // para desencolar no recibe ningun parametro porque no se esta creando se esta eliminando
    public void desencolar(){
        
        if (!esVacia()) {
            prim = prim.getSig();
            if (prim == null) {
                ult = null;                    
            }
        }else {
            JOptionPane.showMessageDialog(null, 
                    "Error pila vacia");
        }
        
        
    }
    
    @Override
    public String toString() {
        String r="COLA\n";
        Nodo aux=prim;
        while(aux!=null){
            r+=aux+"\n";
            aux=aux.getSig();
        }
        return r;
    }
    
    public boolean existe (int id){
        Nodo aux = prim;
        while(aux!=null) {
            if (aux.getDato().getId() == id) {
                return true;
            }
            
            
            aux= aux.getSig();
        }
        return false;
    }
    
    public void eliminar (int id) {
        if (!esVacia()) {
            if (prim.getDato().getId() == id) {
                this.desencolar();
                
            } else {
                Nodo aux = prim;
                while (aux.getSig() != null) {
                    if (aux.getDato().getId() == id) {
                        JOptionPane.showMessageDialog(null, "Desencolando a: "+prim);
                        aux.setSig(aux.getSig().getSig());
                        if (aux.getSig() == null) {
                            ult = aux;
                        }
                        
                    }

                    aux = aux.getSig();
                }
            }
        }
    }
    
    public int cantidadPersonas() {
    int contador = 0;
    Nodo aux = prim;
    while (aux != null) {
        contador++;
        aux = aux.getSig();
    }
    return contador;
}
    
    
    
}
