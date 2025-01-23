/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.food.model;

import java.util.Date;

/**
 *
 * @author ABC
 */
public class grnItem {

    /**
     * @return the productId
     */
    public String getProductId() {
        return productId;
    }

    /**
     * @param productId the productId to set
     */
    public void setProductId(String productId) {
        this.productId = productId;
    }

    /**
     * @return the GrnId
     */
    public String getGrnId() {
        return GrnId;
    }

    /**
     * @param GrnId the GrnId to set
     */
    public void setGrnId(String GrnId) {
        this.GrnId = GrnId;
    }

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the Size
     */
    public String getSize() {
        return Size;
    }

    /**
     * @param Size the Size to set
     */
    public void setSize(String Size) {
        this.Size = Size;
    }

    /**
     * @return the mesureValue
     */
    public String getMesureValue() {
        return mesureValue;
    }

    /**
     * @param mesureValue the mesureValue to set
     */
    public void setMesureValue(String mesureValue) {
        this.mesureValue = mesureValue;
    }

    /**
     * @return the mesureType
     */
    public String getMesureType() {
        return mesureType;
    }

    /**
     * @param mesureType the mesureType to set
     */
    public void setMesureType(String mesureType) {
        this.mesureType = mesureType;
    }

    /**
     * @return the BuyingPrice
     */
    public double getBuyingPrice() {
        return BuyingPrice;
    }

    /**
     * @param BuyingPrice the BuyingPrice to set
     */
    public void setBuyingPrice(double BuyingPrice) {
        this.BuyingPrice = BuyingPrice;
    }

    /**
     * @return the sellingPrice
     */
    public double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * @return the Date
     */
    public Date getDate() {
        return Date;
    }

    /**
     * @param Date the Date to set
     */
    public void setDate(Date Date) {
        this.Date = Date;
    }




  
    private String productId;
     private String GrnId;
    private String productName;
    private String category;
    private String Size;
    private String mesureValue;
    private String mesureType;
    private double BuyingPrice;
    private double sellingPrice;
    private Date Date;

}
