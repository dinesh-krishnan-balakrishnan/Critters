// Authors: Marty Stepp and Stuart Reges
// Minor changes by Mike Scott
import java.awt.*;

public class Stone extends Critter {
    // method comment goes here
    public Attack fight(String opponent) {
        // Good old rock, I mean roar. Nothing beats that.
        return Attack.ROAR;
    }

    public Color getColor() {
        return Color.GRAY;
    }

    public String toString() {
        return "S";
    }
}
