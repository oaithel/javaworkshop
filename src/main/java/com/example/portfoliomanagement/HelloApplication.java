package com.example.portfoliomanagement;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //The sahep in notes:
        //we need two labels
        Label total = new Label("Total Value: 0.0-");
        Label pl = new Label("P/L: 0.0");
        //Need three textfields symbol, number of shares, price
        TextField symbol = new TextField();
        symbol.setPromptText("Symbol");
        symbol.setTooltip(new Tooltip("Enter Symbol"));
        TextField price = new TextField();
        price.setPromptText("Price");
        price.setTooltip(new Tooltip("Enter Price"));
        TextField shares = new TextField();
        shares.setPromptText("Shares");
        shares.setTooltip(new Tooltip("Enter number Shares"));
        //two buttons buy and sell
        Button buy = new Button("Buy");
        Button sell = new Button("Sell");
        HBox hbxLabel = new HBox();
        hbxLabel.setAlignment(Pos.BASELINE_LEFT);
        hbxLabel.setSpacing(10);
        hbxLabel.getChildren().addAll(total, pl);
        BorderPane root = new BorderPane();
        //HBox for the text fields
        HBox hbxText = new HBox();
        hbxText.setAlignment(Pos.BASELINE_RIGHT);
        hbxText.setSpacing(10);
        hbxText.getChildren().addAll(symbol, shares, price);
        //HBox for the bottons
        ButtonBar btnBar = new ButtonBar();
        btnBar.getButtons().addAll(buy, sell);
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setAlignment(Pos.BASELINE_LEFT);
        vbox.getChildren().addAll(hbxText, btnBar);

        root.setBottom(vbox);
        root.setTop(hbxLabel);
        Scene scene = new Scene(root, 500, 300);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}