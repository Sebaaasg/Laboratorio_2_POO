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
        int[] coordenadas = new int[2];
        boolean entradaValida = false;
        do{
            try{
                System.out.print("INGRESE la FILA de la " + numJugada + " ficha: ");
                int fila = scanner.nextInt();
                System.out.print("INGRESE la COLUMNA de la " + numJugada + " ficha: ");
                int columna = scanner.nextInt();
                scanner.nextLine(); // limpiar

                // verificar que las cords estén dentro del tablero
                if (fila >= 0 && fila < dimension && columna >= 0 && columna <dimension) {
                    coordenadas[0] = fila;
                    coordenadas[1] = columna;

                    // cambiar bandera
                    entradaValida = true;
                } else {
                    System.out.println("ERROR: coordenadas fuera del rango del tablero. intente otra vez.");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: debe ingresar un número entero. intente otra vez");
                scanner.nextLine(); // limpiar
            }
        } while (!entradaValida);

        return coordenadas;
    }

    public boolean preguntarJugarDeNuevo(){
        System.out.println("\n Quieren jugar de nuevo? (s/n): ");
        String respuesta = scanner.nextLine().trim().toLowerCase();
        return respuesta.equals("s");
    }

// SOLICITUD DE INFORMACIÓN INICIAL DEL JUEGO
    public String[] solicitarNombresJugadores() {
        mostrarMensaje("--- CONFIGURACIÓN DE LA PARTIDA ---");
        System.out.print("Ingrese el nombre del Jugador 1: ");
        String nombre1 = scanner.nextLine();
        System.out.print("Ingrese el nombre del Jugador 2: ");
        String nombre2 = scanner.nextLine();
        return new String[]{nombre1, nombre2};
    }

    
    public int solicitarDimensionTablero() {
        int dimension = 0;
        boolean entradaValida = false;

        //  bucle do-while para evitar usar while(true)
        do {
            try {
                System.out.print("Ingrese la dimensión del tablero (ejemplo: 4 para 4x4). Debe ser un número par: ");
                dimension = scanner.nextInt();
                scanner.nextLine(); // Limpiar

                // Validación
                if (dimension > 0 && dimension % 2 == 0) {
                    entradaValida = true; // Si es válido se cambia la bander y fin
                } else {
                    mostrarMensaje("Error: La dimensión debe ser un número par y positivo. Intente de nuevo.");
                }

            } catch (InputMismatchException e) {
                mostrarMensaje("Error: Debe ingresar un número entero. Intente de nuevo.");
                scanner.nextLine(); // Limpiar el buffer si la entrada fue incorrecta
            }
        } while (!entradaValida);

        return dimension;
    }
}

