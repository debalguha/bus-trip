package org.progressivelifestyle.bustrip.web;

public enum ERROR_CODE {
	NA("NA"), REG_NO_ID("Id front and back both required"), REG_DUPLICATE_EMAIL("This email id already exists"), REG_DUPLICATE_USER("The user name has already been taken"),
	EVENT_SUBMIT_ERROR("Unable to persist the event detail"), REG_PHONE_VERIFICATION_FAILED("Unable to verify your phone."), UNKNOWN("An unexcpected error occurred. Please check server log for further details."),
	LOCATION_NOT_AVLBL("Bus location not available today for the event");
	private String errorMessage;
	ERROR_CODE(String errMsg){
		this.errorMessage = errMsg;
	}
	@Override
	public String toString() {
		return errorMessage;
	}
}
