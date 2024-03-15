public class Bomb extends Weapon {
    private int inventory;
    public Bomb(int spaces, String name){
        super(spaces, name);
        inventory = 3;
    }

    public int getInventory(){
        return inventory;
    }

    public void setInventory(int num){
        inventory = num;
    }

    @Override
    public void printSound(){
        System.out.println("BOOM!");
    }

}
