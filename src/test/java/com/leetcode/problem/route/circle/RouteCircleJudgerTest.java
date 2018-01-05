package com.leetcode.problem.route.circle;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(DataProviderRunner.class)
public class RouteCircleJudgerTest {

    private RouteCircleJudger sut;

    @Before
    public void setUp() {
        sut = new RouteCircleJudgerImpl();
    }

    @Test
    @UseDataProvider("roboMovesData")
    public void shouldJudgeRouteCircle(String moves, boolean isCircle) {
        assertThat(sut.isCircleMove(moves)).isEqualTo(isCircle);
    }

    @DataProvider
    public static Object[][] roboMovesData() {
        return new Object[][] {
                {"UD", true},
                {"LL", false},
                {"UDLL", false},
                {"UULR", false},
                {"LR", true}
        };
    }
}
