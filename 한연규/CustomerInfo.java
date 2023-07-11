package net.hb.project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CustomerInfo {
	private int login_id;
	private int login_pwd;
	private String login_name;
	
	public static void main(String[] args) {
		//HotelFunction join, login 등의 method 모음
		CustomerFunction CF = new CustomerFunction();
		CustomerInfo CI = new CustomerInfo();
		
		//Customer class 호출 = gettersetter 호출
		Customer CO = new Customer();
		
		//while 구문에 필요한 변수들 = 1.회원가입 2.로그인 9.종료 등에 필요한 변수
		Scanner sc = new Scanner(System.in);	
		
		int id; int pwd; String name;
		int sel = 9; boolean next_page = false;
		boolean isEnded = false;
		
		//고객관리 페이지 (회원가입 , 로그인 , 탈퇴 )
		while(!isEnded) {
			System.out.println("1.회원가입 2.로그인 3.회원탈퇴  9.종료");
			sel = Integer.parseInt(sc.nextLine());
			if (sel == 9) {
				isEnded = true;
			}
			switch (sel) {
			case 1:
				//회원가입
				System.out.print("id");
				id = Integer.parseInt(sc.nextLine());
				System.out.print("pwd");
				pwd = Integer.parseInt(sc.nextLine());
				System.out.print("name");
				name = sc.nextLine();
				
				CO.setCid(id);
				CO.setCpw(pwd);
				CO.setCname(name);
				
				CF.join(CO);
				break;	
			
			
			case 2:
				//로그인
				System.out.print("id");
				id = Integer.parseInt(sc.nextLine());
				System.out.print("pwd");
				pwd = Integer.parseInt(sc.nextLine());
				CI.login_id = id;
				CI.login_pwd = pwd;
				next_page =CF.login(id, pwd);
				break;
				
			case 3:
				//탈퇴
				System.out.print("id");
				id = Integer.parseInt(sc.nextLine());
				System.out.print("pwd");
				pwd = Integer.parseInt(sc.nextLine());
				
				CO.setCid(id);
				CO.setCpw(pwd);
				
				CF.terminate(CO);
				break;
			
			default :
				return;
			}
			
			//로그인 성공 시 예약 페이지로 넘어감
			//로그인 한 고객의 id와 password가 유지됨
			if (next_page ==true) {
				ReservationInfo RI = new ReservationInfo( CI.login_id, CI.login_pwd);
			}
			
		}
		
	}

}
