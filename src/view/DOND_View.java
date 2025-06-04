/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author benma
 */

public class DOND_View extends JFrame {
    
    // Intialising classes that handle the different screens
    private final StartScreen startScreen = new StartScreen();
    
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
                
        setContentPane(startScreen);
    }
    
    // Game Screen
    public void gameScreen(){
        getContentPane().removeAll();
        revalidate();
        repaint();
        
    }
    
    // Screen is shown if the player wins
    public void winScreen(){
        getContentPane().removeAll(); 
        revalidate();
        repaint();
    }
    
    // Screen is shown if the player loses
    public void loseSCreen(){
        getContentPane().removeAll();   
        revalidate();
        repaint();
        
    }
    
    public StartScreen getStartScreen() {
        return startScreen;
    }
    
    // Contains all the components that require actions listeners
    public void addActionListener(ActionListener listener) {
        startScreen.submitButton.addActionListener(listener);
        this.boxButton.addActionListener(listener);
        startScreen.quitButton.addActionListener(listener);
    }
    
}
