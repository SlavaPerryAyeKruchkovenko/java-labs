package lab5.players;

import lab5.drawers.IGameDrawer;
import lab5.models.*;
import lab5.service.Direction;
import lab5.service.Point;

public abstract class Player {
    public abstract Field selectField(Area area);
    public abstract Point selectPosition(Field field);
    public abstract Ball selectBall();
    public abstract Direction selectDirection();
}
