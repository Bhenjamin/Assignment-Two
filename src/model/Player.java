package model;

import java.util.Random;

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
    public Player(String username)
    {
        this.username = username;
        this.box = rand.nextInt(25);
        this.offerTaken = 0;
    }

    public int getBox() {
        return box;
    }

    public String getUsername() {
        return username;
    }

    public int getOfferTaken(){
        return offerTaken;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOfferTaken(int offerTaken) {
        this.offerTaken = offerTaken;
    }
}

