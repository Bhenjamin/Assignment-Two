/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.*;

/**
 *
 * @author Te One
 */
public class LoseScreenPanel {
    public LoseScreenPanel() {}
    private final JPanel loseScreenPanel = new JPanel();
    private final JLabel headingText = new JLabel();
    private final ArrayList<JButton> boxButton = new ArrayList<>();
    
    public void build(Player player, Banker bank, BoxModel box)
    {
        boxButton.clear();
        headingText.removeAll();
        
        loseScreenPanel.setLayout(new BorderLayout(20, 20));
        
        headingText.setText("You won $"+player.getOfferTaken() + 
                " $" + 
                (bank.getOffer()-player.getOfferTaken()) +
                " less than your box of $" +
                box.getBoxList().get(player.getBox()-1).getValue());
        headingText.setFont(new Font("Arial", Font.BOLD, 32));
        
        loseScreenPanel.add(headingText, BorderLayout.CENTER);
        
    }
}
