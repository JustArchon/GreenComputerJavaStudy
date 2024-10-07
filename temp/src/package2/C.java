package package2;

import package1.A;

public class C {
	public C() {
		A a = new A();
		a.field1 = 1;
		//a.field2 = 1; //default 접근자로써 외부패키지에서 접근 불가능
		//a.field3 = 1; //private 접근자로써 불가능
		
		a.method1();
		//a.method2(); //default 접근자로써 외부패키지에서 접근불가능
		//a.method3(); //private 접근자로써 불가능
		
		/****************
		 
		 a.field 2,3 a.method 2,3이 접근이 불가능한 이유
		 field3, method3의 경우 private 접근제한이 걸려있다보니 외부에서 접근이
		 불가능한 메소드 및 변수입니다.
		 field2, method2의 경우는 아무지정자도 지정하지 않았으며
		 이는 default의 접근제한이 적용됩니다. 이럴 경우 동일 패키지내에서는
		 접근이 가능하지만 외부 패키지 및 하위 클래스에서는 해당 메소드 및 변수를
		 접근 할 수 없습니다.
		 
		 ****************/
	}
}
