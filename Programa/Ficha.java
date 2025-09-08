// @author Sebastian_Garcia
// @version 1.0
// @fecha 8/09/2025
// Descripción: representa una única ficha en el tablero.

package Programa;

public class Ficha {
    
    // ATRIBUTOS
    private String simbolo;
    private boolean estaVisible;
    private boolean estaEmparejada;

    // CONSTRUCTOR PARA UNA NUEVA FICHA
    public Ficha(String simbolo){
        this.simbolo = simbolo;
        this.estaVisible = false; // todas comienzan tapadas
        this.estaEmparejada = false; // todas comienzan sin estar emparejadas
    }

    // GETTERS
    public String getSimbolo(){
        return simbolo;
    }
    public boolean isEstaVisible(){
        return estaVisible;
    }
    public boolean isEstaEmparejada(){
        return estaEmparejada;
    }

    // SETTERS
    public void setEstaVisible(boolean estaVisible){
        this.estaVisible = estaVisible;
    }
    public void setEstaEmparejada(boolean estaEmparejada){
        this.estaEmparejada = estaEmparejada;
    }
}
