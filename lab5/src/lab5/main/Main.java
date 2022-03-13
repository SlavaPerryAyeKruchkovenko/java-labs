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
        try{
            while(!area.isDraw()){
                humanMakeSteep(drawer,area,player1);
                computerMakeSteep(drawer,area,player2);
            }
            System.out.println("draw");
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    private static void humanMakeSteep(IGameDrawer drawer,Area area,Player player) throws Exception {
        Field field;
        Point point;
        Ball ball;
        while(true){
            try{
                drawer.printTable(area);
                Function<Area,Field> func1 = player::selectField;
                field = MakeCmd(area,drawer,"Введите номер подполя",func1);

                Field finalField = field;
                Function<Area,Point> func2 = (area1) -> player.selectPosition(finalField);
                point = MakeCmd(area,drawer,"Введите позицию через ; например 1;1",func2);

                ball = player.selectBall();

                field.addBall(point.x, point.y,ball);

                break;
            }catch (Exception ex){
                drawer.printMessage(ex.getMessage());
            }
        }
        checkOnWin(area);
        while(true){
            try{
                Function<Area,Direction> func4 = (area1) -> player.selectDirection();
                Direction direction = MakeCmd(area,drawer,"Введите направление",func4);
                field.rotateField(direction);
                break;
            }
            catch (Exception ex){
                drawer.printMessage(ex.getMessage());
            }
        }
        checkOnWin(area);
    }
    private static void checkOnWin(Area area) throws Exception {
        if(area.isWhiteWin()){
            throw new Exception("White is win");
        }
        if(area.isBlackWin()){
            throw new Exception("Black is win");
        }
    }
    private static void computerMakeSteep(IGameDrawer drawer,Area area,Player player) throws Exception {
        Field field;
        Point point;
        Ball ball;
        while(true){
            try{
                field = player.selectField(area);
                point = player.selectPosition(field);
                ball = player.selectBall();
                field.addBall(point.x, point.y,ball);
                break;
            }
            catch (Exception ex){}
        }
        checkOnWin(area);
        while(true){
            try{
                Direction direction = player.selectDirection();
                field.rotateField(direction);
                break;
            }
            catch (Exception ex){
                drawer.printMessage(ex.getMessage());
            }
        }
        checkOnWin(area);
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
