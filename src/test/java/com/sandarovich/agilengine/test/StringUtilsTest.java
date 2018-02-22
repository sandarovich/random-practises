package com.sandarovich.agilengine.test;

import com.sandarovich.agilengine.test.StringUtils;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(DataProviderRunner.class)
public class StringUtilsTest {

    @Test
    @UseDataProvider("lastIndexOfData")
    public void shouldReturnLastIndexOf(String source, String searched, int expectedIndex) {
        assertThat(StringUtils.lastIndexOf(source, searched)).isEqualTo(expectedIndex);
    }

    @DataProvider
    public static Object[][] lastIndexOfData() {
        return new Object[][]{
                {"Hello", "world", -1},
                {null, "world", -1},
                {"Hworld", "world", 1},
                {"worldHworld", "world", 6},
                {"Hworld", "world", 1},
                {"Hello", "world", -1},
                {"Hello", "", 5},
                {"Hello", null, -1},
                {"HelloWorld", "Boo", -1},
                {"HelloWorldHelloWorld", "World", 15},
                {"", "", 0},
                {"abababab", "abab", 4},
                {"aaaaaaa", "a", 6},
                {"ZZZ", "z", -1},
                {"abab", "ab", 2},
                {"abcdef", "a", 0},
                {"kabacgcbab", "ba", 7},
                {"ztttttttttz", "zt", 0},
                {"", "ba", -1},
                {"ddd", null, -1},
                {"a", "ab", -1},
                {"bababa", "ba", 4},
                {"bab", "b", 2},
                {"ZZZ", "ba", -1},
                {"ba", "ba", 0},
                {"baabaab", "baab", 3},
                {"baabaab", "aab", 4},
                {"b", "aab", -1},
                {null, "", -1},
                {null, null, -1},
                {"@c@ac@b@cabcb@@aa@@caa@c@@@cabbcaab@@bba@c@c@@@b@@b@@bb@aac@ca@c@@bcbaa@@ac@bab@@@c@@bbcbbbb@b@@@bb" +
                        "@baccc@abbac@a@c@aa@c@@b@@aa@", "aa@@", 69 }
        };
    }
}