package kr.or.ddit.basic;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 부모 클래스가 Serializable 인터페이스를 구현하고 있지 않을 경우
 * 부모객체의 필드값 처리 방법
 * 1. 부모클래스가 Serizlizable인터페이스를 구현하도록 해야한다.
 * 2. 자식 클래스에 writeObject()와 readObject()메서드를 이용하여 부모객체의
 *		필드값을 처리할 수 있도록 직접 구현한다.
 * @author pc18
 *
 */
public class T15_NonSerializableParentTest {
	public static void main(String[] args) throws Exception {
		FileOutputStream fos = new FileOutputStream("d:/D_Other/nonSerializableTest");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
			
		Child child = new Child();
		child.parentName = "부모";
		child.childName = "자식";
		oos.writeObject(child); // 직렬화
		oos.flush();
		oos.close();
		fos.close(); // 생략가능
		
		FileInputStream fis = new FileInputStream("d:/D_Other/nonSerializableTest");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		Child child2 = (Child) ois.readObject(); // 역직렬화
		System.out.println("parentName : " + child2.parentName);
		System.out.println("childName : " + child2.childName);
		ois.close();
		fis.close(); // 생략가능
	}
}

/**
 * Serializable을 구현하지 않은 부모 클래스
 * @author pc18
 *
 */
class Parnet {
	public String parentName;

}

/**
 * Serializable을 구현한 자식 클래스
 * @author pc18
 *
 */
class Child extends Parnet implements Serializable {
	public String childName;

	/**
	 * 직렬화 될때 자동으로 호출됨.
	 * (접근 제한자가 private이 아니면 자동 호출되지 않음.)
	 * @param out
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void writeObject(ObjectOutputStream out) throws IOException {	// 재정의 해준것임.
		out.writeUTF(parentName);	// ObjectOuputStream객체의 메서드를 이용하여
									// 부모객체 필드값 처리
		
		out.defaultWriteObject(); // 새로정의한 out.writeUTF실행하고 기존의 writeObject 실행.
	}
	
	/**
	 * 역직렬화 될때 자동으로 호출됨.
	 * (접근 제한자가 private이 아니면 자동호출 되지 않음.)
	 * @param in
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		parentName = in.readUTF();	// ObjectInputStream 객체의 메서드를 이용하여
									// 부모객체 필드값 처리.
		
		in.defaultReadObject(); 
	}
}
