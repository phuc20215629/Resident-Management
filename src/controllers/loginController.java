package controllers;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

import application.authentication.AlertMessage;
import application.authentication.LoginAuthentication;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

public class loginController implements Initializable {

	@FXML
	public ImageView gradient;
	public Button nextBtn, okButton, loginButton, logout;
	public Label forget;
	public ImageView household, fee;
	public TextField username_tf;
	public PasswordField password_tf;
	public TextField newPasswordCf_tf;
	public TextField newPassword_tf;
	public TextField infoCf_tf;

	private static String username, password;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		RotateTransition rotate = new RotateTransition();
		rotate.setNode(gradient);
		rotate.setDuration(Duration.millis(10000));
		rotate.setCycleCount(RotateTransition.INDEFINITE);
		rotate.setInterpolator(Interpolator.LINEAR);
		rotate.setByAngle(360);
		rotate.play();
	}

	// --------------------------------------------------------------------------
	@FXML
	public void loginNext() throws Exception {
		username = username_tf.getText();
		if (username == "") {
			AlertMessage alert = new AlertMessage();
			alert.errorMessage("Bạn chưa nhập tên đăng nhập!");
		} else {
			LoginAuthentication newAuthentication = new LoginAuthentication(username);
			if (newAuthentication.checkUsername()) {
				Parent root = FXMLLoader.load(loginController.class.getResource("/view/loginPassword.fxml"));
				Stage window = (Stage) nextBtn.getScene().getWindow();
				Scene s = new Scene(root, 1400, 800);
				s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
				window.setScene(s);
			} else {
				AlertMessage alert = new AlertMessage();
				alert.errorMessage("Sai tên đăng nhập!");
			}

		}
	}

	public void forgetPass() throws Exception {
		Parent root = FXMLLoader.load(loginController.class.getResource("/view/loginResetPassword.fxml"));
		Stage window = (Stage) forget.getScene().getWindow();
		Scene s = new Scene(root, 1400, 800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
	}

	public void resetPass() throws Exception {
		String infoCf = infoCf_tf.getText();
		String newPassword = newPassword_tf.getText();
		String newPasswordCf = newPasswordCf_tf.getText();
		if (infoCf == "" || newPassword == "" || newPasswordCf == "") {
			AlertMessage alert = new AlertMessage();
			alert.errorMessage("Bạn chưa điền hết thông tin!");
		} else {
			if (newPassword.equals(newPasswordCf)) {
				LoginAuthentication userCf = new LoginAuthentication(null, newPasswordCf, infoCf);
				if (userCf.confirmAuthentication()) {
					AlertMessage alert = new AlertMessage();
					alert.successMessage("Thay đổi mật khẩu thành công!");
					Parent root = FXMLLoader.load(loginController.class.getResource("/view/login.fxml"));
					Stage window = (Stage) okButton.getScene().getWindow();
					Scene s = new Scene(root, 1400, 800);
					s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
					window.setScene(s);
				} else {
					AlertMessage alert = new AlertMessage();
					alert.errorMessage("Sai tên đăng nhập!");
				}
			} else {
				AlertMessage alert = new AlertMessage();
				alert.errorMessage("Mật khẩu mới không khớp!");
			}
		}
	}

	public void login() throws Exception {
		password = password_tf.getText();
		if (password == "") {
			AlertMessage alert = new AlertMessage();
			alert.errorMessage("Bạn chưa nhập mật khẩu!");
		} else {
			LoginAuthentication user = new LoginAuthentication(username, password, null);
			if (user.checkPassword()) {
				Parent root = FXMLLoader.load(loginController.class.getResource("/view/functionOption.fxml"));
				Stage window = (Stage) loginButton.getScene().getWindow();
				Scene s = new Scene(root, 1400, 800);
				s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
				window.setScene(s);
			} else {
				AlertMessage alert = new AlertMessage();
				alert.errorMessage("Sai mât khẩu");
				Parent root = FXMLLoader.load(loginController.class.getResource("/view/login.fxml"));
				Stage window = (Stage) loginButton.getScene().getWindow();
				Scene s = new Scene(root, 1400, 800);
				s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
				window.setScene(s);
			}
		}
	}

	public void nhanKhauHoKhau(MouseEvent event) throws Exception {
		Parent root = FXMLLoader.load(loginController.class.getResource("/view/household.fxml"));
		Stage window = (Stage) household.getScene().getWindow();
		Scene s = new Scene(root, 1400, 800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
	}

	public void thuPhiDongGop(MouseEvent event) throws Exception {
		Parent root = FXMLLoader.load(loginController.class.getResource("/view/feeType.fxml"));
		Stage window = (Stage) fee.getScene().getWindow();
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

}