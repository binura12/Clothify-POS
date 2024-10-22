package controller.admin;

import controller.DashFormController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMenuFormController {

    @FXML
    void btnCashRegOnAction(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/cashier_register.fxml"))));
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnViewProductsOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewProfileOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewReportsOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewSuppliersOnAction(ActionEvent event) {

    }
}
