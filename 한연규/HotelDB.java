package net.hb.project;

import java.sql.Connection;
import java.sql.DriverManager;

public class HotelDB {
	public static Connection dbConnection() {
		Connection CN = null ;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url = "jdbc:oracle:thin:@localhost:1521:XE";
			String user = "system";
			String password = "1234"; 
		  CN = DriverManager.getConnection(url, user, password);
		 }catch(Exception ex){ System.out.println("db서버연결실패 " + ex.toString()); }
		return CN;
	}//end
}
