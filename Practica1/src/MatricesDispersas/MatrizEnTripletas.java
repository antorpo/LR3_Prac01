package MatricesDispersas;

public class MatrizEnTripletas {
    private Tripleta[] V;

    public MatrizEnTripletas(int fila, int columna, int valor){
        Tripleta t = new Tripleta(fila, columna, 0);
        V = new Tripleta[fila*columna + 2];
        V[0] = t;
    }

    public int retornaFilas(){
        return V[0].getFila();
    }

    public int retornaColumnas(){
        return V[0].getColumna();
    }

    public int retornaNumeroElementos(){
        return (int) V[0].getValor();
    }

    public Tripleta retornaTripleta(int i){
        return V[i];
    }

    public String matrizCuadrada(){
        String matriz = "";
        int x = 1, p = this.retornaNumeroElementos(), valor = 0;
        
        for (int i = 1; i <= retornaFilas(); i++) {
            for (int j = 1; j <= retornaColumnas(); j++) {
                
                if(x<=p && (i == V[x].getFila() && j == V[x].getColumna())){
                    valor = (int) V[x].getValor();
                    
                    String espacios = "";
                    if(valor >= 100){
                        espacios = "     ";
                    }else if(valor >= 10){
                        espacios = "        ";
                    }else{
                        espacios = "          ";
                    }
                    
                    matriz += valor + espacios;
                    x++;
                }else{
                    matriz += 0 + "          ";
                }
                
            }
            
            matriz += "\n";
        }
        
        return matriz;
    }
    
    
    public void insertarTripleta(Tripleta t){
        int fila = t.getFila(), columna = t.getColumna();
        int p = (int) V[0].getValor(), i = 1;

        while(i <= p && fila >= V[i].getFila()){

            if(fila == V[i].getFila() && columna <= V[i].getColumna()){
                break;
            }

            i++;
        }

        if(V[i] == null) V[i] = new Tripleta(0, 0, 0);
        
        if(!(fila == V[i].getFila() && columna == V[i].getColumna())){
            int j = p;

            while(j>=i){
                V[j+1] = V[j];
                j--;
            }

            p++;
            V[0].setValor(p);
        }

        V[i] = t;
    }
    
    /**
     * Sumar matriz en tripletas con matriz en forma 2
     * @param mf2 matriz forma 2 a sumar
     * @return resultado de la suma representado como matriz en forma 1
     */
    public MatrizEnForma1 sumaTripletasYF2ResultadoF1(MatrizEnForma2 mf2){
        NodoDoble q = mf2.nodoCabeza().getLigaDerecha(), x;
        Tripleta tq = (Tripleta) q.getDato(), tx;
        int m = mf2.ordenFila(), n = mf2.ordenColumna(), p, i;
        p = retornaNumeroElementos();
        i = 1;
        
        // Creamos la matriz resultado
        MatrizEnForma1 mf1 = new MatrizEnForma1(m, n);
        mf1.construyeNodosCabeza();
        
        // Evaluamos si cumple la condicion de las dimensiones para poder operar
        if( retornaFilas() == m && retornaColumnas() == n ){
            
            // Mientras tengamos elementos de la matriz en tripleta por recorrer
            // Y no hallamos terminado el recorrido de la matriz forma 2 haga:
            while(i <= p && !(mf2.finDeRecorrido(q))){  
                
                /*
                    Para poder sumar vamos a mirar la fila y columna de la posicion en la que vamos
                    tanto en tripletas como en F2, si coinciden exactamente en fila y columna sumamos
                    los valores y guardamos en F1, si no coinciden buscamos por posicion este antes que el 
                    otro.
                
                    Comparando filas primero y luego columnas para encontrar el que posea posicion menor.
                */
                
                // Si fila F2 es menor que la fila de Tripletas
                if(V[i].getFila() > tq.getFila()){
                    
                    // Agregamos el dato de esa posicion a F1
                    tx = new Tripleta(tq.getFila(), tq.getColumna(), tq.getValor());
                    x = new NodoDoble(tx);
                    
                    // Avanzamos la matriz F2 y conectamos en F1
                    mf1.conectaPorFilas(x);
                    mf1.conectaPorColumnas(x);
                    q = q.getLigaDerecha();
                    tq = (Tripleta) q.getDato();
                }else if(V[i].getFila() < tq.getFila()){ // Si fila Tripletas es menor que fila F2
                    
                    // Agregamos el dato de esa posicion a F1
                    tx = new Tripleta(V[i].getFila(), V[i].getColumna(), V[i].getValor());
                    x = new NodoDoble(tx);
                    
                    // Avanzamos la matriz en Tripletas y conectamos en F1
                    mf1.conectaPorFilas(x);
                    mf1.conectaPorColumnas(x);
                    i++;
                }else if(V[i].getFila() == tq.getFila()){ // Si la fila de Tripletas y F2 son iguales comparamos columnas
                    
                    // Si columna F2 es menor que la columna de Tripletas
                    if(V[i].getColumna() > tq.getColumna()){
                        tx = new Tripleta(tq.getFila(), tq.getColumna(), tq.getValor());
                        x = new NodoDoble(tx);
                        
                        mf1.conectaPorFilas(x);
                        mf1.conectaPorColumnas(x);
                        q = q.getLigaDerecha();
                        tq = (Tripleta) q.getDato();
                    }else if(V[i].getColumna() < tq.getColumna()){ // Si columna Tripletas es menor que columna F2
                        tx = new Tripleta(V[i].getFila(), V[i].getColumna(), V[i].getValor());
                        x = new NodoDoble(tx);

                        mf1.conectaPorFilas(x);
                        mf1.conectaPorColumnas(x);
                        i++;
                    }else if(V[i].getColumna() == tq.getColumna()){ 
                        // Si son iguales las columnas de Tripletas y F2
                        // sumamos los valores y agregamos a F1
                    
                        int suma = (int)V[i].getValor() + (int)tq.getValor();
                        tx = new Tripleta(tq.getFila(), tq.getColumna(), suma);
                        x = new NodoDoble(tx);
                        
                        mf1.conectaPorFilas(x);
                        mf1.conectaPorColumnas(x);
                        i++;
                        q = q.getLigaDerecha();
                        tq = (Tripleta) q.getDato();
                    }  
                }
            }
            
            // Si quedaron datos por recorrer del ciclo de arriba terminamos de llenar F1 
            while(i <= p){
                tx = new Tripleta(V[i].getFila(), V[i].getColumna(), V[i].getValor());
                x = new NodoDoble(tx);

                mf1.conectaPorFilas(x);
                mf1.conectaPorColumnas(x);
                i++;
            }
            
            // Si quedaron datos por recorrer del ciclo de arriba terminamos de llenar F1 
            while(!(mf2.finDeRecorrido(q))){
                tx = new Tripleta(tq.getFila(), tq.getColumna(), tq.getValor());
                x = new NodoDoble(tx);

                mf1.conectaPorFilas(x);
                mf1.conectaPorColumnas(x);
                q = q.getLigaDerecha();
                tq = (Tripleta) q.getDato();
            }
        }
        
        return mf1;
    }
    
