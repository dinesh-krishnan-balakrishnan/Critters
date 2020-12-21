import java.awt.Color;

public class Ant extends Critter {
	
	private Direction direction;
	private boolean movedEast;
	
	//Initializes variables, direction being South or North based on parameter input.
	public Ant(boolean walkSouth) {
		direction = walkSouth ? Direction.SOUTH : Direction.NORTH;
		movedEast = false;
	}

	//The ant always eats.
	public boolean eat() {
		return true;
	}

	//The ant always scratches its opponent.
	public Attack fight(String opponent) {
		return Attack.SCRATCH;
	}

	//The ant alternates moving between east and the direction variable.
	public Direction getMove() {
		movedEast = !movedEast;
		return movedEast ? direction: Direction.EAST;
	}

	//The ant will always be red.
	public Color getColor() {
		return Color.RED;
	}

	//The ant's symbol is the % sign.
	public String toString() {
		return "%";
	}
}