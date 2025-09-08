// @author Sebastian_Garcia
// @version 1.0
// @fecha 8/09/2025
// Descripción: Maneja toda la interacción con el usuario. Única clase que puede contener sysout y scanner.

package Programa;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Vista {
    // ATRIBUTOS 
    private Scanner scanner;

    // CONSTRUCTOR 
    public Vista(){
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    // DIBUJAR TABLERO EN CONSOLA
    public void mostrarTablero(Tablero tablero){
        System.out.print("\n ");
        for (int i = 0; i < tablero.getDimension(); i++){
            System.out.print(i + " ");
        }

        System.out.println();
        for (int fila = 0; fila < tablero.getDimension(); fila++){
            System.out.print(fila + " ");
            for (int col = 0; col < tablero.getDimension(); col++){
                Ficha ficha = tablero.getFicha(fila, col);
                if (ficha.isEstaVisible() || ficha.isEstaEmparejada()) {
                    System.out.print(ficha.getSimbolo() + " ");
                } else {
                    System.out.print("⬛ ");
                }
            }
            System.out.println();
        }
    }

    // SE PIDEN COORDENADAS AL USUARIO PARA HACER UNA JUGADA
    public int[] solicitarCoordenadas(String numJugada, int dimension){
        boolean bandera = true;
        while (bandera) {
            try{
                System.out.print("INGRESE la FILA de la " + numJugada + " ficha: ");
                int fila = scanner.nextInt();
                System.out.print("INGRESE la COLUMNA de la " + numJugada + " ficha: ");
                int columna = scanner.nextInt();
                scanner.nextLine(); // limpiar

                // verificar que las cords estén dentro del tablero
                if (fila >= 0 && fila < dimension && columna >= 0 && columna <dimension) {
                    return new int[]{fila, columna};
                } else {
                    System.out.println("ERROR: coordenadas fuera del rango del tablero. intente otra vez.");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: debe ingresar un número entero. intente otra vez");
                scanner.nextLine(); // limpiar
            }
        }
    }

    public boolean preguntarJugarDeNuevo(){
        System.out.println("\n Quieren jugar de nuevo? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s");
    }
}

