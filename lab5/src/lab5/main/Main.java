package lab5.main;

import lab5.drawers.GameDrawerDef;
import lab5.drawers.GameDrawerWithFrame;
import lab5.drawers.IGameDrawer;
import lab5.models.Area;
import lab5.models.Ball;
import lab5.models.Field;
import lab5.players.Computer;
import lab5.players.Player;
import lab5.players.User;
import lab5.service.BallColor;
import lab5.service.Direction;
import lab5.service.Point;

import javax.swing.*;
import java.util.function.Function;

public class Main {
    public static void main(String[] args){
        Area area = new Area();
        Player player2 = new Computer();
        Player player1 = new User();
        IGameDrawer drawer = new GameDrawerWithFrame();
        while(true){

            drawer.printTable(area);
            Function<Area,Field> func1 = (area1) -> player1.selectField(area1);
            Field field = MakeCmd(area,drawer,"Введите номер подполя",func1);

            Function<Area,Point> func2 = (area1) -> player1.selectPosition(field);
            Point point = MakeCmd(area,drawer,"Введите позицию через ; например 1;1",func2);

            Function<Area,Ball> func3 = (area1) -> player1.selectBall();
            Ball ball = MakeCmd(area,drawer,"Введите цвет",func3);

            field.addBall(point.x, point.y,ball);

            while(true){
                try{
                    Function<Area,Direction> func4 = (area1) -> player1.selectDirection();
                    Direction direction = MakeCmd(area,drawer,"Введите направление",func4);
                    field.rotateField(direction);
                    break;
                }
                catch (Exception ex){
                    drawer.printMessage(ex.getMessage());
                }
            }

            Field field2 = player2.selectField(area);
            point = player2.selectPosition(field2);
            ball = player2.selectBall();
            field.addBall(point.x, point.y,ball);

            while(true){
                try{
                    Direction direction = player2.selectDirection();
                    field.rotateField(direction);
                    break;
                }
                catch (Exception ex){
                    drawer.printMessage(ex.getMessage());
                }
            }
        }

    }
    private static<T> T MakeCmd(Area area, IGameDrawer drawer, String message, Function<Area,T> func){
        while(true){
            drawer.printMessage(message);
            try{
                T result = func.apply(area);
                return result;
            }
            catch (Exception ex){
                drawer.printMessage(ex.getMessage());
            }
        }
    }
}
