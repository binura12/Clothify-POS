package controller.admin;

import com.jfoenix.controls.JFXButton;
import controller.DashFormController;
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
import service.custom.CashierService;
import util.EmailUtil;
import util.PasswordValidateUtil;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ResetPasswordFormController implements Initializable {

    public JFXButton btnReset;
    public Label lblPasswordStrong;
    @FXML
    private Label lblWrongOTP;

    @FXML
    private TextField txtGmailAddress;

    @FXML
    private TextField txtOTP;

    @FXML
    private TextField txtPassword;

    public static int otp;

    @FXML
    void btnBackToLoginOnMouseClicked(MouseEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_login_form.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnResetOnAction(ActionEvent event) {
        String password = txtPassword.getText();
        if (!PasswordValidateUtil.isValidPassword(password)){
            lblPasswordStrong.setVisible(true);
        } else {
            AdminService adminService = ServiceFactory.getInstance().getServiceType(ServiceType.admin);
            boolean isPasswordReset = adminService.resetPassword(txtGmailAddress.getText(), txtPassword.getText());
            if (isPasswordReset) {
                new Alert(Alert.AlertType.INFORMATION, "Password Reset!").show();
                Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
                try {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_login_form.fxml"))));
                    stage.show();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                new Alert(Alert.AlertType.ERROR, "Password Reset Failed!").show();
                txtGmailAddress.setText("");
                txtPassword.setText("");
                txtOTP.setText("");
            }
        }
    }

    @FXML
    void btnSendOnAction(ActionEvent event) {
        lblWrongOTP.setVisible(false);
        String enteredGmail = txtGmailAddress.getText();
        AdminService adminService = ServiceFactory.getInstance().getServiceType(ServiceType.admin);
        boolean isForgetPswEmailFound = adminService.forgotPassword(enteredGmail);
        if (isForgetPswEmailFound) {
            otp = adminService.generateOTP();
            EmailUtil.sendOTP(enteredGmail, otp);
            txtOTP.setEditable(true);
        } else {
            lblWrongOTP.setVisible(true);
            txtGmailAddress.setText("");
        }
    }

    @FXML
    void btnVerifyOnAction(ActionEvent event) {
        lblWrongOTP.setVisible(false);
        int enteredOTP = Integer.parseInt(txtOTP.getText());
        if (otp == enteredOTP) {
            txtPassword.setEditable(true);
            btnReset.setDisable(false);
        } else {
            lblWrongOTP.setVisible(true);
            txtOTP.setText("");
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
        lblWrongOTP.setVisible(false);
        btnReset.setDisable(true);
        txtOTP.setEditable(false);
        txtPassword.setEditable(false);
        lblPasswordStrong.setVisible(false);
    }
}
