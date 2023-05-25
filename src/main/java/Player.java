import java.util.Scanner;

public class Player {
    private int damage;
    private int healthy;
    private int money;
    private String charName;
    private String name;
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    public void selectChar(){
        Samurai samurai = new Samurai();
        Knight knight = new Knight();
        Archer archer = new Archer();

        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("########### KARAKTERLER ###########");

        for (GameChar gameChar : charList){
            System.out.println(
                            "\t Id : " + gameChar.getId() +
                            "\t Karakter : " + gameChar.getName() +
                            "\t Hasar : " + gameChar.getDamage() +
                            "\t Sağlık : " + gameChar.getHealthy() +
                            "\t Para : " + gameChar.getMoney()
            );
        }
        System.out.println("");
        System.out.print("Lütfen bir karakter seç : ");
        int selectChar = input.nextInt();
        switch (selectChar){
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println("----------------------------");
    }

    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealthy(gameChar.getHealthy());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public void printInfo(){
        System.out.println(
                        "\t Silah : " + this.getInventory().getWeapon().getName() +
                        ",\t Zırh : " + this.getInventory().getArmor().getName() +
                        ",\t Blok : " + this.getInventory().getArmor().getBlock() +
                        ",\t Hasar : " + this.getDamage() +
                        ",\t Sağlık : " + this.getHealthy() +
                        ",\t Para : " + this.getMoney()
        );
    }

    public int getDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getHealthy() {
        return healthy;
    }

    public void setHealthy(int healthy) {
        this.healthy = healthy;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }
}
