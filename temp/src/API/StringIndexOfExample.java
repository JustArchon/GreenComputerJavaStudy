package API;

public class StringIndexOfExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String subject = "자바 프로그래밍";
		String subject = "웹 개발자";
		
//		int location = subject.indexOf("프로그래밍");
		int location = subject.indexOf("개발자");
		System.out.println(location);
		
		if(subject.indexOf("자바") != -1) {
			System.out.println("자바와 관련된 책이군요");
		} else if(subject.indexOf("개발자") != -1) {
			System.out.println("개발자와 관련된 책이군요");
		} else {
			System.out.println("자바,개발자와 관련없는 책이군요.");
		}
	}

}
