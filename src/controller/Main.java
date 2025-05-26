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
        DOND_Model model = new DOND_Model();
        DOND_View view = new DOND_View();
        DOND_Controller controller = new DOND_Controller(view, model);
                
        // Sets visiable after setup
        view.setVisible(true);
    }
    
}
