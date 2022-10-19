
package com.miniproject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import com.ConnectionTest;

public class Quiz {				
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		int k;
		int count;
		String ans;
		public int quizStart() {							
			Scanner scanner = new Scanner(System.in);
			try {
				ConnectionTest connectionTest = new ConnectionTest();
				connection = connectionTest.getConnection();
				preparedStatement = connection.prepareStatement("select * from javaquestion order by rand()");
				ResultSet resultSet = preparedStatement.executeQuery();
				
				HashMap<String,String> hashMap = new HashMap<String,String>(10);
				HashMap<String,String> hashMap1 = new HashMap<String,String>(10);
				while(resultSet.next()) {
					hashMap.put(resultSet.getString(2), resultSet.getString(3));
					hashMap1.put(resultSet.getString(2), resultSet.getString(4));
				}

				Set<String> set = hashMap.keySet();
				Iterator<String> iterator = set.iterator();
				for(int z=1;z<=10;z++) {
					String s = iterator.next();
					System.out.println("Q:"+z+" "+s);
					System.out.println("Option :- "+hashMap.get(s));
					System.out.print("Enter Answer :- ");
					String ans = scanner.next();
					if(ans.equals("a") || ans.equals("b") || ans.equals("c") || ans.equals("d")) {
						if(ans.equals(hashMap1.get(s))) {
							count++;
					}
						System.out.println(" ");
					}
					else {
						System.out.println("Incorrect Option...........");
					}
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return count;
		}
		
}
