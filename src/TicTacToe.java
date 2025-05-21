
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TicTacToe  {
    List<Integer> allowableStroke = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
    boolean currentMove = true;
    private char[][] gameBoard = {
            {'1', '2', '3'},
            {'4', '5', '6'},
            {'7', '8', '9'}
    };
    User user1 = new User('X', true);
    User user2 =  new User('O', false);
    Scanner scanner = new Scanner(System.in);

    private void printPlayingField () {
        for (char[] x : gameBoard) {
            System.out.println(Arrays.toString(x));
        }
    }

    private boolean changePriory (boolean priority) {
        return !priority;
    }

    public void gameLogic () {
        User[] users = {user1, user2};
        User userCurrent = null;

        System.out.println("=========Welcome to TicTacToe=========");
        printPlayingField();

        while (true) {

            for (User user : users) {
                if (user.priorityPlay == currentMove) {
                    userCurrent = user;
                }
            }
            int numberInput = userInput(scanner);
            allowableStroke.removeIf(n -> n == numberInput);
            char ch = (char) ('0' + numberInput);
            updateBoard(ch, userCurrent.markPlay);
            printPlayingField();

            if (checkWin(userCurrent.markPlay)) {
                switch (userCurrent.markPlay) {
                    case 'X' -> System.out.println("First Player WINNER");
                    case 'O' -> System.out.println("LAST Player WINNER");
                }
                break;
            }

            if (drawTest()) {
                System.out.println("Game Over, Draw.");
                break;
            }
            currentMove = changePriory(currentMove);
        }
        scanner.close();
    }

    private boolean checkWin(char playerSymbol) {
        int[][] winningCombinations = {
                {0,1,2}, {3,4,5}, {6,7,8}, // horizon
                {0,3,6}, {1,4,7}, {2,5,8}, // vertical
                {0,4,8}, {2,4,6} // diagonal
        };
        for (int[] combo : winningCombinations) {
            int r1 = combo[0] / 3, c1 = combo[0] % 3;
            int r2 = combo[1] / 3, c2 = combo[1] % 3;
            int r3 = combo[2] / 3, c3 = combo[2] % 3;

            if (gameBoard[r1][c1] == playerSymbol &&
                gameBoard[r2][c2] == playerSymbol &&
                gameBoard[r3][c3] == playerSymbol) {
                return true;
            }
        }
        return false;
    }

    private boolean drawTest() {
        return allowableStroke.isEmpty();
    }

    public void updateBoard (char target, char newValue) {
        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (gameBoard[i][j] == target) {
                    gameBoard[i][j] = newValue;
                    return;
                }
            }
        }
    }

    private int userInput(Scanner scanner) {
        System.out.println("Enter number: ");
        while(true) {
            int inputBoardNumber = scanner.nextInt();
            for (int x : allowableStroke) {
                if (inputBoardNumber == x) {
                    return x;
                }
            }
            System.out.println("Incorrect part board, try again.");
            }
        }
}
