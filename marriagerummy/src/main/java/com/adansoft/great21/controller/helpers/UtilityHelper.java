package com.adansoft.great21.controller.helpers;

import org.springframework.mobile.device.Device;

public class UtilityHelper {

	public static String detectDevice(Device device)
	{
		if(device != null)
		{
			if(device.isMobile())
				 return "MOBILE";
			if(device.isNormal())
				 return "BROWSER";
			if(device.isTablet())
				 return "TABLET";			
			return "BOT";
		}
		return "UNKNOWN";
	}
	
}
