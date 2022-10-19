
package com.miniproject;

import java.sql.SQLException;
import java.util.Scanner;

import com.adminlogin.Admin;
import com.studentaccess.Result;
import com.studentaccess.Student;

public class MiniProjectMain {
          
	public static void main(String[] args) throws SQLException {
		
			Scanner scanner = new Scanner(System.in);
			System.out.println("************************ WELCOME TO JAVA QUIZ *************************");
			System.out.println(" ");
			System.out.println("Please press 1 - For Admin   Press 2 - For Student ");
			int choice = scanner.nextInt();
		
			if(choice == 1) {
				System.out.println(" ");
				Admin admin = new Admin();
				int a = admin.adminLogin();
				if(a==1) {
					admin.adminOption();
				}else {
					System.out.println("You Enter Wrong Username & Password..........");
				}
			}else if(choice == 2) {
				System.out.println(" ");
				Student student = new Student();
				student.studentOption();
			}
			else{
				System.out.println("Sorry you Enter Wrong Choice..........");
			}
	}

}
