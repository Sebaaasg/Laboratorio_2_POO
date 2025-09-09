// @author Sebastian_Garcia
// @version 1.0
// @fecha 8/09/2025
// Descripción: Maneja el flujo de la aplicación, conecta la vista con el modelo.
// maneja las excepciones lanzadas por el modelo

package Programa;

public class Controlador {
    // ATRIBUTOS
    private JuegoMemoria modelo;
    private Vista vista;

    // CONSTRUCTOR
    public Controlador(JuegoMemoria modelo, Vista vista){
        this.modelo = modelo;
        this.vista = vista;
    }

    // PUNTO DE INICIO DEL PROGRAMA
    public static void main(String[] args) {
        Vista vista = new Vista();

        // pedir datos iniciales al usuario
        String[] nombres = vista.solicitarNombresJugadores();
        int dimension = vista.solicitarDimensionTablero();

        // creación del modelo
        JuegoMemoria modelo = new JuegoMemoria(nombres[0], nombres[1], dimension);
        
        Controlador controlador = new Controlador(modelo, vista);
        controlador.iniciarJuego();
    }

    // GESTIÓN DEL FLUJO DE LA PARTIDA
    public void iniciarJuego(){
        vista.mostrarMensaje("Bienvenido al juego MEMORIA");

        boolean seguirJugando = true;
        while (seguirJugando) {
            jugarPartida(); // ejecuta la partida completa

            // resultados finales de la partida
            Jugador[] jugadores = modelo.getJugadores();
            vista.mostrarMensaje("\nFIN DE LA PARTIDA");
            vista.mostrarMensaje(jugadores[0].getNombre() + ": " + jugadores[0].getPuntuacion() + " puntos");
            vista.mostrarMensaje(jugadores[1].getNombre() + ": " + jugadores[1].getPuntuacion() + " puntos");

            if (jugadores[0].getPuntuacion() > jugadores[1].getPuntuacion()) {
                vista.mostrarMensaje("HA GANADO " + jugadores[0].getNombre());
            } else if (jugadores[1].getPuntuacion() > jugadores[0].getPuntuacion()) {
                vista.mostrarMensaje("HA GANADO " + jugadores[1].getNombre());
            } else {
                vista.mostrarMensaje("EMPATEEE!");
            }

            // JUGAR DE NUEVO
            if (vista.preguntarJugarDeNuevo()) {
                modelo = new JuegoMemoria(jugadores[0].getNombre(), jugadores[1].getNombre(), 3); // reinicio de juego
            } else {
                seguirJugando = false;
            }
        }

        vista.mostrarMensaje("FIN DEL JUEGO, ADIOS :)");
    }

    // GESTIÓN DEL BUCLE DE TURNOS HASTA QUE TERMINA LA PARTIDA
    private void jugarPartida(){
        while (!modelo.verificarFinDeJuego()) {
            gestionarTurno();
        }
    }

    // GESTIÓN DE UN ÚNICO TURNO DE UN JUGADOR
    private void gestionarTurno(){
        vista.mostrarTablero(modelo.getTablero());
        vista.mostrarMensaje("\n Turno de: " + modelo.getJugadorActual().getNombre());

        boolean jugadaValida = false;
        while (!jugadaValida) {
            try{
                // pedir coordenadas
                int[] coords1 = vista.solicitarCoordenadas("Primera", modelo.getTablero().getDimension());
                int[] coords2 = vista.solicitarCoordenadas("Segunda", modelo.getTablero().getDimension());

                // el controlador pasa la jugada a modelo
                boolean fuePar = modelo.realizarJugada(coords1[0], coords1[1], coords2[0], coords2[1]);

                // el controlador actualiza la vista
                vista.mostrarTablero(modelo.getTablero());

                if (fuePar) {
                    vista.mostrarMensaje("ENCONTRASTE UN PAR, CONTINUA JUGANDO");
                } else {
                    vista.mostrarMensaje("NO ES PAR. Las fichas se ocultarán");
                    // pausa para que de tiempo a ver las fichas
                    try{
                        Thread.sleep(2000);
                    } catch (InterruptedException e){}

                    // ocultar fichas
                    modelo.getTablero().getFicha(coords1[0], coords1[1]).setEstaVisible(false);
                    modelo.getTablero().getFicha(coords2[0], coords2[1]).setEstaVisible(false);
                    modelo.cambiarTurno();
                }

                jugadaValida = true; // se sale del bucle
            } catch (JugadaNoValidaException e) {
                // si hay error, el controlador lo atrapa y se lo da a la vista
                vista.mostrarMensaje(e.getMessage() + "Intente otra vez por favor.");

            }
        }
    }
}
