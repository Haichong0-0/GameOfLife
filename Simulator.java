import javafx.scene.paint.Color;

import java.util.*;


/**
 * A Life (Game of Life) simulator, first described by British mathematician
 * John Horton Conway in 1970.
 *
 * @author David J. Barnes, Michael Kölling & Jeffery Raphael
 * @version 2024.02.03
 */

public class Simulator {

    private static final double AMOEBA_ALIVE_PROB = 0.05;
    private static final double MOLLICUTE_ALIVE_PROB = 0.25;
    private static final double PARASITE_ALIVE_PROB = 0.45;
    private static final double Diatom_ALIVE_PROB = 0.63;
    private static final double MYCOPLASMA_ALIVE_PROB = 0.25;

    private List<Cell> cells;
    private Field field;
    private int generation;

    /**
     * Construct a simulation field with default size.
     */
    public Simulator() {
        this(SimulatorView.GRID_HEIGHT, SimulatorView.GRID_WIDTH);
    }

    /**
     * Create a simulation field with the given size.
     *
     * @param depth Depth of the field. Must be greater than zero.
     * @param width Width of the field. Must be greater than zero.
     */
    public Simulator(int depth, int width) {
        cells = new ArrayList<>();
        field = new Field(depth, width);
        reset();
    }

    /**
     * Run the simulation from its current state for a single generation.
     * Iterate over the whole field updating the state of each life form.
     */
    public void simOneGeneration() {
        generation++;
        for (Iterator<Cell> it = cells.iterator(); it.hasNext(); ) {
            Cell cell = it.next();
            cell.act();
        }

        for (Cell cell : cells) {
            if (cell.isReproduce()) {
                duplicate(cell);
            }
            cell.updateState();
        }
    }

    /**
     * Reset the simulation to a starting position.
     */
    public void reset() {
        generation = 0;
        cells.clear();
        populate();
    }

    /**
     * Randomly populate the field live/dead life forms
     */
    private void populate() {
        Random rand = Randomizer.getRandom();
        field.clear();
        for (int row = 0; row < field.getDepth(); row++) {
            for (int col = 0; col < field.getWidth(); col++) {
                Location location = new Location(row, col);
                Cell cell = randomCell(location);
                cells.add(cell);

            }
        }
    }

    /**
     * Pause for a given time.
     *
     * @param millisec The time to pause for, in milliseconds
     */
    public void delay(int millisec) {
        try {
            Thread.sleep(millisec);
        } catch (InterruptedException ie) {
            // wake up
        }
    }

    public Field getField() {
        return field;
    }

    public int getGeneration() {
        return generation;
    }

    //duplicates the given cell and places it in a near by empty locaiton
    public void duplicate(Cell cell) {
        List<Location> avaliableSpace = new LinkedList<>();
        List<Location> locations = cell.getField().adjacentLocations(cell.getLocation());
        for (Location i : locations) {
            if (!field.getObjectAt(i).isAlive()) {
                avaliableSpace.add(i);
            }
        }
        if (!avaliableSpace.isEmpty()) {
            Random rand = new Random();
            int t = (int) (rand.nextDouble() * avaliableSpace.size());
            Location deadLocation = avaliableSpace.get(t);
            Cell deadCell = field.getObjectAt(deadLocation);

            Cell newCell = switch (cell.getType()) {
                case Mollicute: {
                    yield new Mollicute(cell, deadLocation);
                }
                case Amoeba: {
                    yield new Amoeba(cell, deadLocation);
                }
                case Mycoplasma: {
                    yield new Mycoplasma(cell, deadLocation);
                }
                case Parasite: {
                    yield new Parasite(cell, deadLocation);
                }
                case Diatom:{
                    yield new Diatom(cell, deadLocation);
                }
            };
            field.place(newCell, deadLocation);
            cells.set(cells.indexOf(deadCell), newCell);
        }
    }

    //returns a random cell from the given probabilities
    private Cell randomCell(Location location) {
        Random rand = Randomizer.getRandom();
        double random = rand.nextDouble();
        if (random < AMOEBA_ALIVE_PROB) {
            return new Amoeba(field, location, Color.BLUE);
        } else if (random < MOLLICUTE_ALIVE_PROB) {
            return new Mollicute(field, location, Color.BLACK);
        } else if (random < PARASITE_ALIVE_PROB) {
            return new Parasite(field, location, Color.PURPLE);
        } else if (random<Diatom_ALIVE_PROB) {
            return new Diatom(field,location,Color.LIGHTGREEN);

        } else {
            random = rand.nextDouble();
        }
        Mycoplasma myco = new Mycoplasma(field, location, Color.ORANGE);
        if (random <= MYCOPLASMA_ALIVE_PROB) {
            return myco;
        } else {
            myco.setDead();
            return myco;
        }


    }

}

