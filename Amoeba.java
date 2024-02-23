import javafx.scene.paint.Color;


public class Amoeba extends Cell{


    public Amoeba(Field field, Location location, Color col) {
        super(field, location, col);
        age = 0;
        setAggressive(false);
        setDisease(false);
        myType = CellType.Amoeba;
    }

    public Amoeba(Cell cell, Location location) {
        super(cell , location);
        age = 0;
        setAggressive(false);
        setDisease(false);
        myType = CellType.Amoeba;
    }


    public void act() {
        age++;
        setNextState(true);
        if(!willAttack()&&age>10){
            setAggressive(true);
        }
        if(age>20){
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
