package User;

import TestingSite.TestingSiteModel;
import TestingSite.TestingSiteRecords;

import javax.swing.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ModifyBookingView extends JFrame {
    private JTextField givenNameField,familyNameField,phoneField, smsPinField, site;
    private JComboBox<String> jComboBox;
    private JFormattedTextField dateField, timeField;
    private JButton bookingBtn;
    private ArrayList<TestingSiteModel> records;

    public ArrayList<TestingSiteModel> getRecords() {
        return records;
    }

    public JButton getBookingBtn() {
        return bookingBtn;
    }

    public String getSite(){
        return jComboBox.getItemAt(jComboBox.getSelectedIndex());
    }

    public String getDateField() {
        return dateField.getText();
    }

    public String getTimeField() {
        return timeField.getText();
    }

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

    public ModifyBookingView() throws Exception{
        setSize(500,400);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        TestingSiteRecords siteRecords = new TestingSiteRecords();
        records = siteRecords.getTestingSiteRecords();
        String[] dropdownOption = new String[records.size()];
        for (int i = 0; i < records.size(); i++) {
            dropdownOption[i] = records.get(i).getName();
        }

        JLabel givenNameLabel = new JLabel("Given name: ");
        givenNameLabel.setBounds(50,20,100,25);
        JLabel familyNameLabel = new JLabel("Family name: ");
        familyNameLabel.setBounds(50,60,100,25);
        JLabel phoneLabel = new JLabel("Phone No: ");
        phoneLabel.setBounds(50,100,100,25);
        JLabel pinLabel = new JLabel("SMS PIN: ");
        pinLabel.setBounds(50,140,100,25);
        JLabel dateLabel = new JLabel("Pick a Date: ");
        dateLabel.setBounds(50,180,100,25);
        JLabel timeLabel = new JLabel("Choose a time: ");
        timeLabel.setBounds(50,220,100,25);
        JLabel siteLabel = new JLabel("Testing Site: ");
        siteLabel.setBounds(50,260,100,25);


        givenNameField = new JTextField(50);
        givenNameField.setBounds(150,20, 200, 25);
        familyNameField = new JTextField(50);
        familyNameField.setBounds(150,60, 200, 25);
        phoneField = new JTextField(50);
        phoneField.setBounds(150,100, 200, 25);
        smsPinField = new JTextField(50);
        smsPinField.setBounds(150,140, 200, 25);


        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        dateField = new JFormattedTextField(simpleDateFormat);
        dateField.setBounds(150,180,200,25);
        dateField.setText("YYYY-MM-DD");
        dateField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                dateField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });
        Format timeFormat = new SimpleDateFormat("HH:mm:ss");
        timeField = new JFormattedTextField(timeFormat);
        timeField.setBounds(150,220,200,25);
        timeField.setText("08:00:00");
        //set a default value for the field
        timeField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                timeField.setText("");
            }

            @Override
            public void focusLost(FocusEvent e) {

            }
        });

        jComboBox = new JComboBox<>(dropdownOption);
        jComboBox.setBounds(150,260, 200, 25);

        bookingBtn = new JButton("Modify Booking");
        bookingBtn.setBounds(150,300,200,25);

        jPanel.add(givenNameField);
        jPanel.add(givenNameLabel);
        jPanel.add(familyNameLabel);
        jPanel.add(familyNameField);
        jPanel.add(phoneLabel);
        jPanel.add(phoneField);
        jPanel.add(smsPinField);
        jPanel.add(pinLabel);
        jPanel.add(dateLabel);
        jPanel.add(dateField);
        jPanel.add(timeLabel);
        jPanel.add(timeField);
        jPanel.add(siteLabel);
        jPanel.add(jComboBox);
        jPanel.add(bookingBtn);
        add(jPanel);
    }
}
