import javafx.scene.paint.Color;

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
        setAggressive(false);
        setDisease(false);
        super.myType = CellType.Mycoplasma;
    }

    public Mycoplasma(Cell cell, Location location) {
        super(cell,location);
        setAggressive(false);
        setDisease(false);
        super.myType = CellType.Mycoplasma;
    }


    /**
    * This is how the Mycoplasma decides if it's alive or not
    */
    public void act() {
        int alive = checkSurrounding()/10;
        int attack = checkSurrounding()%10;
        setNextState(false);
        if (isAlive()){
            if(alive==2||alive==3){
                setNextState(true);
            }
        }
        else{
            if (alive ==3){
                setNextState(true);
            }
        }


        if(attack !=0){
            setNextState(false);
        }

    }
}
