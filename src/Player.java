import java.util.Scanner;
public class Player {

    String name;
    Water[][] board = new Water[10][10];
    Water[][] shipBoard = new Water[10][10];
    Scanner scan;

    Weapon weapon;
    Bomb bomb;

    public Player(String name) {
        this.name = name;
        scan = new Scanner(System.in);
        setupBoard();
        placeShip();
        weapon = new Weapon(1, "weapon");
        bomb = new Bomb(3, "bomb");
    }

    // prints out the board with the ships
    public void printShipBoard() {
        for (Water[] row : shipBoard) {
            for (Water col : row) {
                System.out.print(col.getSymbol());
            }
            System.out.println();
        }
    }

    // places the ships
    private void addShips(int shipRow, int shipCol, String direction, int length) {
        for (int i = 0; i < length; i++) {
            if (direction.equals("vertical")) {
                shipBoard[shipRow + i][shipCol] = new Ship("0");
            } else {
                shipBoard[shipRow][shipCol + i] = new Ship("0");
            }
        }
    }


    //asks the player to place ships
    private void placeShip() {
        System.out.println("Ship # = how much spaces (Ship 1 = 1 space long, Ship 2 = 2 spaces long, etc.)");
        for (int i = 0; i < 5; i++) {
            System.out.print("Where would you like to place ship " + (i + 1) + " (row): ");
            int shipRow = Integer.parseInt(scan.nextLine()) - 1;

            System.out.print("Where would you like to place ship " + (i + 1) + " (col): ");
            int shipCol = Integer.parseInt(scan.nextLine()) - 1;

            System.out.print("What direction would you want it to go (horizontal/vertical): ");
            String direction = scan.nextLine();

            if (checkValid(shipRow, shipCol, direction, i + 1)) {
                addShips(shipRow, shipCol, direction, i + 1);
            } else {
                System.out.println("Ship can't be placed there");
                i--;
            }

            printShipBoard();
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
                if ((shipBoard[row][col + i] instanceof Ship)) {
                    return false;
                }
            } else {
                if ((shipBoard[row + i][col] instanceof Ship)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void attack(){
        if(bomb.getInventory() > 0) {
            System.out.println("You have " + bomb.getInventory() + " bombs remaining");
            System.out.print("Would you like to use a bomb? (y/n) ");
            String choice = scan.nextLine();
            System.out.println();
            if(choice.equals("y")){
                System.out.print("Which row would you like to hit?");
                int r = scan.nextInt();
                System.out.print("Which column would you like to hit?");
                int c = scan.nextInt();
            } else {

            }
        } else {

        }
    }

}
