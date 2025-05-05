package br.eti.ljr.sn.clientesrv;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

@Provider
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class JacksonConfig extends JacksonJsonProvider {

	private static final DateFormat DATE_TIME_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

	public JacksonConfig() {
		ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        SimpleModule module = new SimpleModule("DateModule", Version.unknownVersion());
        module.addDeserializer(Date.class, new JacksonDateDeserializer(DATE_TIME_FORMAT));
        module.addSerializer(Date.class, new JacksonDateSerializer(DATE_TIME_FORMAT));

        objectMapper.registerModule(module);

        setMapper(objectMapper);
	}
}