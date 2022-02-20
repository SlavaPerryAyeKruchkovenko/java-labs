package com.company;

public class Div extends Operation {

    @Override
    public String getName() {
        return "div";
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public double calculate(double num1, double num2) {
        return num1 / num2;
    }

    @Override
    public boolean equals(Operation opp) {

        return opp.getName() == getName() && opp.getPriority() == getPriority();
    }
}
