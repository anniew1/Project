import java.util.Scanner;
public class Player {

    String name;
    Water[][] board = new Water[10][10];
    Scanner scan;

    public Player(String name) {
        this.name = name;
        scan = new Scanner(System.in);
        setupBoard();
        placeShip();
    }

    //places the ships on the board
    public void placeShip() {
        for (int i = 0; i < 5; i++) {
            System.out.print("Where would you like to place ship " + i + 1 + " (row col): ");
            String position = scan.nextLine();
            int shipRow = Integer.parseInt(position.substring(0, 1));
            int shipCol = Integer.parseInt(position.substring(2, 3));
            board[shipRow][shipCol] = new Ship("O");
        }
    }

    //set up the board
    private void setupBoard() {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c  < board[0].length; c++) {
                board[r][c] = new Water("_");
            }
        }
    }

}
