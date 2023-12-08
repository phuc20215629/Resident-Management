package controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.dao.NhanKhauDAO;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class householdStatsController implements Initializable {

    @FXML
    private DatePicker denNgay_date;

    @FXML
    private ChoiceBox<String> doTuoi_cb;

    @FXML
    private ChoiceBox<String> gioiTinh_cb;

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
    
    @FXML
    private VBox menu;

    ObservableList<NhanKhau> statsTableList;
    ObservableList<String> gioiTinhList = FXCollections.observableArrayList("Tất cả","Nam", "Nữ");
	ObservableList<String> doTuoiList = FXCollections.observableArrayList("Tất cả","Mầm non", "Cấp 1", "Cấp 2", "Cấp 3","Lao động", "Nghỉ hưu");
    ObservableList<String> trangThaiList = FXCollections.observableArrayList("Tất cả", "Tạm vắng", "Tạm trú");

    @FXML
    public void hoKhauView(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/household.fxml"));
		Stage window = (Stage)houseHold.getScene().getWindow();
		Scene s = new Scene(root,1400,800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
    }

    @FXML
    public void nhanKhauView(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/householdMember.fxml"));
		Stage window = (Stage)nhanKhau.getScene().getWindow();
		Scene s = new Scene(root,1400,800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
    }

    @FXML
    public void statView(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/householdStats.fxml"));
		Stage window = (Stage)stat.getScene().getWindow();
		Scene s = new Scene(root,1400,800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
    }

    @FXML
    public void home(MouseEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/functionOption.fxml"));
		Stage window = (Stage)home.getScene().getWindow();
		Scene s = new Scene(root,1400,800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
    }

    @FXML
    public void logoutView(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(loginController.class.getResource("/view/login.fxml"));
		Stage window = (Stage)logout.getScene().getWindow();
		Scene s = new Scene(root,1400,800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
    }

    @FXML
    void thongKe(ActionEvent event) {
        String gioiTinh = gioiTinh_cb.getValue();
        String doTuoi = doTuoi_cb.getValue();
        String trangThai = trangThai_cb.getValue();
        Date tuNgay_sqlDate = Date.valueOf(tuNgay_date.getValue());
        Date denNgay_sqlDate = Date.valueOf(denNgay_date.getValue());
        int soLuong;
        try {
            soLuong = Integer.parseInt(soLuong_tf.getText());
        } catch (Exception e) {
            soLuong = 1000;
        }

        if(doTuoi.equals("Tất cả")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(soLuong, gioiTinh, -1, 200, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        }
        else if (doTuoi.equals("Mầm non")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(soLuong, gioiTinh, 0, 5, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        }
        else if (doTuoi.equals("Cấp 1")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(soLuong, gioiTinh, 6, 10, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        }
        else if (doTuoi.equals("Cấp 2")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(soLuong, gioiTinh, 11, 14, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        }
        else if (doTuoi.equals("Cấp 3")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(soLuong, gioiTinh, 15, 17, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        }
        else if (doTuoi.equals("Lao động")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(soLuong, gioiTinh, 18, 60, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        }
        else if (doTuoi.equals("Nghỉ hưu")) {
            statsTableList = FXCollections.observableArrayList(NhanKhauDAO.getInstance().thongKe(soLuong, gioiTinh, 61, 200, trangThai, tuNgay_sqlDate, denNgay_sqlDate));
        }
        idCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("id"));
        idHoKhauCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("hoKhauID"));
        hoTenCol.setCellValueFactory(new PropertyValueFactory<NhanKhau, ArrayList<String>>("hoTen"));
        statsTable.setItems(statsTableList);
        soLuong_tf.clear();
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
		
		if(gioiTinh_cb != null) {
			gioiTinh_cb.setValue(gioiTinhList.get(0));
			gioiTinh_cb.setItems(gioiTinhList);
		}
		if(doTuoi_cb != null) {
			doTuoi_cb.setValue(doTuoiList.get(0));
			doTuoi_cb.setItems(doTuoiList);
		}
        if(trangThai_cb != null) {
			trangThai_cb.setValue(trangThaiList.get(0));
			trangThai_cb.setItems(trangThaiList);
		}
        if(tuNgay_date != null) {
            tuNgay_date.setValue(LocalDate.of(1900, 1, 1));
        }
        if(denNgay_date != null) {
            denNgay_date.setValue(LocalDate.now());
        }
    }

}
