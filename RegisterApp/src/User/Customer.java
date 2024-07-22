package User;

import classObjects.AdditionalInfo;
import classObjects.Address;
import classObjects.Booking;

public class Customer extends User{


    public Customer(String id, String givenName, String familyName, String userName, String phoneNumber, boolean isCustomer, boolean isReceptionist, boolean isHealthCareWorker, AdditionalInfo additionalInfo) {
        super(id,givenName,familyName,userName,phoneNumber,isCustomer, isReceptionist, isHealthCareWorker, additionalInfo);
    }
}
