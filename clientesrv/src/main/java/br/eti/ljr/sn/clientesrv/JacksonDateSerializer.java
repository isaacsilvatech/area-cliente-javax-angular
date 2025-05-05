package br.eti.ljr.sn.clientesrv;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class JacksonDateSerializer extends JsonSerializer<Date> {

	private final DateFormat dateFormat;

	public JacksonDateSerializer(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		String formattedDate = dateFormat.format(value);
		gen.writeString(formattedDate);

	}
}
