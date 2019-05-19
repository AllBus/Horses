package com.kos.horses.problems.horses;

import com.kos.horses.problems.horses.solvers.FastHorseSolver;
import com.kos.horses.structures.IProblem;
import com.kos.horses.structures.ISolver;
import com.kos.horses.structures.ISolverResult;

public class HorseProblem implements IProblem {

    private final HorseProblemParams problemParams;

    public HorseProblem(HorseProblemParams params) {
        this.problemParams = params;
    }

    @Override
    public ISolverResult solveProblem(){
        ISolver solver = new FastHorseSolver();
        return solver.solve(problemParams.getBoard(), problemParams.getStart(), problemParams.getEnd());
    }
}
