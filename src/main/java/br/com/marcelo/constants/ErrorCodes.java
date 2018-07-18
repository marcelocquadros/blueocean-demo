package br.com.marcelo.constants;

public enum ErrorCodes {
	
	INTERNAL_SERVER_ERROR("Internal server error"),
	INVALID_REQUEST("Invalid request"),
	VALIDATION_FAILED("Validation failed"),
	PRODUCT_NOT_FOUND("Product not found");
	
	private final String message;
	
	ErrorCodes(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
