package temp;

public class ContinueExample {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
//		System.out.println("---------- 짝수 -----------");
		System.out.println("---------- 홀수 -----------");
		for(int i =1; i<= 10; i++) {
			if(i%2 == 0) {
				continue;
			}
			System.out.println(i);
		}
	}

}
