package com.kos.horses.problems.horses.results;

import com.kos.horses.structures.ISolverResult;

public class HorseResult implements ISolverResult {

    private final long result;
    private final boolean solve;


    public HorseResult(boolean solve, long result) {
        this.result = result;
        this.solve = solve;
    }

    @Override
    public long getResult() {
        return result;
    }

    @Override
    public boolean hasSolution() {
        return solve;
    }
}
