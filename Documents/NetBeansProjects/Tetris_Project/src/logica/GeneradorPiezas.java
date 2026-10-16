package logica;

import modelo.Pieza;
import modelo.TipoPieza;
import java.util.Queue;//interfaz para la cola
import java.util.LinkedList;//crea la cola
import java.util.List;//Interfaz para la lista
import java.util.ArrayList;//Crea la lista
import java.util.Collections;
import java.util.Random;

public class GeneradorPiezas {
   private Queue<TipoPieza> cola; //Guarda las piezas que salen en orden
   private Random aleatorio;
   //Constructor
   public GeneradorPiezas() {
      this.cola = new LinkedList<>(); //cola vacia
      this.aleatorio = new Random(); //
      llenarBolsa(); //Llena la cola
   }
   //Constructor con parametro
   public GeneradorPiezas(long semilla) {
      this.cola = new LinkedList<>();
      this.aleatorio = new Random(semilla); //Ponemos cualquier numero y nos va arrojar una secuencia que siempre va perdurar
      llenarBolsa();
   }
   //Funciones
   private void llenarBolsa() {
      List<TipoPieza> bolsa = new ArrayList<>();
      for (TipoPieza tipo : TipoPieza.values()) { //Se pone los 7 tipos de pieza en la bolsa
         bolsa.add(tipo);
      }
      //Mezclamos los elementos de la lista(bolsa) usando el dado que indica como hacerlo
      Collections.shuffle(bolsa, aleatorio);
      cola.addAll(bolsa); //Los elementos de la clista(bolsa) lo pasamos a la cola
   }
   
   // Cuando ya se acaban las piezas se genera una nueva bolsa con las piezas
   public Pieza siguiente() {
      if (cola.size() < 7) {
         llenarBolsa();
      }
      TipoPieza tipo = cola.poll(); //sca la primera piezza de la cola
      Pieza pieza = tipo.crearPieza(); //crea la pieza segun el tipo
        
      //Centramos la pieza
      int columnaCentro = (10 - pieza.getTamano()) / 2;
      pieza.colocarEn(0, columnaCentro);
      return pieza; 
   }

   //Sirve para poder las siguientes piezas que nos tocara antes que salgan
   public List<TipoPieza> verSiguientes(int n) {
      List<TipoPieza> lista = new ArrayList<>(cola); //Copia de la cola
        
      // Si no hay suficientes n elementos, se completan
      while (lista.size() < n) {
         List<TipoPieza> bolsa = new ArrayList<>();
         for (TipoPieza tipo : TipoPieza.values()) {
            bolsa.add(tipo);
         }
         Collections.shuffle(bolsa, aleatorio);
         lista.addAll(bolsa);
      }  
      return lista.subList(0, n);
   }
}