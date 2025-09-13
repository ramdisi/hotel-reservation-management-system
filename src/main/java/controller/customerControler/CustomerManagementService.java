package controller.customerControler;

import javafx.collections.ObservableList;
import model.CustomerDetails;

public interface CustomerManagementService {
    ObservableList<CustomerDetails> getDetails();
    void updateDetails(String name,String email,String phone,String address,String phoneSelected);
    void deleteDetails(String phone);
    void addDetails(String name,String email,String phone,String address);
}
