package API;

import java.io.*;

public class KeyBoardToStringExample {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		byte[] bytes = new byte[100];
		
		System.out.println("입력: ");
		int readByteNo = System.in.read(bytes);
		
		String str = new String(bytes, 0, readByteNo-2);
		System.out.println(str);
	}

}
