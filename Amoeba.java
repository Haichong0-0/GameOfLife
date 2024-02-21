import javafx.scene.paint.Color;


public class Amoeba extends Cell{
    private int generation;
    public Amoeba(Field field, Location location, Color col) {
        super(field, location, col);
        generation = 0;
        setAggressive(false);
        setDisease(false);
    }


    public void act() {
        generation++;
        setNextState(true);
        if(!willAttack()&&generation>10){
            setAggressive(true);
        }
        if(generation>20){
            setNextState(false);
        }
        int nAlive = checkSurrounding();
        int nAttack = nAlive%10;
        nAlive = nAlive/10;
        if (!isAlive()){
            if (nAlive ==8){
                setNextState(true);
            }
        }
    }


}
