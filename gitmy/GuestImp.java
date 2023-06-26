package net.hb.day0616;

import java.util.Date;

public interface GuestImp {
	//interface는 메소드를 선언만 함
	//interface 상속키워드 implements
	//abstract 키워드 생략가능
	//interface에 static/default 함수구현
	public abstract void insert();
	public abstract String login();
	public abstract Date getWDate();
}
