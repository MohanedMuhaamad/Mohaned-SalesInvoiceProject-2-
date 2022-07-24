
package com.salesinvoicedisplay;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Heba
 */



/**
 *
 * @author DELL
 */
public class InvoiceItemDialog extends JDialog{
    private JTextField itemNameField;
    private JTextField itemCountField;
    private JTextField itemFeesField;
    private JLabel itemNameLabel;
    private JLabel itemCountLabel;
    private JLabel itemFeesLabel;
    private JButton okButton;
    private JButton cancelButton;
    
    public InvoiceItemDialog(SalesInvoiceView frame) {
        itemNameField = new JTextField(20);
        itemNameLabel = new JLabel("Item Name");
        
        itemCountField = new JTextField(20);
        itemCountLabel = new JLabel("Item Count");
        
        itemFeesField = new JTextField(20);
        itemFeesLabel = new JLabel("Item Price");
        
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("createLineOK");
        cancelButton.setActionCommand("createLineCancel");
        
        okButton.addActionListener(frame.getcontroller());
        cancelButton.addActionListener(frame.getcontroller());
        setLayout(new GridLayout(4, 2));
        
        add(itemNameLabel);
        add(itemNameField);
        add(itemCountLabel);
        add(itemCountField);
        add(itemFeesLabel);
        add(itemFeesField);
        add(okButton);
        add(cancelButton);
        
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getItemCountField() {
        return itemCountField;
    }

    public JTextField getItemFeesField() {
        return itemFeesField;
    }
}
