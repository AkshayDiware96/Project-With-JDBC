
package com.studentaccess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import com.ConnectionTest;
import com.miniproject.Quiz;
public class Result {					
		Connection connection;
		PreparedStatement preparedStatement;
		Scanner scanner = new Scanner(System.in);
		Student stud = new Student();
		int result;
		public int createResult(int result) {

			if(result>=8 && result<=10) {
				System.out.println(" Congratulation.... You Pass with Class A & Marks - "+result);
			}
			else if(result>=6 && result<8) {
				System.out.println("Congratulation.... You Pass with Class B & marks - "+result);
			}
			else if(result==5){
				System.out.println("Congratulation.... You Pass with Class C marks - "+result);
			}else if(result<5){
				System.out.println("Sorry.... Your are Fail in Exam marks - "+result);
			}
			return result;
		}
		
		public int insurtResult( int result ,int studid) throws SQLException {   		
			int c = 0;
			try {
				ConnectionTest connectionTest = new ConnectionTest();
				connection = connectionTest.getConnection();
				preparedStatement = connection.prepareStatement("insert into result(id,marks,passclass) values (?,?,?)");
					preparedStatement.setInt(1,studid);
					preparedStatement.setInt(2,result);
		
					if(result>=8 && result<=10) {
						preparedStatement.setString(3, "A");
					}
					else if(result>=6 && result<8) {
						preparedStatement.setString(3, "B");
					}
					else if(result==5){
						preparedStatement.setString(3, "C");
					}
					else {
						preparedStatement.setString(3, "Fail");
					}
					c = preparedStatement.executeUpdate();
				
			}
				catch(Exception e) {
				e.printStackTrace();
			}
			finally{
				connection.close();
				preparedStatement.close();
			}
			return c;
		}
}
