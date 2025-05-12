package controller;

import model.Banker;
import model.Boxes;
import java.io.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author benma
 */
public class GameLogic extends Main {
    
    // Initialising variables
    public static String answer;
    public static int offer;
    public static int round = 0;
    public static int questionCount = 0;
    
    // Controls the main flow of the game and decides whether 
    // to continue playing or end the game.
    public static void gameLogic()throws FileNotFoundException,IOException {
        round++;

        selectBoxes();
        Boxes.displayBoxes();
        Boxes.displayPlayerBox();
        
        offer = Banker.bankerOffer();
        if (takeOffer()) endGame();
        else if (round < 5) 
        {
            System.out.println("Very well then the game shall continue");
            Boxes.displayBoxes();
            gameLogic();
        } else
        {
            endGame();
        }
    }
    
    // Determinds the round number and the amount of questions for that round
    // Then asks them the questions
    public static void selectBoxes()
    {
        if (questionCount == 0)
        {
            if (round == 1) questionCount = 10;
            if (round == 2) questionCount = 6;
            if (round == 3) questionCount = 5;
            if (round == 4) questionCount = 2;
            if (round == 5)  {
                questionCount = 1;
                System.out.println("Final stretch! Open the last box");
            } else System.out.println("Please select "+questionCount+" boxes to be opened");
        } else{
            Boxes.displayBoxes();
            System.out.println((questionCount)+" Boxes left to select.");
        }

        String boxRemove = scan.nextLine();
        int number = 0;

        try {
            number = Integer.parseInt(boxRemove);
        } catch (NumberFormatException e)
        {
            invalidBox();
            return;
        }
        if (number <= 25 && number >= 1)
        {
            number--;
            if (!(boxList.get(number).isOpened()))
            {
                if (!(number == player.getBox()))
                {
                    Boxes.openBox(number);
                    questionCount--;
                    if (questionCount > 0)
                    {
                        selectBoxes();
                    }
                } else invalidBox();
            } else invalidBox();
        } else invalidBox();
    }
    
    // If there is an invalid input message is displayed and player asked again
    public static void invalidBox()
    {
        System.out.println("\nPlease use numbers to select an unopened box that isn't yours");
        selectBoxes();
    }
    
    // Gives the player the banker offer and then player choose when to take
    // the deal
    public static boolean takeOffer()
    {
        if (round == 5) return false;
        
        System.out.println("\nHere is the Banks offer $"+offer);
        System.out.println("Deal or No Deal?");
        answer = scan.nextLine();
        
        if (answer.equalsIgnoreCase("no deal"))
        {
            return false;
        }else if (answer.equalsIgnoreCase("deal"))
        {
            player.setOfferTaken(offer);
            return true;
        }else return takeOffer();
        

    }
    
    // Gets player input for name ensuring it doesn't have spaces
    public static String initialisePlayer(){
                
        System.out.println("What is your name?");
        String name = scan.nextLine();
        if (name.contains(" ")){
            System.out.println("\nPlease do not input spaces");
            return initialisePlayer();
        }else{
            return name;
        }
    }
    
    // Functionality for when the game ends, opens their box and determinds 
    // whether they 'won' or not, and savesscores and displays new scores
    public static void endGame() throws FileNotFoundException, IOException
    {
        // Gets the value of the player box from the boxList
        int playerBoxValue = boxList.get(player.getBox()).getValue();
        if (player.getOfferTaken() != 0)
        {
            System.out.println("Offer taken of "+player.getOfferTaken()+" taken . Now let open your box and see if you have won!");
            System.out.println("As a reminder here is your box");
            Boxes.displayPlayerBox();
            System.out.println("Now lets open it");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Boxes.openBox(player.getBox());
            Boxes.displayPlayerBox();
            if (boxList.get(player.getBox()).getValue() >= player.getOfferTaken())
            {
                System.out.println("Unfortunately you have lost :(");
            } else 
            {
                System.out.println("Congratulations! you won.");
            }
        }

        else{
            
            Boxes.openBox(player.getBox());
            // Sets the player money offer to their box value
            player.setOfferTaken(playerBoxValue);
            System.out.println("Now here is your box");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Boxes.displayPlayerBox();
        }

        scores.saveScores(playerInfo);
        scores.printTopScores();
        System.exit(0);
    }
}
