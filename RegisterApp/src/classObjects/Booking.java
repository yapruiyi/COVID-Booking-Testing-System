package classObjects;

import Covid.CovidTest;
import User.Customer;

import java.util.Date;

public class Booking {
    private String id, smsPin, status, Notes;
    private AdditionalInfo additionalInfo;
    private Customer customer;
    private Date createdAt, updatedAt, startTime;
    private CovidTest covidTest;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSmsPin() {
        return smsPin;
    }

    public void setSmsPin(String smsPin) {
        this.smsPin = smsPin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNotes() {
        return Notes;
    }

    public void setNotes(String notes) {
        Notes = notes;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(AdditionalInfo additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public CovidTest getCovidTest() {
        return covidTest;
    }

    public void setCovidTest(CovidTest covidTest) {
        this.covidTest = covidTest;
    }

    public Booking(String id, Customer customer, Date createdAt,
                   Date updatedAt, Date startTime, String smsPin, String status, CovidTest covidTest, String Notes, AdditionalInfo additionalInfo){
        this.id = id;
        this.customer = customer;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.startTime = startTime;
        this.smsPin = smsPin;
        this.status = status;
        this.covidTest = covidTest;
        this.Notes = Notes;
        this.additionalInfo = additionalInfo;

    }
}
