package lab5.players;

import lab5.drawers.IGameDrawer;
import lab5.models.*;
import lab5.service.Direction;
import lab5.service.Point;

public abstract class Player {
    abstract Field selectField(Area area);
    abstract Point selectPosition(Field field);
    abstract Ball selectBall();
    abstract Direction selectDirection();
}
