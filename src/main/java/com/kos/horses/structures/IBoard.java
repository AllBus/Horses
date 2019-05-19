package com.kos.horses.structures;

import org.springframework.lang.NonNull;

public interface IBoard {
    long getWidth();

    long getHeight();

    boolean inBoard(@NonNull Coord coord);
}
