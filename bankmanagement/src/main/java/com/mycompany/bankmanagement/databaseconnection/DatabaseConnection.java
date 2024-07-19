/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagement.databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author tiwar
 */
public class DatabaseConnection {
    @SuppressWarnings("CallToPrintStackTrace")
    public static Connection provideConnection(){
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/bankingsystem";
        try {
            conn = DriverManager.getConnection(url , "root" ,"ak47tiwari#@");
        } catch (SQLException e) {
            System.out.println("Exception occur in Database");
        }
      return conn;
    }
    
}
