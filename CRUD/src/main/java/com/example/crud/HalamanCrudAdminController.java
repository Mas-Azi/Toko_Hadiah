package com.example.crud;
import
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class HalamanCrudAdminController implements Initializable {

    @FXML
    private Button ButtonDelete;

    @FXML
    private Button ButtonLogoutAdmin;

    @FXML
    private Button ButtonSave;

    @FXML
    private Button ButtonUpdate;

    @FXML
    private Button ButtonView;
    //variabel utnuk membuat tambel pada menu hadiah dengan modifier private
    @FXML
    private TableColumn<TokoHadiah, StringProperty> Harga;

    @FXML
    private TableColumn<TokoHadiah, StringProperty> JenisBarang;

    @FXML
    private TableColumn<TokoHadiah, StringProperty> KodeBarang;

    @FXML
    private TableColumn<TokoHadiah, StringProperty> NamaBarang;

    @FXML
    private TableColumn<TokoHadiah, IntegerProperty> Quantity;

    @FXML
    private TableView<TokoHadiah> TableBarang;

    @FXML
    private TextField TeksHarga, TeksJenisBarang, TeksKodeBarang,TeksNamaBarang;


    @FXML
    private TextField TeksQuantity;

    Koneksi koneksi = new Koneksi();

    //Sebuah Method untuk menginisiasi dengan parameter URL,dan resourceBundle
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TampilDatabase();
    }

    //Sebuat Method yang berfungsi untuk menampilkan data yang tersimpan pada database dengan variabel yang sesuai dengan database
    public void TampilDatabase(){
        KodeBarang.setCellValueFactory(new PropertyValueFactory<TokoHadiah, StringProperty>("Kode_Barang"));
        JenisBarang.setCellValueFactory(new PropertyValueFactory<TokoHadiah, StringProperty>("Jenis_Barang"));
        NamaBarang.setCellValueFactory(new PropertyValueFactory<TokoHadiah, StringProperty>("Nama_Barang"));
        Quantity.setCellValueFactory(new PropertyValueFactory<TokoHadiah, IntegerProperty>("Quantity"));
        Harga.setCellValueFactory(new PropertyValueFactory<TokoHadiah, StringProperty>("Harga"));
            try{
                String query = "SELECT * FROM data_barang";
                ResultSet hasil = koneksi.getData(query);
                ObservableList<TokoHadiah> model = FXCollections.observableArrayList();
                TableBarang.setItems(model);
                while (hasil.next()) {
                    String kode_Barang = hasil.getString(1);
                    String jenis_Barang = hasil.getString(2);
                    String nama_Barang = hasil.getString(3);
                    int quantity = hasil.getInt(4);
                        double harga = hasil.getDouble(5);
                        model.add(new TokoHadiah(kode_Barang, jenis_Barang, nama_Barang, quantity, harga));
                }
            } catch (Exception e){
                e.printStackTrace();
            }
            TableBarang.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> showModel(newValue));
    }
    //Sebuah Method yang berfungsi untuk membuat model dengan implementasi dari class Toko Hadiah
    //Parameter pada method ini yaitu dengan membuat object melalui class Toko Hadiah
    private void showModel(TokoHadiah model) {
        if (model != null){
            TeksKodeBarang.setText(model.kode_BarangProperty().getValue().toString());
            TeksJenisBarang.setText(model.getJenis_Barang());
            TeksNamaBarang.setText(model.getNama_Barang());
            TeksQuantity.setText(String.valueOf(model.getQuantity()));
            TeksHarga.setText(String.valueOf(model.getHarga()));

        } else {
            TeksKodeBarang.setText("");
            TeksJenisBarang.setText("");
            TeksNamaBarang.setText("");
            TeksQuantity.setText("");
            TeksHarga.setText("");

        }
    }
    //Sebuah Method yang digunakan jika admin menekan tombol delete pada tampilan menu Halama Crud admin
    // maka program akan secara otomatis menghapus data yang dipilih pada database
    @FXML
    void DeleteButton(ActionEvent event) {
        String Kode = TeksKodeBarang.getText();

        try{
            if(!Kode.isEmpty()){
                String query = "DELETE FROM data_barang WHERE Kode_Barang = '" + Kode + "';";
                int hasil = koneksi.manipulasiData(query);
                if(hasil == 1){
                    System.out.println("Data berhasil dihapus");
                }
            }
        } catch(Exception e){
            System.out.println(e);
        }

    }
    //Sebuah Method yang mana jika admin menkan tombol logout maka program akan mengarahkan admin ke menu Halaman Login
    @FXML
    void LogoutButton(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HalamanLogin.fxml"));
        Stage window = (Stage) ButtonLogoutAdmin.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    //Sebuah Method yang mana jika admin menekan tombol save pada menu halaman crud
    // maka program akan secara otomatis menyimpan data kedalam database
    @FXML
    void SaveButton(ActionEvent event) {
        String Kode = TeksKodeBarang.getText();
        String Jenis = TeksJenisBarang.getText();
        String Nama = TeksNamaBarang.getText();
        String QTY = TeksQuantity.getText();
        String bayar = TeksHarga.getText();
        String query = "INSERT INTO data_barang(Kode_Barang,Jenis_Barang,Nama_Barang,Quantity,Harga_barang) VALUES('"+Kode+"','"
                +Jenis+"','"+Nama+"','"+QTY+"','"+bayar+"')";
        int hasil = koneksi.manipulasiData(query);
        if (hasil == 1){
            System.out.println("Data terinput");
            TampilDatabase();
        }

    }

    //Sebuah Method yang mana jika admin menekan tombol update maka program akan memperbaruhi dan menyimpan dataterbaru kedalam database
    @FXML
    void UpdateButton(ActionEvent event) {
        String Kode = TeksKodeBarang.getText();
        String Jenis = TeksJenisBarang.getText();
        String Nama = TeksNamaBarang.getText();
        String QTY = TeksQuantity.getText();
        String bayar = TeksHarga.getText();
        String query = "UPDATE data_barang SET Jenis_Barang='"+Jenis+"',Nama_Barang='"+Nama+"',Quantity='"+QTY+"',Harga_barang='"+bayar+"' " +
                "WHERE Kode_Barang='" + Kode+"'";
        int hasil = koneksi.manipulasiData(query);
        if (hasil == 1){
            System.out.println("Data terupdate");
        }

    }
    //Sebuah Method yang digunakan jika admin menekan tombol button view pada tampilan menu halaman crud admin
    //maka program akan secara otomatis menampilkan data yang tersimpan di dalam datapase pada table view
    //Selain itu method ini juga memanggil method tampildatabase  untuk menampilkan data yang tersimpan di dalam database
    @FXML
    void ViewButton(ActionEvent event) {
        TampilDatabase();
        System.out.println("Alhamdulilah Terlihat");
    }


}
