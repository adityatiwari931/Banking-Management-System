/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.bankmanagement.dao;

import com.mycompany.bankmanagement.entity.Customer;
import com.mycompany.bankmanagement.exception.CustomerException;

/**
 *
 * @author tiwar
 */
public interface CustomerDao {

    public Customer LoginCustomer(String customerName , String customerPassword , int customerAccountNumber)throws CustomerException;
    
    public int viewBalance(int customerAccountNumber)throws CustomerException;
    
    public int Deposit(int customerAccountNumber,int amount)throws CustomerException;
    
    public int withdraw(int customerAccountNumber,int amount)throws CustomerException;
    
    public int transfer(int senderAcc ,int receiverAcc,int amount)throws CustomerException;
    
}
