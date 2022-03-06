package lab5.main;

import lab5.drawers.ConsoleDrawer;
import lab5.drawers.IGameDrawer;
import lab5.models.Area;
import lab5.models.Field;
import lab5.service.BallColor;

public class Main {
    public static void main(String[] args){
        Area area = new Area();
        IGameDrawer drawer = new ConsoleDrawer();
        Field field = area.getField(0);
        field.addBall(0,0, BallColor.Black);
        drawer.PrintTable(area);
    }
}
