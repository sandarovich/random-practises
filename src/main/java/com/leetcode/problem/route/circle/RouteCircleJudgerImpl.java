package com.leetcode.problem.route.circle;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RouteCircleJudgerImpl implements RouteCircleJudger {

    @Override
    public boolean isCircleMove(String moves) {
        List<Move> mvs = parseMoves(moves);
        Integer horizontalShift = getShiftByAxis(mvs, Axis.X);
        Integer verticalShift = getShiftByAxis(mvs, Axis.Y);

        return horizontalShift == 0 && verticalShift == 0;

    }

    private int getShiftByAxis(List<Move> moves, Axis axis) {
        return moves.stream()
                .filter(getMovePredicate(axis))
                .mapToInt(m -> m.step)
                .sum();
    }

    private Predicate<Move> getMovePredicate(Axis axis) {
        return Axis.X.equals(axis) ? Move::isVertical : Move::isHorizontal;
    }

    private List<Move> parseMoves(String moves) {
        String legalMoves = Optional.ofNullable(moves).orElse("");
        return Arrays.stream(legalMoves.split(""))
                .map(Move::fromString)
                .collect(Collectors.toList());
    }

    public enum Axis {
        X,
        Y
    }

    public enum Move {
        RIGHT("R", -1),
        LEFT("L", 1),
        DOWN("D", -1),
        UP("U", 1);

        private final String value;
        private final int step;

        Move(String value, int step) {
            this.value = value;
            this.step = step;
        }

        public static boolean isHorizontal(Move move) {
            return move == Move.DOWN || move == Move.UP;
        }

        public static boolean isVertical(Move move) {
            return move == Move.RIGHT || move == Move.LEFT;
        }

        public static Move fromString(String move) {
            return Stream.of(values())
                    .filter(v -> v.getValue().equalsIgnoreCase(move))
                    .findAny()
                    .orElse(null);
        }

        public String getValue() {
            return this.value;
        }
    }
}
