package com.conversion.test;

import java.io.File;
import java.io.IOException;

import com.adansoft.great21.gameindexers.deserializers.PlayerKeyDeserializer;
import com.adansoft.great21.games.SevenCardRummy;
import com.adansoft.great21.models.Player;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class JSONtoJava {

	public static void main(String[] args)
	   {
	      SevenCardRummy rmmy = null;
	      ObjectMapper mapper = new ObjectMapper();
	      SimpleModule module = new SimpleModule();
	      module.addKeyDeserializer(Player.class,new PlayerKeyDeserializer());
	      mapper.registerModule(module);
	      try
	      {
	    	  rmmy =  mapper.readValue(new File("c://temp/employee.json"), SevenCardRummy.class);
	      } catch (JsonGenerationException e)
	      {
	         e.printStackTrace();
	      } catch (JsonMappingException e)
	      {
	         e.printStackTrace();
	      } catch (IOException e)
	      {
	         e.printStackTrace();
	      }
	      System.out.println(rmmy + rmmy.getGameInstanceId());
	   }
}
