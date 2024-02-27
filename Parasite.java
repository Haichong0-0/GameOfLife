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
    }

    @Override
    public void act() {
        age++;
        int alive = checkSurrounding()/100;
        int attack = (checkSurrounding()/100)*10;
        int mollicutes = checkSurrounding()%10;
        setNextState(true);
        
        if(attack !=0){
            setNextState(false);
        }
        
    }


}
