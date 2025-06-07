/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.*;

/**
 *
 * @author benma
 */

public class DOND_View extends JFrame {
    
    // Intialising classes that handle the different screens
    private final StartScreen startScreen = new StartScreen();
    private final LeaderboardScreen leaderScreen = new LeaderboardScreen();
    
    // Initialises Labels - move to GameScreen class later
    private final JLabel bankerOffer = new JLabel();
    // Intialises text field
    
    
    // Initialises Buttons

    // Potentially could have the boxes as buttons?
    public final JButton boxButton = new JButton();
   
    // Default Screen - Start Screen
    public DOND_View(){
        setTitle("Deal or No Deal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1090);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Switch to start screen after setting up frame
        startScreen();
        
    }
    
    public void startScreen() {
        setContentPane(startScreen);
        revalidate();
        repaint();
    }
    
    public void leaderboardScreen(){
        setContentPane(leaderScreen);
        revalidate();
        repaint();
    }
    
    // Game Screen
    public void gameScreen(){
        revalidate();
        repaint();
        
    }
    
    // Screen is shown if the player wins
    public void winScreen(){
        revalidate();
        repaint();
    }
    
    // Screen is shown if the player loses
    public void loseSCreen(){
        getContentPane().removeAll();   
        revalidate();
        repaint();
        
    }
    
    // Used when getting the player info for model
    public StartScreen getStartScreen() {
        return startScreen;
    }
    
    // Used when getting the leader info from model
    public LeaderboardScreen getLeaderboardScreen() {
        return leaderScreen;
    }
    
    // Contains all the components that require actions listeners
    public void addActionListener(ActionListener listener) {
        startScreen.submitButton.addActionListener(listener);
        this.boxButton.addActionListener(listener);
        startScreen.quitButton.addActionListener(listener);
        startScreen.leaderboardButton.addActionListener(listener);
        leaderScreen.backButton.addActionListener(listener);
    }
    
}
