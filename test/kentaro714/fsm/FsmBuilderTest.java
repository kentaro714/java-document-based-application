package kentaro714.fsm;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class FsmBuilderTest {
	
	FiniteStateMachine fsm;
	
	@Before
	public void setUp() {
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
				System.out.println("Rejected#proceed");
			}
		}).build();
		FsmBuilder.state(notAccepted).on(proceed).to(accepted);
		FsmBuilder.state(accepted).on(proceed).to(approved);
		FsmBuilder.state(approved).on(reject).to(rejected);
		fsm = FiniteStateMachine.newInstance(notAccepted);
	}
	
	@Test
	public void testInitialEvent() throws GuardException {
		fsm.fire(new Event("proceed"));
		Assert.assertEquals("Accepted", fsm.current().getName());
	}
}
