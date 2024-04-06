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

    // returns the name of the player
    public String getName() {
        return name;
    }

    // prints out the given board
    public void printBoard(Water[][] board) {
        System.out.println("   1  2  3  4  5  6  7  8  9  10");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].getSymbol() + " ");
            }
            System.out.println();
        }
    }

    // checks whether a person has eliminated all ships
    public Boolean victory() {
        for (Water[] row : shipBoard) {
            for (Water col : row) {
                if (col instanceof Ship) {
                    return false;
                }
            }
        }
        System.out.println("You are the winner!!!");
        return true;
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
        for (int i = 0; i < 1; i++) {
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

            printBoard(shipBoard);
        }
    }

    //set up the board that the players see and the one with the ships
    private void setupBoard() {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c  < board[0].length; c++) {
                board[r][c] = new Water("\uD83C\uDF0A");
                shipBoard[r][c] = new Water("\uD83C\uDF0A");
            }
        }
    }

    //checks to see if a ship can be placed
    private Boolean checkValid(int row, int col, String direction, int length) {

        for (int i = 0; i < length; i++) {

            if (direction.equals("h")) {
                if (col + length > 10) {
                    return false;
                }
                if ((shipBoard[row][col + i] instanceof Ship)) {
                    return false;
                }
            } else {
                if (row + length > 10) {
                    return false;
                }
                if ((shipBoard[row + i][col] instanceof Ship)) {
                    return false;
                }
            }
        }
        return true;
    }

    public void attack(Player enemy){
        Water shipFound = new Water("\uD83D\uDD25");
        Water nothingFound = new Water("❌");
        if(bomb.getInventory() > 0) {
            System.out.println("You have " + bomb.getInventory() + " bombs remaining");
            System.out.print("Would you like to use a bomb? (y/n): ");
            String choice = scan.nextLine();
            System.out.println();

            if(choice.equals("y")){
                bomb.setInventory(bomb.getInventory() - 1);
                System.out.print("Which row would you like to hit?");
                int r = scan.nextInt() - 1;
                System.out.print("Which column would you like to hit?");
                int c = scan.nextInt() - 1;
                //implementation which changes the board accordingly
                if(c == 1){
                    for(int i = 0; i < c; i++){
                        if(shipBoard[r][i].getSymbol().equals("\uD83D\uDEA2")){
                            enemy.board[r][i] = shipFound;
                        } else {
                            enemy.board[r][i] = nothingFound;
                        }
                    }
                } else if (c == 10){
                    for(int j = 9; j >= c - 3; j--){
                        if(shipBoard[r][j].getSymbol().equals("\uD83D\uDEA2")){
                            enemy.board[r][j] = shipFound;
                        } else {
                            enemy.board[r][j] = nothingFound;
                        }
                    }
                } else {
                    for(int k = c - 1; k <= c + 1; k++){
                        if(shipBoard[r][k].getSymbol().equals("\uD83D\uDEA2")){
                            enemy.board[r][k] = shipFound;
                        } else {
                            enemy.board[r][k] = nothingFound;
                        }
                    }
                }
            } else {
                System.out.print("Which row would you like to hit?");
                int r = scan.nextInt();
                System.out.print("Which column would you like to hit?");
                int c = scan.nextInt();
                if(shipBoard[r][c].getSymbol().equals("\uD83D\uDEA2")){
                    enemy.board[r][c] = shipFound;
                } else {
                    enemy.board[r][c] = nothingFound;
                }
            }
        } else {
            System.out.print("Which row would you like to hit?");
            int r = scan.nextInt();
            System.out.print("Which column would you like to hit?");
            int c = scan.nextInt();
            if(shipBoard[r][c].getSymbol().equals("\uD83D\uDEA2")){
                enemy.board[r][c] = shipFound;
            } else {
                enemy.board[r][c] = nothingFound;
            }
        }
    }

}
