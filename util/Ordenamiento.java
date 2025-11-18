package util;
import java.util.ArrayList;
import model.Comparable;

public class Ordenamiento {
    /**
     * Ordena una lista ascendentemente
     * 
     * @param arreglo una lista con instancias de clase Comparable
     */
    public static void ordenarAscendente(ArrayList<Comparable> arreglo) { 
    boolean huboIntercambio; 
    do { 
        huboIntercambio = false; 
        for (int i = 0; i < arreglo.size() - 1; i++) { 
            Comparable a = arreglo.get(i); 
            Comparable b = arreglo.get(i + 1); 
            if (b.menorQue(a)) { 
                arreglo.set(i, b); 
                arreglo.set(i + 1, a); 
                huboIntercambio = true; 
            } 
        } 
    } while (huboIntercambio); 
    } 
} 