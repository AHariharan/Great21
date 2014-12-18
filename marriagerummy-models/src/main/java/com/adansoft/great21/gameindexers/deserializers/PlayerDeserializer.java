package com.adansoft.great21.gameindexers.deserializers;

import java.io.IOException;

import com.adansoft.great21.models.HumanPlayer;
import com.adansoft.great21.models.Player;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class PlayerDeserializer extends JsonDeserializer<Player> {

	@Override
	public Player deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		
		if(jp.getText().length() == 0)
			return null;
		
		return new HumanPlayer("Test");
	}

}
