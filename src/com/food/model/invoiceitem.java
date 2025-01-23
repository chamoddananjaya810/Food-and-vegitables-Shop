/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.food.model;

/**
 *
 * @author ABC
 */
public class invoiceitem {

    /**
     * @return the sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return the productname
     */
    public String getProductname() {
        return productname;
    }

    /**
     * @param productname the productname to set
     */
    public void setProductname(String productname) {
        this.productname = productname;
    }

    /**
     * @return the size
     */
    public String getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(String size) {
        this.size = size;
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
     * @return the sizeOfProduct
     */
    public String getSizeOfProduct() {
        return sizeOfProduct;
    }

    /**
     * @param sizeOfProduct the sizeOfProduct to set
     */
    public void setSizeOfProduct(String sizeOfProduct) {
        this.sizeOfProduct = sizeOfProduct;
    }

    /**
     * @return the selling_Price
     */
    public Double getSelling_Price() {
        return selling_Price;
    }

    /**
     * @param selling_Price the selling_Price to set
     */
    public void setSelling_Price(Double selling_Price) {
        this.selling_Price = selling_Price;
    }

    /**
     * @return the total
     */
    public Double getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Double total) {
        this.total = total;
    }

 
 


    private String sid;
    private String productname;
    private String size;
    private String mesureType;
    private String sizeOfProduct;
    private Double selling_Price;
    private Double total;

    /**
     * @return the sid
     */
}
