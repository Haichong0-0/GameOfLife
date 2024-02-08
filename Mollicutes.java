import javafx.scene.paint.Color;
import java.util.List;


public class Mollicutes extends Cell{
    private int generation;
    public Mollicutes(Field field, Location location, Color col) {
        super(field, location, col);
        generation = 0;
    }

    @Override
    public void act() {
        generation++;
        Location location = getLocation();
        
        if(isAlive())
        {
            setNextState(true);
        }
        
        if(generation%2==0){
            setColor(Color.RED);
        
        }
        else
        {
            setColor(Color.ORANGE);
        }
    }


}
