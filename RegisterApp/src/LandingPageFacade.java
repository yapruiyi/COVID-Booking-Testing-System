import TestingSite.TestingSiteRecords;
import TestingSite.searchTestingSitesView;
import User.AdminBookingFacade;
import User.CancelBookingController;
import User.CancelBookingModel;
import User.CancelBookingView;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * landing page that contains different sub system access
 */
public class LandingPageFacade extends JFrame {
    public LandingPageFacade(ObjectNode session){
        String sessionID = session.get("sub").textValue();
        JPanel jPanelLanding = new JPanel();
        jPanelLanding.setLayout(null);
        setTitle("Landing Page");
        setSize(500, 450);


        JButton searchBtn = new JButton("Search Testing site");
        searchBtn.setBounds(100,50,200,30);
        //System.out.println("my session is" + session);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TestingSiteRecords testingSiteRecords = new TestingSiteRecords();
                try {
                    //open a new jFrame that allow search for testing site records
                    new searchTestingSitesView(testingSiteRecords.getTestingSiteRecords()).setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        JButton bookingBtn = new JButton("On-site Booking");
        bookingBtn.setBounds(100,90,200,30);
        bookingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new BookingView().setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        if(!session.get("isReceptionist").asBoolean()){
            bookingBtn.setEnabled(false);
        }// remove access if user is not receptionist


        JButton testBtn = new JButton("On-site Testing");
        testBtn.setBounds(100,130,200,30);
        testBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new onsiteTestingView(sessionID).setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });


        JButton homeBookingBtn = new JButton("Home Booking");
        homeBookingBtn.setBounds(100,170,200,30);
        homeBookingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new HomebookingView().setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        JButton redeemBtn = new JButton("Redeem Test Kit");
        redeemBtn.setBounds(100,210,200,30);
        redeemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new redeemView().setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        JButton adminBtn = new JButton("Admin Page");
        adminBtn.setBounds(100,250,200,30);
        adminBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new AdminBookingFacade().setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        JButton cancelBtn = new JButton("Cancel Booking");
        cancelBtn.setBounds(100,290,200,30);
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    CancelBookingView view = new CancelBookingView();
                    CancelBookingModel model = new CancelBookingModel();
                    CancelBookingController controller = new CancelBookingController(model,view);
                    controller.start();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        JButton phoneBookingBtn = new JButton("Phone Booking");
        phoneBookingBtn.setBounds(100,330,200,30);
        phoneBookingBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new PhoneBookingView().setVisible(true);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        if(!session.get("isHealthcareWorker").asBoolean()){
            testBtn.setEnabled(false);
            homeBookingBtn.setEnabled(false);
            redeemBtn.setEnabled(false);
        }//remove access if user is not health care worker

        if(!session.get("isReceptionist").asBoolean()){
            adminBtn.setEnabled(false);
        }


        jPanelLanding.add(searchBtn);
        jPanelLanding.add(bookingBtn);
        jPanelLanding.add(testBtn);
        jPanelLanding.add(homeBookingBtn);
        jPanelLanding.add(redeemBtn);
        jPanelLanding.add(adminBtn);
        jPanelLanding.add(cancelBtn);
        jPanelLanding.add(phoneBookingBtn);
        add(jPanelLanding);
    }
}
