package com.kos.horses.problems.horses.results;


import com.kos.horses.structures.Coord;
import com.kos.horses.structures.ISolverResult;

public class SolveVisualizier implements ISolverResult {

    private char[][] draw;
    private long result = -1L;
    private boolean solve = false;
    private int width;
    private int height;

    public void setResult(boolean solve, long result) {
        this.result = result;
        this.solve = solve;
    }


    public SolveVisualizier(long width, long height) {
        if (width > 5000 || height > 5000)
            throw new IllegalArgumentException("Visualizier support board only width or height less 5000 ");

        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("Visualizier support board only width or height great 0 ");

        this.width = (int) width;
        this.height = (int) height;

        draw = new char[this.height][this.width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                draw[y][x] = '/';
            }
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < height; y++) {
            sb.append(draw[y]);
            sb.append("\n");
        }
        return sb.toString();
    }

    public void change(Coord coord, char value) {
        draw[(int) coord.getY()][(int) coord.getX()] = value;
    }

    public char getValue(Coord coord) {
        return draw[(int) coord.getY()][(int) coord.getX()];

    }

    @Override
    public long getResult() {
        return result;
    }

    @Override
    public boolean hasSolution() {
        return solve;
    }
}
