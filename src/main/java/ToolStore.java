public class ToolStore extends NormalLoc{
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation() {
        System.out.println("######## Mağazaya Hoşgeldiniz ! ########");
        System.out.println("1 - Silahlar");
        System.out.println("2 - Zırhlar");
        System.out.println("3 - Çıkış");
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
                break;
            case 3:
                System.out.println("Mağazaya tekrar bekleriz..");
                return true;
        }
        return true;
    }

    public void printWeapon(){
        System.out.println("### Silahlar ###");
        for (Weapon w : Weapon.weapons()){
            System.out.println(w.getId() + " - " + w.getName() + " <Para : " + w.getPrice() + " , Hasar : " + w.getDamage() +">");
        }
    }

    public void buyWeapon(){
        System.out.print("Bir silah seçiniz : ");
        int selectWeaponId = input.nextInt();
        while (selectWeaponId < 1 || selectWeaponId > Weapon.weapons().length){
            System.out.print("Geçersiz değer ! Tekrar deneyiniz : ");
            selectWeaponId = input.nextInt();
        }

        Weapon selectedWeapon = Weapon.getWeaponObjById(selectWeaponId);
        if ( selectedWeapon!= null) {
            if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                System.out.println("Yetersiz Bakiye !");
            }else{
                System.out.println(selectedWeapon.getName() + " silahını satın aldınız !");
                int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                this.getPlayer().setMoney(balance);
                System.out.println("Kalan Paranız : " + this.getPlayer().getMoney());
                this.getPlayer().getInventory().setWeapon(selectedWeapon);
            }
        }
    }
    public void printArmor(){
        System.out.println("Zırhlar");
    }
}
