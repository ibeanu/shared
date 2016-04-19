package com.kedut.prime;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
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
    ConcurrentHashMap<Integer, List<Integer>> cachedPrime = new ConcurrentHashMap<>();

    /**
     * retrieve prime numbers from the given limit
     *
     * @param limit
     * @return
     */
    @Override
    public List<Integer> retrievePrimeNumbers(int limit) {
        List<Integer> primesList = cachedPrime.get(limit);
        return primesList != null ? primesList : calculateNewPrime(limit);
    }

    /**
     * Calculate prime for given limit
     * @param limit
     * @return
     */
    private List<Integer> calculateNewPrime(int limit) {
        List<Integer> primes = IntStream
                .range(2, limit + 1)
                .boxed()
                .filter(this::isPrime)
                .collect(Collectors.toList());
        cachedPrime.put(limit, primes);
        return cachedPrime.get(limit);
    }

    /**
     * isPrime
     *
     * @param number
     * @return
     */
    @Override
    public boolean isPrime(int number) {
        assert number > 1;
        return IntStream.range(2, number + 1)
                .filter(i -> number % i == 0)
                .count() < 2;
    }

    /**
     * getPrimeFuture returns the future of the calculation.
     *
     * @param limit
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Future<?> getPrimeFuture(int limit)
            throws ExecutionException, InterruptedException {
        //TODO: Break up number if > than 100 and process individual sublist with paralell threads
        return Executors.newSingleThreadExecutor()
                .submit(() -> retrievePrimeNumbers(limit));
    }
}
