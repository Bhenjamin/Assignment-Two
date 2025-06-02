package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author benma
 */
import java.util.ArrayList;
import java.util.Collections;


public class BoxModel {
    private ArrayList<Box> boxList;
    
    
    public BoxModel(){
        this.boxList = new ArrayList<>();
    }
    
    public void initialiseBoxes()
    {
        // Values $ amounts for the boxes 
        // Start with 25 boxes and choose one a the start
        int[] values = {
            1, 5, 10, 25, 50, 
            75, 100, 200, 300, 400,
            500, 750, 1000, 5000, 10000, 
            25000, 50000, 75000, 100000, 200000, 
            300000, 400000, 500000, 750000, 1000000
        };

        for (Integer value : values) {
            boxList.add(new Box(value));
        }

        // Randomises the order of the money in boxes
        Collections.shuffle(boxList);
    }
    
    public ArrayList<Box> getBoxList() {
        return boxList;
    }
    
}
