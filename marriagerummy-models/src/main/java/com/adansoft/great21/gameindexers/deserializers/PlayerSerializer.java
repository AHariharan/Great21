package com.adansoft.great21.gameindexers.deserializers;

import java.io.IOException;

import com.adansoft.great21.models.Player;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PlayerSerializer extends JsonSerializer<Player>{

	@Override
	public void serialize(Player value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		if(value == null)
		{
			jgen.writeStartObject();
			jgen.writeEndObject();
		}
	}
}
