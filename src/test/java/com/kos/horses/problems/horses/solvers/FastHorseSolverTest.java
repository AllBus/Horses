package com.kos.horses.problems.horses.solvers;


import com.kos.horses.structures.Coord;
import com.kos.horses.structures.ISolver;
import org.junit.Test;

public class FastHorseSolverTest extends  SolverTestUtils {

    private void compareFastSolveWithDummy(Coord start, int width, int height){
        ISolver solver = new FastHorseSolver();
        compareFastSolveWithDummy(solver,start,width,height);
    }

    @Test
    public void testFastSolverCorner() {
        int size = 100;
        Coord start = new Coord(0, 0);

        compareFastSolveWithDummy(start,size,size);
    }

    @Test
    public void testFastSolver1x0() {
        int size = 500;
        Coord start = new Coord(1, 0);

        compareFastSolveWithDummy(start,size,size);
    }

    @Test
    public void testFastSolver2x0() {
        int size = 500;
        Coord start = new Coord(2, 0);

        compareFastSolveWithDummy(start,size,size);
    }

    @Test
    public void testFastSolver3x0() {
        int size = 500;
        Coord start = new Coord(3, 0);

        compareFastSolveWithDummy(start,size,size);
    }

    @Test
    public void testFastSolver1x1() {
        int size = 500;
        Coord start = new Coord(1, 1);

        compareFastSolveWithDummy(start,size,size);
    }

    @Test
    public void testFastSolver1x2() {
        int size = 500;
        Coord start = new Coord(1, 2);

        compareFastSolveWithDummy(start,size,size);
    }

    @Test
    public void testFastSolver5x6() {
        int size = 500;
        Coord start = new Coord(5, 6);

        compareFastSolveWithDummy(start,size,size);
    }

    @Test
    public void testBoardWidth1(){
        for (int size : testSizes) {
            for (int y=0;y<size;y++) {
                Coord start = new Coord(0, y);
                compareFastSolveWithDummy(start, 1, size);
            }
        }
    }

    @Test
    public void testBoardHeight1(){
        for (int size : testSizes) {
            for (int x=0;x<size;x++) {
                Coord start = new Coord( x,0);
                compareFastSolveWithDummy(start,  size,1);
            }
        }
    }

    @Test
    public void testBoardWidth2(){

        for (int size : testSizes) {
            for (int y=0;y<size;y++) {
                Coord start = new Coord(0, y);
                compareFastSolveWithDummy(start, 2, size);
            }
        }
    }

    @Test
    public void testBoardHeight2(){
        for (int size : testSizes) {
            for (int x=0;x<size;x++) {
                Coord start = new Coord( x,1);
                compareFastSolveWithDummy(start,  size,2);
            }
        }
    }

    @Test
    public void testBoardWidth3(){
        for (int size : testSizes) {
            for (int y=0;y<size;y++) {
                for (int x=0;x<3;x++) {
                    Coord start = new Coord(x, y);
                    compareFastSolveWithDummy(start, 3, size);
                }
            }
        }
    }

    @Test
    public void testBoardHeight3(){
        for (int size : testSizes) {
            for (int x=0;x<size;x++) {
                for (int y=0;y<3;y++) {
                    Coord start = new Coord(x, y);
                    compareFastSolveWithDummy(start, size, 3);
                }
            }
        }
    }


    @Test
    public void testBoardWidth4(){
        for (int size : testSizes) {
            for (int y=0;y<size;y++) {
                for (int x=0;x<4;x++) {
                    Coord start = new Coord(x, y);
                    compareFastSolveWithDummy(start, 4, size);
                }
            }
        }
    }

    @Test
    public void testBoardHeight4(){
        for (int size : testSizes) {
            for (int x=0;x<size;x++) {
                for (int y=0;y<4;y++) {
                    Coord start = new Coord(x, y);
                    compareFastSolveWithDummy(start, size, 4);
                }
            }
        }
    }


    @Test
    public void testBoundStart(){
        for (int width : smallTestSizes) {
            for (int height : smallTestSizes) {
                for (int x=0;x<width;x++) {
                    Coord start = new Coord(x, 0);
                    compareFastSolveWithDummy(start, width, height);
                }
            }
        }
    }

    @Test
    public void testBoundWith1Start(){
        for (int width : smallTestSizes) {
            for (int height : smallTestSizes) {
                if (height>1) {
                    for (int x = 0; x < width; x++) {
                        Coord start = new Coord(x, 1);
                        compareFastSolveWithDummy(start, width, height);
                    }
                }
            }
        }
    }

}
