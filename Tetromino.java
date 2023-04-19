package org.example;

import java.awt.*;

public class Tetromino {
    GameBoard gameBoard = new GameBoard(20, 10);

    public static final int[][][] SHAPES = {
            {{1, 1, 1, 1}}, // I
            {{1, 1}, {1, 1}}, // O
            {{1, 1, 0}, {0, 1, 1}}, // Z
            {{0, 1, 1}, {1, 1, 0}}, // S
            {{1, 1, 1}, {0, 1, 0}}, // T
            {{1, 1, 1}, {1, 0, 0}}, // L
            {{1, 1, 1}, {0, 0, 1}} // J
    };
    public static final Color[] COLORS = {
            Color.CYAN, // I
            Color.YELLOW, // O
            Color.RED, // Z
            Color.GREEN, // S
            Color.MAGENTA, // T
            Color.ORANGE, // L
            Color.BLUE // J
    };
    private int[][] shape;
    private int row;
    private int col;
    private Color color;
    public Tetromino(int[][] shape, int row, int col, Color color) {
        this.shape = shape;
        this.row = row;
        this.col = col;
        this.color = color;
    }
    public Tetromino rotateClockwise() {
        int[][] rotatedShape = new int[shape[0].length][shape.length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                rotatedShape[j][shape.length - i - 1] = shape[i][j];
            }
        }
        shape = rotatedShape;
        return null;
    }
    public int[][] getShape() {
        return shape;
    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public Color getColor() {
        return color;
    }
    public void rotate() {
        int[][] newShape = new int[shape.length][shape[0].length];
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                newShape[j][shape.length - 1 - i] = shape[i][j];
            }
        }
        shape = newShape;
    }
    public void moveLeft() {
        col--;
    }
    public void moveRight() {
        col++;
    }
    public void moveDown() {
        row++;
    }
    public void placeTetromino(Tetromino tetromino) {
        int[][] shape = tetromino.getShape();
        int row = tetromino.getRow();
        int col = tetromino.getCol();
        Color color = tetromino.getColor();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    gameBoard.setCellColor(row + i, col + j, color);
                }
            }
        }
    }
}




