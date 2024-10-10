package Interface;

public class RemoteControlExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RemoteControl rc = null;
		rc = new Television();
		rc = new Audio();
		
		RemoteControl.changeBattery();
		
		rc = new Television();
		rc.turnOn();
		rc.setMute(true);
		
		rc = new Audio();
		rc.turnOn();
		rc.setMute(true);
		
//		rc = new SmartTelevision();
//		rc.setVolume(5);
//		
//		rc.turnOn();
//		rc.setVolume(0);
//		rc.turnOff();
	}

}
