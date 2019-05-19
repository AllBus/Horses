package com.kos.horses.problems.horses.solvers;


import com.kos.horses.problems.horses.HorseConstants;
import com.kos.horses.problems.horses.results.SolveVisualizier;
import com.kos.horses.structures.Coord;
import com.kos.horses.structures.IBoard;
import com.kos.horses.structures.ISolver;
import com.kos.horses.structures.Step;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DummyHorseSolver implements ISolver {
    @Override
    public SolveVisualizier solve(IBoard board, Coord begin, Coord end) {

        SolverUtils.checkCoordinatesInBoard(board, begin, end);

        SolveVisualizier result = new SolveVisualizier(board.getWidth(), board.getHeight());

        return bfs(board, begin, end, result);
    }

    private SolveVisualizier bfs(IBoard board, Coord begin, Coord end, SolveVisualizier result) {
        List<Coord> queue = new ArrayList<>();

        queue.add(begin);

        Map<Coord, Long> moveCounter = new HashMap<>();
        moveCounter.put(begin, 0L);
        result.change(begin, '0');

        while (!queue.isEmpty()) {
            Coord current = queue.remove(0);
            long nextMoveCount = moveCounter.get(current) + 1;
            char nextChar = (char) ((int) nextMoveCount + '0');

            for (Step step : HorseConstants.steps) {
                Coord newCoord = new Coord(current, step);

                if (board.inBoard(newCoord) && !moveCounter.containsKey(newCoord)) {
                    if (newCoord.equals(end)) {
                        result.setResult(true, nextMoveCount);
                        //Результат известен можно завершить алгоритм, но мы хотим узнать рассотяние до всех клеток
                    }

                    moveCounter.put(newCoord, nextMoveCount);
                    queue.add(newCoord);
                    result.change(newCoord, nextChar);
                }
            }
        }


        result.setResult(false, HorseConstants.NO_SOLUTION_VALUE);
        return result;
    }
}
