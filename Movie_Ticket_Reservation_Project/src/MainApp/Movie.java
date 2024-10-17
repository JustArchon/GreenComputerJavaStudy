package MainApp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.time.Instant;

public class Movie {
	Scanner sc = new Scanner(System.in);
	List<String[]> movieList = new ArrayList<>();
	File movies = new File("C:/Temp/movies.txt");
	
	public void adminMovieAdd() throws Exception{
		if(movies.exists() == false) {movies.createNewFile();}
		System.out.println("등록할 영화의 정보를 입력하세요.");
		System.out.print("제목:");
		String input = sc.nextLine();
		System.out.print("장르:");
		String input2 = sc.nextLine();
		long movnum = Instant.now().getEpochSecond();
		FileWriter fw = new FileWriter("C:/Temp/movies.txt", true);
		fw.write(movnum + "," + input + "," + input2 +"\n");
		fw.flush();
		fw.close();
		System.out.println("등록되었습니다.");
	}
	
	public void adminMovieListView() throws Exception{
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
	}
	
	public void adminMovieDelete() throws Exception{
		File tempfile = new File("C:/Temp/tempfile.txt");
		boolean notSearch = true;
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
		System.out.print("삭제할 영화를 선택하세요: ");
		String input = sc.nextLine();
		try {
		BufferedReader br = new BufferedReader(new FileReader(movies));
		BufferedWriter bw = new BufferedWriter(new FileWriter(tempfile));
		String line;
					
		while((line=br.readLine())!= null) {
			String[] movData = line.split(",");
			if(!movData[0].equals(input)) {
				bw.write(line);
				bw.newLine();
				bw.flush();
			} else {
				System.out.println("삭제되었습니다.");
				notSearch = false;
			}
		}
			br.close();
			bw.close();
			
			if(notSearch) {
				System.out.println("영화번호가 올바르지 않습니다.");
				tempfile.delete();
			} else {
				if(!movies.delete()) {
					System.out.println("원본 파일을 삭제하지 못했습니다.");
				}else {
					try{Files.move(tempfile.toPath(), movies.toPath()); }
					catch (IOException e){
						System.out.println("영화리스트 갱신을 실패했습니다.");
						e.printStackTrace();
					}
				}
			}
				
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
