package lab5.models;

import lab5.service.BallColor;

import java.util.Arrays;

public class Area {
    private final Field[] fields;
    public final int FieldCount = 4;
    public Area() {
        this.fields = new Field[]{new Field(), new Field(), new Field(), new Field()};
    }

    public Field getField(int index) {
        if (index >= 0 && index < 4)
            return this.fields[index];
        else
            throw new IndexOutOfBoundsException("incorrect index of Field");
    }

    public boolean isWhiteWin(){return isSideWin(BallColor.White);}
    public boolean isBlackWin(){return isSideWin(BallColor.Black);}
    public boolean isDraw(){return Arrays.stream(fields).allMatch(Field::isWhole);}

    private boolean isSideWin(BallColor color){
        return  isDiagonalWin(color,new Field[]{fields[0],fields[3]},0) ||
                isDiagonalWin(color,new Field[]{fields[1],fields[2]},fields[1].size.width-1) ||
                checkVertical(color) || checkHorizontal(color);
    }
    private boolean isDiagonalWin(BallColor color,Field[] fields,int offset){
        for(Field field:fields){
            for(int i = 0; i < 3; i++){
                Ball ball = field.getBall(i,Math.abs(offset-i));
                if(ball == null || ball.state != color){
                    return false;
                }
            }
        }
        return true;
    }
    private boolean checkVertical(BallColor color){
        for (int i = 0; i < 3; i++){
            if(isLineWin(color,new Field[]{fields[0],fields[2]},i,true))
                return true;
            if(isLineWin(color,new Field[]{fields[1],fields[3]},i,true))
                return true;
        }
        return false;
    }
    private boolean checkHorizontal(BallColor color){
        for (int i = 0; i < 3; i++){
            if(isLineWin(color,new Field[]{fields[0],fields[1]},i,false))
                return true;
            if(isLineWin(color,new Field[]{fields[2],fields[3]},i,false))
                return true;
        }
        return false;
    }
    private boolean isLineWin(BallColor color,Field[] fields,int index,boolean isVertical){
        for(Field field:fields){
            for(int i = 0; i < 3; i++){
                Ball ball = isVertical?field.getBall(i,index):field.getBall(index,i);
                if(ball == null || ball.state != color){
                    return false;
                }
            }
        }
        return true;
    }


}
