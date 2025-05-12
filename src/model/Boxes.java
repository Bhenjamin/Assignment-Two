package model;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author benma
 */
import controller.Main;
import java.util.Collections;


public class Boxes extends Main {

    public static void initialiseBoxes()
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

    public static void displayBoxes()
    {
        int SIZE = 5;
        int counter = 0;
        int spaces;
        String boxOutput;
        int padding = 4;

        
        // Prints the boxes out
        System.out.println("\nHere are the current Boxes:");
        for(int col=0; col<SIZE; col++){
            for (int row=0; row<SIZE; row++){
                boxOutput = checkBox(counter);
                System.out.print(boxOutput);
                spaces = (getMaxBoxLength()-boxOutput.length())+padding;
                System.out.print(" ".repeat(spaces));
                counter++;
            }

            System.out.println();
        }
    }
    
    // Checks whether the box has been opened
    public static String checkBox(int index)
    {
        if(index == player.getBox()){
            return ("[Your Box]");
        }
        
        Box boxIndex = boxList.get(index);
        if (!boxIndex.isOpened())
        {
            return ("[Box "+(index+1)+"]");
        }
        return ("[$"+boxIndex.getValue()+"]");
    }
    
    // Gets the maximum length of the boxes for display
    public static int getMaxBoxLength()
    {
        int maxBoxLength = 0;
        String boxOutput;

        for (int i=0; i<5; i++){
            boxOutput = checkBox(i);
            if (boxOutput.length() > maxBoxLength){
                maxBoxLength = boxOutput.length();
            }  
        }

        return maxBoxLength;
    }
    
    // Opens the box
    public static void openBox(int boxIndex){
        // opens a box revealing the cash value
        boxList.get(boxIndex).open();
    }
    
    // Displays the players box
    public static void displayPlayerBox()
    {
        Box playerBox = boxList.get(player.getBox());
        if (playerBox.isOpened())
        {
            System.out.println("[$"+playerBox.getValue()+"]");
        } else System.out.println("Your box is [Box "+(player.getBox()+1)+"]");
    }

}
