// @author Sebastian_Garcia
// @version 1.0
// @fecha 8/09/2025
// Descripción: Clase principal del modelo. Contiene la lógica principal del programa
// contiene el tablero, los jugadores, procesa jugadas y cambia turnos.

package Programa;

public class JuegoMemoria {
    // ATRIBUTOS
    private Tablero tablero;
    private Jugador[] jugadores;
    private Jugador jugadorActual;

    // CONSTRUCTOR
    public JuegoMemoria(String nombreJ1, String nombreJ2, int dimension){
        this.tablero = new Tablero(dimension);
        this.jugadores = new Jugador[]{new Jugador(nombreJ1), new Jugador(nombreJ2)};
        this.jugadorActual = this.jugadores[0]; // empieza jugador1
    }

    // MÉTODO PARA PROCESAR JUGADA: aplica las validaciones de la jugada
    public boolean realizarJugada(int f1, int c1, int f2, int c2) throws JugadaNoValidaException {
        
        //Validaciones
        if (f1 == f2 && c1 == c2){
            throw new JugadaNoValidaException("ERROR: no se puede seleccionar la misma casilla dos veces");
        }
        if (tablero.getFicha(f1, c1).isEstaEmparejada() || tablero.getFicha(f2, c2).isEstaEmparejada()) {
            throw new JugadaNoValidaException("ERROR: una de las casillas seleccionadas ya está emparejada");
        }

        // revelar fichas
        Ficha ficha1 = tablero.getFicha(f1, c1);
        Ficha ficha2 = tablero.getFicha(f2, c2);
        ficha1.setEstaVisible(true);
        ficha2.setEstaVisible(true);

        // se comprueba si es par
        if (ficha1.getSimbolo().equals(ficha2.getSimbolo())) {
            ficha1.setEstaEmparejada(true);
            ficha2.setEstaEmparejada(true);
            jugadorActual.incrementarPuntuacion();
            return true; // FUE PAR
        }

        return false; // SI NO FUE PAR
    }

    // CAMBIO DE TURNO
    public void cambiarTurno(){
        if (jugadorActual == jugadores[0]) {
            jugadorActual = jugadores[1];
        } else {
            jugadorActual = jugadores[0];
        }
    }

    // VERIFICAR SI YA SE EMPAREJÓ EL TABLERO
    public boolean verificarFinDeJuego(){
        for (int fila = 0; fila < tablero.getDimension(); fila++){
            for (int col = 0; col < tablero.getDimension(); fila++){
                if (!tablero.getFicha(fila, col).isEstaEmparejada()){
                    return false; // sigue la partida si todavía no se completa todo
                }
            }
        }
        return true; // se termina si todas están emparejadas
    }

    // GETTERS
    public Tablero getTablero(){
        return tablero;
    }
    public Jugador getJugadorActual(){
        return jugadorActual;
    }
    public Jugador[] getJugadores(){
        return jugadores;
    }

}
