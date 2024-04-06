import java.util.Scanner;
public class GameLogic {

    Scanner scan;
    Player p1;
    Player p2;
    Boolean gameOver;

    public GameLogic() {
        scan = new Scanner(System.in);
        gameOver = false;
    }
    public void startGame() {
        System.out.println("Basic Info: \n-A bomb hits 3 random spots in a row\n-A normal hit affects 1 spot\n-If you attack a spot more than once, nothing happens");
        System.out.println();
        System.out.print("Player 1's Name: ");
        p1 = new Player(scan.nextLine());

        System.out.print("Player 2's Name: ");
        p2 = new Player(scan.nextLine());

        while (!gameOver) {
            takeTurn(p1, p2);
            takeTurn(p2, p1);
        }

    }

    // prints out and does the actions for a player to attack
    private void takeTurn(Player current, Player enemy) {
        System.out.println(current.getName() + "'s Turn: ");
        System.out.println("Enemy's Board: ");
        enemy.printBoard(enemy.board);
        current.attack(enemy);
        enemy.printBoard(enemy.board);
    }

}
