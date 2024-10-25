package controller.cashier;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.CashierService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CashierLoginFormController implements Initializable {

    @FXML
    private Label lblWrongPassword;

    @FXML
    private TextField txtGmailAddress;

    @FXML
    private TextField txtPassword;

    @FXML
    void btnForgotPswOnMouseClicked(MouseEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier/reset_password_form.fxml"))));
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
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier/cashier_menu_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            lblWrongPassword.setVisible(true);
            txtPassword.clear();
            txtGmailAddress.clear();
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
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dash_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblWrongPassword.setVisible(false);
    }
}
