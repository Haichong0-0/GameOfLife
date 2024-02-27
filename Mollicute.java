import javafx.scene.paint.Color;


public class Mollicute extends Cell{

    public Mollicute(Field field, Location location, Color col) {
        super(field, location, col);
        age = 0;
        super.myType = CellType.Mollicutes;
    }

    public Mollicute(Cell cell, Location location) {
        super(cell,location);
        age = 0;
        super.myType = CellType.Mollicutes;
    }

    @Override
    public void act() {
        age++;
        int alive = checkSurrounding()/10;
        int attack = checkSurrounding()%10;
        setNextState(false);

        if(isAlive())
        {
            setNextState(true);
        }

        if(age%2==0){
            setColor(Color.RED);

        }
        else
        {
            setColor(Color.GREEN);
        }
    }


}
