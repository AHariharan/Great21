package com.adansoft.great21.dataaccess.helpers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

public class CacheServerActivateAccountCache {

	@Autowired
	RedisTemplate<String, String> accountActivationRedisTemplate;
	
	public void addEmailActivationtoCache(String emailaddress,String activationCode)
	{
		this.accountActivationRedisTemplate.opsForHash().put(emailaddress, emailaddress.hashCode(), activationCode);		
	}
	
	public String lookupActivationbyEmail(String emailaddress)
	{
		return (String) this.accountActivationRedisTemplate.opsForHash().get(emailaddress, emailaddress.hashCode());
	}
	
	public void deleteActivationCode(String emailaddress)
	{
		this.accountActivationRedisTemplate.delete(emailaddress);
	}
	
}
