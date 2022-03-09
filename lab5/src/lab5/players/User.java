package lab5.players;

import lab5.drawers.IGameDrawer;
import lab5.models.Area;
import lab5.models.Ball;
import lab5.models.Field;
import lab5.service.BallColor;
import lab5.service.Direction;
import lab5.service.Point;

import java.util.Random;
import java.util.Scanner;

public class User extends Player {

    @Override
    Field selectField(Area area) {
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        return area.getField(index);
    }

    @Override
    Point selectPosition(Field field) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        String[] point = line.split(";");
        return new Point(Integer.parseInt(point[0]),Integer.parseInt(point[1]));
    }

    @Override
    Ball selectBall() {
        Scanner scanner = new Scanner(System.in);
        String color = scanner.nextLine();
        if(color == "black" || color == "b")
            return new Ball(BallColor.Black);
        else if(color == "white" || color == "w")
            return new Ball(BallColor.White);
        throw new RuntimeException("inccorect color");
    }

    @Override
    Direction selectDirection() {
        Scanner scanner = new Scanner(System.in);
        String color = scanner.nextLine();
        if(color == "left" || color == "left")
            return Direction.Left;
        else if(color == "white" || color == "w")
            return Direction.Right;
        throw new RuntimeException("inccorect direction");
    }
}
