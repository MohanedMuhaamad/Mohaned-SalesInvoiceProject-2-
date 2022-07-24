/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesinvoicemodel;

import java.util.ArrayList;

/**
 *
 * @author Heba
 */
public class Invoice {
    
    private int id;
    private String daydate;
 
    private String customerName;
    private ArrayList <Item> items;
    //private double invoiceTotal;

  
    public Invoice() {
    }

    public Invoice(int Num, String Date, String Customer) {
        this.id = Num;
        this.daydate = Date;
        this.customerName = Customer;
        
    }
    
    public double getInvoiceAmount (){
        double total = 0.0;
        for (Item line : getItems()){
            total +=line.getItemCount ();
                    }
    return total;
    }

     public ArrayList<Item> getItems() {
        if (items==null){
            items = new ArrayList<>();
        }
        return items;
    }
    
    public String getcustomerName() {
        return customerName;
    }

    public void setcustomerName(String Name) {
        this.customerName = customerName;
    }

    public int getid() {
        return id;
    }

    public void setid(int Num) {
        this.id = Num;
    }

    public String getdaydate() {
        return daydate;
    }

    public void setdaydate(String Date) {
        this.daydate = Date;
    }

    @Override
    public String toString() {
        return "Invoice{" + "Num=" + id + ", Date=" + daydate + ", Customer=" + customerName + '}';
    }

   public String getCSV(){
   
   return id + "," + daydate + "," + customerName;
   }
    
    
    
}
