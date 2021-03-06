package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 파일 인코딩을 이용하여 읽어오기
 * @author pc18
 *
 */
public class T09_FileEncodingTest {
	public static void main(String[] args) {
		// InputStreamReader => 바이트기반 스트림을 문자기반 스트림으로 변환해주는 보조 스트림.(파일은 기본적으로 byte이기 때문에 문자로 변환해줘야함)
		FileInputStream fin = null;
		InputStreamReader isr = null;
		
		try {
			/*
				FileInputStream객체를 생성한 후 이 객체를 매개변수로 받는 
				InputStreamReader객체를 생성한다.
				(바이트 입력 스트림에 연결되어 문자 입력 스트림인 Reader로 변환시켜주는 보조프로그램)
			 */
			fin = new FileInputStream("d:/D_Other/test_utf8.txt");
//			fin = new FileInputStream("d:/D_Other/test_ansi.txt");
			
			/*
				InputStreamReader객체는 파일의 인코딩 방식을 지정할 수 있다.
				형식) new InputStreamReader(바이트기반스트림객체, 인코딩 방식)
				 인코딩 방식
				 - MS949 => 윈도우의 기본 한글 인코딩 방식(ANSI)
				 - UTF-8 => 유니코드 UTF-8 인코딩 방식
				 - US-ASCII => 영문 전용 인코딩 방식
				 
				 ANSI는 영어를 표기하기 위해 만든 코드로 규격 자체에 한글이 없었다가 나중에
				 여기에 euc-kr, cp949 이라는 식으로 한글이 포함됨.
			 */
//			isr = new InputStreamReader(fin);
//			isr = new InputStreamReader(fin, "MS949");
			isr = new InputStreamReader(fin, "UTF-8");
			
			int c;
			while((c=isr.read()) != -1) {
				System.out.print((char)c);
			}
			System.out.println();
			System.out.println("출력 끝...");
			
			isr.close();
//			fin.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
