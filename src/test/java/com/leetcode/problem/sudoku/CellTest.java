package com.leetcode.problem.sudoku;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CellTest {

    private Cell sut;


    @Before
    public void setUp() {
        sut = new Cell(1, 1, Cell.EMPTY_CELL);
    }

    @Test
    public void isComplete() {
        assertThat(sut.isComplete()).isFalse();
    }

    @Test
    public void isInitialized() {
        assertThat(sut.isInitialized()).isTrue();
    }

    @Test
    public void hasCoordinatesAndValues() {
        assertThat(sut).hasFieldOrPropertyWithValue("x", 1)
                .hasFieldOrPropertyWithValue("y", 1)
                .hasFieldOrPropertyWithValue("value", Cell.EMPTY_CELL);
    }

    @Test
    public void shouldNotInitWhenCorruptedValueProvided() {
        sut = new Cell(0, 0, 'a');

        assertThat(sut.isInitialized()).isFalse();
    }

    @Test
    public void shouldSetUpSolvedValue() {
        sut = new Cell(0, 0, '.');
        sut.addCandidates('1', '2');
        sut.setSolvedValue('1');


        assertThat(sut.isComplete()).isTrue();
        assertThat(sut.getValue()).isEqualTo('1');
        assertThat(sut.getCanditates()).hasSize(0);
    }

    @Test
    public void shouldNotSetUpSolvedValueWhenNotNumericChar() {
        sut = new Cell(1, 1, '.');
        sut.addCandidates('1', '2');
        sut.setSolvedValue('a');

        assertThat(sut.isComplete()).isFalse();
        assertThat(sut.getValue()).isEqualTo('.');
        assertThat(sut.getCanditates()).hasSize(2).contains('1', '2');
    }

    @Test
    public void shouldNotSetUpSolvedValueWhenNotListedInCanditates() {
        sut = new Cell(9, 9, '.');
        sut.addCandidates('4');
        sut.setSolvedValue('3');

        assertThat(sut.isComplete()).isFalse();
        assertThat(sut.getValue()).isEqualTo('.');
        assertThat(sut.getCanditates()).hasSize(1).contains('4');
    }
}