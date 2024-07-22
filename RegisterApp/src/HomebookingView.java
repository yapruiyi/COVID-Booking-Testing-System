import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import User.User;

public class HomebookingView extends JFrame{
    public HomebookingView() throws Exception {
    setSize(500,400);
    JPanel jPanel = new JPanel();
    jPanel.setLayout(null);
    String[] dropdownOption = new String[2];
    dropdownOption[0] = "Receive kit from site";
    dropdownOption[1] = "Self-bring testkit";

    JLabel givenNameLabel = new JLabel("Given name: ");
    givenNameLabel.setBounds(50,20,100,25);
    JLabel familyNameLabel = new JLabel("Family name: ");
    familyNameLabel.setBounds(50,60,100,25);
    JLabel phoneLabel = new JLabel("Phone No: ");
    phoneLabel.setBounds(50,100,100,25);
    JTextField givenNameField = new JTextField(50);
    givenNameField.setBounds(150,20, 200, 25);
    JTextField familyNameField = new JTextField(50);
    familyNameField.setBounds(150,60, 200, 25);
    JTextField phoneField = new JTextField(50);
    phoneField.setBounds(150,100, 200, 25);


    JLabel label = new JLabel("Testing Preference:");
    label.setBounds(50,220,100,25);

    JComboBox<String> jComboBox = new JComboBox<>(dropdownOption);
    jComboBox.setBounds(150,220, 200, 25);

    JButton bookingBtn = new JButton("register");
    bookingBtn.setBounds(150,260,200,25);


    bookingBtn.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Homebookingmodel homebookingmodel;
            HomeBookingController staff = new HomeBookingController();

            if (jComboBox.getSelectedIndex() == 0){
                try {
                    User user = new User();
                    String userID = user.findUserID(givenNameField.getText(), familyNameField.getText(), phoneField.getText());
                    if(userID.equals("")){//if user id is invalid
                        JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    }else {
                        //update the record
                        homebookingmodel = new Homebookingmodel(userID, false);
                        staff.updateSystem(homebookingmodel);
                    }
                } catch (Exception ioException) {
                    ioException.printStackTrace();
                }
            }
        }
    });

    jPanel.add(givenNameField);
    jPanel.add(givenNameLabel);
    jPanel.add(familyNameLabel);
    jPanel.add(familyNameField);
    jPanel.add(phoneLabel);
    jPanel.add(phoneField);
    jPanel.add(label);
    jPanel.add(jComboBox);
    jPanel.add(bookingBtn);
    add(jPanel);

    }
}
