package com.example.portfoliomanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        VBox vbx = new VBox(); //Anything you put in a VBox is going to be added in a vertical way
        //elements are displayed in vertical order.
        vbx.setSpacing(20);//vertical spacing, adding spacing in a vertical way
        HBox hbx = new HBox(); //displays elements in a horizontal way
        hbx.setSpacing(20);//horizontal spacing, adding spacing in a horizontal way
        hbx.setAlignment(Pos.CENTER);
        Button btn1 = new Button("Button 1");
        Button btn2 = new Button("Button 2");
        Label lab = new Label("Label 1");
        TextField T1 = new TextField();
        //add all these components to our VBox
        hbx.getChildren().addAll(btn2, btn1);
        vbx.getChildren().addAll(lab, T1);
        BorderPane root = new BorderPane();
        root.setCenter(hbx);
        root.setLeft(vbx);
        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}