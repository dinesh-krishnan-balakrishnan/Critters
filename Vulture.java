import java.awt.Color;

public class Vulture extends Bird {
	
	private boolean hungry;
	
	//The vulture starts off being hungry.
	public Vulture() {
		hungry = true;
	}

	//If the vulture eats, it isn't hungry anymore. If it wasn't hungry in the first place, it remains that way.	 
	public boolean eat() {
		boolean toEat = hungry;
		hungry = false;
		return toEat;
	}
	
	//Every time the vulture fights, it fights like a bird but becomes hungry.
	public Attack fight(String opponent) {
		hungry = true;
		Attack attack = super.fight(opponent);
		return attack;
	}
	
	//The vulture is black in color. 
	public Color getColor() {
		return Color.BLACK;
	}
}
