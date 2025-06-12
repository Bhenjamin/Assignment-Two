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
    private Banker bank;
    
    public DOND_Model(){
        this.bank = new Banker();
        // Used when manipulating boxes 
        this.boxModel = new BoxModel();
        // Sets up boxes
        this.boxModel.initialiseBoxes();
        // Setups database for scores
        this.dbManager = new DBManager();
        this.dbscores = new DBScores(dbManager);
        // dbscores.createScoresTable();
    }

    public void addGameListener(GameChangeListener listener) {
        listeners.add(listener);
    }
    
    public void notifyUserNameEntered(String username) {
        // Initilises a new Player object after username
        // Has been submited
        this.player = new Player (username, -1);
        // Notifies the classes
        for (GameChangeListener listener : listeners) {
            listener.onUserNameEntered(username);
        }
        //
    }
    
    public void notifyonPlayerSearch(String playerName){
        Player searchedPlayer = dbscores.getPlayer(playerName);
        for (GameChangeListener listener : listeners) {
            listener.onPlayerSearch(searchedPlayer);
        }
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
    
    public void notifyGameEnded() {
        // Won't add a new entry to database if player is never created
        if(player != null){
            dbscores.newPlayerEntry(getPlayer());
        }
        
        for (GameChangeListener listener : listeners) {
            listener.onGameEnded(player);
        }
    }
    
    // Resets the leaderboard making them all filler values
    public void resetLeaderboard(String tablename){
        dbscores.dropATableIfExists(tablename);
        dbscores.createScoresTable();
        // Updates the leaderboard screen view
        notifyLeaderboardOpened();
    }
    
    // Called when player openes the leaderboard screen
    public void notifyLeaderboardOpened(){
        ArrayList<Player> leaderBoardPlayers = dbscores.getLeaderBoard();
        for (GameChangeListener listener : listeners) {
            listener.onLeaderboardOpened(leaderBoardPlayers);
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
