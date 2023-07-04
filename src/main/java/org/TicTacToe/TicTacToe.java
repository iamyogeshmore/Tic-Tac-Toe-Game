package org.TicTacToe;

import java.util.Random;
import java.util.Scanner;
public class TicTacToe {
    private char[][] board;
    private char currentPlayer;
    public TicTacToe() {
        board = new char[4][4];
        currentPlayer = 'X';
        initializeBoard();
    }

    private void initializeBoard() {
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                board[row][col] = '-';
            }
        }
    }

    public void toss() {
        Random random = new Random();
        int coinToss = random.nextInt(2);
        boolean userInput = (coinToss == 0);
        if (userInput) {
            System.out.println("Player 1 won the coin toss! Player 1 play first");
            currentPlayer = 'X';
        } else {
            System.out.println("Player 2 won the coin toss! Player 2 play first");
            currentPlayer = 'o';
        }
    }

    public void playGame() {
        boolean gameWon = false;
        boolean gameTied = false;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Tic Tac Toe!");
        System.out.println("Player 1: X");
        System.out.println("Player 2: O");
        System.out.println("Let's start the game!");

        while (!gameWon && !gameTied) {
            System.out.println("Current board:");
            printBoard();

            System.out.println("Player " + currentPlayer + ", enter your move (row [1-3] and column [1-3]):");
            int row = scanner.nextInt();
            int col = scanner.nextInt();

            if (isValidMove(row, col)) {
                board[row][col] = currentPlayer;

                if (checkWin()) {
                    gameWon = true;
                    System.out.println("Player " + currentPlayer + " wins!");
                } else if (checkTie()) {
                    gameTied = true;
                    System.out.println("It's a tie!");
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("Invalid move. Please try again.");
            }
        }
        scanner.close();
    }
    private boolean isValidMove(int row, int col) {
        if (row < 1 || row > 3 || col < 1 || col > 3) {
            return false;
        }
        return board[row][col] == '-';
    }

    private boolean checkWin() {
        for (int row = 1; row <= 3; row++) {
            if (board[row][1] != '-' && board[row][1] == board[row][2] && board[row][2] == board[row][3]) {
                return true;
            }
        }

        for (int col = 1; col <= 3; col++) {
            if (board[1][col] != '-' && board[1][col] == board[2][col] && board[2][col] == board[3][col]) {
                return true;
            }
        }

        if (board[1][1] != '-' && board[1][1] == board[2][2] && board[2][2] == board[3][3]) {
            return true;
        }

        if (board[1][3] != '-' && board[1][3] == board[2][2] && board[2][2] == board[3][1]) {
            return true;
        }
        return false;
    }

    private boolean checkTie() {
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                if (board[row][col] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    private void printBoard() {
        for (int row = 1; row <= 3; row++) {
            for (int col = 1; col <= 3; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}