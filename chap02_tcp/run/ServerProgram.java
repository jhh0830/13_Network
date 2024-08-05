package com.kh.chap02_tcp.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerProgram {
	
	/*
	 * * TCP (Transmission Control Protocol)
	 * - ����, Ŭ���̾�Ʈ ���� 1:1 ���� ���
	 * - �����͸� ��ȯ�ϱ⿡ �ռ� ����, Ŭ���̾�Ʈ�� ���� ����Ǿ��־�� ��
	 * 	(�׻� ������ ���� ����Ǽ� Ŭ���̾�Ʈ�� ��û�� ��ٸ� ��)
	 * - �ŷڼ� �ִ� �����͸� ���� ���� (���� ��������)
	 * 
	 * * Socket Ŭ����
	 * - ���μ��� ���� ����� ����ϴ� ����� ���ǵǾ��ִ� �ڹ� Ŭ����
	 * - Socket ���α׷����� ���� ��� ��Ʈ���� Input / OutputStream�� ������ ����
	 * 
	 * 
	 * * ServerSocket Ŭ����
	 * - ������ ���α׷����� �߰��� �ʿ��� ��ü Ÿ��
	 * - Ŭ���̾�Ʈ �ʿ��� ���� ��û�� ������ �� �� ���� ��û��
	 * 	 "���� (accept)" ���ִ� �뵵
	 * > ���� ��û�� �����ϰ� �Ǹ� �ٷ� Socket ��ü�� �� �� ����!!
	 * 
	 * * Server �� ���α׷� �帧
	 * 1. Server �� ���α׷����� ����� Port��ȣ �����ϱ�
	 * 2. ServerSocket ��ü ���� (Port ��ȣ���ѱ�鼭) > ��Ʈ ���� (Bind)
	 * 3. Ŭ���̾�Ʈ�� ���� ���� ��û�� �ö� ���� "���" ����
	 * 4.  ���� ��û�� ���� ��û ���� (accept) ��
	 * 		���� �� Socket ��ü�� ������
	 * 5. Ŭ���̾�Ʈ�� ����� �� �ִ� ����� ��Ʈ�� ��ü ����
	 * 6. ���� ��Ʈ���� �߰��Ͽ� ���� ����
	 * 7. ��Ʈ���� ���� ������ �а� ����
	 * 8. ��� ���� ���� (��Ʈ�� close, ���� close)
	 * 
	 * 
	 */

	public static void main(String[] args) {
		
		
		System.out.println("*** ������� ***");
		
		Scanner sc = new Scanner(System.in);
		
		// �������� �̽��� �ذ��ϱ� ���� �ڿ� �������� ���� �� null�� �ʱ�ȭ
		ServerSocket server = null;
		Socket socket = null;
		BufferedReader br = null;
		PrintWriter pw  = null;
		
		// 1) ���� ���α׷����� ����� ��Ʈ��ȣ ���� ���� (������)
		int port = 3000;
		
		// 2) ��Ʈ��ȣ�� �ѱ�鼭 ServerSocket ��ü ����(��Ʈ ����, Bind)
		
		try {
			
			server = new ServerSocket(port);
			
		// 3) Ŭ���̾�Ʈ�κ��� ���� ��û�� �� �� ���� ��� ����
		System.out.println("Ŭ���̾�Ʈ�� ��û�� ��ٸ��� �ֽ��ϴ�..");	
		
		// 4) ���� ��û�� ������ ��û ���� (accept) ��
		//    ��ٷ� Socket ��ü�� ������
		// > ���� ��û�� �������ִ� �޼ҵ� : accept() �޼ҵ�
		//   (ServerSocekt ��ü���� ����)
		socket = server.accept();
		
		// ������ ����Ǿ����� ����غ���
		// > Socket ��ü�� �޼ҵ带 ���ؼ� ������ ����Ǿ����� �˾Ƴ� �� ����
		// socket.getInetAddress()
		
		System.out.println(socket.getInetAddress().getHostAddress()+ "�� ������ ��û��..");
		
		// 5) Ŭ���̾�Ʈ�� ����� �� �ִ� ����� ��Ʈ�� ��ü ����
		// > Socket ��ü���� �����ϴ� ��Ʈ�� �̿�
		// 6) ������Ʈ���� �߰��Ͽ� ���� ����
		
		// - �Է¿� ��Ʈ�� 
		// Ŭ���̾�Ʈ�� ������ �޼��� ������ �о���̱� ���� ��Ʈ��
		
		// ��� ��Ʈ�� : InputStream ��ü (socket.getInputStream())
		
		// + InputStreamReader ��ü(ȣȯ�� ������Ʈ�� ��ü)
		// + ����� �߰����� ȣȯ���ִ� ��ü (������Ʈ������ ���)
		// ������Ʈ�� : BufferedReader ��ü
		// > ������ ������, ����� ����� �ٸ�!! (��Ģ�� ������ �Ұ���)
		//   �߰����� ����� ȣȯ���ִ� ������Ʈ�� ��ü�� �ϳ� �� ���� ��!!
		//   ��, ��ݽ�Ʈ���� ������Ʈ���� 2�� ���̰ڴ�.
		
		
		br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		// - ��¿� ��Ʈ��
		// Ŭ���̾�Ʈ���� �޼����� ���� (��������) ���� ��Ʈ��
		
		// ��ݽ�Ʈ�� : OutputStream ��ü (socket.getOutputStream())
		// + 
		// ������Ʈ�� : PrintWriter ��ü
		// > ����� ������ ������ ����� ����� �ٸ�!! (��Ģ�� ���� �Ұ�)
		// >  OutputStreamWriter ��ü : �߰����� ������ ȣȯ���ִ� ��ü
		//    �� �����ϱ�� ������, PrintWriter �� ��� �ϵ� ���� ���̴� ����
		//    ���� OutputStreamWriter ��ü �ʿ� ����, ��ٷ�
		//    1byte ¥�� ��ݽ�Ʈ���� �����ؼ� �� �� �ֵ��� �����Ǿ�����!!
		
		pw = new PrintWriter(socket.getOutputStream());
		
		// 7) ��Ʈ���� ���� ������ �а� ����
		// > ä�� ���α׷��� ���������� �޼����� �ְ� �޾������� !!
		//   ���� �ݺ��� �ȿ��� �а� ���� ���� �ۼ��ϱ�
		while(true) {
			
			//7_1) Ŭ���̾�Ʈ�κ��� ���޵� �޽��� �о���̱�
			// > BufferedReader �� �б�
			String message = br.readLine();
			System.out.println("Ŭ���̾�Ʈ : "+ message);
			
			// 7_2) �ݴ�� Ŭ���̾�Ʈ���� �޼��� ����
			// > PrintWriter�� ��������
			System.out.print("Ŭ���̾�Ʈ���� ���� ���� : ");
			String sendMessage = sc.nextLine();
			
			pw.println(sendMessage);
			// > ���� ������ �������� �޼ҵ�
			
			// 7_3) ���� ��Ʈ���� �����ִ� �����͸� ������ �������� �޼ҵ� ȣ��
			pw.flush(); 			
			// > ������ ���ѹݺ��� �ȿ��� ��� �����͸� �������� �ϱ� ������ ȣ��!!
			
		
		}
		
		
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			
			// 8) ��� ���� ���� (�ڿ� �ݳ�)
			// > ������ ������ �������� �ݳ��ϱ�!!
			try {
			pw.close();
			br.close();
			socket.close();
			server.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
			
		}
		
	
		
		
	}

}
