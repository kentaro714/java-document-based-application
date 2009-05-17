package kentaro714.documents.domain.foo;

import java.util.Date;

import org.springmodules.validation.bean.conf.loader.annotation.handler.MaxLength;

import kentaro714.documents.domain.Document;
import kentaro714.documents.domain.ValidationError;
import kentaro714.documents.domain.ValidationErrors;
import kentaro714.fsm.Action;
import kentaro714.fsm.Event;
import kentaro714.fsm.FiniteStateMachine;
import kentaro714.fsm.FsmBuilder;
import kentaro714.fsm.GuardException;
import kentaro714.fsm.State;

public class FooDocument implements Document {

	private FiniteStateMachine fsm;
	
	@MaxLength(5)
	private String propertyA;
	private int propertyB;
	private Date propertyC;
	
	public FooDocument() {
		Event proceed = FsmBuilder.event("proceed").build();
		Event reject = new Event("reject");
		
		State notAccepted = FsmBuilder.state("NotAccepted").on(proceed).action(new Action() {
			@Override
			public void execute() {
				System.out.println("NotAccepted#proceed");
			}
		}).build();
		State accepted = FsmBuilder.state("Accepted").on(proceed).action(new Action() {
			@Override
			public void execute() {
				System.out.println("Accepted#proceed");
			}
		}).build();
		State approved = FsmBuilder.state("Approved").on(proceed).action(new Action() {
			@Override
			public void execute() {
				System.out.println("Approved#proceed");
			}
		}).build();
		State rejected = FsmBuilder.state("Rejected").on(proceed).action(new Action() {
			@Override
			public void execute() {
				propertyA = "new";
				System.out.println("Rejected#proceed");
			}
		}).build();
		FsmBuilder.state(notAccepted).on(proceed).to(accepted);
		FsmBuilder.state(accepted).on(proceed).to(approved);
		FsmBuilder.state(approved).on(reject).to(rejected);
		fsm = FiniteStateMachine.newInstance(notAccepted);
	}
	
	@Override
	public void proceed(ValidationErrors errors) {
		try {
			fsm.fire(new Event("proceed"));
		} catch (GuardException e) {
			// awkward...
			copyErrors(e.getErrors(), errors);
		}
	}

	@Override
	public void delete(ValidationErrors errors) {
		try {
			fsm.fire(new Event("delete"));
		} catch (GuardException e) {
			// awkward...
			copyErrors(e.getErrors(), errors);
		}
	}

	@Override
	public void reject(ValidationErrors errors) {
		try {
			fsm.fire(new Event("reject"));
		} catch (GuardException e) {
			// awkward...
			copyErrors(e.getErrors(), errors);
		}
	}
	public String status() {
		return fsm.current().getName();
	}
	
	private void copyErrors(ValidationErrors from, ValidationErrors to) {
		for (ValidationError error : from.errors()) {
			to.addError(error);
		}
	}

	String getPropertyA() {
		return propertyA;
	}

	void setPropertyA(String propertyA) {
		this.propertyA = propertyA;
	}

	int getPropertyB() {
		return propertyB;
	}

	void setPropertyB(int propertyB) {
		this.propertyB = propertyB;
	}

	Date getPropertyC() {
		return propertyC;
	}

	void setPropertyC(Date propertyC) {
		this.propertyC = propertyC;
	}
	
}
