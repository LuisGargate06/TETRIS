package logica;

public class Puntaje {
   private int puntos;
   private int totalLineas;
   //El constructor inicializa todo en 0, pues recien esta iniciando el juego
   public Puntaje(){
      this.puntos = 0;
      this.totalLineas = 0;
   }
   //Escribimos los getters
   public int getPuntos(){return puntos;}
   public int getTotalLineas(){return totalLineas;}
   public void sumarLineas(int lineas){
      if (lineas > 0){
         this.totalLineas += lineas;
         if(lineas == 1){this.puntos += 100;}
         else {
            this.puntos += lineas*100 + 50;
         }
      }
   }
   //Cuando se reinicia el juego
   public void reiniciar(){
      this.puntos = 0;
      this.totalLineas = 0;
   }
   //Devuelve los datos acumulados en ese momento
   //Escribimo @Override para saber si esta bien como sobreescribimos el metodo
   @Override
   public String toString(){
      return "\nLograste un total de "+ getPuntos() + " puntos y eliminaste un total de "+ getTotalLineas()+ " lineas.\n";
   }
}