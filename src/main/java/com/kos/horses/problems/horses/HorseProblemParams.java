package com.kos.horses.problems.horses;

import com.kos.horses.structures.Coord;
import com.kos.horses.structures.IBoard;

public class HorseProblemParams {
    private final IBoard board;
    private final Coord start;
    private final Coord end;

    public HorseProblemParams(IBoard board, Coord start, Coord end) {
        this.board = board;
        this.start = start;
        this.end = end;
    }

    public IBoard getBoard() {
        return board;
    }

    public Coord getStart() {
        return start;
    }

    public Coord getEnd() {
        return end;
    }
}
