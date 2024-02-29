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
        
        if (isAlive())
        {
            if (alive>3||alive<2)
            {
                setNextState(false);
            }
        }
        else
        {
            if (alive > 2)
            {
                setNextState(true);
            }
        }

        if(age%2==0){
            setColor(Color.RED);

        }
        else
        {
            setColor(Color.GREEN);
        }
        
        if(attack !=0 ){
            setNextState(false);
        }
        
       
    }


}
