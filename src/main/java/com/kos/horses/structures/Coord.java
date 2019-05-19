package com.kos.horses.structures;

import java.util.Objects;

public class Coord {
    private final long x;
    private final long y;

    public Coord(long x, long y) {
        this.x = x;
        this.y = y;
    }

    public Coord(Coord current, Step step) {
        this.x = current.x + step.getDeltaX();
        this.y = current.y + step.getDeltaY();
    }


    public long getX() {
        return x;
    }

    public long getY() {
        return y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coord coord = (Coord) o;
        return x == coord.x &&
                y == coord.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return x + "x" + y;
    }

    public Coord transpose() {
        //noinspection SuspiciousNameCombination
        return new Coord(y, x);
    }
}
