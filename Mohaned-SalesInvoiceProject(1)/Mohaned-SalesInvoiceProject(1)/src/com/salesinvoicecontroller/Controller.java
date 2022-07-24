/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.salesinvoicecontroller;

import com.salesinvoicedisplay.InvoiceDialoge;
import com.salesinvoicedisplay.InvoiceItemDialog;
import com.salesinvoicedisplay.SalesInvoiceView;
import com.salesinvoicemodel.Invoice;
import com.salesinvoicemodel.InvoicesGridModel;
import com.salesinvoicemodel.Item;
import com.salesinvoicemodel.ItemsGridModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener, ListSelectionListener{
    
    private SalesInvoiceView screen;
    private InvoiceDialoge invoDialoge;
    private InvoiceItemDialog itemDialoge;
    
    public Controller (SalesInvoiceView screen){
        this.screen=screen;
    }
    @Override
    public void actionPerformed (ActionEvent e){
        String actionCommand = e.getActionCommand();
        System.out.println(actionCommand);
        switch (actionCommand){
            case"Load File":
                loadFile();
            break;
            case"Save File":
                saveFile();
            break;
            case"Create New Invoice":
                newInvoice();
            break;
            case"Delete Invoice":
                deleteInvoice();
            break;
            case"Create New Item":
                newItem();
            break;
            case"Delete Item":
                deleteItem();
            break;
             case"createInvoiceCancel":
                invoCancel();
            break;
            case"createInvoiceOK":
                invoOK();
            break;
             case"createLineOK":
                itemOK();
            break;
            case"createLineCancel":
                itemCancel();
            break;
        }
    }
    
    
     @Override
    public void valueChanged(ListSelectionEvent e) {
    int selectedIndex = screen.getInvoGrid().getSelectedRow();
    if (selectedIndex != -1){
    System.out.println("You have selected row: " + selectedIndex);
    Invoice currentInvoice = screen.getInvoices().get(selectedIndex);
    screen.getinvoiceIdValueLabel().setText(""+currentInvoice.getid());
    screen.getinvoicedaydateValueLabel().setText(currentInvoice.getdaydate());
    screen.getcustomerNameValueLabel().setText(currentInvoice.getcustomerName());
    screen.getinvoiceAmountValueLabel().setText(""+currentInvoice.getInvoiceAmount());
    ItemsGridModel linesTableModel = new ItemsGridModel(currentInvoice.getItems());
      screen.getItemsGrid().setModel(linesTableModel);
      linesTableModel.fireTableDataChanged();
    }
    }
    

    private void loadFile() {
        JFileChooser fc = new JFileChooser(); 
        try{
        int res = fc.showOpenDialog(screen);
        if (res == JFileChooser.APPROVE_OPTION){
            File headerFile = fc.getSelectedFile();
            Path headerPath = Paths.get(headerFile.getAbsolutePath());
            List<String> headerLines = Files.readAllLines(headerPath);
            ArrayList<Invoice>invoicesArray = new ArrayList<>();
            for (String headerLine: headerLines){
            String [] headerParts = headerLine.split(",");
            int invoiceNumber = Integer.parseInt(headerParts[0]);
            String invoiceDate = headerParts[1];
            String customerName = headerParts[2];
            Invoice invoice = new Invoice (invoiceNumber,invoiceDate,customerName); 
            invoicesArray.add(invoice);
            }
            res = fc.showOpenDialog(screen);
            if (res == JFileChooser.APPROVE_OPTION){
            File lineFile = fc.getSelectedFile();
            Path linePath = Paths.get(lineFile.getAbsolutePath());
            List<String>lineLines = Files.readAllLines(linePath);
            for (String lineLine : lineLines){
            String lineParts [] = lineLine.split(",");
            int invoiceNum = Integer.parseInt(lineParts[0]);
            String itemName = lineParts[1];
            double itemPrice = Double.parseDouble(lineParts[2]);
            int count = Integer.parseInt(lineParts[3]);
            Invoice inv = null;
            for (Invoice invoice :invoicesArray){
            if (invoice.getid()==invoiceNum){
                inv = invoice;
                break;
            }
            }
            Item line = new Item (itemName,itemPrice,count, inv);
            inv.getItems().add(line);
            }
            }
            
            screen.setInvoices(invoicesArray);
            InvoicesGridModel invoicesTableModel = new InvoicesGridModel(invoicesArray);
            screen.setInvoicesGridModel(invoicesTableModel);
            screen.getInvoGrid().setModel(invoicesTableModel);
            screen.getInvoicesGridModel().fireTableDataChanged();
            
        }
        }catch (IOException ex){
            ex.printStackTrace();
    }
    }

    private void saveFile() {
        ArrayList<Invoice> invoices = screen.getInvoices();
        String headers = "";
        String lines = "";
        for (Invoice invoice : invoices){
        String invCSV = invoice.getCSV();
        headers += invCSV;
        headers += "\n";
        
        for (Item line : invoice.getItems()){
        String lineCSV = line.getCSV();
        lines += lineCSV;
        lines += "\n";
        }
        }    
        try{
        JFileChooser fc = new JFileChooser();
        int result = fc.showSaveDialog(screen);
        if (result == JFileChooser.APPROVE_OPTION){
        File headerFile = fc.getSelectedFile();
        FileWriter hfw = new FileWriter(headerFile);
        hfw.write(headers);
        hfw.flush();
        hfw.close();
        result = fc.showSaveDialog(screen);
        if (result == JFileChooser.APPROVE_OPTION){
        File lineFile = fc.getSelectedFile();
        FileWriter lfw = new FileWriter(lineFile);
        lfw.write(lines);
        lfw.flush();
        lfw.close();
        
        }
        }
        }catch (Exception ex){
        
        }
        }

    private void newInvoice() {
        
        invoDialoge = new InvoiceDialoge(screen);
        invoDialoge.setVisible(true);
        }

    private void deleteInvoice() {
        int selectedRow = screen.getInvoGrid().getSelectedRow();
        if (selectedRow != -1){
            screen.getInvoices().remove(selectedRow);
            screen.getInvoicesGridModel().fireTableDataChanged();
        }
        }

    private void newItem() {
        itemDialoge = new InvoiceItemDialog(screen);
        itemDialoge.setVisible(true);
        }

    private void deleteItem() {
        int selectedRow = screen.getItemsGrid().getSelectedRow();
        if (selectedRow != -1){
            ItemsGridModel linesTableModel = (ItemsGridModel) screen.getItemsGrid().getModel();
            linesTableModel.getItems().remove(selectedRow);
            linesTableModel.fireTableDataChanged();
            screen.getInvoicesGridModel().fireTableDataChanged();
        }
        }

    private void invoCancel() {
        invoDialoge.setVisible(false);
        invoDialoge.dispose();
        invoDialoge = null;
        }

    
    private void invoOK() {
        String date = invoDialoge.getInvoicedaydateField().getText();
        String customer = invoDialoge.getCustomerNameField().getText();
        int num = screen.getNextInvoiceId();
        
        Invoice invoice = new Invoice (num,date,customer);
        screen.getInvoices().add(invoice);
        screen.getInvoicesGridModel().fireTableDataChanged();
        invoDialoge.setVisible(false);
        invoDialoge.dispose();
        invoDialoge = null;
        }

    private void itemOK() {
        String item = itemDialoge.getItemNameField().getText();
        String countStr = itemDialoge.getItemCountField().getText();
        String priceStr = itemDialoge.getItemFeesField().getText();
        int count = Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
        int selectedInvoice = screen.getInvoGrid().getSelectedRow();
        if (selectedInvoice != -1){
            Invoice invoice = screen.getInvoices().get(selectedInvoice);
            Item line = new Item(item, price, count, invoice);
            invoice.getItems().add(line);
            ItemsGridModel linesTableModel = (ItemsGridModel)screen.getItemsGrid().getModel();
            linesTableModel.fireTableDataChanged();
            screen.getInvoicesGridModel().fireTableDataChanged();
        }
        
        itemDialoge.setVisible(false);
        itemDialoge.dispose();
        itemDialoge = null;
        }

    private void itemCancel() {
        itemDialoge.setVisible(false);
        itemDialoge.dispose();
        itemDialoge = null;
        }

   
    
}
