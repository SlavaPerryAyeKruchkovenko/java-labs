package com.operation;

public class sub extends operation {

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public double calculate(double num1, double num2) {
        return num1 - num2;
    }

    @Override
    public boolean equals(operation opp) {

        return opp.toString() == toString() && opp.getPriority() == getPriority();
    }
    @Override
    public String toString() {
        return "-";
    }
}
