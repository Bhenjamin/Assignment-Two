/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.*;
/**
 *
 * @author benma
 */
public class sortByScore implements Comparator<Player>{
    // Used for seeing which players should go one the leaderboard
    @Override
    public int compare(Player a, Player b){
        // Sorts highest to lowest
        return b.getOfferTaken() - a.getOfferTaken();
    }
}
