package controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.authentication.AlertMessage;
import application.dao.HoKhauDAO;
import application.dao.LichSuThayDoiDAO;
import application.dao.NhanKhauDAO;
import application.model.HoKhau;
import application.model.LichSuThayDoi;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class householdController implements Initializable {

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
    private Label household;

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
        Stage window = (Stage) household.getScene().getWindow();
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

    // Internal section
    HoKhau selectedHK = new HoKhau();
    NhanKhau selectedNK = new NhanKhau();

    @FXML
    void getItemNK(MouseEvent event) {
        selectedNK = nhanKhauTable.getSelectionModel().getSelectedItem();
        idChuHo_tf.setText(Integer.toString(selectedNK.getId()));
        tenChuHo_tf.setText(selectedNK.getHoTen());
    }

    @FXML
    public void moHoKhauDialog(ActionEvent event) {
        // Sửa hộ
        if (!hoKhauDialogPane.isVisible() && change_btn.isArmed()) {
            selectedHK = hoKhauTable.getSelectionModel().getSelectedItem();
            if (selectedHK == null) {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Bạn chưa chọn hổ khẩu!");
            } else {
                tenChuHo_lbl.setText("Tên chủ hộ");
                suaHoKhauDialog_btn.setVisible(true);
                themHoKhauDialog_btn.setVisible(false);
                hoKhauDialogPane.setVisible(true);
                nhanKhauTable.setVisible(true);
                themThanhVien_btn.setVisible(true);
                xoaThanhVien_btn.setVisible(true);
                idThanhVien_lbl.setVisible(true);
                idThanhVien_tf.setVisible(true);

                idChuHo_tf.setText(Integer.toString(selectedHK.getIdChuHo()));
                tenChuHo_tf.setText(selectedHK.getTenChuHo());
                maHo_tf.setText(Integer.toString(selectedHK.getIdHoKhau()));
                soNha_tf.setText(Integer.toString(selectedHK.getSoNha()));
                duongPho_tf.setText(selectedHK.getDuong());
                phuong_tf.setText(selectedHK.getPhuong());
                quan_tf.setText(selectedHK.getQuan());
                String lichSuThayDoi = "";
                ArrayList<LichSuThayDoi> list = LichSuThayDoiDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau());
                for(LichSuThayDoi l : list) {
                    lichSuThayDoi = lichSuThayDoi + l.getThayDoi() + "\n";
                }
                lichSuThayDoi_tf.setText(lichSuThayDoi);
                                
                if(Integer.parseInt(idChuHo_tf.getText()) == 0) {
                    refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(0));
                    idChuHo_tf.setText("");
                    thanhVien_lbl.setText("Thêm chủ hộ");
                }
                else refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau()));
            }
        }
        // Thêm hộ
        else if (!hoKhauDialogPane.isVisible() && addHousehold_btn.isArmed()) {          
            hoKhauDialogPane.setVisible(true);
            idChuHo_tf.clear();
            tenChuHo_lbl.setText("Tên chủ hộ");
            themHoKhauDialog_btn.setVisible(true);
            suaHoKhauDialog_btn.setVisible(false);
            refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(0));    //Tim nhan khau chua co ho khau
            themThanhVien_btn.setVisible(false);
            idThanhVien_lbl.setVisible(false);
            idThanhVien_tf.setVisible(false);
            xoaThanhVien_btn.setVisible(false);
        }
    }

    @FXML
    void themThanhVien(ActionEvent event) {
        // Thêm thành viên vào hộ
        selectedHK = hoKhauTable.getSelectionModel().getSelectedItem();
        Integer idThanhVien = null;
        try {
            idThanhVien = Integer.parseInt(idThanhVien_tf.getText());
            ArrayList<NhanKhau> list = NhanKhauDAO.getInstance().selectAll();
            boolean checkThanhVien = false;
            for (NhanKhau nk : list) {
                if (idThanhVien == nk.getId() && nk.getHoKhauID() == 0) {   //Tìm thấy id nhân khẩu và nhân khẩu đó chưa thuộc hộ khẩu nào
                    checkThanhVien = true;
                    nk.setHoKhauID(selectedHK.getIdHoKhau());
                    NhanKhauDAO.getInstance().update(nk);
                    break;
                }
            }
            if (checkThanhVien) {
                refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau()));
            } else {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Không tìm thấy ID thành viên hoặc thành viên đã thuộc hộ khẩu khác!");
            }
        } catch (Exception e) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("ID thành viên phải là số!");
        }
    }

    @FXML
    void xoaThanhVien(ActionEvent event) {
        selectedHK = hoKhauTable.getSelectionModel().getSelectedItem();
        Integer idThanhVien = null;
        try {
            idThanhVien = Integer.parseInt(idThanhVien_tf.getText());
            ArrayList<NhanKhau> list = NhanKhauDAO.getInstance().selectAll();
            boolean checkThanhVien = false;
            for (NhanKhau nk : list) {
                if (idThanhVien == nk.getId() && nk.getHoKhauID() == selectedHK.getIdHoKhau()) {   //Tìm thấy id nhân khẩu và nhân khẩu đó thuộc hộ khẩu này
                    if(nk.getLaChuHo() == 1) {
                        idChuHo_tf.clear();
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Bạn vừa xóa chủ hộ, hãy thêm chủ hộ mới từ bảng dưới đây");
                    }
                    checkThanhVien = true;
                    nk.setHoKhauID(0);
                    nk.setLaChuHo(0);
                    nk.setQhChuHo("");
                    NhanKhauDAO.getInstance().update(nk);
                    break;
                }
            }
            if (checkThanhVien) {
                if(idChuHo_tf.getText().isEmpty()) {
                    refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(0)); //TH xoa chu ho thi table dua ra cac nhan khau chua co ho khau de them lam chu ho moi
                    thanhVien_lbl.setText("Thêm chủ hộ");
                } else {
                    refreshNhanKhauTable(NhanKhauDAO.getInstance().selectByHKId(selectedHK.getIdHoKhau()));
                    thanhVien_lbl.setText("Thành viên");
                }
            } else {
                AlertMessage alert = new AlertMessage();
                alert.errorMessage("Không tìm thấy ID thành viên hoặc thành viên không thuộc hộ khẩu này!");
            }
        } catch (Exception e) {
            AlertMessage alert = new AlertMessage();
            alert.errorMessage("ID thành viên phải là số!");
        }
        idThanhVien_tf.clear();
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
                    HoKhauDAO.getInstance().insert(new HoKhau(chuHo.getHoTen(), ID, 1, duong, phuong, quan, soNha, null));
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
            LichSuThayDoiDAO.getInstance().deleteByID(idHoKhau);
            if(HoKhauDAO.getInstance().deleteByID(idHoKhau)) {
                AlertMessage alert = new AlertMessage();
                alert.successMessage("Bạn đã xóa hộ khẩu thành công!");
                ArrayList<NhanKhau> list = NhanKhauDAO.getInstance().selectByHKId(idHoKhau);
                for(NhanKhau x : list) {
                    x.setHoKhauID(0);
                    x.setLaChuHo(0);
                    NhanKhauDAO.getInstance().update(x);
                }
            }
            else {
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
            String lichSuThayDoi = "Thay doi";
            int idChuHoCu = selectedHK.getIdChuHo();
            boolean checkIdChuHo = false;
            NhanKhau chuHo = new NhanKhau();
            if(selectedHK.getSoNha() != soNha) {
                lichSuThayDoi = lichSuThayDoi + " " + "So nha";
            }
            if(!selectedHK.getDuong().equals(duong)) {
                lichSuThayDoi = lichSuThayDoi + " " + "Duong";
            }
            if(!selectedHK.getPhuong().equals(phuong)) {
                lichSuThayDoi = lichSuThayDoi + " " + "Phuong";
            }
            if(!selectedHK.getQuan().equals(quan)) {
                lichSuThayDoi = lichSuThayDoi + " " + "Quan";
            }
            if(idChuHoCu == Integer.parseInt(idChuHoMoi)) {     //Neu khong thay doi chu ho
                    checkIdChuHo = true;
                    chuHo = NhanKhauDAO.getInstance().selectById(idChuHoCu);
                    chuHo.setLaChuHo(1);
                    chuHo.setQhChuHo("Chủ hộ");
                    chuHo.setHoKhauID(selectedHK.getIdHoKhau());
            } else {                                            //Neu thay doi chu ho
                for (NhanKhau nk : nhanKhauTableList) {
                    if(idChuHoMoi.equals(Integer.toString(nk.getId())) && (nk.getHoKhauID() == 0 || nk.getHoKhauID() == selectedHK.getIdHoKhau())) {
                        checkIdChuHo = true;
                        chuHo = nk;
                        chuHo.setLaChuHo(1);
                        chuHo.setQhChuHo("Chủ hộ");
                        chuHo.setHoKhauID(Integer.parseInt(maHo_tf.getText()));
                        break;
                    }
                }
                lichSuThayDoi = lichSuThayDoi + " " + "Chu ho";
            }
            if (checkIdChuHo) {
                try {
                    // Xóa chủ hộ cũ nếu có
                    try {
                        HoKhau selectedHK = hoKhauTable.getSelectionModel().getSelectedItem();
                        NhanKhau chuHoCu = NhanKhauDAO.getInstance().selectById(selectedHK.getIdChuHo());
                        chuHoCu.setLaChuHo(0);
                        chuHoCu.setQhChuHo("");
                        NhanKhauDAO.getInstance().update(chuHoCu);
                    } catch(Exception e) {
                        System.out.println("Khong co chu ho cu");
                    } finally {
                        // Cập nhật chủ hộ mới
                        NhanKhauDAO.getInstance().update(chuHo);
                        HoKhau hk = new HoKhau(selectedHK.getIdHoKhau(), chuHo.getHoTen(), chuHo.getId(), nhanKhauTableList.size(), duong, phuong, quan, soNha, "Đã sửa");
                        HoKhauDAO.getInstance().update(hk);
                        if(selectedHK.getSoNha() != soNha || !selectedHK.getDuong().equals(duong) || 
                          !selectedHK.getPhuong().equals(phuong) || !selectedHK.getQuan().equals(quan) || 
                          idChuHoCu != Integer.parseInt(idChuHoMoi)) {
                            lichSuThayDoi = lichSuThayDoi + " " + LocalDate.now().toString();
                            LichSuThayDoiDAO.getInstance().insert(new LichSuThayDoi(selectedHK.getIdHoKhau(), lichSuThayDoi));
                        }

                        dongHoKhauDialog(event);
                        AlertMessage alert = new AlertMessage();
                        alert.successMessage("Sửa thành công!");
                        refreshHoKhauTable(HoKhauDAO.getInstance().selectAll());
                    }
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
    }

    @FXML
    public void moTachKhauDialog(ActionEvent event) {
        if(!tachKhauDialogPane.isVisible()) tachKhauDialogPane.setVisible(true);
    }

    @FXML
    void saveTachKhau(ActionEvent event) {
        if(newIdChuHo_tf.getText().isBlank() || newSoNha_tf.getText().isBlank() || newDuong_tf.getText().isBlank() 
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
                if(checkIdChuHo) {
                    try {
                        HoKhau newHK = new HoKhau(newChuHo.getHoTen(), newIdChuHo, 1, duong, phuong, quan, newSoNha, null);
                        HoKhauDAO.getInstance().insert(newHK);
                        newChuHo.setHoKhauID(HoKhauDAO.getInstance().getLatestID());
                        NhanKhauDAO.getInstance().update(newChuHo);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

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

    @FXML
    public void moTamTruDialog(ActionEvent event) {
        if (!tamTruDialogPane.isVisible())
            tamTruDialogPane.setVisible(true);
    }

    @FXML
    void saveTamTru(ActionEvent event) {

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

        if (searchHo != null) {
            searchHo.setValue(hoKhauList.get(0));
            searchHo.setItems(hoKhauList);
        }

        if (hoKhauDialogPane != null)
            hoKhauDialogPane.setVisible(false);
        if (tachKhauDialogPane != null)
            tachKhauDialogPane.setVisible(false);
        if (tamTruDialogPane != null)
            tamTruDialogPane.setVisible(false);
    }

}
