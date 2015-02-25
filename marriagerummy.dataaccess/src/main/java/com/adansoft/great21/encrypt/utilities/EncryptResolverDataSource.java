package com.adansoft.great21.encrypt.utilities;

import org.apache.commons.dbcp2.BasicDataSource;
import org.jasypt.util.text.BasicTextEncryptor;

public class EncryptResolverDataSource extends BasicDataSource {

	@Override
	public void setPassword(String password) {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("Letmein@123");
		String result = textEncryptor.decrypt(password);
		super.setPassword(result);
	}
	
}
