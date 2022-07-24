
package com.salesinvoicemodel;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Heba
 */
public class ItemsGridModel extends AbstractTableModel{
    
    private ArrayList<Item> items;
private String [] columns = {"No.","Item Name","Item Price","Count","Item Total"};

    public ItemsGridModel(ArrayList<Item> lines) {
        this.items = lines ;
    }

    public ArrayList<Item> getItems() {
        return items;
    }
    
    
    
    @Override
    public int getRowCount() {
    return items.size();  
        }

    @Override
    public int getColumnCount() {
return columns.length;
    }
    
     @Override
    public String getColumnName(int t){
        return columns [t];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    Item line = items.get(rowIndex); 
    switch (columnIndex){
        case 0:return line.getInvoice().getid();
        case 1:return line.getItem();
        case 2:return line.getFees();
         case 3:return line.getCountt();
          case 4:return line.getItemCount();
          default : return "";
    }
    }
}
