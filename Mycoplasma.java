import javafx.scene.paint.Color; 
import java.util.List;

/**
 * Simplest form of life.
 * Fun Fact: Mycoplasma are one of the simplest forms of life.  A type of
 * bacteria, they only have 500-1000 genes! For comparison, fruit flies have
 * about 14,000 genes.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public class Mycoplasma extends Cell {

    /**
     * Create a new Mycoplasma.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Mycoplasma(Field field, Location location, Color col) {
        super(field, location, col);
    }

    /**
    * This is how the Mycoplasma decides if it's alive or not
    */
    public void act() {
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        List<Location> locations = getField().adjacentLocations(getLocation());
        //setNextState(false);
        int alive = 0;
        int died = 0;
        for (Location i : locations){
            if(getField().getObjectAt(i).isAlive()){
                alive++;
            }
            else{
                died++;
            }
        }
        if(alive<2||alive>3){
            setNextState(false);
        }
        else{
            if 
        }
        
        if (isAlive()) {
            if (neighbours.size() > 1)
                setNextState(true);
        }
    }
}
