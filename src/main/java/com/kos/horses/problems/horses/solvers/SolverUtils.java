package com.kos.horses.problems.horses.solvers;

import com.kos.horses.structures.Coord;
import com.kos.horses.structures.IBoard;

class SolverUtils {

    public static void checkCoordinatesInBoard(IBoard board, Coord begin, Coord end) {
        if (!(board.inBoard(begin) && board.inBoard(end)))
            throw new IndexOutOfBoundsException("Coordinates should be in board");

    }
}
