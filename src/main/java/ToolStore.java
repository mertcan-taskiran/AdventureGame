public class ToolStore extends NormalLoc{
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("");
        System.out.println("######## Mağazaya Hoşgeldiniz ! ########");
        boolean showMenu = true;
        while (showMenu){
            System.out.println("1 - Silahlar");
            System.out.println("2 - Zırhlar");
            System.out.println("3 - Çıkış");
            System.out.println("");
            System.out.print("Seçiminiz : ");
            int selectCase = input.nextInt();
            while (selectCase < 1 || selectCase > 3){
                System.out.print("Geçersiz değer ! Tekrar deneyiniz : ");
                selectCase = input.nextInt();
            }
            switch (selectCase){
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Mağazaya tekrar bekleriz..");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }

    public void printWeapon(){
        System.out.println("");
        System.out.println("######## Silahlar ########");
        for (Weapon w : Weapon.weapons()){
            System.out.println(w.getId() + " - " + w.getName() + " <Para : " + w.getPrice() + " , Hasar : " + w.getDamage() +">");
        }
        System.out.println("0 - Çıkış");
    }
    public void buyWeapon(){
        System.out.println("");
        System.out.print("Bir silah seçiniz : ");
        int selectWeaponId = input.nextInt();
        while (selectWeaponId < 0 || selectWeaponId > Weapon.weapons().length){
            System.out.print("Geçersiz değer ! Tekrar deneyiniz : ");
            selectWeaponId = input.nextInt();
        }

        if (selectWeaponId != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponId);
            if ( selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("");
                    System.out.println("Yetersiz Bakiye !");
                    System.out.println("");
                }else{
                    System.out.println("");
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız !");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan Bakiye : " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("");
                }
            }
        }
    }
    public void printArmor(){
        System.out.println("");
        System.out.println("######## Zırhlar ########");
        for (Armor a : Armor.armors()){
            System.out.println(a.getId() + " - " + a.getName() + " <Para : " + a.getPrice() + " , Zırh : " + a.getBlock() +">");
        }
        System.out.println("0 - Çıkış");
    }
    public void buyArmor(){
        System.out.println("");
        System.out.print("Bir zırh seçiniz : ");
        int selectArmorId = input.nextInt();
        while (selectArmorId < 0 || selectArmorId > Armor.armors().length){
            System.out.print("Geçersiz değer ! Tekrar deneyiniz : ");
            selectArmorId = input.nextInt();
        }

        if (selectArmorId != 0){
            Armor selectedArmor = Armor.getArmorObjById(selectArmorId);
            if ( selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("");
                    System.out.println("Yetersiz Bakiye !");
                    System.out.println("");
                }else{
                    System.out.println("");
                    System.out.println(selectedArmor.getName() + " zırhını satın aldınız !");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan Bakiye : " + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("");
                }
            }
        }
    }
}
