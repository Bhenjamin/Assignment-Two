/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;
import java.awt.GridLayout;
import model.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author benma
 */

public class DOND_View extends JFrame {

    
    // Intitalises Panels
    private final JPanel startPanel = new JPanel();
    private final JPanel gameScreenPanel = new JPanel();
    private final JPanel winScreenPanel = new JPanel();
    private final JPanel loseScreenPanel = new JPanel();
    
    
    // Initialises Labels
    private final JLabel headingText = new JLabel("Welcome to Deal or No Deal");
    private final JLabel subHeadingText = new JLabel("enter your username to begin");
    private final JLabel bankerOffer = new JLabel();
    // Intialises text field
    public final JTextField usernameField = new JTextField("Enter your username", 20);
    
    // Initialises Buttons
    private final JButton submitButton = new JButton("Submit");
    
    // The boxes as button lists
    public final ArrayList<JButton> boxButton = new ArrayList<>();
   
    // Default Screen - Start Screen
    public DOND_View(){
        setTitle("Deal or No Deal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1090);
        
        startPanel.add(headingText);
        startPanel.add(subHeadingText);
        startPanel.add(usernameField);
        startPanel.add(submitButton);
        
        add(startPanel);
    }
    
   
    public ArrayList<JButton> getBoxButtons() {
        return boxButton;
    }
    
    // Game Screen
    public void gameScreen(){
        getContentPane().removeAll();
        
        headingText.setText("Deal or No Deal?");
        gameScreenPanel.setLayout(new GridLayout(4, 5, 10, 10));
        gameScreenPanel.add(headingText);
        
        boxButton.clear();
        for (int i = 1; i <= 25; i++)
        {
            JButton button = new JButton(String.valueOf(i));
            button.setActionCommand("Box "+i);
            boxButton.add(button);
            gameScreenPanel.add(button);
        }
        
        add(gameScreenPanel);
        
        revalidate();
        repaint();
        
    }
    
    // Screen is shown if the player wins
    public void winScreen(){
        getContentPane().removeAll();
        winScreenPanel.add(headingText);
        winScreenPanel.add(subHeadingText);
        
        revalidate();
        repaint();
    }
    
    // Screen is shown if the player loses
    public void loseSCreen(){
        getContentPane().removeAll();
        loseScreenPanel.add(headingText);
        loseScreenPanel.add(subHeadingText);
        
        revalidate();
        repaint();
        
    }
    
    // Contains all the components that require actions listeners
    public void addActionListener(ActionListener listener) {
        this.submitButton.addActionListener(listener);
        
        for (JButton button : boxButton)
        {
            button.addActionListener(listener);
        }
    }
    
}
