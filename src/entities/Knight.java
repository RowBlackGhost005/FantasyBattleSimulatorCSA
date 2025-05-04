package entities;

public class Knight extends Entity{

    public Knight() {
        super(100, 20, 10, 25, 10);
    }

    @Override
    public String toString() {
        return String.format("%s | Hp: %d" , "Knight", getHealth());
    }
}
