package org.example;

import javax.swing.*;
import java.awt.*;

import static org.example.TetrisGame.BLOCK_SIZE;

public class TetrisGUI extends JFrame {
    private GameBoard gameBoard;
    private GameController gameController;
    private JLabel scoreLabel;
    private JButton startButton;
    private JButton pauseButton;

    public TetrisGUI(GameBoard gameBoard, GameController gameController) {
        this.gameBoard = gameBoard;
        gameBoard.setPreferredSize(new Dimension(gameBoard.getCols() * TetrisGame.BLOCK_SIZE, gameBoard.getRows() * TetrisGame.BLOCK_SIZE));
        this.gameController = gameController;
        scoreLabel = new JLabel("Score: 0");
        startButton = new JButton("Start");
        pauseButton = new JButton("Pause");
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(pauseButton);
        add(scoreLabel, BorderLayout.NORTH);
        add(gameBoard, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        startButton.addActionListener(e -> start());
        pauseButton.addActionListener(e -> gameController.pause());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(gameBoard.getCols() * BLOCK_SIZE + 200, gameBoard.getRows() * BLOCK_SIZE + 100);
        setVisible(true);
    }

    public void start() {
        gameController.generateNewTetromino();
    }
}



