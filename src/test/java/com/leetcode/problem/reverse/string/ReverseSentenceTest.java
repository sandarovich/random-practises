package com.leetcode.problem.reverse.string;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class ReverseSentenceTest {

    private ReverseSentence sut;

    @Before
    public void setUp() {
        sut = new ReverseSentenceImpl();
    }

    @Test
    @UseDataProvider("sentencesData")
    public void shouldReverseSentence(String source, String expectedResult) {
        assertThat(sut.reverse(source)).isEqualTo(expectedResult);
    }

    @DataProvider
    public static Object[][] sentencesData() {
        return new Object[][] {
                {"", ""},
                {"ab", "ba"},
                {"Let's take LeetCode contest", "s'teL ekat edoCteeL tsetnoc"}
        };
    }
}
