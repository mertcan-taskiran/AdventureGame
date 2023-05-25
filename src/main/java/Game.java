import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start(){
        System.out.println("########### Macera Oyununa Hoşgeldiniz ! ###########");
        System.out.println("Bir isim giriniz : ");
        //String playerName = input.nextLine();
        //Player player = new Player(playerName);
        Player player = new Player("Mertcan");
        System.out.println("Merhaba " + player.getName() + " karanlık ve sisli adaya hoşgeldin !");

        player.selectChar();

        Location location = null;
        while (true){
            player.printInfo();
            System.out.println("");
            System.out.println("########### Bölgeler ###########");
            System.out.println("1 - Güvenli Ev => Burası güvenli bölge. Düşman yok !");
            System.out.println("2 - Silahçı => Silah veya zırh satın alabilirsiniz !");
            System.out.println("3 - Mağara => Ödül <Yemek>, dikkatli ol. Zombi çıkabilir !");
            System.out.println("4 - Orman => Ödül <Odun>, dikkatli ol. Vampir çıkabilir !");
            System.out.println("5 - Nehir => Ödül <Su>, dikkatli ol. Ayı çıkabilir !");
            System.out.println("0 - Çıkış => Oyunu sonlandır !");
            System.out.println("");
            System.out.print("Lütfen gitmek istediğiniz bölgeyi seçiniz : ");
            int selectLoc = input.nextInt();
            switch (selectLoc){
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge seçiniz !");
            }

            if (location == null){
                System.out.println("Karanlık ve sisli adada çabuk pes ettin !");
                break;
            }
            if (!location.onLocation()){
                System.out.println("Game Over!");
                break;
            }
        }


    }

}
