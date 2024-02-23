import javafx.scene.paint.Color;


public class Mollicutes extends Cell{

    public Mollicutes(Field field, Location location, Color col) {
        super(field, location, col);
        age = 0;
        super.myType = CellType.Mollicutes;
    }

    public Mollicutes(Cell cell, Location location) {
        super(cell,location);
        age = 0;
        super.myType = CellType.Mollicutes;
    }

    @Override
    public void act() {
        age++;
        Location location = getLocation();

        if(isAlive())
        {
            setNextState(true);
        }

        if(age%2==0){
            setColor(Color.RED);

        }
        else
        {
            setColor(Color.ORANGE);
        }
    }


}
