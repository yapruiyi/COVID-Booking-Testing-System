package User;

import API.readAPIkey;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CancelBookingController {
    private CancelBookingModel cancelBookingModel;
    private CancelBookingView view;


    public CancelBookingController(CancelBookingModel cancelBookingModel, CancelBookingView view){
        this.view = view;
        this.cancelBookingModel = cancelBookingModel;
    }


    public void start() throws Exception {
        view.setVisible(true);
        view.getDeleteBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String givenName = view.getGivenNameField();
                String familyName = view.getFamilyNameField();
                String phoneNumber = view.getPhoneField();
                String smsPin = view.getSmsPinField();
                try {
                    cancelBookingModel.cancelBooking(givenName,familyName, phoneNumber, smsPin);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

    }
}
