package com.kriukov.cursor;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class CalculatorImpl implements Calculator {

    @Override
    public double calculate(String expression) {

        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("javascript");
        try {
            defineMathFunctions(scriptEngine);
            return ((Number) scriptEngine.eval(expression)).doubleValue();
        }catch (ScriptException exception){
            throw new IllegalArgumentException("Failed to calculate expression");
        }
    }

    private static void defineMathFunctions(ScriptEngine scriptEngine) throws ScriptException{
        for(String function : new String[]{"sin", "cos", "sqrt"}){
            scriptEngine.eval("function " + function + "(x) { return Java.type('java.lang.Math')." + function + "(x)}");
        }
    }
}
