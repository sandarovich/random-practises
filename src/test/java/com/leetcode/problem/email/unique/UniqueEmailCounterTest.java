package com.leetcode.problem.email.unique;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class UniqueEmailCounterTest {

    private UniqueEmailCounter sut;

    @DataProvider
    public static Object[][] emailData() {
        return new Object[][]{
                {new String[]{}, 0},
                {null, 0},
                {new String[]{"test.email+alex@leetcode.com",
                        "test.e.mail+bob.cathy@leetcode.com",
                        "testemail+david@lee.tcode.com"}, 2},
        };
    }

    @Before
    public void setUp() {
        sut = new UniqueEmailCounter();
    }

    @Test
    @UseDataProvider("emailData")
    public void shouldReverseSentence(String[] emails, int expectedResult) {
        assertThat(sut.numUniqueEmails(emails)).isEqualTo(expectedResult);
    }
}
