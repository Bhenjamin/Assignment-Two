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
    private List<GameChangeListener> listeners = new ArrayList<>();
    private BoxModel boxModel;
    private DBManager dbManager;
    private DBScores dbscores;
    private Player player;
    
    public DOND_Model(){
        // Used when manipulating boxes 
        this.boxModel = new BoxModel();
        // Sets up boxes
        this.boxModel.initialiseBoxes();
        // Setups database for scores
        this.dbManager = new DBManager();
        this.dbscores = new DBScores(dbManager);
        dbscores.createScoresTable();
    }
    
    // Gets Player data
    public Player getPlayer() {
        return player;
    }
    
    public void addGameListener(GameChangeListener listener) {
        listeners.add(listener);
    }
    
    public void notifyUserNameEntered(String username) {
        // Initilises a new Player object after username
        // Has been submited
        this.player = new Player(username);
        // Notifies the classes
        for (GameChangeListener listener : listeners) {
            listener.onUserNameEntered(username);
        }
    }

    public void notifyBoxClicked(int boxNumber) {
        for (GameChangeListener listener : listeners) {
            listener.onBoxClicked(boxNumber);
        }
    }
    
    public void notifyRoundEnded(int bankerOffer) {
        for (GameChangeListener listener : listeners) {
            listener.onRoundEnded(bankerOffer);
        }
    }
    
    public void notifyGameEnded(Player player) {
        dbscores.newPlayerEntry(getPlayer());
        
        for (GameChangeListener listener : listeners) {
            listener.onGameEnded(player);
        }
    }
    
    public void endRound(){
//        int offer = bankerOffer();
//        notifyRoundEnded(offer);
    }
    
    
    public void openBox(int index){
        boxModel.getBoxList().get(index).open();
        // Notifies the view that a box has been opened
        notifyBoxClicked(index);
    }
    
}
