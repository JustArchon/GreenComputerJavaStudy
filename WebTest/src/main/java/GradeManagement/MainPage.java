package GradeManagement;

import java.util.Scanner;

import BoardExample.BoardDAO;

public class MainPage {
	public void mainMenu() throws Exception {
		GradeDAO GDAO = new GradeDAO();
		AccountDAO ADAO = new AccountDAO();
		MemberDAO MDAO = new MemberDAO();
		SubjectDAO SDAO = new SubjectDAO();
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
				System.out.println("2: 교직원 및 학생 관리 ");
				System.out.println("3: 과목 관리");
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
					GDAO.getPersonalScores(loginID);
					break;
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
						GDAO.getStudentGrade();
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
					boolean selectloop = true;
					while(selectloop) {
					System.out.println("------------------------------------------------------------------");
					System.out.println("성적관리 메뉴: 1.학생성적 조회 | 2. 나가기 ");
					System.out.println("------------------------------------------------------------------");
					System.out.print("메뉴선택: ");
					String input2 = sc.nextLine();
					switch(input2) {
					case "1":
						GDAO.getStudentGrade();
						break;
					case "2":
						selectloop = false;
						break;
					}
				}
					
				} else {
					LoginDAO LDAO = new LoginDAO();
					System.out.print("아이디: ");
					String id = sc.nextLine();
					System.out.print("비밀번호: ");
					String password = sc.nextLine();
					int result = LDAO.login(id, password);
					if(result == 3 || result == 4) {
						memberType = "guest";
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
					boolean selectloop = true;
					while(selectloop) {
					System.out.println("------------------------------------------------------------------");
					System.out.println("학생관리 메뉴: 1.학생 조회 | 2. 나가기 ");
					System.out.println("------------------------------------------------------------------");
					System.out.print("메뉴선택: ");
					String input2 = sc.nextLine();
					switch(input2) {
					case "1":
						MDAO.getStudentList();
						break;
					case "2":
						selectloop = false;
						break;
					}
				}
				} else if (memberType.equals("admin")){
					boolean selectloop = true;
					while(selectloop) {
					System.out.println("------------------------------------------------------------------");
					System.out.println("학생, 교직원 관리 메뉴: 1.조회 | 2.추가 | 3.삭제 | 4. 나가기 ");
					System.out.println("------------------------------------------------------------------");
					System.out.print("메뉴선택: ");
					String input2 = sc.nextLine();
					switch(input2) {
					case "1":
						MDAO.getStudentStaffList();
						break;
					case "2":
						System.out.println("-----------------------------------------------------------------------");
						System.out.println(" 학생 혹은 교직원을 추가합니다. 아래의 내용을 적어주시기 바랍니다.");
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
						System.out.print("추가 유형(학생: S, 선생님: T): ");
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
						break;
					case "3":
						System.out.println("------------------------------------------------------------------");
						System.out.println(" 학생 혹은 교직원을 정보를 삭제합니다. 삭제할 아이디를 입력해주십시오.");
						System.out.println("------------------------------------------------------------------");
							String deleteid = sc.nextLine();
							System.out.print("삭제할 멤버 유형(학생: S, 선생님: T): ");
							String deletemembertype = sc.nextLine();
							System.out.println("정말로 회원을 탈퇴하시겠습니까? 맞으시면 yes를 입력하세요.");
							String check = sc.nextLine();
							if(check.equals("yes")) {
								if(deletemembertype.equals("student")) {
									ADAO.delete_account(deleteid,"S");
								} else {
									ADAO.delete_account(deleteid,"T");
								}
								memberType = "guest";
								loginID = "";
							}
						break;
					case "4":
						selectloop = false;
						break;
					}
				}
					
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
				String memberType2 = "";
				if(memberType.equals("student") || memberType.equals("teacher")){
					System.out.println("------------------------------------------------------------------");
					System.out.println("Profile 메뉴: 1.정보수정 | 2.계정탈퇴 | 3.나가기 ");
					System.out.println("------------------------------------------------------------------");
					System.out.print("메뉴선택: ");
					String input3 = sc.nextLine();
					switch(input3) {
					case "1":
						System.out.println("------------------------------------------------------------------");
						System.out.println(loginID +" 개인정보 수정 페이지 수정할 내용만 적으십시오.");
						System.out.println("------------------------------------------------------------------");
						System.out.print("비밀번호: ");
						String password = sc.nextLine();
						System.out.print("이름: ");
						String name = sc.nextLine();
						System.out.print("휴대전화 번호: ");
						String phonenumber = sc.nextLine();
						System.out.print("이메일: ");
						String email = sc.nextLine();
						if(memberType.equals("student")) {
							memberType2 = "S";
						} else {
							memberType2 = "T";
						}
						ADAO.edit_profile(loginID, password, name, phonenumber, email, memberType2);
						break;
					case "2":
						System.out.println("------------------------------------------------------------------");
						System.out.println(loginID+"의 계정을 탈퇴합니다. 탈퇴하시려면 yes를 입력하십시오.");
						System.out.println("------------------------------------------------------------------");
							System.out.println("정말로 회원을 탈퇴하시겠습니까? ");
							String check = sc.nextLine();
							if(check.equals("yes")) {
								if(memberType.equals("student")) {
									ADAO.delete_account(loginID,"S");
								} else {
									ADAO.delete_account(loginID,"T");
								}
								memberType = "guest";
								loginID = "";
							}
						break;
					case "3":
						break;
					}
				}else if(memberType.equals("admin")) {
					boolean selectloop = true;
					while(selectloop) {
					System.out.println("--------------------------------------------------------------------------");
					System.out.println("과목관리 메뉴: 1.과목 조회 | 2.과목 추가 | 3.과목 삭제 | 4.교직원 과목 변경 | 5. 나가기 ");
					System.out.println("--------------------------------------------------------------------------");
					System.out.print("메뉴선택: ");
					String input2 = sc.nextLine();
					switch(input2) {
					case "1":
						SDAO.getSubjectList();
						break;
					case "2":
						System.out.println("-----------------------------------------------------------------------");
						System.out.println(" 과목을 추가합니다. 안내에 맞춰서 내용을 적어주시기 바랍니다.");
						System.out.println("-----------------------------------------------------------------------");
						System.out.print("과목코드: ");
						String subID = sc.nextLine();
						System.out.print("과목이름: ");
						String subNAME = sc.nextLine();
						SDAO.insertSubject(subID, subNAME);
						break;
					case "3":
						System.out.println("-----------------------------------------------------------------------");
						System.out.println(" 과목을 삭제합니다. 안내에 맞춰서 내용을 적어주시기 바랍니다.");
						System.out.println("-----------------------------------------------------------------------");
						System.out.print("삭제할 과목코드: ");
						String deletesubID = sc.nextLine();
						SDAO.delete_subject(deletesubID);
						break;
					case "4":
						System.out.println("-----------------------------------------------------------------------");
						System.out.println(" 교직원의 담당 과목을 변경합니다. 안내에 맞춰서 내용을 적어주시기 바랍니다.");
						System.out.println("-----------------------------------------------------------------------");
						System.out.print("변경할 교사 ID: ");
						String editTeacherID = sc.nextLine();
						System.out.print("변경할 과목명: ");
						String editsubject = sc.nextLine();
						SDAO.edit_teacherSubject(editTeacherID, editsubject);
						break;
					case "5":
						selectloop = false;
						break;
					}
				}
			}
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

