package com.kos.horses.structures;

import org.springframework.lang.NonNull;

public interface ISolver {

    @NonNull
    ISolverResult solve(@NonNull IBoard board, @NonNull Coord begin, @NonNull Coord end);
}
