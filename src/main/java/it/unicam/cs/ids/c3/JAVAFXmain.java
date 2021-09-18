package it.unicam.cs.ids.c3;

import it.unicam.cs.ids.c3.model.Vista.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class JAVAFXmain extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("/login.fxml"));
        primaryStage.setTitle("Centro Commerciale In Centro C3");
        System.out.println(getClass().getResource("/login.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        primaryStage.setScene(new Scene(root,500,500));
        primaryStage.getIcons().add(new Image((getClass().getResource("/image.png")).toString()));
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}
