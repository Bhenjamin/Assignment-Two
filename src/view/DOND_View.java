/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.BoxModel;

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
    public void gameScreen(BoxModel boxModel){
        boxButton.clear();
        getContentPane().removeAll();
        gameScreenPanel.removeAll();
        
        gameScreenPanel.setLayout(new BorderLayout(20, 20));
        
        headingText.setText("Deal or No Deal?");
        headingText.setHorizontalAlignment(SwingConstants.CENTER);
        gameScreenPanel.add(headingText, BorderLayout.NORTH);
        
        JPanel boxGrid = new JPanel(new GridLayout(5, 5, 25, 25));
        
        ArrayList<model.Box> boxes = boxModel.getBoxList();
        
        for (int i = 0; i < 25; i++)
        {
            model.Box box = boxes.get(i);
            JButton button = new JButton();
            
            if (box.isOpen())
            {
                button.setEnabled(false);
                button.setText(String.valueOf(box.getValue()));
                button.setBackground(Color.DARK_GRAY);
            } else {
                button.setText("Box "+String.valueOf(i + 1));
                button.setActionCommand("Box " + (i + 1));
            }
            
            boxButton.add(button);
            boxGrid.add(button);
        }
        
        
        JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        centerWrapper.add(boxGrid);

        gameScreenPanel.add(centerWrapper, BorderLayout.CENTER);
        
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
