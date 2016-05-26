package com.consultoras.app.consultora.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import com.consultoras.app.common.json.JsonReader;
import com.consultoras.app.consultora.model.Consultora;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@ApplicationScoped
public class ConsultoraJsonConverter {
	
	public Consultora convertFrom(final String json) throws IllegalArgumentException {
		final JsonObject jsonObject = JsonReader.readAsJsonObject(json);

		final Consultora consultora = new Consultora();
		
		
		
		
		
		consultora.setPrimerNombre(JsonReader.getStringOrNull(jsonObject, "primerNombre"));		
		consultora.setSegundoNombre(JsonReader.getStringOrNull(jsonObject, "segundoNombre"));
		consultora.setPrimerApellido(JsonReader.getStringOrNull(jsonObject, "primerApellido"));
		consultora.setSegundoApellido(JsonReader.getStringOrNull(jsonObject, "segundoApellido"));
		consultora.setApellidoCasada(JsonReader.getStringOrNull(jsonObject, "apellidoCasada"));
		consultora.setCodigoConsultora(JsonReader.getStringOrNull(jsonObject, "codigoConsultora"));		
		consultora.setEmail(JsonReader.getStringOrNull(jsonObject, "email"));
		consultora.setNit(JsonReader.getStringOrNull(jsonObject, "nit"));
		consultora.setTelefono(JsonReader.getStringOrNull(jsonObject, "telefono"));				

		return consultora;
	}

	public JsonElement convertToJsonElement(final Consultora consultora) {
		final JsonObject jsonObject = new JsonObject();		
		
		
		jsonObject.addProperty("id", consultora.getId());
		jsonObject.addProperty("primerNombre", consultora.getPrimerNombre());
		jsonObject.addProperty("segundoNombre", consultora.getSegundoNombre());
		jsonObject.addProperty("primerApellido", consultora.getPrimerApellido());
		jsonObject.addProperty("segundoApellido", consultora.getSegundoApellido());
		jsonObject.addProperty("apellidoCasada", consultora.getApellidoCasada());		
		jsonObject.addProperty("codigoConsultora", consultora.getCodigoConsultora());
		jsonObject.addProperty("email", consultora.getEmail());
		jsonObject.addProperty("nit", consultora.getNit());
		jsonObject.addProperty("telefono", consultora.getTelefono());		
		
		return jsonObject;
	}

	public JsonElement convertToJsonElement(final List<Consultora> consultoras) {
		final JsonArray jsonArray = new JsonArray();

		for (final Consultora consultora : consultoras) {
			jsonArray.add(convertToJsonElement(consultora));
		}

		return jsonArray;
	}

}
