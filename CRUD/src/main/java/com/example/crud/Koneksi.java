package com.example.crud;

import java.sql.*;

public class Koneksi {

    private Connection conn;
    private Statement st;
    //Method untuk menghubungkan database dengan program
    public Koneksi(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String urldb = "jdbc:mysql://localhost:3306/crud_toko_hadiah";
            final String user = "root";
            final String password = "";
            conn = DriverManager.getConnection(urldb, user, password);
            System.out.println("Koneksi Tersambung");
        } catch(Exception e){
            System.out.println("Koneksi gagal : " + e);
        }
    }

    //Method untuk mengambil data- SELECT
    public ResultSet getData(String query){
        try{
            st = conn.createStatement();
            return st.executeQuery(query);
        } catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
    // Method untuk insert, Update, Delete
    public int manipulasiData(String query){
        try{
            st = conn.createStatement();
            return st.executeUpdate(query);
        } catch(SQLException e){
            System.out.println("SQL Error : " + e);

        } catch (Exception e){
            System.out.println("Terjadi error yang tidak diketahui: " + e);
        }
        return 0;
    }

}
