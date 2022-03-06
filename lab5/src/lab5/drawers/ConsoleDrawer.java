package lab5.drawers;

import lab5.models.Area;
import lab5.models.Ball;
import lab5.models.Field;

public class ConsoleDrawer implements IGameDrawer{

    @Override
    public void PrintTable(Area area) {
        for (int i = 0; i < 4; i++){
            Field field = area.getField(i);
            i++;
            Field field2 = area.getField(i);
            for (int j = 0; j < 3; j++){
                for (int k = 0; k < 3; k++){
                    PrintBall(field.getBall(j,k));
                }
                for (int k = 0; k < 3; k++) {
                    PrintBall(field2.getBall(j, k));
                }
                System.out.println("\n");
            }
        }
    }

    @Override
    public void PrintBall(Ball ball) {
        if(ball != null){
            System.out.println(ball);
        }
        else{
            System.out.println("x");
        }
    }
}
