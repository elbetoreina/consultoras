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
			final Exception ex) {

		if (ex.getMessage().contains("enum")) {
			return OperationResult.error("Argumentos No Validos para Enumeracion", ex.getMessage());
		} else if (ex.toString().contains("NumberFormatException")) {
			return OperationResult.error("Argumentos Numericos No Validos", ex.toString());
		} else {
			return OperationResult.error("Error Generico de Insercion", ex.toString());
		}
	}

}