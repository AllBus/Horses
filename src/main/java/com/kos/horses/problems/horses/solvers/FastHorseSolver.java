package com.kos.horses.problems.horses.solvers;

import com.kos.horses.problems.horses.results.HorseResult;
import com.kos.horses.structures.Coord;
import com.kos.horses.structures.IBoard;
import com.kos.horses.structures.ISolver;
import org.springframework.lang.Nullable;

import static com.kos.horses.problems.horses.HorseConstants.NO_SOLUTION_VALUE;

public class FastHorseSolver implements ISolver {

    @Override
    public HorseResult solve(IBoard board, Coord begin, Coord end) {

        SolverUtils.checkCoordinatesInBoard(board, begin, end);

        if (begin.equals(end))
            return new HorseResult(true, 0);

        //Частные случаеи доски
        HorseResult specialCaseResult = specialCaseBoards(board, begin, end);
        if (specialCaseResult != null)
            return specialCaseResult;

        //Основные доски
        return mainAlgorithm(board, begin, end);
    }

    /**
     * Основной алгоритм поиска количества шагов коня из begin в end
     *
     * @param board доска
     * @param begin координата начала движения
     * @param end   координата конца движения
     * @return решение задачи
     */
    private HorseResult mainAlgorithm(IBoard board, Coord begin, Coord end) {
        long x = Math.abs(end.getX() - begin.getX()); //Расстояние по оси x
        long y = Math.abs(end.getY() - begin.getY()); //Расстояние по оси y

        if (x <= 3 && y <= 3) {
            //Для малых расстояний в случае если начало или конец находятся у края доски основная формула не сработает.
            //Решим задачу другим алгоритмом
            return new BFSHorseSolver().solve(board, begin, end);
        }

        long coordSum = x + y;

        //Конь за ход может максимум преодолеть 3 клетки
        long cr = coordSum / 3; //Диагональный радиус
        //Конь не может по одной оси двигаться больше чем на две клетки за ход
        long xr = x / 2;  // Радиус по оси x
        long yr = y / 2;  // Радиус по оси y

        //Возьмём максимальное значение из радиусов, так как за меньший радиус конь пройти не может
        long value = (cr > xr) ? ((cr > yr) ? cr : yr) : ((xr > yr) ? xr : yr);

        boolean evenCoordSum = coordSum % 2 == 0;

        //Сумма координат чётное - результат чётное число ходов, иначе наоборот

        if ((value % 2 == 1) == evenCoordSum) {
            //Увеличим, чтобы сохранить чётность
            value += 1;
        } else {
            int offset = (evenCoordSum) ? 1 : 3;

            if (
                    (xr >= cr && (x % 4 == offset)) ||
                    (yr >= cr && (y % 4 == offset)) ||
                    (xr <= cr && yr <= cr && ((x + y) % 3 == 2))
            ) {
                value += 2;
            }
        }

        return new HorseResult(true, value);
    }

    /**
     * Частные случаеи доски
     *
     * @param board доска
     * @param begin координата начала движения
     * @param end   координата конца движения
     * @return вернёт результат или null если не является частным случаем доски
     */
    private @Nullable
    HorseResult specialCaseBoards(IBoard board, Coord begin, Coord end) {
        if (board.getWidth() == 1)
            return solveBoardWidth1(board.getHeight(), begin, end);
        if (board.getHeight() == 1)
            return solveBoardWidth1(board.getWidth(), begin.transpose(), end.transpose());
        if (board.getWidth() == 2)
            return solveBoardWidth2(board.getHeight(), begin, end);
        if (board.getHeight() == 2)
            return solveBoardWidth2(board.getWidth(), begin.transpose(), end.transpose());

        return null;
    }


    private HorseResult solveBoardWidth2(long height, Coord begin, Coord end) {
        long dist = Math.abs(begin.getY() - end.getY());
        long resValue = dist / 2;
        long resX = resValue % 2;

        //Конь может достичь только клетки расстоние до котрых по оси x делится 2
        if (dist % 2 == 0) {
            //А также чередует ряды каждый ход
            long distX = Math.abs(begin.getX() - end.getX());
            if (distX == resX) {
                return new HorseResult(true, resValue);
            }
        }
        return new HorseResult(false, NO_SOLUTION_VALUE);
    }


    private HorseResult solveBoardWidth1(long height, Coord begin, Coord end) {
        //По доске шириной в одну клетку конь не может сдвинуться
        if (begin.equals(end))
            return new HorseResult(true, 0);
        else
            return new HorseResult(false, NO_SOLUTION_VALUE);
    }
}