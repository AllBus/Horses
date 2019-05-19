package com.kos.horses.structures;

public class Board implements IBoard {

    protected long width;
    protected long height;

    public static final long MAX_BOARD_SIZE = 1000_000_000_000_000L;

    public Board(long width, long height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Board size should be > 0");

        if (width > MAX_BOARD_SIZE || height > MAX_BOARD_SIZE)
            throw new IllegalArgumentException("Board size should be <= " + MAX_BOARD_SIZE);

        this.width = width;
        this.height = height;
    }

    @Override
    public long getWidth() {
        return width;
    }

    @Override
    public long getHeight() {
        return height;
    }

    @Override
    public boolean inBoard(Coord coord) {
        return coord.getX() >= 0 && coord.getX() < getWidth() && coord.getY() >= 0 && coord.getY() < getHeight();
    }

}
