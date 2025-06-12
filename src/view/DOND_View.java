/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;
import model.*;

/**
 *
 * @author benma
 */

public class DOND_View extends JFrame {

    
    // Intitalises Panels
    private final JPanel startPanel = new JPanel();
    private final JPanel gameScreenPanel = new JPanel();
    private final JPanel dealOrNoDealPanel = new JPanel();
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
    
    public void dealOrNoDeal(BoxModel boxModel, Banker bank) {
        boxButton.clear();
        getContentPane().removeAll();
        dealOrNoDealPanel.removeAll();
        
        dealOrNoDealPanel.setLayout(new BorderLayout(20, 20));
        
        headingText.setText("Bank Offers: "+bank.bankerOffer(boxModel.getBoxList()));
        headingText.setHorizontalAlignment(SwingConstants.CENTER);
        dealOrNoDealPanel.add(headingText, BorderLayout.NORTH);
        
        JPanel dond = new JPanel(new GridLayout(2, 1, 25, 25));
        
        for (int i = 1; i <= 2; i++)
        {
            
            JButton button = new JButton();
            if (i==1)
            {
            button.setText("Deal");
            button.setActionCommand("Deal");
            }
            if (i==2)
            {
            button.setText("No Deal");
            button.setActionCommand("No Deal");
            }
            boxButton.add(button);
            dond.add(button);
        }
        JPanel centerWrapper = new JPanel();
        centerWrapper.setLayout(new BoxLayout(centerWrapper, BoxLayout.Y_AXIS));
        centerWrapper.setBorder(BorderFactory.createEmptyBorder(20, 100, 20, 100));
        centerWrapper.add(dond);

        dealOrNoDealPanel.add(centerWrapper, BorderLayout.CENTER);
        
        add(dealOrNoDealPanel);
        
        revalidate();
        repaint();
        
    }
    
    // Game Screen
    public void gameScreen(BoxModel boxModel, Banker bank){
        boxButton.clear();
        getContentPane().removeAll();
        gameScreenPanel.removeAll();
        
        gameScreenPanel.setLayout(new BorderLayout(20, 20));
        
        headingText.setText("Please Select "+ bank.getOfferCounter() +" Box's");
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
            } else if (model.Player.getBox() == i+1) {
                button.setText("Your Box");
                button.setBackground(Color.GRAY);
                button.setForeground(Color.WHITE);
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

    public JLabel getHeadingText() {
        return headingText;
    }
}
