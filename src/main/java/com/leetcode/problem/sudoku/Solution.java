package com.leetcode.problem.sudoku;

public class Solution {

    public void solveSudoku(char[][] board) {
        Solution solution = new Solution();
        Board tempBoard = Board.from(board);
        solution.solve(tempBoard);
        char[][] solved = tempBoard.toCharArray();
        System.arraycopy(solved, 0, board, 0, solved.length);

    }


    void solve(Board board) {
        board.print();
        while (!board.isComplete()) {
            board.solve();
        }
        board.print();
    }

}
