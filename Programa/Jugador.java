// @author Sebastian_Garcia
// @version 1.0
// @fecha 8/09/2025
// Descripción: representa a uno de los jugadores de la partida.

package Programa;

public class Jugador {
    // ATRIBUTOS
    private String nombre;
    private int puntuacion;

    // CONSTRUCTOR
    public Jugador(String nombre){
        this.nombre = nombre;
        this.puntuacion = 0; // inicia en 0
    }

    public void incrementarPuntuacion(){
        this.puntuacion++;
    }

    // GETTERS
    public String getNombre(){
        return nombre;
    }
    public int getPuntuacion(){
        return puntuacion;
    }
}
