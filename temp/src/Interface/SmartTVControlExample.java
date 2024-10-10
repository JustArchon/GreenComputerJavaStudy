package Interface;

public class SmartTVControlExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoteControl rc;
		
		rc = new SmartTelevision();
		
		rc.turnOn();
		rc.setVolume(0);
		rc.turnOff();
	}

}
