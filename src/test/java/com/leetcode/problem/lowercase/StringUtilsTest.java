package com.leetcode.problem.lowercase;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class StringUtilsTest {

    @DataProvider
    public static Object[][] data() {
        return new Object[][]{
                {"", ""},
                {null, null},
                {"Hello", "hello"},
                {"here", "here"},
                {"LOVELY", "lovely"},

        };
    }

    @Test
    @UseDataProvider("data")
    public void shouldLowercase(String source, String expected) {
        assertThat(StringUtils.toLowerCase(source)).isEqualTo(expected);
    }

}
