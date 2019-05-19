package com.kos.horses.structures;

import java.util.Objects;

public class Step {
    private final long deltaX;
    private final long deltaY;

    public Step(long deltaX, long deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public long getDeltaX() {
        return deltaX;
    }

    public long getDeltaY() {
        return deltaY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Step step = (Step) o;
        return deltaX == step.deltaX &&
                deltaY == step.deltaY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(deltaX, deltaY);
    }
}
