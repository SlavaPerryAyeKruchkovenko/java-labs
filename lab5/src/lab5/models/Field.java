package lab5.models;

import lab5.service.BallColor;

public class Field {
   private final Ball[][] field = new Ball[3][3];
   public void addBall(int index1, int index2, BallColor color){
       if(index1 < 3 && index2 >= 0 || index2 < 3 && index2 >= 0)
           this.field[index1][index2] = new Ball(color);
       else
           throw new IndexOutOfBoundsException("inccorect index");
   }
}
