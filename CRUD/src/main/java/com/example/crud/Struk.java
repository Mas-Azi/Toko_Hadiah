package com.example.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;

public class Struk {
    @FXML
    private Button ButtonExit;

    //Sebuah Method yang mana jika User menekan tombol Exit maka program akan mengarahkan user ke menu utama
    @FXML
    void ExitButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage window = (Stage) ButtonExit.getScene().getWindow();
        window.setScene(new Scene(root));

    }
}
