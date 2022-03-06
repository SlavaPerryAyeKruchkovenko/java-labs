package lab5.main;

import lab5.drawers.GameDrawerDef;
import lab5.drawers.GameDrawerWithFrame;
import lab5.drawers.IGameDrawer;
import lab5.models.Area;
import lab5.models.Field;
import lab5.service.BallColor;

public class Main {
    public static void main(String[] args) {
        Area area = new Area();
        IGameDrawer drawer = new GameDrawerWithFrame();
        IGameDrawer drawer2 = new GameDrawerDef();
        Field field = area.getField(0);
        field.addBall(0, 0, BallColor.Black);
        drawer.PrintTable(area);
        drawer2.PrintTable(area);
    }
}