    /**
     * Para multiplicar las matrices nos vamos a apoyar de un vector adicional 
     * de resultados el cual va a ir almacenando los resultados de las multiplicaciones 
     * parciales para al terminar de recorrer las matrices tener el resultado final.
     * 
     * @param mf2 matriz forma 2 a multiplicar
     * @return resultado de la multiplicacion representado como matriz en forma 1
     */
    public MatrizEnForma1 multTripletasYF2ResultadoF1(MatrizEnForma2 mf2){
        NodoDoble w = mf2.nodoCabeza().getLigaIzquierda(),q = w, x;
        Tripleta tq = (Tripleta) q.getDato();
        int m = mf2.ordenFila(), n = mf2.ordenColumna(), p, i, valor = 0;
        p = retornaNumeroElementos();
        i = 1;
        
        // Creamos la matriz resultado:1
        MatrizEnForma1 mf1 = new MatrizEnForma1(retornaFilas(), n);
        mf1.construyeNodosCabeza();
        
        // En este vector de tripletas almacenaremos parcialmente los resultados de las multiplicaciones:
        Tripleta T[] = new Tripleta[retornaFilas()*n];
        // formula de direccionamiento:  (i-1)n + j 
        
        
        // Miramos si cumple las condiciones de las dimensiones para poder multiplicar
        if( retornaColumnas() == m ){
            
            // Mientras tengamos elementos de la matriz en tripleta por recorrer
            while(i <= p){
                
                // Recorremos la matriz en F2 por cada elemento de la matriz en tripletas
                while(!(mf2.finDeRecorrido(q))){
                    
                    // Buscamos todos los elementos donde la columna tripletas == fila F2
                    // Si son iguales quiere decir que se deben multiplicar
                    if(V[i].getColumna() == tq.getFila()){
                       
                       // Calculamos la posicion para guardar el resultado en el vetor auxiliar de resultados
                       int pos = ((V[i].getFila()-1)*n + tq.getColumna())-1;
                       valor = (int) V[i].getValor() * (int) tq.getValor();
                       
                       // Si esa posicion nunca habia sido revisada antes la creamos
                       if(T[pos] == null){
                           T[pos] = new Tripleta(V[i].getFila(), tq.getColumna(), valor);
                       }else{ // Si ya habia sido visitada antes concatenamos el valor de esta otro multiplicacion
                           T[pos].setValor((int) T[pos].getValor() + valor);
                       }
                    }
                    
                    q = q.getLigaIzquierda();
                    tq = (Tripleta) q.getDato();
                }
                
                // Igualamos q a w para poder volver a recorrer la matriz F2
                q = w;
                tq = (Tripleta) q.getDato();
                i++; 
            }
        }

        // Ahora simplemente todos los elementos del vector de resultados diferentes de null
        // los adicionamos a un nodo y los conectamos con nuestra matriz resultado.
        for (int j = 0; j < T.length; j++) {
            if(T[j] != null){
                x = new NodoDoble(T[j]);

                mf1.conectaPorFilas(x);
                mf1.conectaPorColumnas(x);         
            }
        }
        
        
        return mf1;
    }
    
    
}
