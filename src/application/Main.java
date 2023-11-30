package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		// try {
		// 	Parent root = FXMLLoader.load(Main.class.getResource("/view/login.fxml"));
		// 	Scene scene = new Scene(root,1400,800);
		// 	scene.getStylesheets().add(getClass().getResource("/view/style.css").toExternalForm());
		// 	Image icon = new Image("logo.png");
		// 	primaryStage.setResizable(false);
		// 	primaryStage.getIcons().add(icon);
		// 	primaryStage.setScene(scene);
		// 	primaryStage.show();
		// } catch(Exception e) {
		// 	e.printStackTrace();
		// }

		TextField textField = new TextField();
        Button button = new Button("Click me");

        // Set an event handler for the button
        button.setOnAction(event -> {
            System.out.println("Button clicked");
            // Add your custom logic here
        });

        // Set an event handler for the Enter key press event in the text field
        textField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                button.fire(); // Trigger the button's action event
            }
        });

        VBox root = new VBox(textField, button);
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Set the focus on the text field so that it can receive keyboard events
        textField.requestFocus();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
