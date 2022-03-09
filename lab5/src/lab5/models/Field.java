package lab5.models;

import com.sun.glass.ui.Size;
import lab5.service.BallColor;
import lab5.service.Direction;

public class Field {
    private final Ball[][] field = new Ball[3][3];
    private boolean isWhole = false;
    public final Size size = new Size(3,3);
    public Ball getBall(int index1, int index2) {
        if (index1 >= 0 && index1 < 3 && index2 >= 0 && index2 < 3)
            return this.field[index1][index2];
        else
            throw new IndexOutOfBoundsException("incorrect index of ball");
    }

    public void addBall(int index1, int index2, BallColor color) {
        addBall(index1,index2,new Ball(color));
    }

    public void addBall(int index1, int index2, Ball ball) {
        if (index1 < 3 && index1 >= 0 && index2 < 3 && index2 >= 0 && field[index1][index2] == null)
            this.field[index1][index2] = ball;
        else
            throw new IndexOutOfBoundsException("incorrect index");

        isWhole = true;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j<3; j++)
                if(this.field[i][j] == null)
                    isWhole = false;
    }
    public boolean isWhole(){
        return this.isWhole;
    }
    public void rotateField(Direction direction) throws Exception {
        int size = this.field.length;
        Ball[][] newBalls = new Ball[3][3];
        if (direction == Direction.Left) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    newBalls[i][j] = this.field[size - j - 1][i];
                }
            }
        } else if (direction == Direction.Right) {
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    newBalls[i][j] = this.field[j][size - i - 1];
                }
            }

        } else throw new Exception("incorrect direction");
        copyArrToArr(newBalls,this.field);
    }
    /**
     * copy value of array in next array if it's lenght is equal
     *
     * @param  arrToCopy  arr from which will be copy values
     * @param  arr arr to which will be copied values
     */
    private static<T> void copyArrToArr(T[][] arrToCopy,T[][] arr) throws Exception {
        if (arrToCopy.length == arr.length && arrToCopy[0].length == arr[0].length)
            for (int i = 0; i < arrToCopy.length; i++)
                for (int j = 0; j < arrToCopy[0].length; j++)
                    arr[i][j] = arrToCopy[i][j];
        else
            throw new Exception("incorrect arrs");
    }
}

