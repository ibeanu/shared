package com.kedut.prime;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Module: prime-calculator
 * IBCTEC LTD
 * AUthor: ikenna1
 **/
@Component
public class PrimeCalculatorService implements PrimeCalculator {
    /**
     * retrieve prime numbers from the given limit
     * @param limit
     * @return
     */
    @Override
    public List<Integer> retrievePrimeNumbers(int limit) {
        return IntStream
                .range(2, limit+1)
                .boxed()
                .filter(this::isPrime)
                .collect(Collectors.toList());
    }

    /**
     * isPrime
     * @param number
     * @return
     */
    @Override
    public boolean isPrime(int number) {
        assert number > 1;
        return IntStream.range(2, number+1)
                .filter(i -> number % i == 0)
                .count() < 2;
    }

    /**
     *getPrimeFuture returns the future of the calculation.
     * @param limit
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Future<?> getPrimeFuture( Integer limit)
            throws ExecutionException, InterruptedException{
        return Executors.newSingleThreadExecutor()
                .submit(() -> retrievePrimeNumbers(limit));
    }
}
