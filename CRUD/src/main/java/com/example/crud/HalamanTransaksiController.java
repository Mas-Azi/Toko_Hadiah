package com.example.crud;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HalamanTransaksiController implements Initializable{
    @FXML
    private Button ButtonBack;
    @FXML
    private Button ButtonBeli;
    @FXML
    private TextField InputUang, NamaUser;
    @FXML
    private Label LabelHarga, LabelItem, LabelEror;
    @FXML
    private TableColumn<TokoHadiah, DoubleProperty> hargabarang;
    @FXML
    private TableColumn<TokoHadiah, StringProperty> kodebarang;
    @FXML
    private TableColumn<TokoHadiah, StringProperty> namabarang;
    @FXML
    private TableView<TokoHadiah> tabelhadiahuser;
    Koneksi koneksi = new Koneksi();

    //Sebuah Method untuk menginisiasi dengan parameter URL,dan resourceBundle
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        kodebarang.setCellValueFactory(new PropertyValueFactory<>("Kode_Barang"));
        namabarang.setCellValueFactory(new PropertyValueFactory<>("Nama_Barang"));
        hargabarang.setCellValueFactory(new PropertyValueFactory<>("Harga"));
    }
    //Sebuat Method yang berfungsi untuk menampilkan data yang tersimpan pada database dengan variabel yang sesuai dengan database
    public void TampilanTable(String Kodehadiah){
        try{
            String query = "SELECT * FROM data_barang WHERE Kode_Barang='" +Kodehadiah +"'";
            ResultSet hasil = koneksi.getData(query);
            ObservableList<TokoHadiah> model = FXCollections.observableArrayList();
            tabelhadiahuser.setItems(model);
            while (hasil.next()){
                String Kode = hasil.getString(1);
                String Nama = hasil.getString(3);
                double Harga = hasil.getDouble(5);
                model.add(new TokoHadiah(Kode, Nama, Harga));
            }
        }catch (Exception e){
            System.out.println(e);
        }
        tabelhadiahuser.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> showModel(newValue));

    }
    //Sebuah Method yang berfungsi untuk membuat model dengan implementasi dari class Toko Hadiah
    //Selain itu jika model tidak sama dengan null atau kosong maka akan mengambil data menggunakan get
    private void showModel(TokoHadiah model){
        if(model != null){
            model.getKode_Barang();
            model.getNama_Barang();
            model.getHarga();
        }
    }

    //Sebuah Fungsi jika user menekan tombol button maka user akan diarahkan ke menu awal
    @FXML
    void BackButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        Stage window = (Stage) ButtonBack.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    //sebuah Fungsi jika user menekan tombol beli maka program akan menampilkan pop up apakah anak yakin ingin membeli
    //jika tidak akan kemabil ke menu transaksi dan jika iya maka akan di tampilkan ke menu struk
    //selain itu kita juga membuat kondisi yang mana jika jumlah uang yang dimasukan tidak sama dengan harga maka akan menampilkan struk 1
    //jika jumlah uang yang dimasukan lebih besar dari harga maka akan menampilka struk 2
    //jika uang yang dimasukan kurang dari harga maka akan ada pop up uang anda kurang
    @FXML
    void BeliButton(ActionEvent event) {
        String uang = InputUang.getText();
        String labelharga = LabelHarga.getText();
        Alert alert1 =new Alert(Alert.AlertType.CONFIRMATION);
        Alert alert2 =new Alert(Alert.AlertType.INFORMATION);
        Alert alert3 =new Alert(Alert.AlertType.CONFIRMATION);
        Alert alert4 =new Alert(Alert.AlertType.INFORMATION);
        try{
            if(uang.equals(labelharga)){
                alert1.setTitle("Konfirmasi");
                alert1.setContentText("Apakah Anda Yakin ingin Membeli?");
                if(alert1.showAndWait().get() == ButtonType.OK) {
                    alert2.setAlertType(Alert.AlertType.INFORMATION);
                    alert2.setContentText("Pembelian Anda Berhasil");
                    Parent root = FXMLLoader.load(getClass().getResource("Struk1.fxml"));
                    Stage window = (Stage) ButtonBeli.getScene().getWindow();
                    window.setScene(new Scene(root));
                }
            } else if (Integer.parseInt(uang) > Integer.parseInt(labelharga) ) {
                Parent root = FXMLLoader.load(getClass().getResource("Struk2.fxml"));
                Stage window = (Stage) ButtonBeli.getScene().getWindow();
                window.setScene(new Scene(root));
            } else if (Integer.parseInt(uang) < Integer.parseInt(labelharga)){
                alert3.setContentText("Uang Anda Kurang, Silahkan Input Kembali");
                if(alert3.showAndWait().get() == ButtonType.YES) {
                    alert4.setAlertType(Alert.AlertType.INFORMATION);
                    alert4.setContentText("");
                    Parent root = FXMLLoader.load(getClass().getResource("TransaksiUser.fxml"));
                    Stage window = (Stage) ButtonBeli.getScene().getWindow();
                    window.setScene(new Scene(root));
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }


    }
    //Semua method di bawah ini adalalah ketika user memilih hadiah yang ingin di beli maka program akan secara otomatis menampilkan
    //nama item dan harga pada teksfield item dan harga. selain itu juga program akan menampilkan database di table view
    @FXML
    void PilihBrith1(ActionEvent event) {
        LabelItem.setText("Paket 1");
        LabelHarga.setText("55.000");
        this.TampilanTable("GFTBOX1");

    }

    @FXML
    void PilihBrith2(ActionEvent event) {
        LabelItem.setText("Paket 2");
        LabelHarga.setText("60.000");
        this.TampilanTable("GFTBOX2");
    }

    @FXML
    void PilihBrith3(ActionEvent event) {
        LabelItem.setText("Paket 3");
        LabelHarga.setText("65.000");
        this.TampilanTable("GFTBOX3");
    }
    @FXML
    void Pilihbrith4(ActionEvent event) {
        LabelItem.setText("Paket 4");
        LabelHarga.setText("70.000");
        this.TampilanTable("GFTBOX4");
    }

    @FXML
    void PilihBrith5(ActionEvent event) {
        LabelItem.setText("Paket 5");
        LabelHarga.setText("72.000");
        this.TampilanTable("GFTBOX5");
    }

    @FXML
    void PilihBrith6(ActionEvent event) {
        LabelItem.setText("Paket 6");
        LabelHarga.setText("75.000");
        this.TampilanTable("GFTBOX6");

    }

    @FXML
    void PilihBucket1(ActionEvent event) {
        LabelItem.setText("Bunga Biru");
        LabelHarga.setText("92000");
        this.TampilanTable("BKTBG 3");

    }

    @FXML
    void PilihBucket2(ActionEvent event) {
        LabelItem.setText("Bunga Biru Putih");
        LabelHarga.setText("100000");
        this.TampilanTable("BKTBG 6");

    }

    @FXML
    void PilihBucket3(ActionEvent event) {
        LabelItem.setText("Bunga Merah Putih");
        LabelHarga.setText("87000");
        this.TampilanTable("BKTBG 5");

    }

    @FXML
    void PilihBucket4(ActionEvent event) {
        LabelItem.setText("Bunga Pink");
        LabelHarga.setText("92000");
        this.TampilanTable("BKTBG 4");

    }

    @FXML
    void PilihBucket5(ActionEvent event) {
        LabelItem.setText("Bunga Mawar");
        LabelHarga.setText("80000");
        this.TampilanTable("BKTBG 1");

    }

    @FXML
    void PilihBucket6(ActionEvent event) {
        LabelItem.setText("Bunga Tulip");
        LabelHarga.setText("70000");
        this.TampilanTable("BKTBG 2");

    }

    @FXML
    void PilihGradu1(ActionEvent event) {
        LabelItem.setText("Paket 1");
        LabelHarga.setText("55000");
        this.TampilanTable("GFTBOX1");

    }

    @FXML
    void PilihGradu2(ActionEvent event) {
        LabelItem.setText("Paket 2");
        LabelHarga.setText("60000");
        this.TampilanTable("GFTBOX2");
    }
    @FXML
    void pilihGradu3(ActionEvent event) {
        LabelItem.setText("Paket 3");
        LabelHarga.setText("65000");
        this.TampilanTable("GFTBOX3");

    }
    @FXML
    void PilihGradu4(ActionEvent event) {
        LabelItem.setText("Paket 4");
        LabelHarga.setText("70000");
        this.TampilanTable("GFTBOX4");

    }

    @FXML
    void PilihGradu5(ActionEvent event) {
        LabelItem.setText("Paket 5");
        LabelHarga.setText("72000");
        this.TampilanTable("GFTBOX5");

    }

    @FXML
    void PilihGradu6(ActionEvent event) {
        LabelItem.setText("Paket 5");
        LabelHarga.setText("75000");
        this.TampilanTable("GFTBOX6");

    }
}
