import java.awt.Color;

public class Bird extends Critter {

	private int turn;
	private int currentDirection;

	public Bird() {
		turn = 0;
	}

	// The birds never eat.
	public boolean eat() {
		return false;
	}

	//The birds will roar on ants, but will pounce on any other animal. 
	public Attack fight(String opponent) {
		return opponent.equals("%") ? Attack.ROAR : Attack.POUNCE;
	}

	//Birds move 3 spaces in each ordinal direction, repeating.
	public Direction getMove() {
		final Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
		//The bird moves a total of 3 spaces in a direction before changing direction.
		final int MOVEMENT_LENGTH = 3;
		/*The TOTAL_MOVEMENT variable takes into account when the counting needs to be reset, 
		based on all movement that can occur.*/
		final int TOTAL_MOVEMENT = MOVEMENT_LENGTH * directions.length;
		currentDirection = turn % TOTAL_MOVEMENT / MOVEMENT_LENGTH;
		turn++;
		return directions[currentDirection];
	}

	//Birds are generally blue.
	public Color getColor() {
		return Color.BLUE;
	}

	//The symbol changes depending on the direction the bird moved.
	public String toString() {
		final String[] CHARACTERS = {"^", ">", "V", "<"};
		return CHARACTERS[currentDirection];
	}
}