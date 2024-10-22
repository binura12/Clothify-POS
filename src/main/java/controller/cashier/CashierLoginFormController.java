package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.CashierService;
import util.ServiceType;

import java.io.IOException;

public class CashierLoginFormController {

    @FXML
    private Label lblWrongPassword;

    @FXML
    private TextField txtGmailAddress;

    @FXML
    private TextField txtPassword;

    @FXML
    void initialize() {
        lblWrongPassword.setVisible(false);
    }

    @FXML
    void btnForgotPswOnMouseClicked(MouseEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/cashier/reset_password_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String enteredGmail = txtGmailAddress.getText();
        String enteredPassword = txtPassword.getText();

        CashierService cashierService = ServiceFactory.getInstance().getServiceType(ServiceType.cashier);
        boolean isCashierFound = cashierService.loginCashier(enteredGmail, enteredPassword);
        if (isCashierFound) {
            // TODO Go to DashBoard
        } else {
            lblWrongPassword.setVisible(true);
            txtPassword.clear();
            txtGmailAddress.clear();
        }
    }
}