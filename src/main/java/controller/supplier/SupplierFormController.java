package controller.supplier;

import controller.DashFormController;
import dto.Supplier;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.SupplierService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SupplierFormController implements Initializable {

    @FXML
    private ComboBox<String> cmbCategory;

    @FXML
    private TableColumn<Supplier, String> colmnCategory;

    @FXML
    private TableColumn<Supplier, String> colmnCompany;

    @FXML
    private TableColumn<Supplier, String> colmnEmail;

    @FXML
    private TableColumn<Supplier, Long> colmnId;

    @FXML
    private TableColumn<Supplier, String> colmnItem;

    @FXML
    private TableColumn<Supplier, String> colmnName;

    @FXML
    private TableView<Supplier> tblSupplier;

    @FXML
    private TextField txtCompany;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtItem;

    @FXML
    private TextField txtName;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        SupplierService supplierService= ServiceFactory.getInstance().getServiceType(ServiceType.supplier);
        Supplier supplier = new Supplier(
                txtName.getText(),
                txtEmail.getText(),
                txtCompany.getText(),
                txtItem.getText(),
                cmbCategory.getValue()
        );
        if (supplierService.addSupplier(supplier)) {
            txtEmail.clear();
            txtName.clear();
            txtCompany.clear();
            txtItem.clear();
            cmbCategory.setValue(null);
            new Alert(Alert.AlertType.INFORMATION, "Supplier Added Successfully!").show();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not Added!").show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) {
        if (DashFormController.active.equals("admin")) {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/admin/admin_menu_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (DashFormController.active.equals("cashier")) {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/cashier/cashier_menu_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.supplier);
        Supplier deletedSupplier = tblSupplier.getSelectionModel().getSelectedItem();
        if (deletedSupplier!= null) {
            deletedSupplier.setSupplierId(tblSupplier.getSelectionModel().getSelectedItem().getSupplierId());

            boolean isDeleted = supplierService.deleteSupplier(deletedSupplier);;

            if (isDeleted) {
                txtEmail.clear();
                txtName.clear();
                txtCompany.clear();
                txtItem.clear();
                cmbCategory.setValue(null);
                new Alert(Alert.AlertType.INFORMATION, "Supplier Deleted Successfully!").show();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier Not Deleted!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "No Supplier Selected!").show();
        }
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) {
        if (DashFormController.active.equals("admin")) {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dash_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (DashFormController.active.equals("cashier")) {
            Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
            try {
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dash_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @FXML
    void btnReloadTblOnAction(ActionEvent event) {
        loadTableData();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.supplier);
        List<Supplier> searchSupplier = supplierService.searchSupplier(txtName.getText());

        ObservableList<Supplier> supplierList = FXCollections.observableArrayList(searchSupplier);
        tblSupplier.setItems(supplierList);

        colmnId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colmnName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colmnEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmail"));
        colmnCompany.setCellValueFactory(new PropertyValueFactory<>("supplierCompany"));
        colmnItem.setCellValueFactory(new PropertyValueFactory<>("supplierItem"));
        colmnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.supplier);
        Supplier updatedSupplier = tblSupplier.getSelectionModel().getSelectedItem();
        if (updatedSupplier!= null) {
            updatedSupplier.setSupplierId(tblSupplier.getSelectionModel().getSelectedItem().getSupplierId());
            updatedSupplier.setSupplierName(txtName.getText());
            updatedSupplier.setSupplierEmail(txtEmail.getText());
            updatedSupplier.setSupplierCompany(txtCompany.getText());
            updatedSupplier.setSupplierItem(txtItem.getText());
            updatedSupplier.setCategory(cmbCategory.getValue());

            boolean isUpdated = supplierService.updateSupplier(updatedSupplier);;

            if (isUpdated) {
                txtEmail.clear();
                txtName.clear();
                txtCompany.clear();
                txtItem.clear();
                cmbCategory.setValue(null);
                new Alert(Alert.AlertType.INFORMATION, "Supplier Updated Successfully!").show();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Supplier Not Updated!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "No Supplier Selected!").show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> titles = FXCollections.observableArrayList();
        titles.add("Ladies");
        titles.add("Gents");
        titles.add("Kids");
        cmbCategory.setItems(titles);

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setTextFields(newValue);
        });

        loadTableData();
    }

    private void loadTableData() {
        tblSupplier.getItems().clear();

        colmnId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colmnName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colmnEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmail"));
        colmnCompany.setCellValueFactory(new PropertyValueFactory<>("supplierCompany"));
        colmnItem.setCellValueFactory(new PropertyValueFactory<>("supplierItem"));
        colmnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));

        SupplierService supplierService = ServiceFactory.getInstance().getServiceType(ServiceType.supplier);
        List<Supplier> suppliers = supplierService.getAllSuppliers();

        ObservableList<Supplier> supplierList = FXCollections.observableArrayList(suppliers);
        tblSupplier.setItems(supplierList);
    }

    private void setTextFields(Supplier newValue) {
        if (newValue!= null) {
            txtEmail.setText(newValue.getSupplierEmail());
            txtName.setText(newValue.getSupplierName());
            txtCompany.setText(newValue.getSupplierCompany());
            txtItem.setText(newValue.getSupplierItem());
            cmbCategory.setValue(newValue.getCategory());
        }else {
            txtEmail.clear();
            txtName.clear();
            txtCompany.clear();
            txtItem.clear();
            cmbCategory.setValue(null);
        }
    }
}
