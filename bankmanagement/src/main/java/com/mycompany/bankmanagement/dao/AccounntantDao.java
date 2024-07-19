/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.bankmanagement.dao;

import com.mycompany.bankmanagement.entity.Accountant;
import com.mycompany.bankmanagement.entity.Customer;
import com.mycompany.bankmanagement.exception.CustomerException;
import javax.security.auth.login.AccountException;

/**
 *
 * @author tiwar
 */
public interface AccounntantDao {
    public Accountant LoginAccountant(String accountantUsername, String accountantPassword) throws AccountException;
    
    public int addCustomer(String customerName,String customerMail,String customerPassword,String customerMobile,String customerAddress) throws CustomerException;
    
    public String addAccount(int customerBalance,int cid)throws CustomerException;
    
    public String updateCustomer(int customerAccountNumber,String customerAddress)throws CustomerException;
    
    public String deleteAccount(int customerAccountNumber) throws CustomerException;
    
    public Customer viewCustomer(int customerAccountNumber)throws CustomerException;
    
    public Customer viewAllCustomer()throws CustomerException;
}
