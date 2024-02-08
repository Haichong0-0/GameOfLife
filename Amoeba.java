import javafx.scene.paint.Color;
import java.util.List;


public class Amoeba extends Cell{
    private int generation;
    public Amoeba(Field field, Location location, Color col) {
        super(field, location, col);
        generation = 0;
    }

    @Override
    public void act() {
        generation++;
        Location location = getLocation();
        if(generation>100){


        }
    }


}
