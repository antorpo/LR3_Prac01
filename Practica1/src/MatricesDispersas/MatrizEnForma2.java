package MatricesDispersas;

public class MatrizEnForma2 {
    private NodoDoble mat;
    
    public MatrizEnForma2(int m, int n){
        Tripleta t, tx;
        NodoDoble x;
        t = new Tripleta(m, n, null);
        mat = new NodoDoble(t);
        tx = new Tripleta(0, 0, null);
        x = new NodoDoble(tx);
        x.setLigaIzquierda(x);
        x.setLigaDerecha(x);
        mat.setLigaDerecha(x);
    }
   
    public NodoDoble nodoCabeza(){
        return mat.getLigaDerecha();
    }
    
    public NodoDoble primerNodo(){
        return mat;
    }
    
    public boolean esVacia(){
        NodoDoble p = nodoCabeza();
        return (p.getLigaIzquierda() == p && p.getLigaDerecha() == p);
    }
    
    public boolean finDeRecorrido(NodoDoble p) {
        return p == nodoCabeza();
    }
    
    public String muestraMatriz(){
        String matriz = "";
        int qf, qc, qv;
        NodoDoble q;
        Tripleta tq;
        
        q = nodoCabeza().getLigaDerecha();
        
        while(!finDeRecorrido(q)){
            tq = (Tripleta) q.getDato();
            qf = tq.getFila();
            qc = tq.getColumna();
            qv = (int) tq.getValor();
            matriz += qf + " " + qc + " " + qv + "\n";
            q = q.getLigaDerecha();
        }
        
        return matriz;
    }
    
    
    public void conectaPorFilas(NodoDoble x){
        NodoDoble p, q, anterior;
        Tripleta tq, tx;
        
        tx = (Tripleta) x.getDato();
        p = nodoCabeza();
        anterior = p;
        
        q = p.getLigaDerecha();
        tq = (Tripleta) q.getDato();
        
        while(!finDeRecorrido(q) && (tq.getFila() < tx.getFila())){
            anterior = q;
            q = q.getLigaDerecha();
            tq = (Tripleta) q.getDato();
        }
        
        while(!finDeRecorrido(q) && (tq.getFila() == tx.getFila()) && (tq.getColumna() < tx.getColumna())){
            anterior = q;
            q = q.getLigaDerecha();
            tq = (Tripleta) q.getDato();
        }
        
    
        anterior.setLigaDerecha(x);
        x.setLigaDerecha(q);

    }
    
    
    public void conectaPorColumnas(NodoDoble x){
        NodoDoble p, q, anterior;
        Tripleta tq, tx;
        
        tx = (Tripleta) x.getDato();
        p = nodoCabeza();
        anterior = p;
        
        q = p.getLigaIzquierda();
        tq = (Tripleta) q.getDato();
        
        while(!finDeRecorrido(q) && (tq.getColumna()< tx.getColumna())){
            anterior = q;
            q = q.getLigaIzquierda();
            tq = (Tripleta) q.getDato();
        }
        
        while(!finDeRecorrido(q) && (tq.getColumna() == tx.getColumna()) && (tq.getFila()< tx.getFila())){
            anterior = q;
            q = q.getLigaIzquierda();
            tq = (Tripleta) q.getDato();
        }
        

        anterior.setLigaIzquierda(x);
        x.setLigaIzquierda(q);

         
    }

    public String matrizCuadrada(){
        Tripleta tp, tq;
        NodoDoble p = primerNodo(), q;
        q = nodoCabeza().getLigaDerecha();
        tp = (Tripleta) p.getDato();
        tq = (Tripleta) q.getDato();
        int m = tp.getFila(), n = tp.getColumna(), valor = 0;
        String matriz = "";
        
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                
                if(!finDeRecorrido(q) && (i == tq.getFila() && j == tq.getColumna())){
                    valor = (int) tq.getValor();
                    
                    String espacios = "";
                    if(valor >= 100){
                        espacios = "     ";
                    }else if(valor >= 10){
                        espacios = "        ";
                    }else{
                        espacios = "          ";
                    }
                    
                    matriz += valor + espacios;
                    q = q.getLigaDerecha();
                    tq = (Tripleta) q.getDato();
                }else{
                    matriz += 0 + "          ";
                }
            }  
            
            matriz += "\n";  
        }
        
        return matriz;
    }
    
    /**
     * Intercambiamos las filas creando otro objeto de la clase MatrizEnForma2 vacio,
     * recorremos la matriz actual, si el nodo no es de la fila Fx o Fy lo agregamos
     * normalmente a la nueva matriz, si pertenece a Fx o Fy hacemos el cambio de 
     * fila en la tripleta y agregamos, al final la variable mat de la matriz 
     * actual se le asigna la variable mat de la nueva matriz quedando totalmente
     * reemplazada
     * 
     * @param fx fila a cambiar
     * @param fy fila destino
     */
    public void intercambiaFilas(int fx, int fy){
        Tripleta tq, tx;
        NodoDoble q = nodoCabeza().getLigaDerecha(), x;
        tq = (Tripleta) q.getDato();
        
        // No hacemos cambio
        if(fx == fy){
            return;
        }
        
        // fx sera la fila menor:
        int aux = fx, filaDestino = 0;
        
        if(fx > fy){
            fx = fy;
            fy = aux;
        }
        
        // Creamos la matriz donde se guardaran los cambios
        MatrizEnForma2 matN = new MatrizEnForma2(ordenFila(), ordenColumna());
        
        while(!finDeRecorrido(q)){
            
            
            if(tq.getFila() == fx){ 
                filaDestino = fy;    
            }else if(tq.getFila() == fy){
                filaDestino = fx;       
            }else{ // Si la fila de la tripleta del nodo no es ni Fx ni Fy simplemente la conservamos
                filaDestino = tq.getFila();
            }
            
            // Creamos una tripleta con los datos de la tripleta en la que va el recorrido
            tx = new Tripleta(filaDestino, tq.getColumna(), tq.getValor());
            x = new NodoDoble(tx);
            
            // Conectamos normalmente
            matN.conectaPorFilas(x);
            matN.conectaPorColumnas(x); 
            
            // Avanzamos
            q = q.getLigaDerecha();
            tq = (Tripleta) q.getDato();
        }
        
        // Asignamos el primer nodo de la matriz nueva a la matriz actual 
        mat = matN.primerNodo();
    }
    
    public int ordenFila(){
        Tripleta t = (Tripleta) mat.getDato();
        return t.getFila();
    }
    
     public int ordenColumna(){
        Tripleta t = (Tripleta) mat.getDato();
        return t.getColumna();
    }
}
