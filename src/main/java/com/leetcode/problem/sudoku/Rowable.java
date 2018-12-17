package com.leetcode.problem.sudoku;

import java.util.List;

public interface Rowable {
    boolean isInRow(int rowIndex, Character value);

    List<Cell> getRow(int rowIndex);
}
