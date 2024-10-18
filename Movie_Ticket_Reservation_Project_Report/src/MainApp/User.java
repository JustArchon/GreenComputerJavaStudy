package MainApp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class User {
	Scanner sc = new Scanner(System.in);
	File userLists = new File("C:/Temp/userlist.txt");
	
	public void userRegister() throws Exception{
		boolean registerEnable = true;
		boolean sameID = false;
		if(userLists.exists() == false) {userLists.createNewFile();}
		
		System.out.println("회원가입에 오신걸 환영합니다. 가입할 아이디와 비밀번호를 입력하세요.");
		while(registerEnable) {
			sameID = false;
			System.out.print("아이디: ");
			String id = sc.nextLine();
			try {
				BufferedReader br = new BufferedReader(new FileReader("C:/Temp/userlist.txt"));
				String line;
							
				while((line=br.readLine())!= null) {
					String[] usrData = line.split(",");
					if(usrData[0].equals(id)) {
						sameID = true;
					}
				}
					br.close();
					if(sameID) {
						System.out.println("중복된 아이디가 존재합니다. 다시 입력해주세요.");
					} else {
						System.out.print("비밀번호: ");
						String passwd = sc.nextLine();
						FileWriter fw = new FileWriter("C:/Temp/userlist.txt", true);
						fw.write(id + "," + passwd +"\n");
						fw.flush();
						fw.close();
						System.out.println("회원가입 하신것을 환영합니다. 메인화면에서 로그인 바랍니다.");
						registerEnable = false;
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
		}

	}
	
	public boolean userLogin(String id, String passwd) throws Exception{
		if(userLists.exists() == false) {userLists.createNewFile();}
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/Temp/userlist.txt"));
			String line;
						
			while((line=br.readLine())!= null) {
				String[] usrData = line.split(",");
				if(usrData[0].equals(id) && usrData[1].equals(passwd)) {
					br.close();
					return true;
				}
			}
				br.close();
				return false;
			} catch(Exception e) {
				e.printStackTrace();
				return false;
			}
	}
}
