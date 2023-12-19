package controllers;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import application.authentication.AlertMessage;
import application.dao.GiaoDichDAO;
import application.model.GiaoDich;
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
import javafx.stage.Stage;
import javafx.util.Duration;

public class feeStatsController implements Initializable {

	@FXML
	private DatePicker denNgay_date;

	@FXML
	private Button find_btn;

	@FXML
	private ImageView gradient;

	@FXML
	private ImageView home;

	@FXML
	private Button logout;

	@FXML
	private TextField search_tf;

	@FXML
	private Label soHoDaNop_lbl;

	@FXML
	private TextField soHoDaNop_tf;

	@FXML
	private Label stat;

	@FXML
	private Label tongThu_lbl;

	@FXML
	private TextField tongThu_tf;

	@FXML
	private DatePicker tuNgay_date;

	@FXML
	private Label type;

	@FXML
	private ChoiceBox<String> loaiPhi_cb;

	@FXML
	private TableView<GiaoDich> timMaHo_table;

	@FXML
	private TableColumn<GiaoDich, String> khoanDaNop_col;

	@FXML
	private TableColumn<GiaoDich, Integer> maHo_col;

	@FXML
	private TableColumn<GiaoDich, Integer> soTien_col;

	@FXML
	private TableColumn<GiaoDich, Date> thoiGian_col;

	ObservableList<GiaoDich> timMaHoTableList;
	ObservableList<String> loaiPhiList = FXCollections.observableArrayList("Tất cả", "Bắt buộc", "Bắt buộc theo hộ",
			"Bắt buộc theo người", "Không bắt buộc");

	@FXML
	void thongKe(ActionEvent event) {
		LocalDate tuNgay = tuNgay_date.getValue();
		LocalDate denNgay = denNgay_date.getValue();
		ArrayList<GiaoDich> list = new ArrayList<>();

		int soHoDaNop = 0;
		if (tuNgay == null || denNgay == null) {
			tuNgay = LocalDate.now().minusDays(1);
			denNgay = LocalDate.now().plusDays(1);
			tuNgay_date.setValue(tuNgay);
			denNgay_date.setValue(denNgay);
		}
		if (loaiPhi_cb.getValue().equals("Bắt buộc")) {
			list = GiaoDichDAO.getInstance().selectByPeriodAndType(Date.valueOf(tuNgay),
					Date.valueOf(denNgay), "Bắt buộc%");
			soHoDaNop = GiaoDichDAO.getInstance().countSoHoDaNop(Date.valueOf(tuNgay),
					Date.valueOf(denNgay), "Bắt buộc%");
			refreshTimMaHoTable(list);
		} else if (loaiPhi_cb.getValue().equals("Tất cả")) {
			list = GiaoDichDAO.getInstance().selectByPeriodAndType(Date.valueOf(tuNgay),
					Date.valueOf(denNgay), "Tất cả");
			soHoDaNop = GiaoDichDAO.getInstance().countSoHoDaNop(Date.valueOf(tuNgay),
					Date.valueOf(denNgay), "Tất cả");
			refreshTimMaHoTable(list);
		} else {
			list = GiaoDichDAO.getInstance().selectByPeriodAndType(Date.valueOf(tuNgay),
					Date.valueOf(denNgay), loaiPhi_cb.getValue());
			soHoDaNop = GiaoDichDAO.getInstance().countSoHoDaNop(Date.valueOf(tuNgay),
					Date.valueOf(denNgay), loaiPhi_cb.getValue());
			refreshTimMaHoTable(list);
		}
		long tongThu = 0;
		for (GiaoDich gd : list) {
			tongThu += (long) gd.getSoTien();
		}
		tongThu_tf.setText(Long.toString(tongThu));
		soHoDaNop_tf.setText(Integer.toString(soHoDaNop));
	}

	@FXML
	void timKhoanPhi(ActionEvent event) {
		tongThu_lbl.setVisible(true);
		tongThu_tf.setVisible(true);
		
		if (search_tf.getText().isBlank()) {
			AlertMessage alert = new AlertMessage();
			alert.errorMessage("Bạn chưa nhập thông tin tìm kiếm!");
			refreshTimMaHoTable(GiaoDichDAO.getInstance().selectAll());
		} else {
			try {
				int maHo = Integer.parseInt(search_tf.getText());
				ArrayList<GiaoDich> list = GiaoDichDAO.getInstance().selectByHKID(maHo);
				if (!list.isEmpty()) {
					refreshTimMaHoTable(list);
				long tongThu = 0;
				for (GiaoDich gd : list) {
					tongThu += (long) gd.getSoTien();
				}
				tongThu_tf.setText(Long.toString(tongThu));
				} else {
					AlertMessage alert = new AlertMessage();
					alert.errorMessage("Không tìm thấy mã hộ khẩu hoặc hộ khẩu này chưa thực hiện đóng phí nào!");
				}
			} catch (Exception e) {
				AlertMessage alert = new AlertMessage();
				alert.errorMessage("Mã hộ khẩu phải là số!");
			}
		}
	}

	@FXML
	void xoaGiaoDich(ActionEvent event) {
		GiaoDich selectedGD = timMaHo_table.getSelectionModel().getSelectedItem();
		if (GiaoDichDAO.getInstance().deleteByID(selectedGD.getMaGiaoDich())) {
			AlertMessage alert = new AlertMessage();
			alert.successMessage("Xóa giao dịch thành công!");
			refreshTimMaHoTable(GiaoDichDAO.getInstance().selectAll());
		} else {
			AlertMessage alert = new AlertMessage();
			alert.errorMessage("Xóa giao dịch không thành công!");
		}
	}

	void refreshTimMaHoTable(ArrayList<GiaoDich> list) {
		timMaHoTableList = FXCollections.observableArrayList(list);
		maHo_col.setCellValueFactory(new PropertyValueFactory<GiaoDich, Integer>("maHoKhau"));
		khoanDaNop_col.setCellValueFactory(new PropertyValueFactory<>("tenKhoanPhi"));
		soTien_col.setCellValueFactory(new PropertyValueFactory<GiaoDich, Integer>("soTien"));
		thoiGian_col.setCellValueFactory(new PropertyValueFactory<GiaoDich, Date>("thoiGian"));
		timMaHo_table.setItems(timMaHoTableList);
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

		ArrayList<GiaoDich> list = GiaoDichDAO.getInstance().selectAll();
		refreshTimMaHoTable(list);
		int soHoDaNop = GiaoDichDAO.getInstance().countSoHoDaNop(Date.valueOf(LocalDate.of(0, 1, 1)),
				Date.valueOf(LocalDate.of(9999, 12, 31)), "Tất cả");
		int tongThu = 0;
		for (GiaoDich gd : list) {
			tongThu += gd.getSoTien();
		}
		tongThu_tf.setText(Integer.toString(tongThu));
		soHoDaNop_tf.setText(Integer.toString(soHoDaNop));
		if (loaiPhi_cb != null) {
			loaiPhi_cb.setValue(loaiPhiList.get(0));
			loaiPhi_cb.setItems(loaiPhiList);
		}
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
}
