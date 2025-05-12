package model;

import controller.Main;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author benma
 */
public class Player extends Main {
    // Initialising variables
    private String name;
    private final int box;
    private int offerTaken;
    
    // Player constructor
    public Player()
    {
        this.name = "";
        this.box = rand.nextInt(25);
        this.offerTaken = 0;
    }

    public int getBox() {
        return box;
    }

    public String getName() {
        return name;
    }

    public int getOfferTaken(){
        return offerTaken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOfferTaken(int offerTaken) {
        this.offerTaken = offerTaken;
    }
}

