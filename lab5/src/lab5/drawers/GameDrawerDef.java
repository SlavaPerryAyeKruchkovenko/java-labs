package lab5.drawers;

import lab5.models.Area;
import lab5.models.Ball;
import lab5.models.Field;
import lab5.service.BallColor;

public class GameDrawerDef implements IGameDrawer {
    @Override
    public void PrintTable(Area area) {
        for (int i = 0; i < area.FieldCount; i++) {
            Field field = area.getField(i);
            i++;
            Field field2 = area.getField(i);
            for (int j = 0; j < 3; j++) {
                printLineOfField(field,j);
                printLineOfField(field2,j);
                System.out.print("\n");
            }
        }
    }
    private void printLineOfField(Field field,int j){
        for (int k = 0; k < field.size.width; k++) {
            PrintBall(field.getBall(j, k));
        }
    }
    @Override
    public void PrintBall(Ball ball) {
        if(ball != null) {
            if(ball.state == BallColor.White)
                System.out.print("x");
            else
                System.out.print("o");
        }
        else{
            System.out.print("*");
        }
    }
}
