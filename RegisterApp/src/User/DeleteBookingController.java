package User;

import API.readAPIkey;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DeleteBookingController {
    private DeleteBookingModel bookingModel;
    private DeleteBookingView view;
    public DeleteBookingController(DeleteBookingModel bookingModel, DeleteBookingView view){
        this.bookingModel = bookingModel;
        this.view = view;
    }

    public void start() {
        view.setVisible(true);
        view.getDeleteBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String givenName = view.getGivenNameField();
                String familyName = view.getFamilyNameField();
                String phoneNumber = view.getPhoneField();
                String smsPin = view.getSmsPinField();
                try {
                    bookingModel.deleteBooking(givenName,familyName, phoneNumber, smsPin);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });

    }

}
