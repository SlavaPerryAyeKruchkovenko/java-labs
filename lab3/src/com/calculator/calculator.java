package com.calculator;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class calculator {
    private static ScriptEngineManager manager = new ScriptEngineManager();
    private static ScriptEngine engine = manager.getEngineByName("js");

    public static double calc(String exp) throws ScriptException {
        Object result = engine.eval(exp);
        return new Double(result.toString());
    }
}
