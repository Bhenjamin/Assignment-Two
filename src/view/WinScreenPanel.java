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
import model.Player;

/**
 *
 * @author Te One
 */
public class WinScreenPanel {
    public WinScreenPanel(){};
    private final JPanel winScreenPanel = new JPanel();
    private final JLabel headingText = new JLabel();
    
    public void build (Player player){
        
        headingText.removeAll();
        
        winScreenPanel.setLayout(new BorderLayout(20, 20));
        
        headingText.setText("You won $"+player.getOfferTaken());
        headingText.setFont(new Font("Arial", Font.BOLD, 32));
        
        winScreenPanel.add(headingText, BorderLayout.CENTER);
        
    }
}
