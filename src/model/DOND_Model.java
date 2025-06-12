/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author benma
 */
public class DOND_Model {
    private Player player;
    private List<GameChangeListener> listeners = new ArrayList<>();
    private BoxModel boxModel;
    private Banker bank;
    
    public DOND_Model(){
        // initilises Player object 
        this.player = new Player();
        // Used when manipulating boxes 
        this.boxModel = new BoxModel();
        // Sets up boxes
        this.boxModel.initialiseBoxes();
        this.bank = new Banker();
    }
    
    public void addGameListener(GameChangeListener listener) {
        listeners.add(listener);
    }
    
    public void notifyUserNameEntered(String username) {
        // Sets the user in the player object
        player.setUsername(username);
        // Notifies the classes
        for (GameChangeListener listener : listeners) {
            listener.onUserNameEntered(username);
        }
        //
    }

    public void notifyBoxClicked(int boxNumber) {
        Box box = boxModel.getBoxList().get(boxNumber);
        if (box.isOpen()) return;
        
        box.open();
        for (GameChangeListener listener : listeners) {
            listener.onBoxClicked(boxNumber);
        }
    }
    
    public void notifyRoundEnded(int bankerOffer) {
        for (GameChangeListener listener : listeners) {
            listener.onRoundEnded(bankerOffer);
        }
    }
    
    
    public void endRound(){
//        int offer = bankerOffer();
//        notifyRoundEnded(offer);
    }
    

    public BoxModel getBoxModel() {
        return this.boxModel;
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public Banker getBank() {
        return this.bank;
    }
}
