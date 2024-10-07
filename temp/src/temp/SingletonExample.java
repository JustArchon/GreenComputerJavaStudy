package temp;

public class SingletonExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 Singleton obj1 = new Singleton();
		 Singleton obj2 = new Singleton();
		 */
		
		Singleton obj1 = Singleton.getInstance();
		Singleton obj2 = Singleton.getInstance();
		
		if(obj1 == obj2) {
			System.out.println("같은 Singletone 객체 입니다.");
		} else {
			System.out.println("다른 Singletone 객체 입니다.");
		}
	}

}
