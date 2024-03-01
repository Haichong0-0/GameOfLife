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

    private int dCount;
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
        dCount =0;
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
        int num = checkSurrounding();
        int nAlive = num/100;
        int nAttack = (num -nAlive*100)/10;
        int nDisease = num -nAlive*100-nAttack*10;
        setNextState(false);
        //cell behavior as requested in base task
        if (isAlive()){
            if (nDisease>1){
                setDisease(true);
            }
            if (hasDisease()){
                setAggressive(true);
                dCount++;
            }
            if (dCount>10){
                setNextState(false);
            }
            if (nAlive>3||nAlive<2){
                setNextState(false);
            }
            if (nAlive-nDisease == 2){
                setDisease(false);
                dCount =0;

            }


        }
        else{
            if (nAlive ==3){
                setNextState(true);
            }
        }


    }
}
