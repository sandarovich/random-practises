package com.leetcode.problem.count.battleship;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(DataProviderRunner.class)
public class BattleShipCounterTest {

    private BattleShipCounter sut;

    @Before
    public void setUp() {
        sut = new BattleShipCounterImpl();
    }

    @Test
    @UseDataProvider("battleShipBoardData")
    public void shouldCountBattleships(char[][] board, int battleShips) {
        assertThat(sut.countBattleships(board)).isEqualTo(battleShips);
    }

    @DataProvider
    public static Object[][] battleShipBoardData() {
        return new Object[][]{
                {
                        new char[][]{
                                {'X', '.', '.', 'X'},
                                {'.', '.', '.', 'X'},
                                {'.', '.', '.', 'X'},
                                {'.', '.', '.', 'X'}
                        }, 2
                },
                {
                        new char[][]{
                                {'.', '.', '.', '.'},
                                {'.', '.', '.', '.'},
                                {'.', '.', '.', '.'},
                                {'.', '.', '.', '.'}
                        }, 0
                },
                {
                        new char[][]{
                                {'X', 'X', '.', '.'},
                                {'.', '.', '.', '.'},
                                {'X', '.', 'X', 'X'},
                                {'.', '.', '.', '.'}
                        }, 3
                }
        };
    }
}
