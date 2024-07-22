package User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminBookingFacade extends JFrame {
    public AdminBookingFacade(){
        JPanel jPanelAdmin = new JPanel();
        jPanelAdmin.setLayout(null);
        setTitle("Admin Booking Interface");
        setSize(500, 350);

        JButton viewBtn = new JButton("View Booking");
        viewBtn.setBounds(100,50,200,30);
        //System.out.println("my session is" + session);
        viewBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayBookingModel model = new displayBookingModel();
                displayBookingView view = new displayBookingView();
                displayBookingController displayController = new displayBookingController(model,view);
                try {
                    displayController.start();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

        JButton deleteBtn = new JButton("Delete Booking");
        deleteBtn.setBounds(100,90,200,30);

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeleteBookingView view = new DeleteBookingView();
                DeleteBookingModel model = new DeleteBookingModel();
                DeleteBookingController controller = new DeleteBookingController(model,view);
                controller.start();
            }
        });

        JButton modifyBtn = new JButton("Modify Booking");
        modifyBtn.setBounds(100,130,200,30);
        //System.out.println("my session is" + session);
        modifyBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ModifyBookingView view = new ModifyBookingView();
                    ModifyBookingModel model = new ModifyBookingModel();
                    ModifyBookingController controller = new ModifyBookingController(model,view);
                    controller.start();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        jPanelAdmin.add(viewBtn);
        jPanelAdmin.add(deleteBtn);
        jPanelAdmin.add(modifyBtn);
        add(jPanelAdmin);
    }
}
