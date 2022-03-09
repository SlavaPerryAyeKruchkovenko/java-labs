package lab5.players;

import lab5.drawers.IGameDrawer;
import lab5.models.Area;
import lab5.models.Ball;
import lab5.models.Field;
import lab5.service.BallColor;
import lab5.service.Direction;
import lab5.service.Point;

import java.util.Random;

public class Computer extends Player {

    @Override
    public Field selectField(Area area) {
        int counter = 0;
        while(true){
            counter++;
            Random rnd = new Random();
            int index = rnd.nextInt(4);
            Field field = area.getField(index);
            if(counter == 100){
                throw new RuntimeException("fields are whole");
            }
            if(!field.isWhole()){
                return field;
            }
        }
    }

    @Override
    public Point selectPosition(Field field) {
        for (int i = 0; i<3; i++)
            for (int j = 0; j<3; j++)
                if(field.getBall(i,j)==null)
                    return new Point(i,j);

        throw new RuntimeException("Field is whole");
    }

    @Override
    public Ball selectBall() {
        Random rnd = new Random();
        int index = rnd.nextInt(2);
        if(index == 0)
            return new Ball(BallColor.White);
        else
            return new Ball(BallColor.Black);
    }

    @Override
    public Direction selectDirection() {
        Random rnd = new Random();
        int index = rnd.nextInt(2);
        if(index == 0)
            return Direction.Left;
        else
            return Direction.Right;
    }
}
