package com.leetcode.problem.sudoku;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Board implements Completable, Initializable, Rowable, Columnable, Printable {

    static final int QUAD_SIZE = 3;
    private static final int BOARD_SIZE = QUAD_SIZE * QUAD_SIZE;

    private final List<Quad> quads;

    Board(List<Quad> quads) {
        this.quads = quads;
    }

    public static Board from(char[][] board) {

        List<Quad> quads = Stream.generate(Quad::new)
                .limit(BOARD_SIZE)
                .collect(toList());

        for (int y = 0; y < board.length; y++) {
            for (int x = 0; x < board[y].length; x++) {
                Quad quad = quads.get(detectQuadIndex(x, y));
                quad.addCell(new Cell(y, x, board[x][y]));
            }
        }
        return new Board(quads);
    }

    static int detectQuadIndex(int x, int y) {
        return (x / QUAD_SIZE) * QUAD_SIZE + y / QUAD_SIZE;
    }

    public char[][] toCharArray() {
        char[][] result = new char[BOARD_SIZE][BOARD_SIZE];
        IntStream.rangeClosed(0, BOARD_SIZE)
                .forEach(rowIndex -> this.getRow(rowIndex)
                        .forEach(cell -> result[cell.getY()][cell.getX()] = cell.getValue()));
        return result;
    }

    @Override
    public boolean isComplete() {
        return quads.stream().allMatch(Quad::isComplete);
    }

    @Override
    public boolean isInitialized() {
        return quads.stream().allMatch(Quad::isInitialized);
    }

    @Override
    public boolean isInColumn(int columnIndex, Character value) {
        return quads.stream().anyMatch(quad -> quad.isInColumn(columnIndex, value));
    }

    @Override
    public boolean isInRow(int rowIndex, Character value) {
        return quads.stream().anyMatch(quad -> quad.isInRow(rowIndex, value));
    }

    @Override
    public List<Cell> getRow(int rowIndex) {
        return quads.stream()
                .sequential()
                .map(quad -> quad.getRow(rowIndex))
                .flatMap(List::stream)
                .collect(toList());
    }

    public void solve() {
        while (!isComplete()) {
            quads.forEach(this::solverFor);
        }
    }

    void solverFor(Quad quad) {
        //Find and set up Candidates
        setCandidatesFor(quad);
        //Find perfect match list and set up them as Cell value
        quad.setPerfectMatchValues();
        //Clear all candidates in quad.
        quad.clearCandidates();
    }


    void setCandidatesFor(Quad quad) {
        Set<Character> valuesToSolve = quad.getValuesToSolve();
        List<Cell> cellsToSolve = quad.getCellsToSolve();
        cellsToSolve.stream()
                .filter(Cell::isCandidateNotDefined)
                .forEach(cell -> valuesToSolve.forEach(value -> {
                    if (isCandidate(cell, value)) {
                        cell.addCandidates(value);
                    }
                }));
    }

    private boolean isCandidate(Cell cell, Character ch) {
        return !isInRow(cell.getY(), ch) && !isInColumn(cell.getX(), ch);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        IntStream.range(0, quads.size())
                .forEach(i -> {
                            String row = getRow(i).stream()
                                    .map(Cell::toString)
                                    .collect(Collectors.joining());
                            result.append(row).append(System.lineSeparator());
                        }
                );
        return result.toString();
    }

    @Override
    public void print() {
        System.out.println(this);
    }


    public Quad getQuad(int quadIndex) {
        return quads.get(quadIndex);
    }
}
