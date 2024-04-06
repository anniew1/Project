import java.util.Scanner;
public class Player {

    String name;
    Water[][] board = new Water[10][10];
    Water[][] shipBoard = new Water[10][10];
    Scanner scan;

    Weapon weapon;
    Bomb bomb;
    public boolean found = false;
    public int bombLeft;

    public Player(String name) {
        this.name = name;
        scan = new Scanner(System.in);
        setupBoard();
        placeShip();
        weapon = new Weapon(1, "weapon");
        bomb = new Bomb(3, "bomb");
        bombLeft = bomb.getInventory();
    }

    // returns the name of the player
    public String getName() {
        return name;
    }

    public int getBombLeft(){
        return bombLeft;
    }

    public Boolean getFound(){
        return found;
    }

    public void setFound(boolean b){
        found = b;
    }

    public void setBombLeft(int num){
        bombLeft = num;
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


    public void attack(Player other){
        Water shipFound = new Water("\uD83D\uDD25");
        Water nothingFound = new Water("❌");
        System.out.println("You have " + other.getBombLeft() + " bombs remaining");
        if(other.getBombLeft() > 0) {
            System.out.print("Would you like to use a bomb? (y/n): ");
            String choice = scan.nextLine();
            System.out.println();

            if(choice.equals("y")){
                other.setBombLeft(other.getBombLeft() - 1);
                //bomb.setInventory(bomb.getInventory() - 1);
                System.out.print("Would you like to wipe out a row or column? (r or c) ");
                String wipeOut = scan.nextLine();
                //option yes
                if(wipeOut.equals("r")){
                    System.out.print("Which row would you like to hit? ");
                    int r = scan.nextInt() - 1;
                    scan.nextLine();
                    for(int i = 0; i < board[0].length; i++){
                        if(shipBoard[r][i].getSymbol().equals("\uD83D\uDEA2")){
                            board[r][i] = shipFound;
                            other.setFound(true);
                        } else {
                            board[r][i] = nothingFound;
                        }
                    }
                }
                if (wipeOut.equals("c")){
                    System.out.print("Which column would you like to hit? ");
                    int c = scan.nextInt() - 1;
                    scan.nextLine();
                    for(int j = 0; j < 10; j++){
                        if(shipBoard[j][c].getSymbol().equals("\uD83D\uDEA2")){
                            board[j][c] = shipFound;
                            other.setFound(true);
                        } else {
                            board[j][c] = nothingFound;
                        }
                    }
                }
                bomb.printSound();

                //option no
            } else {
                System.out.print("Which row would you like to hit? ");
                int r = scan.nextInt();
                scan.nextLine();
                System.out.print("Which column would you like to hit? ");
                int c = scan.nextInt();
                scan.nextLine();
                if(shipBoard[r-1][c-1].getSymbol().equals("\uD83D\uDEA2")){
                    board[r-1][c-1] = shipFound;
                    other.setFound(true);
                } else {
                    board[r-1][c-1] = nothingFound;
                }
                weapon.printSound();
            }
        } else {
            System.out.print("Which row would you like to hit? ");
            int r = scan.nextInt();
            scan.nextLine();
            System.out.print("Which column would you like to hit? ");
            int c = scan.nextInt();
            scan.nextLine();
            if(shipBoard[r-1][c-1].getSymbol().equals("\uD83D\uDEA2")){
                board[r-1][c-1] = shipFound;
                other.setFound(true);
            } else {
                board[r-1][c-1] = nothingFound;
            }
            weapon.printSound();
        }

    }

}
