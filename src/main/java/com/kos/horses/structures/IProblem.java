package com.kos.horses.structures;

import org.springframework.lang.NonNull;

public interface IProblem {
    @NonNull
    ISolverResult solveProblem();
}
