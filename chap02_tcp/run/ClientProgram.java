package com.kh.chap02_tcp.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//클라이언트용 프로그램
public class ClientProgram {
	
	/*
	 * * Client 측 프로그램 흐름
	 * 1. 요청하고자 하는 서버의 정보를 셋팅하기 (변수로 셋팅)
	 * 2. 서버에 연결 요청을 보내기 (Socket 객체를 생성함으로써)
	 * 3. 수락이 잘 되었다면 Socket 객체가 제대로 생성된 것!!
	 * 4. 서버와 통신할 수 있는 입출력 스트림을 Socket 객체로부터 얻어내기
	 * 5. 보조스트림을 추가하여 성능 개선
	 * 6. 스트림을 통해서 데이터 읽고 쓰기
	 * 7. 통신 종료 절차 (역순으로 반드시)
	 * 
	 * 
	 */
	public static void main(String[] args) {
		
		System.out.println("*** 버디버디 ***");
		// 자원 변수 null로 초기화
		Socket socket = null;
		BufferedReader br= null;
		PrintWriter pw= null;
		
		Scanner sc = new Scanner(System.in);
		try {
		// 1) 요청하고자 하는 서버의 정보 (IP 주소, Port 번호) 셋팅
		// - 요청하고자 하는 서버의 IP 주소 : 127.0.0.1 또는 localhost
		//  ( 127.0.0.1 루프 백 IP, 자기 자신의 IP 주소)
		// - 요청하고자 하는 서버의 Port 번호 : 3000
		String serverIp = "127.0.0.1"; // 또는 "localhost"
		int serverPort = 3000;
		
		// 2) Socket 객체 생성(== 곧 서버로 연결 요청을 보내겠다.)
		// > 누구랑 연결할건지 상대방의 IP 주소 및 Port 번호를 넘기면서
		
		
			 socket = new Socket(serverIp, serverPort);
		// 3) 서버로 연결 요청 후 연결 요청이 잘 수락되었는지 검사
		// > 연결이 잘 될 경우 : Socket 객체가 제대로 생성됨
		//   연결이 잘 안된 경우 : null 값이 Socket 객체에 담김
		if(socket !=null) {
			// > 연결이 잘 된 경우
			//   나머지 단계 이어 쓰기
			System.out.println("서버와 연결 성공!");
			
			
			// 4) 서버와 통신할 수 있는 입출력 스트림 생성
			// 5) 보조 스트림을 추가하여 성능 개선
			
			// - 입력용 스트림
			// socket.getInputStream()
			// + InputStreamReader 객체 (사이즈 호환용도)
			// BufferedReader
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// - 출력용 스트림
			// socket.getOutputStream()
			//+
			// PrintWriter 객체
			 pw = new PrintWriter(socket.getOutputStream());
			
			// 6) 스트림을 통해 데이터 읽고 쓰기
			// > 마찬가지로 무한 반복문 안에서 작성
			while(true) {
				
				// 6_1) 서버로 데이터를 전달 (내보내기)
				System.out.print("서버에게 보낼 내용 : " );
				String sendMessage =sc.nextLine();
				pw.println(sendMessage);
				
				// 6_2) 현재 스트림에 남아있는 데이터 내보내기
				pw.flush();
				
				// 6_3) 서버로부터 전달된 데이터 읽기
				String message = br.readLine();
				System.out.println("서버 : " + message); 
				
				
				}
			
			
			}else {
				System.out.println("서버와 연결 실패!");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
			// 7) 통신 종료 절차 (자원 반납 역순으로)
			pw.close();
			br.close();
			socket.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
	}

}
