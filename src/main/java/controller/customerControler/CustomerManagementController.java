package controller.customerControler;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.CustomerDetails;

import java.sql.*;

public class CustomerManagementController implements CustomerManagementService {
    public ObservableList<CustomerDetails> getDetails(){
        ObservableList<CustomerDetails> customerDetailsObservableList = FXCollections.observableArrayList();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            ResultSet resultSet = connection.prepareStatement("select * from Customers").executeQuery();
            while (resultSet.next()){
                customerDetailsObservableList.add(new CustomerDetails(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("address"),
                        resultSet.getString("phone")
                ));
            }
            return customerDetailsObservableList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateDetails(String name,String email,String phone,String address,String phoneSelected){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update Customers set name=?,email=?,phone=?,address=? where phone=?");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,phone);
            preparedStatement.setString(4,address);
            preparedStatement.setString(5,phoneSelected);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteDetails(String phone){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            connection.prepareStatement("delete from Customers where phone='"+phone+"'").execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addDetails(String name,String email,String phone,String address){
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into Customers (name,email,phone,address)values (?,?,?,?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,phone);
            preparedStatement.setString(4,address);
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
