package com.consultoras.app.cliente.resource;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;

import static com.consultoras.app.common.utils.CalendarUtils.*;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.consultoras.app.cliente.model.Cliente;
import com.consultoras.app.cliente.model.ColorCabello;
import com.consultoras.app.cliente.model.ColorOjos;
import com.consultoras.app.cliente.model.FormaCara;
import com.consultoras.app.cliente.model.RangoEdad;
import com.consultoras.app.cliente.model.TipoLabios;
import com.consultoras.app.cliente.model.TipoPiel;
import com.consultoras.app.cliente.model.TonoBase;
import com.consultoras.app.cliente.model.TonoPiel;
import com.consultoras.app.common.json.JsonReader;

@ApplicationScoped
public class ClienteJsonConverter {
	
	public Cliente convertFrom(final String json) throws IllegalArgumentException {
		final JsonObject jsonObject = JsonReader.readAsJsonObject(json);

		final Cliente cliente = new Cliente();
		cliente.setPrimerNombre(JsonReader.getStringOrNull(jsonObject, "primerNombre"));
		cliente.setSegundoNombre(JsonReader.getStringOrNull(jsonObject, "segundoNombre"));
		cliente.setPrimerApellido(JsonReader.getStringOrNull(jsonObject, "primerApellido"));
		cliente.setSegundoApellido(JsonReader.getStringOrNull(jsonObject, "segundoApellido"));
		cliente.setApellidoCasada(JsonReader.getStringOrNull(jsonObject, "apellidoCasada"));
		cliente.setFechaNacimiento(trimDateFromEpoch(JsonReader.getStringOrNull(jsonObject, "fechaNacimiento")));		
		cliente.setFechaAniversario(trimDateFromEpoch(JsonReader.getStringOrNull(jsonObject, "fechaAniversario")));
		cliente.setHoraLocalizacion(JsonReader.getStringOrNull(jsonObject, "horaLocalizacion"));
		cliente.setDireccion(JsonReader.getStringOrNull(jsonObject, "direccion"));
		cliente.setEmail(JsonReader.getStringOrNull(jsonObject, "email"));
		cliente.setCelular(JsonReader.getStringOrNull(jsonObject, "celular"));
		cliente.setTelefonoCasa(JsonReader.getStringOrNull(jsonObject, "telefonoCasa"));
		cliente.setTelefonoOficina(JsonReader.getStringOrNull(jsonObject, "telefonoOficina"));
		cliente.setTelefonoOficinaExtension(JsonReader.getStringOrNull(jsonObject, "telefonoOficinaExtension"));
		cliente.setTelefonoConyuge(JsonReader.getStringOrNull(jsonObject, "telefonoConyuge"));
		cliente.setFotografia(JsonReader.getStringOrNull(jsonObject, "fotografia"));
		
		try{
			cliente.setRangoEdad(RangoEdad.valueOf(JsonReader.getStringOrNull(jsonObject, "rangoEdad")));
			cliente.setTonoBase(TonoBase.valueOf(JsonReader.getStringOrNull(jsonObject, "tonoBase")));
			cliente.setTipoLabios(TipoLabios.valueOf(JsonReader.getStringOrNull(jsonObject, "tipoLabios")));
			cliente.setFormaCara(FormaCara.valueOf(JsonReader.getStringOrNull(jsonObject, "formaCara")));
			cliente.setTipoPiel(TipoPiel.valueOf(JsonReader.getStringOrNull(jsonObject, "tipoPiel")));
			cliente.setTonoPiel(TonoPiel.valueOf(JsonReader.getStringOrNull(jsonObject, "tonoPiel")));
			cliente.setColorCabello(ColorCabello.valueOf(JsonReader.getStringOrNull(jsonObject, "colorCabello")));
			cliente.setColorOjos(ColorOjos.valueOf(JsonReader.getStringOrNull(jsonObject, "colorOjos")));
		}
		catch (IllegalArgumentException e){
			throw e;
		}
				
		cliente.setFechaClientePreferido(trimDateFromEpoch(JsonReader.getStringOrNull(jsonObject, "fechaClientePreferido")));
		cliente.setReferidoPor(JsonReader.getStringOrNull(jsonObject, "referidoPor"));
		

		return cliente;
	}

	public JsonElement convertToJsonElement(final Cliente cliente) {
		final JsonObject jsonObject = new JsonObject();		
		
		
		jsonObject.addProperty("id", cliente.getId());
		jsonObject.addProperty("primerNombre", cliente.getPrimerNombre());
		jsonObject.addProperty("segundoNombre", cliente.getSegundoNombre());
		jsonObject.addProperty("primerApellido", cliente.getPrimerApellido());
		jsonObject.addProperty("segundoApellido", cliente.getSegundoApellido());
		jsonObject.addProperty("apellidoCasada", cliente.getApellidoCasada());
		jsonObject.addProperty("fechaNacimiento", epochFromCalendar(cliente.getFechaNacimiento()));
		jsonObject.addProperty("fechaAniversario", epochFromCalendar(cliente.getFechaAniversario()));
		jsonObject.addProperty("horaLocalizacion", cliente.getHoraLocalizacion());
		jsonObject.addProperty("direccion", cliente.getDireccion());
		jsonObject.addProperty("email", cliente.getEmail());
		jsonObject.addProperty("celular", cliente.getCelular());
		jsonObject.addProperty("telefonoCasa", cliente.getTelefonoCasa());
		jsonObject.addProperty("telefonoOficina", cliente.getTelefonoOficina());
		jsonObject.addProperty("telefonoOficinaExtension", cliente.getTelefonoOficinaExtension());
		jsonObject.addProperty("telefonoConyuge", cliente.getTelefonoConyuge());
		jsonObject.addProperty("fotografia", cliente.getFotografia());
		jsonObject.addProperty("rangoEdad", cliente.getRangoEdad().toString());
		jsonObject.addProperty("tonoBase", cliente.getTonoBase().toString());
		jsonObject.addProperty("tipoLabios", cliente.getTipoLabios().toString());
		jsonObject.addProperty("formaCara", cliente.getFormaCara().toString());
		jsonObject.addProperty("tipoPiel", cliente.getTipoPiel().toString());
		jsonObject.addProperty("tonoPiel", cliente.getTonoPiel().toString());
		jsonObject.addProperty("colorCabello", cliente.getColorCabello().toString());
		jsonObject.addProperty("colorOjos", cliente.getColorOjos().toString());
		jsonObject.addProperty("fechaClientePreferido", epochFromCalendar(cliente.getFechaClientePreferido()));
		jsonObject.addProperty("referidoPor", cliente.getReferidoPor());		
		
		
		return jsonObject;
	}

	public JsonElement convertToJsonElement(final List<Cliente> clientes) {
		final JsonArray jsonArray = new JsonArray();

		for (final Cliente cliente : clientes) {
			jsonArray.add(convertToJsonElement(cliente));
		}

		return jsonArray;
	}

}
