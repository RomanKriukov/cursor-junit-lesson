package com.kriukov.cursor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.StringReader;

public class CalculatorCliTest {

    private Calculator calculator;
    private CalculatorCli calculatorCli;

    @BeforeEach
    public void setup(){
        calculator = Mockito.mock(Calculator.class);
        calculatorCli = new CalculatorCli(calculator);
    }

    @Test
    public void calculatorCli_shouldSkipEmptyLines(){
        calculatorCli.runInteractiveSession(new StringReader(";;;   ;;;   \t\n;;;"));
        Mockito.verify(calculator, Mockito.never());
    }

    @Test
    public void calculatorCli_shouldCalculateEachExpression(){
        Mockito.when(calculator.calculate("1")).thenReturn(1.0);
        Mockito.when(calculator.calculate("2")).thenReturn(2.0);
        Mockito.when(calculator.calculate("3")).thenReturn(3.0);

        calculatorCli.runInteractiveSession(new StringReader("1;2;3"));
        Mockito.verify(calculator).calculate("1");
        Mockito.verify(calculator).calculate("2");
        Mockito.verify(calculator).calculate("3");
        Mockito.verifyNoMoreInteractions(calculator);
    }
}
