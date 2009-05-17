package kentaro714.documents.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidationErrors {

	private List<ValidationError> errors = new ArrayList<ValidationError>();
	
	public List<ValidationError> errors() {
		return Collections.unmodifiableList(errors);
	}

	public void addError(ValidationError error) {
		errors.add(error);
	}

}
