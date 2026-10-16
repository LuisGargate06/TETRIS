package falsos;

/**
 * Tablero provisional (stub). Lo reemplazará el Tablero real de la Persona B.
 * Tamaño 20 filas x 10 columnas. Coordenadas siempre (fila, columna).
 * 0 = vacío, 1 = ocupado.
 */
public class TableroFalso {

    public static final int FILAS = 20;
    public static final int COLUMNAS = 10;

    private int[][] celdas = new int[FILAS][COLUMNAS];

    /**
     * Indica si la pieza cabe con su esquina superior izquierda en (fila, col):
     * dentro del tablero y sin chocar con bloques ya fijados.
     */
    public boolean cabe(PiezaFalsa pieza, int fila, int col) {
        int[][] forma = pieza.getForma();
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j] == 1) {
                    int f = fila + i;
                    int c = col + j;
                    if (f < 0 || f >= FILAS || c < 0 || c >= COLUMNAS) {
                        return false;
                    }
                    if (celdas[f][c] != 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /** Deja los bloques de la pieza fijos en el tablero. */
    public void fijar(PiezaFalsa pieza, int fila, int col) {
        int[][] forma = pieza.getForma();
        for (int i = 0; i < forma.length; i++) {
            for (int j = 0; j < forma[i].length; j++) {
                if (forma[i][j] == 1) {
                    celdas[fila + i][col + j] = 1;
                }
            }
        }
    }

    /**
     * Borra las filas completas y baja las de arriba.
     * @return cuántas filas se borraron
     */
    public int borrarLineasCompletas() {
        int borradas = 0;
        int f = FILAS - 1;
        while (f >= 0) {
            if (filaCompleta(f)) {
                for (int k = f; k > 0; k--) {
                    celdas[k] = celdas[k - 1].clone();
                }
                celdas[0] = new int[COLUMNAS];
                borradas++;
                // no se baja f: la fila nueva en esta posición hay que revisarla
            } else {
                f--;
            }
        }
        return borradas;
    }

    private boolean filaCompleta(int f) {
        for (int c = 0; c < COLUMNAS; c++) {
            if (celdas[f][c] == 0) {
                return false;
            }
        }
        return true;
    }

    /** Dibuja el tablero en texto: '.' vacío, '#' ocupado. */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                sb.append(celdas[f][c] == 0 ? '.' : '#');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
}