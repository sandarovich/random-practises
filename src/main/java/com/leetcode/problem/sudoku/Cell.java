package com.leetcode.problem.sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Cell implements Completable, Initializable, Printable {

    public static final Character EMPTY_CELL = '.';

    private final int x;
    private final int y;
    private final boolean initialized;
    private Character value;
    private Set<Character> canditates = new HashSet<>();

    Cell(int x, int y, Character value) {
        this.x = x;
        this.y = y;
        this.value = value;
        this.initialized = isValidCell(value);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Character getValue() {
        return value;
    }

    public void setSolvedValue(Character value) {
        if (isSolved(value) && canditates.contains(value)) {
            this.value = value;
            clearCanditates();
        }
    }

    @Override
    public boolean isComplete() {
        return isSolved(this.value);
    }

    private boolean isSolved(Character value) {
        return value != EMPTY_CELL && Character.isDigit(value);
    }

    @Override
    public boolean isInitialized() {
        return initialized;
    }

    private boolean isValidCell(Character value) {
        return value == EMPTY_CELL || Character.isDigit(value);
    }

    @Override
    public void print() {
        System.out.print(this);
    }

    @Override
    public String toString() {
        return String.valueOf(" " + value + " ");
    }

    public void clearCanditates() {
        canditates.clear();
    }

    public Set<Character> getCanditates() {
        return canditates;
    }

    public void addCandidates(Character... characters) {
        canditates.addAll(Arrays.asList(characters));
    }

    public boolean isCandidateNotDefined() {
        return canditates == null || canditates.size() == 0;
    }
}
