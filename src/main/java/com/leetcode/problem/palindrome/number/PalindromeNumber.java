package com.leetcode.problem.palindrome.number;

public class PalindromeNumber {

    public boolean isPalindrome(int num) {
        return num >= 0 && getReverseInt(num) == num;
    }

    /**
     * Reverse int number.
     * <p>
     * Initial idea was:
     * int tenThousand = num / 10000;
     * int thousand = (num % 10000) / 1000;
     * int hundred = (num % 1000) / 100;
     * int decimals = (num % 100) / 10;
     * int number = (num % 100) % 10;
     *
     * @param value int value.
     * @return {@link int}
     */
    private int getReverseInt(int value) {
        int resultNumber = 0;
        for (int i = value; i != 0; i /= 10) {
            resultNumber = resultNumber * 10 + i % 10;
        }

        return resultNumber;
    }
}
