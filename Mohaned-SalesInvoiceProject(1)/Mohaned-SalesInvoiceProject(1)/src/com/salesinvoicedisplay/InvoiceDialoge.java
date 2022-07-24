
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

public class InvoiceDialoge extends JDialog {
    private JTextField customerNameField;
    private JTextField invoicedaydateField;
    private JLabel customerNameLabel;
    private JLabel invoicedaydateLabel;
    private JButton okButton;
    private JButton cancelButton;

    public InvoiceDialoge(SalesInvoiceView frame) {
        customerNameLabel = new JLabel("Customer Name:");
        customerNameField = new JTextField(20);
        invoicedaydateLabel = new JLabel("Invoice Date:");
        invoicedaydateField = new JTextField(20);
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        
        okButton.setActionCommand("createInvoiceOK");
        cancelButton.setActionCommand("createInvoiceCancel");
        
        okButton.addActionListener(frame.getcontroller());
        cancelButton.addActionListener(frame.getcontroller());
        setLayout(new GridLayout(3, 2));
        
        add(invoicedaydateLabel);
        add(invoicedaydateField);
        add(customerNameLabel);
        add(customerNameField);
        add(okButton);
        add(cancelButton);
        
        pack();
        
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getInvoicedaydateField() {
        return invoicedaydateField;
    }
    
}
