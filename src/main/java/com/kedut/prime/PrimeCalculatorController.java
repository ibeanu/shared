package com.kedut.prime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@Controller
public class PrimeCalculatorController {

    @Autowired
    PrimeCalculator calculator;

    @RequestMapping("/primes/list")
    public String retrievePrimes(@RequestParam(value = "limit",
            required = false, defaultValue = "limit")
                                 Integer limit, Model model)
            throws ExecutionException, InterruptedException {

        if (limit <= 10000 ) {
            Future<?> primeFuture = calculator.getPrimeFuture(limit);
            while (!primeFuture.isDone()) {
            }
            model.addAttribute("limit", limit);
            model.addAttribute("primes", primeFuture.get());
            model.addAttribute("errorMsg", "");
        } else {
            model.addAttribute("limit", limit);
            model.addAttribute("errorMsg", "Limit is too high");
        }
        return "prime";
    }
}
