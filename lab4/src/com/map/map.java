package com.map;


import javax.xml.bind.Element;
import java.awt.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.LinkedList;

public class map {
    private element[][] map;
    public String[][] findPath(String[][] visualMap){
        try{
            this.map = cnvrtToMap(visualMap);
            Point start = getStart(map);
            LinkedList<Point> list = findPath(start,new ArrayList<>());
            list.removeLast();
            for(Point point: list){
                visualMap[point.x][point.y] = new way().toString();
            }
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return visualMap;
    }
    private Point getStart(element[][] map) throws Exception {
        for (int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++){
                if(map[i][j].equals(new start())){
                    return new Point(i,j);
                }
            }
        }
        throw new IllegalArgumentException("you are map doesnt have start");
    }
    private LinkedList<Point> findPath(Point point, ArrayList<Point> visited) throws Exception {
        LinkedList<Point> list = new LinkedList<>();
        visited.add(point);

        if(point.x < 0 || point.y >= map.length || point.x < 0 || point.y >= map[0].length){
            throw new ArrayIndexOutOfBoundsException("incorrect index");
        }
        element el = map[point.x][point.y];
        if(el.equals(new wall())){
            throw new IllegalArgumentException("you crashed");
        }
        if(el.equals(new finish())){
            list.add(point);
            return list;
        }
        if(!visited.contains(new Point(point.x-1,point.y))){
            list.addAll(findSubPath(point.x-1, point.y, visited));
        }
        if(!visited.contains(new Point(point.x+1,point.y))){
            list.addAll(findSubPath(point.x+1, point.y, visited));
        }
        if(!visited.contains(new Point(point.x,point.y-1))){
            list.addAll(findSubPath(point.x, point.y-1, visited));
        }
        if(!visited.contains(new Point(point.x,point.y+1))){
            list.addAll(findSubPath(point.x, point.y+1, visited));
        }
        return list;
    }
    private LinkedList<Point> findSubPath(int index1,int index2,ArrayList<Point> visited){
        try{
            Point point = new Point(index1,index2);
            LinkedList<Point> list = findPath(point,visited);
            if(!list.isEmpty()){
                list.addFirst(point);
            }
            return list;
        }catch(Exception ex){
            return new LinkedList<>();
        }
    }
    private static element[][] cnvrtToMap(String[][] arrs) throws Exception {
        Point size = new Point(arrs.length,arrs[0].length);
        element[][] _map = new element[size.x][size.y];
        for (int i = 0; i < size.x; i++){
            for (int j = 0; j < size.x; j++){
                _map[i][j] = element.getElement(arrs[i][j]);
            }
        }
        return _map;
    }


}
