package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class feeCollectionController implements Initializable {

    @FXML
    private Label collection;

    @FXML
    private ImageView gradient;

    @FXML
    private ImageView home;

    @FXML
    private ImageView home1;

    @FXML
    private Button logout;

    @FXML
    private Label stat;

    @FXML
    private Label type;

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
	
	public void home() throws Exception{
		Parent root = FXMLLoader.load(loginController.class.getResource("/view/functionOption.fxml"));
		Stage window = (Stage)home.getScene().getWindow();
		Scene s = new Scene(root,1400,800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
	}
	
	public void logoutView() throws Exception{
		Parent root = FXMLLoader.load(loginController.class.getResource("/view/login.fxml"));
		Stage window = (Stage)logout.getScene().getWindow();
		Scene s = new Scene(root,1400,800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
	}
	
	public void collectionView() throws Exception{
		Parent root = FXMLLoader.load(loginController.class.getResource("/view/feeCollection.fxml"));
		Stage window = (Stage)collection.getScene().getWindow();
		Scene s = new Scene(root,1400,800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
	}
	public void typeView() throws Exception{
		Parent root = FXMLLoader.load(loginController.class.getResource("/view/feeType.fxml"));
		Stage window = (Stage)type.getScene().getWindow();
		Scene s = new Scene(root,1400,800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
	}
	public void statView() throws Exception{
		Parent root = FXMLLoader.load(loginController.class.getResource("/view/feeStats.fxml"));
		Stage window = (Stage)stat.getScene().getWindow();
		Scene s = new Scene(root,1400,800);
		s.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		window.setScene(s);
	}
}
