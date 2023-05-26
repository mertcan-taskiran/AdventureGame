import java.util.Random;

public abstract class BattleLoc extends Location{
    private Monster monster;
    private String award;
    private int maxMonster;
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

    public boolean combat(int monNumber){
        for (int i = 0; i <= monNumber; i++){
            this.getMonster().setHealth(this.getMonster().getHealth());
            playerStats();
            monsterStats(i);
            while (this.getPlayer().getHealthy() > 0 && this.getMonster().getHealth() > 0) {
                System.out.println("<V>ur veya <K>aç : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V"))
                {
                    System.out.println("Siz vurdunuz !");
                    this.getMonster().setHealth(this.getMonster().getHealth()-this.getPlayer().getTotalDamage());
                    afterHit();
                    if (this.getMonster().getHealth() > 0){
                        System.out.println("Canavar size vurdu !");
                        int monsterDamage = this.getMonster().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (monsterDamage < 0){
                            monsterDamage = 0;
                        }
                        this.getPlayer().setHealthy(this.getPlayer().getHealthy() - monsterDamage);
                    }
                }
            }

            if (this.getMonster().getHealth() < this.getPlayer().getHealthy()){
                System.out.println("Bu bölgedeki tüm " +this.getMonster().getName()+" canavarlarını yendiniz !");
                System.out.println("Tebrikler " + this.getMonster().getAward() + " para kazandınız !");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getMonster().getAward());
                System.out.println("Güncel Bakiyeniz : " + this.getPlayer().getMoney());
            }else {
                return false;
            }
        }
        return true;
    }

    public void afterHit(){
        System.out.println("Sağlığınız : " + this.getPlayer().getHealthy());
        System.out.println(this.getMonster().getName() + "Sağlığı : " + this.getMonster().getHealth());
        System.out.println("------------------");
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
        Random r = new Random();
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
