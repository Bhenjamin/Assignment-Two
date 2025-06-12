/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Color;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.Timer;
import model.*;
import view.DOND_View;
/**
 *
 * @author benma
 */
public class DOND_Controller implements ActionListener, GameChangeListener {
    
    public DOND_View view;
    public DOND_Model model;

    
    public DOND_Controller(DOND_View view, DOND_Model model) {
        this.model = model;
        this.view = view;
        
        this.view.addActionListener(this);
        this.model.addGameListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Gets the text of the button e.g. "enter"
        String command = e.getActionCommand();
        
        // Uses switch case to detemine what happens on action
        switch(command){
            
            case "Submit":
                String username = view.getStartScreen().usernameField.getText();
                model.notifyUserNameEntered(username);
                break;
                
            case "Deal":
                model.getPlayer().setOfferTaken(model.getBank().getOffer());
                int playerBox = model.getBoxModel().getBoxList().get(model.getPlayer().getBox()-1).getValue();
                if (model.getPlayer().getOfferTaken() >= playerBox)
                {
                    view.winScreen(model.getPlayer());
                    view.addActionListener(this);
                } else
                {
                    view.loseScreen(model.getPlayer(), model.getBank(), model.getBoxModel());
                    view.addActionListener(this);
                }
                model.notifyGameEnded();
                break;
                
            case "No Deal":
                if (!(model.getBank().getRound() == 6))
                {
                    view.gameScreen(model.getBoxModel(), model.getBank(), model.getPlayer());
                    view.addActionListener(this);
                }
                break;
                
            case "Quit":
                System.exit(0);
                break;
                
            case "Back":
                view.startScreen();
                break;
                
            case "See Leaderboard":
                model.notifyLeaderboardOpened();
                break;
                
            case "Reset Leaderboard":
                model.resetLeaderboard("SCORESTABLE");
                break;
                
            case "Search":
                String playerName = view.getLeaderboardScreen().playerSearch.getText();
                model.notifyonPlayerSearch(playerName);
                break;
                
            case "Return to Leaderboard":     
                // Refreshes the leaderboard back to original state
                model.notifyLeaderboardOpened();
                view.getLeaderboardScreen().switchButtonLayoutDefault();
                view.leaderboardScreen();
                break;
                
            case "Return to Main Menu":
                    String[] args = new String[0]; 
                    Main.main(args);
                    break;
                
                
            default:
                if (command.startsWith("Box ")) {
                    int boxNum = Integer.parseInt(command.substring(4));
                    model.notifyBoxClicked(boxNum-1);
                    if (model.getBank().getRound() == 5)
                    {
                        model.getPlayer().setOfferTaken(model.getBoxModel().getBoxList().get(model.getPlayer().getBox()-1).getValue());
                        view.winScreen(model.getPlayer());
                        view.addActionListener(this);
                        model.notifyGameEnded();
                    }
                }
                break;
        }
    }

    @Override
    public void onUserNameEntered(String username) {
        // After Player is created in model
        // Switch to the main game
        view.gameScreen(model.getBoxModel(), model.getBank(), model.getPlayer());
        view.addActionListener(this);
    }

    @Override
    public void onBoxClicked(int boxNumber) {
        JButton boxButton = view.getBoxButtons().get(boxNumber);
        Box openBox = model.getBoxModel().getBoxList().get(boxNumber);
        model.getBoxModel().getBoxList().get(boxNumber).open();

        // Disable all buttons temporarily
        for (JButton b : view.getBoxButtons()) {
            b.setEnabled(false);
        }

        // Update clicked box
        boxButton.setText(String.valueOf(openBox.getValue()));
        boxButton.setBackground(Color.DARK_GRAY);

        // Pause for 1 second before continuing
        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!model.getBank().nextRound() && model.getBank().getRound()!= 6) {
                    view.getHeadingText().setText("Please Select " + model.getBank().getOfferCounter() + " Box's");

                    // Re-enable only unopened, unselected boxes
                    for (int i = 0; i < view.getBoxButtons().size(); i++) {
                        JButton b = view.getBoxButtons().get(i);
                        Box box = model.getBoxModel().getBoxList().get(i);
                        if (!box.isOpen()) {
                            b.setEnabled(true);
                        }
                    }

                } else {
                    // Transition to Deal or No Deal screen
                    if (model.getBank().getRound() != 6)
                    {
                        view.dealOrNoDeal(model.getBoxModel(), model.getBank());
                        view.addActionListener(DOND_Controller.this); // Reattach listeners
                    }
                }
            }
        });

        timer.setRepeats(false);
        timer.start();
    }

    @Override
    public void onRoundEnded(int bankerOffer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void onGameEnded(Player player) {
        // Updates
    }
    
    @Override
    public void onLeaderboardOpened(ArrayList<Player> topPlayers) {
        // Updates the model
        view.getLeaderboardScreen().updateLeaderboard(topPlayers);
        // After leaderboard is updated in model, switch to screen
        view.leaderboardScreen();
        
    }

    @Override
    public void onPlayerSearch(Player playerName) {
        ArrayList searchedPlayer = new ArrayList<>();
        searchedPlayer.add(playerName);
        // Changes the JList to show the searched Player
        view.getLeaderboardScreen().updateLeaderboard(searchedPlayer);
        // Changes the button layout
        view.getLeaderboardScreen().switchButtonLayoutWhenSearching();
    }
    
    
}
