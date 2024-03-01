import javafx.scene.paint.Color;

import java.util.List;


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

        boolean friendlyFire = false;
        
        int num = checkSurrounding();
        int alive = num/100;//alive cells round
        int attack = (num -alive*100)/10;
        
        int mollicutes =0;
        int parasites =0;
       
        List<Location> locations = getField().adjacentLocations(getLocation());
        
        for (Location i: locations)
        {
            Cell obj  = getField().getObjectAt(i);
            if (obj.getType() == CellType.Mollicute && obj.isAlive())
            {
                mollicutes++;
            }
            else if (obj.getType() == CellType.Parasite && obj.willAttack())
            {
                parasites++;
            } 
            
        }
        
        if (parasites == attack)
        {
            friendlyFire = true;
        }
        
        
        
        if (isAlive())
        {
            if (alive<5){
                setNextState(false);
            }
            //age restriction
            if (age > 30)
            {
                setDisease(true);
                setContagious(true);
            }
            //reacting to cells it parasites on
            if (mollicutes !=0)
            {
                setAggressive(true);
                setReproduce(true);
            }
            else
            {
                setAggressive(false);
                setReproduce(false);
            }

            if(attack !=0 && !friendlyFire && !hasDisease())
            {
                setNextState(false);
            }
        }

        else
        {
            if (alive == 2)
            {
                setNextState(true);
            }
            if (mollicutes > 3)
            {
                setNextState(true);
            }
            setDisease(false);
            setContagious(false);
        }

    }


}
