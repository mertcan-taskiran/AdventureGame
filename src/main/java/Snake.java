import java.util.Random;

public class Snake extends Monster{
    private int randomDamage = new Random().nextInt(2, 6) +1;
    public Snake() {
        super(4, "Yılan", 3, 12, 0);
        this.setDamage(randomDamage);
    }
}
