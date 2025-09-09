// @author Sebastian_Garcia
// @version 1.0
// @fecha 8/09/2025
// Descripción: representa el tablero del juego.

package Programa;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

public class Tablero {
    // ATRIBUTOS
    private Ficha[][] cuadricula;
    private int dimension;

    // CONSTRUCTOR  
    public Tablero(int dimension){
        // Se hace obligatorio que el tamaño del tablero sea un número par
        if (dimension % 2 != 0){
            throw new IllegalArgumentException("La dimensión del tablero tiene que ser un número par.");
        }

        this.dimension = dimension;
        this.cuadricula = new Ficha[dimension][dimension];
        inicializarFichas();
    }

    // LÓGICA PARA LAS FICHAS: crear los pares, aleatorizar su orden y ponerlas en el tablero
    private void inicializarFichas(){
        // SIMBOLOS
        ArrayList<String> simbolosBase = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R"));

        int numeroDePares = (dimension * dimension) / 2;
        ArrayList<String> simbolosParaJuego = new ArrayList<>(); 

        // SE AÑADE CADA SIMBOLO 2 VECES PARA LOS PARES
        for (int i = 0; i < numeroDePares; i++){
            simbolosParaJuego.add(simbolosBase.get(i));
            simbolosParaJuego.add(simbolosBase.get(i));
        }

        // ALEATORIZAR BARAJA PARA QUE SU POSICIÓN SEA SIEMPRE AL AZAR
        Collections.shuffle(simbolosParaJuego);

        // LLENAR LA CUADRÍCULA CON FICHAS USANDO LOS SÍMBOLOS
        int w = 0;
        for (int filas = 0; filas < dimension; filas++){
            for(int col = 0; col < dimension; col++){
                cuadricula[filas][col] = new Ficha(simbolosParaJuego.get(w));
                w++;
            }
        }
    }

    // GETTERS
    public Ficha getFicha(int fila, int col){
        return cuadricula[fila][col];
    }
    public int getDimension(){
        return dimension;
    }
}
