import javafx.scene.paint.Color;


public class Parasite extends Cell{

    public Parasite(Field field, Location location, Color col) {
        super(field, location, col);
        age = 0;
        super.myType = CellType.Parasite;
    }

    public Parasite(Cell cell, Location location) {
        super(cell,location);
        age = 0;
        super.myType = CellType.Parasite;
    }

    @Override
    public void act() {
        age++;
        int alive = checkSurrounding()/10;
        int attack = checkSurrounding()%10;
        setNextState(true);
        
    }


}
