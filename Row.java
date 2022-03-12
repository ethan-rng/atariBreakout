
import java.awt.*;

public class Row {
    //Properties
    Brick[] brkList = new Brick[10];
    int intRow;

    //Methods
    public Color getColor(){
        if(intRow == 0){
            return Color.RED;
        }else if(intRow == 1){
            return Color.YELLOW;
        }else{
            return Color.BLUE;
        }
    }

    public void resetBricks(){
        for(int intCount1=0; intCount1<10; intCount1++){
            brkList[intCount1].blnVisible = true;
        }
    }

    //Constructor
    public Row(int intRow){
        this.intRow = intRow;
        for(int intCount1=0; intCount1<10; intCount1++){
           brkList[intCount1] = new Brick(intCount1*80, intRow*40);
        }
    }
}
