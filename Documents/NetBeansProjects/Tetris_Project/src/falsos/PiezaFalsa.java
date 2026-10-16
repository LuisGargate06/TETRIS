package falsos;

/**
 * Pieza provisional (stub). La reemplazará la clase Pieza real de la Persona A.
 * Es una pieza en forma de L dentro de una matriz 3x3 (1 = bloque, 0 = vacío).
 * No conoce su posición: eso lo guarda Juego.
 */
public class PiezaFalsa {

    private int[][] forma;

    /** Crea una pieza en L. */
    public PiezaFalsa() {
        this.forma = new int[][]{
            {0, 1, 0},
            {0, 1, 0},
            {0, 1, 1}
        };
    }

    // Constructor privado: lo usa copiar() para armar la copia
    private PiezaFalsa(int[][] forma) {
        this.forma = forma;
    }

    /** @return la matriz actual de la pieza */
    public int[][] getForma() {
        return forma;
    }

    /** Rota la pieza 90 grados en sentido horario (sobre sí misma). */
    public void rotar() {
        int n = forma.length;
        int[][] nueva = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nueva[j][n - 1 - i] = forma[i][j];
            }
        }
        forma = nueva;
    }

    /** @return una copia independiente (copia profunda de la matriz) */
    public PiezaFalsa copiar() {
        int[][] copia = new int[forma.length][];
        for (int i = 0; i < forma.length; i++) {
            copia[i] = forma[i].clone();
        }
        return new PiezaFalsa(copia);
    }
}