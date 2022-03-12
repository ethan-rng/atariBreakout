
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class APanel extends JPanel implements ActionListener, KeyListener{
    //Properties
    Timer theTimer = new Timer(1000/60, this);
    Row[] brkMap = new Row[3];

    int intNRemaining = 30;
    int intLives = 3;
    String strFilePath = "";

    int intPaddleX = 400;

    int intBallX = 475;
    int intBallY = 550;
    int intBallDefX = 0;
    int intBallDefY = 0;
    boolean blnBallStart = false;
    boolean blnRestart = false;

    //Methods
    @Override
    public void actionPerformed(ActionEvent evt){
        if(evt.getSource() == theTimer){
            this.repaint();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        restart((char)e.getKeyCode());

    }

    @Override
    public void keyPressed(KeyEvent e) {
        restart((char)e.getKeyCode());

    }

    @Override
    public void keyReleased(KeyEvent e) {
        restart((char)e.getKeyCode());
    }

    public void restart(char c){
        if(c == 'f'){
            blnRestart = false;
            blnBallStart = false;
            intNRemaining = 30;
            intLives = 3;

            intPaddleX = 400;
            intBallX = 475;
            intBallY = 550;
            intBallDefX = 0;
            intBallDefY = 0;
            for(int intCount1=0; intCount1<3; intCount1++){
               brkMap[intCount1].resetBricks();
            }
        }
    }
    public Image loadStage(){
        if(intNRemaining > 20){
            try{
                return ImageIO.read(new File(strFilePath + "landscape1.jpg"));
            }catch(IOException e){}
        }else if(intNRemaining > 10){
            try{
                return ImageIO.read(new File(strFilePath + "landscape2.jpg"));
            }catch(IOException e){}
        }else{
            try{
                return ImageIO.read(new File(strFilePath + "landscape3.jpg"));
            }catch(IOException e){}
        }
        System.out.println("IMG LOAD PROBLEM");
        return null;
    }
    public Image loadPaddle(){
        try{
            return ImageIO.read(new File(strFilePath + "breakoutPaddle.jpg"));
        }catch(IOException e){
            return null;
        }
    }
    public Image loadBall(){
        try{
            return ImageIO.read(new File(strFilePath + "ball.jpg"));
        }catch(IOException e){
            return null;
        }
    }
    public Image loadEndScreen(){
        if(intLives == 0){
            try{
                blnRestart = true;
                return ImageIO.read(new File(strFilePath + "loser.jpg"));
            }catch(IOException e){
                return null;
            }
        }else if(intNRemaining == 0){
            try{
                blnRestart = true;
                return ImageIO.read(new File(strFilePath + "winner.jpg"));
            }catch(IOException e){
                return null;
            }
        }else{
            return null;
        }
    }

    //Override paintComponent
    @Override
    public void paintComponent(Graphics g) {
        //Drawing Stage and Paddle
        g.drawImage(loadStage(), 0, 0, null);
        g.drawImage(loadPaddle(), intPaddleX, 550, null);

        //Draw Map
        for(int intCount1=0; intCount1<3; intCount1++){
            Row newRow = brkMap[intCount1];
            g.setColor(newRow.getColor());
            for(int intCount2=0; intCount2<10; intCount2++){
                Brick newBrick = newRow.brkList[intCount2];
                if(newBrick.blnVisible){
                    g.fillRect(newBrick.intX, newBrick.intY, 80, 40);
                }
            }
        }

        //Bounds Detection for Ball
        if(intBallY+intBallDefY<=0){
            intBallDefY = -intBallDefY;
        }else if(intBallX+intBallDefX<=0 || intBallX+intBallDefX>=785){
            intBallDefX = -intBallDefX;
        }else if(intBallY>=520 && intBallY<=525 && intBallX>=intPaddleX && intBallX<=intPaddleX+150 && blnBallStart){
            intBallY -= 10;
            intBallDefY = -intBallDefY;
        }else if(intBallY+intBallDefY>=585){
            intLives-=1;

            //reset ball position
            intBallX = 475;
            intBallY = 550;
            intBallDefX = 0;
            intBallDefY = 0;
            blnBallStart = false;
        }

        //Boundary Detection For Brick and Ball
        Rectangle rectBall = new Rectangle(intBallX, intBallY, 30, 30);
        for(int intCount3=0; intCount3<3; intCount3++) {
            Row newRow = brkMap[intCount3];
            for (int intCount4=0; intCount4<10; intCount4++) {
                Brick newBrick = newRow.brkList[intCount4];
                if(newBrick.blnVisible){
                    Rectangle rectBrick = newBrick.rectBrick;
                    if(rectBall.intersects(rectBrick)){
                        intNRemaining -= 1;
                        //Bounce Back Action
                        if(intBallX >= rectBall.x && intBallX <= rectBall.x+80) {
                            intBallDefX = -intBallDefX;
                        }
                        if(intBallY >= rectBall.y && intBallY <= rectBall.y+40){
                            intBallDefY = -intBallDefY;
                        }
                        newBrick.blnVisible = false;
                    }
                }
            }
        }

        //Movement of Ball
        if(!blnBallStart){
            intBallX = intPaddleX+65;
            intBallY = 520;
        }else{
            intBallX += intBallDefX;
            intBallY += intBallDefY;
        }
        g.drawImage(loadBall(), intBallX, intBallY, null);

        //End Screen Checker
        g.drawImage(loadEndScreen(), 0, 0, null);

        //Health
        g.setColor(Color.BLACK);
        g.drawString("Health Remaining: " + intLives, 15, 555);
        g.drawString("Tiles Remaining: " + intNRemaining, 15, 575);
    }

    //Constructor
    public APanel(){
        super();
        this.setPreferredSize(new Dimension(800, 600));
        this.setLayout(null);
        theTimer.start();

        //Create a Brick Map
        for(int intCount1=0; intCount1<3; intCount1++){
            brkMap[intCount1] = new Row(intCount1);
        }
    }
}
