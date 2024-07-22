package User;

import User.DeleteBookingController;
import User.DeleteBookingModel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancelBookingView extends JFrame {

    private JTextField givenNameField,familyNameField,phoneField, smsPinField;
    private JButton deleteBtn;

    public String getGivenNameField() {
        return givenNameField.getText();
    }

    public String getFamilyNameField() {
        return familyNameField.getText();
    }

    public String getPhoneField() {
        return phoneField.getText();
    }

    public String getSmsPinField() {
        return smsPinField.getText();
    }

    public JButton getDeleteBtn() {
        return deleteBtn;
    }

    public CancelBookingView(){
        JPanel jPanelCancel = new JPanel();
        jPanelCancel.setLayout(null);
        setTitle("Booking Cancellation");
        setSize(500, 350);

        JLabel givenNameLabel = new JLabel("Given name: ");
        givenNameLabel.setBounds(50,20,100,25);
        JLabel familyNameLabel = new JLabel("Family name: ");
        familyNameLabel.setBounds(50,60,100,25);
        JLabel phoneLabel = new JLabel("Phone No: ");
        phoneLabel.setBounds(50,100,100,25);
        JLabel smsPinLabel = new JLabel("SMS PIN: ");
        smsPinLabel.setBounds(50,140,100,25);

        givenNameField = new JTextField(50);
        givenNameField.setBounds(150,20, 200, 25);
        familyNameField = new JTextField(50);
        familyNameField.setBounds(150,60, 200, 25);
        phoneField = new JTextField(50);
        phoneField.setBounds(150,100, 200, 25);
        smsPinField = new JTextField(50);
        smsPinField.setBounds(150,140, 200, 25);

        deleteBtn = new JButton("Cancel Booking");
        deleteBtn.setBounds(100,180,200,25);

        jPanelCancel.add(givenNameField);
        jPanelCancel.add(givenNameLabel);
        jPanelCancel.add(familyNameField);
        jPanelCancel.add(familyNameLabel);
        jPanelCancel.add(phoneField);
        jPanelCancel.add(phoneLabel);
        jPanelCancel.add(smsPinField);
        jPanelCancel.add(smsPinLabel);
        jPanelCancel.add(deleteBtn);
        add(jPanelCancel);
    }
}
