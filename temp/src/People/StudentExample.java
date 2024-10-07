package People;

public class StudentExample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student student = new Student("홍길동", "123456-1234567",1);
		Student student2 = new Student("강규진", "990525-1983293",2);
		printStudentInfo(student);
		printStudentInfo(student2);
		
	}
	
	public static void printStudentInfo(Student student) {
		System.out.println("name : " + student.name);
		System.out.println("ssn : " + student.ssn);
		System.out.println("studentNo : " + student.studentNo);
	}

}
