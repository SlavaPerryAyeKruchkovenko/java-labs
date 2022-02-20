package com.company;

public abstract class Operation {

    public abstract int getPriority();
    public abstract double calculate(double num1,double num2);
    public abstract boolean equals(Operation opp);
}
