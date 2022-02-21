package com.operation;

public abstract class operation {

    public abstract int getPriority();
    public abstract double calculate(double num1,double num2);
    public abstract boolean equals(operation opp);
}
