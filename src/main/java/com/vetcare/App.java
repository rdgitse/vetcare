
package com.vetcare;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        Label message = new Label(
            "VetCare Panamá - Sistema de Gestión Veterinaria"
        );

        StackPane root = new StackPane(message);
        Scene scene = new Scene(root, 700, 400);

        stage.setTitle("VetCare Panamá");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
