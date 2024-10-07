package temp;

public class Car4Example {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Car4 myCar = new Car4();
		myCar.keyTurnOn();
		myCar.run();
		int speed = myCar.getSpeed();
		System.out.println("현재 속도: " + speed + "km/h");
	}

}
