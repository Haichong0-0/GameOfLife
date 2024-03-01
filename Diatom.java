import javafx.scene.paint.Color;

import java.util.Random;

public class Diatom extends Cell{
    private Random rand;
    public Diatom(Field field, Location location, Color col) {
        super(field, location, col);
        setAggressive(false);
        setDisease(false);
        super.myType = CellType.Diatom;
        rand = new Random();
        setContagious(false);
    }

    public Diatom(Cell cell,Location location){
        super(cell, location);
        setAggressive(false);
        super.myType = CellType.Diatom;
        rand = new Random();
        setContagious(false);
    }

    @Override
    public void act() {
        age++;
        int num = checkSurrounding();
        int nAlive = num/100;
        int nAttack = (num -nAlive*100)/10;
        int nDisease = num -nAlive*100-nAttack*10;
        double random = rand.nextDouble();
        setAggressive(false);
        setReproduce(random>0.88);
        setNextState(true);

        //age limit
        if (age>12){
            setNextState(false);
        }
        if (!isAlive()){
            setReproduce(false);
            setNextState(false);
            setAggressive(false);
            setContagious(false);
        }
        if (nAttack>=1){
            setNextState(false);
        }
        if (nDisease>0){
            setDisease(true);
            setColor(Color.DARKGREEN);
        }
        if (nAlive-nDisease>1){
            setDisease(false);
            setColor(Color.LIGHTGREEN);
        }
        if (hasDisease()){
            random = rand.nextDouble();
            setAggressive(random>0.5);
        }

    }
}
