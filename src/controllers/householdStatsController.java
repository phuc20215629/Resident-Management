package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.authentication.AlertMessage;
import application.dao.NhanKhauDAO;
import application.dao.TamTruDAO;
import application.dao.TamVangDAO;
import application.model.NhanKhau;
import application.model.TamTru;
import application.model.TamVang;
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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class householdStatsController implements Initializable {

    @FXML
    private Label tuNgay_lbl;

    @FXML
    private Label denNgay_lbl;

    @FXML
    private Button next_btn;

    @FXML
    private Button back1_btn;

    @FXML
    private TextField biDanh_tf;

    @FXML
    private TextField cccd_tf;

    @FXML
    private TextField danToc_tf;

    @FXML
    private DatePicker denNgayTamTru_date;

    @FXML
    private TextField ghiChu_tf;

    @FXML
    private ChoiceBox<?> gioiTinh_cb1;

    @FXML
    private TextField hoTen_tf;

    @FXML
    private TextField idHoKhauThuongTru_tf;

    @FXML
    private Button luuNK_btn;

    @FXML
    private DatePicker ngayCap_date;

    @FXML
    private DatePicker ngayChuyenDi_date;

    @FXML
    private DatePicker ngayChuyenVe_date;

    @FXML
    private DatePicker ngaySinh_date;

    @FXML
    private TextField ngheNghiep_tf;

    @FXML
    private TextField nguyenQuan_tf;

    @FXML
    private Pane nhanKhauPane1;

    @FXML
    private Pane nhanKhauPane2;

    @FXML
    private TextField noiCap_tf;

    @FXML
    private TextField noiChuyenDi_tf;

    @FXML
    private TextField noiLamViec_tf;

    @FXML
    private TextField noiSinh_tf;

    @FXML
    private TextField noiThuongTru_tf;

    @FXML
    private TextField quanHe_tf;

    @FXML
    private DatePicker tuNgayTamTru_date;

    @FXML
    private DatePicker denNgay_date;

    @FXML
    private ChoiceBox<String> doTuoi_cb;

    @FXML
    private ChoiceBox<String> gioiTinh_cb;

    @FXML
    private ChoiceBox<String> gioiTinhPane_cb;

    @FXML
    private ImageView gradient;

    @FXML
    private ImageView home;

    @FXML
    private Label houseHold;

    @FXML
    private Button logout;

    @FXML
    private Label nhanKhau;

    @FXML
    private TextField soLuong_tf;

    @FXML
    private Label stat;

    @FXML
    private ChoiceBox<String> trangThai_cb;

    @FXML
    private Button thongKe_btn;

    @FXML
    private DatePicker tuNgay_date;

    @FXML
    private TableView<NhanKhau> statsTable;

    @FXML
    private TableColumn<NhanKhau, Integer> idCol;

    @FXML
    private TableColumn<NhanKhau, Integer> idHoKhauCol;

    @FXML
    private TableColumn<NhanKhau, ArrayList<String>> hoTenCol;

    ObservableList<NhanKhau> statsTableList;
    ObservableList<String> gioiTinhList = FXCollections.observableArrayList("Tất cả", "Nam", "Nữ");
    ObservableList<String> doTuoiList = FXCollections.observableArrayList("Tất cả", "Mầm non", "Cấp 1", "Cấp 2",
            "Cấp 3", "Lao động", "Nghỉ hưu");
    ObservableList<String> trangThaiList = FXCollections.observableArrayList("Tất cả", "Tạm vắng", "Tạm trú");

    @FXML
    public void hoKhauView(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/household.fxml"));
        Stage window = (Stage) houseHold.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }

    @FXML
    public void nhanKhauView(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/householdMember.fxml"));
        Stage window = (Stage) nhanKhau.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }

    @FXML
    public void statView(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/householdStats.fxml"));
        Stage window = (Stage) stat.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }

    @FXML
    public void home(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/functionOption.fxml"));
        Stage window = (Stage) home.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }

    @FXML
    public void logoutView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/login.fxml"));
        Stage window = (Stage) logout.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }

    @FXML
    void dongNhanKhauDialog(ActionEvent event) {
        hoTen_tf.clear();
        gioiTinhPane_cb.setValue(gioiTinhList.get(0));
        quanHe_tf.clear();
        biDanh_tf.clear();
        ngaySinh_date.setValue(null);
        danToc_tf.clear();
        noiSinh_tf.clear();
        nguyenQuan_tf.clear();
        ngheNghiep_tf.clear();
        noiLamViec_tf.clear();
        cccd_tf.clear();
        noiCap_tf.clear();
        ngayCap_date.setValue(null);
        tuNgayTamTru_date.setValue(null);
        denNgayTamTru_date.setValue(null);
        ngayChuyenDi_date.setValue(null);
        ngayChuyenVe_date.setValue(null);
        noiChuyenDi_tf.clear();
        ghiChu_tf.clear();
        nhanKhauPane2.setVisible(false);
        nhanKhauPane1.setVisible(false);
    }

    @FXML
    void moNhanKhauDialog(ActionEvent event) {
        if (!nhanKhauPane1.isVisible() && back1_btn.isArmed()) {
            nhanKhauPane1.setVisible(true);
            nhanKhauPane2.setVisible(false);
        }
        if (!nhanKhauPane1.isVisible()) {
            NhanKhau selectedNK = statsTable.getSelectionModel().getSelectedItem();
            if (selectedNK == null) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa chọn nhân khẩu!");
            } else {
                hoTen_tf.setText(selectedNK.getHoTen());
                if (selectedNK.getGioiTinh().equals("Nam")) {
                    gioiTinhPane_cb.setValue(gioiTinhList.get(1));
                } else {
                    gioiTinhPane_cb.setValue(gioiTinhList.get(2));
                }
                quanHe_tf.setText(selectedNK.getQhChuHo());
                biDanh_tf.setText(selectedNK.getBiDanh());

                // Date sqlDate = Date.valueOf(selectedNK.getNgaySinh().toString());
                Date sqlDate = selectedNK.getNgaySinh();
                ngaySinh_date.setValue(sqlDate.toLocalDate());
                danToc_tf.setText(selectedNK.getDanToc());
                noiSinh_tf.setText(selectedNK.getNoiSinh());
                nguyenQuan_tf.setText(selectedNK.getNguyenQuan());
                ngheNghiep_tf.setText(selectedNK.getNgheNghiep());
                noiLamViec_tf.setText(selectedNK.getNoiLamViec());

                nhanKhauPane1.setVisible(true);
            }
        }
    }

    @FXML
    public void moNhanKhauDialog2(ActionEvent event) {
        if (!nhanKhauPane2.isVisible()) {
            nhanKhauPane2.setVisible(true);
            nhanKhauPane1.setVisible(false);

            NhanKhau selectedNK = statsTable.getSelectionModel().getSelectedItem();
            cccd_tf.setText(selectedNK.getCccd());

            Date sqlDate = Date.valueOf(selectedNK.getNgayCapID().toString());
            ngayCap_date.setValue(sqlDate.toLocalDate());
            noiCap_tf.setText(selectedNK.getNoiCapID());
            ghiChu_tf.setText(selectedNK.getGhiChu());

            if (selectedNK.getGhiChu().equals("Tạm trú")) {

                tuNgayTamTru_date.setEditable(true);
                denNgayTamTru_date.setEditable(true);
                noiThuongTru_tf.setEditable(true);

                TamTru tt = TamTruDAO.getInstance().selectByNKID(selectedNK.getId());
                if (tt != null) {
                    tuNgayTamTru_date.setValue(tt.getTuNgayDangKy().toLocalDate());
                    denNgayTamTru_date.setValue(tt.getDenNgayDangKy().toLocalDate());
                    noiThuongTru_tf.setText(tt.getDiaChiTruocChuyenDen());
                    idHoKhauThuongTru_tf.setText(Integer.toString(tt.getIdHoKhau()));
                }
            }
            if (selectedNK.getGhiChu().equals("Tạm vắng")) {

                ngayChuyenDi_date.setEditable(true);
                ngayChuyenVe_date.setEditable(true);
                noiChuyenDi_tf.setEditable(true);

                TamVang tv = TamVangDAO.getInstance().selectByNKID(selectedNK.getId());
                if (tv != null) {
                    ngayChuyenDi_date.setValue(tv.getTuNgayDangKy().toLocalDate());
                    ngayChuyenVe_date.setValue(tv.getDenNgayDangKy().toLocalDate());
                    noiChuyenDi_tf.setText(tv.getDiaChiChuyenDen());
                }
            }
        }
    }

    @FXML
    void thongKe(ActionEvent event) {
        String gioiTinh = gioiTinh_cb.getValue();
        String doTuoi = doTuoi_cb.getValue();
        String trangThai = trangThai_cb.getValue();
        Date tuNgay_sqlDate = Date.valueOf(tuNgay_date.getValue());
        Date denNgay_sqlDate = Date.valueOf(denNgay_date.getValue());

        if (doTuoi.equals("Tất cả")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(gioiTinh, -1,
                    200, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        } else if (doTuoi.equals("Mầm non")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(gioiTinh, 0,
                    5, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        } else if (doTuoi.equals("Cấp 1")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(gioiTinh, 6,
                    10, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        } else if (doTuoi.equals("Cấp 2")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(gioiTinh, 11,
                    14, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        } else if (doTuoi.equals("Cấp 3")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(gioiTinh, 15,
                    17, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        } else if (doTuoi.equals("Lao động")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(gioiTinh, 18,
                    60, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        } else if (doTuoi.equals("Nghỉ hưu")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(gioiTinh, 61,
                    200, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        }

        idCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        idHoKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("hoKhauID"));
        hoTenCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, ArrayList<String>>("hoTen"));
        statsTable.setItems(statsTableList);
        int soLuong = statsTableList.size();
        soLuong_tf.setText(Integer.toString(soLuong));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RotateTransition rotate = new RotateTransition();
        rotate.setNode(gradient);
        rotate.setDuration(Duration.millis(10000));
        rotate.setCycleCount(RotateTransition.INDEFINITE);
        rotate.setInterpolator(Interpolator.LINEAR);
        rotate.setByAngle(360);
        rotate.play();

        statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().selectGroupByHKID());
        idCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        idHoKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("hoKhauID"));
        hoTenCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, ArrayList<String>>("hoTen"));
        statsTable.setItems(statsTableList);

        soLuong_tf.setText(Integer.toString(statsTableList.size()));
        trangThai_cb.setOnAction(event -> {
            if(!trangThai_cb.getValue().equals("Tất cả")) {
                tuNgay_lbl.setVisible(true);
                tuNgay_date.setVisible(true);
                denNgay_lbl.setVisible(true);
                denNgay_date.setVisible(true);
            } else {
                tuNgay_lbl.setVisible(false);
                tuNgay_date.setVisible(false);
                denNgay_lbl.setVisible(false);
                denNgay_date.setVisible(false);
            }
        });

        if (gioiTinh_cb != null) {
            gioiTinh_cb.setValue(gioiTinhList.get(0));
            gioiTinh_cb.setItems(gioiTinhList);
        }
        if (doTuoi_cb != null) {
            doTuoi_cb.setValue(doTuoiList.get(0));
            doTuoi_cb.setItems(doTuoiList);
        }
        if (trangThai_cb != null) {
            trangThai_cb.setValue(trangThaiList.get(0));
            trangThai_cb.setItems(trangThaiList);
        }
        if (tuNgay_date != null) {
            tuNgay_date.setValue(LocalDate.now().plusDays(-1));
        }
        if (denNgay_date != null) {
            denNgay_date.setValue(LocalDate.now().plusDays(1));
        }
    }

}
