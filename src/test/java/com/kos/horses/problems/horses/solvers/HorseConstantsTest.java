package com.kos.horses.problems.horses.solvers;

import com.kos.horses.problems.horses.HorseConstants;
import com.kos.horses.problems.horses.results.SolveVisualizier;
import com.kos.horses.structures.Coord;
import com.kos.horses.structures.Step;
import org.junit.Assert;
import org.junit.Test;

public class HorseConstantsTest {

    @Test
    public void testHorseStep() {

        Coord startCoord = new Coord(3, 2);
        String oneMoveBoard =

                ("//1/1///|" +
                "/1///1//|" +
                "////////|" +
                "/1///1//|" +
                "//1/1///|" +
                "////////|" +
                "////////|" +
                "////////|").replace('|', '\n');

        SolveVisualizier boardVisualizier = new SolveVisualizier(8, 8);
        for (Step step : HorseConstants.steps) {
            Coord move = new Coord(startCoord, step);
            boardVisualizier.change(move, '1');
        }

        String stepsBoard = boardVisualizier.toString();
        Assert.assertEquals(oneMoveBoard, stepsBoard);
    }




}
