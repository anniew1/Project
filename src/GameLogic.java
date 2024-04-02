import java.util.Scanner;
public class GameLogic {

    Scanner scan;
    Player p1;
    Player p2;

    public GameLogic() {
        scan = new Scanner(System.in);
    }
    public void startGame() {
        System.out.println("Basic Info: \n-A bomb hits 3 random spots in a row\n-A normal hit affects 1 spot\n-If you attack a spot more than once, nothing happens");
        System.out.println();
        System.out.print("Player 1's Name: ");
        p1 = new Player(scan.nextLine());

        System.out.print("Player 2's Name: ");
        p2 = new Player(scan.nextLine());

    }
}
