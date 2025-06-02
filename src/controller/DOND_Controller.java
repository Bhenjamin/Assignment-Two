/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
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
                view.gameScreen();
                view.addActionListener(this);
                break;
            
            default:
                if (command.startsWith("Box ")) {
                    int boxNum = Integer.parseInt(command.substring(4));
                    model.notifyBoxClicked(boxNum);
                }
        }
    }

    @Override
    public void onUserNameEntered(String username) {
    }

    @Override
    public void onBoxClicked(int boxNumber) {
        JButton boxButton = view.getBoxButtons().get(boxNumber);
        Box openBox = model.getBoxModel().getBoxList().get(boxNumber);
        
        // Update button
        boxButton.setEnabled(false);
        boxButton.setText(String.valueOf(openBox.getValue()));
        boxButton.setBackground(Color.BLACK);
    }

    @Override
    public void onRoundEnded(int bankerOffer) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    
}
