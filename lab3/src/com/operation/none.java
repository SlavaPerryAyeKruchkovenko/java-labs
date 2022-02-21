package com.operation;

public class none extends operation {
    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public double calculate(double num1, double num2) {
        return num1 * Math.pow(10, String.valueOf((int)num2).length()) + num2;
    }

    @Override
    public boolean equals(operation opp) {
        return opp.toString() == toString() && opp.getPriority() == getPriority();
    }
    @Override
    public String toString() {
        return "";
    }
}
