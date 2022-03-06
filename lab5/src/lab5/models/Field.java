package lab5.models;

import lab5.service.BallColor;
import lab5.service.Direction;

public class Field {
    private Ball[][] field = new Ball[3][3];

    public Ball getBall(int index1,int index2) {
        if(index1 >= 0 && index1 < 3 && index2 >= 0 && index2 < 3)
            return this.field[index1][index2];
        else
            throw new IndexOutOfBoundsException("incorrect index of ball");
    }

    public void addBall(int index1, int index2, BallColor color){
       if(index1 < 3 && index2 >= 0 && index2 < 3 && index2 >= 0)
           this.field[index1][index2] = new Ball(color);
       else
           throw new IndexOutOfBoundsException("incorrect index");
    }

    public void addBall(int index1, int index2, Ball ball){
        if(index1 < 3 && index2 >= 0 && index2 < 3 && index2 >= 0)
            this.field[index1][index2] = ball;
        else
            throw new IndexOutOfBoundsException("incorrect index");
    }

    public void RotateField(Direction direction) throws Exception {
        int size = this.field.length;
        Ball[][] newBalls = new Ball[3][3];
        if(direction == Direction.Left){
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    newBalls[i][j] = newBalls[size - j - 1][i];
                }
            }
            this.field = newBalls;
        }
        else if(direction == Direction.Right){
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    newBalls[i][j] = newBalls[j][size - i - 1];
                }
            }
            this.field = newBalls;
        }
        else throw new Exception("incorrect direction");
    }
}
