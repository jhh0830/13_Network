package com.kh.chap02_tcp.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

//Ŭ���̾�Ʈ�� ���α׷�
public class ClientProgram {
	
	/*
	 * * Client �� ���α׷� �帧
	 * 1. ��û�ϰ��� �ϴ� ������ ������ �����ϱ� (������ ����)
	 * 2. ������ ���� ��û�� ������ (Socket ��ü�� ���������ν�)
	 * 3. ������ �� �Ǿ��ٸ� Socket ��ü�� ����� ������ ��!!
	 * 4. ������ ����� �� �ִ� ����� ��Ʈ���� Socket ��ü�κ��� ����
	 * 5. ������Ʈ���� �߰��Ͽ� ���� ����
	 * 6. ��Ʈ���� ���ؼ� ������ �а� ����
	 * 7. ��� ���� ���� (�������� �ݵ��)
	 * 
	 * 
	 */
	public static void main(String[] args) {
		
		System.out.println("*** ������� ***");
		// �ڿ� ���� null�� �ʱ�ȭ
		Socket socket = null;
		BufferedReader br= null;
		PrintWriter pw= null;
		
		Scanner sc = new Scanner(System.in);
		try {
		// 1) ��û�ϰ��� �ϴ� ������ ���� (IP �ּ�, Port ��ȣ) ����
		// - ��û�ϰ��� �ϴ� ������ IP �ּ� : 127.0.0.1 �Ǵ� localhost
		//  ( 127.0.0.1 ���� �� IP, �ڱ� �ڽ��� IP �ּ�)
		// - ��û�ϰ��� �ϴ� ������ Port ��ȣ : 3000
		String serverIp = "127.0.0.1"; // �Ǵ� "localhost"
		int serverPort = 3000;
		
		// 2) Socket ��ü ����(== �� ������ ���� ��û�� �����ڴ�.)
		// > ������ �����Ұ��� ������ IP �ּ� �� Port ��ȣ�� �ѱ�鼭
		
		
			 socket = new Socket(serverIp, serverPort);
		// 3) ������ ���� ��û �� ���� ��û�� �� �����Ǿ����� �˻�
		// > ������ �� �� ��� : Socket ��ü�� ����� ������
		//   ������ �� �ȵ� ��� : null ���� Socket ��ü�� ���
		if(socket !=null) {
			// > ������ �� �� ���
			//   ������ �ܰ� �̾� ����
			System.out.println("������ ���� ����!");
			
			
			// 4) ������ ����� �� �ִ� ����� ��Ʈ�� ����
			// 5) ���� ��Ʈ���� �߰��Ͽ� ���� ����
			
			// - �Է¿� ��Ʈ��
			// socket.getInputStream()
			// + InputStreamReader ��ü (������ ȣȯ�뵵)
			// BufferedReader
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			// - ��¿� ��Ʈ��
			// socket.getOutputStream()
			//+
			// PrintWriter ��ü
			 pw = new PrintWriter(socket.getOutputStream());
			
			// 6) ��Ʈ���� ���� ������ �а� ����
			// > ���������� ���� �ݺ��� �ȿ��� �ۼ�
			while(true) {
				
				// 6_1) ������ �����͸� ���� (��������)
				System.out.print("�������� ���� ���� : " );
				String sendMessage =sc.nextLine();
				pw.println(sendMessage);
				
				// 6_2) ���� ��Ʈ���� �����ִ� ������ ��������
				pw.flush();
				
				// 6_3) �����κ��� ���޵� ������ �б�
				String message = br.readLine();
				System.out.println("���� : " + message); 
				
				
				}
			
			
			}else {
				System.out.println("������ ���� ����!");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
			// 7) ��� ���� ���� (�ڿ� �ݳ� ��������)
			pw.close();
			br.close();
			socket.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
	}

}
