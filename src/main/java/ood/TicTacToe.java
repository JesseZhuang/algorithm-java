package ood;

import array.FindWinnerTicTacToe;

/**
 * LeetCode 348, LintCode 746, medium, tags: array, simulation, matrix, design, hash table.
 * <p>
 * Assume the following rules are for the tic-tac-toe game on an n x n board between two players:
 * <p>
 * A move is guaranteed to be valid and is placed on an empty block.
 * Once a winning condition is reached, no more moves are allowed.
 * A player who succeeds in placing n of their marks in a horizontal, vertical, or diagonal row wins the game.
 * Implement the TicTacToe class:
 * <p>
 * TicTacToe(int n) Initializes the object the size of the board n.
 * int move(int row, int col, int player) Indicates that the player with id player plays at the cell (row, col) of
 * the board. The move is guaranteed to be a valid move, and the two players alternate in making moves. Return
 * 0 if there is no winner after the move,
 * 1 if player 1 is the winner after the move, or
 * 2 if player 2 is the winner after the move.
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input
 * ["TicTacToe", "move", "move", "move", "move", "move", "move", "move"]
 * [[3], [0, 0, 1], [0, 2, 2], [2, 2, 1], [1, 1, 2], [2, 0, 1], [1, 0, 2], [2, 1, 1]]
 * Output
 * [null, 0, 0, 0, 0, 0, 0, 1]
 * <p>
 * Explanation
 * TicTacToe ticTacToe = new TicTacToe(3);
 * Assume that player 1 is "X" and player 2 is "O" in the board.
 * ticTacToe.move(0, 0, 1); // return 0 (no one wins)
 * |X| | |
 * | | | |    // Player 1 makes a move at (0, 0).
 * | | | |
 * <p>
 * ticTacToe.move(0, 2, 2); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 2 makes a move at (0, 2).
 * | | | |
 * <p>
 * ticTacToe.move(2, 2, 1); // return 0 (no one wins)
 * |X| |O|
 * | | | |    // Player 1 makes a move at (2, 2).
 * | | |X|
 * <p>
 * ticTacToe.move(1, 1, 2); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 2 makes a move at (1, 1).
 * | | |X|
 * <p>
 * ticTacToe.move(2, 0, 1); // return 0 (no one wins)
 * |X| |O|
 * | |O| |    // Player 1 makes a move at (2, 0).
 * |X| |X|
 * <p>
 * ticTacToe.move(1, 0, 2); // return 0 (no one wins)
 * |X| |O|
 * |O|O| |    // Player 2 makes a move at (1, 0).
 * |X| |X|
 * <p>
 * ticTacToe.move(2, 1, 1); // return 1 (player 1 wins)
 * |X| |O|
 * |O|O| |    // Player 1 makes a move at (2, 1).
 * |X|X|X|
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 2 <= n <= 100
 * player is 1 or 2.
 * 0 <= row, col < n
 * (row, col) are unique for each different call to move.
 * At most n^2 calls will be made to move.
 * <p>
 * <p>
 * Follow-up: Could you do better than O(n^2) per move() operation?
 */
public class TicTacToe {

    boolean gameEnded;
    private FindWinnerTicTacToe fw;

    TicTacToe(int n) {
        fw = new FindWinnerTicTacToe(n);
    }

    int move(int row, int col, int player) {
        if (gameEnded) throw new RuntimeException("game ended");
        String res = fw.tictactoe(row, col, player);
        return switch (res) {
            case "A":
                yield 1;
            case "B":
                yield 2;
            case "Draw":
                gameEnded = true; // fall through to return 0
            default: // Pending
                yield 0;
        };
    }
}

/**
 * LintCode 746, hard.
 * <p>
 * Design Tic-Tac-Toe game.
 * <p>
 * board has fixed size of 3
 * X always take the first move
 * If a place already got taken, and one player want to take that place,
 * an AlreadyTakenException will be thrown
 * <p>
 * If one player wins, and somebody try to make another move, a GameEndException will be thrown.
 * If all the places got taken,you should print "it's a draw"
 * If the winner shows up, return True
 * If tie or if there is no winner yet, return False
 * <p>
 * public TicTacToe() {}
 * <p>
 * public boolean move(int row, int col) throws AlreadyTakenException, GameEndException {}
 */