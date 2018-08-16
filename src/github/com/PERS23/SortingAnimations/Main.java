package github.com.PERS23.SortingAnimations;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("values/strings", Locale.getDefault());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainUI.fxml"), resourceBundle);
        Parent root = (Parent) loader.load();

        primaryStage.setTitle("Sorting Animation");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
