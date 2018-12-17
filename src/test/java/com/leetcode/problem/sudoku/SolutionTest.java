package com.leetcode.problem.sudoku;

import org.junit.Before;
import org.junit.Test;

public class SolutionTest {

    private Solution sut;

    @Before
    public void setUp() {
        sut = new Solution();
    }

    @Test
    public void solve() {
        sut.solve(Board.from(BoardTest.getInitialBoard()));
    }

}