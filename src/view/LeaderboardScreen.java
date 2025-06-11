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
    public final JTextField playerSearch = new JTextField("Search for a player", 20);
    public final JButton searchButton = new JButton("Search");
    public final JButton returnButton = new JButton("Return to Leaderboard");
    public JPanel buttonPanel = new JPanel();
    
    
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
        playerSearch.setFont(FontManager.TEXT_FIELD);
        backButton.setFont(FontManager.BUTTON);
        resetButton.setFont(FontManager.BUTTON);
        searchButton.setFont(FontManager.BUTTON);
        returnButton.setFont(FontManager.BUTTON);
        
        // Heading Panel Layout
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout((new BorderLayout()));
        headingPanel.add(headingText,BorderLayout.NORTH);
        
        // Centre Panel Layout
        JPanel centrePanel = new JPanel();
        centrePanel.setLayout(new BoxLayout(centrePanel, BoxLayout.Y_AXIS));
        // Search Bar panel
        JPanel searchBarPanel = new JPanel();
        searchBarPanel.add(playerSearch);
        searchBarPanel.add(searchButton);
        
        
        // Adds the player data to a JList to be displayed
        JPanel leaderBoardPanel = new JPanel(new GridLayout());
        JList<String> playerJList = new JList<>(playerListModel);
        playerJList.setFont(FontManager.SUBHEADER);
        
        // Formating the JList
        playerJList.setBackground(new Color(0, 0, 0, 0));
        playerJList.setOpaque(false);
        
        // Centres the JList
        DefaultListCellRenderer renderer = (DefaultListCellRenderer)playerJList.getCellRenderer(); 
        renderer.setHorizontalAlignment(JLabel.CENTER);
        
        leaderBoardPanel.add(playerJList);
        
        // Adds to the centre Panel
        centrePanel.add(searchBarPanel);
        centrePanel.add(leaderBoardPanel);

        // Button Panel Layout
        buttonPanel.add(resetButton);
        buttonPanel.add(backButton);
        
        // Adds the components to the JPanel and set layout positions
        add(headingPanel, BorderLayout.NORTH);
        add(centrePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public void updateLeaderboard(ArrayList<Player> topPlayer){
        
        revalidate();
        repaint();
        
        playerListModel.clear();
        for(int i=0; i< topPlayer.size(); i++){
            Player p = topPlayer.get(i);
            
            // Determines the display format depending whether it is 
            //displaying leaderboard or searching
            
            if(topPlayer.size() == 1){
                playerListModel.addElement(p.getUsername()+" - $"+p.getOfferTaken());
            }else{
                playerListModel.addElement((i+1)+". "+p.getUsername()+" - $"+p.getOfferTaken());
            }
            
            
        }
    }
    
    public void switchButtonLayoutWhenSearching(){
        // Changes the button layout to one button
        // To return the user back to the default leaderboard view
        buttonPanel.removeAll();
        buttonPanel.add(returnButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
    
    public void switchButtonLayoutDefault(){
        // Changes the button layout back to default
        buttonPanel.removeAll();
        buttonPanel.add(resetButton);
        buttonPanel.add(backButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
}