package package1;

public class B {
	public B() {
	A a = new A();
	a.field1 = 1;
	a.field2 = 1;
	// a.field3 = 1; // private이기 때문에 접근불가
	
	a.method1();
	a.method2();
	//a.method3(); // private이기때문에 접근불가
	}
}
