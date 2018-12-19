package com.leetcode.problem.movingzeros;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class MovingZerosTest {

    private MovingZeros sut;

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {new int[]{}, new int[]{}},
                {null, null},
                {new int[]{0, 1, 0, 3, 12}, new int[]{1, 3, 12, 0, 0}},
                {new int[]{0, 1, 0, 3, 12, 0}, new int[]{1, 3, 12, 0, 0, 0}},
                {new int[]{0, 1, 0, 3, 12, 0}, new int[]{1, 3, 12, 0, 0, 0}},
                {new int[]{0, 0, 0, 0, 0, 1}, new int[]{1, 0, 0, 0, 0, 0}},
                {new int[]{0, 0, 0, 0, 0, 0}, new int[]{0, 0, 0, 0, 0, 0}},
                {new int[]{0, 0, 0, 1, 0, 0}, new int[]{1, 0, 0, 0, 0, 0}},
                {new int[]{0, 0, 0, 1, 0, 0}, new int[]{1, 0, 0, 0, 0, 0}},
                {new int[]{0, 1, 2}, new int[]{1, 2, 0}},
                {new int[]{1, 0}, new int[]{1, 0}},
                {new int[]{0, 1}, new int[]{1, 0}},
                {new int[]{2, 1}, new int[]{2, 1}},
                {new int[]{1, 0, 1}, new int[]{1, 1, 0}},
                {new int[]{4, 2, 4, 0, 0, 3, 0, 5, 1, 0}, new int[]{4, 2, 4, 3, 5, 1, 0, 0, 0, 0}}
        };
    }

    @Before
    public void setUp() {
        sut = new MovingZeros();
    }

    @Test
    @UseDataProvider("data")
    public void shouldReverseSentence(int[] nums, int[] expected) {
        assertThat(sut.moveZeroes(nums)).isEqualTo(expected);
    }
}
