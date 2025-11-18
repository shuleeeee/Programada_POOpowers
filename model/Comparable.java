package model;


/**
 * Define el comportamiento de menorQue
 *
 * @author Esteban
 * @version 11/17/2025
 */
public interface Comparable {
    /**
     * valida que un objeto sea menor a otro
     *
     * @param  obj una instancia de Comparable
     * @return   true si es menor que el objeto pasado como parametro
     */
    boolean menorQue(Comparable obj);
}
