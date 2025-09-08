// @author Sebastian_Garcia
// @version 1.0
// @fecha 8/09/2025
// Descripción: Excepción personalizada para manejar errores del programa.

package Programa;

public class JugadaNoValidaException extends Exception { // La clase hereda los atributos y métodos de Exception
    
    // Constructor
    public JugadaNoValidaException(String messsage){
        super(messsage); // el super llama al constructor de la clase padre
    }
}
