package com.kos.horses.problems.horses;

import com.kos.horses.structures.Step;

public class HorseConstants {

    public static final Step[] steps = {
            new Step(1, 2),
            new Step(2, 1),
            new Step(1, -2),
            new Step(2, -1),
            new Step(-1, 2),
            new Step(-2, 1),
            new Step(-1, -2),
            new Step(-2, -1),
    };
    public static final int NO_SOLUTION_VALUE = -1;

}
