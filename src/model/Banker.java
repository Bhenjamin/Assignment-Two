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
    // References boxList from Model
    private final ArrayList<Box> boxList;
    private final Random rand = new Random();
    
    public Banker(ArrayList<Box> boxList) {
        this.boxList = boxList;
    }
    // Gets the mean of remaining boxes
    public double getAverage()
    {
        double avg = 0.0;

        for(int i = 0; i < boxList.size(); i++){
            // Averages box only if has not been opened
            if (!boxList.get(i).isOpened())
            {
                avg += boxList.get(i).getValue();
            }
        }
        
        return avg/boxList.size();
    }
    
    // Calcualates the bank offer avg - sum proportional to the avg
    public int bankerOffer()
    {
        double avg = getAverage(); 
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

        return (int)avg-random;
    }

}
