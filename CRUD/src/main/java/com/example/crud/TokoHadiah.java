package com.example.crud;

import javafx.beans.property.*;

public class TokoHadiah {
    //Membuat Variabel dan property yang sesuai dengan kebutuhan aplikasi dengan modifier private
    //Penamaan variabel haru sesuai dengan penamaan di dalam database
    private StringProperty Kode_Barang;
    private StringProperty Jenis_Barang;
    private StringProperty Nama_Barang;
    private IntegerProperty Quantity;
    private DoubleProperty Harga;

    //Membuat Method Model dengan Nama TokoHadiah dan memiliki parameter
    public TokoHadiah (String Kode, String JenisBarang, String NamaBarang, int Quantity, double HargaBarang){
        this.Kode_Barang = new SimpleStringProperty(Kode);
        this.Jenis_Barang = new SimpleStringProperty(JenisBarang);
        this.Nama_Barang =  new SimpleStringProperty(NamaBarang);
        this.Quantity = new SimpleIntegerProperty(Quantity);
        this.Harga = new SimpleDoubleProperty(HargaBarang);
    }

    public TokoHadiah (String Kode, String Nama, double Harga){
        this.Kode_Barang = new SimpleStringProperty(Kode);
        this.Nama_Barang =  new SimpleStringProperty(Nama);
        this.Harga = new SimpleDoubleProperty(Harga);
    }

    //Setelah membuat Variabel dan property selesai dibuat method di bawah ini berfungsi untuk membuat setter dan getter
    //beserta dengan prperty sesuai dengan tipe data masing-masing variabel
    public String getKode_Barang() {
        return Kode_Barang.get();
    }

    public StringProperty kode_BarangProperty() {
        return Kode_Barang;
    }

    public void setKode_Barang(String kode_Barang) {
        this.Kode_Barang.set(kode_Barang);
    }

    public String getJenis_Barang() {
        return Jenis_Barang.get();
    }

    public StringProperty jenis_BarangProperty() {
        return Jenis_Barang;
    }

    public void setJenis_Barang(String jenis_Barang) {
        this.Jenis_Barang.set(jenis_Barang);
    }

    public String getNama_Barang() {
        return Nama_Barang.get();
    }

    public StringProperty nama_BarangProperty() {
        return Nama_Barang;
    }

    public void setNama_Barang(String nama_Barang) {
        this.Nama_Barang.set(nama_Barang);
    }

    public int getQuantity() {
        return Quantity.get();
    }

    public IntegerProperty quantityProperty() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        this.Quantity.set(quantity);
    }

    public double getHarga() {
        return Harga.get();
    }

    public DoubleProperty hargaProperty() {
        return Harga;
    }

    public void setHarga(double harga) {
        this.Harga.set(harga);
    }

}
