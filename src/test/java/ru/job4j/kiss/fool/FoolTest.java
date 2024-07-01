package ru.job4j.kiss.fool;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FoolTest {

    @Test
    void whenNumberIsMultipleOf3And5_thenReturnFizzBuzz() {
        assertEquals("FizzBuzz", Fool.getExpectedAnswer(15));
        assertEquals("FizzBuzz", Fool.getExpectedAnswer(30));
    }

    @Test
    void whenNumberIsMultipleOf3_thenReturnFizz() {
        assertEquals("Fizz", Fool.getExpectedAnswer(3));
        assertEquals("Fizz", Fool.getExpectedAnswer(6));
        assertEquals("Fizz", Fool.getExpectedAnswer(9));
    }

    @Test
    void whenNumberIsMultipleOf5_thenReturnBuzz() {
        assertEquals("Buzz", Fool.getExpectedAnswer(5));
        assertEquals("Buzz", Fool.getExpectedAnswer(10));
        assertEquals("Buzz", Fool.getExpectedAnswer(20));
    }

    @Test
    void whenNumberIsNotMultipleOf3Or5_thenReturnNumber() {
        assertEquals("1", Fool.getExpectedAnswer(1));
        assertEquals("2", Fool.getExpectedAnswer(2));
        assertEquals("4", Fool.getExpectedAnswer(4));
    }
}