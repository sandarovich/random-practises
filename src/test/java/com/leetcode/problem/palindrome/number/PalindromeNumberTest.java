package com.leetcode.problem.palindrome.number;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class PalindromeNumberTest {
    private PalindromeNumber sut;

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {1121, false},
                {121, true},
                {-121, false},
                {10, false},
        };
    }

    @Before
    public void setUp() {
        sut = new PalindromeNumber();
    }

    @Test
    @UseDataProvider("data")
    public void shouldReverseSentence(int num, boolean expected) {
        assertThat(sut.isPalindrome(num)).isEqualTo(expected);
    }
}
