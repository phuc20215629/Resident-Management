package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.authentication.AlertMessage;
import application.dao.GiaoDichDAO;
import application.dao.HoKhauDAO;
import application.dao.KhoanPhiDAO;
import application.model.GiaoDich;
import application.model.HoKhau;
import application.model.KhoanPhi;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class feeTypeController implements Initializable {

    @FXML
    private ChoiceBox<String> loaiPhi_cb;

    @FXML
    private DatePicker denNgay_date;

    @FXML
    private TextField dinhMuc_tf;

    @FXML
    private TextField dinhMuc_tf1;

    @FXML
    private Pane dongPhiDialog;

    @FXML
    private Button dongPhiVS_btn;

    @FXML
    private Button dongPhi_btn;

    @FXML
    private Button find_btn;

    @FXML
    private ImageView gradient;

    @FXML
    private ImageView home;

    @FXML
    private Pane khoanThuDialog;

    @FXML
    private Button logout;

    @FXML
    private TextField maHo_tf;

    @FXML
    private VBox menu;

    @FXML
    private TextField search_tf;

    @FXML
    private Label stat;

    @FXML
    private Label suaKhoan_lbl;

    @FXML
    private Label suaKhoan_lbl1;

    @FXML
    private Button sua_btn;

    @FXML
    private Button sua_btn1;

    @FXML
    private TextField tenKhoan_tf;

    @FXML
    private TextField tenKhoan_tf1;

    @FXML
    private Label themKhoan_lbl;

    @FXML
    private Label themKhoan_lbl1;

    @FXML
    private Button them_btn;

    @FXML
    private Button them_btn1;

    @FXML
    private DatePicker tuNgay_date;

    @FXML
    private Label type;

    @FXML
    private Button xoa_btn;

    @FXML
    private ChoiceBox<String> search_cb;

    @FXML
    private TableView<HoKhau> hoKhauTable;

    @FXML
    private TableColumn<HoKhau, Integer> idHoKhauCol;

    @FXML
    private TableColumn<HoKhau, String> tenChuHoCol;

    @FXML
    private TableView<KhoanPhi> statsTable;

    @FXML
    private TableColumn<KhoanPhi, String> tenKhoanPhi_col;

    @FXML
    private TableColumn<KhoanPhi, String> loaiPhi_col;

    @FXML
    private TableColumn<KhoanPhi, String> dinhMuc_col;

    @FXML
    private TableColumn<KhoanPhi, Integer> id_col;

    @FXML
    private TableColumn<KhoanPhi, String> trangThai_col;

    ObservableList<KhoanPhi> statsTableList;
    ObservableList<HoKhau> hoKhauTableList;
    ObservableList<String> searchOptionList = FXCollections.observableArrayList("ID", "Tên khoản");
    KhoanPhi selectedKP;
    HoKhau selectedHK;

    @FXML
    void timKhoanPhi(ActionEvent event) {
        if (!search_tf.getText().isBlank()) {
            statsTableList.clear();
            if (search_cb.getValue().equals("ID")) {
                KhoanPhi kp = KhoanPhiDAO.getInstance().selectById(Integer.parseInt(search_tf.getText()));
                if (kp == null) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Không tìm thấy khoản phí!");
                } else {
                    statsTableList.add(kp);
                    statsTable.setItems(statsTableList);
                }
            } else {
                ArrayList<KhoanPhi> kp = KhoanPhiDAO.getInstance().selectByTen(search_tf.getText());
                if (kp == null) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Không tìm thấy khoản phí!");
                } else {
                    statsTableList = FXCollections.observableArrayList(kp);
                    statsTable.setItems(statsTableList);
                }
            }
        } else {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Bạn chưa nhập thông tin tìm kiếm!");
            refreshStatsTable();
        }
    }

    @FXML
    void moDongPhiDialog(ActionEvent event) {
        menu.setVisible(false);
        dinhMuc_tf1.setEditable(false);
        refreshHoKhauTable(HoKhauDAO.getInstance().selectAll());
        hoKhauTable.setOnMouseClicked(e -> {
            selectedHK = hoKhauTable.getSelectionModel().getSelectedItem();
            maHo_tf.setText(Integer.toString(selectedHK.getIdHoKhau()));
        });

        selectedKP = statsTable.getSelectionModel().getSelectedItem();
        if (selectedKP == null) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Bạn chưa chọn khoản phí!");
        } else {
            if (selectedKP.getTrangThai().equals("Không hiệu lực")) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Khoản phí không hiệu lực!");
            } else {
                dongPhiDialog.setVisible(true);
                tenKhoan_tf1.setText(selectedKP.getTenKhoanPhi());
                if (selectedKP.getLoaiPhi().equals("Bắt buộc theo hộ")) {
                    dinhMuc_tf1.setText(Integer.toString(selectedKP.getSoTien()));
                } else if (selectedKP.getLoaiPhi().equals("Bắt buộc theo người")) {
                    maHo_tf.textProperty().addListener((observable, oldVal, newVal) -> {
                        dinhMuc_tf1.setText(Integer.toString(selectedKP.getSoTien() * selectedHK.getSoThanhVien()));
                    });
                } else {
                    dinhMuc_tf1.setEditable(true);
                }
            }
        }
    }

    @FXML
    void dongDongPhiDialog(ActionEvent event) {
        menu.setVisible(true);
        dongPhiDialog.setVisible(false);
        clearInfo();
    }

    @FXML
    void nopPhi(ActionEvent event) {
        if (maHo_tf.getText().isBlank() || dinhMuc_tf1.getText().isBlank()) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Bạn chưa nhập đủ thông tin!");
        } else {
            int maHo = Integer.parseInt(maHo_tf.getText());
            int soTien = Integer.parseInt(dinhMuc_tf1.getText());
            Date now = Date.valueOf(LocalDate.now());
            if (selectedKP.getLoaiPhi().equals("Không bắt buộc")) {
                if (GiaoDichDAO.getInstance().insert(new GiaoDich(selectedKP.getMaKhoanPhi(), soTien, maHo, now,
                        selectedKP.getTenKhoanPhi()))) {
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Nộp phí thành công!");
                    dongDongPhiDialog(event);
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Nộp phí không thành công!");
                }
            } else {    //Loại phí bắt buộc
                if (GiaoDichDAO.getInstance().selectByHK_KPID(maHo, selectedKP.getMaKhoanPhi()) == null) {
                    if (GiaoDichDAO.getInstance().insert(new GiaoDich(selectedKP.getMaKhoanPhi(), soTien, maHo, now,
                            selectedKP.getTenKhoanPhi()))) {
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Nộp phí thành công!");
                        dongDongPhiDialog(event);
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Nộp phí không thành công!");
                    }
                } else { // Khoản phí bắt buộc này đã được đóng bởi hộ từ trước -> không cho nộp nữa
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Hộ đã đóng khoản phí này rồi!");
                }
            }
        }
    }

    public void moKhoanThuDialog() {
        if (!khoanThuDialog.isVisible() && them_btn.isArmed()) { // Them khoan phi
            khoanThuDialog.setVisible(true);
            them_btn1.setVisible(true);
            sua_btn1.setVisible(false);
            themKhoan_lbl.setVisible(true);
            suaKhoan_lbl.setVisible(false);
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

                tenKhoan_tf.setText(selectedKP.getTenKhoanPhi());
                Date tuNgayDate = selectedKP.getTuNgay();
                Date denNgayDate = selectedKP.getDenNgay();
                tuNgay_date.setValue(tuNgayDate.toLocalDate());
                denNgay_date.setValue(denNgayDate.toLocalDate());
                loaiPhi_cb.setValue(selectedKP.getLoaiPhi());
                dinhMuc_tf.setText(Integer.toString(selectedKP.getSoTien()));
            }
        }
    }

    public void dongKhoanThuDialog() {
        khoanThuDialog.setVisible(false);
        clearInfo();
    }

    @FXML
    void themKhoanPhi(ActionEvent event) {
        String tenKhoan = tenKhoan_tf.getText();
        if (loaiPhi_cb.getValue().equals("Loại phí")) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Hãy chọn loại khoản phí!");
            dinhMuc_tf.clear();
        } else {
            try {
                Date tuNgay = Date.valueOf(tuNgay_date.getValue());
                Date denNgay = Date.valueOf(denNgay_date.getValue());
                int choice = loaiPhi_cb.getSelectionModel().getSelectedIndex();
                if (choice == 0 || choice == 1) { // Loại phí thu theo hộ, người
                    try {
                        int soTien = Integer.parseInt(dinhMuc_tf.getText());
                        if (soTien > 1e9) {
                            AlertMessage alert = new AlertMessage();
                            alert.errorMessage("Số tiền tối đa là 1 tỷ VND!");
                        } else {
                            if (tenKhoan.isBlank()) {
                                AlertMessage alert = new AlertMessage();
                                alert.errorMessage("Bạn chưa nhập đủ thông tin!");
                            } else {
                                String trangThai = "Đang hiệu lực";
                                if (tuNgay.toLocalDate().isAfter(LocalDate.now())
                                        || denNgay.toLocalDate().isBefore(LocalDate.now())) {
                                    trangThai = "Không hiệu lực";
                                }
                                String loaiPhi = "Bắt buộc theo hộ";
                                if (choice == 1)
                                    loaiPhi = "Bắt buộc theo người";
                                KhoanPhi newKP = new KhoanPhi(tenKhoan, loaiPhi, soTien, tuNgay, denNgay, trangThai);
                                if (KhoanPhiDAO.getInstance().insert(newKP)) {
                                    AlertMessage alert = new AlertMessage();
                                    alert.successMessage("Thêm khoản phí thành công!");
                                    dongKhoanThuDialog();
                                    refreshStatsTable();
                                } else {
                                    AlertMessage alert = new AlertMessage();
                                    alert.errorMessage("Thêm khoản phí không thành công!");
                                }
                            }
                        }
                    } catch (Exception e) {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Định mức phải là số!");
                    }
                } else { // Loại phí không bắt buộc
                    if (tenKhoan.isBlank()) {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Bạn chưa nhập đủ thông tin!");
                    } else {
                        String trangThai = "Đang hiệu lực";
                        if (tuNgay.toLocalDate().isAfter(LocalDate.now())
                                || denNgay.toLocalDate().isBefore(LocalDate.now())) {
                            trangThai = "Không hiệu lực";
                        }
                        KhoanPhi newKP = new KhoanPhi(tenKhoan, loaiPhi_cb.getSelectionModel().getSelectedItem(), 0,
                                tuNgay, denNgay, trangThai);
                        if (KhoanPhiDAO.getInstance().insert(newKP)) {
                            AlertMessage alert = new AlertMessage();
                            alert.successMessage("Thêm khoản phí thành công!");
                            dongKhoanThuDialog();
                            refreshStatsTable();
                        } else {
                            AlertMessage alert = new AlertMessage();
                            alert.errorMessage("Thêm khoản phí không thành công!");
                        }
                    }
                }
            } catch (Exception e) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa nhập thông tin thời hạn khoản phí!");
            }
        }
    }

    @FXML
    void suaKhoanPhi(ActionEvent event) {
        String tenKhoan = tenKhoan_tf.getText();
        int choice = loaiPhi_cb.getSelectionModel().getSelectedIndex();
        if (choice == 0 || choice == 1) { // loại phí thu theo hộ, người
            try {
                int soTien = Integer.parseInt(dinhMuc_tf.getText());
                if (soTien > 1e9) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Số tiền tối đa là 1 tỷ VND!");
                } else {
                    if (tenKhoan.isBlank()) {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Bạn chưa nhập đủ thông tin!");
                    } else {
                        selectedKP.setTenKhoanPhi(tenKhoan);
                        selectedKP.setLoaiPhi(loaiPhi_cb.getSelectionModel().getSelectedItem());
                        selectedKP.setSoTien(soTien);
                        selectedKP.setDenNgay(Date.valueOf(denNgay_date.getValue()));
                        selectedKP.setTuNgay(Date.valueOf(tuNgay_date.getValue()));
                        selectedKP.setTrangThai("Đang hiệu lực");
                        if (tuNgay_date.getValue().isAfter(LocalDate.now())
                                || denNgay_date.getValue().isBefore(LocalDate.now())) {
                            selectedKP.setTrangThai("Không hiệu lực");
                        }
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
            } catch (Exception e) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Định mức phải là số!");
            }
        } else { // Loại phí không bắt buộc
            if (tenKhoan.isBlank()) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa nhập đủ thông tin!");
            } else {
                selectedKP.setTenKhoanPhi(tenKhoan);
                selectedKP.setLoaiPhi(loaiPhi_cb.getSelectionModel().getSelectedItem());
                selectedKP.setDenNgay(Date.valueOf(denNgay_date.getValue()));
                selectedKP.setTuNgay(Date.valueOf(tuNgay_date.getValue()));
                selectedKP.setTrangThai("Đang hiệu lực");
                if (tuNgay_date.getValue().isAfter(LocalDate.now())
                        || denNgay_date.getValue().isBefore(LocalDate.now())) {
                    selectedKP.setTrangThai("Không hiệu lực");
                }
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

    @FXML
    void xoaKhoanPhi(ActionEvent event) {
        selectedKP = statsTable.getSelectionModel().getSelectedItem();
        if (selectedKP == null) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Bạn chưa chọn khoản phí!");
        } else {
            try {
                GiaoDichDAO.getInstance().deleteByKPID(selectedKP.getMaKhoanPhi());
                KhoanPhiDAO.getInstance().deleteByID(selectedKP.getMaKhoanPhi());
                AlertMessage alert = new AlertMessage();
                alert.successMessage("Xóa khoản phí thành công");
                refreshStatsTable();
            } catch (Exception e) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Xóa khoản phí không thành công!");
            }
        }
    }

    public void clearInfo() {
        if (!tenKhoan_tf.getText().isEmpty())
            tenKhoan_tf.clear();
        if (!dinhMuc_tf.getText().isEmpty())
            dinhMuc_tf.clear();
        if (tuNgay_date.getValue() != null)
            tuNgay_date.setValue(null);
        if (denNgay_date.getValue() != null)
            denNgay_date.setValue(null);
        if (!maHo_tf.getText().isEmpty())
            maHo_tf.clear();
        if (!tenKhoan_tf1.getText().isEmpty())
            tenKhoan_tf1.clear();
        if (!dinhMuc_tf1.getText().isEmpty())
            dinhMuc_tf1.clear();
    }

    public void refreshStatsTable() {
        statsTableList = FXCollections.observableArrayList(KhoanPhiDAO.getInstance().selectAll());
        id_col.setCellValueFactory(new PropertyValueFactory<KhoanPhi, Integer>("maKhoanPhi"));
        tenKhoanPhi_col.setCellValueFactory(new PropertyValueFactory<KhoanPhi, String>("tenKhoanPhi"));
        loaiPhi_col.setCellValueFactory(new PropertyValueFactory<KhoanPhi, String>("loaiPhi"));
        dinhMuc_col.setCellValueFactory(new PropertyValueFactory<KhoanPhi, String>("dinhMucStr"));
        trangThai_col.setCellValueFactory(new PropertyValueFactory<KhoanPhi, String>("trangThai"));
        statsTable.setItems(statsTableList);
    }

    public void refreshHoKhauTable(ArrayList<HoKhau> list) {
        hoKhauTableList = FXCollections.observableArrayList(list);
        idHoKhauCol.setCellValueFactory(new PropertyValueFactory<HoKhau, Integer>("idHoKhau"));
        tenChuHoCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("tenChuHo"));
        hoKhauTable.setItems(hoKhauTableList);
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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(gradient);
        rotate.setDuration(Duration.millis(10000));
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.play();

        loaiPhi_cb.getItems().addAll("Bắt buộc theo hộ", "Bắt buộc theo người", "Không bắt buộc");
        loaiPhi_cb.setValue("Loại phí");
        ChangeListener<String> choiceBoxListener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue.equals("Không bắt buộc")) {
                    dinhMuc_tf.setEditable(false);
                } else {
                    dinhMuc_tf.setEditable(true);
                }
            }
        };
        loaiPhi_cb.getSelectionModel().selectedItemProperty().addListener(choiceBoxListener);

        if (khoanThuDialog != null)
            khoanThuDialog.setVisible(false);
        if (search_cb != null) {
            search_cb.setValue(searchOptionList.get(0));
            search_cb.setItems(searchOptionList);
        }
        refreshStatsTable();

    }
}
