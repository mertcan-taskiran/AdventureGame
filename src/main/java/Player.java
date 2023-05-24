import java.util.Scanner;

public class Player {
    private int damage;
    private int healthy;
    private int money;
    private String charName;
    private String name;
    private Scanner input = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
    }

    public void selectChar(){
        Samurai samurai = new Samurai();
        Knight knight = new Knight();
        Archer archer = new Archer();

        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("--------KARAKTERLER---------");

        for (GameChar gameChar : charList){
            System.out.println(
                            "\t Id : " + gameChar.getId() +
                            "\t Karakter : " + gameChar.getName() +
                            "\t Hasar : " + gameChar.getDamage() +
                            "\t Sağlık : " + gameChar.getHealthy() +
                            "\t Para : " + gameChar.getMoney()
            );
        }
        System.out.println("----------------------------");
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
        System.out.println("Seçilen Karakter => " + this.getCharName() + "\t Karakterin Özellikleri => Hasar : " + this.getDamage() + ", Sağlık : " + this.getHealthy() + ", Para : " + this.getMoney() );
    }

    public void initPlayer(GameChar gameChar){
        this.setDamage(gameChar.getDamage());
        this.setHealthy(gameChar.getHealthy());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public int getDamage() {
        return damage;
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
}
