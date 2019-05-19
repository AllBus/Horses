package com.kos.horses.problems.horses.solvers;


import com.kos.horses.problems.horses.results.SolveVisualizier;
import com.kos.horses.structures.*;
import org.junit.Assert;
import org.junit.Test;

public class DummyHorseSolverTest {

    @Test
    public void testDummySolver() {
        ISolver solver = new DummyHorseSolver();
        IBoard board = new Board(8, 8);

        String moveBoard =
               ("34121432|" +
                "21232123|" +
                "32303232|" +
                "21232123|" +
                "34121432|" +
                "23232323|" +
                "32323234|" +
                "43434343|").replace('|', '\n');

        Coord start = new Coord(3, 2);
        Coord end = new Coord(0, 0);
        ISolverResult solve = solver.solve(board, start, end);

        Assert.assertEquals(moveBoard, solve.toString());
    }

    @Test
    public void testDummySolverEdge() {
        ISolver solver = new DummyHorseSolver();
        IBoard board = new Board(8, 3);

        String moveBoard =
               ("32303232|" +
                "21232123|" +
                "34121432|").replace('|', '\n');


        Coord start = new Coord(3, 0);
        Coord end = new Coord(0, 0);
        ISolverResult solve = solver.solve(board, start, end);

        Assert.assertEquals(moveBoard, solve.toString());
    }

    @Test
    public void testDummySolver1x0() {
        ISolver solver = new DummyHorseSolver();
        IBoard board = new Board(6, 3);

        String moveBoard =
               ("303232|" +
                "232123|" +
                "121432|").replace('|', '\n');


        Coord start = new Coord(1, 0);
        Coord end = new Coord(0, 0);
        ISolverResult solve = solver.solve(board, start, end);

        Assert.assertEquals(moveBoard, solve.toString());
    }



    @Test
    public void testDummySolverCorner() {
        ISolver solver = new DummyHorseSolver();
        IBoard board = new Board(5, 5);

        String moveBoard =
               ("03232|" +
                "34123|" +
                "21432|" +
                "32323|" +
                "23234|").replace('|', '\n');


        Coord start = new Coord(0, 0);
        Coord end = new Coord(0, 0);
        ISolverResult solve = solver.solve(board, start, end);

        Assert.assertEquals(moveBoard, solve.toString());
    }

    @SuppressWarnings("SuspiciousNameCombination")
    @Test
    public void testDummySymmetryResult() {
        DummyHorseSolver solver = new DummyHorseSolver();
        IBoard board = new Board(1000, 1000);
        Coord start = new Coord(0, 0);
        Coord end = new Coord(0, 0);
        SolveVisualizier solve = solver.solve(board, start, end);

        for (int x = 0; x < board.getWidth(); x++)
            for (int y = x + 1; y < board.getWidth(); y++) {
                Assert.assertEquals(solve.getValue(new Coord(x, y)), solve.getValue(new Coord(y, x)));
            }
    }

    @Test
    public void testDummySymmetryResultCenterStart() {
        DummyHorseSolver solver = new DummyHorseSolver();
        IBoard board = new Board(1000, 1000);
        Coord start = new Coord(300, 500);
        Coord end = new Coord(0, 0);
        SolveVisualizier solve = solver.solve(board, start, end);

        for (int x = -300; x < 500; x++)
            for (int y = x + 1; y < 500; y++) {
                Assert.assertEquals(solve.getValue(new Coord(start.getX() + x, start.getY() + y)), solve.getValue(new Coord(start.getX() + y, start.getY() + x)));
            }
        for (int x = -300; x < 300; x++)
            for (int y = x + 1; y < 300; y++) {
                Assert.assertEquals(solve.getValue(new Coord(start.getX() - x, start.getY() + y)), solve.getValue(new Coord(start.getX() - y, start.getY() + x)));
            }
    }

    @Test
    public void testDummyBoardWidth1(){

        DummyHorseSolver solver = new DummyHorseSolver();
        Coord end = new Coord(0, 0);

        for (int height=1;height<1000;height+=1) {
            IBoard board = new Board(1, height);
            for (int y=0;y<height;y++){
                Coord start = new Coord(0, y);
                SolveVisualizier solve = solver.solve(board, start, end);
                for (int yv=0;yv<height;yv++){
                    if (yv!=y) {
                        Assert.assertEquals('/', solve.getValue(new Coord(0, yv)));
                    }
                    else
                        Assert.assertEquals('0',solve.getValue(new Coord(0,yv)));
                }
            }
        }
    }

    @Test
    public void testDummyBoardWidth2(){

        DummyHorseSolver solver = new DummyHorseSolver();
        Coord end = new Coord(0, 0);

        for (int height=1;height<1000;height+=1) {
            IBoard board = new Board(2, height);
            for (int y=0;y<height;y++){
                Coord start = new Coord(0, y);
                SolveVisualizier solve = solver.solve(board, start, end);

                for (int yv=0;yv<height;yv++){
                    int dist = Math.abs(y-yv);
                    int resValue=dist/2;
                    int resX = resValue%2;
                    if (dist%2==1) {
                        Assert.assertEquals('/', solve.getValue(new Coord(0, yv)));
                        Assert.assertEquals('/', solve.getValue(new Coord(1, yv)));
                    }else{
                        Assert.assertEquals((char)(resValue+'0'), solve.getValue(new Coord(resX, yv)));
                        Assert.assertEquals('/', solve.getValue(new Coord((resX==1)?0:1, yv)));
                    }
                }
            }
        }
    }
    @Test
    public void testDummyBoard3x3(){
        String moveBoard1x1 =
               ("///|" +
                "/0/|" +
                "///|").replace('|', '\n');
        String moveBoard1x0 =
                ("303|" +
                 "2/2|" +
                 "141|").replace('|', '\n');
        String moveBoard0x0 =
                ("032|" +
                 "3/1|" +
                 "214|").replace('|', '\n');


        DummyHorseSolver solver = new DummyHorseSolver();
        IBoard board = new Board(3, 3);

        Coord end = new Coord(0, 0);
        ISolverResult solve1x1 = solver.solve(board, new Coord(1, 1), end);
        ISolverResult solve1x0 = solver.solve(board, new Coord(1, 0), end);
        ISolverResult solve0x0 = solver.solve(board, new Coord(0, 0), end);
        Assert.assertEquals(moveBoard1x1, solve1x1.toString());
        Assert.assertEquals(moveBoard1x0, solve1x0.toString());
        Assert.assertEquals(moveBoard0x0, solve0x0.toString());
    }
    @Test
    public void testDummyBoard3x4(){
        String moveBoard1x1 =
                ("4321|" +
                 "3054|" +
                 "4321|").replace('|', '\n');

        DummyHorseSolver solver = new DummyHorseSolver();
        IBoard board = new Board(4, 3);

        Coord end = new Coord(0, 0);
        ISolverResult solve1x1 = solver.solve(board, new Coord(1, 1), end);
        Assert.assertEquals(moveBoard1x1, solve1x1.toString());
    }
}
