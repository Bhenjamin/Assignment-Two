package model;

import java.util.Random;
import java.util.Objects;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author benma
 */
public class Player {

    // Initialising variables
    private static final Random rand = new Random();
    private String username;
    private final int box;
    private int offerTaken;

    // Player constructor
    public Player(String username, int offerTaken) {
        this.username = username;
        this.box = rand.nextInt(25);
        this.offerTaken = offerTaken;
    }

    public int getBox() {
        return box;
    }

    public String getUsername() {
        return username;
    }

    public int getOfferTaken() {
        return offerTaken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOfferTaken(int offerTaken) {
        this.offerTaken = offerTaken;
    }
    
    // Overrides the default equals for player class
    // Checks the username and offertaken only
    @Override
    public boolean equals(Object o) {
    if (this == o) return true;
    
    // Returns false if object is null or they are a Player class
    if (o == null || getClass() != o.getClass()) return false;
    
    Player player = (Player) o;
    
    return offerTaken == player.offerTaken && username.equals(player.username);
    
    }
    @Override
    public int hashCode() {
        return Objects.hash(username, offerTaken);
    }

}
