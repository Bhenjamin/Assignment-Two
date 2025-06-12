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
    private final JPanel winScreenPanel = new JPanel();
    private final JPanel loseScreenPanel = new JPanel();
    // Intialising classes that handle the different screens
    private final StartScreen startScreen = new StartScreen();
    private final LeaderboardScreen leaderScreen = new LeaderboardScreen();
    
    // Intialises text field
    
    private final JLabel headingText = new JLabel();
    
    // Initialises Buttons
    private final JButton submitButton = new JButton("Submit");
    public final JButton leaderboardButton = new JButton("See Leaderboard");
    public final JButton quitButton = new JButton("Quit");
    public final JButton replayButton = new JButton("Replay?");
    
    // The boxes as button lists
    public final ArrayList<JButton> boxButton = new ArrayList<>();
    
    
    public final DealOrNoDealScreen dondScreen = new DealOrNoDealScreen();
    public final GameScreen gameScreen = new GameScreen();
    
   
    // Default Screen - Start Screen
    public DOND_View(){
        setTitle("Deal or No Deal");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1920, 1090);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
        // Switch to start screen after setting up frame
        startScreen();
        
    }
    
   
    public ArrayList<JButton> getBoxButtons() {
        return boxButton;
    }
    
    public void dealOrNoDeal(BoxModel boxModel, Banker bank) {
        boxButton.clear();
        getContentPane().removeAll();
        
        dondScreen.build(boxModel, bank);
        add(dondScreen.getPanel());
        revalidate();
        repaint();
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
    public void gameScreen(BoxModel boxModel, Banker bank, Player player){
        boxButton.clear();
        getContentPane().removeAll();
        gameScreenPanel.removeAll();
        headingText.removeAll();
        
        gameScreenPanel.setLayout(new BorderLayout(20, 20));
        
        headingText.setText("Please Select "+ bank.getOfferCounter() +" Box's");
        headingText.setFont(new Font("Arial", Font.BOLD, 32));
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
                button.setText(String.valueOf(boxModel.getBoxList().get(i).getValue()));
                button.setBackground(Color.DARK_GRAY);
            } else if (player.getBox()-1 == i) {
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
        
        quitButton.setFont(FontManager.BUTTON);
        leaderboardButton.setFont(FontManager.BUTTON);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(leaderboardButton);
        buttonPanel.add(quitButton);
        gameScreenPanel.add(buttonPanel, BorderLayout.SOUTH);

        gameScreenPanel.add(centerWrapper, BorderLayout.CENTER);
        
        add(gameScreenPanel);
        
        revalidate();
        repaint();
    }
    
    // Screen is shown if the player wins
    public void winScreen(Player player){
        boxButton.clear();
        getContentPane().removeAll();
        gameScreenPanel.removeAll();
        winScreenPanel.removeAll();
        headingText.removeAll();
        
        winScreenPanel.setLayout(new BorderLayout(20, 20));
        
        headingText.setText("You won $"+player.getOfferTaken());
        headingText.setFont(new Font("Arial", Font.BOLD, 32));
        
        quitButton.setFont(FontManager.BUTTON);
        leaderboardButton.setFont(FontManager.BUTTON);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(leaderboardButton);
        buttonPanel.add(quitButton);
        winScreenPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        replayButton.setFont(FontManager.BUTTON);
        loseScreenPanel.add(replayButton, BorderLayout.NORTH);
        
        winScreenPanel.add(headingText, BorderLayout.CENTER);
        
        add(winScreenPanel);
        revalidate();
        repaint();
    }
    
    // Screen is shown if the player loses
    public void loseScreen(Player player, Banker bank, BoxModel box){
        boxButton.clear();
        getContentPane().removeAll();
        gameScreenPanel.removeAll();
        winScreenPanel.removeAll();
        headingText.removeAll();
        
        loseScreenPanel.setLayout(new BorderLayout(20, 20));
        
        headingText.setText("You won $"+player.getOfferTaken() + 
                " $" + 
                (bank.getOffer()-player.getOfferTaken()) +
                " less than your box of $" +
                box.getBoxList().get(player.getBox()-1).getValue());
        headingText.setFont(new Font("Arial", Font.BOLD, 32));
        
        quitButton.setFont(FontManager.BUTTON);
        leaderboardButton.setFont(FontManager.BUTTON);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(leaderboardButton);
        buttonPanel.add(quitButton);
        
        loseScreenPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        replayButton.setFont(FontManager.BUTTON);
        loseScreenPanel.add(replayButton, BorderLayout.NORTH);
        
        loseScreenPanel.add(headingText, BorderLayout.CENTER);
        
        add(loseScreenPanel);
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
        for (int i = 0; i < dondScreen.getBoxButtons().size(); i++)
        {
            dondScreen.getBoxButtons().get(i).addActionListener(listener);
        }
        
        dondScreen.leaderboardButton.addActionListener(listener);
        dondScreen.quitButton.addActionListener(listener);
        
            
        startScreen.submitButton.addActionListener(listener);
        startScreen.quitButton.addActionListener(listener);
        startScreen.leaderboardButton.addActionListener(listener);
        
        leaderboardButton.addActionListener(listener);
        quitButton.addActionListener(listener);
        leaderScreen.backButton.addActionListener(listener);
        leaderScreen.resetButton.addActionListener(listener);
        leaderScreen.searchButton.addActionListener(listener);
        leaderScreen.returnButton.addActionListener(listener);
    }
    // Used when getting the player info for model
    public StartScreen getStartScreen() {
        return startScreen;
    }

    public JLabel getHeadingText() {
        return headingText;
    }
    
    // Used when getting the leaderboard info from model
    public LeaderboardScreen getLeaderboardScreen() {
        return leaderScreen;
    }
    
}
