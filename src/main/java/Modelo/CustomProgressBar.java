package Modelo;

import com.jfoenix.controls.JFXProgressBar;
import javafx.geometry.HPos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

public class CustomProgressBar extends GridPane {
    private JFXProgressBar barra = new JFXProgressBar();
    private Label cantidad = new Label();


    public CustomProgressBar(int dentro, int capacidad) {
        super();

        this.barra.setProgress(Double.parseDouble(String.valueOf(dentro)) / Double.parseDouble(String.valueOf(capacidad)));
        if (barra.getProgress() <= 0.4){
            this.barra.getStyleClass().add("green-bar");
        }else if (barra.getProgress() > 0.4 && barra.getProgress() < 0.7){
            this.barra.getStyleClass().add("orange-bar");
        }else if (barra.getProgress() >= 0.7){
            this.barra.getStyleClass().add("red-bar");
        }

        this.barra.setPrefHeight(20.0);

        this.cantidad.setText(dentro + "/" + capacidad);
        this.cantidad.setTranslateX(this.getWidth() / 2);

        GridPane.setHalignment(barra, HPos.CENTER);
        GridPane.setHalignment(cantidad, HPos.CENTER);
        GridPane.setHgrow(barra, Priority.ALWAYS);

        this.getChildren().addAll(barra, cantidad);
    }

    public JFXProgressBar getBarra() {
        return barra;
    }

    public void setBarra(JFXProgressBar barra) {
        this.barra = barra;
    }

    public Label getCantidad() {
        return cantidad;
    }

    public void setCantidad(Label cantidad) {
        this.cantidad = cantidad;
    }
}
