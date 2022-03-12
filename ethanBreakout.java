// Program: Breakout
// Program Version: 1.0
// Created By: Ethan Rong
// Date Completed: 2021-11-19

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class ethanBreakout implements ActionListener, MouseListener, MouseMotionListener, KeyListener{
    //Properties
    JFrame theFrame = new JFrame("BREAKOUT GAME");
    APanel theMainPanel = new APanel();

    int intMouseX = 0;
    int intMouseXPrev = 0;

    //Methods
    @Override
    public void actionPerformed(ActionEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseDragged(MouseEvent e) {}
    @Override
    public void mousePressed(MouseEvent e) {
        int intButton = e.getButton();
        if(intButton ==  3 && !theMainPanel.blnBallStart){
            theMainPanel.blnBallStart = true;
            theMainPanel.intBallDefY = -5;
            theMainPanel.intBallDefX = -5;
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        intMouseX = e.getX();
        if (intMouseX < intMouseXPrev) {
            if(theMainPanel.intPaddleX>=0) {
                theMainPanel.intPaddleX += -3;
            }
        }else if(intMouseX > intMouseXPrev) {
            if(theMainPanel.intPaddleX<=800-150){
                theMainPanel.intPaddleX += 3;
            }
        }
        intMouseXPrev=intMouseX;
    }
    @Override
    public void keyPressed(KeyEvent e) {
        restart(e.getKeyChar());;
    }
    @Override
    public void keyTyped(KeyEvent e) {
        restart(e.getKeyChar());;
    }
    @Override
    public void keyReleased(KeyEvent e){
        restart(e.getKeyChar());
    }
    public void restart(char c){
        if(c == 'f' && theMainPanel.blnRestart){
            theMainPanel.blnRestart = false;
            theMainPanel.blnBallStart = false;
            theMainPanel.intNRemaining = 30;
            theMainPanel.intLives = 3;
            System.out.println("adklfjaskdl;fj");
            theMainPanel.intPaddleX = 400;
            theMainPanel.intBallX = 475;
            theMainPanel.intBallY = 550;
            theMainPanel.intBallDefX = 0;
            theMainPanel.intBallDefY = 0;
            for(int intCount1=0; intCount1<3; intCount1++){
                theMainPanel.brkMap[intCount1].resetBricks();
            }
        }
    }

    //Constructor
    public ethanBreakout(){
        theMainPanel.setPreferredSize(new Dimension(800, 600));
        theMainPanel.setLayout(null);
        theFrame.setResizable(false);

        theFrame.addMouseListener(this);
        theFrame.addMouseMotionListener(this);
        theFrame.addKeyListener(this);

        theFrame.setContentPane(theMainPanel);
        theFrame.pack();
        theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        theFrame.setVisible(true);
    }
     public static void main(String[] args) {
	// write your code here
        new ethanBreakout();
    }
}
