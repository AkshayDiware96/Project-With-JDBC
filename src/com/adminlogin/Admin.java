
package com.adminlogin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ConnectionTest;
import com.miniproject.Quizable;

public class Admin implements Quizable{			
	int a = 0;
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	Scanner scanner = new Scanner(System.in);

	public int adminLogin() {   				 
		try {
		ConnectionTest connectionTest = new ConnectionTest();
		connection = connectionTest.getConnection();
		System.out.println(" ");
		System.out.println("----------  Enter LoginId & Password  ---------");
		System.out.println(" ");
		System.out.print("Enter UserName -> ");
		String str1 = scanner.next();
		System.out.print("Enter Password ->");
		String str2 = scanner.next();
		preparedStatement = connection.prepareStatement("select username,password from admin where username=?");
		preparedStatement.setString(1, str1);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			String username1 = resultSet.getString(1);
			String password1 = resultSet.getString(2);
			if(str1.equals(username1) && str2.equals(password1)) {
				System.out.println(" ");
				System.out.println("Login Successful .............");
				a = 1;
			}else{
			
				a = 0;
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return a;
	}
	
	public void adminOption() {						
		Admin admin = new Admin();
		System.out.println(" ");
		System.out.println("1 for Insert Que. into Table.");
		System.out.println("2 for Dsplay Result by Exam Id.");
		System.out.println("3 for Display Result of All Id .");
		int adminchoice = scanner.nextInt();
		try {
			if(adminchoice == 1) {
			admin.insertQue();
			}else if(adminchoice == 2) {
				admin.getStudentDetail();
			}
			else if(adminchoice == 3){
				admin.getAllStudentDetail();
			}
			else {
				System.out.println("Admin you endter wrong choice");
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}
	
	public void insertQue() throws SQLException {					
			try {
				ConnectionTest connectionTest = new ConnectionTest();
				connection = connectionTest.getConnection();
				preparedStatement = connection.prepareStatement("insert into javaquestion (question,optn,answer) values (?,?,?)");
				for(int i=1;i<=10;i++) {
					System.out.println("Enter Question Number "+i+" ->");
					String str1 = scanner.nextLine();
					preparedStatement.setString(1, str1);
					System.out.println("Enter 4 Options ->");
					String str2 = scanner.nextLine();
					preparedStatement.setString(2, str2);
					System.out.println("Enter Correct Option ->");
					String str3 = scanner.nextLine();
					preparedStatement.setString(3, str3);
					preparedStatement.executeUpdate();
				}
			}
				catch(Exception e) {
				e.printStackTrace();
			}
			finally{
				connection.close();
				preparedStatement.close();
			}		
	}
	
	public void getStudentDetail() {					
		try {
		ConnectionTest connectionTest = new ConnectionTest();
		connection = connectionTest.getConnection();
		System.out.println("Enter Enter Student Exam Id -> ");
		String str1 = scanner.next();
		preparedStatement = connection.prepareStatement("select studentdetail.id,studentdetail.firstname,studentdetail.lastname,studentdetail.city,studentdetail.mobile,result.marks,result.passclass from studentdetail inner join result on studentdetail.id=result.id where studentdetail.id=?");
		preparedStatement.setString(1, str1);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			System.out.println("****************************   "+resultSet.getString(2)+" "+resultSet.getString(3)+"   ***********************************");
			System.out.println("Student Id :- "+resultSet.getString(1));
			System.out.print("Student name :-  "+resultSet.getString(2));
			System.out.println(" "+resultSet.getString(3));
			System.out.print("City :- "+resultSet.getString(4));
			System.out.println("  Mobile Number +91 "+resultSet.getString(5));
			System.out.print("Exam Marks:- "+resultSet.getString(6));
			System.out.println(" ,  Grade in Class  "+resultSet.getString(7));	
			System.out.println("****************************---------------------***********************************");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getAllStudentDetail() {				
		try {
		ConnectionTest connectionTest = new ConnectionTest();
		connection = connectionTest.getConnection();
		preparedStatement = connection.prepareStatement("select studentdetail.id,studentdetail.firstname,studentdetail.lastname,studentdetail.city,studentdetail.mobile,result.marks,result.passclass from studentdetail inner join result on studentdetail.id=result.id");
		ResultSet resultSet = preparedStatement.executeQuery();
	
	
			System.out.println("*************************************************************************************");
			System.out.print(" | Exam Id | ");
			System.out.print(" First Name | ");
			System.out.print(" Last Name | ");
			System.out.print(" City  | ");
			System.out.print(" Mobile Number | ");
			System.out.print(" Marks | ");
			System.out.println(" Grade | ");
			System.out.println(" ");
		while(resultSet.next()) {
			System.out.print(" | "+resultSet.getString(1)+" |  ");
			System.out.print(" "+resultSet.getString(2)+" | ");
			System.out.print(" "+resultSet.getString(3)+" | ");
			System.out.print(" "+resultSet.getString(4)+" | ");
			System.out.print(" +91 "+resultSet.getString(5)+" | ");
			System.out.print(" "+resultSet.getString(6)+" | ");
			System.out.println(" "+resultSet.getString(7)+" | ");
			System.out.println(" ");
		}
		System.out.println("***************************************************************************************");
	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	
}
