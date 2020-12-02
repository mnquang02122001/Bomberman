package uet.oop.bomberman.AI;

import java.util.Random;

public class AI {
    protected Random random = new Random();
    public int calculateDir(){
        return random.nextInt(4);
    }
}
