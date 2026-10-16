package vista;

import logica.Juego;
import logica.Comando;
import excepciones.ComandoInvalidoException;
import modelo.TipoBloque;
import java.util.List;
import java.util.ArrayList;

public class VistaConsola {
   //Comenzamos a dibujar el juego
   public String dibujar(Juego juego) {
      StringBuilder sb = new StringBuilder(); //Es una clase propia de java

      
      sb.append("+");
      for (int c = 0; c < juego.getColumnas(); c++){
         sb.append("---+");
      }
      sb.append("\n");

      for (int f = 0; f < juego.getFilas(); f++) {
         sb.append("|");
         for (int c = 0; c < juego.getColumnas(); c++){
            TipoBloque bloque = juego.getCelda(f, c);
            sb.append(" ").append(bloque.getSimbolo()).append(" |");
         }
         sb.append("\n");

         sb.append("+");
         for (int c = 0; c < juego.getColumnas(); c++) {
            sb.append("---+");
         }
         sb.append("\n");
      }

      //Muestra la información del juego
      sb.append("Puntos: ").append(juego.getPuntos()).append("\n");
      sb.append("Terminado: ").append(juego.estaTerminado()).append("\n");

      return sb.toString();
   }

   //Pasamos a que cada letra sea un camndo
   public List<Comando> interpreter(String linea) throws ComandoInvalidoException {
      List<Comando> comandos = new ArrayList<>();

      //Si no hay nada, la gravedad actua
      if (linea.isEmpty()) {
         comandos.add(Comando.GRAVEDAD);
         return comandos;
      }

      //Fiajmos los casos
      for (char c : linea.toCharArray()) {
         switch (c) {
            case 'a': comandos.add(Comando.IZQUIERDA); break;
            case 'd': comandos.add(Comando.DERECHA); break;
            case 'w': comandos.add(Comando.ROTAR_HORARIO); break;
            case 's': comandos.add(Comando.CAIDA_SUAVE); break;
            case 'x': comandos.add(Comando.CAIDA_RAPIDA); break;
            default:
               throw new ComandoInvalidoException("Comando desconocido: " + c);
         }
      }
      return comandos;
   }

   //Guía de juego
   public String ayuda() {
      return "a=izquierda, d=derecha, w=rotar, s=caida suave, x=caida rapida, Enter=gravedad";
   }
}