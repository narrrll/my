package net.hb.day0616;

public class Son extends Father{ //상속받은 자식클래스 subclass
	public static void main(String[] args) {
		Son sn = new Son();
		sn.display();
	}
	
	@Override //재정의 생략가능
	public void bank() {
		System.out.println("Son class bank");
		System.out.println("0원");
	}

	public void display() { //non-static
		super.bank(); //부모 클래스 접근 (extends Father)
		this.bank(); //자식 클래스 접근
		//super,this는 static에서 사용불가
		System.out.println();
		System.out.println("Son class display");
	}
}
