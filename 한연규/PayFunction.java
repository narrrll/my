package net.hb.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;


public class PayFunction {
	private String msg;
	private Connection CN;
	private Statement ST;
	private ResultSet RS;
	private Scanner sc = new Scanner(System.in);
	private PreparedStatement PST = null;
	
	public PayFunction() {
		CN = HotelDB.dbConnection();
	}
	//윤영님 보너스 매서드
	public void bonus(int login_user_id, int roomnum) {
	      double userpoint = userbonus(login_user_id);
	      double roomprice = roomPrice(roomnum);
	      double bonusRatio =0.01;
	      double bonusPoint = roomprice * bonusRatio;
	      
	      userpoint += bonusPoint ;
	   
	      try {
	         msg = "update customer set points = " + userpoint + "where id =" + login_user_id ;
	         ST = CN.createStatement();
	         RS = ST.executeQuery(msg);
	         
	         while(RS.next()==true) {
	        	System.out.println(login_user_id + "님 예약완료되셨습니다!");
	            System.out.println(bonusPoint +"포인트 적립");
	         }
	      }catch(Exception ex) {}
	      
	  }//bonus end
	
	
	
	public double userbonus (int login_user_id) {
	      double point = 0;
	      try {
	         msg = "select points from customer where id =" + login_user_id ;
	         ST= CN.createStatement();
	         RS = ST.executeQuery(msg);
	         
	         if(RS.next()==true) {
	            point = RS.getInt("points");
	         }
	      }catch(Exception ex) {}
	      return point;
	   }
	
	
	public int roomPrice(int roomnum) {
		int price = 0;
		try {
			msg = "select price from room where roomnum="+ roomnum;
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			
			if(RS.next()) {
				price  = RS.getInt("price");
			}
		
		}catch(Exception ex) {System.out.println("error2");}
		return price;
	}
	
	
	public void PayCancle(int id, int roomnum) {//제거
		try {
			msg = "delete from reservation where id = ? and roomnum = ?";
			PST = CN.prepareStatement(msg);
				PST.setInt(1, id );
				PST.setInt(2,roomnum);
				
			int success =  PST.executeUpdate();
			if (success != 0) {
				System.out.println("결제 취소");
			}else {
				System.out.println("error");
			}
			
		}catch(Exception ex) {System.out.println("Error"+ex);}
	   
	}
	
	
	

}
