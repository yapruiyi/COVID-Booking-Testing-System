import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class LoginGUI extends JFrame{


    private final JTextField userText;
    private final JPasswordField pwText;


    /**
     * main GUI for login page
     */
    public LoginGUI() {

        setSize(500,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(50,20,80,25);
        JLabel pwLabel = new JLabel("Password");
        pwLabel.setBounds(50,60,80,25);


        userText = new JTextField(20);
        userText.setForeground(Color.GRAY);
        userText.setText("Enter your username");
        userText.setBounds(150,20, 165, 25);
        userText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (userText.getText().equals("Enter your username")){
                    userText.setText("");
                    userText.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (userText.getText().isEmpty()){
                    userText.setText("Enter your username");
                }
            }
        });

        pwText = new JPasswordField(20);
        pwText.setBounds(150,60, 165, 25);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150,100,100,25);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    LoginValidate loginValidate = new LoginValidate();
                    if(loginValidate.validateUser(userText, pwText)){

                        LandingPageFacade landingPage = new LandingPageFacade(loginValidate.session());
                        landingPage.setVisible(true);
                        dispose();
                    }else{
                        JOptionPane.showMessageDialog(null, "Invalid Credentials");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        jPanel.add(userLabel);
        jPanel.add(pwLabel);
        jPanel.add(userText);
        jPanel.add(pwText);
        jPanel.add(loginBtn);

        add(jPanel);
        setTitle("Login");
    }

}
