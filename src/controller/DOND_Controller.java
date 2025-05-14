/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;
import java.awt.event.*;
import model.DOND_Model;
import view.DOND_View;
/**
 *
 * @author benma
 */
public class DOND_Controller implements ActionListener {
    
    public DOND_View view;
    public DOND_Model model;
    
    public DOND_Controller(View view, Model model) {
        this.view = view;
        this.model = model;
        this.view.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = "add in a function later";
        
        // Uses switch case to detemine what happens on action
        switch(command){
            case view.Box:
                
                return;
        }
    }
    
}
