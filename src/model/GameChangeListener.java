/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.ArrayList;
/**
 *
 * @author benma
 */
public interface GameChangeListener {
    void onUserNameEntered(String username);
    void onBoxClicked(int boxNumber);
    void onRoundEnded(int bankerOffer);
    void onGameEnded(Player player);
    void onLeaderboardOpened(ArrayList<Player> topPlayers);
    void onPlayerSearch(Player playerName);
}
