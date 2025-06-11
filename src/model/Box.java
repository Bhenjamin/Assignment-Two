package model;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author benma
 */
public class Box {
    private int value;
    private boolean isOpened;
    private boolean userBox = false;
    
    // Box Constructor
    public Box(int value){
        this.value = value;
        this.isOpened = false;
    }
    
    public int getValue() {
        return value;
    }

    public boolean isOpen() {
        return isOpened;
    }

    public void open() {
        this.isOpened = true;
    }

    public void setValue(int value) {
        this.value = value;
    }
    
    public void userBox() {
        this.userBox = true;
    }
    
    public boolean isUserBox() {
        return userBox;
    }
}
