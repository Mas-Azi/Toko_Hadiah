package com.example.crud;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;

public class HalamanLoginController {

    @FXML
    private Button Buttonlogin, ButtonExit;

    @FXML
    private AnchorPane HalamanLogin;

    @FXML
    private PasswordField PasswordAdmin;

    @FXML
    private TextField UsernameAdmin;

    //Memanggil Clas Koneksi dengan nama variaber koneksi yang mana nantinya akan digunakan untuk mengambil data di database
    Koneksi koneksi = new Koneksi();

    // Sebuah Method yang mana ketika user menekan tombol exit maka program akan mengarahkan user ke menu utama(hello-view)
    @FXML
    void ButtonExitlogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage window = (Stage) ButtonExit.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    // Sebuah Method yang berfungsi jika admin menekan tombol login pada mhalaman logi maka program akan mengarahkan admin
    // ke tampilan menu CRUD Admin
    // Jika Username dan Password tidak sama dengan database login maka program akan menampilkan pop up usernam dan password salah
    // Jika Username dan password Kosong maka program akan menampilkan pop up untuk menyuruh admin memasukan username dan Password
    // Jika username dan password sama dengan database login maka admin akan di arahkan ke menu HalamanCrudAdmin
    @FXML
    void LoginButton(ActionEvent event) throws IOException {
        String usnm = UsernameAdmin.getText();
        String pass = PasswordAdmin.getText();
        //Object alert 1 dan alert 2 untuk menampilkan alret dialog
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);

        if (usnm.isBlank() || pass.isBlank()) {
            alert1.setTitle("Verifikasi");
            alert1.setContentText("Harap Masukan Username dan Password Anda");
            alert1.showAndWait();
            return;
        }

        try {
            //Mengambil data username dan password dari database login_admin
            String query = "SELECT * FROM login_admin WHERE Username='" + usnm + "'AND Password='" + pass+"'";
            ResultSet hasil = koneksi.getData(query);

            if(hasil.next()){
                String AdminHadiah = hasil.getString("Username");
                String PassHadiah = hasil.getString("Password");

                System.out.println(AdminHadiah);
                System.out.println(PassHadiah);
                if (usnm.equals(AdminHadiah) && pass.equals(PassHadiah)){
                    alert.setContentText("Anda Berhasil Login");
                    alert.show();
                    Parent root = FXMLLoader.load(getClass().getResource("HalamanCrudAdmin.fxml"));
                    Stage window = (Stage) Buttonlogin.getScene().getWindow();
                    window.setScene(new Scene(root));
                }
            } else {
                System.out.println("cek");
                UsernameAdmin.setText("");
                PasswordAdmin.setText("");
                alert.setTitle("Username and Password Salah");
                alert.setContentText("Username and Password Salah");
                alert.showAndWait();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
