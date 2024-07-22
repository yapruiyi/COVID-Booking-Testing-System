import TestingSite.*;
import User.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PhoneBookingView extends JFrame{
    boolean flag;

    public PhoneBookingView() throws Exception{
        setSize(500,400);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        TestingSiteRecords siteRecords = new TestingSiteRecords();
        ArrayList<TestingSiteModel> records = siteRecords.getTestingSiteRecords();
        String[] dropdownOption = new String[records.size()];
        for (int i = 0; i < records.size(); i++) {
            dropdownOption[i] = records.get(i).getName();

        }



        JLabel bookingIDLabel = new JLabel("Booking ID: ");
        bookingIDLabel.setBounds(50,20,100,25);
        JLabel pinLabel = new JLabel("SMS PIN: ");
        pinLabel.setBounds(50,60,100,25);
        JLabel dateLabel = new JLabel("Pick a Date: ");
        dateLabel.setBounds(50,100,100,25);
        JLabel timeLabel = new JLabel("Choose a time: ");
        timeLabel.setBounds(50,140,100,25);
        JLabel siteLabel = new JLabel("Testing Site: ");
        siteLabel.setBounds(50,180,100,25);

        JTextField bookingIDField = new JTextField(50);
        bookingIDField.setBounds(150,20, 200, 25);
        JTextField pinLabelField = new JTextField(50);
        pinLabelField.setBounds(150,60,100,25);

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        JFormattedTextField dateField = new JFormattedTextField(simpleDateFormat);
        dateField.setBounds(150,100,200,25);
        dateField.setText("2022-5-2");
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
        JFormattedTextField timeField = new JFormattedTextField(timeFormat);
        timeField.setBounds(150,140,200,25);
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

        JComboBox<String> jComboBox = new JComboBox<>(dropdownOption);
        jComboBox.setBounds(150,180, 200, 25);


        JButton changeBtn = new JButton("Change Booking");
        changeBtn.setBounds(150,220,200,25);
        changeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //BookingModel bookingModel;
                    PhoneBookingModel phoneBookingModel;
                    PhoneBookingController changebookinghandler = new PhoneBookingController();
                    String bookingID = bookingIDField.getText();
                    User user = new User();
                    String userID = user.findUserID(bookingID);


                    if(userID.equals("")){//if user id is invalid
                        JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    }
                    if(bookingID.equals("")){//if booking id is invalid
                        JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    }else { //proceed with cancelling booking, create http response with delete\
                        String dateStr = dateField.getText() + " " + timeField.getText();
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date date = format.parse(dateStr);
                        phoneBookingModel = new PhoneBookingModel(userID, bookingID,  jComboBox.getItemAt(jComboBox.getSelectedIndex()), date);
                        changebookinghandler.changeBooking(phoneBookingModel);
                        //System.out.println(jComboBox.getItemAt(jComboBox.getSelectedIndex()));
                    }


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        JButton checkBtn = new JButton("Check Booking");
        checkBtn.setBounds(150,260,200,25);
        checkBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    PhoneBookingModel phoneBookingModel;
                    PhoneBookingController checkphonebookinghandler = new PhoneBookingController();
                    String bookingID = bookingIDField.getText();
                    if(bookingID.equals("")){//if user id is invalid
                        JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    }else { //proceed with cancelling booking, create http response with delete\
                        phoneBookingModel = new PhoneBookingModel(bookingIDField.getText(), pinLabelField.getText());
                        flag = checkphonebookinghandler.checkPhoneBooking(phoneBookingModel);
                        if (flag)
                        {
                            System.out.println("change booking button enabled");
                            changeBtn.setEnabled(true);
                        }
                        else {
                            changeBtn.setEnabled(false);
                        }
                    }


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });









        changeBtn.setEnabled(false);
        jPanel.add(bookingIDLabel);
        jPanel.add(bookingIDField);
        jPanel.add(pinLabel);
        jPanel.add(pinLabelField);
        jPanel.add(dateLabel);
        jPanel.add(dateField);
        jPanel.add(timeLabel);
        jPanel.add(timeField);
        jPanel.add(siteLabel);
        jPanel.add(jComboBox);
        jPanel.add(checkBtn);
        jPanel.add(changeBtn);
        add(jPanel);
    }

}
