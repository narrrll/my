package net.hb.day0616;

import java.util.Scanner;
import java.util.Date;

public class Game {
	//필드, 생성자
	//method 3개 이상 구현
	//난수발생 Math.random(), Random r = new Random();
	//총 게임, 승, 패, 무
	//Scanner 예외처리 포함
	//게임시간 2023-06-16-금요일-시:분:초
	//for 반복문 if문 사용
	public static void main(String[] args) {
		int my = 0;
		double d = Math.random()*3;
		int com = (int)d+1;
		int tot = 0;
		int win=0, lose=0, mu=0;
		
		Scanner sc = new Scanner(System.in);
		Date wdate = new Date();
		
	while(true) {
		try {
			System.out.println("가위:1 바위:2 보:3");
			System.out.print("가위바위보에 해당하는 숫자를 입력하세요 >>> ");
			my = Integer.parseInt(sc.nextLine());
			
			if(my==1) {
				if(com==2) {System.out.println("컴퓨터:바위 / 나:가위 => 졌습니다");}
				else if(com==3) {System.out.println("컴퓨터:보 / 나:가위 => 이겼습니다");}
				else if(com==1) {System.out.println("컴퓨터:가위 / 나:가위 => 비겼습니다");}
				else {System.out.println("다시 입력하세요 >>> ");}
			}
			if(my==2) {
				if(com==3) {System.out.println("컴퓨터:보 / 나:바위 => 졌습니다");}
				else if(com==1) {System.out.println("컴퓨터:가위 / 나:바위 => 이겼습니다");}
				else if(com==2) {System.out.println("컴퓨터:바위 / 나:바위 => 비겼습니다");}
				else {System.out.println("다시 입력하세요 >>> ");}
			}
			if(my==3) {
				if(com==1) {System.out.println("컴퓨터:가위 / 나:보 => 졌습니다");}
				else if(com==2) {System.out.println("컴퓨터:바위 / 나:보 => 이겼습니다");}
				else if(com==3) {System.out.println("컴퓨터:보 / 나:보 => 비겼습니다");}
				else {System.out.println("다시 입력하세요 >>> ");}
			}
		}catch(Exception e) {}
		
		tot++;
		if((my == 1 && com == 3) || (my == 2 && com == 1) || (my == 3 && com == 2)) {win++;}
		else if((my == 1 && com == 2) || (my == 2 && com == 3) || (my == 3 && com == 1)) {lose++;}
		else if((my == 1 && com == 1) || (my == 2 && com == 2) || (my == 3 && com == 3)) {mu++;}
		System.out.println("총게임수 : "+tot+"게임 "+win+"승 "+lose+"패 "+mu+"무");
		System.out.println(wdate.toLocaleString());
		System.out.println();
		
	}//while end
	
	}//main end
}//class end
