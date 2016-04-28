package com.consultoras.app.common.model;

import com.consultoras.app.common.exception.FieldNotValidException;

public final class StandardsOperationResults {

	private StandardsOperationResults() {
	}

	public static OperationResult getOperationResultExistent(final ResourceMessage resourceMessage,
			final String fieldsNames) {
		return OperationResult.error(resourceMessage.getKeyOfResourceExistent(),
				resourceMessage.getMessageOfResourceExistent(fieldsNames));
	}

	public static OperationResult getOperationResultInvalidField(final ResourceMessage resourceMessage,
			final FieldNotValidException ex) {
		return OperationResult.error(resourceMessage.getKeyOfInvalidField(ex.getFieldName()), ex.getMessage());
	}

	public static OperationResult getOperationResultNotFound(final ResourceMessage resourceMessage) {
		return OperationResult.error(resourceMessage.getKeyOfResourceNotFound(),
				resourceMessage.getMessageOfResourceNotFound());
	}

	public static OperationResult getOperationResultIllegalArgument(final ResourceMessage resourceMessage,
			final IllegalArgumentException ex) {

		if (ex.getMessage().contains("enum")) {
			return OperationResult.error("Argumentos No Validos para Enumeracion", ex.getMessage());
		} else if (ex.getMessage().contains("input string")) {
			return OperationResult.error("Argumentos No Validos de Fecha", ex.getMessage());
		} else {
			return OperationResult.error("Error Generico de Insercion", ex.getMessage());
		}
	}

}