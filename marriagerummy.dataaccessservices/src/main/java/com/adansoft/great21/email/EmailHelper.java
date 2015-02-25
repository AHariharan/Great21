package com.adansoft.great21.email;

import java.io.StringWriter;
import java.util.HashMap;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

public class EmailHelper {

	private  VelocityEngine velocityEngine;
	

	public EmailHelper(VelocityEngine velocityEngine){
		this.velocityEngine = velocityEngine;	
		velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
		velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
		velocityEngine.init();
	}
	
	public String CreateSignUpEmail(HashMap<String,Object> varMap){
		
		
		System.out.println("Velocity Resource path " + velocityEngine.getTemplate("SignUpEmail.vm"));
		
		VelocityContext context = new VelocityContext();
		for(String key : varMap.keySet())
		{
			context.put(key, varMap.get(key));
		}
	
		StringWriter writer = new StringWriter();
		
		velocityEngine.mergeTemplate("SignUpEmail.vm", "UTF-8", context, writer);
		return writer.toString();
	}
}
