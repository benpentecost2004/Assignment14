import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {

    private static final int BOARD_SIZE = 5;
    private String currentPlayer = "X";
    private boolean gameOver = false;

    @Override
    public void start(Stage primaryStage) {
        Button[][] buttons = new Button[BOARD_SIZE][BOARD_SIZE];
        GridPane grid = new GridPane();

        for (int row = 0; row < BOARD_SIZE; row++) {
            final int finalRow = row;
            for (int col = 0; col < BOARD_SIZE; col++) {
                final int finalCol = col;
                Button button = new Button();
                button.setPrefSize(60, 60);
                button.setOnAction(event -> {
                    if (!gameOver && button.getText().isEmpty()) {
                        button.setText(currentPlayer);
                        if (checkForWin(buttons, finalRow, finalCol)) {
                            gameOver = true;
                            System.out.println(currentPlayer + " wins!");
                        } else if (checkForDraw(buttons)) {
                            gameOver = true;
                            System.out.println("It's a draw!");
                        } else {
                            currentPlayer = (currentPlayer == "X") ? "O" : "X";
                        }
                    }
                });
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }

        primaryStage.setScene(new Scene(grid, 350, 350));
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }

    private boolean checkForWin(Button[][] buttons, int row, int col) {
        String symbol = buttons[row][col].getText();
        boolean rowWin = true, colWin = true, diagonalWin = true, antiDiagonalWin = true;

        for (int i = 0; i < BOARD_SIZE; i++) {
            if (!buttons[row][i].getText().equals(symbol)) {
                rowWin = false;
            }
            if (!buttons[i][col].getText().equals(symbol)) {
                colWin = false;
            }
            if (!buttons[i][i].getText().equals(symbol)) {
                diagonalWin = false;
            }
            if (!buttons[i][BOARD_SIZE - 1 - i].getText().equals(symbol)) {
                antiDiagonalWin = false;
            }
        }

        return rowWin || colWin || diagonalWin || antiDiagonalWin;
    }

    private boolean checkForDraw(Button[][] buttons) {
        for (Button[] row : buttons) {
            for (Button button : row) {
                if (button.getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


