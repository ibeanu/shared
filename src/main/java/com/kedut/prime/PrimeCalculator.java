package com.kedut.prime;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Module: gs-serving-web-content
 * IBCTEC LTD
 * AUthor: ikenna1
 * DAte: 18/04/2016.
 **/
public interface PrimeCalculator {
    List<Integer> retrievePrimeNumbers(int limit);
    boolean isPrime(int number);
    Future<?> getPrimeFuture(Integer limit) throws ExecutionException, InterruptedException;
}
