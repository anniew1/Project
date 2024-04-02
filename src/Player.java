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
            if (direction.equals("v")) {
                shipBoard[shipRow + i][shipCol] = new Ship("\uD83D\uDEA2");
            } else {
                shipBoard[shipRow][shipCol + i] = new Ship("\uD83D\uDEA2");
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

            System.out.print("What direction would you want it to go (h/v): ");
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
                board[r][c] = new Water("\uD83D\uDCA7");
                shipBoard[r][c] = new Water("\uD83D\uDCA7");
            }
        }
    }

    //checks to see if a ship can be placed
    private Boolean checkValid(int row, int col, String direction, int length) {

        for (int i = 0; i < length; i++) {
            if (row > 10 || col > 10) {
                return false;
            }

            if (direction.equals("h")) {
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
        Water shipFound = new Water("\uD83D\uDEA2");
        Water nothingFound = new Water("❌");
        if(bomb.getInventory() > 0) {
            System.out.println("You have " + bomb.getInventory() + " bombs remaining");
            System.out.print("Would you like to use a bomb? (y/n) ");
            String choice = scan.nextLine();
            System.out.println();
            if(choice.equals("y")){
                bomb.setInventory(bomb.getInventory() - 1);
                System.out.print("Which row would you like to hit?");
                int r = scan.nextInt();
                System.out.print("Which column would you like to hit?");
                int c = scan.nextInt();
                //implementation which changes the board accordingly
                if(c == 1){
                    for(int i = 0; i < c; i++){
                        if(shipBoard[r][i].getSymbol().equals("\uD83D\uDEA2")){
                            board[r][i] = shipFound;
                        } else {
                            board[r][i] = nothingFound;
                        }
                    }
                } else if (c == 10){
                    for(int j = 9; j >= c - 3; j--){
                        if(shipBoard[r][j].getSymbol().equals("\uD83D\uDEA2")){
                            board[r][j] = shipFound;
                        } else {
                            board[r][j] = nothingFound;
                        }
                    }
                } else {
                    for(int k = c - 1; k <= c + 1; k++){
                        if(shipBoard[r][k].getSymbol().equals("\uD83D\uDEA2")){
                            board[r][k] = shipFound;
                        } else {
                            board[r][k] = nothingFound;
                        }
                    }
                }
            } else {
                System.out.print("Which row would you like to hit?");
                int r = scan.nextInt();
                System.out.print("Which column would you like to hit?");
                int c = scan.nextInt();
                if(shipBoard[r][c].getSymbol().equals("\uD83D\uDEA2")){
                    board[r][c] = shipFound;
                } else {
                    board[r][c] = nothingFound;
                }
            }
        } else {
            System.out.print("Which row would you like to hit?");
            int r = scan.nextInt();
            System.out.print("Which column would you like to hit?");
            int c = scan.nextInt();
            if(shipBoard[r][c].getSymbol().equals("\uD83D\uDEA2")){
                board[r][c] = shipFound;
            } else {
                board[r][c] = nothingFound;
            }

        }
    }

}
