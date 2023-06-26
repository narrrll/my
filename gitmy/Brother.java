package net.hb.day0616;

//final 클래스 상속불가
public final class Brother {
	public static void main(String[] args) {
		System.out.println("Brother class main method");
	}
	
	//생성자 중복 = Overloading /타입,개수가 달라야함
	public Brother() {}
	public Brother(String a) {}
	public Brother(String a,int b) {}
	public Brother(double c) {}
	
	//메소드 중복 = Overloading /타입,개수가 달라야함
	public void shop() {System.out.println("shop()");}
	public void shop(String baemin) {}
	public void shop(boolean coupang) {}
	public void shop(String kurly, double gmarket) {}
}
