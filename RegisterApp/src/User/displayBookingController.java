package User;

import API.readAPIkey;
import TestingSite.TestingSiteModel;
import classObjects.AdditionalInfo;
import classObjects.Address;
import classObjects.Booking;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class displayBookingController {
    private displayBookingModel model;
    private  displayBookingView view;
    public displayBookingController(displayBookingModel model, displayBookingView view){
        this.view = view;
        this.model = model;
    }

    public void start() throws Exception {
        view.setBookingList(model.getBookingRecords());
        view.displayTable();
        view.setVisible(true);
    }

}
