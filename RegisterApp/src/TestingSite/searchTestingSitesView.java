package TestingSite;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class searchTestingSitesView extends JFrame {

    /**
     * the main GUI for testing site
     * @param testingSites
     */
    public searchTestingSitesView(ArrayList<TestingSiteModel> testingSites) {
        setSize(500,250);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        String[] dropdownOption = {"","Drive Through", "Walk-in", "Clinics", "GP", "Hospital"};
        ArrayList<TestingSiteModel> filteredList = new ArrayList<>();


        JLabel suburbLabel = new JLabel("Suburb");
        suburbLabel.setBounds(50,20,80,25);
        JLabel dropdownLabel = new JLabel("Facility Type");
        dropdownLabel.setBounds(50,60,80,25);

        JTextField suburbField = new JTextField(50);
        suburbField.setBounds(150,20, 165, 25);

        JComboBox<String> jComboBox = new JComboBox<>(dropdownOption);
        jComboBox.setBounds(150,60, 165, 25);

        JButton searchBtn = new JButton("Search");
        searchBtn.setBounds(150,100,100,25);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //for loop to filter result and add to filtered list
                for (TestingSiteModel data : testingSites) {
                    String suburb = data.getAddress().getSuburb().toLowerCase();
                    String suburbInput = suburbField.getText();
                    String facilityInput = jComboBox.getItemAt(jComboBox.getSelectedIndex());
                    String facilityType = data.getFacilityType().toLowerCase();
                    System.out.println(suburb);
                    System.out.println(suburbInput);
                    System.out.println(facilityType);
                    System.out.println(facilityInput);
                    if (suburb.contains(suburbInput.toLowerCase()) && !suburbInput.isEmpty()) {
                            filteredList.add(data);
                    }else if (facilityType.equals(facilityInput.toLowerCase())){
                        filteredList.add(data);
                    }
                }
                System.out.println(filteredList);
                new displayTestingSites(filteredList).setVisible(true);
                dispose();
            }
        });

        jPanel.add(dropdownLabel);
        jPanel.add(suburbLabel);
        jPanel.add(suburbField);
        jPanel.add(jComboBox);
        jPanel.add(searchBtn);
        add(jPanel);

    }
}
