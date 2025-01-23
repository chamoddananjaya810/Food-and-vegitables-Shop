/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.food.component;

import com.formdev.flatlaf.FlatClientProperties;
import javax.swing.JButton;

/**
 *
 * @author ABC
 */
public class RoudedButon extends JButton{
    public RoudedButon(){
    
    init();
    }
    
    public void init(){
    this.putClientProperty("JButton.buttonType", "roundRect" );
    
    }
    
    
    
}
