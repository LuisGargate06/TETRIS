package logica;

public class Puntaje {
   private int puntos;
   private int totalLineas;
   private int nivel;
   //El constructor inicializa todo en 0, pues recien esta iniciando el juego
   public Puntaje(){
      this.puntos = 0;
      this.totalLineas = 0;
      this.nivel = 1;
   }
   //Escribimos los getters
   public int getPuntos(){return puntos;}
   public int getTotalLineas(){return totalLineas;}
   public int getNivel(){return nivel;}
   public void sumarLineas(int lineas){
      if (lineas > 0){
         this.totalLineas += lineas;
         if (lineas == 1) {this.puntos += 100;}
         else if (lineas == 2) {this.puntos += 300;}
         else if (lineas == 3) {this.puntos += 500;}
         else if (lineas == 4) {this.puntos += 800;}
         this.nivel = 1 + (this.totalLineas / 10);
      }
   }
   public void botonCaidaSuave(int filas) {
      this.puntos += filas * 1;
   }

   public void botonCaidaRapida(int filas) {
      this.puntos += filas * 2;
   }
   //Cuando se reinicia el juego
   public void reiniciar(){
      this.puntos = 0;
      this.totalLineas = 0;
      this.nivel = 1;
   }
   //Devuelve los datos acumulados en ese momento
   //Escribimo @Override para saber si esta bien como sobreescribimos el metodo
   @Override
   public String toString(){
      return "\nLograste un total de "+ getPuntos() + " puntos, eliminaste un total de "+ getTotalLineas()+ " lineas y alcanzaste el nivel" + getNivel() + ".\n";
   }
}