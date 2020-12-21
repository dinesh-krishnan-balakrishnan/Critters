import java.awt.Color;

public class Hippo extends Critter{
	
	private int turn;
	private Direction direction;
	private int hunger;
	private boolean hungry;
	
	//Initializes variables, the hunger variable based on the number parameter.
	public Hippo(int hunger) {
		this.hunger = hunger;
		turn = 0;
	}

	//Hunger decreases as the hippo eats; once it goes past 0, the hippo will no longer eat.
	public boolean eat() {
		hunger--;
		hungry = hunger > 0;
		return hunger >= 0;
	}

	//The hippo scratches or pounces based on whether it is hungry or not.
	public Attack fight(String opponent) {
		return hungry ? Attack.SCRATCH : Attack.POUNCE;
	}

	//Every 5 turns, the hippo moves in a different random direction.
	public Direction getMove() {
		final Direction[] directions = {Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST};
		//The hippo can move a total of 5 spaces in a direction. 
		final int MOVEMENT_LENGTH = 5;
		//Total number of directions being taken into account.
		final int DIRECTION_COUNT = directions.length;
		if (turn % MOVEMENT_LENGTH == 0) {
			direction = directions[(int) Math.floor(Math.random() * DIRECTION_COUNT)];
		}
		turn++;
		return direction;
	}

	// The hippo is gray or white based on whether it is hungry or not.
	public Color getColor() {
		return hungry ? Color.GRAY : Color.WHITE;
	}

	//The hippo's symbol is the number of turns it can continue eating, not going past 0
	public String toString() {
		return Math.max(hunger, 0) + "";
	}
}