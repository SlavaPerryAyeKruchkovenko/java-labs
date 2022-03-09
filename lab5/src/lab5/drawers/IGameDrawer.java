package lab5.drawers;

import lab5.models.Area;
import lab5.models.Ball;

public interface IGameDrawer {
    void printTable(Area area);

    void printBall(Ball ball);

    void printMessage(String message);
}
