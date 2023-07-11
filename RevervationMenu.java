package net.hb.project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Date;
import java.util.HashMap;

public class RevervationMenu {
	private String msg;
	private Connection CN;
	private Statement ST;
	private ResultSet RS;
	private Scanner sc = new Scanner(System.in);
	private PreparedStatement PST = null;
	private boolean payflag = false;
	
	public RevervationMenu() {
		try {
			CN = HotelDB.dbConnection();
		} catch (Exception ex) { System.out.println("Error" + ex);}
	}// 기본생성자 DB연결 
	
	public boolean isDuplicated(int roomNumber) {//예약 중복 체크
		try {
			msg = "select * from reservation where roomnum= "+ roomNumber;
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			if(RS.next()) {
				return true;
			}else {
				return false;
			}
		}catch(Exception ex)  { System.out.println("Error" + ex);}
		return false;
	}//isDuplicated END
	
	
	
	public boolean checkIn(Reservation RV) {//입실
		try {
			msg = "insert into reservation values(?,?,?,?)";
			PST = CN.prepareStatement(msg);
				PST.setInt(1, RV.getRoom_num());
				PST.setInt(2, RV.getId());
				PST.setString(3, RV.getStart_date() );
				PST.setString(4, RV.getEnd_date());
			int checkInSuccess = PST.executeUpdate();
			if(checkInSuccess > 0 ) {
				payflag = true;
				System.out.println(RV.getId() +"님 결재 창으로 넘어갑니다 ");
			}else {
				payflag = false;
				System.out.println(RV.getId() +"님 이미 예약된 방입니다");
			}		
		}
		catch(Exception ex){ System.out.println("Error" + ex);}
		return payflag;
	}// checkIn END
	
	
	public void checkOut(int reservedRoomNumber) {//퇴실
		try {
			msg = "delete from reservation where roomnum =?";
			PST = CN.prepareStatement(msg);
				PST.setInt(1, reservedRoomNumber);
			int checkOutSuccess = PST.executeUpdate();	
			if(checkOutSuccess > 0 )
				System.out.print("퇴실처리되었습니다.");
			else
				System.out.print("이미 퇴실처리된 방입니다");
			System.out.println();
		}
		catch(Exception ex){ System.out.println("Error" + ex);}
	}// checkOut END
	
	public ArrayList<Reservation> reservationStatus() {//전체 예약자, 예약 방 조회
		ArrayList<Reservation> reservationList = new ArrayList<>();
		try {
			msg = "select * from reservation order by roomnum";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			while(RS.next()==true) {
				Reservation RV = new Reservation();
				RV.setRoom_num(RS.getInt("roomnum"));
				RV.setId(RS.getInt("id"));
				RV.setStart_date(RS.getString("start_date"));
				RV.setEnd_date(RS.getString("end_date"));
	
				reservationList.add(RV);
			}
			
		}
		catch(Exception ex){ System.out.println("Error" + ex);}
		return reservationList;
	}// reservationStatus END
	
	// 윤영님, 나리님 id 예약조회
	public void userRoom(int find) {// 예약자 id를 통한 조회
		try {
			int id = find;
			msg = "select * from reservation where id ="+id;
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			
			System.out.println("아이디\t호실\t입실날짜\t퇴실날짜");
			while(RS.next() ==true) {
				int num =  RS.getInt("roomnum");
				String sDate = RS.getString("start_Date");
				String eDate = RS.getString("end_Date");
				System.out.println(id+"\t"+num+"\t"+sDate+"\t"+eDate);
			}
			
		}catch(Exception ex) {System.out.println("error");}
	}
	
	public ArrayList<Room> Search() {//예약되지 않은 방 조회
		ArrayList<Room> alist = new ArrayList<>();
		try {
			//윤영님 작성 코드
			msg ="select * from room where roomnum not in (select roomnum from reservation) order by roomnum";
			ST = CN.createStatement();
			RS = ST.executeQuery(msg);
			
			while(RS.next()) {
				Room RO = new Room();
				int roomnum = RS.getInt("roomnum");
				int price = RS.getInt("price");
				String type = RS.getString("type");
				RO.setRoomnum(roomnum);
				RO.setPrice(price);
				RO.setType(type);
				alist.add(RO);
			}
			
		}catch(Exception ex) {System.out.println("Errsor"+ex);}  
		
		return alist;
		
	}
	
	public void EmptyRoom() {
		System.out.println("이코노미 공실");
		for (Room r: Search()) {
			if(r.getType().equals("a"))
			System.out.print(r.getRoomnum()+ " ");
		}
		System.out.println("\n");
		System.out.println("비지니스 공실");
		for (Room r: Search()) {
			if(r.getType().equals("b"))
			System.out.print(r.getRoomnum()+ " ");
		}
		System.out.println("\n");
		System.out.println("퍼스트 공실");
		for (Room r: Search()) {
			if(r.getType().equals("c"))
			System.out.print(r.getRoomnum()+ " ");
		}
		System.out.println("\n");
	}
	
}

