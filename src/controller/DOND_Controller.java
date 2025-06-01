/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.event.*;
import model.*;
import view.DOND_View;
/**
 *
 * @author benma
 */
public class DOND_Controller implements ActionListener, GameChangeListener {
    
    public DOND_View view;
    public DOND_Model model;
    private Player player;

    
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
                String username = view.usernameField.getText();
                model.notifyUserNameEntered(username);
                break;
            
            case "box":
                int boxNumber = Integer.parseInt(view.boxButton.getText());
                model.notifyBoxClicked(boxNumber);
                
                // Need to update the banker after a certain amount of rounds
                break;
                
            case "Quit":
                model.notifyGameEnded(player);
                break;
                
            default:
                System.exit(0);
                break;
        }
    }

    @Override
    public void onUserNameEntered(String username) {
        // After Player is created in model
        // Switch to the main game
        view.gameScreen();      
    }

    @Override
    public void onBoxClicked(int boxNumber) {
        throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void onRoundEnded(int bankerOffer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public void onGameEnded(Player player) {
        System.exit(0);
    }
    
    
}
