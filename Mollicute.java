import javafx.scene.paint.Color;


public class Mollicute extends Cell{

    public Mollicute(Field field, Location location, Color col) {
        super(field, location, col);
        age = 0;
        super.myType = CellType.Mollicute;
    }

    public Mollicute(Cell cell, Location location) {
        super(cell,location);
        age = 0;
        super.myType = CellType.Mollicute;
    }
    
    @Override
    public void act() { 
        age++;
        int num = checkSurrounding();
        int alive = num/100;
        int attack = (num -alive*100)/10;
        int disease = num -alive*100-attack*10;
        
        if (isAlive())
        {
            if (alive>7||alive<2)
            {
                setNextState(false);
            }
            if (hasDisease() && age>50)
            {
                setNextState(false);
            }
            if (age % 15 == 0)
            {
                setReproduce(true);
            }
            else
            {
                setReproduce(false);
            }
            if(age%2==0)
            {
                setColor(Color.RED);
            }
            else
            {
                setColor(Color.BLACK);
            }   
        }
        else
        {
            if (alive == 2)
            {
                setNextState(true);
            }
        }
    
        
        
        if(attack !=0 ){
            setNextState(false);
        }
        
        
        if (disease > 0)
        {
            setDisease(true);
        }
    
        
    }


}
