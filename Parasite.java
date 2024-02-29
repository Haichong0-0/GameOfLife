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
    
    /*@Override
    protected int checkSurrounding(){
        List<Cell> neighbours = getField().getLivingNeighbours(getLocation());
        List<Location> locations = getField().adjacentLocations(getLocation());
        int alive = 0;
        int attack = 0;
        int mollicutes = 0;
        for (Location i : locations){
            if(getField().getObjectAt(i).isAlive()){
                alive++;
            }
            if(getField().getObjectAt(i).willAttack()){
                attack++;
            }
            if(getField().getObjectAt(i).getType() == CellType.Mollicute){
                mollicutes++;
            }
            
        }
        return alive*100+attack*10+mollicutes; 
    }*/

    @Override
    public void act() {
        age++;
        //int attack = Math.round(((checkSurrounding()%100)/10)*10);
        //int mollicutes = checkSurrounding()%10;

        boolean friendlyFire = false;
        
        int num = checkSurrounding();
        int alive = num/100;
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
            if (alive>3){
                setNextState(false);
            }
        }
        else
        {
            if (alive == 2)
            {
                setNextState(true);
            }
        }
        
        
        
        
        if (mollicutes !=0)
        {
            setAggressive(true);
        }
        else
        {
            setAggressive(false);
        }
        
        if (mollicutes > 3)
        {
            setNextState(true);
        }
        
        
         
        if(attack !=0 && !friendlyFire){
            setNextState(false);
            //setColor(Color.ORANGE);
    
        }
        
       
        
        
        
    }


}
