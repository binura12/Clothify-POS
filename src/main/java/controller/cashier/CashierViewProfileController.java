package controller.cashier;

import dto.Admin;
import dto.Cashier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import repository.custom.CashierDao;
import repository.custom.impl.AdminDaoImpl;
import repository.custom.impl.CashierDaoImpl;
import service.ServiceFactory;
import service.custom.AdminService;
import service.custom.CashierService;
import util.ServiceType;

import java.io.IOException;

public class CashierViewProfileController {

    @FXML
    private Label lblAge;

    @FXML
    private Label lblCompany;

    @FXML
    private Label lblGmail;

    @FXML
    private Label lblID;

    @FXML
    private Label lblName;

    @FXML
    void initialize() {
        CashierService cashierService = ServiceFactory.getInstance().getServiceType(ServiceType.cashier);
        String loggedInCashierEmail = CashierDaoImpl.CashierLoggedInEmail;
        Cashier cashier = cashierService.loadCashierProfile(loggedInCashierEmail);

        lblID.setText("ID : " + cashier.getId());
        lblAge.setText("Age : " + cashier.getAge());
        lblName.setText("Name : " + cashier.getFullname());
        lblCompany.setText("Company : " + cashier.getCompany());
        lblGmail.setText("Email : " + cashier.getEmail());
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier/cashier_menu_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dash_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
