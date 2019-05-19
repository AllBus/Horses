package com.kos.horses.problems.horses.solvers;

import com.kos.horses.problems.horses.results.SolveVisualizier;
import com.kos.horses.structures.Board;
import com.kos.horses.structures.Coord;
import com.kos.horses.structures.ISolver;
import com.kos.horses.structures.ISolverResult;
import org.junit.Assert;

public class SolverTestUtils {

    public static final int[] testSizes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 24, 33, 57, 98, 167, 250, 500, 1000, 3000};
    public static final int[] smallTestSizes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 16, 20};

    void compareFastSolveWithDummy(ISolver solver, Coord start, int width, int height) {
        DummyHorseSolver dummySolver = new DummyHorseSolver();
        Board board = new Board(width, height);

        SolveVisualizier fastResult = new SolveVisualizier(width, height);

        Coord end = new Coord(0, 0);
        SolveVisualizier dummyResult = dummySolver.solve(board, start, end);

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Coord coord = new Coord(x, y);
                ISolverResult solve = solver.solve(board, start, coord);

                fastResult.change(coord, (char) (solve.getResult() + '0'));
            }
        }
        Assert.assertEquals(dummyResult.toString(), fastResult.toString());
    }
}
