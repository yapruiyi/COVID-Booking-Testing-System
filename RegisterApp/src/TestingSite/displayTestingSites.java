package TestingSite;

import javax.swing.*;
import java.util.ArrayList;

public class displayTestingSites extends JFrame {

    /**
     * display a table of testing sites that fulfills the search criteria
     * @param testingSites testing site array
     */
    public displayTestingSites(ArrayList<TestingSiteModel> testingSites){

        setSize(800,400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        setTitle("Testing Site Records");

        String[] column = {"Name", "Facility Type", "Address","Available Day", "RAT", "PCR", "On-site Booking", "Waiting Time"};
        Object[][] rows = new Object[100][100];
        for(int i = 0; i < testingSites.size();i++){
            TestingSiteModel data = testingSites.get(i);
            rows[i][0] = data.getName();
            rows[i][1] = data.getFacilityType();
            rows[i][2] = data.getAddress();
            rows[i][3] = data.getAvailableDay();
            rows[i][4] = data.isRAT();
            rows[i][5] = data.isPCR();
            rows[i][6] = data.isOnsiteBooking();
            rows[i][7] = data.getWaitingTime();
        }
        JTable recordTable =new JTable(rows,column);
        recordTable.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(recordTable);
        add(sp);
    }

}
