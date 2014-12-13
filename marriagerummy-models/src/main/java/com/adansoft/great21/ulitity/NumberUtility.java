package com.adansoft.great21.ulitity;

import java.util.Random;

public class NumberUtility {
	
	public static int generateRandomNumber(int startrange,int endrange)
	{
		Random random = new Random();
		long range = (long)endrange - (long)startrange + 1;
	    long fraction = (long)(range * random.nextDouble());
	    int randomNumber =  (int)(fraction + startrange);    
		return randomNumber;
	}

}
