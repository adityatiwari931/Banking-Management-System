/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagement.dao;

import com.mycompany.bankmanagement.databaseconnection.DatabaseConnection;
import com.mycompany.bankmanagement.entity.Customer;
import com.mycompany.bankmanagement.exception.CustomerException;
import com.mysql.cj.protocol.Resultset;
import com.mysql.cj.xdevapi.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author tiwar
 */
public class CustomerDaoImplementation implements CustomerDao{

    @Override
    public Customer LoginCustomer(String customerName, String customerPassword, int customerAccountNumber) throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        Customer customer = null;
        try(Connection conn = DatabaseConnection.provideConnection()) {
            
            PreparedStatement ps = conn.prepareStatement("select * from customerinformation i inner join account a on a.cid = i.cid where customerName = ? and customerPassword = ? and customerAccountNumber = ? ");
            
            ps.setString(1, customerName);
            ps.setString(2, customerPassword);
            ps.setInt(3, customerAccountNumber);
            
            ResultSet rs =  ps.executeQuery();
            
            if(rs.next())
            {
                int ac = rs.getInt("customerAccountNumber");
                String n = rs.getString("customerName");
                int b = rs.getInt("customerBalance");
                String e = rs.getString("customerMail");
                String p = rs.getString("customerPassword");
                String m = rs.getString("customerMobile");
                String ad = rs.getString("customerAddress");
                
                customer = new Customer(ac, n, b, p, m, m, ad);
                
            }else{
                throw new CustomerException("""
                                            Invalid Customer Name And Password !!\r
                                            Please Try Again""");
            }
        } catch (SQLException e) {
            throw new CustomerException(e.getMessage());
        }
        
        return customer;
    }

    @Override
    public int viewBalance(int customerAccountNumber) throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int balance = -1;
        try(Connection conn = DatabaseConnection.provideConnection()){
            
            PreparedStatement ps = conn.prepareStatement("select customerBalance from account where customerAccountNumber = ?");
            
            ps.setInt(1, customerAccountNumber);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                balance = rs.getInt("customerBalance");
                
            }
        } catch (SQLException e) {
            throw new CustomerException(e.getMessage());
        }
        return balance;
    }

    @Override
    public int Deposit(int customerAccountNumber, int amount) throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
           if(amount<0){
               
               throw new CustomerException("Amount cannot be negative");
           }
           int balance = -1;
           int x=-1;
           int updatebalance = 0;
            try(Connection conn = DatabaseConnection.provideConnection()) {
            
            PreparedStatement ps = conn.prepareStatement("select customerBalance from account where customerAccountNumber = ?");
            
            ps.setInt(1, customerAccountNumber);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                balance = rs.getInt("customerBalance");
                updatebalance = amount+balance;
            
            PreparedStatement ps2 = conn.prepareStatement("update account set customerBalance = ? where customerAccountNumber = ?");
            ps2.setInt(1, updatebalance);
            ps2.setInt(2, customerAccountNumber);
            x = ps2.executeUpdate();
            
            if(x>0){
                System.out.println("Amount Added Successfully --------");
                return updatebalance;
            }else{
                System.out.println("Not Updation----------------------");
                return balance;
            }
            
           }else{
                System.out.println("-----------No such Account Exist-------------");
            }
         
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
      return balance;
    }

    @Override
    public int withdraw(int customerAccountNumber, int amount) throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

            if(amount<0 ){
               
               throw new CustomerException("Amount cannot be negative");
           }
           int balance = -1;
           int x=-1;
           int updatebalance = 0;
            try(Connection conn = DatabaseConnection.provideConnection()) {
            
            PreparedStatement ps = conn.prepareStatement("select customerBalance from account where customerAccountNumber = ?");
            
            ps.setInt(1, customerAccountNumber);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next())
            {
                balance = rs.getInt("customerBalance");
                if(balance<amount){
                    throw new CustomerException("Not sufficient Balance");
                }
                updatebalance = balance-amount;
            
            PreparedStatement ps2 = conn.prepareStatement("update account set customerBalance = ? where customerAccountNumber = ?");
            ps2.setInt(1, updatebalance);
            ps2.setInt(2, customerAccountNumber);
            x = ps2.executeUpdate();
            
            if(x>0){
                System.out.println("Amount Withdraw Successfully --------");
                return updatebalance;
            }else{
                System.out.println("Not Updation----------------------");
                return balance;
            }
            
           }else{
                System.out.println("-----------No such Account Exist-------------");
            }
      
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
      return balance;
    }

    @Override
    public int transfer(int senderAcc, int receiverAcc, int amount) throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int transferBalance = -1;
        int currentBalance =-1;
        try {
            transferBalance = withdraw(senderAcc, amount);
            currentBalance = Deposit(receiverAcc, amount);
        } catch (Exception e) {
            
        }
        return transferBalance;
    }
    
}
