package GradeManagement;

import java.util.Scanner;

import BoardExample.BoardDAO;

public class MainPage {
	public void mainMenu() {
		GradeDAO GDAO = new GradeDAO();
		AccountDAO ADAO = new AccountDAO();
		Scanner sc = new Scanner(System.in);
		String input = "";
		String memberType = "guest";
		String loginID = "";
		String subject = "";
		boolean ProgramEnable = true;
		
		while(ProgramEnable) {
			System.out.println("=====================");
			System.out.println("  성적관리 프로그램	     ");
			System.out.println("=====================");
			System.out.println("로그인 유형: " + memberType);
			if(memberType.equals("student")){
				System.out.println("1: 성적 조회");
				System.out.println("3: 개인정보 수정");
				System.out.println("4: 로그아웃");
			} else if(memberType.equals("teacher")) {
				System.out.println("담당 과목: " + subject);
				System.out.println("1: 성적 관리");
				System.out.println("2: 학생 관리 ");
				System.out.println("3: 개인정보 수정");
				System.out.println("4: 로그아웃");
			} else if (memberType.equals("admin")){
				System.out.println("1: 성적 관리");
				System.out.println("2: 학생 관리 ");
				System.out.println("3: 개인정보 수정");
				System.out.println("4: 로그아웃");
			} else {
				System.out.println("1: 로그인");
				System.out.println("2: 회원 가입");	
			}
			
			System.out.println("q: 종료");
			System.out.print("메뉴를 선택하세요:");
			input = sc.nextLine();
			
			switch(input) {
			case "1":
				if(memberType.equals("student")){
					
				} else if(memberType.equals("teacher")) {
					boolean selectloop = true;
					while(selectloop) {
					System.out.println("------------------------------------------------------------------");
					System.out.println("성적관리 메뉴: 1.학생성적 조회 | 2. 학생성적 입력 | 3. 나가기 ");
					System.out.println("------------------------------------------------------------------");
					System.out.print("메뉴선택: ");
					String input2 = sc.nextLine();
					switch(input2) {
					case "1":
//						GDAO.
						break;
					case "2":
						System.out.println("------------------------------------------------------------------");
						System.out.println(" 학생 성적 입력중 입니다. 안내에 맞춰서 내용을 적어주시기 바랍니다.");
						System.out.println(" 현재 " + subject + "과목의 성적을 입력중 입니다.");
						System.out.println("------------------------------------------------------------------");
						System.out.print("학생 이름: ");
						String stuname = sc.nextLine();
						System.out.print("점수: ");
						String score = sc.nextLine();
						GDAO.grade_insert(subject, score, stuname, loginID);
						selectloop = false;
						break;
					case "3":
						selectloop = false;
						break;
					}
				}
				} else if (memberType.equals("admin")){
					
				} else {
					LoginDAO LDAO = new LoginDAO();
					System.out.print("아이디: ");
					String id = sc.nextLine();
					System.out.print("비밀번호: ");
					String password = sc.nextLine();
					int result = LDAO.login(id, password);
					if(result == 3 || result == 4) {
						loginID = "guest";
					} else {
						loginID = id;
						if(result == 1) {
							memberType = "student";
						}
						if(result == 2) {
							memberType = "teacher";
							subject = LDAO.getPosition(id);
						}
						if(result == 5) {
							memberType = "admin";
						}
					}
				}
				break;
			case "2":
				
				if(memberType.equals("student")){
					
				} else if(memberType.equals("teacher")) {
					
				} else if (memberType.equals("admin")){
					
				} else {
					System.out.println("-----------------------------------------------------------------------");
					System.out.println(" 성적 관리 프로그램 회원가입에 오신것을 환영합니다! 안내에 맞춰서 내용을 적어주시기 바랍니다.");
					System.out.println("-----------------------------------------------------------------------");
					System.out.print("아이디: ");
					String id = sc.nextLine();
					System.out.print("비밀번호: ");
					String password = sc.nextLine();
					System.out.print("이름: ");
					String name = sc.nextLine();
					System.out.print("핸드폰번호: ");
					String phoneNum = sc.nextLine();
					System.out.print("이메일: ");
					String email = sc.nextLine();
					System.out.print("가입유형(학생: S, 선생님: T): ");
					String membertype = sc.nextLine();
					String position = "";
					String stulevel = "";
					String stuclass = "";
					if(membertype.equals("T")) {
						System.out.print("담당 과목: ");
						position = sc.nextLine();
						stulevel = "0";
						stuclass = "0";
					} else {
						System.out.print("학년: ");
						stulevel = sc.nextLine();
						System.out.print("반: ");
						stuclass = sc.nextLine();
						position = "";
					}
					ADAO.page_account(id,password,name,stulevel,stuclass,position,phoneNum,email,membertype);
					
				}
				break;
			case "3":
				System.out.println("------------------------------------------------------------------");
				System.out.println(loginID +" 개인정보 수정 페이지 수정할 내용만 적으십시오.");
				System.out.println("------------------------------------------------------------------");
				System.out.print("비밀번호: ");
				String password = sc.nextLine();
				System.out.print("이름: ");
				String name = sc.nextLine();
				System.out.print("이메일: ");
				String email = sc.nextLine();
				ADAO.edit_profile(loginID, password, name, email);
				break;
			case "4":
				memberType = "guest";
				loginID = "";
				break;
			case "q":
				System.out.println("프로그램을 종료합니다.");
				ProgramEnable = false;
				break;
			}
		}
	}
}