/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package view;
import java.awt.event.ActionListener;
import java.swing.*;

/**
 *
 * @author benma
 */

public class DOND_View {

    /**
     * @param args the command line arguments
     */
    private JFrame gameWindow;
    private JPanel Box;
    private JLabel bankerOffer;
    
    private ResultSet boxesAmounts;
   
    // Contains all the components that require actions listeners
    public void addActionListener(ActionListener listener) {
        this.Box.addActionListener(listener);
    }
    
    public void renderBoxes(){
        
    }
    
}
