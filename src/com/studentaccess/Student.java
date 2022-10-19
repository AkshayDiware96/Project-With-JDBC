
package com.studentaccess;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import com.ConnectionTest;
import com.miniproject.Quiz;
import com.miniproject.Quizable;

public class Student {			
	Connection connection = null;
	PreparedStatement preparedStatement= null;
	Scanner scanner = new Scanner(System.in);
	
	private int id;
	private String firstname;
	private String lastname;
	private String city;
	private String mobile;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public void studentOption() throws SQLException {		
		Student student = new Student();
		System.out.println("--------------- WELCOME STUDENT ---------------");
		System.out.println(" ");
		System.out.println("Enter 1 - For Restration & Quiz Start.");
		System.out.println("Enter 2 - For Display Result.");
		int studentchoice = scanner.nextInt();
		if(studentchoice == 1) {
			int studid = student.setStudentDetail();
			int a = student.insertStudentDetail();
			System.out.println(" ");
			System.out.println("Registration Successful.....");
			System.out.println(" ");
			System.out.println("---------------  Exam Start Now  -------------- ");
			System.out.println(" ");
			if(a==1) {
			Quiz quiz = new Quiz();
			int result = quiz.quizStart();
			System.out.println("Your marks is "+result);
			Result resultClass = new Result();
			resultClass.createResult(result);
			resultClass.insurtResult(result,studid);
			}
			if(a==0){
				System.out.println("You Duplicate Exam Id .......");
			}
		}
		else if(studentchoice == 2){
			System.out.println("---------------- Your Result ----------------- ");
			student.getStudentDetail();
		}
		else {
			System.out.println("You Enter Wrong Choice........");
		}
	}
	
	public int setStudentDetail() {   		
		System.out.print("Enter Id -> ");
		int id = scanner.nextInt();
		setId(id);
		System.out.print("Enter First Name -> ");
		String firstname = scanner.next();
		setFirstname(firstname);
		System.out.print("Enter Last Name -> ");
		String lastname = scanner.next();
		setLastname(lastname);
		System.out.print("Enter city -> ");
		String city = scanner.next();
		setCity(city);
		System.out.print("Enter Mobile Number -> ");
		String mobile = scanner.next();
		setMobile(mobile);
		return id;
	}
	
	public int insertStudentDetail() {			
		int i = 0;
		try {
		ConnectionTest connectionTest = new ConnectionTest();
		connection = connectionTest.getConnection();
		preparedStatement = connection.prepareStatement("insert into studentdetail(id,firstname,lastname,city,mobile) values (?,?,?,?,?)");
		preparedStatement.setInt(1, getId());
		preparedStatement.setString(2, getFirstname());
		preparedStatement.setString(3, getLastname());
		preparedStatement.setString(4, getCity());
		preparedStatement.setString(5, getMobile());
		i = preparedStatement.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return i;
	}
	
	public void getStudentDetail() {			
		try {
		ConnectionTest connectionTest = new ConnectionTest();
		connection = connectionTest.getConnection();
		System.out.println("Enter Student Exam Id -> ");
		String str1 = scanner.next();
		preparedStatement = connection.prepareStatement("select studentdetail.id,studentdetail.firstname,studentdetail.lastname,studentdetail.city,studentdetail.mobile,result.marks,result.passclass from studentdetail inner join result on studentdetail.id=result.id where studentdetail.id=?");
		preparedStatement.setString(1, str1);
		ResultSet resultSet = preparedStatement.executeQuery();
		while(resultSet.next()) {
			System.out.println("****************************   Mr."+resultSet.getString(2)+" "+resultSet.getString(3)+"   ***********************************");
			System.out.println("Student Id :- "+resultSet.getString(1));
			System.out.print("Student name :- Mr./Mrs. "+resultSet.getString(2));
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
}
