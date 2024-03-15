public class Weapon {
    private int spaces;
    private String name;

    public Weapon(int spaces, String name){
        this.spaces = spaces;
        this.name = name;
    }

    public String getName(){
        return name;
    }
    public int getSpaces(){
        return spaces;
    }

    public void printSound(){
        System.out.println("Pew! Pew!");
    }
}
