package lab5.players;

import lab5.models.Area;
import lab5.models.Ball;
import lab5.models.Field;
import lab5.service.BallColor;
import lab5.service.Direction;
import lab5.service.Point;

import java.util.Locale;
import java.util.Scanner;

public class User extends Player {

    @Override
    public Field selectField(Area area) {
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        return area.getField(index);
    }

    @Override
    public Point selectPosition(Field field) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] point = line.split(";");
        return new Point(Integer.parseInt(point[0]),Integer.parseInt(point[1]));
    }

    @Override
    public Ball selectBall() {
        Scanner scanner = new Scanner(System.in);
        String color = scanner.nextLine().toLowerCase(Locale.ROOT).trim();
        if(color.equals("black") || color.equals("b"))
            return new Ball(BallColor.Black);
        else if(color.equals("white") || color.equals("w"))
            return new Ball(BallColor.White);
        throw new RuntimeException("inccorect color");
    }

    @Override
    public Direction selectDirection() {
        Scanner scanner = new Scanner(System.in);
        String color = scanner.nextLine();
        if(color.equals("left") || color.equals("l"))
            return Direction.Left;
        else if(color.equals("right") || color.equals("r"))
            return Direction.Right;
        throw new RuntimeException("inccorect direction");
    }
}
