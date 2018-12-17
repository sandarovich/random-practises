package com.leetcode.problem.sudoku;


import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(DataProviderRunner.class)
public class BoardTest {

    private Board sut;

    @Mock
    private Quad quad;

    public static char[][] getInitialBoard() {
        return new char[][]{
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
    }

    public static char[][] getFilledBoard() {
        return new char[][]{
                {'5', '3', '.', '6', '7', '8', '9', '1', '2'},
                {'6', '7', '2', '1', '9', '5', '.', '4', '8'},
                {'1', '9', '8', '.', '4', '2', '5', '6', '7'},
                {'8', '5', '9', '7', '6', '1', '4', '2', '3'},
                {'4', '2', '6', '8', '5', '3', '.', '9', '1'},
                {'7', '.', '3', '9', '2', '4', '8', '5', '6'},
                {'9', '6', '1', '5', '3', '7', '2', '8', '4'},
                {'2', '8', '7', '4', '1', '9', '6', '.', '5'},
                {'3', '.', '5', '2', '8', '6', '1', '7', '9'}
        };
    }

    @DataProvider
    public static Object[][] quadDetectionData() {
        return new Object[][]{
                {0, 0, 0},
                {0, 1, 0},
                {0, 2, 0},
                {0, 3, 1},
                {0, 4, 1},
                {0, 5, 1},
                {0, 6, 2},
                {0, 7, 2},
                {0, 8, 2},
                {6, 6, 8},
                {7, 7, 8},
                {8, 8, 8},
        };
    }

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void shouldCheckIfIsCompleteWhenSquadIsComplete() {
        sut = new Board(Collections.singletonList(quad));
        when(quad.isComplete()).thenReturn(true);

        assertThat(sut.isComplete()).isTrue();
    }

    @Test
    public void shouldCheckIfIsNotCompleteWhenInitialBoard() {
        sut = Board.from(getInitialBoard());

        assertThat(sut.isComplete()).isFalse();
    }

    @Test
    public void shouldCheckIfIsCompleteSquadsIsNotComplete() {
        sut = new Board(Collections.singletonList(quad));
        when(quad.isComplete()).thenReturn(false);

        assertThat(sut.isComplete()).isFalse();
    }

    @Test
    public void shouldCheckIfBoardInitialized() {
        sut = Board.from(getInitialBoard());
        assertThat(sut.isInitialized()).isTrue();
    }

    @Test
    public void shouldDetectInRow() {
        sut = Board.from(getInitialBoard());

        assertThat(sut.isInRow(8, '9')).isTrue();
    }

    @Test
    public void shouldDetectInColumn() {
        sut = Board.from(getInitialBoard());

        assertThat(sut.isInColumn(6, '2')).isTrue();
    }

    @Test
    public void shouldDetectInRowAndColumn() {
        sut = Board.from(getInitialBoard());

        assertThat(sut.isInColumn(2, '2')).isFalse();
        assertThat(sut.isInRow(1, '2')).isFalse();
        assertThat(sut.isInColumn(0, '4')).isTrue();

    }

    @Test
    public void shouldReturnToString() {
        sut = Board.from(getInitialBoard());
        assertThat(sut.toString()).isEqualToIgnoringNewLines(
                " 5  3  .  .  7  .  .  .  . \n"
                        + " 6  .  .  1  9  5  .  .  . \n"
                        + " .  9  8  .  .  .  .  6  . \n"
                        + " 8  .  .  .  6  .  .  .  3 \n"
                        + " 4  .  .  8  .  3  .  .  1 \n"
                        + " 7  .  .  .  2  .  .  .  6 \n"
                        + " .  6  .  .  .  .  2  8  . \n"
                        + " .  .  .  4  1  9  .  .  5 \n"
                        + " .  .  .  .  8  .  .  7  9 \n");
    }

    @Test
    public void shouldReturnRow_0() {
        sut = Board.from(getInitialBoard());

        assertThat(sut.getRow(1))
                .hasSize(9)
                .extracting("value")
                .containsExactly('6', '.', '.', '1', '9', '5', '.', '.', '.');
    }

    @Test
    public void shouldConvertBoardToCharArray() {
        sut = Board.from(getInitialBoard());

        assertThat(sut.toCharArray())
                .isEqualTo(getInitialBoard());
    }

    @Test
    @UseDataProvider("quadDetectionData")
    public void shouldDetectQuadSize(int x, int y, int expectedQuadIndex) {
        assertThat(Board.detectQuadIndex(x, y)).isEqualTo(expectedQuadIndex);
    }

    @Test
    public void shouldSolveForQuad1() {
        sut = Board.from(getInitialBoard());
        quad = sut.getQuad(1);
        sut.solverFor(quad);
        assertThat(sut.toString()).isEqualToIgnoringNewLines(
                " 5  3  .  .  7  8  .  .  . \n"
                        + " 6  .  .  1  9  5  .  .  . \n"
                        + " .  9  8  .  .  .  .  6  . \n"
                        + " 8  .  .  .  6  .  .  .  3 \n"
                        + " 4  .  .  8  .  3  .  .  1 \n"
                        + " 7  .  .  .  2  .  .  .  6 \n"
                        + " .  6  .  .  .  .  2  8  . \n"
                        + " .  .  .  4  1  9  .  .  5 \n"
                        + " .  .  .  .  8  .  .  7  9 \n");
    }

    @Test
    public void shouldSolve2() {
        sut = Board.from(getFilledBoard());
        sut.solve();
        assertThat(sut.toString()).isEqualToIgnoringNewLines(
                " 5  3  4  6  7  8  9  1  2 \n"
                        + " 6  7  2  1  9  5  3  4  8 \n"
                        + " 1  9  8  3  4  2  5  6  7 \n"
                        + " 8  5  9  7  6  1  4  2  3 \n"
                        + " 4  2  6  8  5  3  7  9  1 \n"
                        + " 7  1  3  9  2  4  8  5  6 \n"
                        + " 9  6  1  5  3  7  2  8  4 \n"
                        + " 2  8  7  4  1  9  6  3  5 \n"
                        + " 3  4  5  2  8  6  1  7  9 \n");
    }

    @Test
    public void shouldSolve() {
        sut = Board.from(getInitialBoard());
        sut.print();

        Instant start = Instant.now();
        sut.solve();
        Instant end = Instant.now();

        System.out.println("Solved in: " + Duration.between(start, end).toMillis() + " ms");
        sut.print();

        assertThat(sut.toString()).isEqualToIgnoringNewLines(
                " 5  3  4  6  7  8  9  1  2 \n"
                        + " 6  7  2  1  9  5  3  4  8 \n"
                        + " 1  9  8  3  4  2  5  6  7 \n"
                        + " 8  5  9  7  6  1  4  2  3 \n"
                        + " 4  2  6  8  5  3  7  9  1 \n"
                        + " 7  1  3  9  2  4  8  5  6 \n"
                        + " 9  6  1  5  3  7  2  8  4 \n"
                        + " 2  8  7  4  1  9  6  3  5 \n"
                        + " 3  4  5  2  8  6  1  7  9 \n");
    }

    @Test
    public void shouldFindCandidatesPerCell() {
        sut = Board.from(getInitialBoard());
        quad = sut.getQuad(0);
        sut.setCandidatesFor(quad);

        quad = sut.getQuad(0);
        assertThat(quad.getCell(2, 0).getCanditates())
                .hasSize(3)
                .contains('1', '2', '4');
        assertThat(quad.getCell(1, 1).getCanditates())
                .hasSize(3)
                .contains('2', '4', '7');

        assertThat(quad.getCell(2, 1).getCanditates())
                .hasSize(3)
                .contains('2', '4', '7');

        assertThat(quad.getCell(0, 2).getCanditates())
                .hasSize(2)
                .contains('1', '2');
    }
}