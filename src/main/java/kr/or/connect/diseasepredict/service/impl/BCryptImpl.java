package kr.or.connect.diseasepredict.service.impl;

import org.mindrot.jbcrypt.BCrypt;

import kr.or.connect.diseasepredict.service.EncryptHelper;

public class BCryptImpl implements EncryptHelper {
	@Override
	public String encrypt(String password) {
		// TODO Auto-generated method stub
		return BCrypt.hashpw(password, BCrypt.gensalt()); 
	}

	@Override
	public boolean isMatch(String password, String hashed) {
		// TODO Auto-generated method stub
		return BCrypt.checkpw(password, hashed);
	}
	
}
