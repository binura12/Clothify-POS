package controller.admin;

import dto.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.AdminService;
import util.PasswordValidateUtil;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminSignupFormController implements Initializable {

    public Label lblPasswordStrong;
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
    void btnBackToHome(MouseEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_login_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnSignupOnAction(ActionEvent event) {
        String password = txtPassword.getText();
        if (!PasswordValidateUtil.isValidPassword(password)) {
            lblPasswordStrong.setVisible(true);
        } else {
            AdminService adminService = ServiceFactory.getInstance().getServiceType(ServiceType.admin);
            Admin admin = new Admin(
                    txtName.getText(),
                    txtGmailAddress.getText(),
                    txtPassword.getText(),
                    Integer.parseInt(txtAge.getText()),
                    txtCompany.getText()
            );
            if (adminService.addAdmin(admin)) {
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_login_form.fxml"))));
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Admin Not Added!").show();
            }
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
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_login_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lblPasswordStrong.setVisible(false);
    }
}
