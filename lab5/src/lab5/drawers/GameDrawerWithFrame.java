package lab5.drawers;

import lab5.models.Area;
import lab5.models.Ball;
import lab5.models.Field;

public class GameDrawerWithFrame implements IGameDrawer {

    @Override
    public void PrintTable(Area area) {
        System.out.println(getUpperLine(6*2+2));
        for (int i = 0; i < area.FieldCount; i++) {
            Field field = area.getField(i);
            i++;
            Field field2 = area.getField(i);
            for (int j = 0; j < 3; j++) {
                System.out.print("║");
                printLineOfField(field,j);
                System.out.print("║");
                printLineOfField(field2,j);
                System.out.print("║");
                System.out.print("\n");
            }
            if(i < area.FieldCount-2){
                System.out.println(getMiddleLine(14));
            }
        }
        System.out.println(getLowerLine(14));
    }
    private void printLineOfField(Field field,int j){
        for (int k = 0; k < field.size.width; k++) {
            PrintBall(field.getBall(j, k));
            if(k != field.size.width-1){
                System.out.print("|");
            }
        }
    }
    private String getUpperLine(int size) {
        return "╔" + getRepeatLine(5, "═") + "╦" +getRepeatLine(5, "═") +"╗" ;
    }

    private String getLowerLine(int size) {
        return "╚"+getRepeatLine(5, "═")+ "╩" + getRepeatLine(5, "═") + "╝";
    }
    private String getMiddleLine(int size){
        return "╠"+getRepeatLine(5, "═") + "╬" +getRepeatLine(5, "═")+ "╣";
    }
    private String getRepeatLine(int size, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++)
            stringBuilder.append(str);
        return stringBuilder.toString();
    }

    @Override
    public void PrintBall(Ball ball) {
        if (ball != null) {
            System.out.print(ball);
        } else {
            System.out.print(" ");
        }
    }
}
