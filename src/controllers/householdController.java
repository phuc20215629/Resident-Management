package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.authentication.AlertMessage;
import application.dao.GiaoDichDAO;
import application.dao.HoKhauDAO;
import application.dao.LichSuThayDoiDAO;
import application.dao.NhanKhauDAO;
import application.dao.TamTruDAO;
import application.dao.TamVangDAO;
import application.model.HoKhau;
import application.model.LichSuThayDoi;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class householdController implements Initializable {

    @FXML
    private Button themXoaNK_btn;

    @FXML
    private Button huyThemXoa_btn;

    @FXML
    private Label diaChi_lbl;

    @FXML
    private Button tamTru_btn;

    @FXML
    private Button tamVang_btn;

    @FXML
    private TextField idTamTru_tf;

    @FXML
    private TextField diaChiTamTru_tf;

    @FXML
    private TextField hoTenTamTru_tf;

    @FXML
    private DatePicker tuNgay_date;

    @FXML
    private DatePicker denNgay_date;

    @FXML
    private Label thanhVien_lbl;

    @FXML
    private Button xoaThanhVien_btn;

    @FXML
    private TextField newIdChuHo_tf;

    @FXML
    private TextField idChuHo_tf;

    @FXML
    private Button find_btn;

    @FXML
    private Button suaHoKhauDialog_btn;

    @FXML
    private Button themHoKhauDialog_btn;

    @FXML
    private Button saveTachKhau_btn;

    @FXML
    private Button saveTamTru_btn;

    @FXML
    private Button addHousehold_btn;

    @FXML
    private Button add_btn;

    @FXML
    private Button change_btn;

    @FXML
    private TextField duongPho_tf;

    @FXML
    private ImageView gradient;

    @FXML
    private Pane hoKhauDialogPane;

    @FXML
    private ImageView home;

    @FXML
    private Label houseHold;

    @FXML
    private TextArea lichSuThayDoi_tf;

    @FXML
    private Button logout;

    @FXML
    private TextField maHo_tf;

    @FXML
    private Label nhanKhau;

    @FXML
    private TextField idThanhVien_tf;

    @FXML
    private TextField newDuong_tf;

    @FXML
    private TextField newMaHo_tf;

    @FXML
    private TextField newNhanKhau_tf;

    @FXML
    private TextField newPhuong_tf;

    @FXML
    private TextField newQuan_tf;

    @FXML
    private TextField newSoNha_tf;

    @FXML
    private TextField newTenChuHo_tf;

    @FXML
    private TextField phuong_tf;

    @FXML
    private TextField quan_tf;

    @FXML
    private Button save_btn;

    @FXML
    private Button themThanhVien_btn;

    @FXML
    private ChoiceBox<String> searchHo;

    @FXML
    private TextField search_tf;

    @FXML
    private TextField soNha_tf;

    @FXML
    private Label stat;

    @FXML
    private Label idThanhVien_lbl;

    @FXML
    private Pane tachKhauDialogPane;

    @FXML
    private Pane tamTruDialogPane;

    @FXML
    private TextField tenChuHo_tf;

    @FXML
    private Label tenChuHo_lbl;

    @FXML
    private VBox menu;

    @FXML
    private TableView<HoKhau> hoKhauTable;
    @FXML
    private TableColumn<HoKhau, String> tenChuHoCol;
    @FXML
    private TableColumn<HoKhau, Integer> idHoKhauCol;
    @FXML
    private TableColumn<HoKhau, String> ghiChuCol;

    @FXML
    private TableView<NhanKhau> nhanKhauTable;
    @FXML
    private TableColumn<NhanKhau, Integer> nhanKhauID_col;
    @FXML
    private TableColumn<NhanKhau, String> nhanKhauName_col;

    ObservableList<NhanKhau> nhanKhauTableList;
    ObservableList<HoKhau> hoKhauTableList;
    ObservableList<String> hoKhauList = FXCollections.observableArrayList("Mã hộ", "Chủ hộ");

    // External section
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

    // Internal section
    @FXML
    public void timHoKhau(ActionEvent event) {
        if (search_tf.getText() != "") {
            if (searchHo.getValue() == "Mã hộ") {
                int maHo = 0;
                try {
                    maHo = Integer.parseInt(search_tf.getText());
                    if (HoKhauDAO.getInstance().selectById(maHo) != null) {
                        ArrayList<HoKhau> list = new ArrayList<>();
                        list.add(HoKhauDAO.getInstance().selectById(maHo));
                        refreshHoKhauTable(list);
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.errorMessage("Không tìm thấy hộ khẩu!");
                    }
                } catch (Exception e) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Mã hộ phải là số!");
                }
            }
            // searchHo.getValue() = "Chủ hộ"
            else {
                String tenChuHo = search_tf.getText();
                if (HoKhauDAO.getInstance().selectByTenChuHo(tenChuHo) != null) {
                    refreshHoKhauTable(HoKhauDAO.getInstance().selectByTenChuHo(tenChuHo));
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Không tìm thấy tên chủ hộ!");
                }
            }
        } else {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Bạn chưa nhập thông tin tìm kiếm!");
            refreshHoKhauTable(HoKhauDAO.getInstance().selectAll());
        }
        search_tf.clear();
    }

    HoKhau selectedHK = new HoKhau();
    NhanKhau selectedNK = new NhanKhau();

    @FXML
    public void moHoKhauDialog(ActionEvent event) {
        themXoaNK_btn.setVisible(false);
        // Sửa hộ
        if (!hoKhauDialogPane.isVisible() && change_btn.isArmed()) {
            selectedHK = hoKhauTable.getSelectionModel().getSelectedItem();
            if (selectedHK == null) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa chọn hổ khẩu!");
            } else {
                suaHoKhauDialog_btn.setVisible(true);
                themHoKhauDialog_btn.setVisible(false);
                hoKhauDialogPane.setVisible(true);
                nhanKhauTable.setVisible(true);
                themThanhVien_btn.setVisible(true);
                xoaThanhVien_btn.setVisible(true);
                idThanhVien_lbl.setVisible(true);
                idThanhVien_tf.setVisible(true);
                thanhVien_lbl.setText("Thành viên");
                menu.setVisible(false);

                idChuHo_tf.setText(Integer.toString(selectedHK.getIdChuHo()));
                tenChuHo_tf.setText(selectedHK.getTenChuHo());

                maHo_tf.setText(Integer.toString(selectedHK.getIdHoKhau()));
                soNha_tf.setText(Integer.toString(selectedHK.getSoNha()));
                duongPho_tf.setText(selectedHK.getDuong());
                phuong_tf.setText(selectedHK.getPhuong());
                quan_tf.setText(selectedHK.getQuan());
                String lichSuThayDoi = "";
                ArrayList<LichSuThayDoi> list = LichSuThayDoiDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau());
                for (LichSuThayDoi l : list) {
                    lichSuThayDoi = lichSuThayDoi + l.getThayDoi() + "\n";
                }
                lichSuThayDoi_tf.setText(lichSuThayDoi);

                if (Integer.parseInt(idChuHo_tf.getText()) == 0) {
                    refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau()));
                    idChuHo_tf.setText("");
                    thanhVien_lbl.setText("Thêm chủ hộ");
                } else
                    refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau()));
            }
        }
        // Thêm hộ
        else if (!hoKhauDialogPane.isVisible() && addHousehold_btn.isArmed()) {
            hoKhauDialogPane.setVisible(true);
            idChuHo_tf.clear();
            thanhVien_lbl.setText("Thêm chủ hộ");
            themHoKhauDialog_btn.setVisible(true);
            suaHoKhauDialog_btn.setVisible(false);
            refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(0)); // Tim nhan khau chua co ho khau
            themThanhVien_btn.setVisible(false);
            idThanhVien_lbl.setVisible(false);
            idThanhVien_tf.setVisible(false);
            themXoaNK_btn.setVisible(false);
            xoaThanhVien_btn.setVisible(false);
            menu.setVisible(false);

            nhanKhauTable.setOnMouseClicked((MouseEvent e) -> {
                selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
                idChuHo_tf.setText(Integer.toString(selectedNK.getId()));
            });

            idChuHo_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                int idNhanKhau = 0;
                try {
                    idNhanKhau = Integer.parseInt(idChuHo_tf.getText());
                    NhanKhau chuHo = NhanKhauDAO.getInstance().selectById(idNhanKhau);
                    tenChuHo_tf.setText(chuHo.getHoTen());
                } catch (Exception e) {
                    tenChuHo_tf.setText("");
                }
            });
        }
    }

    @FXML
    void themThanhVien(ActionEvent event) {
        if (!NhanKhauDAO.getInstance().selectByHKId(0).isEmpty()) {
            themThanhVien_btn.setVisible(false);
            xoaThanhVien_btn.setVisible(false);
            thanhVien_lbl.setText("Thêm thành viên");
            themXoaNK_btn.setVisible(true);
            huyThemXoa_btn.setVisible(true);
            huyThemXoa_btn.setLayoutX(607);
            themXoaNK_btn.setText("Thêm");

            refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(0));

            nhanKhauTable.setOnMouseClicked((MouseEvent e) -> {
                selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
                idThanhVien_tf.setText(Integer.toString(selectedNK.getId()));
            });
        } else {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Không có nhân khẩu nào để thêm!");
        }
    }

    @FXML
    void xoaThanhVien(ActionEvent event) {
        if (!NhanKhauDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau()).isEmpty()) {
            themThanhVien_btn.setVisible(false);
            xoaThanhVien_btn.setVisible(false);
            thanhVien_lbl.setText("Xóa thành viên");
            themXoaNK_btn.setVisible(true);
            huyThemXoa_btn.setVisible(true);
            huyThemXoa_btn.setLayoutX(590);
            themXoaNK_btn.setText("Xóa");

            refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau()));

            nhanKhauTable.setOnMouseClicked((MouseEvent e) -> {
                selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
                idThanhVien_tf.setText(Integer.toString(selectedNK.getId()));
            });
        } else {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Không có nhân khẩu nào để xóa!");
        }
    }

    @FXML
    void huyThemXoaNK(ActionEvent event) {
        themXoaNK_btn.setVisible(false);
        huyThemXoa_btn.setVisible(false);
        themThanhVien_btn.setVisible(true);
        xoaThanhVien_btn.setVisible(true);
        thanhVien_lbl.setText("Thành viên");

        refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau()));
    }

    @FXML
    void luuNhanKhau(ActionEvent event) {
        nhanKhauTable.setOnMouseClicked(null);
        selectedHK = hoKhauTable.getSelectionModel().getSelectedItem();
        selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
        int idHoKhau = selectedHK.getIdHoKhau();
        // Thêm thành viên vào hộ
        if (selectedNK != null) {
            if (themXoaNK_btn.getText().equals("Thêm")) {
                selectedNK.setHoKhauID(idHoKhau);
                NhanKhauDAO.getInstance().update(selectedNK);
                selectedHK.setSoThanhVien(HoKhauDAO.getInstance().getSoThanhVien(idHoKhau));
                selectedHK.setGhiChu("Đã sửa");
                HoKhauDAO.getInstance().update(selectedHK);
                String lichSuThayDoi = "Thêm thành viên " + selectedNK.getId() + " " + LocalDate.now().toString();
                LichSuThayDoiDAO.getInstance().insert(new LichSuThayDoi(idHoKhau, lichSuThayDoi));

                refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(idHoKhau));
                idThanhVien_tf.clear();
                thanhVien_lbl.setText("Thành viên");

            }
            // Xóa thành viên khỏi hộ
            if (themXoaNK_btn.getText().equals("Xóa")) {
                if (selectedNK.getLaChuHo() == 1) {
                    idChuHo_tf.clear();
                    tenChuHo_tf.clear();
                    selectedHK.setIdChuHo(0);
                    selectedHK.setTenChuHo("");
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Bạn vừa xóa chủ hộ, hãy thêm chủ hộ mới từ bảng dưới đây");
                }
                selectedNK.setHoKhauID(0);
                selectedNK.setLaChuHo(0);
                selectedNK.setQhChuHo("");
                NhanKhauDAO.getInstance().update(selectedNK);

                selectedHK.setSoThanhVien(HoKhauDAO.getInstance().getSoThanhVien(selectedHK.getIdHoKhau()));
                selectedHK.setGhiChu("Đã sửa");
                HoKhauDAO.getInstance().update(selectedHK);

                String lichSuThayDoi = "Xóa thành viên " + selectedNK.getId() + " " + LocalDate.now().toString();
                LichSuThayDoiDAO.getInstance().insert(new LichSuThayDoi(selectedHK.getIdHoKhau(), lichSuThayDoi));

                refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau()));
                if (idChuHo_tf.getText().isEmpty()) {
                    // TH xoa chu ho thi table dua ra cac nhan khau thuoc ho khau nay de them lam
                    // chu ho moi
                    thanhVien_lbl.setText("Thêm chủ hộ");
                    nhanKhauTable.setOnMouseClicked(e -> {
                        NhanKhau newChuHo = nhanKhauTable.getSelectionModel().getSelectedItem();
                        idChuHo_tf.setText(Integer.toString(newChuHo.getId()));
                        tenChuHo_tf.setText(newChuHo.getHoTen());
                    });

                } else {
                    thanhVien_lbl.setText("Thành viên");
                }

                idThanhVien_tf.clear();
            }
            themXoaNK_btn.setVisible(false);
            huyThemXoa_btn.setVisible(false);
            themThanhVien_btn.setVisible(true);
            xoaThanhVien_btn.setVisible(true);
        } else {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Bạn chưa chọn nhân khẩu!");
        }
    }

    @FXML
    void themHoKhau(ActionEvent event) {
        int ID = 0, soNha = 0;
        try {
            ID = Integer.parseInt(idChuHo_tf.getText());
            soNha = Integer.parseInt(soNha_tf.getText());
        } catch (Exception e) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Số nhà và ID phải là số!");
        }
        String duong = duongPho_tf.getText();
        String phuong = phuong_tf.getText();
        String quan = quan_tf.getText();

        if (soNha_tf.getText().isBlank() || duong.isBlank() || phuong.isBlank() || quan.isBlank()) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Hãy nhập đủ thông tin!");
        } else {
            ArrayList<NhanKhau> nhanKhauList = NhanKhauDAO.getInstance().selectAll();
            boolean checkIDChuHo = false;
            NhanKhau chuHo = new NhanKhau();
            for (NhanKhau nk : nhanKhauList) {
                if (ID == nk.getId()) {
                    if (nk.getLaChuHo() == 1) {
                        break;
                    } else {
                        checkIDChuHo = true;
                        chuHo = nk;
                        chuHo.setLaChuHo(1);
                        chuHo.setQhChuHo("Chủ hộ");
                        break;
                    }
                }
            }
            if (checkIDChuHo) {
                try {
                    HoKhauDAO.getInstance()
                            .insert(new HoKhau(chuHo.getHoTen(), ID, 1, duong, phuong, quan, soNha, null));
                    chuHo.setHoKhauID(HoKhauDAO.getInstance().getLatestID());
                    NhanKhauDAO.getInstance().update(chuHo);
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Thêm hộ thành công!");
                    refreshHoKhauTable(HoKhauDAO.getInstance().selectAll());
                    dongHoKhauDialog(event);
                } catch (Exception e) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Thêm hộ không thành công!");
                }
            } else {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Không tìm thấy ID hoặc ID này đã là chủ hộ của hộ khác!");
            }
        }
    }

    @FXML
    void xoaHoKhau(ActionEvent event) {
        selectedHK = hoKhauTable.getSelectionModel().getSelectedItem();
        if (selectedHK == null) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Bạn chưa chọn hộ khẩu!");
        } else {
            int idHoKhau = selectedHK.getIdHoKhau();
            TamTruDAO.getInstance().deleteByHKID(idHoKhau);
            TamVangDAO.getInstance().deleteByHKID(idHoKhau);
            LichSuThayDoiDAO.getInstance().deleteByID(idHoKhau);
            GiaoDichDAO.getInstance().deleteByHKID(idHoKhau);
            if (HoKhauDAO.getInstance().deleteByID(idHoKhau)) {
                AlertMessage alert = new AlertMessage();
                alert.successMessage("Bạn đã xóa hộ khẩu thành công!");
                ArrayList<NhanKhau> list = NhanKhauDAO.getInstance().selectByHKId(idHoKhau);
                for (NhanKhau x : list) {
                    x.setHoKhauID(0);
                    x.setLaChuHo(0);
                    NhanKhauDAO.getInstance().update(x);
                }
            } else {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Xóa hộ khẩu không thành công!");
            }
        }
        refreshHoKhauTable(HoKhauDAO.getInstance().selectAll());
    }

    @FXML
    void suaHoKhau(ActionEvent event) {
        String idChuHoMoi = idChuHo_tf.getText();
        int soNha = 0;
        try {
            soNha = Integer.parseInt(soNha_tf.getText());
        } catch (Exception e) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Số nhà phải là số!");
        }

        String duong = duongPho_tf.getText();
        String phuong = phuong_tf.getText();
        String quan = quan_tf.getText();

        if (idChuHoMoi.isBlank() || soNha_tf.getText().isBlank() || duong.isBlank() || phuong.isBlank()
                || quan.isBlank()) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Hãy nhập đủ thông tin!");
        } else {
            String lichSuThayDoi = "Thay đổi";
            int idChuHoCu = selectedHK.getIdChuHo();
            boolean checkIdChuHo = false;
            boolean themThanhVien = false;
            boolean xoaThanhVien = false;
            NhanKhau chuHoMoi = new NhanKhau();
            if (selectedHK.getSoNha() != soNha) {
                lichSuThayDoi = lichSuThayDoi + " " + "Số nhà";
            }
            if (!selectedHK.getDuong().equals(duong)) {
                lichSuThayDoi = lichSuThayDoi + " " + "Đường";
            }
            if (!selectedHK.getPhuong().equals(phuong)) {
                lichSuThayDoi = lichSuThayDoi + " " + "Phường";
            }
            if (!selectedHK.getQuan().equals(quan)) {
                lichSuThayDoi = lichSuThayDoi + " " + "Quận";
            }
            if (idChuHoCu == Integer.parseInt(idChuHoMoi)) { // Neu khong thay doi chu ho
                checkIdChuHo = true;
            }
            if (idChuHoCu != Integer.parseInt(idChuHoMoi)) { // Neu thay doi chu ho
                for (NhanKhau nk : nhanKhauTableList) {
                    if (idChuHoMoi.equals(Integer.toString(nk.getId()))
                            && (nk.getHoKhauID() == 0 || nk.getHoKhauID() == selectedHK.getIdHoKhau())) {
                        checkIdChuHo = true;
                        chuHoMoi = nk;
                        chuHoMoi.setLaChuHo(1);
                        chuHoMoi.setQhChuHo("Chủ hộ");
                        chuHoMoi.setHoKhauID(Integer.parseInt(maHo_tf.getText()));
                        NhanKhauDAO.getInstance().update(chuHoMoi);

                        selectedHK.setTenChuHo(chuHoMoi.getHoTen());
                        selectedHK.setIdChuHo(chuHoMoi.getId());
                        selectedHK.setGhiChu("Đã sửa");
                        selectedHK.setSoThanhVien(HoKhauDAO.getInstance().getSoThanhVien(selectedHK.getIdHoKhau()));
                        HoKhauDAO.getInstance().update(selectedHK);
                        break;
                    }
                }
                lichSuThayDoi = lichSuThayDoi + " " + "Chủ hộ";
            }
            if (checkIdChuHo) {
                try {
                    // Cập nhật chủ hộ mới
                    if (selectedHK.getSoNha() != soNha || !selectedHK.getDuong().equals(duong) ||
                            !selectedHK.getPhuong().equals(phuong) || !selectedHK.getQuan().equals(quan) ||
                            idChuHoCu != Integer.parseInt(idChuHoMoi) || themThanhVien || xoaThanhVien) {
                        lichSuThayDoi = lichSuThayDoi + " " + LocalDate.now().toString();
                        LichSuThayDoiDAO.getInstance()
                                .insert(new LichSuThayDoi(selectedHK.getIdHoKhau(), lichSuThayDoi));
                    }
                    dongHoKhauDialog(event);
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Sửa thành công!");
                    refreshHoKhauTable(HoKhauDAO.getInstance().selectAll());
                } catch (Exception e) {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Sửa không thành công!");
                }
            } else {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("ID chủ hộ không tồn tại!");
            }
        }
    }

    @FXML
    public void dongHoKhauDialog(ActionEvent event) {
        hoKhauDialogPane.setVisible(false);
        tenChuHo_tf.clear();
        maHo_tf.clear();
        soNha_tf.clear();
        duongPho_tf.clear();
        phuong_tf.clear();
        quan_tf.clear();
        idThanhVien_tf.clear();
        lichSuThayDoi_tf.clear();
        menu.setVisible(true);
    }

    @FXML
    public void moTachKhauDialog(ActionEvent event) {
        if (!tachKhauDialogPane.isVisible())
            tachKhauDialogPane.setVisible(true);
        menu.setVisible(false);
    }

    @FXML
    void saveTachKhau(ActionEvent event) {
        if (newIdChuHo_tf.getText().isBlank() || newSoNha_tf.getText().isBlank() || newDuong_tf.getText().isBlank()
                || newPhuong_tf.getText().isBlank() || newQuan_tf.getText().isBlank()) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("Hãy nhập đủ thông tin!");
        } else {
            int newIdChuHo = 0, newSoNha = 0;
            try {
                newIdChuHo = Integer.parseInt(newIdChuHo_tf.getText());
                newSoNha = Integer.parseInt(newSoNha_tf.getText());
                String duong = newDuong_tf.getText();
                String phuong = newPhuong_tf.getText();
                String quan = newQuan_tf.getText();

                boolean checkIdChuHo = false;
                NhanKhau newChuHo = new NhanKhau();
                ArrayList<NhanKhau> list = NhanKhauDAO.getInstance().selectByHKId(Integer.parseInt(maHo_tf.getText()));
                for (NhanKhau nk : list) {
                    if (newIdChuHo == nk.getId() && nk.getLaChuHo() == 0) {
                        checkIdChuHo = true;
                        newChuHo = nk;
                        newChuHo.setLaChuHo(1);
                        newChuHo.setQhChuHo("Chủ hộ");
                        break;
                    }
                }
                if (checkIdChuHo) {
                    try {
                        HoKhau newHK = new HoKhau(newChuHo.getHoTen(), newIdChuHo, 1, duong, phuong, quan, newSoNha,
                                null);
                        HoKhauDAO.getInstance().insert(newHK);
                        newChuHo.setHoKhauID(HoKhauDAO.getInstance().getLatestID());
                        NhanKhauDAO.getInstance().update(newChuHo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String lichSuThayDoi = "Tách hộ " + LocalDate.now().toString();
                    LichSuThayDoiDAO.getInstance().insert(new LichSuThayDoi(selectedHK.getIdHoKhau(), lichSuThayDoi));
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Tách hộ thành công!");
                    refreshHoKhauTable(HoKhauDAO.getInstance().selectAll());
                    dongHoKhauDialog(event);
                    dongTachKhauDialog(event);
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("ID bạn vừa nhập đã là chủ hộ hoặc không là thành viên của hộ!");
                }
            } catch (Exception e) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Số nhà và ID chủ hộ phải là số!");
            }
        }
    }

    @FXML
    public void dongTachKhauDialog(ActionEvent event) {
        tachKhauDialogPane.setVisible(false);
    }

    boolean tamTru = false; // tamTru = false -> tam vang, tamTru = true -> tamTru

    @FXML
    public void moTamTruDialog(ActionEvent event) {
        // Tam tru
        if (!tamTruDialogPane.isVisible() && tamTru_btn.isArmed()) {
            tamTru = true;
            tamTruDialogPane.setVisible(true);
            diaChi_lbl.setText("Địa chỉ trước khi chuyển tới");
            idTamTru_tf.textProperty().addListener((observable, oldValue, newValue) -> {
                int idNhanKhau = 0;
                try {
                    idNhanKhau = Integer.parseInt(newValue);
                    NhanKhau nk = NhanKhauDAO.getInstance().selectById(idNhanKhau);
                    hoTenTamTru_tf.setText(nk.getHoTen());
                } catch (Exception e) {
                    hoTenTamTru_tf.clear();
                }
            });
        }
        // Tam vang
        if (!tamTruDialogPane.isVisible() && tamVang_btn.isArmed()) {
            selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
            if (selectedNK == null) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa chọn nhân khẩu tạm vắng!");
            } else {
                tamTru = false;
                tamTruDialogPane.setVisible(true);
                diaChi_lbl.setText("Địa chỉ nơi chuyển tới");
                idTamTru_tf.setEditable(false);
                idTamTru_tf.setText(Integer.toString(selectedNK.getId()));
                hoTenTamTru_tf.setText(selectedNK.getHoTen());
            }
        }
    }

    @FXML
    void saveTamTru(ActionEvent event) {
        // Tam tru
        if (tamTru) {
            String diaChi = diaChiTamTru_tf.getText();
            if (hoTenTamTru_tf.getText().isBlank()) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("ID nhân khẩu không hợp lệ!");
            } else if (diaChi.isBlank() || tuNgay_date.getValue() == null || denNgay_date.getValue() == null) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa nhập đủ thông tin!");
            } else if (tuNgay_date.getValue().isAfter(denNgay_date.getValue())) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Thời gian tạm trú không hợp lệ!");
            } else {
                int idNK = Integer.parseInt(idTamTru_tf.getText());
                NhanKhau nk = NhanKhauDAO.getInstance().selectById(idNK);
                nk.setHoKhauID(selectedHK.getIdHoKhau());
                nk.setGhiChu("Tạm trú");
                NhanKhauDAO.getInstance().update(nk);
                TamTru tt = new TamTru(idNK, selectedHK.getIdHoKhau(),
                        Date.valueOf(tuNgay_date.getValue()), Date.valueOf(denNgay_date.getValue()), diaChi);
                if (TamTruDAO.getInstance().insert(tt)) {
                    String lichSuThayDoi = "Đăng ký tạm trú cho nhân khẩu " + idNK;
                    LichSuThayDoiDAO.getInstance().insert(new LichSuThayDoi(selectedHK.getIdHoKhau(), lichSuThayDoi));
                    AlertMessage alert = new AlertMessage();
                    alert.successMessage("Đăng ký tạm trú thành công!");
                    dongTamTruDialog(event);
                    dongHoKhauDialog(event);
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Nhân khẩu đã đăng kí tạm trú trước đó!");
                    idTamTru_tf.clear();
                    hoTenTamTru_tf.clear();
                    diaChiTamTru_tf.clear();
                    tuNgay_date.setValue(null);
                    denNgay_date.setValue(null);
                }
            }
        }
        // Tam vang
        else {
            String diaChi = diaChiTamTru_tf.getText();
            if (diaChi.isBlank() || tuNgay_date.getValue() == null || denNgay_date.getValue() == null) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa nhập đủ thông tin!");
            }
            if (tuNgay_date.getValue().isAfter(denNgay_date.getValue())) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Thời gian tạm vắng không hợp lệ!");
            } else {
                int idNK = Integer.parseInt(idTamTru_tf.getText());
                if (TamVangDAO.getInstance().insert(new TamVang(idNK, selectedNK.getHoKhauID(),
                        Date.valueOf(tuNgay_date.getValue()), Date.valueOf(denNgay_date.getValue()), diaChi))) {
                    String lichSuThayDoi = "Đăng ký tạm vắng cho nhân khẩu " + idNK;
                    LichSuThayDoiDAO.getInstance().insert(new LichSuThayDoi(selectedHK.getIdHoKhau(), lichSuThayDoi));
                    // Neu dang ky tam vang chu ho
                    if (selectedNK.getLaChuHo() == 1) {
                        AlertMessage alert = new AlertMessage();
                        alert.infoMessage("Bạn vừa đăng kí tạm vắng cho chủ hộ! Hãy thêm chủ hộ tạm thời mới!");
                        dongTamTruDialog(event);
                    } else {
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Đăng ký tạm vắng thành công!");
                        dongTamTruDialog(event);
                        dongHoKhauDialog(event);
                    }
                } else {
                    AlertMessage alert = new AlertMessage();
                    alert.errorMessage("Nhân khẩu đã đăng kí tạm vắng trước đó!");
                    idTamTru_tf.clear();
                    hoTenTamTru_tf.clear();
                    diaChiTamTru_tf.clear();
                    tuNgay_date.setValue(null);
                    denNgay_date.setValue(null);
                }

            }
        }
    }

    @FXML
    public void dongTamTruDialog(ActionEvent event) {
        tamTruDialogPane.setVisible(false);
    }

    public void refreshNhanKhauTable(ArrayList<NhanKhau> list) {
        nhanKhauTableList = FXCollections.observableArrayList(list);
        nhanKhauID_col.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        nhanKhauName_col.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        nhanKhauTable.setItems(nhanKhauTableList);
    }

    public void refreshHoKhauTable(ArrayList<HoKhau> list) {
        hoKhauTableList = FXCollections.observableArrayList(list);
        idHoKhauCol.setCellValueFactory(new PropertyValueFactory<HoKhau, Integer>("idHoKhau"));
        tenChuHoCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("tenChuHo"));
        ghiChuCol.setCellValueFactory(new PropertyValueFactory<HoKhau, String>("ghiChu"));
        hoKhauTable.setItems(hoKhauTableList);
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

        refreshHoKhauTable(HoKhauDAO.getInstance().selectAll());

        // Update in realtime - Tam tru
        TamTruDAO.getInstance().deleteByTimeNotEffective();
        ArrayList<TamTru> listTamTruHomNay = TamTruDAO.getInstance()
                .selectByTimeEffective();
        if (!listTamTruHomNay.isEmpty()) {
            for (TamTru tt : listTamTruHomNay) {
                int idNk = tt.getIdNhanKhau();
                int idHk = tt.getIdHoKhau();
                NhanKhau nkTamTru = NhanKhauDAO.getInstance().selectById(idNk);
                nkTamTru.setHoKhauID(idHk);
                NhanKhauDAO.getInstance().update(nkTamTru);
                HoKhau hkTamTru = HoKhauDAO.getInstance().selectById(idHk);
                hkTamTru.setSoThanhVien(HoKhauDAO.getInstance().getSoThanhVien(idHk));
                HoKhauDAO.getInstance().update(hkTamTru);
            }
        }

        // Update in realtime - Tam vang
        ArrayList<TamVang> listTamVangHomNay = TamVangDAO.getInstance().selectByTimeMove();
        ArrayList<TamVang> listTroVeHomNay = TamVangDAO.getInstance().selectByTimeReturn();
        if (listTamVangHomNay != null) {
            for (TamVang tv : listTamVangHomNay) {
                int idNk = tv.getIdNhanKhau();
                int idHk = tv.getIdHoKhau();
                NhanKhau nkTamVang = NhanKhauDAO.getInstance().selectById(idNk);
                nkTamVang.setHoKhauID(0);
                nkTamVang.setGhiChu("Tạm vắng");
                NhanKhauDAO.getInstance().update(nkTamVang);
                HoKhau hkTamVang = HoKhauDAO.getInstance().selectById(idHk);
                hkTamVang.setSoThanhVien(HoKhauDAO.getInstance().getSoThanhVien(idHk));
                HoKhauDAO.getInstance().update(hkTamVang);
            }
        }
        if (listTroVeHomNay != null) {
            for (TamVang tv : listTroVeHomNay) {
                int idNk = tv.getIdNhanKhau();
                int idHk = tv.getIdHoKhau();
                NhanKhau nkTamVang = NhanKhauDAO.getInstance().selectById(idNk);
                nkTamVang.setHoKhauID(idHk);
                nkTamVang.setGhiChu("");
                NhanKhauDAO.getInstance().update(nkTamVang);
                HoKhau hkTamVang = HoKhauDAO.getInstance().selectById(idHk);
                hkTamVang.setSoThanhVien(HoKhauDAO.getInstance().getSoThanhVien(idHk));

                if (nkTamVang.getLaChuHo() == 1) {
                    NhanKhau chuHoTamThoi = NhanKhauDAO.getInstance().selectById(hkTamVang.getIdChuHo());
                    chuHoTamThoi.setLaChuHo(0);
                    chuHoTamThoi.setQhChuHo("");
                    hkTamVang.setIdChuHo(nkTamVang.getId());
                    hkTamVang.setTenChuHo(nkTamVang.getHoTen());
                }

                HoKhauDAO.getInstance().update(hkTamVang);
                TamVangDAO.getInstance().deleteByNKID(idNk);
            }
        }

        if (searchHo != null) {
            searchHo.setValue(hoKhauList.get(0));
            searchHo.setItems(hoKhauList);
        }
    }

}
