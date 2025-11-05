package com.example.portfoliomanagement;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.text.TabableView;
import java.util.ArrayList;

public class PortfolioManagement {

    private BorderPane root;
    private Label total, pl;
    private TextField symbol, price, shares;
    private Button buy, sell, refresh;
    private ArrayList<Position> portfolio;
    public PortfolioManagement(){
        portfolio = new ArrayList<>();
        root = new BorderPane();

        //The sahep in notes:
        //we need two labels
        total = new Label("Total Value: 0.0-");
        pl = new Label("P/L: 0.0");
        //Need three textfields symbol, number of shares, price
        symbol = new TextField();
        symbol.setPromptText("Symbol");
        symbol.setTooltip(new Tooltip("Enter Symbol"));
        price = new TextField();
        price.setPromptText("Price");
        price.setTooltip(new Tooltip("Enter Price"));
        shares = new TextField();
        shares.setPromptText("Shares");
        shares.setTooltip(new Tooltip("Enter number Shares"));
        //two buttons buy and sell
        buy = new Button("Buy");
        buy.setOnAction(e->{
            this.addPosition(symbol.getText(), shares.getText(), price.getText());
        });
        sell = new Button("Sell");
        refresh = new Button("Refresh");
        refresh.setTooltip(new Tooltip("Refresh"));
        refresh.setPadding(new Insets(0, 5, 0, 0));
        refresh.setTextFill(Color.WHITE);
        refresh.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(20), new Insets(0,0,0,0))));
       // refresh.getBackground().getImages().add(new BackgroundImage(new Image("C:\\Users\\momo\\workshop\\refresh.png"), BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(20, 40, true, true, true, true) ));
        refresh.setPrefWidth(100);
        refresh.setPrefHeight(20);
        refresh.setOnAction(e -> {
            refreshView();
        });
        HBox hbxLabel = new HBox();
        hbxLabel.setAlignment(Pos.BASELINE_LEFT);
        hbxLabel.setSpacing(10);
        hbxLabel.getChildren().addAll(total, pl);
        HBox hbxRefresh = new HBox();
        hbxRefresh.setSpacing(200);
        hbxRefresh.getChildren().addAll(hbxLabel, refresh);
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
        root.setTop(hbxRefresh);
        //create few Positions.
        Position p = new Position("AAPL", 200, 230);
        portfolio.add(p);
        portfolio.add(new Position("IBM", 300, 450));
        portfolio.add(new Position("MSFT", 400, 550));
    }

    private void addPosition(String s, String sh, String pr){
        double prc = Double.parseDouble(pr);
        int shr = Integer.parseInt(sh);
        for(Position p: portfolio){
            if(p.getSymbol().equals(s)){
                double newPrice = (p.getPrice()*p.getShares() + shr*prc)/(p.getShares() + shr);
                p.setShares(p.getShares() + shr);
                p.setPrice(newPrice);
                refreshView();
                return;
            }
        }
        Position p = new Position(s,shr,prc);
        portfolio.add(p);
        refreshView();
    }

    private void refreshView(){
        TableView<Position> positionView = new TableView<>();
        TableColumn<Position, String> nameCol = new TableColumn<>("Symbol");
        nameCol.setCellValueFactory(new PropertyValueFactory<Position, String>("symbol"));
        //nameCol.setCellFactory(TextFieldTableCell.forTableColumn());

        TableColumn<Position, Integer> sharesCol = new TableColumn<>("Shares");
        sharesCol.setCellValueFactory(new PropertyValueFactory<Position, Integer>("shares"));
        sharesCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        TableColumn<Position, Double> priceCol = new TableColumn<>("Price");
        priceCol.setCellValueFactory(new PropertyValueFactory<Position, Double>("price")); //same name as variable in Position class
        priceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<Position, Double> costCol = new TableColumn<>("Cost");
        costCol.setCellValueFactory(new PropertyValueFactory<Position, Double>("totalPrice")); //same name as variable in Position class
        costCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<Position, Double> currentPriceCol = new TableColumn<>("Current Price");
        currentPriceCol.setCellValueFactory(new PropertyValueFactory<Position, Double>("currentPrice")); //same name as variable in Position class
        currentPriceCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        TableColumn<Position, Double> plCol = new TableColumn<>("PL");
        plCol.setCellValueFactory(new PropertyValueFactory<Position, Double>("profitLoss")); //same name as variable in Position class
        plCol.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

        positionView.getColumns().addAll(nameCol, sharesCol, priceCol, costCol, currentPriceCol, plCol);
        positionView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        double totalValue = 0;
        double totalCost = 0;
        for(Position p : portfolio){
            positionView.getItems().add(p);
            totalCost += p.getTotalPrice();
            totalValue += p.getShares()*p.getCurrentPrice();
        }
        root.setCenter(positionView);
        total.setText("Total Value: " + Double.toString(totalValue));
        pl.setText("P/L: " + Double.toString(totalValue-totalCost));
        pl.setFont(new Font(18));
        total.setFont(new Font(18));
        if(totalValue-totalCost >= 0){
            pl.setTextFill(Color.GREEN);
        }else{
            pl.setTextFill(Color.RED);
        }


    }
    public BorderPane getRoot() {
        return root;
    }
}
