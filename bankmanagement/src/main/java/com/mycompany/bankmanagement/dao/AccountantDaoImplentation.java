/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bankmanagement.dao;

import com.mycompany.bankmanagement.databaseconnection.DatabaseConnection;
import com.mycompany.bankmanagement.entity.Accountant;
import com.mycompany.bankmanagement.entity.Customer;
import com.mycompany.bankmanagement.exception.CustomerException;
import com.mysql.cj.xdevapi.PreparableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.security.auth.login.AccountException;

/**
 *
 * @author tiwar
 */
public class AccountantDaoImplentation implements AccounntantDao {

    @Override
    public Accountant LoginAccountant(String accountantUsername, String accountantPassword) throws AccountException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        Accountant acc = null;
        try (Connection conn = DatabaseConnection.provideConnection()) {
            PreparedStatement ps = conn.prepareStatement("select * from accountant where accountantUsername = ? and accountantPassword = ?");
            ps.setString(1, accountantUsername);
            ps.setString(2, accountantPassword);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String n = rs.getString("accountantUsername");
                String e = rs.getString("accountantEmail");
                String p = rs.getString("accountantPassword");

                acc = new Accountant(n, e, p);
            }
        } catch (SQLException e) {
            throw new AccountException("Invalid username and Password");
        }
        return acc;
    }

    @Override
    public int addCustomer(String customerName, String customerMail, String customerPassword, String customerMobile, String customerAddress) throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        int cid = -1;
        try (Connection conn = DatabaseConnection.provideConnection()) {
            
            PreparedStatement ps = conn.prepareStatement("insert into customerinformation (customerName,customerMail,customerPassword,customerMobile,customerAddress) values(?,?,?,?,?) ");

            ps.setString(1, customerName);
            ps.setString(2, customerMail);
            ps.setString(3, customerPassword);
            ps.setString(4, customerMobile);
            ps.setString(5, customerAddress);

            int x = ps.executeUpdate();

            if(x>0){
                System.out.println("Customer Added Successfully!!");
            }else{
                System.out.println("Customer Not Added Successfully!!");
            }
            if (x > 0) {
                PreparedStatement ps2 = conn.prepareStatement("select cid from customerinformation where customerMail = ? and customerMobile = ? ");
                ps2.setString(1, customerMail);
                ps2.setString(2, customerMobile);

                ResultSet rs = ps2.executeQuery();

                if (rs.next()) {
                    cid = rs.getInt("cid");
                    

                } else {
                    System.out.println("Inserted Data is Inncorrect ! Please Try Again ");
                }
            }

        } catch (SQLException e) {
            System.out.println("SQL Query related error !!");
        }
//        System.out.println("cid="+cid);
        return cid;
    }

    @Override
    public String addAccount(int customerBalance, int cid) throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String message = null;
        try(Connection conn = DatabaseConnection.provideConnection()) {
           
            PreparedStatement ps = conn.prepareStatement("insert into account(customerBalance,cid) values(?,?)");
            ps.setInt(1, customerBalance);
            ps.setInt(2, cid);
            
            int x = ps.executeUpdate();

            if (x > 0) {
                
                System.out.println("Account added Successfully");
                PreparedStatement ps2=conn.prepareStatement("select * from account where cid=?");
				ps2.setInt(1, cid);
				ResultSet num=ps2.executeQuery();
				if(num.next()) {
					System.out.println("Your Account Number is : "+num.getInt("customerAccountNumber"));
				}
            } else {
                System.out.println("Inserted not added successfully");
            }
        } catch (SQLException e) {
            System.out.println("SQL related Exception in addAccount method");
        }
        return message;
    }

    @Override
    public String updateCustomer(int customerAccountNumber, String customerAddress) throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        String message = null;
        
        try(Connection conn = DatabaseConnection.provideConnection()){
            
            PreparedStatement ps = conn.prepareStatement("update customerinformation i inner join account a on i.cid=a.cid set i.customerAddress=? where a.customerAccountNumber= ?");
            
            ps.setString(1, customerAddress);
            ps.setInt(2, customerAccountNumber);
            
		int x=ps.executeUpdate();
			
		if(x>0) {
		    System.out.println("Address updated Successfully");
		}else {
		    System.out.println("Address is not Updated Successfully");
		}
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String deleteAccount(int customerAccountNumber) throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

        String message = null;
        
        try(Connection conn = DatabaseConnection.provideConnection()) {
           
            PreparedStatement ps = conn.prepareStatement("delete i from customerinformation i inner join account a on i.cid = a.cid where a.customerAccountNumber = ?");
            
            ps.setInt(1, customerAccountNumber);
            
            int x = ps.executeUpdate();
            
            if(x>0){
                System.out.println("Account deleted Successfully");
            }else{
                System.out.println("Delete failed.....Account not found !");
                System.out.println("-------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public Customer viewCustomer(int customerAccountNumber) throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        Customer cu = null;
        try(Connection conn = DatabaseConnection.provideConnection()){
            
            PreparedStatement ps = conn.prepareStatement("select * from customerinformation i inner join account a on a.cid = i.cid where customerAccountNumber = ?");
            
            ps.setInt(1, customerAccountNumber);
            
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                int a = rs.getInt("customerAccountNumber");
                String n = rs.getString("customerName");
                int b = rs.getInt("customerBalance");
                String e = rs.getString("customerMail");
                String p = rs.getString("customerPassword");
                String m = rs.getString("customerMobile");
                String ad = rs.getString("customerAddress");
                
                cu = new Customer(a, n, b, p, e, m, ad);
            }
        } catch (Exception e) {
        }
     
     return cu;
    }

    @Override
    public Customer viewAllCustomer() throws CustomerException {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        
        Customer cu = null;
        try(Connection conn = DatabaseConnection.provideConnection()){
            PreparedStatement ps = conn.prepareStatement("select * from customerinformation i inner join account a on a.cid = i.cid");
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                int a = rs.getInt("customerAccountNumber");
                String n = rs.getString("customerName");
                int b = rs.getInt("customerBalance");
                String e = rs.getString("customerMail");
                String p = rs.getString("customerPassword");
                String m = rs.getString("customerMobile");
                String ad = rs.getString("customerAddress");
                
                System.out.println("*******************************");
                System.out.println("Accoount Number : "+a);
                System.out.println("Customer Name : "+n);
                System.out.println("customer Balance : "+b);
                System.out.println("customer Mail : "+e);
                System.out.println("customer Password : "+p);
                System.out.println("customer Mobile : "+m);
                System.out.println("customer Address : "+ad);
                
                System.out.println("********************************");
                
                cu = new Customer(a,n,b,p,e,m,ad);
            }
        } catch (SQLException e) {
            throw new CustomerException("Invalid Account Number");
        }
        return cu;
    }

}
