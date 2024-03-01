import javafx.scene.paint.Color;


public class Amoeba extends Cell{

    private int dcount;
    public Amoeba(Field field, Location location, Color col) {
        super(field, location, col);
        age = 0;
        setAggressive(false);
        setDisease(false);
        myType = CellType.Amoeba;
    }
    
    public Amoeba(Cell cell, Location location) {
        super(cell , location);
        setAggressive(false);
        setDisease(false);
        myType = CellType.Amoeba;
    }
    
    
    public void act() {
        age++;
        setAggressive(true);
        setReproduce(false);
        if (age<50&&isAlive()){
            setNextState(true);
            int num = checkSurrounding();
            int nAlive = num/100;
            int nAttack = (num -nAlive*100)/10;
            int nDisease = num -nAlive*100-nAttack*10;
            nAlive = nAlive/10;
            switch (nAttack){
                case 8,7,6,5,4,3:
                    dcount++;
                case 2,1:
                    setAggressive(true);
                    dcount =0;
                    break;
                case 0:
                    setAggressive(false);
                    dcount = 0;
            }
            if (dcount>3){
                setNextState(false);

            }
            if (nDisease>0){
                setDisease(true);
            }
            if (age>40){
                setReproduce(true);
            }
        }
        else{
            setNextState(false);
        }
        if(hasDisease()){
            setColor(Color.LIGHTBLUE);
            if(age>30){
                setNextState(false);
                System.out.println("Amoeba dies at age "+age);
            }
            setAggressive(false);
            setContagious(true);
        }
        if (age<3){setNextState(true);}


    }



}
