/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import controller.DOND_Controller;
import model.DOND_Model;
import view.DOND_View;
/**
 *
 * @author benma
 */
public class Main {
    
    public static void main(String[] args){
        // This code is generate by ChatGPT
        // It checks if there a any other screen open and closes them
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Checks no other screen are open
            for (java.awt.Window w : java.awt.Window.getWindows()) {
                if (w instanceof javax.swing.JFrame) {
                    w.dispose(); // Force closes any open window
                }
            }
        
        
        DOND_Model model = new DOND_Model();
        DOND_View view = new DOND_View();
        DOND_Controller controller = new DOND_Controller(view, model);
                
        // Sets visiable after setup
        view.setVisible(true);
        });
        
    }
    
}
