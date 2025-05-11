package dealnodealgui;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author benma
 */
import java.io.*;
import java.util.LinkedHashMap;
public class Scores extends DealNoDealGUI
{   
    // Temporary plater LinkedHashMap
    LinkedHashMap<String, Integer> playerInfo = new LinkedHashMap<>();
    
    // Gets the player money winnings by reading off the text file
    public LinkedHashMap<String, Integer> getScores() throws FileNotFoundException, IOException
    {

        BufferedReader br = new BufferedReader(new FileReader("./resources/dondScores.txt"));

        String line;
        String[] score;

        // Runs until the end of the text file
        while ((line = br.readLine()) != null){
            // Splits each line by the space
            // then adds it to the LinkedHashMap which preserves the list order
            score = line.split(" ");
            // Converts the string to a integer when adding to
            // the LinkedHashMap
            playerInfo.put(score[0], Integer.valueOf(score[1]));
        }

        br.close();
        return playerInfo;
    }

    // Saves the new players' score after the game is complete
    public void saveScores(LinkedHashMap<String, Integer> playerInfo) throws FileNotFoundException, IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter("./resources/dondScores.txt"));
        // Adds the player amount of money the player won
        String name = player.getName();
        int moneyAmountWon = player.getOfferTaken();

        playerInfo.put(name,moneyAmountWon);

        for (String key : playerInfo.keySet()){
            bw.write(key+" "+playerInfo.get(key));
            bw.newLine();
        }

        bw.close();
     
    }
    
    // Gets the top three scores
    public String[] getTopScores()
    {
        // Adding values to a temporary LinkedHashMap so I don't interfer with them
        LinkedHashMap<String, Integer> tempList = new LinkedHashMap<>(playerInfo);
        // Stores the top three scores as Strings
        String[] topScores = new String[3];
        // Used to record the highest values 
        String keyname;
        Integer greatestValue;
        int counter = 0;
        
        while (counter < 3) {
            greatestValue = Integer.MIN_VALUE;
            keyname = "";
            for (String key : tempList.keySet())
            {
                if (tempList.get(key) > greatestValue){
                    keyname = key;
                    greatestValue = tempList.get(key);
                }
            }

            topScores[counter] = keyname+": $"+greatestValue.toString();
            tempList.remove(keyname);
            counter++;
        }
        return topScores;
    }
    
    // Prints the top three player scores
    public void printTopScores()
    {
        String[] topThree = getTopScores();
        int count = 1;

        System.out.println("Top Three Winners:");
        System.out.println("-------------------");
        for(String index : topThree)
        {
            System.out.print(count+++". ");
            System.out.println(index);
        }
        System.out.println("");
    }
}

