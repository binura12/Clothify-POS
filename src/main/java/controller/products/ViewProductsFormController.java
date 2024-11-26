package controller.products;

import controller.DashFormController;
import dto.Product;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.ProductService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewProductsFormController implements Initializable {

    @FXML
    private TableColumn<Product, String> colomnSupplierId;

    @FXML
    private ComboBox<String> cmbCategories;

    @FXML
    private ComboBox<String> cmbSize;

    @FXML
    private ComboBox<String> cmbSupplier;

    @FXML
    private TableColumn<Product, String> colomnCategory;

    @FXML
    private TableColumn<Product, Long> colomnId;

    @FXML
    private TableColumn<Product, String> colomnProductName;

    @FXML
    private TableColumn<Product, Double> colomnProductPrice;

    @FXML
    private TableColumn<Product, String> colomnProductSize;

    @FXML
    private TableColumn<Product, Integer> colomnQty;

    @FXML
    private TableView<Product> tblProducts;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtQty;

    @FXML
    void btnAddOnAction(ActionEvent event) {
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.product);
        Product product = new Product(
                txtName.getText(),
                cmbSize.getValue(),
                Double.parseDouble(txtPrice.getText()),
                Integer.parseInt(txtQty.getText()),
                cmbCategories.getValue(),
                cmbSupplier.getValue()
        );
        if (productService.addProduct(product)) {
            txtQty.clear();
            txtName.clear();
            txtPrice.clear();
            cmbCategories.setValue(null);
            cmbSize.setValue(null);
            cmbSupplier.setValue(null);
            new Alert(Alert.AlertType.INFORMATION,"Product added successfully!").show();
            loadTableData();
        } else {
            new Alert(Alert.AlertType.ERROR,"Failed to add product!").show();
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
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.product);
        Product deletedProduct = tblProducts.getSelectionModel().getSelectedItem();
        if (deletedProduct!= null) {
            deletedProduct.setProductId(tblProducts.getSelectionModel().getSelectedItem().getProductId());

            boolean isDeleted = productService.deleteProduct(deletedProduct);;

            if (isDeleted) {
                txtQty.clear();
                txtName.clear();
                txtPrice.clear();
                cmbCategories.setValue(null);
                cmbSize.setValue(null);
                cmbSupplier.setValue(null);
                new Alert(Alert.AlertType.INFORMATION, "Product Deleted Successfully!").show();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Product Not Deleted!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "No Product Selected!").show();
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
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.product);
        List<Product> productSupplier = productService.searchProduct(txtName.getText());

        ObservableList<Product> productList = FXCollections.observableArrayList(productSupplier);
        tblProducts.setItems(productList);

        colomnId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colomnSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colomnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colomnProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colomnQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colomnProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colomnProductSize.setCellValueFactory(new PropertyValueFactory<>("size"));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.product);
        Product updatedProduct = tblProducts.getSelectionModel().getSelectedItem();
        if (updatedProduct!= null) {
            updatedProduct.setProductId(tblProducts.getSelectionModel().getSelectedItem().getProductId());
            updatedProduct.setProductName(txtName.getText());
            updatedProduct.setSize(cmbSize.getValue());
            updatedProduct.setPrice(Double.valueOf(txtPrice.getText()));
            updatedProduct.setSupplierId(cmbSupplier.getValue());
            updatedProduct.setQuantity(Integer.valueOf(txtQty.getText()));
            updatedProduct.setCategory(cmbCategories.getValue());

            boolean isUpdated = productService.updateProduct(updatedProduct);;

            if (isUpdated) {
                txtName.clear();
                txtPrice.clear();
                txtQty.clear();
                cmbCategories.setValue(null);
                cmbSize.setValue(null);
                cmbSupplier.setValue(null);
                new Alert(Alert.AlertType.INFORMATION, "Product Updated Successfully!").show();
                loadTableData();
            } else {
                new Alert(Alert.AlertType.ERROR, "Product Not Updated!").show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "No Product Selected!").show();
        }
    }

    @FXML
    void cmbCategoriesOnMouseClicked(MouseEvent event) {
        String selectedCategory = cmbCategories.getValue();
        if (selectedCategory!= null) {
            ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.product);
            List<Supplier> suppliers = productService.getSuppliersByCategory(selectedCategory);

            ObservableList<String> supplierIds = FXCollections.observableArrayList();
            for (Supplier supplier : suppliers) {
                supplierIds.add(String.valueOf(supplier.getSupplierId()));
            }
            cmbSupplier.setItems(supplierIds);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<String> categories = FXCollections.observableArrayList();
        categories.add("Ladies");
        categories.add("Gents");
        categories.add("Kids");
        cmbCategories.setItems(categories);

        ObservableList<String> sizes = FXCollections.observableArrayList();
        sizes.add("XS");
        sizes.add("S");
        sizes.add("M");
        sizes.add("L");
        cmbSize.setItems(sizes);

        tblProducts.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setTextField(newValue);
        });

        loadTableData();
    }

    private void loadTableData() {
        tblProducts.getItems().clear();

        colomnId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colomnSupplierId.setCellValueFactory(new PropertyValueFactory<>("supplierId"));
        colomnCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colomnProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colomnQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colomnProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colomnProductSize.setCellValueFactory(new PropertyValueFactory<>("size"));

        ProductService productService = ServiceFactory.getInstance().getServiceType(ServiceType.product);
        List<Product> products = productService.getAllProducts();

        ObservableList<Product> productObservableList = FXCollections.observableArrayList(products);
        tblProducts.setItems(productObservableList);
    }

    private void setTextField(Product newValue) {
        if (newValue!= null) {
            txtQty.setText(String.valueOf(newValue.getQuantity()));
            txtName.setText(newValue.getProductName());
            txtPrice.setText(String.valueOf(newValue.getPrice()));
            cmbSize.setValue(newValue.getSize());
            cmbCategories.setValue(newValue.getCategory());
            cmbSupplier.setValue(newValue.getSupplierId());
        } else {
            txtQty.clear();
            txtName.clear();
            txtPrice.clear();
            cmbCategories.setValue(null);
            cmbSize.setValue(null);
            cmbSupplier.setValue(null);
        }
    }

    public void supplierOnMouseClicked(MouseEvent mouseEvent) {
    }
}
