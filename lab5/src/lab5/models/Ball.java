package lab5.models;

import lab5.service.BallColor;

public class Ball {
    public final BallColor state;
    public Ball(BallColor state){
        this.state = state;
    }
    @Override
    public String toString() {
        if (state == BallColor.Black) return "B";
        else if (state == BallColor.White) return "W";
        else return " ";
    }
    @Override
    public boolean equals(Object obj){
        return obj instanceof Ball && ((Ball)obj).state == this.state;
    }
}
