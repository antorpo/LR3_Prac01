package MatricesDispersas;

public class MatrizEnForma1 {
    private NodoDoble mat;
    
    public MatrizEnForma1(int m, int n){
        Tripleta t = new Tripleta(m, n, null);
        mat = new NodoDoble(t);
        t.setValor(mat);
        //mat.setDato(t); Redundante ya que la copia a la referencia cambia automaticamente la tripleta en mat.
    }
    
    public NodoDoble nodoCabeza(){
        return mat;
    }
    
    public NodoDoble primerNodo(){
        Tripleta t = (Tripleta) mat.getDato();
        return (NodoDoble) t.getValor();
    }
    
    public boolean esVacia(){
        return primerNodo() == mat;
    }
    
    public boolean finDeRecorrido(NodoDoble p) {
        return p == mat;
    }
    
    public String muestraMatriz() {
        String matriz = "";
        int qf, qc, qv;
        NodoDoble p, q;
        Tripleta tp, tq;
        p = primerNodo();
        while (!finDeRecorrido(p)) {
            q = p.getLigaDerecha();
            while (q != p) {
                tq = (Tripleta) q.getDato();
                qf = tq.getFila();
                qc = tq.getColumna();
                qv = (int) tq.getValor();
                matriz += qf + " " + qc + " " + qv + "\n";
                q = q.getLigaDerecha();
            }
            tp = (Tripleta) p.getDato();
            p = (NodoDoble) tp.getValor();
        }
        return matriz;
    }
    
    public void construyeNodosCabeza(){
        Tripleta t = (Tripleta) mat.getDato();
        NodoDoble ultimo,  x;
        int f = t.getFila();
        int c = t.getColumna();
        int p = Math.max(f, c);
        
        ultimo = mat;
        
        for (int i = 1; i <= p; i++) {
            t = new Tripleta(0, 0, nodoCabeza());
            x = new NodoDoble(t);
            x.setLigaIzquierda(x);
            x.setLigaDerecha(x);
            
            t = (Tripleta) ultimo.getDato();
            t.setValor(x);
            ultimo = x;
        }
    }
    
    public void conectaPorFilas(NodoDoble x){
        Tripleta t = (Tripleta) x.getDato();
        int f = t.getFila();
        int c = t.getColumna();
        NodoDoble p = primerNodo(), q, aq;
        
        for (int i = 1; i < f; i++) {
            t = (Tripleta) p.getDato();
            p = (NodoDoble) t.getValor();
        }
        
        q = p.getLigaDerecha();
        aq = p;
        
        t = (Tripleta) q.getDato();
        
        while((q != p) && (t.getColumna() < c)){
            aq = q;
            q = q.getLigaDerecha();
            
            t = (Tripleta) q.getDato();
        }
        
        aq.setLigaDerecha(x);
        x.setLigaDerecha(q);
        
        t = (Tripleta) p.getDato();
        t.setFila(t.getFila() + 1);
        p.setDato(t);
    }
    
    
    public void conectaPorColumnas(NodoDoble x){
        NodoDoble p, q, anterior;
        Tripleta tp, tq, tx;
        tx = (Tripleta) x.getDato();
        p = primerNodo();
        
        for (int i = 1; i < tx.getColumna(); i++) {
            tp = (Tripleta) p.getDato();
            p = (NodoDoble) tp.getValor();
        }
        
        anterior = p;
        q = p.getLigaIzquierda();
        tq = (Tripleta) q.getDato();
        
        while ((q != p) && (tq.getFila() < tx.getFila())) {
            anterior = q;
            q = q.getLigaIzquierda();
            tq = (Tripleta) q.getDato();
        }
        
        anterior.setLigaIzquierda(x);
        x.setLigaIzquierda(q);
        
        tp = (Tripleta) p.getDato();
        tp.setColumna(tp.getColumna()+ 1);
        p.setDato(tp);
    }

    public void mostrarPorFilas(){
        NodoDoble p = primerNodo(), q;
        Tripleta tp, tq;
 
        while(!finDeRecorrido(p)){
           q = p.getLigaDerecha();
           
           while(q != p){
               tq = (Tripleta) q.getDato();
               System.out.println(tq.getValor());
               q = q.getLigaDerecha();
           }
           
            tp = (Tripleta) p.getDato();
            p = (NodoDoble) tp.getValor();
        }
    }
    
    public String matrizCuadrada() {
        Tripleta[] vector = new Tripleta[100];
        int c = 0;
        int qf, qc, qv;
        NodoDoble p, q;
        Tripleta tp, tq;
        p = primerNodo();
        while (!finDeRecorrido(p)) {
            q = p.getLigaDerecha();
            while (q != p) {
                tq = (Tripleta) q.getDato();
                qf = tq.getFila();
                qc = tq.getColumna();
                qv = (Integer) tq.getValor();
                Tripleta t = new Tripleta(qf, qc, qv);
                vector[c] = t;
                c++;
                q = q.getLigaDerecha();
            }
            tp = (Tripleta) p.getDato();
            p = (NodoDoble) tp.getValor();
        }
        Tripleta tc = (Tripleta) nodoCabeza().getDato();
        int fila = tc.getFila();
        int columna = tc.getColumna();
        int valor = 0;
        for (int i = 0; i < 100; i++) {
            if (vector[i] != null) {
                valor++;
            }
        }
        String matriz = "";
        int k = 0;
        for (int i = 1; i <= fila; i++) {
            for (int j = 1; j <= columna; j++) {
                if (k < valor && vector[k].getFila() == i && vector[k].getColumna() == j) {
                    
                    String espacios = "";
                    if((int)vector[k].getValor() >= 100){
                        espacios = "     ";
                    }else if((int)vector[k].getValor() >= 10){
                        espacios = "        ";
                    }else{
                        espacios = "          ";
                    }
                    
                    matriz += vector[k].getValor() + espacios;
                    k++;
                } else {
                    matriz += 0 + "          ";          
                }
            }
            matriz += "\n";
        }
        return matriz;
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
