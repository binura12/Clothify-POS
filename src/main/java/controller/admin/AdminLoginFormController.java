package controller.admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.AdminService;
import util.ServiceType;

import java.io.IOException;

public class AdminLoginFormController {

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
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/reset_password_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String enteredGmail = txtGmailAddress.getText();
        String enteredPassword = txtPassword.getText();

        AdminService adminService = ServiceFactory.getInstance().getServiceType(ServiceType.admin);
        boolean isAdminFound = adminService.loginAdmin(enteredGmail, enteredPassword);

        if (isAdminFound) {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_menu_form.fxml"))));
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

    @FXML
    void btnNewAccOnMouseClicked(MouseEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_signup.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
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
}
