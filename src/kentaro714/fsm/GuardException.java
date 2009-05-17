package kentaro714.fsm;

import kentaro714.documents.domain.ValidationErrors;

public class GuardException extends Exception {
	private ValidationErrors errors;

	public GuardException(ValidationErrors errors) {
		this.errors = errors;
	}
	
	public ValidationErrors getErrors() {
		return errors;
	}

	public void setErrors(ValidationErrors errors) {
		this.errors = errors;
	}
}
