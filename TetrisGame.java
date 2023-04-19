package org.example;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class TetrisGame {
    private static final int ROWS = 20;
    private static final int COLS = 10;
//    private static final int BLOCK_SIZE = 30;
    private static final int DELAY = 500;
    public static final int BLOCK_SIZE = 30;

    private GameBoard gameBoard;
    private GameController gameController;
    private TetrisGUI tetrisGUI;
    private Timer timer;

    public TetrisGame() {
        gameBoard = new GameBoard(ROWS, COLS);
        gameController = new GameController(gameBoard);
        tetrisGUI = new TetrisGUI(gameBoard, gameController);
        timer = new Timer(DELAY, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gameController.update();
                gameBoard.drawBoard();
                gameBoard.drawTetromino(gameController.getCurrentTetromino());
            }
        });
        timer.setInitialDelay(0);
    }

    public void start() {
        gameController.generateNewTetromino();
        tetrisGUI.start();
        timer.start();
    }

    public void pause() {
        timer.stop();
    }

    public static void main(String[] args) {
        TetrisGame tetrisGame = new TetrisGame();
        tetrisGame.start();
    }
}



