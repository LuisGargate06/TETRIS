package tetris;

import logica.Juego;
import logica.Comando;
import vista.VistaConsola;
import excepciones.ComandoInvalidoException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
      //Creamos el juego y la consola
      Juego juego = new Juego(7); // Semilla 7 para reproducibilidad (puedes usar new Juego())
      VistaConsola vista = new VistaConsola();
      Scanner scanner = new Scanner(System.in);

      //Bucle del jueo
      while (!juego.estaTerminado()) {
         System.out.println(vista.dibujar(juego));
         System.out.println(vista.ayuda());
         System.out.print("> ");

         //Leer la entrada 
         String linea = scanner.nextLine();

         try {
            List<Comando> comandos = vista.interpreter(linea);
            for (Comando c : comandos) {
               juego.ejecutar(c);
            }
         } 
         catch (ComandoInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
         }
      }
      //Fin del juego
      System.out.println("¡Game Over!");
      System.out.println("Puntos finales: " + juego.getPuntos());
    }
}