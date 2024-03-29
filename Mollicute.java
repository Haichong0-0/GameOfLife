import javafx.scene.paint.Color;

import java.awt.*;


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
        setAggressive(false);
        
        if (isAlive())
        {
            //behavior as described in report
            if (alive>7||alive<2)
            {
                setNextState(false);
            }
            if (hasDisease() && age>50)
            {
                setNextState(false);
            }
            //reproduction every 15 generaiton
            if (age % 10 == 0)
            {
                setReproduce(true);
            }
            else
            {
                setReproduce(false);
            }
            //Color change
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
            setNextState(false);
            setAggressive(false);
            setContagious(false);
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
