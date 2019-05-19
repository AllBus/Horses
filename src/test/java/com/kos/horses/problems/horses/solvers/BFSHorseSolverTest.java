package com.kos.horses.problems.horses.solvers;


import com.kos.horses.structures.Coord;
import com.kos.horses.structures.ISolver;
import org.junit.Test;

public class BFSHorseSolverTest extends  SolverTestUtils {

    private void compareSolveWithDummy(Coord start, int width, int height){
        ISolver solver = new BFSHorseSolver();
        compareFastSolveWithDummy(solver,start,width,height);
    }

    @Test
    public void testSolve() {
        for (int width : smallTestSizes) {
            for (int height : smallTestSizes) {
                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        Coord start = new Coord(x, y);
                        compareSolveWithDummy(start, width, height);
                    }
                }
            }
        }
    }
}