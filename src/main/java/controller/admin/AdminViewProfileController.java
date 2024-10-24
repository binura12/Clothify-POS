package controller.admin;

import dto.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import repository.custom.impl.AdminDaoImpl;
import service.ServiceFactory;
import service.custom.AdminService;
import util.ServiceType;

import java.io.IOException;

public class AdminViewProfileController {

    public Label lblGmail;
    @FXML
    private Label lblAge;

    @FXML
    private Label lblCompany;

    @FXML
    private Label lblID;

    @FXML
    private Label lblName;

    @FXML
    void initialize() {
        AdminService adminService = ServiceFactory.getInstance().getServiceType(ServiceType.admin);
        String loggedInAdminEmail = AdminDaoImpl.AdminLoggedInEmail;
        Admin admin = adminService.loadAdminProfile(loggedInAdminEmail);

        lblID.setText("ID : " + admin.getId());
        lblAge.setText("Age : " + admin.getAge());
        lblName.setText("Name : " + admin.getFullname());
        lblCompany.setText("Company : " + admin.getCompany());
        lblGmail.setText("Email : " + admin.getEmail());
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_menu_form.fxml"))));
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
