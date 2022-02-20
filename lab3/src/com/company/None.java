package com.company;

public class None extends Operation {
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
        return opp.toString() == toString() && opp.getPriority() == getPriority();
    }
    @Override
    public String toString() {
        return "";
    }
}
