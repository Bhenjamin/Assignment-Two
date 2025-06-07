/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author benma
 */
public class StartScreen extends JPanel {
    
    
    public final JTextField usernameField = new JTextField("Enter a username to begin", 20);
    public final JButton leaderboardButton = new JButton("See Leaderboard");
    public final JButton submitButton = new JButton("Submit");
    public final JButton quitButton = new JButton("Quit");
    
    public StartScreen(){
        
        // Sets the layout type for this screen
        setLayout(new BorderLayout());
        
        // Creates the headings for this page
        JLabel headingText = new JLabel("Welcome to Deal or No Deal", SwingConstants.CENTER);
        JLabel subHeadingText = new JLabel("Enter your username to begin", SwingConstants.CENTER);
        
        // Sets the fonts using custom FontManager class
        headingText.setFont(FontManager.HEADER);
        subHeadingText.setFont(FontManager.SUBHEADER);
        usernameField.setFont(FontManager.TEXT_FIELD);
        
        submitButton.setFont(FontManager.BUTTON);
        quitButton.setFont(FontManager.BUTTON);
        leaderboardButton.setFont(FontManager.BUTTON);
        
        // Heading Panel Layout
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout((new BorderLayout()));
        headingPanel.add(headingText,BorderLayout.NORTH);
        headingPanel.add(subHeadingText,BorderLayout.SOUTH);
        
        // Centre Panel Layout
        JPanel centrePanel = new JPanel(new FlowLayout());
        
        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePanel.add(usernameField);
        
        JPanel submitPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        submitPanel.add(submitButton);
        
        centrePanel.add(usernamePanel,BorderLayout.NORTH);
        centrePanel.add(submitPanel,BorderLayout.SOUTH);
        
        // Button Panel Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(leaderboardButton);
        buttonPanel.add(quitButton);
        
        // Adds the components to the JPanel and set layout positions
        add(headingPanel, BorderLayout.NORTH);
        add(centrePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}
