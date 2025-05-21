/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controller;

/**
 *
 * @author benma
 */

import model.GameLogic;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.Scanner;



public class Main {

    /**
     * @param args the command line arguments
     */
    
    
    // Initialising variables
    
    public static Random rand = new Random();
    public static Player player = new Player();
    public static Scores scores = new Scores();
    public static Scanner scan = new Scanner(System.in);
    public static LinkedHashMap<String, Integer> playerInfo = new LinkedHashMap<>();
    
    public static void main(String[] args) {
        // TODO code application logic here
        Boxes.initialiseBoxes();
        
        playerInfo = scores.getScores();
        scores.printTopScores();
        
        System.out.println("Hello, Welcome to Deal or No Deal!");
        player.setName(GameLogic.initialisePlayer());
        
        Boxes.displayPlayerBox();
        Boxes.displayBoxes();
        GameLogic.gameLogic();

        scan.close();
    }
    
}
