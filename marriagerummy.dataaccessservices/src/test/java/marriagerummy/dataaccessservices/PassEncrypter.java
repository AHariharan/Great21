package marriagerummy.dataaccessservices;

import org.jasypt.util.password.ConfigurablePasswordEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;

public class PassEncrypter {

	
	public static void main(String args[])
	{
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("Letmein@123");
		String result = textEncryptor.encrypt("P3ecost1");
	    System.out.println("Result :- " + result);
	}
}
