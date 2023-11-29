package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import application.authentication.AlertMessage;
import application.dao.HoKhauDAO;
import application.dao.NhanKhauDAO;
import application.model.HoKhau;
import application.model.NhanKhau;
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

public class householdMemberController implements Initializable {

    @FXML
    private Button back1_btn;

    @FXML
    private Button back2_btn;

    @FXML
    private Button xoa_btn;

    @FXML
    private ChoiceBox<String> gioiTinh_cb;

    @FXML
    private TextField biDanh_tf;

    @FXML
    private TextField cccd_tf;

    @FXML
    private TextField danToc_tf;

    @FXML
    private Button find_btn;

    @FXML
    private Button sua_btn;

    @FXML
    private Button themNk_btn;

    @FXML
    private TextField ghiChu_tf;

    @FXML
    private ImageView gradient;

    @FXML
    private TextField hoTen_tf;

    @FXML
    private ImageView home;

    @FXML
    private Label household;

    @FXML
    private Button logout;

    @FXML
    private Button luuNK_btn;

    @FXML
    private Label member;

    @FXML
    private DatePicker ngayCap_date;

    @FXML
    private DatePicker ngayChuyenDi_date;

    @FXML
    private DatePicker ngayDangKi_date;

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
    private Pane nhanKhauPane3;

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

    @FXML
    public void hoKhauView(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/household.fxml"));
        Stage window = (Stage) household.getScene().getWindow();
        Scene s = new Scene(root, 1400, 800);
        s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
        window.setScene(s);
    }

