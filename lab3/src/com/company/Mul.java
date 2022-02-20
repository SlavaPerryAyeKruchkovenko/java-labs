package com.company;

public class Mul extends Operation {
    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public double calculate(double num1, double num2) {
        return num1 * num2;
    }

    @Override
    public boolean equals(Operation opp) {
        return opp.toString() == toString() && opp.getPriority() == getPriority();
    }
    @Override
    public String toString() {
        return "*";
    }
}
