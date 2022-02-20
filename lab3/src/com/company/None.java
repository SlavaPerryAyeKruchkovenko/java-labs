package com.company;

public class None extends Operation {

    @Override
    public String getName() {
        return "none";
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public double calculate(double num1, double num2) {
        return num1 * Math.pow(10, String.valueOf(num2).length()) + num2;
    }

    @Override
    public boolean equals(Operation opp) {
        return opp.getName() == getName() && opp.getPriority() == getPriority();
    }
}
