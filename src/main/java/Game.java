import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.println("============================");
        System.out.println("Bir isim giriniz : ");
        //String playerName = input.nextLine();
        //Player player = new Player(playerName);
        Player player = new Player("Mertcan");
        System.out.println("Merhaba " + player.getName() + " karanlık ve sisli adaya hoşgeldin !");

        player.selectChar();

    }

}
