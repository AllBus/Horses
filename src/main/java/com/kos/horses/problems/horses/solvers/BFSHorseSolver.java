package com.kos.horses.problems.horses.solvers;

import com.kos.horses.problems.horses.HorseConstants;
import com.kos.horses.problems.horses.results.HorseResult;
import com.kos.horses.structures.Coord;
import com.kos.horses.structures.IBoard;
import com.kos.horses.structures.ISolver;
import com.kos.horses.structures.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.kos.horses.problems.horses.HorseConstants.NO_SOLUTION_VALUE;

public class BFSHorseSolver implements ISolver {

    @Override
    public HorseResult solve(IBoard board, Coord begin, Coord end) {
        SolverUtils.checkCoordinatesInBoard(board, begin, end);

        if (begin.equals(end)) {
            return new HorseResult(true, 0);
        }
        return bfs(board, begin, end);
    }


    private HorseResult bfs(IBoard board, Coord begin, Coord end) {
        List<Coord> queue = new ArrayList<>();
        queue.add(begin);

        Map<Coord, Long> moveCounter = new HashMap<>();
        moveCounter.put(begin, 0L);

        while (!queue.isEmpty()) {
            Coord current = queue.remove(0);
            long nextMoveCount = moveCounter.get(current) + 1;

            for (Step step : HorseConstants.steps) {
                Coord newCoord = new Coord(current, step);

                if (board.inBoard(newCoord) && !moveCounter.containsKey(newCoord)) {
                    if (newCoord.equals(end)) {
                        return new HorseResult(true, nextMoveCount);
                    } else {
                        moveCounter.put(newCoord, nextMoveCount);
                        queue.add(newCoord);
                    }
                }
            }
        }

        return new HorseResult(false, NO_SOLUTION_VALUE);
    }
}
