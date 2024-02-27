import javafx.scene.paint.Color;

import java.util.List;

/**
 * A class representing the shared characteristics of all forms of life
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2022.01.06
 */

public abstract class Cell {

    private boolean alive;    
    private boolean nextAlive; // The state of the cell in the next iteration
    private boolean aggressive, disease,reproduce;
    private Field field;
    private Location location;
    private Color color = Color.WHITE;
    protected CellType myType = null;
    protected int age;

    /**
     * Create a new cell at location in field.
     *
     * @param field The field currently occupied.
     * @param location The location within the field.
     */
    public Cell(Field field, Location location, Color col) {
        alive = true;
        nextAlive = false;
        this.field = field;
        setLocation(location);
        setColor(col);
        age =0;
    }

    public Cell(Cell cell,Location location){
        this.alive = cell.alive;
        this.nextAlive = true;
        this.aggressive = cell.aggressive;
        this.disease = cell.disease;
        this.reproduce = cell.reproduce;
        this.field = cell.field;
        this.color = cell.color;
        this.location = location;
        this.myType = cell.myType;
        age =0;

    }

    /**
     * Make this cell act - that is: the cell decides it's status in the
     * next generation.
     */
    abstract public void act();

    /**
     * Check whether the cell is alive or not.
     * @return true if the cell is still alive.
     */
    protected boolean isAlive() {
        return alive;
    }

    /**
     * Indicate that the cell is no longer alive.
     */
    protected void setDead() {
        alive = false;
    }

    /**
     * Indicate that the cell will be alive or dead in the next generation.
     */
    public void setNextState(boolean value) {
        nextAlive = value;
    }

    /**
     * Changes the state of the cell
     */
    public void updateState() {
        alive = nextAlive;
    }

    /**
     * Changes the color of the cell
     */
    public void setColor(Color col) {
        color = col;
    }

    /**
     * Returns the cell's color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Return the cell's location.
     * @return The cell's location.
     */
    protected Location getLocation() {
        return location;
    }

    /**
     * Place the cell at the new location in the given field.
     * @param location The cell's location.
     */
    protected void setLocation(Location location) {
        this.location = location;
        field.place(this, location);
    }
    /**
     * Return the cell's field.
     * @return The cell's field.
     */
    protected Field getField() {
        return field;
    }
    /*
    Set the disease condition of the cell
     */
    protected void setDisease(Boolean T){disease = T;}

    protected boolean hasDisease(){return disease;}

    /*
    Set if the Cell attacks
     */
    protected void setAggressive(Boolean T){
        aggressive = T;}
    protected boolean willAttack(){return aggressive;}

    
    /*
    returns a 2 digit integer
    The first digit represents the living cells around it
    The second digit represents the cells will attack
     */
    protected int checkSurrounding(){
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        List<Location> locations = getField().adjacentLocations(getLocation());
        int alive = 0;
        int attack = 0;
        for (Location i : locations){
            if(getField().getObjectAt(i).isAlive()){
                alive++;
            }
            if(getField().getObjectAt(i).willAttack()){
                attack++;
            }
        }
        return alive*10+attack;
    }
    protected boolean isReproduce(){return reproduce;}

    public CellType getType(){
        return myType;
    }


}
