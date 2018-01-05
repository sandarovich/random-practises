package com.leetcode.problem.count.battleship;


public class BattleShipCounterImpl implements BattleShipCounter {

    private static final char BATTLE_SHIP_DECK = 'X';
    private static final char EMPTY_SLOT = '.';

    @Override
    public int countBattleships(char[][] board) {
        int maxY = board.length;
        if (maxY == 0) return 0;
        int maxX = board[0].length;

        int result = 0;

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                if (board[y][x] == EMPTY_SLOT) continue;
                if (y > 0 && board[y - 1][x] == BATTLE_SHIP_DECK) continue;
                if (x > 0 && board[y][x - 1] == BATTLE_SHIP_DECK) continue;
                result++;
            }
        }

        return result;
    }

}
