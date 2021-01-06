package com.code.dojo.demo.leetcode412;

import java.util.ArrayList;
import java.util.List;
import com.google.common.annotations.VisibleForTesting;

public class FizzBuzzRF {
    public static final String FIZZ = "Fizz";
    public static final String BUZZ = "Buzz";

    public List<String> fizzBuzz(int number) {
        if (number <= 0 || number > 100) {
            throw new IllegalArgumentException("参数异常");
        }
        
        List<String> resutList = new ArrayList<>(number);
        for (int currentNumber = 1; currentNumber <= number; currentNumber++) {
            resutList.add(of(currentNumber));
        }
        return resutList;
    }


    @VisibleForTesting
    protected String of(int number) {

        StringBuilder sb = new StringBuilder();
        if (number % 3 == 0) {
            sb.append(FIZZ);
        }
        if (number % 5 == 0) {
            sb.append(BUZZ);
        }
        if (sb.length() == 0) {
            sb.append(number);
        }
        return sb.toString();

    }
}
