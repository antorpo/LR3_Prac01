package MatricesDispersas;

public class NodoDoble {
    private NodoDoble ligaDerecha;
    private NodoDoble ligaIzquierda;
    private Object dato;
    
    public NodoDoble(Object dato){
       this.dato = dato; 
    }
    
    public NodoDoble(NodoDoble ligaIzquierda, NodoDoble ligaDerecha, Object dato){
        this.ligaIzquierda = ligaIzquierda;
        this.ligaDerecha = ligaDerecha;
        this.dato = dato;
    }

    public NodoDoble getLigaDerecha() {
        return ligaDerecha;
    }

    public void setLigaDerecha(NodoDoble ligaDerecha) {
        this.ligaDerecha = ligaDerecha;
    }

    public NodoDoble getLigaIzquierda() {
        return ligaIzquierda;
    }

    public void setLigaIzquierda(NodoDoble ligaIzquierda) {
        this.ligaIzquierda = ligaIzquierda;
    }

    public Object getDato() {
        return dato;
    }

    public void setDato(Object dato) {
        this.dato = dato;
    }
}
