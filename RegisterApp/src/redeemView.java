import User.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class redeemView extends JFrame {
    public redeemView(){
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        setTitle("Redeem Test Kit");
        setSize(500, 350);
        JLabel givenNameLabel = new JLabel("Given name: ");
        givenNameLabel.setBounds(50,20,100,25);
        JLabel familyNameLabel = new JLabel("Family name: ");
        familyNameLabel.setBounds(50,60,100,25);
        JLabel phoneLabel = new JLabel("Phone No: ");
        phoneLabel.setBounds(50,100,100,25);
        JLabel qrLabel = new JLabel("QR Code: ");
        qrLabel.setBounds(50,140,100,25);

        JTextField givenNameField = new JTextField(50);
        givenNameField.setBounds(150,20, 200, 25);
        JTextField familyNameField = new JTextField(50);
        familyNameField.setBounds(150,60, 200, 25);
        JTextField phoneField = new JTextField(50);
        phoneField.setBounds(150,100, 200, 25);
        JTextField qrField = new JTextField(50);
        qrField.setBounds(150,140, 200, 25);


        JButton redeemBtn = new JButton("Redeem");
        redeemBtn.setBounds(150,260,200,25);


        redeemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User user = new User();
                try {
                    Redeemmodel redeemmodel;
                    String userID = user.findUserID(givenNameField.getText(),familyNameField.getText(),phoneField.getText());
                    if (userID.equals("")){
                        JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    }else{
                    //update the record for QR Code
                        redeemmodel = new Redeemmodel(userID,qrField.getText());
                    new redeemController().redeemQRCode(redeemmodel);
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            });

        jPanel.add(givenNameField);
        jPanel.add(givenNameLabel);
        jPanel.add(familyNameLabel);
        jPanel.add(familyNameField);
        jPanel.add(phoneLabel);
        jPanel.add(phoneField);
        jPanel.add(qrLabel);
        jPanel.add(qrField);
        jPanel.add(redeemBtn);
        add(jPanel);
        }
}


