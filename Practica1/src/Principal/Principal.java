package Principal;
import MatricesDispersas.*;
import javax.swing.JOptionPane;

public class Principal {
    static boolean bf2 = false, bt = false;
    static MatrizEnTripletas mt = null;
    static MatrizEnForma1 mf1 = null;
    static MatrizEnForma2 mf2 = null;
    
    public static void main(String[] args) {
        int opcion = 0, m, n, fx, fy;


        do{
           opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "PRACTICA 1", 
                   JOptionPane.INFORMATION_MESSAGE));
           
            switch (opcion) {
                case (1):
                    m = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de Filas (M): ", "PRACTICA 1",
                            JOptionPane.INFORMATION_MESSAGE));

                    n = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de Columnas (N): ", "PRACTICA 1",
                            JOptionPane.INFORMATION_MESSAGE));

                    mf2 = generarForma2(m, n);
                    JOptionPane.showMessageDialog(null, "Matriz forma 2 generada!", "INFORMACION",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                
                case(2):
                    if(bf2){
                        rellenarForma2();
                   }else{
                      JOptionPane.showMessageDialog(null, "Genere primero la matriz F2", "ERROR", 
                      JOptionPane.ERROR_MESSAGE);  
                   }
                    break;
                case(3):
                    if(bf2){
                      JOptionPane.showMessageDialog(null, mf2.matrizCuadrada(), "FORMA 2",
                            JOptionPane.INFORMATION_MESSAGE);  
                   }else{
                      JOptionPane.showMessageDialog(null, "Genere primero la matriz F2", "ERROR", 
                      JOptionPane.ERROR_MESSAGE);  
                   }
                    break;
                case(4):
                    m = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de Filas (M): ", "PRACTICA 1",
                            JOptionPane.INFORMATION_MESSAGE));

                    n = Integer.parseInt(JOptionPane.showInputDialog(null, "Cantidad de Columnas (N): ", "PRACTICA 1",
                            JOptionPane.INFORMATION_MESSAGE));

                    mt = generarTripletas(m, n);           
                    JOptionPane.showMessageDialog(null, "Matriz en tripletas generada!", "INFORMACION",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case(5):
                    if(bt){
                      rellenarTripletas();
                   }else{
                      JOptionPane.showMessageDialog(null, "Genere primero la matriz en tripletas", "ERROR", 
                      JOptionPane.ERROR_MESSAGE);  
                   }
                    break;
                case(6):
                    if(bt){
                      JOptionPane.showMessageDialog(null, mt.matrizCuadrada(), "TRIPLETAS",
                            JOptionPane.INFORMATION_MESSAGE);  
                   }else{
                      JOptionPane.showMessageDialog(null, "Genere primero la matriz en tripletas", "ERROR", 
                      JOptionPane.ERROR_MESSAGE);  
                   }
                    break;
                case(7):
                    if(bf2 && bt){ 
                        if(mt.retornaFilas() == mf2.ordenFila() && mt.retornaColumnas() == mf2.ordenColumna()){
                            mf1 = mt.sumaTripletasYF2ResultadoF1(mf2);
                            JOptionPane.showMessageDialog(null, mf1.matrizCuadrada(), "SUMA",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "Las dimensiones de las matrices deben ser iguales", "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                   }else{
                      JOptionPane.showMessageDialog(null, "Falta generar una de las matrices", "ERROR", 
                      JOptionPane.ERROR_MESSAGE); 
                   }
                    break;
                case(8):
                    if(bf2 && bt){ 
                        if(mt.retornaColumnas() == mf2.ordenFila()){
                            mf1 = mt.multTripletasYF2ResultadoF1(mf2);
                            JOptionPane.showMessageDialog(null, mf1.matrizCuadrada() + "\n"
                                    + "Recuerde que el orden es Tripleta x Forma2!", "MULTIPLICACION",
                                    JOptionPane.INFORMATION_MESSAGE);
                        }else{
                            JOptionPane.showMessageDialog(null, "El numero de columnas de la matriz en tripletas \n"
                                    + "debe ser igual al numero de filas de la matriz en forma 2", "ERROR",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                   }else{
                      JOptionPane.showMessageDialog(null, "Falta generar una de las matrices", "ERROR", 
                      JOptionPane.ERROR_MESSAGE); 
                   }
                    break;
                case(9):
                    if(bf2){
                        String[] options = options(mf2.ordenFila());
                        fx = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Fila: ", "FORMA 2",
                                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]));

                        fy = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Fila: ", "FORMA 2",
                                JOptionPane.INFORMATION_MESSAGE, null, options, options[0]));
                        
                        mf2.intercambiaFilas(fx, fy);
                        JOptionPane.showMessageDialog(null, mf2.matrizCuadrada(), "FORMA 2",
                            JOptionPane.INFORMATION_MESSAGE);
                   }else{
                      JOptionPane.showMessageDialog(null, "Genere primero la matriz F2", "ERROR", 
                      JOptionPane.ERROR_MESSAGE);  
                   }
                    break;
                case (10):
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion incorrecta", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
            }
               
        }while(opcion != 10);
    }
    
    /**
     * Genera la matriz forma 2 para luego almacenarla en la variable global
     * mf2, ademas vuelve verdadero el booleano para saber que fue creada
     * y poder utilizar los metodos que dependan de la matriz forma 2.
     * 
     * @param m filas de la matriz
     * @param n columnas de la matriz
     * @return objeto matriz en forma 2
     */
    public static MatrizEnForma2 generarForma2(int m, int n){    
        MatrizEnForma2 f2 = new MatrizEnForma2(m, n);
        bf2 = true;
        return f2;
    }
    
    /**
     * Genera la matriz en tripletas para luego almacenarla en la variable global
     * mt, ademas vuelve verdadero el booleano para saber que fue creada 
     * y poder utilizar los metodos que dependan de la matriz en tripletas.
     * 
     * @param m filas de la matriz
     * @param n columnas de la matriz
     * @return objeto matriz en tripletas
     */
    public static MatrizEnTripletas generarTripletas(int m, int n){    
        MatrizEnTripletas tr = new MatrizEnTripletas(m, n, 0);
        bt = true;
        return tr;
    }
    
    /**
     * Rellenar las diferentes posiciones de la matriz en forma 2.
     */
    public static void rellenarForma2(){
        NodoDoble x;
        Tripleta t;
        int fila = 0, columna = 0, valor = 0, opcion = 0;
        String[] options = options(mf2.ordenFila()), options2 = options(mf2.ordenColumna());
        
        
        do{
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, opt, "FORMA 2", 
                   JOptionPane.WARNING_MESSAGE));
            
            switch(opcion){
                // Leemos manualmente los datos a ingresar a la matriz
                case(1):        
                    fila = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Fila: ", "FORMA 2",
                            JOptionPane.INFORMATION_MESSAGE, null, options, options[0]));
                    
                    columna = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Columna: ", "FORMA 2",
                            JOptionPane.INFORMATION_MESSAGE, null, options2, options2[0]));

                    valor = Integer.parseInt(JOptionPane.showInputDialog(null, "Valor: ", "FORMA 2",
                            JOptionPane.INFORMATION_MESSAGE));
                    
                    t = new Tripleta(fila, columna, valor);
                    x = new NodoDoble(t);
                    
                    mf2.conectaPorFilas(x);
                    mf2.conectaPorColumnas(x);
                    break;
                case(2):
                    // Llenamos la matriz con datos aleatorios
                    for (int i = 1; i <= mf2.ordenFila(); i++) {
                        for (int j = 1; j <= mf2.ordenColumna(); j++) {
                            int aleatorio = generarNumeroRandom();
                            
                            if (aleatorio != 0) {
                                t = new Tripleta(i, j, aleatorio);
                                x = new NodoDoble(t);

                                mf2.conectaPorFilas(x);
                                mf2.conectaPorColumnas(x);
                            }
                        } 
                    }
                    
                    JOptionPane.showMessageDialog(null, "Llenada aleatoriamente !", "INFORMACION",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case(3):
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion incorrecta", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
            }

        }while(opcion != 3);
    }
    
    /**
     * Rellenar las diferentes posiciones de la matriz en tripletas.
     */
    public static void rellenarTripletas(){
        Tripleta t;
        int fila = 0, columna = 0, valor = 0, opcion = 0;
        String[] options = options(mt.retornaFilas()), options2 = options(mt.retornaColumnas());
        
        do{
            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, opt, "TRIPLETAS", 
                   JOptionPane.WARNING_MESSAGE));
            
            switch(opcion){
                // Leemos manualmente los datos a ingresar a la matriz
                case(1):
                    fila = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Fila: ", "TRIPLETAS",
                            JOptionPane.INFORMATION_MESSAGE, null, options, options[0]));
                    
                    columna = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Columna: ", "TRIPLETAS",
                            JOptionPane.INFORMATION_MESSAGE, null, options2, options2[0]));

                    valor = Integer.parseInt(JOptionPane.showInputDialog(null, "Valor: ", "TRIPLETAS",
                            JOptionPane.INFORMATION_MESSAGE));
                    
                    t = new Tripleta(fila, columna, valor);
                    mt.insertarTripleta(t);
                    break;
                case(2):
                    // Llenamos la matriz con datos aleatorios
                    for (int i = 1; i <= mt.retornaFilas(); i++) {
                        for (int j = 1; j <= mt.retornaColumnas(); j++) {
                            int aleatorio = generarNumeroRandom();
                            
                            if (aleatorio != 0) {
                                t = new Tripleta(i, j, aleatorio);
                                mt.insertarTripleta(t);
                            }
                        } 
                    }
                    
                    JOptionPane.showMessageDialog(null, "Llenada aleatoriamente !", "INFORMACION",
                            JOptionPane.INFORMATION_MESSAGE);
                    break;
                case(3):
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion incorrecta", "ERROR",
                            JOptionPane.ERROR_MESSAGE);
            }
                   
        }while(opcion != 3);
    }

    /**
     * Generamos numeros random para llenar la matriz aleatoriamente
     * @return numero aleatorio
     */
    public static int generarNumeroRandom(){
        // Genera numero aleatorio [0,80)
        int rangoGrande = (int)Math.floor(Math.random()*80);
        
        // Genera numero aletorio entre [0,3)
        int rangoPeque = (int)Math.floor(Math.random()*3);
        
        /* Aplicamos la multiplicacion de ambos rangos
           para que la probabilidad de que salga cero sea mayor.
        */
        return rangoGrande*rangoPeque;
    }
    
    /**
     * Generamos un arreglo desde [1-x] para visualizacion en los inputs
     * @param x numero final del rango
     * @return cadena con el rango 1-x
     */
    public static String[] options(int x){
        String[] result = new String[x];
        
        for (int i = 0; i < x; i++) {
            result[i] = String.valueOf(i+1);
        }
        
        return result;
    }
    
    private static final String menu = "1. Generar Matriz F2 \n" + 
                      "2. Rellenar Matriz F2 \n" +
                      "3. Ver Matriz F2 \n" + 
                      "4. Generar Matriz Tripletas \n" + 
                      "5. Rellenar Matriz en Tripletas \n" +
                      "6. Ver Matriz en Tripletas \n" + 
                      "7. Sumar Matrices Tripletas+F2 (Resultado F1) \n" +
                      "8. Multiplicar Matrices Tripletas*F2 (Resultado F1) \n" +
                      "9. Cambiar Filas en Forma 2 \n" +
                      "10. Salir";
    
    private static final String opt = "1. Ingresar un dato \n"
            + "2. Rellenar toda la matriz con numeros aleatorios \n"
            + "3. Regresar \n";  
}
