package com.leetcode.problem.sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static com.leetcode.problem.sudoku.Board.QUAD_SIZE;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;


public class Quad implements Completable, Initializable, Rowable, Columnable, Printable {

    private List<Cell> cells;

    Quad(List<Cell> cells) {
        this.cells = cells;
    }

    Quad() {
        this.cells = new ArrayList<>(QUAD_SIZE * QUAD_SIZE);
    }

    public void addCell(Cell cell) {
        cells.add(cell);
    }


    @Override
    public boolean isComplete() {
        return cells.stream().allMatch(Cell::isComplete);
    }

    @Override
    public boolean isInitialized() {
        return cells.stream().allMatch(Cell::isInitialized);
    }


    @Override
    public boolean isInColumn(int columnIndex, Character value) {
        return cells.stream()
                .anyMatch(c -> c.getX() == columnIndex && c.getValue() == value);
    }

    @Override
    public boolean isInRow(int rowIndex, Character value) {
        return getRow(rowIndex).stream().anyMatch(c -> c.getValue() == value);
    }

    @Override
    public List<Cell> getRow(int rowIndex) {
        return cells.stream().sequential()
                .filter(cell -> cell.getY() == rowIndex)
                .collect(toList());
    }

    public List<Cell> getColumn(int columnIndex) {
        return cells.stream().sequential()
                .filter(cell -> cell.getX() == columnIndex)
                .collect(toList());
    }

    @Override
    public void print() {
        Cell[][] temp = new Cell[QUAD_SIZE][QUAD_SIZE];
        for (int i = 0; i < QUAD_SIZE * QUAD_SIZE; i++) {
            temp[i / QUAD_SIZE][i % QUAD_SIZE] = cells.get(i);
        }
        for (int i = 0; i < QUAD_SIZE; i++) {
            for (int j = 0; j < QUAD_SIZE; j++) {
                temp[j][i].print();
            }
            System.out.print("\n");
        }
    }

    Set<Character> getValuesToSolve() {
        Set<Character> result = getFullQuadValues();
        result.removeAll(cells.stream()
                .map(Cell::getValue)
                .collect(toSet())
        );
        return result;
    }

    private Set<Character> getFullQuadValues() {
        return IntStream.range(1, QUAD_SIZE * QUAD_SIZE + 1)
                .mapToObj(i -> Character.forDigit(i, 10))
                .collect(toSet());
    }


    List<Cell> getCellsToSolve() {
        return cells.stream()
                .filter(cell -> !cell.isComplete())
                .collect(toList());
    }

    public Cell getCell(int x, int y) {
        return cells.stream()
                .sequential()
                .filter(c -> c.getX() == x && c.getY() == y)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Cell with x =" + x + " and y = " + y + " can not be found!"));
    }

    @Deprecated
    public Set<Character> getRepeatableCandidates() {
        Set<Character> charactersToSolve = getValuesToSolve();
        getCellsToSolve().stream()
                .map(Cell::getCanditates)
                .forEach(charactersToSolve::retainAll);
        return charactersToSolve;
    }

    public void clearCandidates() {
        cells.forEach(Cell::clearCanditates);
    }

    public void setPerfectMatchValues() {
        List<Cell> cellsToSolve = this.getCellsToSolve();
        List<Character> perfectMatchValues = findPerfectMatchValues(cellsToSolve);
        for (Character perfectMatchValue : perfectMatchValues) {
            cellsToSolve.forEach(cell -> {
                if (cell.getCanditates().contains(perfectMatchValue)) {
                    cell.setSolvedValue(perfectMatchValue);
                }
            });
        }
    }

    private List<Character> findPerfectMatchValues(List<Cell> cellsToSolve) {
        List<Character> result = new ArrayList<>();
        for (Character character : getValuesToSolve()) {
            Long count = cellsToSolve.stream()
                    .map(Cell::getCanditates)
                    .filter(set -> set.contains(character))
                    .count();
            if (count == 1) {
                result.add(character);
            }
        }
        return result;
    }
}