    @FXML
    public void nhanKhauView(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/householdMember.fxml"));
        Stage window = (Stage) member.getScene().getWindow();
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
    void timNhanKhau(ActionEvent event) {
        if (search_tf.getText() != "") {
            if (searchNK_cb.getValue() == "Mã hộ") {
                int maHo = 0;
                try {
                    maHo = Integer.parseInt(search_tf.getText());
                    if (HoKhauDAO.getInstance().selectById(maHo) != null) {
                        nhanKhauTableList = FXCollections
                                .observableArrayList(NhanKhauDAO.getInstance().selectByHKId(maHo));
                        idHoKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("hoKhauID"));
                        idNhanKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
                        tenNhanKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
                        nhanKhauTable.setItems(nhanKhauTableList);
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
                    nhanKhauTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().selectByTen(ten));
                    idHoKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("hoKhauID"));
                    idNhanKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
                    tenNhanKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
                    nhanKhauTable.setItems(nhanKhauTableList);
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Không tìm thấy tên chủ hộ!");
                    search_tf.clear();
                }
            }
        } else {
            refreshNhanKhauTable();
        }
    }

    @FXML
    void xoaNhanKhau(ActionEvent event) {
        NhanKhau selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
        if (selectedNK == null) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Bạn chưa chọn nhân khẩu!");
        } else {
            if(selectedNK.getLaChuHo() == 1) {
                HoKhau hk = new HoKhau();
                if((hk = HoKhauDAO.getInstance().selectById(selectedNK.getHoKhauID())) != null) { //TH hộ khẩu chưa bị xóa
                    hk.setIdChuHo(0);
                    hk.setTenChuHo("");
                    HoKhauDAO.getInstance().update(hk);
                    if(NhanKhauDAO.getInstance().deleteByID(selectedNK.getId())) {
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Bạn đã xóa chủ hộ thành công, hãy thêm chủ hộ mới vào hộ khẩu có ID = " + hk.getIdHoKhau() + "!");
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Xóa chủ hộ không thành công!");
                    }
                } else {    //TH hộ khẩu đã bị xóa từ trước
                    if(NhanKhauDAO.getInstance().deleteByID(selectedNK.getId())) {
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Bạn đã xóa chủ hộ thành công!");
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Xóa chủ hộ không thành công!");
                    }
                }
            } else {    //Khong la chu ho
                if(NhanKhauDAO.getInstance().deleteByID(selectedNK.getId())) {
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Bạn đã xóa nhân khẩu thành công!");
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Xóa nhân khẩu không thành công!");
                }
            } 
        }
        refreshNhanKhauTable();
    }

    public boolean dangSuaNK = false;

    @FXML
    public void moNhanKhauDialog(ActionEvent event) {
        if(!nhanKhauPane1.isVisible() && back1_btn.isArmed()) {
            nhanKhauPane1.setVisible(true);
            nhanKhauPane2.setVisible(false);
        }
        if (!nhanKhauPane1.isVisible() && sua_btn.isArmed()) {
            NhanKhau selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
            if (selectedNK == null) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa chọn nhân khẩu!");
            } else {
                dangSuaNK = true;
                hoTen_tf.setText(selectedNK.getHoTen());
                if(selectedNK.getGioiTinh().equals("Nam")) {
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
            } 
        } else if (themNk_btn.isArmed()) {
            nhanKhauPane1.setVisible(true);
        }
    }

    @FXML
    public void moNhanKhauDialog2(ActionEvent event) {
        if(!nhanKhauPane2.isVisible() && back2_btn.isArmed()) {
            nhanKhauPane2.setVisible(true);
            nhanKhauPane3.setVisible(false);
        }
        if (!nhanKhauPane2.isVisible()) {
            if (dangSuaNK) {
                NhanKhau selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
                cccd_tf.setText(selectedNK.getCccd());

                Date sqlDate = Date.valueOf(selectedNK.getNgayCapID().toString());
                ngayCap_date.setValue(sqlDate.toLocalDate());
                noiCap_tf.setText(selectedNK.getNoiCapID());

                // Chua lam phan dang ky thuong tru

                nhanKhauPane2.setVisible(true);
                nhanKhauPane1.setVisible(false);
            } else {
                nhanKhauPane2.setVisible(true);
                nhanKhauPane1.setVisible(false);
            }
        }
    }

    @FXML
    public void moNhanKhauDialog3(ActionEvent event) {
        if (!nhanKhauPane3.isVisible()) {
            if (dangSuaNK) {
                NhanKhau selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
                ghiChu_tf.setText(selectedNK.getGhiChu());

                nhanKhauPane3.setVisible(true);
                nhanKhauPane2.setVisible(false);
            } else {
                nhanKhauPane3.setVisible(true);
                nhanKhauPane2.setVisible(false);
            }
        }
    }

    @FXML
    public void luuNhanKhau(ActionEvent event) {
        //Sửa nhân khẩu
        if (dangSuaNK) {
            NhanKhau selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
            selectedNK.setHoTen(hoTen_tf.getText());
            selectedNK.setBiDanh(biDanh_tf.getText());
            selectedNK.setQhChuHo(quanHe_tf.getText());
            if(gioiTinh_cb.getValue().equals("Giới tính")) {
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

                if (NhanKhauDAO.getInstance().update(selectedNK)) {
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Sửa nhân khẩu thành công!");
                    dongNhanKhauDialog(event);
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Sửa nhân khẩu không thành công!");
                }
            }
        } //Thêm nhân khẩu 
        else {
            NhanKhau newNK = new NhanKhau();
            if (luuNK_btn.isArmed()) {
                newNK.setHoTen(hoTen_tf.getText());
                newNK.setBiDanh(biDanh_tf.getText());
                newNK.setQhChuHo(quanHe_tf.getText());
                if(gioiTinh_cb.getValue().equals("Giới tính")) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Bạn chưa nhập giới tính!");
                } else {
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
                    newNK.setGhiChu(ghiChu_tf.getText());

                    if (NhanKhauDAO.getInstance().insert(newNK)) {
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Thêm nhân khẩu thành công!");
                        dongNhanKhauDialog(event);
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Thêm nhân khẩu không thành công!");
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
        ghiChu_tf.clear();
        nhanKhauPane3.setVisible(false);
        dangSuaNK = false;
        search_tf.clear();
        refreshNhanKhauTable();
    }

    public void refreshNhanKhauTable() {
        nhanKhauTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().selectAll());
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

        nhanKhauTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().selectAll());
        idNhanKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        tenNhanKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        idHoKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("hoKhauID"));
        nhanKhauTable.setItems(nhanKhauTableList);

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
        if (nhanKhauPane3 != null)
            nhanKhauPane3.setVisible(false);
    }

}
