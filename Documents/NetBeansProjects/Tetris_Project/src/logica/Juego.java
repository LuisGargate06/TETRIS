package logica;
//importamos las clases
import modelo.Pieza;
import modelo.Tablero;
import modelo.TipoPieza;
import modelo.TipoBloque;
import java.util.List;

public class Juego {
   private Tablero tablero;
   private GeneradorPiezas generador;
   private Puntaje puntaje;
   private Pieza piezaActual;
   private boolean gameOver;
   
   //Inicializamos todo para iniciar el juego!!!
   public Juego(){
      this.tablero = new Tablero();
      this.generador = new GeneradorPiezas();
      this.puntaje = new Puntaje();
      this.piezaActual = generador.siguiente();
      this.gameOver = false;
   }
   //Constructor con parametros para las pruebas
   public Juego(long semilla) {
      this.tablero = new Tablero();
      this.generador = new GeneradorPiezas(semilla);
      this.puntaje = new Puntaje();
      this.piezaActual = generador.siguiente();
      this.gameOver = false;
   }
   //Movimientos
   private boolean intentarMover(int dFila, int dColumna) {
      if (gameOver) return false;                     // Paso 1
      Pieza copia = piezaActual.clone();              // Paso 2
      copia.mover(dFila, dColumna);                   // Paso 3
      if (tablero.puedeColocar(copia)) {              // Paso 4
         piezaActual.mover(dFila, dColumna);         // Paso 5
         return true;                                // Paso 6
      }
      return false;                                   // Paso 7
   }
   public boolean moverIzquierda() {
      return intentarMover(0, -1);
   }
   public boolean moverDerecha() {
      return intentarMover(0, 1);
   }
   public boolean rotarHorario() {
      if (gameOver) return false;
      Pieza copia = piezaActual.clone();
      copia.rotarHorario();
      if (tablero.puedeColocar(copia)) {
         piezaActual.rotarHorario();
         return true;
      }
      return false;
   }
   public boolean rotarAntihorario() {
      if (gameOver) return false;
      Pieza copia = piezaActual.clone();
      copia.rotarAntihorario();
      if (tablero.puedeColocar(copia)) {
         piezaActual.rotarAntihorario();
         return true;
      }
      return false;
   }
   //Caída Suave (baja solo una fila)
   public boolean caidaSuave() {
      if (gameOver) return false;
      Pieza copia = piezaActual.clone();
      copia.mover(1, 0);
      if (tablero.puedeColocar(copia)) {
         piezaActual.mover(1, 0);
         puntaje.botonCaidaSuave(1);
         return true;
      }
      return false;
   }
   //Caída Rápida (baja todas las filas hasta la ultima)
   public int caidaRapida() {
      if (gameOver) return 0;
      int filas = 0;
      Pieza copia = piezaActual.clone();
      while (true) {                        
         copia.mover(1, 0);
         if (!tablero.puedeColocar(copia)) break;  
         piezaActual.mover(1, 0);          
         filas++;                          
      }
      if (filas > 0) {
         puntaje.botonCaidaRapida(filas);  
      }
      fijarPieza();                        
      return filas;
   }
   //Gravedad
   public boolean aplicarGravedad() {
      if (gameOver) return false;
      Pieza copia = piezaActual.clone();
      copia.mover(1, 0);
      if (tablero.puedeColocar(copia)) {
         piezaActual.mover(1, 0);
         return true;
      } 
      else {
         fijarPieza();
         return false;
      }  
   }
   //Fija la pieza y hace el proceso para que aparezca otra pieza
   private void fijarPieza() {
      //Fija la pieza en el tablero
      tablero.fijar(piezaActual);
      //Nos fijamos si se borraron lines
      int lineasBorradas = tablero.limpiarLineas();
      //Sumamos puntos si se borraron líneas
      puntaje.sumarLineas(lineasBorradas);
      //Generamos nueva pieza
      piezaActual = generador.siguiente();
      //Verificamos si la nueva pieza cabe
      if (!tablero.puedeColocar(piezaActual)) {
         gameOver = true;
      }
   }
   //Ejecutamos los comandos de movimiento
   public void ejecutar(Comando comando) {
      if (gameOver) return;
      switch (comando) {
         case IZQUIERDA:          moverIzquierda();      break;
         case DERECHA:            moverDerecha();         break;
         case ROTAR_HORARIO:      rotarHorario();         break;
         case ROTAR_ANTIHORARIO:  rotarAntihorario();     break;
         case CAIDA_SUAVE:        caidaSuave();           break;
         case CAIDA_RAPIDA:       caidaRapida();          break;
         case GRAVEDAD:           aplicarGravedad();      break;
      }
   }
   //Getters
   public boolean estaTerminado() {
      return gameOver;
   }
   public Pieza getPiezaActual() {
      return piezaActual.clone();
   }
   public int getPuntos() {
      return puntaje.getPuntos();
   }
   public TipoBloque getCelda(int fila, int columna) {
      return tablero.getCelda(fila, columna);
   }
   public int getFilas() {
      return tablero.getFilas();
   }
   public int getColumnas() {
      return tablero.getColumnas();
   }
}