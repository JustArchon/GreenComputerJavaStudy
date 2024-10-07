package temp;

public class Car6 {
	int speed;
	
	void run() {
		System.out.println(speed + "으로 달립니다.");
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car6 myCar = new Car6();
		myCar.speed = 60;
		myCar.run();
	}

}
