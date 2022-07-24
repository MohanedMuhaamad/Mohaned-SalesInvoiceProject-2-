/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesinvoicemodel;

//import java.util.ArrayList;

/**
 *
 * @author Heba
 */
public class Item {
    
   
    private String Item;
    private double Fees;
    private int Countt;
    private Invoice invoice;
    //private ArrayList <Line> lines;

    public Item() {
    }


    public Item(String Item, double Price, int Count, Invoice invoice) {
      
        this.Item = Item;
        this.Fees = Price;
        this.Countt = Count;
        this.invoice = invoice;
    }

     public double getItemCount (){
    return Fees * Countt;
    }
    public int getCountt() {
        return Countt;
    }

    public void setCountt(int Count) {
        this.Countt = Count;
    }

  
    public String getItem() {
        return Item;
    }

    public void setItem(String Item) {
        this.Item = Item;
    }

    public double getFees() {
        return Fees;
    }

    public void setFees(double Price) {
        this.Fees = Price;
    }

    @Override
    public String toString() {
        return "Line{" + "Num=" + invoice.getid() + ", Item=" + Item + ", Price=" + Fees + ", Count=" + Countt + '}';
    }

    public Invoice getInvoice() {
        return invoice;
    }
    
    
    public String getCSV(){
   
   return invoice.getid() + "," + Item + "," + Fees + "," + Countt;
   }
    
}
