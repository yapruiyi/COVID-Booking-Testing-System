package User;

import TestingSite.TestingSiteModel;

import javax.swing.*;
import java.util.ArrayList;

public class displayBookingView extends JFrame{


    private ArrayList<displayBookingModel> bookingList;

    public void setBookingList(ArrayList<displayBookingModel> bookingList) {
        this.bookingList = bookingList;
    }
    public displayBookingView(){
    }

    public void displayTable(){
        setSize(800,400);
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        setTitle("Booking Records");

        String[] column = {"ID", "Customer Name", "Testing Site","Created At", "Updated At", "Start Time", "SMS PIN", "Status","Notes"};
        Object[][] rows = new Object[100][100];
        for(int i = 0; i < bookingList.size();i++){
            displayBookingModel data = bookingList.get(i);
            rows[i][0] = data.getId();
            rows[i][1] = data.getCustomer();
            rows[i][2] = data.getTestingSite();
            rows[i][3] = data.getCreatedAt();
            rows[i][4] = data.getUpdatedAt();
            rows[i][5] = data.getStartTime();
            rows[i][6] = data.getSmsPin();
            rows[i][7] = data.getStatus();
            rows[i][8] = data.getNotes();
        }
        JTable recordTable =new JTable(rows,column);
        recordTable.setBounds(30,40,200,300);
        JScrollPane sp = new JScrollPane(recordTable);
        add(sp);
    }
}
