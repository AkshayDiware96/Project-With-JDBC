
package com.miniproject;

import java.sql.SQLException;

public interface Quizable {		
	// abstract method for Admin
	public abstract int adminLogin () ;
	public abstract void adminOption();
	public abstract void insertQue() throws SQLException;
	public void getStudentDetail();
	public void getAllStudentDetail();
	
}
