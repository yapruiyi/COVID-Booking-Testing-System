import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class onsiteTestingView extends JFrame {

    public onsiteTestingView(String sessionID) throws Exception {
        String administererID = sessionID;
        setSize(500,400);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        String[] dropdownResult = {"POSITIVE","NEGATIVE", "PENDING"};
        String[] dropdownSymptom = {"MILD", "MODERATE","SEVERE"};
        String[] dropdownTest = {"PCR","RAT"};

        JLabel pinLabel = new JLabel("SMS PIN: ");
        pinLabel.setBounds(50,20,100,25);
        JLabel symptomLabel = new JLabel("Symptom: ");
        symptomLabel.setBounds(50,60,100,25);
        JLabel typeLabel = new JLabel("Type of Test: ");
        typeLabel.setBounds(50,100,100,25);
        JLabel resultLabel = new JLabel("Test Result: ");
        resultLabel.setBounds(50,140,100,25);


        JTextField pinField = new JTextField(50);
        pinField.setBounds(150,20, 200, 25);

        JComboBox<String> jComboSymptom = new JComboBox<>(dropdownSymptom);
        jComboSymptom.setBounds(150,60, 200, 25);
        JComboBox<String> jComboTest = new JComboBox<>(dropdownTest);
        jComboTest.setBounds(150,100, 200, 25);
        JComboBox<String> jComboResult = new JComboBox<>(dropdownResult);
        jComboResult.setBounds(150,140, 200, 25);

        JButton submitBtn = new JButton("Submit Result");
        submitBtn.setBounds(150,180,200,25);
        submitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Onsitetestingmodel onsitetestingmodel;
                onsiteTestingController testingHandler = new onsiteTestingController();
                try {
                    onsitetestingmodel = new Onsitetestingmodel(sessionID, pinField.getText(),jComboTest.getItemAt(jComboTest.getSelectedIndex()),
                            jComboResult.getItemAt(jComboResult.getSelectedIndex()),jComboSymptom.getItemAt(jComboSymptom.getSelectedIndex()));
                    testingHandler.submitTest(onsitetestingmodel);


                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        jPanel.add(pinField);
        jPanel.add(pinLabel);
        jPanel.add(resultLabel);
        jPanel.add(jComboResult);
        jPanel.add(symptomLabel);
        jPanel.add(jComboSymptom);
        jPanel.add(typeLabel);
        jPanel.add(jComboTest);

        jPanel.add(submitBtn);
        add(jPanel);
    }
}
