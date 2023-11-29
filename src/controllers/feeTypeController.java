package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import application.authentication.AlertMessage;
import application.dao.KhoanPhiDAO;
import application.model.KhoanPhi;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class feeTypeController implements Initializable {

    @FXML
    private Button sua_btn1;

    @FXML
    private Button them_btn1;

    @FXML
    private Label suaKhoan_lbl;

    @FXML
    private Label themKhoan_lbl;

    @FXML
    private Label collection;

    @FXML
    private TextField dinhMuc_tf;

    @FXML
    private Button find_btn;

    @FXML
    private TextField ghiChu_tf;

    @FXML
    private ImageView gradient;

    @FXML
    private ImageView home;

    @FXML
    private Pane khoanThuDialog;

    @FXML
    private CheckBox batBuoc_cb;

    @FXML
    private Button logout;

    @FXML
    private Button luu_btn;

    @FXML
    private Label stat;

    @FXML
    private Button sua_btn;

    @FXML
    private TextField tenKhoan_tf;

    @FXML
    private Button them_btn;

    @FXML
    private Label type;

    @FXML
    private TableView<KhoanPhi> statsTable;

    @FXML
    private TableColumn<KhoanPhi, String> tenKhoanPhi_col;

    @FXML
    private TableColumn<KhoanPhi, String> loaiPhi_col;

    @FXML
    private TableColumn<KhoanPhi, Integer> dinhMuc_col;

    @FXML
    private TableColumn<KhoanPhi, Integer> id_col;

    ObservableList<KhoanPhi> statsTableList;

    @FXML
    void timKhoan(ActionEvent event) {

    }

    KhoanPhi selectedKP;

    public void moKhoanThuDialog() {
        if (!khoanThuDialog.isVisible() && them_btn.isArmed()) { // Them khoan phi
            khoanThuDialog.setVisible(true);
            them_btn1.setVisible(true);
            sua_btn1.setVisible(false);
            themKhoan_lbl.setVisible(true);
            suaKhoan_lbl.setVisible(false);
            batBuoc_cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                if (newValue)
                    dinhMuc_tf.setEditable(true);
                else
                    dinhMuc_tf.setEditable(false);
            });
        }
        if (!khoanThuDialog.isVisible() && sua_btn.isArmed()) { // Sua khoan phi
            selectedKP = statsTable.getSelectionModel().getSelectedItem();
            if (selectedKP == null) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa chọn khoản phí!");
            } else {
                khoanThuDialog.setVisible(true);
                them_btn1.setVisible(false);
                sua_btn1.setVisible(true);
                themKhoan_lbl.setVisible(false);
                suaKhoan_lbl.setVisible(true);
                batBuoc_cb.selectedProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue)
                        dinhMuc_tf.setEditable(true);
                    else
                        dinhMuc_tf.setEditable(false);
                });

                tenKhoan_tf.setText(selectedKP.getTenKhoanPhi());
                if (selectedKP.getLoaiPhi().equals("Bat buoc")) {
                    batBuoc_cb.setSelected(true);
                    dinhMuc_tf.setText(Integer.toString(selectedKP.getSoTien()));
                } else {
                    batBuoc_cb.setSelected(false);
                    dinhMuc_tf.setEditable(false);
                }
            }
        }
    }

    public void dongKhoanThuDialog() {
        khoanThuDialog.setVisible(false);
    }

    @FXML
    void themKhoanPhi(ActionEvent event) {
        String tenKhoan = tenKhoan_tf.getText();
        if (batBuoc_cb.isSelected()) { // Loai phi bat buoc
            dinhMuc_tf.setEditable(true);
            try {
                int soTien = Integer.parseInt(dinhMuc_tf.getText());
                if (tenKhoan.isBlank()) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Bạn chưa nhập đủ thông tin!");
                } else {
                    KhoanPhi newKP = new KhoanPhi(tenKhoan, "Bat buoc", soTien);
                    if (KhoanPhiDAO.getInstance().insert(newKP)) {
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Sửa khoản phí thành công!");
                        dongKhoanThuDialog();
                        refreshStatsTable();
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Sửa khoản phí không thành công!");
                    }
                }
            } catch (Exception e) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Định mức phải là số!");
            }
        } else { // Loai phi khong bat buoc
            dinhMuc_tf.setEditable(false);
            if (tenKhoan.isBlank()) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa nhập đủ thông tin!");
            } else {
                KhoanPhi newKP = new KhoanPhi(tenKhoan, "Khong bat buoc", 0);
                if (KhoanPhiDAO.getInstance().insert(newKP)) {
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Sửa khoản phí thành công!");
                    dongKhoanThuDialog();
                    refreshStatsTable();
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Sửa khoản phí không thành công!");
                }
            }
        }

    }

    @FXML
    void suaKhoanPhi(ActionEvent event) {
        String tenKhoan = tenKhoan_tf.getText();
        if (batBuoc_cb.isSelected()) { // Loai phi bat buoc
            dinhMuc_tf.setEditable(true);
            try {
                int soTien = Integer.parseInt(dinhMuc_tf.getText());
                if (tenKhoan.isBlank()) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Bạn chưa nhập đủ thông tin!");
                } else {
                    selectedKP.setTenKhoanPhi(tenKhoan);
                    selectedKP.setLoaiPhi("Bat buoc");
                    selectedKP.setSoTien(soTien);
                    if (KhoanPhiDAO.getInstance().update(selectedKP)) {
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Sửa khoản phí thành công!");
                        dongKhoanThuDialog();
                        refreshStatsTable();
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Sửa khoản phí không thành công!");
                    }
                }
            } catch (Exception e) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Định mức phải là số!");
            }
        } else { // Loai phi khong bat buoc
            dinhMuc_tf.setEditable(false);
            if (tenKhoan.isBlank()) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa nhập đủ thông tin!");
            } else {
                selectedKP.setTenKhoanPhi(tenKhoan);
                selectedKP.setLoaiPhi("Khong bat buoc");
                if (KhoanPhiDAO.getInstance().update(selectedKP)) {
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Sửa khoản phí thành công!");
                    dongKhoanThuDialog();
                    refreshStatsTable();
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Sửa khoản phí không thành công!");
                }
            }
        }
    }

    public void refreshStatsTable() {
        statsTableList = FXCollections.observableArrayList(KhoanPhiDAO.getInstance().selectAll());
        id_col.setCellValueFactory(new PropertyValueFactory<KhoanPhi, Integer>("maKhoanPhi"));
        tenKhoanPhi_col.setCellValueFactory(new PropertyValueFactory<KhoanPhi, String>("tenKhoanPhi"));
        loaiPhi_col.setCellValueFactory(new PropertyValueFactory<KhoanPhi, String>("loaiPhi"));
        dinhMuc_col.setCellValueFactory(new PropertyValueFactory<KhoanPhi, Integer>("soTien"));
        statsTable.setItems(statsTableList);
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(gradient);
        rotate.setDuration(Duration.millis(10000));
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.play();

        if (khoanThuDialog != null)
            khoanThuDialog.setVisible(false);
        refreshStatsTable();

    }

    public void home() throws Exception {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/functionOption.fxml"));
        Stage window = (Stage) home.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }

    public void logoutView() throws Exception {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/login.fxml"));
        Stage window = (Stage) logout.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }

    public void collectionView() throws Exception {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/feeCollection.fxml"));
        Stage window = (Stage) collection.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }

    public void typeView() throws Exception {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/feeType.fxml"));
        Stage window = (Stage) type.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }

    public void statView() throws Exception {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/feeStats.fxml"));
        Stage window = (Stage) stat.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }
}
