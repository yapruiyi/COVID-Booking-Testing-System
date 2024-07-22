package User;

import API.readAPIkey;
import TestingSite.TestingSiteModel;
import TestingSite.TestingSiteRecords;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class ModifyBookingController {
    final String rootUrl = "https://fit3077.com/api/v2";
    String myApiKey = new readAPIkey().getApiKey();
    private ModifyBookingModel model;
    private ModifyBookingView view;
    public ModifyBookingController(ModifyBookingModel model, ModifyBookingView view){
        this.model = model;
        this.view = view;
    }
    public void start(){
        view.setVisible(true);
        view.getBookingBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String givenName = view.getGivenNameField();
                String familyName = view.getFamilyNameField();
                String phoneNumber = view.getPhoneField();
                String smsPin = view.getSmsPinField();
                String date = view.getDateField();
                String time = view.getTimeField();
                String siteName = view.getSite();

                String dateStr = date + " " + time;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date parseDate = null;
                try {
                    parseDate = format.parse(dateStr);
                } catch (ParseException parseException) {
                    parseException.printStackTrace();
                }
                String siteID = "";
                for(TestingSiteModel site: view.getRecords()){
                    if(site.getName().equals(siteName)){
                        siteID = site.getId();
                        try {
                            model.modifyBooking(givenName,familyName, phoneNumber, smsPin, siteID,parseDate);
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }

                    }
                }
            }
        });
    }
}
