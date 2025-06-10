/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import model.*;



/**
 *
 * @author benma
 */
public class LeaderboardScreen extends JPanel{
    
    public JLabel leaderBoardPlayers = new JLabel();
    public final JButton backButton = new JButton("Back");
    public final JButton resetButton = new JButton("Reset Leaderboard");
    private DefaultListModel<String> playerListModel;
    
    public LeaderboardScreen(){
        // Initalising the List Model
        playerListModel = new DefaultListModel<>();
        // Sets the layout type for this screen
        setLayout(new BorderLayout());
        
        // Creates the headings for this page
        JLabel headingText = new JLabel("DOND Leaderboard", SwingConstants.CENTER);
        
        // Sets the fonts using custom FontManager class
        headingText.setFont(FontManager.HEADER);
        leaderBoardPlayers.setFont(FontManager.TEXT_FIELD);
        backButton.setFont(FontManager.BUTTON);
        resetButton.setFont(FontManager.BUTTON);
        
        // Heading Panel Layout
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout((new BorderLayout()));
        headingPanel.add(headingText,BorderLayout.NORTH);
        
        // Centre Panel Layout
        JPanel centrePanel = new JPanel(new GridBagLayout());
        
        // Adds the player data to a JList to be adding on screen using a ScrollPane
        JList<String> playerJList = new JList<>(playerListModel);
        playerJList.setFont(FontManager.SUBHEADER);
        centrePanel.add(playerJList);
        
        
        // Button Panel Layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(resetButton);
        buttonPanel.add(backButton);
        
        // Adds the components to the JPanel and set layout positions
        add(headingPanel, BorderLayout.NORTH);
        add(centrePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void updateLeaderboard(ArrayList<Player> topPlayer){
        playerListModel.clear();
        for(int i=0; i< topPlayer.size(); i++){
            Player p = topPlayer.get(i);
            playerListModel.addElement((i+1)+": "+p.getUsername()+" - $"+p.getOfferTaken());
        }
    }
}
