package BoardExample;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import SQL.Board;

public class BoardExample {
	public void list() throws Exception {
		BoardListDAO BLDAO = new BoardListDAO();
		List<Board> boardlist = new ArrayList<>();
		boardlist = BLDAO.getBoardList();
		System.out.println();
		System.out.println("[게시물 목록]");
		System.out.println("------------------------------------------------------------------");
		System.out.printf("%-6s%-12s%-16s%-40s\n", "no", "wirter", "date", "title");
		System.out.println("------------------------------------------------------------------");
		for(int i = 0; i<boardlist.size(); i++) {
			System.out.printf("%-6s%-12s%-16s%-40s \n", boardlist.get(i).getBno(), boardlist.get(i).getBwriter(), boardlist.get(i).getBdate(), boardlist.get(i).getBtitle());
		}
		
	}
	
	public void mainMenu() throws Exception {
		Scanner sc = new Scanner(System.in);
		BoardDAO BDAO = new BoardDAO();
		AccountDAO ADAO = new AccountDAO();
		String loginID = "guest";
		boolean looping = true;
		while(looping) {
		list();
		System.out.println("");
		System.out.println("------------------------------------------------------------------");
		if(loginID.equals("guest")) {
			System.out.println("메인메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit | 5.Login | 6.Account");
		} else {
			System.out.println("메인메뉴: 1.Create | 2.Read | 3.Clear | 4.Exit | 5.LogOut | 6.Profile_Edit | 반갑습니다 " + loginID + "님");
		}
		System.out.print("메뉴선택: ");
		String input = sc.nextLine();
		System.out.println();
		switch(input) {
		case "1":
			BDAO.writeBoard();
			break;
		case "2":
			System.out.print("읽을 게시글 번호선택: ");
			String input2 = sc.nextLine();
			boolean readloop = true;
			while(readloop) {
			BDAO.readBoard(Integer.parseInt(input2));
			System.out.println("------------------------------------------------------------------");
			System.out.println("Read 메뉴: 1.Update | 2.Delete | 3.Exit ");
			System.out.println("------------------------------------------------------------------");
			System.out.print("메뉴선택: ");
			String input3 = sc.nextLine();
			switch(input3) {
			case "1":
				BDAO.updatePost(Integer.parseInt(input2));
				break;
			case "2":
				BDAO.deletePost(Integer.parseInt(input2));
				readloop = false;
				break;
			case "3":
				readloop = false;
				break;
			}
		}
			break;
		case "3":
			BDAO.clearBoard();
			break;
		case "4":
			System.out.println("게시판 앱을 종료합니다.");
			looping = false;
			break;
		case "5": // 로그인, 로그아웃
			if(loginID.equals("guest")) {
				System.out.print("아이디: ");
				String id = sc.nextLine();
				System.out.print("비밀번호: ");
				String password = sc.nextLine();
				int result = ADAO.page_login(id, password);
				if(result == 0) {
					loginID = id;
				}
			} else {
				loginID = "guest";
			}
			break;
		case "6": // 회원가입, 회원정보수정
			if(loginID.equals("guest")) {
				System.out.println("------------------------------------------------------------------");
				System.out.println(" 게시판 회원가입에 오신것을 환영합니다! 안내에 맞춰서 내용을 적어주시기 바랍니다.");
				System.out.println("------------------------------------------------------------------");
				System.out.print("아이디: ");
				String id = sc.nextLine();
				System.out.print("비밀번호: ");
				String password = sc.nextLine();
				System.out.print("이름: ");
				String name = sc.nextLine();
				System.out.print("나이: ");
				String age = sc.nextLine();
				System.out.print("이메일: ");
				String email = sc.nextLine();
				System.out.print("주소: ");
				String address = sc.nextLine();
				ADAO.page_account(id, password, name, age, email, address);
			} else {
				System.out.println("------------------------------------------------------------------");
				System.out.println("Profile 메뉴: 1.Edit | 2.Delete | 3.Exit ");
				System.out.println("------------------------------------------------------------------");
				System.out.print("메뉴선택: ");
				String input3 = sc.nextLine();
				switch(input3) {
				case "1":
					System.out.println("------------------------------------------------------------------");
					System.out.println(loginID+" 개인정보 수정 페이지 수정할 내용만 적으십시오.");
					System.out.println("------------------------------------------------------------------");
					System.out.print("비밀번호: ");
					String password = sc.nextLine();
					System.out.print("이름: ");
					String name = sc.nextLine();
					System.out.print("나이: ");
					String age = sc.nextLine();
					System.out.print("이메일: ");
					String email = sc.nextLine();
					System.out.print("주소: ");
					String address = sc.nextLine();
					ADAO.edit_profile(loginID, password, name, age, email, address);
					break;
				case "2":
					System.out.println("------------------------------------------------------------------");
					System.out.println(loginID+"의 계정을 탈퇴합니다. 비밀번호를 입력하십시오.");
					System.out.println("------------------------------------------------------------------");
					System.out.print("비밀번호: ");
					String checkpassword = sc.nextLine();
					if(ADAO.page_login(loginID, checkpassword) == 0) {
						System.out.println("정말로 회원을 탈퇴하시겠습니까? 탈퇴하시려면 yes를 입력하십시오.");
						String check = sc.nextLine();
						if(check.equals("yes")) {
							ADAO.delete_account(loginID);
							loginID = "guest";
						}
					} else {
						System.out.println("비밀번호가 일치하지 않습니다.");
					}
					
					
					break;
				case "3":
					break;
				}
			}
			break;
		default:
			System.out.println("잘못된 입력입니다.");
			break;
			}
		}	
	}
}
