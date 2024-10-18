package MainApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;


public class Reservation {
	Scanner sc = new Scanner(System.in);
	List<String[]> movieList = new ArrayList<>();
	File movies = new File("C:/Temp/movies.txt");
	File reservations = new File("C:/Temp/reservations.txt");
	
	public void reservationSelect(String loginID) throws Exception{
		boolean reservationEnable = true;
		
		if(movies.exists() == false) {movies.createNewFile();}
		if(reservations.exists() == false) {reservations.createNewFile();}
		while(reservationEnable) {
		try {
			BufferedReader br = new BufferedReader(new FileReader("C:/Temp/movies.txt"));
			String line;
			movieList.clear();
						
			while((line=br.readLine())!= null) {
				String[] movieData = line.split(",");
				movieList.add(movieData);
			}
			br.close();
			for(int i = 0; i < movieList.size(); i++) {
				System.out.println("[" + movieList.get(i)[0] +"]: " + movieList.get(i)[1] +"("+ movieList.get(i)[2]+")");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
			System.out.println();
			System.out.print("예매할 영화를 선택하세요: ");
			String input = sc.nextLine();
			for(int i = 0; i < movieList.size(); i++) {
				if(movieList.get(i)[0].contains(input)) {
					reservationEnable = false;
					seatSelect(input, i, loginID);
					break;
				}
			}
			if(input.equals(""))
			System.out.println("선택한 영화는 없는 영화입니다. 다시선택하십시오.");
		}
	}
	
	public void seatSelect(String movieID, int index, String loginID) throws Exception{
		Seats seats = new Seats();
		System.out.println("선택된 영화는 " + movieList.get(index)[1] + " 입니다.");
		seats.seatView(movieID);
		System.out.print("좌석을 선택하세요(예: E-9): ");
		String input = sc.nextLine();
		Date date = new Date();
		long resnum = date.getTime();
		FileWriter fw = new FileWriter("C:/Temp/reservations.txt", true);
		fw.write(resnum + "," + movieList.get(index)[0] + "," + movieList.get(index)[1] + "," + input +  "," + loginID + "\n");
		fw.flush();
		fw.close();
		System.out.println("예매가 완료되었습니다.");
		System.out.println("발급번호: " + resnum);
	}
	
	public void inquiryReservation() {
		boolean notSearch = true;
		System.out.print("발급번호를 입력하세요: ");
		String input = sc.nextLine();
		try {
		BufferedReader br = new BufferedReader(new FileReader("C:/Temp/reservations.txt"));
		String line;
					
		while((line=br.readLine())!= null) {
			String[] resData = line.split(",");
			if(resData[0].equals(input)) {
				System.out.println("[확인 완료] 영화: "+ resData[2]+", 좌석: " + resData[3]);
				notSearch = false;
			}
		}
			br.close();
			if(notSearch)
				System.out.println("발급번호가 올바르지 않거나 예매된 사항이 존재하지 않습니다.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void reservationCancle() {
		File tempfile = new File("C:/Temp/tempfile.txt");
		boolean notSearch = true;
		System.out.print("발급번호를 입력하세요: ");
		String input = sc.nextLine();
		try {
		BufferedReader br = new BufferedReader(new FileReader(reservations));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempfile));
		String line;
					
		while((line=br.readLine())!= null) {
			String[] resData = line.split(",");
			if(!resData[0].equals(input)) {
				bw.write(line);
				bw.newLine();
				bw.flush();
			} else {
				System.out.println("[취소 완료] 영화: "+ resData[2]+", 좌석: " + resData[3] +"의 예매가 취소되었습니다.");
				notSearch = false;
			}
		}
			br.close();
			bw.close();
			
			if(notSearch) {
				System.out.println("발급번호가 올바르지 않거나 예매된 사항이 존재하지 않습니다.");
				tempfile.delete();
			} else {
				if(!reservations.delete()) {
					System.out.println("원본 파일을 삭제하지 못했습니다.");
				}else {
					try{Files.move(tempfile.toPath(), reservations.toPath()); }
					catch (IOException e){
						System.out.println("예약리스트 갱신을 실패했습니다.");
						e.printStackTrace();
					}
				}
			}
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
