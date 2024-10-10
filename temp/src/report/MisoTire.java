package report;

public class MisoTire extends Tire {
	//필드
	//생성자
	public MisoTire(String location, int maxRotation) {
		super(location, maxRotation);
	}	
	//메소드
	@Override
	public boolean roll() {
		++accumulatedRotation;		
		if(accumulatedRotation<maxRotation) {
			System.out.println(location + " MisoTire 수명: " + (maxRotation-accumulatedRotation) + "회");
			return true;
		} else {
			System.out.println("*** " + location + " MisoTire 펑크 ***");
			return false;
		}
	}
}
