package lab5.drawers;

import lab5.models.Area;
import lab5.models.Ball;

public interface IGameDrawer {
    void PrintTable(Area area);

    void PrintBall(Ball ball);
}
