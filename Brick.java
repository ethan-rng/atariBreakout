
import java.awt.*;
import java.awt.Rectangle.*;

public class Brick{
    //Properties
    int intX;
    int intY;
    boolean blnVisible=true;
    Rectangle rectBrick;

    //Constructor
    public Brick(int intX, int intY){
        this.intX = intX;
        this.intY = intY;
        this.rectBrick = new Rectangle(intX, intY, 80, 40);
    }
}
