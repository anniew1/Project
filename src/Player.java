import java.util.Scanner;
public class Player {

    String name;
    Water[][] board = new Water[10][10];
    Water[][] shipBoard = new Water[10][10];
    Scanner scan;

    public Player(String name) {
        this.name = name;
        scan = new Scanner(System.in);
        setupBoard();
        placeShip();
    }

    //places the ships on the board
    private void placeShip() {
        System.out.println("Ship # = how much spaces (Ship 1 = 1 space long, Ship 2 = 2 spaces long, etc.)");
        for (int i = 0; i < 5; i++) {
            System.out.print("Where would you like to place ship " + (i + 1) + " (row col): ");
            String position = scan.nextLine();

            int shipRow = Integer.parseInt(position.substring(0, 1)) - 1;
            scan.nextLine();

            int shipCol = Integer.parseInt(position.substring(2, 3)) - 1;
            scan.nextLine();

            System.out.print("What direction would you want it to go (horizontal/vertical): ");
            String direction = scan.nextLine();

            if (checkValid(shipRow, shipCol, direction, i + 1)) {
                shipBoard[shipRow - 1][shipCol - 1] = new Ship("O");
            }
        }
    }

    //set up the board that the players see and the one with the ships
    private void setupBoard() {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c  < board[0].length; c++) {
                board[r][c] = new Water("_");
                shipBoard[r][c] = new Water("_");
            }
        }
    }

    //checks to see if a ship can be placed
    private Boolean checkValid(int row, int col, String direction, int length) {
        for (int i = 0; i < length; i++) {
            if (direction.equals("horizontal")) {
                if (!(shipBoard[row][col + i] instanceof Water)) {
                    return false;
                }
            } else {
                if (!(shipBoard[row + i][col] instanceof Water)) {
                    return false;
                }
            }
        }
        return true;
    }

}
