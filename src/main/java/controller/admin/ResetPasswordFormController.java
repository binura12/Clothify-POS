package controller.admin;

import com.jfoenix.controls.JFXButton;
import controller.DashFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import util.ServiceType;

import java.io.IOException;

public class ResetPasswordFormController {

    public JFXButton btnReset;
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
    void initialize() {
        lblWrongOTP.setVisible(false);
        btnReset.setDisable(true);
        txtOTP.setEditable(false);
        txtPassword.setEditable(false);
    }

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
        AdminService adminService = ServiceFactory.getInstance().getServiceType(ServiceType.admin);
        boolean isPasswordReset = adminService.resetPassword(txtGmailAddress.getText(), txtPassword.getText());
        if (isPasswordReset) {
            new Alert(Alert.AlertType.INFORMATION, "Password Reset!").show();
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/admin/admin_login_form.fxml"))));
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

    @FXML
    void btnSendOnAction(ActionEvent event) {
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
        int enteredOTP = Integer.parseInt(txtOTP.getText());
        if (otp == enteredOTP) {
            txtPassword.setEditable(true);
            btnReset.setDisable(false);
        } else {
            lblWrongOTP.setVisible(true);
            txtOTP.setText("");
        }
    }
}
