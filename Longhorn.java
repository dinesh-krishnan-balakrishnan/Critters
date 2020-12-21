import java.awt.Color;
import java.util.*;

public class Longhorn extends Critter {

	//Shared ArrayLists are created to keep track of seen enemies, attacks against them, and longhorns who didn't mate.
	public static List<Longhorn> virginLonghorns = new ArrayList<Longhorn>();
	public static List<String> enemies = new ArrayList<String>();
	public static List<Attack> attacks = new ArrayList<Attack>();

	//The private variables keep track of the last fought opponent and whether the longhorn is sleeping.
	private String lastOpponent;
	private boolean isAwake;
	
	//Every time a longhorn is added to the simulation world, it is born as a virgin and awake.
	public Longhorn() {
		virginLonghorns.add(this);
		isAwake = true;
	}
	
	//The static method initializes the attacks known to be usually effective against certain enemies.
	static {
		//HIPPO (When hungry)
		for (int digit = 1; digit <= 9; digit++) {
			enemies.add(digit + "");
			attacks.add(Attack.ROAR);
		}
		//ANT
		enemies.add("%");
		attacks.add(Attack.ROAR);
		//BIRD and HIPPO (When full)
		enemies.addAll(Arrays.asList("<", ">", "^", "V", "0"));
		for (int enemy = 1; enemy <= 5; enemy++) {
			attacks.add(Attack.SCRATCH);
		}
		//STONE
		enemies.add("S");
		attacks.add(Attack.POUNCE);
	}

	//The longhorn always chooses to eat.
	public boolean eat() {
		return true;
	}
	
	//The sleep and wakeup methods help control the isAwake boolean variable.
	public void sleep() {
		isAwake = false;
	}
	
	//They help determine whether the fight mechanisms should change.
	public void wakeup() {
		isAwake = true;
	}

	/*Checks to see whether known attacks exist, or else creates a new enemy directory that has a 
	 default value of Attack.ROAR */
	public Attack fight(String opponent) {
		lastOpponent = opponent;
		int attackIndex = enemies.indexOf(opponent);
		if (attackIndex == -1) {
			enemies.add(opponent);
			attacks.add(Attack.ROAR);
			return Attack.ROAR;
		}
		return attacks.get(attackIndex);
	}
	
	//Simply changes the attack against a certain enemy depending on the last attack used, if the longhorn lost.
	public void lose() {
		if (isAwake) {
			int attackIndex = enemies.indexOf(lastOpponent);
			Attack attack = attacks.get(attackIndex);
			if (attack == Attack.SCRATCH) {
				attacks.set(attackIndex, Attack.POUNCE);
			} else if (attack == Attack.POUNCE) {
				attacks.set(attackIndex, Attack.ROAR);
			}
			attacks.set(attackIndex, Attack.SCRATCH);
		}
	}

	//The longhorn moves to mate first, then goes to find food.
	public Direction getMove() {
		return virginLonghorns.contains(this)? findMate() : findFood();
	}
	
	//Finds the closest mate and moves towards it.
	private Direction findMate() {
		//Finds the closest longhorn considering the west and east sides.
		Longhorn closestLonghorn = virginLonghorns.get(0);
		int xPos = getX();
		int yPos = getY();
		for (Longhorn longhorn: virginLonghorns) {
			if (longhorn != this) {
				if (Math.abs(longhorn.getX() - xPos) < Math.abs(closestLonghorn.getX() - xPos)) {
					closestLonghorn = longhorn;
				}
			}
		}
		/*Finds the relative position of its current position to the closest longhorn,
		 and moves in the current direction. */
		int toX = closestLonghorn.getX();
		int toY = closestLonghorn.getY();
		if (toX < xPos) {
			return Direction.WEST;
		} else if (toX > xPos) {
			return Direction.EAST;
		} else if (toY < yPos) {
			return Direction.NORTH;
		}
		return Direction.SOUTH;
	}

	//The longhorn moves to eat food if it is found within its sight, or ends up moving in a ranodm direction.
	private Direction findFood() {
		//Only contains WEST and NORTh to make sure the longhorn doesn't waste time moving back and forth.
		final Direction[] DIRECTIONS = {Direction.WEST, Direction.NORTH};
		final int NUM_DIRECTIONS = DIRECTIONS.length;
		for (int index = 0; index < NUM_DIRECTIONS; index++) {
			if (getNeighbor(DIRECTIONS[index]) == ".") {
				return DIRECTIONS[index];
			}
		}
		return DIRECTIONS[(int) (Math.random() * NUM_DIRECTIONS)];
	}

	//When a longhorn mates, it shouldn't be in the virginLonghorns ArrayList.
	public void mate() {
		virginLonghorns.remove(this);
	}

	//When the game is reset, no current longhorns in the virginLonghorns list should exist.
	public void reset() {
		virginLonghorns = new ArrayList<Longhorn>();
	}

	//The longhorn is the UT color, orange.
	public Color getColor() {
		return Color.ORANGE;
	}

	//The toString method is a W to represent the hook'em sign.
	public String toString() {
		return "W";
	}
}