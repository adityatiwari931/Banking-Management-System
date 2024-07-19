/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.bankmanagement;

import com.mycompany.bankmanagement.dao.AccounntantDao;
import com.mycompany.bankmanagement.dao.AccountantDaoImplentation;
import com.mycompany.bankmanagement.dao.CustomerDao;
import com.mycompany.bankmanagement.dao.CustomerDaoImplementation;
import com.mycompany.bankmanagement.entity.Accountant;
import com.mycompany.bankmanagement.entity.Customer;
import com.mycompany.bankmanagement.exception.AccountantException;
import com.mycompany.bankmanagement.exception.CustomerException;
import java.util.Scanner;


/**
 *
 * @author tiwar
 */
public class Bankmanagement {

    @SuppressWarnings("UseSpecificCatch")
    public static void main(String[] args) {
        System.out.println("Hello World!");
        Scanner sc = new Scanner(System.in);
         boolean f = true;
         
         while(f){
             System.out.println("------Welcome to the AdityaCoprative Bank Pvt. Ltd.-------");
             System.out.println("-----------------------------------------------------------");
             System.out.println("""
                                1.ADMIN LOGIN PORTAL \r
                                2.CUSTOMER""");
             
             System.out.println("Choose Your Option");
             
             int choice = sc.nextInt();
             switch (choice) 
             {
                 case 1 -> {
                     System.out.println("-----Admin Login Credential -----");
                     System.out.println("Enter Username : ");
                     String username = sc.next();
                     System.out.println("Enter Password : ");
                     String pass = sc.next();
                     
                     AccounntantDao ad = new AccountantDaoImplentation();
                     
                     try {
                         Accountant a = ad.LoginAccountant(username, pass);
                         if(a==null){
                             System.out.println("Wrong Credential");
                             break;
                         }
                         System.out.println("Login Successfully!!!");
                         System.out.println("Welcome : "+a.getAccountantUsername()+" as admin of AdityaCoprative Bank Pvt. Ltd.");
                         
                         boolean y = true;
                         while(y){
                             System.out.println("""
                                                ---------------------\r
                                                1.Add New Customer Account
                                                2.Update Customer Address
                                                3.Delete Customer
                                                4.View Particular Account Details
                                                5.View All Customer/Account List
                                                6.Account Logout
                                                """);
                             
                             int x = sc.nextInt();
                             if(x==1)
                             {
                                 System.out.println("-----New Account-----");
                                 System.out.println("Enter the CustomerName : ");
                                 String a1 = sc.next();
                                 System.out.println("Enter Account Opening Balance");
                                 int a2 = sc.nextInt();
                                 System.out.println("Enter the CustomerMail : ");
                                 String a3 = sc.next();
                                 System.out.println("Enter the CustomerPassword : ");
                                 String a4= sc.next();
                                 System.out.println("Enter the CustomerMobile : ");
                                 String a5 = sc.next();
                                 System.out.println("Enter the CustomerAddress : ");
                                 String a6 = sc.next();
                                 
                                 int s1 =-1;
                                 try {
                                     s1 = ad.addCustomer(a1, a3, a4, a5, a6);
//                                     System.out.println("s1="+s1);
                                     
                                     try {
                                         
                                         ad.addAccount(a2, s1);
                                         
                                     } catch (CustomerException e) {
                                         e.printStackTrace();
                                     }
                                     
                                     
                                 } catch (CustomerException e) {
                                     System.out.println(e.getMessage());
                                 }
                                 
                                 System.out.println("----------------------------");
                                 
                             }
                             if(x==2)
                             {
                                 System.out.println("Update Customer Address....");
                                 System.out.println("Enter Customer Account Number..");
                                 int u =sc.nextInt();
                                 System.out.println("Enter the Address....");
                                 String u2 = sc.next();
                                 
                                 try {
                                     String mes = ad.updateCustomer(u, u2);
                                 } catch (Exception e) {
                                     e.printStackTrace();
                                 }
                             }
                             if(x==3)
                             {
                                 System.out.println("Delete Customer.....");
                                 System.out.println("Enter the Account Number");
                                 int ac = sc.nextInt();
                                 String s = null;
                                 try {
                                     s = ad.deleteAccount(ac);
                                 } catch (Exception e) {
                                     e.printStackTrace();
                                     
                                 }
                                 if(s!=null){
                                     System.out.println(s);
                                 }
                             }
                             if(x==4)
                             {
                                 System.out.println("----------Customer Details------");
                                 System.out.println("Enter the Customer Account Number : ");
                                 int ac = sc.nextInt();
                                 
                                 try {
                                     Customer cus = ad.viewCustomer(ac);
                                     
                                     if (cus!=null) {
                                         System.out.println("------------------");
                                         System.out.println("Account Number : "+cus.getCustomerAccountNumber());
                                         System.out.println("Name : "+cus.getCustomerName());
                                         System.out.println("Balance : "+cus.getCustomerBalance());
                                         System.out.println("Email : "+cus.getCustomerMail());
                                         System.out.println("Password : "+cus.getCustomerPassword());
                                         System.out.println("Mobile Number : "+cus.getCustomerMobile());
                                         System.out.println("Address : "+cus.getCustomerAddress());
                                         
                                     }else{
                                         System.out.println("Account does Not Exist!!");
                                         System.out.println("-------------------------");
                                     }
                                 } catch (Exception e) {
                                     e.printStackTrace();
                                 }
                             }
                             
                             if(x==5)
                             {
                                 try {
                                     System.out.println("-------All Customer List------");
                                     
                                     Customer cus = ad.viewAllCustomer();
                                 } catch (CustomerException e) {
                                     e.printStackTrace();
                                 }
                             }
                             
                             if(x==6)
                             {
                                 System.out.println("-----------Account Logout Successfully---------");
                                 y=false;
                             }
                         }
                         break;
                     } catch (Exception e) {
//                         will handle here
                          System.out.println(e.getMessage());
                     }
                }
                 
                 case 2 ->
                 {
                     System.out.println("LOGIN<<------------->>CUSTOMER");
                     System.out.println("------------------------------");
                     System.out.println("Enter the Customer Name : ");
                     String customerUsername = sc.next();
                     System.out.println("Enter the Password : ");
                     String customerPassword = sc.next();
                     System.out.println("Enter the Account Number : ");
                     int accountNumber = sc.nextInt();
                     
                     CustomerDao cd = new CustomerDaoImplementation();
                     
                     try {
                         
                         Customer cus = cd.LoginCustomer(customerUsername, customerPassword , accountNumber);
                         System.out.println("Welcome : "+cus.getCustomerName());
                         
                         boolean m = true;
                         
                         
                         while (m) {                             
                             System.out.println("""
                                                ---------------------\r
                                                1.View Blance
                                                2.Deposit
                                                3.Withdraw
                                                4.Transfer
                                                5.Logout
                                                """);
                             
                             int x= sc.nextInt();
                             try {
                                 if(x==1)
                             {
                                 System.out.println("------Balance-------");
                                 System.out.println("Current Account Balance----");
                                 System.out.println(cd.viewBalance(accountNumber));
                             }
                             } catch (Exception e) {
                                 System.out.println("---------");
                             }
                             try {
                             if(x==2)
                             {
                                 System.out.println("---------------------------Deposit-----------------------------");
				 System.out.println("------------Enter the amount to be deposited-------------------");
				 int deposit=sc.nextInt();
				 System.out.println("Final amount : "+cd.Deposit(accountNumber, deposit));
							 
		             }
                             } catch (Exception e) {
                                 System.out.println(e.getMessage());
                             }
                             try {
                             if(x==3)
                             {
                                 System.out.println("---------------------------Withdraw-----------------------------");
				 System.out.println("------------Enter the amount to be Withdraw-------------------");
				 int Withdraw=sc.nextInt();
				 System.out.println("Final amount : "+cd.withdraw(accountNumber, Withdraw));
							 
		             }
                             } catch (Exception e) {
                                 System.out.println(e.getMessage());
                             }

                             try {
                             if(x==4)
                             {
                                 System.out.println("---------------------------Transfer-----------------------------");
                                 
                                
                                 System.out.println("Enter the Account Number of reciever : ");
                                 int receiverAcc = sc.nextInt();
				 System.out.println("Enter the amount to be Transfer-------------------");
				 int transferammount=sc.nextInt();
				 System.out.println("Your Current balance is : "+cd.transfer(accountNumber, receiverAcc, transferammount));
							 
		             }
                             
                             if(x==5){
                                 System.out.println("-----------Account Logout Successfully---------");
                                 m=false;
                             }
                             } catch (Exception e) {
                                 System.out.println(e.getMessage());
                             }
                             
                             
                         }
                     } catch (Exception e) {
//                         System.out.println("Invalid");
                           e.printStackTrace();
                     }
                     break;
                 }
                 
                 default -> throw new AssertionError();
             }
         }
    }
}
