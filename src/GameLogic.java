import java.util.Scanner;
public class GameLogic {

    Scanner scan;
    Player p1;
    Player p2;

    public GameLogic() {
        scan = new Scanner(System.in);
    }
    public void startGame() {
        System.out.print("Player 1's Name: ");
        p1 = new Player(scan.nextLine());

        System.out.print("Player 2's Name: ");
        p2 = new Player(scan.nextLine());
    }
}
