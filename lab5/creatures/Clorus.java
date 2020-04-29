package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

import static huglife.HugLifeUtils.randomEntry;
import static huglife.HugLifeUtils.randomInt;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    public String name() {
        return this.name;
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    public void attack(Creature c) {
        this.energy += c. energy();

    }

    public void move() {
        this.energy -= 0.03;
        if(this.energy < 0)
            this.energy = 0;
    }

    public void stay() {
        this.energy -= 0.01;

    }

    public Clorus replicate() {
        Clorus offSpring = new Clorus(this.energy/2);
        this.energy /= 2;
        return offSpring;
    }

    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        //boolean anyPi

        for(Direction key: neighbors.keySet()) {
            if(neighbors.get(key).name().equals("empty")) {
                emptyNeighbors.add(key);
            } else if (neighbors.get(key).name().equals("plip")) {
                plipNeighbors.add(key);
            }
        }

        if(emptyNeighbors.size() == 0) {
            return new Action(Action.ActionType.STAY);
        }

        if(plipNeighbors.size() !=0 ) {
            return new Action(Action.ActionType.ATTACK, randomEntry(plipNeighbors));
        }

        if(this.energy >= 1) {
            return new Action(Action.ActionType.REPLICATE, randomInt(HugLife.WORLD_SIZE), randomInt(HugLife.WORLD_SIZE));
        }

        return new Action(Action.ActionType.MOVE, randomEntry(emptyNeighbors));
    }
}
