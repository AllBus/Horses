package com.kos.horses.problems.horses;

import com.kos.horses.structures.Board;
import com.kos.horses.structures.Coord;
import com.kos.horses.utils.ConvertException;
import com.kos.horses.utils.CoordConverter;

public class HorseProblemFactory {

    public static HorseProblemParams build(long width, long height, String startText, String endText) throws ConvertException, IllegalArgumentException {
        Board board = new Board(width, height);
        Coord startCoord = CoordConverter.exelCoordToBoardCoord(startText);
        Coord endCoord = CoordConverter.exelCoordToBoardCoord(endText);

        //Уменьшим значения координат на 1 так как отсчёт на доске ведётся с 0
        return new HorseProblemParams(
                board,
                new Coord(startCoord.getX() - 1, startCoord.getY() - 1),
                new Coord(endCoord.getX() - 1, endCoord.getY() - 1)
        );
    }

    public static HorseProblemParams build(String width, String height, String startText, String endText) throws ConvertException, IllegalArgumentException {
        return build(Long.parseLong(width), Long.parseLong(height), startText, endText);
    }

}
