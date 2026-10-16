package excepciones;

public class ComandoInvalidoException extends Exception {
    public ComandoInvalidoException(String mensaje) {
        super(mensaje);
    }
}