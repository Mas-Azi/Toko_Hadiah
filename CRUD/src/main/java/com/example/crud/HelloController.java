package com.example.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;

//Sebuah Class Controoler yang mana berisikan 4 buah menu yang nantinya progam akan menampilkan pertama kali saat
// program dijalankan

public class HelloController {

    @FXML
    private AnchorPane HalamanMenu;
    @FXML
    private Button LoginButton, HadiahButton, TipsButton;
    @FXML
    public Button ExitProgramButton;
    @FXML
    private Stage stage;

    //Sebuah Method yang mana jika user atau admin menekan tombol pilih hadiah maka program akan mengarahkannya ke menu
    //Transaksi user
    public void PilihHadiah(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("TransaksiUser.fxml"));
        Stage window = (Stage) HadiahButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    //Sebuah Method yang mana jika Admin menekan tombol login admin maka program akan mengarahkan admin ke menu halaman Login
    public void LoginAdmin(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HalamanLogin.fxml"));
        Stage window = (Stage) LoginButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    //Sebuah Method yang mana jika admin atau user menekan tombol Tips Penggunaan maka program akan mengarahkan ke menu
    // Halaman Petunjuk
    public void TipsPenggunaan(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HalamanPetunjuk.fxml"));
        Stage window = (Stage) TipsButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    //Sebuah Method yang mana jika user atau admin menekan tombol Exit, User atau admin akan menjumpai dialog alret
    //apakah yakin untuk keluar?, jika iya maka program akan berhenti dan jka tidak program akan kembali ke halaman menu awal
    public void ExitButton(ActionEvent actionEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Exit");
        alert.setHeaderText("");
        alert.setContentText("Apakah anda yakin ingin keluar?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) HalamanMenu.getScene().getWindow();
            System.out.println("Anda berhasil keluar");
            stage.close();
        }
    }
}
