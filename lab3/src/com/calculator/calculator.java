package com.calculator;

import net.objecthunter.exp4j.*;

public class calculator {

    public static double calc(String exp) {
        Expression e = new ExpressionBuilder(exp)
                .build();
        double result = e.evaluate();
        return result;
    }
}
