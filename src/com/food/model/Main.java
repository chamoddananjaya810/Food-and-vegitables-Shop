/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.food.model;

/**
 *
 * @author ABC
 */
public class Main {

    public static void main(String[] args) {
        String value = "dvfdd";  // or input from the user

        try {
            int intValue = Integer.parseInt(value);
            System.out.println(value + " is a valid integer.");
        } catch (NumberFormatException e) {
            System.out.println(value + " is not a valid integer.");
        }
    }
}
