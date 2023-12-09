package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.authentication.AlertMessage;
import application.dao.HoKhauDAO;
import application.dao.NhanKhauDAO;
import application.dao.TamTruDAO;
import application.dao.TamVangDAO;
import application.model.HoKhau;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class householdMemberController implements Initializable {

    @FXML
    private Button back1_btn;

    @FXML
    private TextField biDanh_tf;

    @FXML
    private TextField ghiChu_tf;

    @FXML
    private TextField cccd_tf;

    @FXML
    private TextField danToc_tf;

    @FXML
    private DatePicker denNgayTamTru_date;

    @FXML
    private Button find_btn;

    @FXML
    private ChoiceBox<String> gioiTinh_cb;

    @FXML
    private ImageView gradient;

    @FXML
    private TextField hoTen_tf;

    @FXML
    private ImageView home;

    @FXML
    private Label houseHold;

    @FXML
    private TextField idHoKhauThuongTru_tf;

    @FXML
    private Button logout;

    @FXML
    private Button luuNK_btn;

    @FXML
    private VBox menu;

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
    private Label nhanKhau;

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
    private ChoiceBox<String> searchNK_cb;

    @FXML
    private TextField search_tf;

    @FXML
    private Label stat;

    @FXML
    private Button sua_btn;

    @FXML
    private Button themNk_btn;

    @FXML
    private DatePicker tuNgayTamTru_date;

    @FXML
    private Button xoa_btn;

    @FXML
    private TableView<NhanKhau> nhanKhauTable;
    @FXML
    private TableColumn<NhanKhau, String> tenNhanKhauCol;
    @FXML
    private TableColumn<NhanKhau, Integer> idHoKhauCol;
    @FXML
    private TableColumn<NhanKhau, Integer> idNhanKhauCol;

    ObservableList<NhanKhau> nhanKhauTableList;
    ObservableList<String> nhanKhauList = FXCollections.observableArrayList("Họ tên", "Mã hộ");
    ObservableList<String> gioiTinhList = FXCollections.observableArrayList("Giới tính", "Nam", "Nữ");

    // External section
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

    // Internal section
    @FXML
    void timNhanKhau(ActionEvent event) {
        if (search_tf.getText() != "") {
            if (searchNK_cb.getValue() == "Mã hộ") {
                int maHo = 0;
                try {
                    maHo = Integer.parseInt(search_tf.getText());
                    if (HoKhauDAO.getInstance().selectById(maHo) != null) {
                        refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(maHo));
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Không tìm thấy hộ khẩu!");
                    }
                } catch (Exception e) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Mã hộ phải là số!");
                }
            }
            // searchNK_cb.getValue() = "Chủ hộ"
            else {
                String ten = search_tf.getText();
                if (!NhanKhauDAO.getInstance().selectByTen(ten).isEmpty()) {
                    refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByTen(ten));
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Không tìm thấy tên chủ hộ!");
                    search_tf.clear();
                }
            }
        } else {
            refreshNhanKhauTable(NhanKhauDAO.getInstance().selectAll());
        }
    }

    @FXML
    void xoaNhanKhau(ActionEvent event) {
        NhanKhau selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
        if (selectedNK == null) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Bạn chưa chọn nhân khẩu!");
        } else {
            int idNhanKhau = selectedNK.getId();
            if (selectedNK.getLaChuHo() == 1) {
                HoKhau hk = new HoKhau();
                if ((hk = HoKhauDAO.getInstance().selectById(selectedNK.getHoKhauID())) != null) { // TH hộ khẩu chưa bị xóa
                    hk.setIdChuHo(0);
                    hk.setTenChuHo("");
                    hk.setSoThanhVien(HoKhauDAO.getInstance().getSoThanhVien(hk.getIdHoKhau()));
                    HoKhauDAO.getInstance().update(hk);
                    TamTruDAO.getInstance().deleteByNKID(idNhanKhau);
                    TamVangDAO.getInstance().deleteByNKID(idNhanKhau);
                    if (NhanKhauDAO.getInstance().deleteByID(idNhanKhau)) {
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Bạn đã xóa chủ hộ thành công, hãy thêm chủ hộ mới vào hộ khẩu có ID = "
                                + hk.getIdHoKhau() + "!");
                        try {
                            hoKhauView(null); // Chuyen toi ho khau de them chu ho moi
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Xóa chủ hộ không thành công!");
                    }
                } else { // TH hộ khẩu đã bị xóa từ trước
                    TamTruDAO.getInstance().deleteByNKID(idNhanKhau);
                    TamVangDAO.getInstance().deleteByNKID(idNhanKhau);
                    if (NhanKhauDAO.getInstance().deleteByID(idNhanKhau)) {
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Bạn đã xóa chủ hộ thành công!");
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Xóa chủ hộ không thành công!");
                    }
                }
            } else { // Khong la chu ho
                TamTruDAO.getInstance().deleteByNKID(idNhanKhau);
                TamVangDAO.getInstance().deleteByNKID(idNhanKhau);
                if (NhanKhauDAO.getInstance().deleteByID(idNhanKhau)) {
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Bạn đã xóa nhân khẩu thành công!");
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Xóa nhân khẩu không thành công!");
                }
            }
        }
        refreshNhanKhauTable(NhanKhauDAO.getInstance().selectAll());
    }

    public boolean dangSuaNK = false;

    @FXML
    public void moNhanKhauDialog(ActionEvent event) {
        if (!nhanKhauPane1.isVisible() && back1_btn.isArmed()) {
            nhanKhauPane1.setVisible(true);
            nhanKhauPane2.setVisible(false);
            menu.setVisible(false);
        }
        // Sua nhan khau
        if (!nhanKhauPane1.isVisible() && sua_btn.isArmed()) {
            NhanKhau selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
            if (selectedNK == null) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa chọn nhân khẩu!");
            } else {
                dangSuaNK = true;
                hoTen_tf.setText(selectedNK.getHoTen());
                if (selectedNK.getGioiTinh().equals("Nam")) {
                    gioiTinh_cb.setValue(gioiTinhList.get(1));
                } else {
                    gioiTinh_cb.setValue(gioiTinhList.get(2));
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
                menu.setVisible(false);
            }
        } else if (themNk_btn.isArmed()) { // Them nhan khau
            dangSuaNK = false;
            nhanKhauPane1.setVisible(true);
            menu.setVisible(false);
        }
    }

    @FXML
    public void moNhanKhauDialog2(ActionEvent event) {
        if (!nhanKhauPane2.isVisible()) {
            if (dangSuaNK) { // Sua nhan khau
                nhanKhauPane2.setVisible(true);
                nhanKhauPane1.setVisible(false);
                menu.setVisible(false);

                NhanKhau selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
                cccd_tf.setText(selectedNK.getCccd());

                Date sqlDate = Date.valueOf(selectedNK.getNgayCapID().toString());
                ngayCap_date.setValue(sqlDate.toLocalDate());
                noiCap_tf.setText(selectedNK.getNoiCapID());
                ghiChu_tf.setText(selectedNK.getGhiChu());

                if (selectedNK.getGhiChu().equals("Tam tru")) {

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
                if (selectedNK.getGhiChu().equals("Tam vang")) {

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
            } else { // Them nhan khau
                tuNgayTamTru_date.setEditable(false);
                denNgayTamTru_date.setEditable(false);
                noiThuongTru_tf.setEditable(false);
                ngayChuyenDi_date.setEditable(false);
                noiChuyenDi_tf.setEditable(false);
                ngayChuyenDi_date.setEditable(false);
                ngayChuyenVe_date.setEditable(false);
                noiChuyenDi_tf.setEditable(false);
                ghiChu_tf.setEditable(false);

                nhanKhauPane2.setVisible(true);
                nhanKhauPane1.setVisible(false);
                menu.setVisible(false);
            }
        }
    }

    @FXML
    public void luuNhanKhau(ActionEvent event) {
        // Sửa nhân khẩu
        if (dangSuaNK) {
            NhanKhau selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
            selectedNK.setHoTen(hoTen_tf.getText());
            selectedNK.setBiDanh(biDanh_tf.getText());
            selectedNK.setQhChuHo(quanHe_tf.getText());
            if (gioiTinh_cb.getValue().equals("Giới tính")) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa nhập giới tính!");
            } else {
                selectedNK.setGioiTinh(gioiTinh_cb.getValue());
                selectedNK.setDanToc(danToc_tf.getText());
                selectedNK.setNgaySinh(Date.valueOf(ngaySinh_date.getValue()));
                selectedNK.setNoiSinh(noiSinh_tf.getText());
                selectedNK.setNguyenQuan(nguyenQuan_tf.getText());
                selectedNK.setNgheNghiep(ngheNghiep_tf.getText());
                selectedNK.setNoiLamViec(noiLamViec_tf.getText());
                selectedNK.setCccd(cccd_tf.getText());
                selectedNK.setNgayCapID(Date.valueOf(ngayCap_date.getValue()));
                selectedNK.setNoiCapID(noiCap_tf.getText());
                selectedNK.setGhiChu(ghiChu_tf.getText());
                if (ghiChu_tf.getText().equals("Tam tru")) {
                    if (tuNgayTamTru_date.getValue().isAfter(denNgayTamTru_date.getValue())) {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Thời gian tạm trú không hợp lệ!");
                    } else {
                        TamTru tt = new TamTru();
                        tt.setIdNhanKhau(selectedNK.getId());
                        tt.setDenNgayDangKy(Date.valueOf(denNgayTamTru_date.getValue()));
                        tt.setTuNgayDangKy(Date.valueOf(tuNgayTamTru_date.getValue()));
                        tt.setDiaChiTruocChuyenDen(noiThuongTru_tf.getText());
                        TamTruDAO.getInstance().update(tt);
                        if (tuNgayTamTru_date.getValue().isAfter(LocalDate.now())
                                || denNgayTamTru_date.getValue().isBefore(LocalDate.now())) { // Neu sau khi sua, ngay
                                                                                              // tam tru khong hieu luc
                                                                                              // thi chua them vao ho
                                                                                              // khau
                            selectedNK.setHoKhauID(0);
                            int hkID = Integer.parseInt(idHoKhauThuongTru_tf.getText());
                            HoKhau hk = HoKhauDAO.getInstance().selectById(hkID);
                            hk.setSoThanhVien(HoKhauDAO.getInstance().getSoThanhVien(hkID));
                            HoKhauDAO.getInstance().update(hk);
                        }
                        if (NhanKhauDAO.getInstance().update(selectedNK)) {
                            AlertMessage alert = new AlertMessage();
                            alert.successMessage("Sửa nhân khẩu thành công!");
                            dongNhanKhauDialog(event);
                        } else {
                            AlertMessage alert = new AlertMessage();
                            alert.errorMessage("Sửa nhân khẩu không thành công!");
                        }
                    }
                } else if (ghiChu_tf.getText().equals("Tam vang")) {
                    if (ngayChuyenDi_date.getValue().isAfter(ngayChuyenVe_date.getValue())) {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Thời gian tạm vắng không hợp lệ!");
                    } else {
                        TamVang tv = new TamVang();
                        tv.setIdNhanKhau(selectedNK.getId());
                        tv.setDenNgayDangKy(Date.valueOf(ngayChuyenVe_date.getValue()));
                        tv.setTuNgayDangKy(Date.valueOf(ngayChuyenDi_date.getValue()));
                        tv.setDiaChiChuyenDen(noiChuyenDi_tf.getText());
                        TamVangDAO.getInstance().update(tv);
                        if (ngayChuyenDi_date.getValue().isAfter(LocalDate.now())
                                || ngayChuyenVe_date.getValue().isBefore(LocalDate.now())) { // Neu sau khi sua, ngay
                                                                                             // tam vang chua hieu luc
                                                                                             // thi chua cap nhat tam
                                                                                             // vang
                            selectedNK.setHoKhauID(tv.getIdHoKhau());
                            selectedNK.setGhiChu("");
                            int hkID = TamVangDAO.getInstance().selectByNKID(selectedNK.getId()).getIdHoKhau();
                            HoKhau hk = HoKhauDAO.getInstance().selectById(hkID);
                            hk.setSoThanhVien(HoKhauDAO.getInstance().getSoThanhVien(hkID));
                            HoKhauDAO.getInstance().update(hk);
                        }
                        if (NhanKhauDAO.getInstance().update(selectedNK)) {
                            AlertMessage alert = new AlertMessage();
                            alert.successMessage("Sửa nhân khẩu thành công!");
                            dongNhanKhauDialog(event);
                        } else {
                            AlertMessage alert = new AlertMessage();
                            alert.errorMessage("Sửa nhân khẩu không thành công!");
                        }
                    }
                } else { // Nhan khau khong tam tru hay tam vang
                    if (NhanKhauDAO.getInstance().update(selectedNK)) {
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Sửa nhân khẩu thành công!");
                        dongNhanKhauDialog(event);
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Sửa nhân khẩu không thành công!");
                    }
                }
            }
        } // Thêm nhân khẩu
        else {
            NhanKhau newNK = new NhanKhau();
            if (luuNK_btn.isArmed()) {
                newNK.setHoTen(hoTen_tf.getText());
                newNK.setBiDanh(biDanh_tf.getText());
                newNK.setQhChuHo(quanHe_tf.getText());
                if (gioiTinh_cb.getValue().equals("Giới tính")) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Bạn chưa nhập giới tính!");
                } else {
                    try {
                        newNK.setGioiTinh(gioiTinh_cb.getValue());
                        newNK.setDanToc(danToc_tf.getText());
                        newNK.setNgaySinh(Date.valueOf(ngaySinh_date.getValue()));
                        newNK.setNoiSinh(noiSinh_tf.getText());
                        newNK.setNguyenQuan(nguyenQuan_tf.getText());
                        newNK.setNgheNghiep(ngheNghiep_tf.getText());
                        newNK.setNoiLamViec(noiLamViec_tf.getText());
                        newNK.setCccd(cccd_tf.getText());
                        newNK.setNgayCapID(Date.valueOf(ngayCap_date.getValue()));
                        newNK.setNoiCapID(noiCap_tf.getText());

                        if (NhanKhauDAO.getInstance().insert(newNK)) {
                            AlertMessage alert = new AlertMessage();
                            alert.successMessage("Thêm nhân khẩu thành công!");
                            dongNhanKhauDialog(event);
                        } else {
                            AlertMessage alert = new AlertMessage();
                            alert.errorMessage("Thêm nhân khẩu không thành công!");
                        }
                    } catch (Exception e) {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Bạn chưa nhập đủ thông tin nhân khẩu!");
                    }
                }
            }
        }
    }

    @FXML
    public void dongNhanKhauDialog(ActionEvent event) {
        hoTen_tf.clear();
        gioiTinh_cb.setValue(gioiTinhList.get(0));
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
        dangSuaNK = false;
        search_tf.clear();
        ghiChu_tf.clear();
        refreshNhanKhauTable(NhanKhauDAO.getInstance().selectAll());
        menu.setVisible(true);
        nhanKhauPane2.setVisible(false);
        nhanKhauPane1.setVisible(false);
    }

    public void refreshNhanKhauTable(ArrayList<NhanKhau> list) {
        nhanKhauTableList = FXCollections.observableArrayList(list);
        idHoKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("hoKhauID"));
        idNhanKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        tenNhanKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        nhanKhauTable.setItems(nhanKhauTableList);
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

        refreshNhanKhauTable(NhanKhauDAO.getInstance().selectAll());

        gioiTinh_cb.setValue(gioiTinhList.get(0));
        gioiTinh_cb.setItems(gioiTinhList);

        if (searchNK_cb != null) {
            searchNK_cb.setValue(nhanKhauList.get(0));
            searchNK_cb.setItems(nhanKhauList);
        }
        if (nhanKhauPane1 != null)
            nhanKhauPane1.setVisible(false);
        if (nhanKhauPane2 != null)
            nhanKhauPane2.setVisible(false);
    }

}
