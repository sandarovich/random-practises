package com.leetcode.problem.sudoku;


import org.assertj.core.groups.Tuple;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class QuadTest {

    private Quad sut;

    /**
     * <pre>
     *   5  3  .
     *   6  .  .
     *   .  9  8
     * </pre>
     */
    static List<Cell> getSampleQuad() {
        return Arrays.asList(new Cell(0, 0, '5'),
                new Cell(0, 1, '6'),
                new Cell(0, 2, Cell.EMPTY_CELL),
                new Cell(1, 0, '3'),
                new Cell(1, 1, Cell.EMPTY_CELL),
                new Cell(1, 2, '9'),
                new Cell(2, 0, Cell.EMPTY_CELL),
                new Cell(2, 1, Cell.EMPTY_CELL),
                new Cell(2, 2, '8')
        );
    }

    @Before
    public void setUp() {
        sut = new Quad(Collections.singletonList(new Cell(1, 1, Cell.EMPTY_CELL)));
    }

    @Test
    public void isComplete() {
        assertThat(sut.isComplete()).isFalse();
    }

    @Test
    public void isInitialized() {
        assertThat(sut.isInitialized()).isTrue();
    }

    /**
     * <pre>
     *   5  3  .
     *   6  .  .
     *   .  9  8
     * </pre>
     */
    @Test
    public void isValueInColumn() {
        sut = new Quad(getSampleQuad());

        assertThat(sut.isInColumn(2, '8')).isTrue();
    }

    /**
     * <pre>
     *   5  3  .
     *   6  .  .
     *   .  9  8
     * </pre>
     */
    @Test
    public void isValueInRow() {
        sut = new Quad(getSampleQuad());

        assertThat(sut.isInRow(1, '6')).isTrue();
    }

    /**
     * <pre>
     *   5  3  .
     *   6  .  .
     *   .  9  8
     * </pre>
     */
    @Test
    public void shouldGetColumn() {
        sut = new Quad(getSampleQuad());

        assertThat(sut.getColumn(0)).hasSize(3)
                .extracting("value")
                .containsExactly('5', '6', '.');
    }

    @Test
    public void shouldReturnValuesToSolve() {
        sut = new Quad(getSampleQuad());

        assertThat(sut.getValuesToSolve()).hasSize(4)
                .contains('1', '2', '4', '7');
    }

    @Test
    public void shouldFindCellsToSolve() {
        sut = new Quad(getSampleQuad());

        assertThat(sut.getCellsToSolve())
                .hasSize(4)
                .extracting("x", "y")
                .contains(new Tuple(0, 2))
                .contains(new Tuple(1, 1))
                .contains(new Tuple(2, 1))
                .contains(new Tuple(2, 0));
    }

    @Test
    public void shouldGetCellByCoordinates() {
        sut = new Quad(getSampleQuad());

        assertThat(sut.getCell(2, 1))
                .hasFieldOrPropertyWithValue("x", 2)
                .hasFieldOrPropertyWithValue("y", 1)
                .hasFieldOrPropertyWithValue("value", Cell.EMPTY_CELL);
    }

    @Test
    public void shouldThrowIllegalArgumentsWhenGetCellByWrongCoordinates() {
        sut = new Quad(getSampleQuad());

        Throwable thrown = catchThrowable(() -> sut.getCell(10, 11));

        assertThat(thrown).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cell with x =10 and y = 11 can not be found!");
    }

    @Test
    public void shouldAddCell() {
        sut = new Quad();
        sut.addCell(new Cell(0, 0, '1'));

        assertThat(sut).extracting("cells").hasSize(1);
    }

    @Test
    public void shouldDetectRepeatableInAllCanditates() {
        sut = new Quad();
        Cell cell = new Cell(0, 0, '.');
        cell.addCandidates('1', '2', '4');

        Cell otherCell = new Cell(0, 1, '.');
        otherCell.addCandidates('1', '2');

        Cell otherCell2 = new Cell(0, 1, '.');
        otherCell2.addCandidates('1');

        sut.addCell(cell);
        sut.addCell(otherCell);
        sut.addCell(otherCell2);

        assertThat(sut.getRepeatableCandidates())
                .hasSize(1).contains('1');

    }

}