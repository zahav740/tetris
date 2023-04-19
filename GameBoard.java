package org.example;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    public static final int ROWS = 1024;
    public static final int COLS = 10;
    private final int rows;
    private final int cols;
    private final Color[][] board;
    public GameBoard(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        board = new Color[rows][cols];
        setPreferredSize(new Dimension(cols * TetrisGame.BLOCK_SIZE, rows * TetrisGame.BLOCK_SIZE));
    }

    public void clearBoard() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = null;
            }
        }
    }

    public void drawBoard() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                System.out.print(board[row][col] != null ? "[]" : "  ");
            }
            System.out.println();
        }
    }

    public void drawTetromino(Tetromino tetromino) {
        int[][] shape = tetromino.getShape();
        int row = tetromino.getRow();
        int col = tetromino.getCol();
        Color color = tetromino.getColor();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    setCellColor(row + i, col + j, color);
                }
            }
        }
    }

    public void checkRows() {
        int numCompletedRows = 0;
        for (int row = 0; row < rows; row++) {
            boolean rowCompleted = true;
            for (int col = 0; col < cols; col++) {
                if (board[row][col] == null) {
                    rowCompleted = false;
                    break;
                }
            }
            if (rowCompleted) {
                numCompletedRows++;
                removeRow(row);
            }
        }
        if (numCompletedRows > 0) {
            System.out.println("You completed " + numCompletedRows + " row(s)!");
        }
    }

    public void removeRow(int row) {
        for (int i = row; i > 0; i--) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = board[i - 1][j];
            }
        }
        for (int j = 0; j < cols; j++) {
            board[0][j] = null;
        }
    }

    public int getCols() {
        return cols;
    }

    public void setCellColor(int row, int col, Color color) {
        board[row][col] = color;
    }

    public boolean isValidMove(Tetromino tetromino, int row, int col) {
        int[][] shape = tetromino.getShape();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    int newRow = row + i;
                    int newCol = col + j;
                    if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols) {
                        return false;
                    }
                    if (board[newRow][newCol] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public int getRows() {
        return this.rows;
    }

    public void placeTetromino(Tetromino tetromino) {
        int[][] shape = tetromino.getShape();
        int row = tetromino.getRow();
        int col = tetromino.getCol();
        Color color = tetromino.getColor();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    setCellColor(row + i, col + j, color);
                }
            }
        }
    }


}



