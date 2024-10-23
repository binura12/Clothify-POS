package controller.admin;

import dto.Cashier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.CashierService;
import util.ServiceType;

import java.io.IOException;

public class CashierRegisterFormController {

    @FXML
    private Label lbltitle;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtGmailAddress;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnSignupOnAction(ActionEvent event) {
        CashierService cashierService = ServiceFactory.getInstance().getServiceType(ServiceType.cashier);
        Cashier cashier = new Cashier(
                txtName.getText(),
                txtGmailAddress.getText(),
                txtPassword.getText(),
                Integer.parseInt(txtAge.getText()),
                txtCompany.getText()
        );
        if (cashierService.addCashier(cashier)) {
            txtName.setText("");
            txtGmailAddress.setText("");
            txtPassword.setText("");
            txtAge.setText("");
            txtCompany.setText("");
            new Alert(Alert.AlertType.INFORMATION, "Cashier Added!").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Cashier Not Added!").show();
        }
    }

    public void btnHomeOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dash_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) ((javafx.scene.Node) actionEvent.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_menu_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
