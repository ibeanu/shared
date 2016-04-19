package com.kedut.prime;

import org.junit.Test;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Module: gs-serving-web-content
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 18/04/2016.
 **/
public class PrimeCalculatorServiceTest {

    @Test
    public void testRetrievePrimeNumbers() throws Exception {
        PrimeCalculator primeCalculator = new PrimeCalculatorService();

        List<Integer> primeNumbers = primeCalculator.retrievePrimeNumbers(10);
        assertTrue(primeNumbers.size() > 0);
        primeNumbers
                .stream()
                .forEach(n -> {
                    assertTrue(primeCalculator.isPrime(n));
                });
    }

    @Test
    public void testIsPrime() throws Exception {
        PrimeCalculator primeCalculator = new PrimeCalculatorService();

        assertTrue(primeCalculator.isPrime(2));
        assertTrue(primeCalculator.isPrime(3));
        assertFalse(primeCalculator.isPrime(4));
        assertTrue(primeCalculator.isPrime(5));
        assertFalse(primeCalculator.isPrime(6));
        assertTrue(primeCalculator.isPrime(7));
        assertFalse(primeCalculator.isPrime(8));
        assertFalse(primeCalculator.isPrime(9));
        assertFalse(primeCalculator.isPrime(10));
        assertTrue(primeCalculator.isPrime(11));
        assertFalse(primeCalculator.isPrime(15));
    }
}