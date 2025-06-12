package model;

import java.util.ArrayList;
import java.util.Random;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author benma
 */

public class Banker {
    private final Random rand = new Random();
    private int OfferCounter = 10;
    private int round = 1;
    private int offer = 0;
    
    public Banker() {}
    // Gets the mean of remaining boxes
    public double getAverage(ArrayList<Box> boxList)
    {
        double avg = 0.0;

        for(int i = 0; i < boxList.size(); i++){
            // Averages box only if has not been opened
            if (!boxList.get(i).isOpen())
            {
                avg += boxList.get(i).getValue();
            }
        }
        
        return avg/boxList.size();
    }
    
    // Calcualates the bank offer avg - sum proportional to the avg
    public void bankerOffer(ArrayList<Box> boxList)
    {
        double avg = getAverage(boxList); 
        int random = 0;
        
        if (avg > 200000)
        {
            random = (60000+ rand.nextInt(40000));
        } else if (avg > 100000)
        {
            random = (40000 + rand.nextInt(20000)); 
        } else if (avg > 50000)
        {
            random = (10000 + rand.nextInt(2000));
        } else random = rand.nextInt(10000);

        while (random >= avg) {
            random -= rand.nextInt(500);
        }
        
        offer = (int)avg-random;
    }
    
    public int getOfferCounter() {
        return this.OfferCounter;
    }
    
    public int getRound() {
        return this.round;
    }
    
    public boolean nextRound() {
        this.OfferCounter--;
        if (this.OfferCounter == 0 && this.round != 6)
        {
            this.round ++;
            System.out.println(this.round);
            
            
            if (this.round == 2) this.OfferCounter = 6;
            if (this.round == 3) this.OfferCounter = 5;
            if (this.round == 4) this.OfferCounter = 2;
            if (this.round == 5) this.OfferCounter = 1;
            if (this.round == 6) return false;
            return true;
        } else 
        {
            return false;
        }
    }
    
    public int getOffer() {
        return this.offer;
    }
}
