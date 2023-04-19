package org.example;

import java.awt.Color;
import java.util.Random;
import javax.swing.Timer;

public class GameController {
    private GameBoard gameBoard;
    private Tetromino currentTetromino;
    private Timer tetrisTimer = new Timer(500, e -> update());

    public GameController(GameBoard gameBoard) {
        this.gameBoard = gameBoard;
        currentTetromino = new Tetromino(new int[][] {
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}
        }, 0, gameBoard.getCols() / 2 - 1, Color.ORANGE);
    }

    private void drawBlock(int row, int col, Color color) {
        gameBoard.setCellColor(row, col, color);
    }

    public void generateNewTetromino() {
        Random random = new Random();
        int index = random.nextInt(Tetromino.SHAPES.length);
        int[][] shape = Tetromino.SHAPES[index];
        int row = 0;
        int col = gameBoard.getCols() / 2 - 1;
        Color color = Tetromino.COLORS[index];
        currentTetromino = new Tetromino(shape, row, col, color);
    }

    public void drawTetromino(Tetromino tetromino) {
        int[][] shape = tetromino.getShape();
        int row = tetromino.getRow();
        int col = tetromino.getCol();
        for (int i = 0; i < shape.length; i++) {
            for (int j = 0; j < shape[0].length; j++) {
                if (shape[i][j] != 0) {
                    drawBlock(row + i, col + j, tetromino.getColor());
                }
            }
        }
    }
    public void moveCurrentTetrominoLeft() {
        if (gameBoard.isValidMove(currentTetromino, currentTetromino.getRow(), currentTetromino.getCol() - 1)) {
            currentTetromino.moveLeft();
        }
    }
    public void moveCurrentTetrominoRight() {
        if (gameBoard.isValidMove(currentTetromino, currentTetromino.getRow(), currentTetromino.getCol() + 1)) {
            currentTetromino.moveRight();
        }
    }
    public void rotateCurrentTetromino() {
        if (gameBoard.isValidMove(currentTetromino.rotateClockwise(), currentTetromino.getRow(), currentTetromino.getCol())) {
            currentTetromino = currentTetromino.rotateClockwise();
        }
    }
    public void moveCurrentTetrominoDown() {
        if (gameBoard.isValidMove(currentTetromino, currentTetromino.getRow() + 1, currentTetromino.getCol())) {
            currentTetromino.moveDown();
        } else {
            gameBoard.placeTetromino(currentTetromino);
            generateNewTetromino();
        }
    }
    public void dropCurrentTetromino() {
        while (gameBoard.isValidMove(currentTetromino, currentTetromino.getRow() + 1, currentTetromino.getCol())) {
            currentTetromino.moveDown();
        }
        gameBoard.placeTetromino(currentTetromino);
        generateNewTetromino();
    }
    public void update() {
        moveCurrentTetrominoDown();
        gameBoard.checkRows();
    }
    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }
    public void pause() {
        tetrisTimer.stop();
    }

}