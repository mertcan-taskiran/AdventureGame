import java.util.Random;

public abstract class BattleLoc extends Location{
    private Monster monster;
    private String award;
    private int maxMonster;
    private Random r  = new Random();
    public BattleLoc(Player player, String name, Monster monster, String award, int maxMonster) {
        super(player, name);
        this.monster = monster;
        this.award = award;
        this.maxMonster = maxMonster;
    }

    @Override
    public boolean onLocation() {
        int monNumber = this.randomMonsterNumber();
        System.out.println("");
        System.out.println("###### " +this.getName()+" ######");
        System.out.println("Şuan Buradasın : " + this.getName());
        System.out.println("Dikkatli Ol ! Burada " + monNumber + " tane " + this.getMonster().getName() + " yaşıyor !");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine().toUpperCase();

        if (selectCase.equals("S") && combat(monNumber)){
            System.out.println(this.getName() + " bölgesinde bulunan tüm " + this.getMonster().getName() + " canavarlarını yendiniz !");
            return true;
        }
        if (this.getPlayer().getHealthy() <= 0){
            System.out.println("Öldünüz !");
            return false;
        }
        return true;
    }

    public boolean isStart(){
        return r.nextBoolean();
    }

    public boolean combat(int monNumber){
        for (int i = 1; i <= monNumber; i++) {
            boolean rnd = isStart();
            this.getMonster().setHealth(this.getMonster().getOrjinalHealth());
            playerStats();
            monsterStats(i);

            if (rnd) {
                System.out.println("Savaşa " + this.getMonster().getName() + " başlayacak ! ");
                System.out.print("<V>ur ya da <K>aç : ");
                String selectCase = input.nextLine().toUpperCase();
                if (selectCase.equals("K")) {
                    return false;
                }
            } else {
                System.out.println("Savaşa siz başlayacaksınız !");
            }

            while (this.getPlayer().getHealthy() > 0 && this.getMonster().getHealth() > 0) {

                if (rnd) {
                    monsterAttack();
                    System.out.println(this.getMonster().getName() + " size vurdu !");
                    this.getPlayer().setHealthy(this.getPlayer().getHealthy() - monsterAttack());
                    afterHit();

                    if (this.getPlayer().getHealthy() > 0) {
                        System.out.println("<V>ur ya da <K>aç");
                        String selectCase = input.nextLine().toUpperCase();

                        if (selectCase.equals("V")) {
                            playerAttack();
                            afterHit();
                        } else {
                            return true;
                        }
                    }
                } else {
                    System.out.println("<V>ur ya da <K>aç");
                    String selectCase = input.nextLine().toUpperCase();

                    if (selectCase.equals("V")) {
                        playerAttack();
                        afterHit();
                        if (this.getMonster().getHealth() > 0) {
                            monsterAttack();
                            System.out.println(this.getMonster().getName() + " size vurdu!");
                            this.getPlayer().setHealthy(this.getPlayer().getHealthy() - monsterAttack());
                            afterHit();
                        }
                    } else {
                        return true;
                    }
                }
            }
        }

        if(this.getMonster().getHealth() < this.getPlayer().getHealthy() && !(this.getName().equals("Maden"))){
            System.out.println("Düşmanı yendiniz.");
            System.out.println(this.getMonster().getAward()+ " para kazandınız.");
            this.getPlayer().setMoney(
                    this.getPlayer().getMoney() + this.getMonster().getAward()
            );
            System.out.println("Güncel Bakiye : "+this.getPlayer().getMoney());
        }

        else if(this.getMonster().getHealth() < this.getPlayer().getHealthy() && this.getName().equals("Maden")){
            Mine mine = new Mine(getPlayer());
            System.out.println("Düşmanı yendiniz");
            mine.itemDrop(getPlayer());
            System.out.println();
        }
        else{
            return false;
        }

        if(this.getName() == "Mağara"){
            this.getPlayer().getInventory().setFood(true);
        }

        if(this.getName() == "Orman"){
            this.getPlayer().getInventory().setFirewood(true);
        }

        if(this.getName() == "Nehir"){
            this.getPlayer().getInventory().setWater(true);
        }

        return true;
    }

    public void playerAttack(){
        System.out.println("Siz vurdunuz !");
        this.getMonster().setHealth(this.getMonster().getHealth() - this.getPlayer().getTotalDamage());
    }

    public int monsterAttack(){
        return  this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();

    }

    public void afterHit(){
        System.out.println("Sağlığınız : " + this.getPlayer().getHealthy());
        System.out.println(this.getMonster().getName() + "Sağlığı : " + this.getMonster().getHealth());
        System.out.println("----------------------------");
    }

    public void playerStats(){
        System.out.println("Oyuncu Değerleri =>" +
                "\tSağlık: " +this.getPlayer().getHealthy() +
                "\tSilah: " +this.getPlayer().getInventory().getWeapon().getName() +
                "\tHasar: " + this.getPlayer().getDamage() +
                "\tZırh: " + this.getPlayer().getInventory().getArmor().getName() +
                "\tBloklama: " + this.getPlayer().getInventory().getArmor().getBlock() +
                "\tPara: " + this.getPlayer().getMoney());
    }

    public void monsterStats(int i){
        System.out.println(i + ". " + this.getMonster().getName() + " Değerleri => " +
                        "\tSağlık: " + this.getMonster().getHealth() +
                        "\tHasar: " + this.getMonster().getDamage() +
                        "\tÖdül: " + this.getMonster().getAward());
    }

    public int randomMonsterNumber(){
        return r.nextInt(this.getMaxMonster()) + 1;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }
}
