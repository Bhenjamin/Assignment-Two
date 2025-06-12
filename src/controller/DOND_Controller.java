/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.Color;
import java.awt.event.*;
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
                String username = view.usernameField.getText();
                model.notifyUserNameEntered(username);
                view.gameScreen(model.getBoxModel(), model.getBank(), model.getPlayer());
                view.addActionListener(this);
                break;
            case "No Deal":
                if (!(model.getBank().getRound() == 6))
                {
                    view.gameScreen(model.getBoxModel(), model.getBank(), model.getPlayer());
                    view.addActionListener(this);
                }
                break;
            default:
                if (command.startsWith("Box ")) {
                    int boxNum = Integer.parseInt(command.substring(4));
                    model.notifyBoxClicked(boxNum-1);
                    if (model.getBank().getRound() == 5)
                    {
                        model.getPlayer().setOfferTaken(model.getBoxModel().getBoxList().get(model.getPlayer().getBox()).getValue());
                        view.winScreen(model.getPlayer());
                        view.addActionListener(this);
                    }
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
    
    
}
