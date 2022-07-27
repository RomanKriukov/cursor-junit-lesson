package com.kriukov.cursor;

import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

public class CalculatorImplTest {

    private Calculator calculator;

    @BeforeAll
    public static void setupClass(){
        System.out.println("BEFORE ALL");
    }

    @BeforeEach
    public void setupTest(){
        calculator = new CalculatorImpl();
        System.out.println("BEFORE EACH");
    }

    @AfterEach
    public void after(){
        System.out.println("AFTER EACH");
    }

    @AfterAll
    public static void afterClass(){
        System.out.println("AFTER ALL");
    }

    @Test
    public void givenInputStringWithNumberWhenCalculateThenReturnParsedNumber(){
        // Given

        // When
        double result = calculator.calculate("3");

        //Then
        Assertions.assertEquals(3.0, result, 1e-9);
    }

    @Test
    public void givenInputDecimalWhenCalculateThenReturnParsedNumber(){
        // Given

        // When
        double result = calculator.calculate("1.5");

        //Then
        Assertions.assertEquals(1.5, result, 1e-9);
    }

    @Test
    public void givenAddingExpressionWhenCalculateThenReturnCalculatedNumber(){
        // Given

        // When
        double result = calculator.calculate("2+2");

        //Then
        Assertions.assertEquals(4.0, result, 1e-9);
    }

    @Test
    public void givenComplexExpressionWhenCalculateThenReturnCalculatedNumber(){
        // Given

        // When
        double result = calculator.calculate("(2+2)*1.5/10-444");

        //Then
        Assertions.assertEquals(-443.4, result, 1e-9);
    }

    @Test
    public void givenFunctionExpressionWhenCalculateThenReturnFunctionResult(){
        // Given

        // When
        double result = calculator.calculate("sin(1)*sin(1) + cos(1)*cos(1)");

        //Then
        Assertions.assertEquals(1.0, result, 1e-9);
    }

    @Test
    public void givenEmptyStringWhenCalculateThenThrowException(){
        Assertions.assertThrows(IllegalArgumentException.class, () -> calculator.calculate("invalid input"));
    }

    @Test
    public void givenEmptyStringWhenCalculateThenThrowExceptionWithMessage(){
        try{
            calculator.calculate("invalid input");
        }catch (Exception e){
            Assertions.assertTrue(e instanceof IllegalArgumentException);
            Assertions.assertEquals("Failed to calculate expression", e.getMessage());
        }
    }

    @Test
    @Timeout(value = 1000l, unit = TimeUnit.MILLISECONDS)
    public void givenExpressionWhenCalculateThenDoFasterThenSecond() throws InterruptedException {
        //Thread.sleep(2000l);
        calculator.calculate("2+2");

    }
}
