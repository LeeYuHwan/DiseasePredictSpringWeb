package kr.or.connect;

import org.mindrot.jbcrypt.BCrypt;

public class bcryptTest {
	private static String hashPassword(String plainTextPassword) { 
		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt()); 
	}
	
	private static boolean checkPass(String plainPassword, String hasedPassword) { 
		return BCrypt.checkpw(plainPassword, hasedPassword);
	}
	
	public static void main(String[] args) {
		String testPass = hashPassword("1234");
		System.out.println(testPass);
		String falseCheckTest = "1234ewq";
		String trueCheckTest = "1234";
		
		System.out.println(checkPass(falseCheckTest, testPass));
		System.out.println(checkPass(trueCheckTest, "$2a$10$QGK24t3pyqWFwE1L1fRBTO/XO3rWle9H8ebXjgRzVOJRFLiztdIfe"));
	}
}
