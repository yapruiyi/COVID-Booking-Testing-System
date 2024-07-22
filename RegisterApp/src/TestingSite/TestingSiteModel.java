package TestingSite;

import classObjects.Address;

import java.util.ArrayList;

public class TestingSiteModel {
    private String id, name, availableDay;
    private Address address;
    private boolean isRAT;
    private boolean isPCR;
    private boolean onsiteBooking;
    private int waitingTime;
    private String facilityType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailableDay() {
        return availableDay;
    }

    public void setAvailableDay(String availableDay) {
        this.availableDay = availableDay;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public boolean isRAT() {
        return isRAT;
    }

    public void setRAT(boolean RAT) {
        isRAT = RAT;
    }

    public boolean isPCR() {
        return isPCR;
    }

    public void setPCR(boolean PCR) {
        isPCR = PCR;
    }

    public boolean isOnsiteBooking() {
        return onsiteBooking;
    }

    public void setOnsiteBooking(boolean onsiteBooking) {
        this.onsiteBooking = onsiteBooking;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public String getFacilityType() {
        return facilityType;
    }

    public void setFacilityType(String facilityType) {
        this.facilityType = facilityType;
    }

    public TestingSiteModel(String id, String name, String availableDay, Address address, boolean isRAT, boolean isPCR, boolean onsiteBooking, int waitingTime, String facilityType) {
        this.id = id;
        this.name = name;
        this.availableDay = availableDay;
        this.address = address;
        this.isRAT = isRAT;
        this.isPCR = isPCR;
        this.onsiteBooking = onsiteBooking;
        this.waitingTime = waitingTime;
        this.facilityType = facilityType;
    }
}
