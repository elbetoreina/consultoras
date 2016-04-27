package com.consultoras.app.common.model;

public class ResourceMessage {
	private final String resource;

	private static final String KEY_EXISTENT = "%s.existent";
	private static final String MESSAGE_EXISTENT = "Ya existe un cliente con ese Primer Nombre, Primer Apellido y Numero de Celular.";
	private static final String MESSAGE_INVALID_FIELD = "%s.invalidField.%s";
	private static final String KEY_NOT_FOUND = "%s.NotFound";
	private static final String MESSAGE_NOT_FOUND = "%s no encontrado";

	public ResourceMessage(final String resource) {
		this.resource = resource;
	}

	public String getKeyOfResourceExistent() {
		return String.format(KEY_EXISTENT, resource);
	}

	public String getMessageOfResourceExistent(final String fieldsNames) {
		return String.format(MESSAGE_EXISTENT, resource, fieldsNames);
	}

	public String getKeyOfInvalidField(final String invalidField) {
		return String.format(MESSAGE_INVALID_FIELD, resource, invalidField);
	}

	public String getKeyOfResourceNotFound() {
		return String.format(KEY_NOT_FOUND, resource);
	}

	public String getMessageOfResourceNotFound() {
		return String.format(MESSAGE_NOT_FOUND, resource);
	}

}
